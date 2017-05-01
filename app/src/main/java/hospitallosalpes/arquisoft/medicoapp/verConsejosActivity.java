package hospitallosalpes.arquisoft.medicoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import hospitallosalpes.arquisoft.medicoapp.objects.Consejo;
import hospitallosalpes.arquisoft.medicoapp.objects.Paciente;

public class verConsejosActivity extends AppCompatActivity {


    public Paciente pacienteActual;
    public String ip;
    public ListView lista;
    public ArrayList<Consejo> consejos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_consejos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pacienteActual = getIntent().getExtras().getParcelable("pacienteActual");
        ip = getIntent().getStringExtra("ip");
        lista = (ListView) findViewById(R.id.listView2);

        String url = "http://" + ip + ":8081/pacientes/" + pacienteActual.getId() + "/consejos";
        try {
            consejos = new HttpRequestTask().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Defined Array values to show in ListView
        String[] values = new String[consejos.size()];
        ArrayList names = new ArrayList();
        for(Consejo actual: consejos){
            names.add(actual.getTipo()+": "+ actual.getDescripcion());
        }
        names.toArray(values);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        lista.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
//            Intent intent = new Intent(getBaseContext(), MedicoViewPacientes.class);
//            intent.putExtra("medicoActual", medicoActual);
//            intent.putExtra("ip", ip);
//            startActivity(intent);
        }
        if (id == R.id.action_logOut) {
            deleteFile("mybean.ser");
            File file = new File("mybean.ser");
            boolean deleted = file.delete();
            startActivity(new Intent(getBaseContext(), MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private class HttpRequestTask extends AsyncTask<String, Void, ArrayList <Consejo>> {

        ArrayList <Consejo> resp= new ArrayList();

        @Override
        protected ArrayList <Consejo> doInBackground(String... url) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Consejo[] response = restTemplate.getForObject(url[0], Consejo[].class);
                return new ArrayList<Consejo>(Arrays.asList(response));
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(ArrayList <Consejo> resp) {

        }

    }

}
