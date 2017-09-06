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
        TextView provincia = (TextView) item.findViewById(R.id.txtProvincia);
        TextView cp = (TextView) item.findViewById(R.id.txtCP);
        TextView hora = (TextView) item.findViewById(R.id.txtHora);
        LinearLayout global = (LinearLayout)item.findViewById(R.id.global);
        String dir = "";
        if (arrayList.get(position).getTipo_via()!=""&&arrayList.get(position).getTipo_via()!="null"){
            dir+=arrayList.get(position).getTipo_via();
        }
        if (arrayList.get(position).getVia()!=""&&arrayList.get(position).getVia()!="null"){
            dir+=arrayList.get(position).getTipo_via();
        }
        if (arrayList.get(position).getTipo_via()!=""&&arrayList.get(position).getTipo_via()!="null"){
            dir+=arrayList.get(position).getTipo_via();
        }
        direccion.setText(String.valueOf(arrayList.get(position).getFecha_aviso()));
        cp.setText("C.P.:  "+ String.valueOf(arrayList.get(position).getHorario()));
        hora.setText(String.valueOf(arrayList.get(position).getHorario()));
        provincia.setText("("+ String.valueOf(arrayList.get(position).getNum_parte())+")");
        global.setTag(String.valueOf(arrayList.get(position).getId_parte()));
        return item;
    }
}
