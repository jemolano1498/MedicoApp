package hospitallosalpes.arquisoft.medicoapp;

import android.content.Intent;
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

import com.google.firebase.iid.FirebaseInstanceId;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hospitallosalpes.arquisoft.medicoapp.objects.Medico;
import hospitallosalpes.arquisoft.medicoapp.objects.Paciente;
import hospitallosalpes.arquisoft.medicoapp.objects.Quote;
import hospitallosalpes.arquisoft.medicoapp.objects.QuotePaciente;

public class MainActivity extends AppCompatActivity {

    public Button loginButton;
    public Button serverButton;
    public String ip;
    public String respuesta;
    public Paciente pacienteActual;
    public Medico medicoActual;
    public EditText userText;
    public EditText passwordText;
    public EditText ipText;
    public TextView textView;
    public static String userId;
    public Spinner tipoLogin;
    public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginButton = (Button) findViewById(R.id.loginButton);
        serverButton = (Button) findViewById(R.id.serverButton);
        userText = (EditText) findViewById(R.id.userText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        ipText = (EditText) findViewById(R.id.ipText);
        textView = (TextView) findViewById(R.id.textView4);

        token = FirebaseInstanceId.getInstance().getToken();
        addItemsOnSpinner();
        String.valueOf(tipoLogin.getSelectedItem());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = ipText.getText().toString();
                login (userText.getText().toString(),passwordText.getText().toString(),ip,String.valueOf(tipoLogin.getSelectedItem()));
            }
        });
        if (fileExistance("mybean.ser")){
            String ans= readSavedData("mybean.ser");
            String [] info = ans.split("@");
            login (info[0],info[1],info[2],info[3]);

        }
    }

    public void addItemsOnSpinner() {

        tipoLogin = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Médico");
        list.add("Paciente");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoLogin.setAdapter(dataAdapter);

    }

    public void login (String a, String b, String c, String d)
    {

        String url = "http://" + c + ":8081/auth?user=" + a + "&password=" + b;
        String url2 = "http://" + c + ":8081/auth/key?key=" + token;
        if (d.equals("Paciente")){
            QuotePaciente resp = null;
            try {
                resp = new HttpRequestTaskPaciente().execute(url).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (resp!=null){
                if (resp.getEstado().equals("exitoso")) {

                    pacienteActual = resp.getUsuario();
                    Intent intent = new Intent(getBaseContext(), PacienteMenuActivity.class);
                    intent.putExtra("pacienteActual", pacienteActual);
                    intent.putExtra("ip", c);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Bienvenido "+ pacienteActual.getNombre(), Toast.LENGTH_LONG)
                            .show();

                    try{
                        String string = userText.getText().toString()+"@"+passwordText.getText().toString()+"@"+ip+"@"+String.valueOf(tipoLogin.getSelectedItem())+"@";
                        try {
                            FileOutputStream fou = openFileOutput("mybean.ser", MODE_APPEND);
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
                            outputStreamWriter.write(string);
                            outputStreamWriter.close();
                            fou.close();
                        }
                        catch (IOException e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                    }
                    catch(Exception e){

                    }

                } else {
                    Toast.makeText(getApplicationContext(), resp.getElemento(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        }
        else{
            Quote resp = null;
            try {
                resp = new HttpRequestTask().execute(url,url2).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (resp!=null){
                if (resp.getEstado().equals("exitoso")) {

                    medicoActual = resp.getUsuario();
                    Intent intent = new Intent(getBaseContext(), MedicoViewPacientes.class);
                    intent.putExtra("medicoActual", medicoActual);
                    intent.putExtra("ip", c);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Bienvenido "+ medicoActual.getNombre(), Toast.LENGTH_LONG)
                            .show();

                    try{
                        String string = userText.getText().toString()+"@"+passwordText.getText().toString()+"@"+ip+"@"+String.valueOf(tipoLogin.getSelectedItem())+"@";
                        try {
                            FileOutputStream fou = openFileOutput("mybean.ser", MODE_APPEND);
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
                            outputStreamWriter.write(string);
                            outputStreamWriter.close();
                            fou.close();
                        }
                        catch (IOException e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                    }
                    catch(Exception e){

                    }

                } else {
                    Toast.makeText(getApplicationContext(), resp.getElemento(), Toast.LENGTH_LONG)
                            .show();
                }
            }

        }

    }


    public boolean fileExistance(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
    public String readSavedData ( String path ) {
        StringBuffer datax = new StringBuffer("");
        try {
            FileInputStream fIn = openFileInput ( path ) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                datax.append(readString);
                readString = buffreader.readLine ( ) ;
            }

            buffreader.close();
            isr.close ( ) ;
            fIn.close();
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        return datax.toString() ;
    }

    private class HttpRequestTask extends AsyncTask<String, Void, Quote> {

        Quote resp;

        @Override
        protected Quote doInBackground(String... url) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Quote resp= restTemplate.getForObject(url[0], Quote.class);
                restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getForObject(url[1], String.class);
                return resp;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(Quote resp) {

        }

    }
    private class HttpRequestTaskPaciente extends AsyncTask<String, Void, QuotePaciente> {

        QuotePaciente resp;

        @Override
        protected QuotePaciente doInBackground(String... url) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                QuotePaciente resp= restTemplate.getForObject(url[0], QuotePaciente.class);
                return resp;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(QuotePaciente resp) {

        }

    }
}
