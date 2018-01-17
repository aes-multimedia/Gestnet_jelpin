package com.multimedia.aes.gestnet_nucleo.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;

import java.util.ArrayList;

public class AdaptadorPartes extends ArrayAdapter {

    private Context context;
    private int view;
    private ArrayList<Parte> arrayList;

    public AdaptadorPartes(Context context, int view, ArrayList<Parte> arrayList) {
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
        TextView direccion = (TextView) item.findViewById(R.id.txtDireccion);
        TextView cp = (TextView) item.findViewById(R.id.txtCP);
        TextView hora = (TextView) item.findViewById(R.id.txtHora);
        TextView cliente = (TextView) item.findViewById(R.id.txtCliente);
        LinearLayout global = (LinearLayout)item.findViewById(R.id.global);
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
                global.setBackgroundResource(R.drawable.fondo_ambar);
                break;
            case 3:
                global.setBackgroundResource(R.drawable.fondo_verde);
                break;
        }
        direccion.setText(dir);
        cp.setText("C.P.:  "+ String.valueOf(arrayList.get(position).getCp_direccion()));
        hora.setText(String.valueOf(arrayList.get(position).getHorario()));
        cliente.setText(arrayList.get(position).getNombre_cliente());
        global.setTag(String.valueOf(arrayList.get(position).getId_parte()));
        return item;
    }
}
