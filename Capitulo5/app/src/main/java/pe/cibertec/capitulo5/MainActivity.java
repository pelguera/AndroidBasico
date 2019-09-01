package pe.cibertec.capitulo5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        AgendaAdapter adapter = new AgendaAdapter(this, crearLista());
        listView.setAdapter(adapter);
    }

    private List<Contacto> crearLista() {
        List<Contacto> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Contacto contacto = new Contacto();
            contacto.setNombre("Contacto " + i);
            contacto.setTelefono("+51 999 999 99" + i);
            lista.add(contacto);
        }

        return lista;
    }
}
