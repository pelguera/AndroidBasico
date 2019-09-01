package pe.cibertec.capitulo11.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pe.cibertec.capitulo11.modelo.Contacto;

@Database(entities = {Contacto.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context, AppDatabase.class, "contactos").allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract ContactoDao contactoDao();
}
