package pe.cibertec.capitulo6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetalleFragment extends Fragment {

    public static final String ARG_CONTACTO = "contacto";

    private Contacto contacto;

    public static DetalleFragment newInstance(Contacto contacto) {
        DetalleFragment f = new DetalleFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTACTO, contacto);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contacto = getArguments().getParcelable(ARG_CONTACTO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtNombre = view.findViewById(R.id.txtNombre);
        TextView txtTelefono = view.findViewById(R.id.txtTelefono);
        TextView txtEmail = view.findViewById(R.id.txtEmail);
        TextView txtDireccion = view.findViewById(R.id.txtDireccion);

        txtNombre.setText(contacto.getNombre());
        txtTelefono.setText(contacto.getTelefono());
        txtEmail.setText(contacto.getEmail());
        txtDireccion.setText(contacto.getDireccion());
    }
}
