package hospitallosalpes.arquisoft.medicoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import hospitallosalpes.arquisoft.medicoapp.objects.Medico;
import hospitallosalpes.arquisoft.medicoapp.objects.Paciente;

public class MedicoViewPacientes extends AppCompatActivity {


    public Medico medicoActual;
    public String ip;
    ListView listView ;
    public TextView bienvenida;
    public ArrayList<Paciente> pacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_view_pacientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        medicoActual = getIntent().getExtras().getParcelable("medicoActual");
        ip= getIntent().getStringExtra("ip");
        listView = (ListView) findViewById(R.id.listViewPacientes);
        bienvenida = (TextView) findViewById(R.id.textViewBienvenido);
        bienvenida.setText("Bienvenido " + medicoActual.getNombre());
        pacientes= new ArrayList<Paciente>();
        CargarPacientes();

        // Defined Array values to show in ListView
        String[] values = new String[pacientes.size()];
        ArrayList names = new ArrayList();
        for(Paciente actual: pacientes){
            names.add(actual.getNombre()+" "+ actual.getEdad()+" años");
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
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                Intent intent = new Intent(getBaseContext(), PacienteActivity.class);
                intent.putExtra("medicoActual", medicoActual);
                intent.putExtra("pacienteActual", pacientes.get(itemPosition));
                intent.putExtra("ip", ip);
                startActivity(intent);

            }

        });
    }

    private void CargarPacientes() {
        for (String id:medicoActual.getIdPacientes()) {
            if (!id.equals("")){
                String url = "http://" + ip + ":8081/pacientes/" + id;
                Paciente resp = null;
                try {
                    resp = new HttpRequestTask().execute(url).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (resp!=null){
                    pacientes.add(resp);
                }

            }

        }
    }

    private class HttpRequestTask extends AsyncTask<String, Void, Paciente> {

        Paciente resp;

        @Override
        protected Paciente doInBackground(String... url) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                resp = restTemplate.getForObject(url[0], Paciente.class);
                return resp;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(Paciente resp) {

        }

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
//    public void deleteAFile(String filePath){
//        if(filePath.startsWith("content://")){
//            ContentResolver contentResolver = getActivity().getContentResolver();
//            contentResolver.delete(Uri.parse(filePath), null, null);
//        }else {
//            File file = new File(filePath);
//            if(file.exists()) {
//                if (file.delete()) {
//                    Log.e("algo", "File deleted.");
//                }else {
//                    Log.e("algo", "Failed to delete file!");
//                }
//            }else {
//                Log.e("algo", "File not exist!");
//            }
//        }
//    }

}
