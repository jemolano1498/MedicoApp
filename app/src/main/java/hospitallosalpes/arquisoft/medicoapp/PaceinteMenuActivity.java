package hospitallosalpes.arquisoft.medicoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import hospitallosalpes.arquisoft.medicoapp.objects.Paciente;

public class PaceinteMenuActivity extends AppCompatActivity {

    public TextView textView;
    public Button consejosButton;
    public Button doctorButton;
    public Button medicionesButton;
    public Paciente pacienteActual;
    public String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paceinte_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pacienteActual = getIntent().getExtras().getParcelable("pacienteActual");
        ip = getIntent().getStringExtra("ip");
        consejosButton = (Button) findViewById(R.id.button);
        doctorButton = (Button) findViewById(R.id.button2);
        medicionesButton = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.textView12);
        textView.setText(pacienteActual.getNombre());

        consejosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), verConsejosActivity.class);
                intent.putExtra("pacienteActual", pacienteActual);
                intent.putExtra("ip", ip);
                startActivity(intent);
            }
        });
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

}
