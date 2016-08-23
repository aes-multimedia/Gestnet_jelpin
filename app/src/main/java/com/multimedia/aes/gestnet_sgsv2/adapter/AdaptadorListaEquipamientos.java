package com.multimedia.aes.gestnet_sgsv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.clases.DataEquipamientos;
import com.multimedia.aes.gestnet_sgsv2.fragment.TabFragment2;

import java.util.ArrayList;


public class AdaptadorListaEquipamientos extends ArrayAdapter implements View.OnClickListener {
    private Context context;
    private int view;
    private ArrayList<DataEquipamientos> arrayList;

    public AdaptadorListaEquipamientos(Context context, int view, ArrayList<DataEquipamientos> arrayList) {
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
        TextView txtProducto = (TextView)item.findViewById(R.id.txtProducto);
        TextView txtNumLinea = (TextView)item.findViewById(R.id.txtNumLinea);
        Button btnBorrar = (Button)item.findViewById(R.id.btnBorrar);
        btnBorrar.setTag(position);
        btnBorrar.setOnClickListener(this);
        txtProducto.setText(arrayList.get(position).descripcion);
        txtNumLinea.setText(arrayList.get(position).potencia);
        return item;
    }

    @Override
    public void onClick(View v) {
        TabFragment2.borrarArrayProductos((int)v.getTag(),context);
    }
}
