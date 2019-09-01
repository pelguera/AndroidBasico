package pe.cibertec.capitulo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vehiculo auto = new Auto();
        Vehiculo moto = new Moto();
        Vehiculo avion = new Avion();
        Vehiculo helicoptero = new Helicoptero();

        imprimirNombre(auto);
        imprimirNombre(moto);
        imprimirNombre(avion);
        imprimirNombre(helicoptero);

        ((Volador) avion).volar();
        ((Volador) helicoptero).volar();
    }

    private void imprimirNombre(Vehiculo vehiculo) {
        Log.d(TAG, "imprimirNombre: " + vehiculo.getNombre());
    }
}
