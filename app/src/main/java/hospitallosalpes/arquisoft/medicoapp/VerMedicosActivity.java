package hospitallosalpes.arquisoft.medicoapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import hospitallosalpes.arquisoft.medicoapp.objects.Medico;

public class VerMedicosActivity extends AppCompatActivity {

    private ListView listMedicos;
    private String ip;
    private ArrayList<Medico> medicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_medicos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ip = getIntent().getStringExtra("ip");

        listMedicos = (ListView) findViewById(R.id.listView3);
        String url = "http://" + ip + ":8081/medicos";
        try {
            medicos = new HttpRequestTask().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Defined Array values to show in ListView
        String[] values = new String[medicos.size()];
        ArrayList names = new ArrayList();
        for(Medico actual: medicos){
            names.add(actual.getNombre()+": "+ actual.getTipo());
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
        listMedicos.setAdapter(adapter);

    }

    private class HttpRequestTask extends AsyncTask<String, Void, ArrayList <Medico>> {

        ArrayList <Medico> resp= new ArrayList();

        @Override
        protected ArrayList <Medico> doInBackground(String... url) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Medico[] response = restTemplate.getForObject(url[0], Medico[].class);
                return new ArrayList<Medico>(Arrays.asList(response));
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(ArrayList <Medico> resp) {

        }

    }

}
