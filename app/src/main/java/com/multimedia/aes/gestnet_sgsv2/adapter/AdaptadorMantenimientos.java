package com.multimedia.aes.gestnet_sgsv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        TextView direccion = (TextView) item.findViewById(R.id.txtDireccion);
        TextView provincia = (TextView) item.findViewById(R.id.txtProvincia);
        TextView cp = (TextView) item.findViewById(R.id.txtCP);
        LinearLayout global = (LinearLayout)item.findViewById(R.id.global);
        ImageView ivEstado = (ImageView)item.findViewById(R.id.ivEstado);
        cambiarColorEstado(position,ivEstado);
        direccion.setText(String.valueOf(arrayList.get(position).getDireccion()));
        cp.setText("C.P.:  "+String.valueOf(arrayList.get(position).getCod_postal()));
        provincia.setText("("+String.valueOf(arrayList.get(position).getProvincia())+"-"+String.valueOf(arrayList.get(position).getMunicipio())+")");
        global.setTag(String.valueOf(arrayList.get(position).getId_mantenimiento()));
        return item;
    }
    public void cambiarColorEstado(int position,ImageView ivEstado){
        int estado = Integer.parseInt(arrayList.get(position).getEstado_android());
        if (estado==0){
            ivEstado.setImageResource(R.drawable.circle);
        }else if (estado==1){
            ivEstado.setImageResource(R.drawable.circle);
        }else if (estado==2){
            ivEstado.setImageResource(R.drawable.circle);
        }else if (estado==3){
            ivEstado.setImageResource(R.drawable.circle);
        }else if (estado==4){

        }
    }
}
