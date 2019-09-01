package pe.cibertec.capitulo6;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ListaFragment.OnContactoClickListener {

    private boolean dualPane;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View detalleFrame = findViewById(R.id.detalle);

        dualPane = detalleFrame != null && detalleFrame.getVisibility() == View.VISIBLE;

        ListaFragment listaFragment = (ListaFragment) getSupportFragmentManager()
                .findFragmentById(R.id.lista);
        index = listaFragment.getPosicionSeleccionada();
    }

    @Override
    public void onContactoClick(Contacto contacto) {
        if (dualPane) {
            DetalleFragment detalleFragment = (DetalleFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.detalle);

            ListaFragment listaFragment = (ListaFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.lista);

            if (detalleFragment == null || listaFragment.getPosicionSeleccionada() != index) {
                index = listaFragment.getPosicionSeleccionada();
                detalleFragment = DetalleFragment.newInstance(contacto);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.detalle, detalleFragment);
                ft.commit();
            }
        } else {
            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra(DetalleFragment.ARG_CONTACTO, contacto);
            startActivity(intent);
        }
    }
}
