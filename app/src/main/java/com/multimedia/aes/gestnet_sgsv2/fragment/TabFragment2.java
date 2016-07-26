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
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.UsoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MarcaCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Potencia;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

import org.json.JSONException;
import org.json.JSONObject;

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
    private Mantenimiento mantenimiento = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_2, container, false);
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getContext(),id);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        spTipo = (Spinner)vista.findViewById(R.id.spTipo);
        spMarca = (Spinner)vista.findViewById(R.id.spMarca);
        spUso = (Spinner)vista.findViewById(R.id.spUso);
        spPotencia = (Spinner)vista.findViewById(R.id.spPotencia);
        spPuestaMarcha = (Spinner)vista.findViewById(R.id.spPuestaMarcha);
        etModelo = (EditText)vista.findViewById(R.id.etModelo);
        btnDespiece = (Button)vista.findViewById(R.id.btnDespiece);
        try {
            listaTipos = TipoCalderaDAO.buscarTodosLosTipoCaldera(getContext());
            tipos = new String[listaTipos.size()];
            for (int i = 0; i < listaTipos.size(); i++) {
                tipos[i]=listaTipos.get(i).getNombre_tipo_caldera();
            }
            spTipo.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipos));

            String tipo = null;
            if (!mantenimiento.getTipo_maquina().equals("null")) {
                tipo= mantenimiento.getTipo_maquina();
                try {
                    tipo = TipoCalderaDAO.buscarTipoCalderaPorId(getContext(),Integer.parseInt(tipo)).getNombre_tipo_caldera();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (tipo!=null) {
                String myString = tipo;
                ArrayAdapter myAdap = (ArrayAdapter) spTipo.getAdapter();
                int spinnerPosition = myAdap.getPosition(myString);
                spTipo.setSelection(spinnerPosition);
            }

            listaMarcas = MarcaCalderaDAO.buscarTodosLosMarcaCaldera(getContext());
            marcas = new String[listaMarcas.size()];
            for (int i = 0; i < listaMarcas.size(); i++) {
                marcas[i]=listaMarcas.get(i).getNombre_marca_caldera();
            }
            spMarca.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, marcas));

            String marca= mantenimiento.getMarca_maquina();
            ArrayAdapter myAdap = (ArrayAdapter) spMarca.getAdapter();
            int spinnerPosition = myAdap.getPosition(marca);
            spMarca.setSelection(spinnerPosition);


            listaUso = UsoCalderaDAO.buscarTodosLosUsoCaldera(getContext());
            usos=new String[listaUso.size()];
            for (int i = 0; i < listaUso.size(); i++) {
                usos[i]=listaUso.get(i).getNombre_uso_caldera();
            }
            spUso.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, usos));

            listaPotencia = PotenciaDAO.buscarTodosLosPotencia(getContext());
            potencias=new String[listaPotencia.size()];
            for (int i = 0; i < listaPotencia.size(); i++) {
                potencias[i]=listaPotencia.get(i).getPotencia();
            }
            spPotencia.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, potencias));

            Date d = new Date();
            String s  = String.valueOf(DateFormat.format("yyyy", d.getTime()));
            int año = Integer.parseInt(s);
            puestaMarcha=new String[66];
            int a = 0;
            for (int i = año-65; i <= año; i++) {
                puestaMarcha[a]=String.valueOf(i);
                a++;
            }
            spPuestaMarcha.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, puestaMarcha));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int estado = Integer.parseInt(mantenimiento.getEstado_android());
        if (estado==0){

        }else if (estado==1){

        }else if (estado==2){

        }else if (estado==3){
            spMarca.setEnabled(false);
            spTipo.setEnabled(false);
            spPuestaMarcha.setEnabled(false);
            spPotencia.setEnabled(false);
            spUso.setEnabled(false);
            etModelo.setEnabled(false);
        }
        return vista;
    }

}