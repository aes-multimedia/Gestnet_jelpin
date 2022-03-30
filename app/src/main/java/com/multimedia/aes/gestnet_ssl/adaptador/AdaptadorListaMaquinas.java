package com.multimedia.aes.gestnet_ssl.adaptador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.dao.MarcaDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Maquina;
import com.multimedia.aes.gestnet_ssl.nucleo.AnadirDatosMaquina;

import java.sql.SQLException;
import java.util.ArrayList;


public class AdaptadorListaMaquinas extends ArrayAdapter implements View.OnClickListener {
    //VARIABLES GLOBALES
    private Context context;
    private int view;
    private ArrayList<Maquina> arrayList;
    private Activity activity;
    //CONSTRUCTOR
    public AdaptadorListaMaquinas(Context context, int view, ArrayList<Maquina> arrayList, Activity activity) {
        super(context, view, arrayList);
        this.context = context;
        this.view = view;
        this.arrayList=arrayList;
        this.activity=activity;
    }
    //OVERRIDE METHODS
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(view, null);
        }

        LinearLayout llMaquina = item.findViewById(R.id.llMaquina);
        TextView txtMarca =item.findViewById(R.id.txtMarca);
        TextView txtModelo = item.findViewById(R.id.txtModelo);
        TextView txtCombustion = item.findViewById(R.id.txtCombustible);
        TextView txtPotencia = item.findViewById(R.id.txtPotencia);
        llMaquina.setOnClickListener(this);
        llMaquina.setTag(arrayList.get(position).getId_maquina());
        String marca = null;
        String potencia = null;
        try {
            marca = MarcaDAO.buscarNombreMarcaPorId(context,arrayList.get(position).getFk_marca());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtMarca.setText(marca);
        txtModelo.setText(arrayList.get(position).getModelo()+" | "+arrayList.get(position).getUbicacion());
        txtPotencia.setText(potencia);
        txtCombustion.setText(arrayList.get(position).getCombustible_txt());

        return item;
    }
    @Override
    public void onClick(View v) {

       if(v.getId()==R.id.llMaquina){
           Intent i = new Intent(getContext(), AnadirDatosMaquina.class);
           i.putExtra("id",Integer.parseInt(String.valueOf(v.getTag())));
           getContext().startActivity(i);
        }
    }
}
