package pe.cibertec.capitulo7.bottonnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import pe.cibertec.capitulo7.R;

public class BottonNavigationActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botton_navigation);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottonNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        mostrarContenido("inicio");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String tag = "";
        switch (item.getItemId()) {
            case R.id.navInicio:
                tag = "inicio";
                break;
            case R.id.navMensajes:
                tag = "mensajes";
                break;
            case R.id.navNotificaciones:
                tag = "notificaciones";
                break;
        }
        mostrarContenido(tag);
        return true;
    }

    private void mostrarContenido(String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment contenido = getSupportFragmentManager().findFragmentByTag(tag);
        if (contenido == null) {
            contenido = new ContenidoFragment();
            ft.add(R.id.contenido, contenido, tag);
        } else {
            ft.attach(contenido);
        }
        Fragment contenidoAnterior = getSupportFragmentManager().getPrimaryNavigationFragment();
        if (contenidoAnterior != null) {
            ft.detach(contenidoAnterior);
        }
        ft.setPrimaryNavigationFragment(contenido);
        ft.setReorderingAllowed(true);
        ft.commit();
    }
}
