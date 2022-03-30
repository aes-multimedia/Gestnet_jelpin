package com.multimedia.aes.gestnet_ssl.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.clases.DataDetallePartes;

import java.util.ArrayList;

public class AdaptadorDetallePartes extends ArrayAdapter {

    private Context context;
    private int view;
    private ArrayList<DataDetallePartes> arrayList;

    public AdaptadorDetallePartes(Context context, int view, ArrayList<DataDetallePartes> arrayList) {
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
        TextView cliente =  item.findViewById(R.id.txtCliente);
        TextView direccion = item.findViewById(R.id.txtDireccion);
        TextView contrato =  item.findViewById(R.id.txtContrato);
        TextView endesa =  item.findViewById(R.id.txtEndesa);
        LinearLayout global = item.findViewById(R.id.global);

        direccion.setText(String.valueOf(arrayList.get(position).getDireccion()));
        contrato.setText(String.valueOf("Nº Contrato: "+arrayList.get(position).getNumContrato()));
        endesa.setText(String.valueOf("Nº Orden Endesa: "+arrayList.get(position).getOrdenEndesa()));
        cliente.setText(arrayList.get(position).getNombreCliente());
        global.setTag(String.valueOf(arrayList.get(position).getId()));
        return item;
    }
}
