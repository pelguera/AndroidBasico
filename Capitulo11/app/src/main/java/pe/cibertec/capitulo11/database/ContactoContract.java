package pe.cibertec.capitulo11.database;

import android.net.Uri;

public class ContactoContract {

    public static final String CONTENT_AUTHORITY = "pe.cibertec.capitulo11";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CONTACTO = "contacto";

    public static final String CONTENT_TYPE =
            "vnd.android.cursor.dir/vnd" + CONTENT_AUTHORITY  + "." + PATH_CONTACTO;
    public static final String CONTENT_ITEM_TYPE =
            "vnd.android.cursor.item/vnd" + CONTENT_AUTHORITY + "." + PATH_CONTACTO;
}
