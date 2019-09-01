package pe.cibertec.capitulo7.tabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.cibertec.capitulo7.R;

public class ContenidoFragment extends Fragment {

    private String titulo;

    public static ContenidoFragment newInstance(String titulo) {
        ContenidoFragment f = new ContenidoFragment();
        Bundle args = new Bundle();
        args.putString("titulo", titulo);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titulo = getArguments().getString("titulo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        TextView txtTitulo = view.findViewById(R.id.txtTab);
        txtTitulo.setText(titulo);
        return view;
    }

}
