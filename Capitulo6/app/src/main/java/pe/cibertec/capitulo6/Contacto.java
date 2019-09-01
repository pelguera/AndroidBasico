package pe.cibertec.capitulo6;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {

    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.telefono);
        dest.writeString(this.email);
        dest.writeString(this.direccion);
    }

    public Contacto(String nombre, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    protected Contacto(Parcel in) {
        this.nombre = in.readString();
        this.telefono = in.readString();
        this.email = in.readString();
        this.direccion = in.readString();
    }

    public static final Parcelable.Creator<Contacto> CREATOR = new Parcelable.Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel source) {
            return new Contacto(source);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    @Override
    public String toString() {
        return nombre;
    }

    public static Contacto[] LISTA = {
            new Contacto("Tony Stark", "+1 111 111 111", "ironman@marvel.com", "Manhattan, New York"),
            new Contacto("Peter Parker", "+1 222 222 222", "spiderman@marvel.com", "Queens, New York"),
            new Contacto("Steve Rogers", "+1 333 333 333", "captainamerica@marvel.com", "Manhattan, New York"),
            new Contacto("Natasha Romanoff", "+1 444 444 444", "blackwidow@marvel.com", "Stalingrad, Russia"),
            new Contacto("Thor", "+1 666 666 666", "thor@marvel.com", "Asgard"),
            new Contacto("Bruce Banner", "+1 777 777 777", "hulk@marvel.com", "Dayton, Ohio"),

    };
}
