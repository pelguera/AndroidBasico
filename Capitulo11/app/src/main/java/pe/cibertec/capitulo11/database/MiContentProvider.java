package pe.cibertec.capitulo11.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pe.cibertec.capitulo11.modelo.Contacto;

public class MiContentProvider extends ContentProvider {

    private static final int CONTACTOS = 1;
    private static final int CONTACTO_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(ContactoContract.CONTENT_AUTHORITY, ContactoContract.PATH_CONTACTO, CONTACTOS);
        uriMatcher.addURI(ContactoContract.CONTENT_AUTHORITY, ContactoContract.PATH_CONTACTO + "/#", CONTACTO_ID);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        ContactoDao contactoDao = AppDatabase.getInstance(getContext()).contactoDao();
        switch (uriMatcher.match(uri)) {
            case CONTACTOS:
                cursor = contactoDao.listarEnCursor();
                break;
            case CONTACTO_ID:
                cursor = contactoDao.obtenerPorId(ContentUris.parseId(uri));
                break;
            default:
                throw new UnsupportedOperationException("Uri desconocida: " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CONTACTOS:
                return ContactoContract.CONTENT_TYPE;
            case CONTACTO_ID:
                return ContactoContract.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Uri desconocida: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case CONTACTOS:
                ContactoDao contactoDao = AppDatabase.getInstance(getContext()).contactoDao();
                Contacto contacto = new Contacto();
                contacto.setId(values.getAsLong("id"));
                contacto.setNombre(values.getAsString("nombre"));
                contacto.setTelefono(values.getAsString("telefono"));
                contacto.setEmail(values.getAsString("email"));
                contacto.setDireccion(values.getAsString("direccion"));
                long id = contactoDao.insertar(contacto);
                return ContentUris.withAppendedId(uri, id);
            case CONTACTO_ID:
                throw new IllegalArgumentException("Uri invalida, no puede insertar con un ID: " + uri);
            default:
                throw new UnsupportedOperationException("Uri desconocida: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case CONTACTOS:
                throw new IllegalArgumentException("Uri invalida, no puede insertar sin un ID: " + uri);
            case CONTACTO_ID:
                ContactoDao contactoDao = AppDatabase.getInstance(getContext()).contactoDao();
                return contactoDao.eliminarPorId(ContentUris.parseId(uri));
            default:
                throw new UnsupportedOperationException("Uri desconocida: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case CONTACTOS:
                throw new IllegalArgumentException("Uri invalida, no puede insertar sin un ID: " + uri);
            case CONTACTO_ID:
                ContactoDao contactoDao = AppDatabase.getInstance(getContext()).contactoDao();
                Contacto contacto = new Contacto();
                contacto.setId(values.getAsLong("id"));
                contacto.setNombre(values.getAsString("nombre"));
                contacto.setTelefono(values.getAsString("telefono"));
                contacto.setEmail(values.getAsString("email"));
                contacto.setDireccion(values.getAsString("direccion"));
                return contactoDao.actualizar(contacto);
            default:
                throw new UnsupportedOperationException("Uri desconocida: " + uri);
        }
    }
}
