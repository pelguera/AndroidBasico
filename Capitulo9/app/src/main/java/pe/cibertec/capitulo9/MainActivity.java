package pe.cibertec.capitulo9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar;
    private TextView txtContador;

    private boolean servicioIniciado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtContador = findViewById(R.id.txtContador);
        btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (servicioIniciado) {
                    detenerServicio();
                } else {
                    iniciarServicio();
                }
                actualizarUI(servicioIniciado);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(MiService.ACTION_ACTUALIZAR);
        LocalBroadcastManager.getInstance(this).registerReceiver(miReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(miReceiver);
    }

    private BroadcastReceiver miReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MiService.ACTION_ACTUALIZAR)) {
                txtContador.setText("" + intent.getIntExtra("contador", 0));

                servicioIniciado = true;
                actualizarUI(servicioIniciado);
            }
        }
    };

    private void iniciarServicio() {
        Intent serviceIntent = new Intent(this, MiService.class);
        startService(serviceIntent);
        servicioIniciado = true;
    }

    private void detenerServicio() {
        Intent serviceIntent = new Intent(this, MiService.class);
        stopService(serviceIntent);
        servicioIniciado = false;
    }

    private void actualizarUI(boolean servicioIniciado) {
        if (servicioIniciado) {
            btnIniciar.setText("Detener");
        } else {
            btnIniciar.setText("Iniciar");
        }
    }
}
