package pe.cibertec.capitulo11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import pe.cibertec.capitulo11.database.AppDatabase;
import pe.cibertec.capitulo11.database.ContactoDao;
import pe.cibertec.capitulo11.modelo.Contacto;

public class ContactoActivity extends AppCompatActivity {

    private Contacto contacto;

    private EditText edtNombre, edtTelefono, edtEmail, edtDireccion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        edtNombre = findViewById(R.id.txtNombre);
        edtTelefono = findViewById(R.id.txtTelefono);
        edtEmail = findViewById(R.id.txtEmail);
        edtDireccion = findViewById(R.id.txtDireccion);

        contacto = getIntent().getParcelableExtra("contacto");

        if (contacto != null) {
            edtNombre.setText(contacto.getNombre());
            edtTelefono.setText(contacto.getTelefono());
            edtEmail.setText(contacto.getEmail());
            edtDireccion.setText(contacto.getDireccion());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacto, menu);
        if (contacto == null) {
            menu.getItem(1).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_guardar:
                guardar();
                break;
            case R.id.action_eliminar:
                eliminar();
                break;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void guardar() {
        if (contacto == null) {
            contacto = new Contacto();
        }
        contacto.setNombre(edtNombre.getText().toString());
        contacto.setTelefono(edtTelefono.getText().toString());
        contacto.setEmail(edtEmail.getText().toString());
        contacto.setDireccion(edtDireccion.getText().toString());

        ContactoDao contactoDao = AppDatabase.getInstance(getApplicationContext()).contactoDao();

        if (contacto.getId() == 0) {
            contactoDao.insertar(contacto);
        } else {
            contactoDao.actualizar(contacto);
        }
    }

    private void eliminar() {
        if (contacto != null) {
            ContactoDao contactoDao = AppDatabase.getInstance(getApplicationContext()).contactoDao();
            contactoDao.eliminar(contacto);
        }
    }
}
