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
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;
import com.multimedia.aes.gestnet_sgsv2.entities.Potencia;
import com.multimedia.aes.gestnet_sgsv2.fragment.TabFragment2;

import java.sql.SQLException;
import java.util.ArrayList;


public class AdaptadorListaMaquinas extends ArrayAdapter implements View.OnClickListener {
    private Context context;
    private int view;
    private ArrayList<Maquina> arrayList;

    public AdaptadorListaMaquinas(Context context, int view, ArrayList<Maquina> arrayList) {
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
        TextView txtMarca = (TextView)item.findViewById(R.id.txtMarca);
        TextView txtModelo = (TextView)item.findViewById(R.id.txtModelo);
        TextView txtPotencia = (TextView)item.findViewById(R.id.txtPotencia);
        Button btnBorrar = (Button)item.findViewById(R.id.btnBorrar);
        btnBorrar.setTag(position);
        btnBorrar.setOnClickListener(this);
        String marca = null;
        String potencia = null;
        try {
            marca = MarcaCalderaDAO.buscarNombreMarcaCalderaPorId(context,arrayList.get(position).getFk_marca_maquina());
            potencia = PotenciaDAO.buscarNombrePotenciaPorId(context,arrayList.get(position).getFk_potencia_maquina());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtMarca.setText(marca);
        txtModelo.setText(arrayList.get(position).getModelo_maquina());
        txtPotencia.setText(potencia);
        return item;
    }

    @Override
    public void onClick(View v) {
        TabFragment2.borrarArrayMaquinas((int)v.getTag(),context);
    }
}
