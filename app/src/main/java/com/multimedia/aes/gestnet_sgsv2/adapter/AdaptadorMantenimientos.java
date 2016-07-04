package com.multimedia.aes.gestnet_sgsv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;

import java.util.ArrayList;

public class AdaptadorMantenimientos extends ArrayAdapter {

    private Context context;
    private int view;
    private ArrayList<Mantenimiento> arrayList;

    public AdaptadorMantenimientos(Context context, int view, ArrayList<Mantenimiento> arrayList) {
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
        TextView empresa = (TextView) item.findViewById(R.id.txtEmpresa);
        empresa.setText(String.valueOf(arrayList.get(position).getFk_direccion()));
        return item;
    }
}
