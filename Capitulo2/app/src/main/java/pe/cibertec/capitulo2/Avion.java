package pe.cibertec.capitulo2;

import android.util.Log;

public class Avion extends Vehiculo implements Volador {

    private static final String TAG = "Avion";

    private static final String NOMBRE = "Avion";
    private static final int NUMERO_ASIENTOS = 50;

    public Avion() {
        this.nombre = NOMBRE;
        this.numeroAsientos = NUMERO_ASIENTOS;
    }

    @Override
    public void volar() {
        Log.d(TAG, "volar");
    }
}
