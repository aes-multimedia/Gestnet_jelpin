package com.multimedia.aes.gestnet_ssl.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;

import java.util.ArrayList;

public class AdaptadorPartes_trenc extends ArrayAdapter {

    private Context context;
    private int view;
    private ArrayList<Parte> arrayList;

    public AdaptadorPartes_trenc(Context context, int view, ArrayList<Parte> arrayList) {
        super(context, view, arrayList);
        this.context = context;
        this.view = view;
        this.arrayList=arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(view, null);
        }
        TextView hora =  item.findViewById(R.id.txtHora);
        TextView txtTipoIntervencion =  item.findViewById(R.id.txtTipoIntervencion_trenc);
        TextView poblacion = item.findViewById(R.id.txtPoblacion_trenc);
        TextView direccion = item.findViewById(R.id.txtDireccion_trenc);
        TextView cliente =  item.findViewById(R.id.txtCliente);
        TextView observaciones =  item.findViewById(R.id.observaciones_trenc);


        LinearLayout global = item.findViewById(R.id.global);
        String dir = "";
        if (!arrayList.get(position).getTipo_via().trim().equals("")&&!arrayList.get(position).getTipo_via().trim().equals("null")){
            dir+=arrayList.get(position).getTipo_via()+" ";
        }
        if (!arrayList.get(position).getVia().trim().equals("")&&!arrayList.get(position).getVia().trim().equals("null")){
            dir+=arrayList.get(position).getVia()+" ";
        }
        if (!arrayList.get(position).getNumero_direccion().trim().equals("")&&!arrayList.get(position).getNumero_direccion().trim().equals("null")){
            dir+="Nº "+arrayList.get(position).getNumero_direccion()+" ";
        }
        if (!arrayList.get(position).getEscalera_direccion().trim().equals("")&&!arrayList.get(position).getEscalera_direccion().trim().equals("null")){
            dir+="Esc. "+arrayList.get(position).getEscalera_direccion()+" ";
        }
        if (!arrayList.get(position).getPiso_direccion().trim().equals("")&&!arrayList.get(position).getPiso_direccion().trim().equals("null")){
            dir+="Piso "+arrayList.get(position).getPiso_direccion()+" ";
        }
        if (!arrayList.get(position).getPuerta_direccion().trim().equals("")&&!arrayList.get(position).getPuerta_direccion().trim().equals("null")){
            dir+=arrayList.get(position).getPuerta_direccion()+" ";
        }
        if (!arrayList.get(position).getMunicipio_direccion().trim().equals("")&&!arrayList.get(position).getMunicipio_direccion().trim().equals("null")){
            dir+="("+arrayList.get(position).getMunicipio_direccion()+"-";
        }
        if (!arrayList.get(position).getProvincia_direccion().trim().equals("")&&!arrayList.get(position).getProvincia_direccion().trim().equals("null")){
            dir+=arrayList.get(position).getProvincia_direccion()+")";
        }

        switch (arrayList.get(position).getEstado_android()){
            case 0:
                global.setBackgroundResource(R.drawable.fondo_rojo);
                break;
            case 1:
                global.setBackgroundResource(R.drawable.fondo_ambar);
                break;
            case 2:
                global.setBackgroundResource(R.drawable.fondo_azul);
                break;
            case 3:
                global.setBackgroundResource(R.drawable.fondo_verde);
                break;
            case 4:
                global.setBackgroundResource(R.drawable.fondo_tenue);
                break;
            case 436:
                global.setBackgroundResource(R.drawable.verde_oscuro);
                break;
        }
        hora.setText(String.valueOf(arrayList.get(position).getHorario()));
        poblacion.setText(String.valueOf(arrayList.get(position).getMunicipio_direccion()));
        direccion.setText(dir);
        cliente.setText(arrayList.get(position).getNombre_cliente());
        txtTipoIntervencion.setText(arrayList.get(position).getTipo());
        observaciones.setText(String.valueOf(arrayList.get(position).getObservaciones()));
        global.setTag(String.valueOf(arrayList.get(position).getId_parte()));
        return item;
    }
}
