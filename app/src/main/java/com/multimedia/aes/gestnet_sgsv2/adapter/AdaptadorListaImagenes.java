package com.multimedia.aes.gestnet_sgsv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.clases.DataImagenes;
import com.multimedia.aes.gestnet_sgsv2.fragment.TabFragment3;

import java.util.ArrayList;

public class AdaptadorListaImagenes extends ArrayAdapter implements View.OnClickListener {
    private Context context;
    private int view;
    private ArrayList<DataImagenes> arrayList;

    public AdaptadorListaImagenes(Context context, int view, ArrayList<DataImagenes> arrayList) {
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
        TextView txtNombre = (TextView)item.findViewById(R.id.txtNombre);
        ImageView ivFoto = (ImageView)item.findViewById(R.id.ivFoto);
        Button btnBorrar = (Button)item.findViewById(R.id.btnBorrar);
        btnBorrar.setTag(position);
        btnBorrar.setOnClickListener(this);
        String nombre = arrayList.get(position).nombre;
        if (nombre.length()>20){
            nombre=nombre.substring(0,10)+"..."+nombre.substring(nombre.length()-5, nombre.length());
        }
        txtNombre.setText(nombre);
        ivFoto.setImageBitmap(arrayList.get(position).bitmap);
        return item;
    }

    @Override
    public void onClick(View v) {
        TabFragment3.borrarArrayImagenes((int)v.getTag(),context);
    }
}