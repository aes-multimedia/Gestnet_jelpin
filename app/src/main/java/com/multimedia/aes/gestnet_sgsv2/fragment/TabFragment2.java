package com.multimedia.aes.gestnet_sgsv2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.UsoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.MarcaCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Potencia;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class TabFragment2 extends Fragment {

    private View vista;
    private Spinner spTipo,spMarca,spUso,spPotencia,spPuestaMarcha;
    private EditText etModelo;
    private Button btnDespiece;
    private List<TipoCaldera> listaTipos=null;
    private List<MarcaCaldera> listaMarcas=null;
    private List<UsoCaldera> listaUso=null;
    private List<Potencia> listaPotencia=null;
    private String[] tipos;
    private String[] marcas;
    private String[] usos;
    private String[] potencias;
    private String[] puestaMarcha;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_2, container, false);
        spTipo = (Spinner)vista.findViewById(R.id.spTipo);
        spMarca = (Spinner)vista.findViewById(R.id.spMarca);
        spUso = (Spinner)vista.findViewById(R.id.spUso);
        spPotencia = (Spinner)vista.findViewById(R.id.spPotencia);
        spPuestaMarcha = (Spinner)vista.findViewById(R.id.spPuestaMarcha);
        try {
            listaTipos = TipoCalderaDAO.buscarTodosLosTipoCaldera(getContext());
            tipos = new String[listaTipos.size()];
            for (int i = 0; i < listaTipos.size(); i++) {
                tipos[i]=listaTipos.get(i).getNombre_tipo_caldera();
            }
            spTipo.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipos));
/*
            listaMarcas = MarcaCalderaDAO.buscarTodosLosMarcaCaldera(getContext());
            for (int i = 0; i < listaMarcas.size()-1; i++) {
                marcas[i]=listaMarcas.get(i).getNombre_marca_caldera();
            }
            spMarca.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, marcas));

            listaUso = UsoCalderaDAO.buscarTodosLosUsoCaldera(getContext());
            for (int i = 0; i < listaUso.size(); i++) {
                usos[i]=listaUso.get(i).getNombre_uso_caldera();
            }
            spUso.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, usos));

            listaPotencia = PotenciaDAO.buscarTodosLosPotencia(getContext());
            for (int i = 0; i < listaPotencia.size(); i++) {
                potencias[i]=listaPotencia.get(i).getPotencia();
            }
            spPotencia.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, potencias));

            Date d = new Date();
            String s  = String.valueOf(DateFormat.format("yyyy", d.getTime()));
            int año = Integer.parseInt(s);
            for (int i = 1950; i < año; i++) {
                puestaMarcha[i]=String.valueOf(i);
            }
            spPuestaMarcha.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, puestaMarcha));*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vista;
    }

}