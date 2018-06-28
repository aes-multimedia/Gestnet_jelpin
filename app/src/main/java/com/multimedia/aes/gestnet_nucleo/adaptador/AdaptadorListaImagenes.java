package com.multimedia.aes.gestnet_nucleo.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.fragments.TabFragment5_documentacion;
import com.multimedia.aes.gestnet_nucleo.clases.DataImagenes;
import com.multimedia.aes.gestnet_nucleo.nucleo.Galeria;
import com.multimedia.aes.gestnet_nucleo.nucleo.GaleriaV2;

import java.util.ArrayList;

public class AdaptadorListaImagenes extends ArrayAdapter implements View.OnClickListener {
    //VARIABLES GLOBALES
    private Context context;
    private int view;
    private ArrayList<DataImagenes> arrayList;
    //CONSTRUCTOR
    public AdaptadorListaImagenes(Context context, int view, ArrayList<DataImagenes> arrayList) {
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
        TextView txtNombre =item.findViewById(R.id.txtNombre);
        ImageView ivFoto = item.findViewById(R.id.ivFoto);
        Button btnBorrar = item.findViewById(R.id.btnBorrar);
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

        GaleriaV2.borrarArrayImagenes((int)v.getTag(),context);
    }
}