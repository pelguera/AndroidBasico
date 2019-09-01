package pe.cibertec.capitulo5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AgendaAdapter extends ArrayAdapter<Contacto> {


    public AgendaAdapter(@NonNull Context context, @NonNull List<Contacto> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtNombre = convertView.findViewById(R.id.txtNombre);
            viewHolder.txtTelefono = convertView.findViewById(R.id.txtTelefono);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contacto contacto = getItem(position);

        viewHolder.txtNombre.setText(contacto.getNombre());
        viewHolder.txtTelefono.setText(contacto.getTelefono());

        return convertView;
    }

    static class ViewHolder {
        TextView txtNombre, txtTelefono;
    }
}
