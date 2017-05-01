package hospitallosalpes.arquisoft.medicoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import hospitallosalpes.arquisoft.medicoapp.objects.Medico;
import hospitallosalpes.arquisoft.medicoapp.objects.Paciente;

public class PacienteActivity extends AppCompatActivity {

    public Medico medicoActual;
    public String ip;
    public Paciente pacienteActual;

    public TextView nombreMedico;
    public TextView tipoMedico;
    public TextView NombrePaciente;
    public TextView direccionPaciente;
    public TextView telefonoPaciente;
    public TextView edadPaciente;
    public TextView idPaciente;
    public TextView marcaPasosPaciente;

    public Button botonVerHistorial;
    public Button botonEnviarConsejo;
    public Button botonConfigurarMarcapasos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        medicoActual = getIntent().getExtras().getParcelable("medicoActual");
        pacienteActual = getIntent().getExtras().getParcelable("pacienteActual");
        ip= getIntent().getStringExtra("ip");
        nombreMedico = (TextView) findViewById(R.id.textViewDoctorName);
        nombreMedico.setText(medicoActual.getNombre());
        tipoMedico = (TextView) findViewById(R.id.TextViewTipoDoctor);
        tipoMedico.setText(medicoActual.getTipo());
        NombrePaciente = (TextView) findViewById(R.id.textViewNombrePaciente);
        NombrePaciente.setText(pacienteActual.getNombre());
        direccionPaciente = (TextView) findViewById(R.id.textViewDireccion);
        direccionPaciente.setText(pacienteActual.getDireccion());
        telefonoPaciente = (TextView) findViewById(R.id.textViewTelefono);
        telefonoPaciente.setText(pacienteActual.getTelefono()+"");
        edadPaciente = (TextView) findViewById(R.id.textViewEdad);
        edadPaciente.setText(pacienteActual.getEdad()+"");
        idPaciente = (TextView) findViewById(R.id.textViewIdentificacion);
        idPaciente.setText(pacienteActual.getDocIdentidad()+"");
        marcaPasosPaciente = (TextView) findViewById(R.id.textViewFrecuencia);
        marcaPasosPaciente.setText(pacienteActual.getFecuenciaMarcapasos()+"");

        botonConfigurarMarcapasos = (Button) findViewById(R.id.buttonReconfig);
        if (!medicoActual.getTipo().equals("ESPECIALIZADO"))
        {
            botonConfigurarMarcapasos.setClickable(false);
        }
        botonConfigurarMarcapasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Configuraciones Iniciales hechas", Toast.LENGTH_LONG)
                        .show();
            }
        });
        botonEnviarConsejo = (Button) findViewById(R.id.buttonRecomendacion);
        botonEnviarConsejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EnviarConsejoActivity.class);
                intent.putExtra("medicoActual", medicoActual.getId());
                intent.putExtra("ip", ip);
                intent.putExtra("pacienteActual", pacienteActual);
                startActivity(intent);
            }
        });
        botonVerHistorial = (Button) findViewById(R.id.buttonHistorial);
        botonVerHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), VerHistorialActivity.class);
                intent.putExtra("medicoActual", medicoActual.getId());
                intent.putExtra("ip", ip);
                intent.putExtra("pacienteActual", pacienteActual);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // close this activity as oppose to navigating up

        return false;
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
            Intent intent = new Intent(getBaseContext(), MedicoViewPacientes.class);
            intent.putExtra("medicoActual", medicoActual);
            intent.putExtra("ip", ip);
            startActivity(intent);
        }
        if (id == R.id.action_logOut) {
            File file = new File("mybean.ser");
            boolean deleted = file.delete();
            setContentView(R.layout.activity_main);
        }
        if (id ==android.R.id.home){
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
