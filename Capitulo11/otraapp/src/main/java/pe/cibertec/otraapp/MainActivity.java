package pe.cibertec.otraapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(getListaContactos());
    }

    private List<String> getListaContactos() {
        Cursor cursor = listar();
        List<String> stringList = new ArrayList<>();
        if(cursor != null){
            while (cursor.moveToNext()) {
                stringList.add(transformarCursor(cursor));
            }
            cursor.close();
        }

        return stringList;
    }

    private Cursor listar() {
        ContentResolver cr = getContentResolver();
        String[] projection = {"id", "nombre", "telefono", "email", "direccion"};
        Uri uri = Uri.parse("content://pe.cibertec.capitulo11/contacto");
        return cr.query(uri, projection, null, null, null);
    }

    private String transformarCursor(Cursor cursor) {
        return String.valueOf(cursor.getLong(0)) + " - " + cursor.getString(1);
    }
}
