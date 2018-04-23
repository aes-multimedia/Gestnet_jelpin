package com.multimedia.aes.gestnet_nucleo.adaptador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.fragments.TabFragment2_equipo;

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

        LinearLayout llMaquina = (LinearLayout) item.findViewById(R.id.llMaquina);
        TextView txtMarca = (TextView)item.findViewById(R.id.txtMarca);
        TextView txtModelo = (TextView)item.findViewById(R.id.txtModelo);
        TextView txtCombustion = (TextView)item.findViewById(R.id.txtCombustible);
        TextView txtPotencia = (TextView)item.findViewById(R.id.txtPotencia);
        Button btnBorrar = (Button)item.findViewById(R.id.btnBorrar);
        btnBorrar.setTag(position);
        btnBorrar.setOnClickListener(this);
        llMaquina.setOnClickListener(this);
        llMaquina.setTag(position);
        String marca = null;
        String potencia = null;
        try {
            marca = MarcaDAO.buscarNombreMarcaPorId(context,arrayList.get(position).getFk_marca());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtMarca.setText(marca);
        txtModelo.setText(arrayList.get(position).getModelo());
        txtPotencia.setText(potencia);
        txtCombustion.setText(arrayList.get(position).getCombustible_txt());

        return item;
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnBorrar) {
            TabFragment2_equipo.borrarArrayMaquina(arrayList.get((int) v.getTag()).getId_maquina(), context);
        }else if(v.getId()==R.id.llMaquina){
            TabFragment2_equipo.rellenarDatosMaquina(arrayList.get((int) v.getTag()).getId_maquina(),context,(int) v.getTag());
        }
    }
}
