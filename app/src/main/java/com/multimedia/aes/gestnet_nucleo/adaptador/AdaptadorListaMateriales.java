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
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment2_equipo;

import java.sql.SQLException;
import java.util.ArrayList;


public class AdaptadorListaMateriales extends ArrayAdapter implements View.OnClickListener {
    //VARIABLES GLOBALES
    private Context context;
    private int view;
    private ArrayList<Articulo> arrayList;
    private Activity activity;
    private int fk_parte;
    //CONSTRUCTOR
    public AdaptadorListaMateriales(Context context, int view, ArrayList<Articulo> arrayList, Activity activity,int fk_parte) {
        super(context, view, arrayList);
        this.context = context;
        this.view = view;
        this.arrayList=arrayList;
        this.activity=activity;
        this.fk_parte=fk_parte;
    }
    //OVERRIDE METHODS
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(view, null);
        }
        TextView txtTituloArticulo = (TextView) item.findViewById(R.id.txtTituloArticulo);
        TextView txtUsadas = (TextView) item.findViewById(R.id.txtUsadas);
        TextView txtPrecio = (TextView) item.findViewById(R.id.txtPrecio);
        txtTituloArticulo.setText(arrayList.get(position).getNombre_articulo()+"-"+arrayList.get(position).getReferencia());
        int usados = 1;
        try {
            usados = ArticuloParteDAO.buscarArticuloPartePorFkParteFkArticulo(context,arrayList.get(position).getId_articulo(),fk_parte).getUsados();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtUsadas.setText(usados+"");
        txtPrecio.setText(arrayList.get(position).getCoste()*usados+"€");

        return item;
    }
    @Override
    public void onClick(View v) {

    }
}
