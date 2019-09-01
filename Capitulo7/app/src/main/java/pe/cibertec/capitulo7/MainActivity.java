package pe.cibertec.capitulo7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import pe.cibertec.capitulo7.bottonnavigation.BottonNavigationActivity;
import pe.cibertec.capitulo7.drawer.DrawerActivity;
import pe.cibertec.capitulo7.tabs.TabsActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        switch (position) {
            case 0: // Tabs
                intent = new Intent(this, TabsActivity.class);
                break;
            case 1: // Bottom Navigation
                intent = new Intent(this, BottonNavigationActivity.class);
                break;
            case 2: // Navigation Drawer
                intent = new Intent(this, DrawerActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
