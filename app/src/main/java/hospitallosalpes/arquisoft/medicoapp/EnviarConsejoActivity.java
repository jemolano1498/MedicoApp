package hospitallosalpes.arquisoft.medicoapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hospitallosalpes.arquisoft.medicoapp.objects.Consejo;
import hospitallosalpes.arquisoft.medicoapp.objects.Paciente;

public class EnviarConsejoActivity extends AppCompatActivity {

    public EditText consejo;
    public TextView nombrePaciente;
    public Button enviarConsejo;
    public Spinner tiposConsejo;
    public Consejo consejoActual;
    public Paciente pacienteActual;
    public String docId;
    public String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_consejo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        docId = getIntent().getStringExtra("medicoActual");
        pacienteActual = getIntent().getExtras().getParcelable("pacienteActual");
        ip = getIntent().getStringExtra("ip");

        addItemsOnSpinner();
        consejo = (EditText) findViewById (R.id.editTextConsejo);
        enviarConsejo = (Button) findViewById(R.id.buttonEnviarConsejo);
        nombrePaciente = (TextView) findViewById(R.id.textViewPaciente);
        nombrePaciente.setText(pacienteActual.getNombre());
        enviarConsejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consejoActual = crearConsejo (consejo.getText().toString(),docId,String.valueOf(tiposConsejo.getSelectedItem()));

                String url = "http://" + ip + ":8081/pacientes/"+pacienteActual.getId()+"/consejos";
                try {
                    new HttpRequestTask().execute(url,consejo.getText().toString(),docId,String.valueOf(tiposConsejo.getSelectedItem())).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Consejo enviado", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        });


    }

    public Consejo crearConsejo (String descripcion, String idDoctor, String tipo){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date hoy = new Date();
        return new Consejo (hoy.toString() ,  descripcion,  idDoctor, tipo);
    }
    public void addItemsOnSpinner() {

        tiposConsejo = (Spinner) findViewById(R.id.spinnerTiposConsejos);
        List<String> list = new ArrayList<String>();
        list.add("DIETA");
        list.add("ACTIVIDADFISICA");
        list.add("TOMAMEDICAMENTO");
        list.add("ASISTENCIACITAMEDICA");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tiposConsejo.setAdapter(dataAdapter);

    }

    private class HttpRequestTask extends AsyncTask<String, Void, String> {

        String resp;

        @Override
        protected String doInBackground(String... url) {
            try {
                if(android.os.Debug.isDebuggerConnected())
                    android.os.Debug.waitForDebugger();
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date hoy = new Date();
                Consejo obj = new Consejo (hoy.toString() ,  url[1],  url[2], url[3]);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity entity = new HttpEntity(obj,headers);
                restTemplate.exchange(url[0], HttpMethod.POST, entity
                        , String.class);
                return resp;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(String resp) {

        }

    }


}
