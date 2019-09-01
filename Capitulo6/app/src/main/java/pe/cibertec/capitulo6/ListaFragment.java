package pe.cibertec.capitulo6;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListaFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String POSICION_SELECCIONADA = "seleccion";

    private ListView listView;
    private boolean dualPane;
    private Contacto[] contactos;
    private int posicionSeleccionada = 0;
    private OnContactoClickListener onContactoClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onContactoClickListener = (OnContactoClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnContactoClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);

        contactos = Contacto.LISTA;

        ListAdapter adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_activated_1, contactos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View detalleFrame = getActivity().findViewById(R.id.detalle);

        dualPane = detalleFrame != null && detalleFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            posicionSeleccionada = savedInstanceState.getInt(POSICION_SELECCIONADA, 0);
        }

        if (dualPane) {
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // callback que notifica cuando el layout se termino de dibujar
            listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    seleccionarContacto(posicionSeleccionada);
                    listView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSICION_SELECCIONADA, posicionSeleccionada);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        posicionSeleccionada = position;
        onContactoClickListener.onContactoClick(contactos[position]);
    }

    private void seleccionarContacto(int index) {
        if (dualPane) {
            // marca el item indicado como seleccionado
            listView.setItemChecked(index, true);
            // desplaza la lista hasta el item indicado
            listView.setSelection(index);
        }
        onContactoClickListener.onContactoClick(contactos[index]);
    }

    public int getPosicionSeleccionada() {
        return posicionSeleccionada;
    }

    public interface OnContactoClickListener {
        void onContactoClick(Contacto contacto);
    }
}
