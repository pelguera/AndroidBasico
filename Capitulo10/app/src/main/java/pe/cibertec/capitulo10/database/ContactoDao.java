package pe.cibertec.capitulo10.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import pe.cibertec.capitulo10.modelo.Contacto;

@Dao
public interface ContactoDao {

    @Query("SELECT * from contacto")
    List<Contacto> listar();

    @Insert
    long insertar(Contacto contacto);

    @Update
    int actualizar(Contacto contacto);

    @Delete
    int eliminar(Contacto contacto);
}
