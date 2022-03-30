package com.multimedia.aes.gestnet_ssl.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.entidades.Analisis;

import java.util.ArrayList;


public class AdaptadorListaAnalisis extends ArrayAdapter{
    //VARIABLES GLOBALES
    private Context context;
    private int view;
    private ArrayList<Analisis> arrayList;
    //CONSTRUCTOR
    public AdaptadorListaAnalisis(Context context, int view, ArrayList<Analisis> arrayList) {
        super(context, view, arrayList);
        this.context = context;
        this.view = view;
        this.arrayList=arrayList;
    }
    //OVERRIDE METHODS
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(view, null);

        }
        LinearLayout llMaquina = (LinearLayout) item.findViewById(R.id.llMaquina);
        TextView txtMarca = (TextView)item.findViewById(R.id.txtMarca);
        llMaquina.setTag(arrayList.get(position).getId_analisis());
        String marca = arrayList.get(position).getNombre_medicion();
        txtMarca.setText(marca);
        return item;
    }
}
