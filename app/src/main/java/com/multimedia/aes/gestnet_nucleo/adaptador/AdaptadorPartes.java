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
        LinearLayout global = (LinearLayout)item.findViewById(R.id.global);
        direccion.setText(String.valueOf(arrayList.get(position).getDireccion_averia()));
        cp.setText("C.P.:  "+ String.valueOf(arrayList.get(position).getCp_averia()));
        provincia.setText("("+ String.valueOf(arrayList.get(position).getProvincia_averia())+")");
        global.setTag(String.valueOf(arrayList.get(position).getId_averia()));
        return item;
    }
}
