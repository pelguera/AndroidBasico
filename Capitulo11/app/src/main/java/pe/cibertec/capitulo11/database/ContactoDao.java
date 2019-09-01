package pe.cibertec.capitulo11.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

import pe.cibertec.capitulo11.modelo.Contacto;

@Dao
public interface ContactoDao {

    @Query("SELECT * FROM contacto")
    List<Contacto> listar();

    @Query("SELECT * FROM contacto")
    Cursor listarEnCursor();

    @Query("SELECT * FROM contacto WHERE id = :id")
    Cursor obtenerPorId(long id);

    @Insert
    long insertar(Contacto contacto);

    @Update
    int actualizar(Contacto contacto);

    @Delete
    int eliminar(Contacto contacto);

    @Query("DELETE FROM contacto WHERE id = :id")
    int eliminarPorId(long id);
}
