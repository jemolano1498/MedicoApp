package hospitallosalpes.arquisoft.medicoapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import hospitallosalpes.arquisoft.medicoapp.objects.Paciente;
import hospitallosalpes.arquisoft.medicoapp.objects.Reporte;

public class VerHistorialActivity extends AppCompatActivity {

    public Paciente pacienteActual;
    public String ip;
    public TextView nombre;
    public ListView lista;
    public Button ok;
    public ArrayList<Reporte> Reportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_historial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pacienteActual = getIntent().getExtras().getParcelable("pacienteActual");
        ip = getIntent().getStringExtra("ip");

        nombre= (TextView) findViewById(R.id.textView10);
        nombre.setText(pacienteActual.getNombre());

        String url = "http://" + ip + ":8081/pacientes/" + pacienteActual.getId() + "/historiasClinicas";
        ArrayList<Reporte> resp = null;
        try {
            resp = new HttpRequestTask().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String[] values = new String[resp.size()];
        ArrayList names = new ArrayList();
        for(Reporte actual: resp){
            names.add(actual.getTipo()+": "+ actual.getDescripcion());
        }
        names.toArray(values);

        lista = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        lista.setAdapter(adapter);

        ok = (Button) findViewById(R.id.buttonDejarDeVerHistorias);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class HttpRequestTask extends AsyncTask<String, Void, ArrayList <Reporte>> {

        ArrayList <Reporte> resp= new ArrayList();

        @Override
        protected ArrayList <Reporte> doInBackground(String... url) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Reporte[] response = restTemplate.getForObject(url[0], Reporte[].class);
                return new ArrayList<Reporte>(Arrays.asList(response));
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(ArrayList <Reporte> resp) {

        }

    }

}
