package com.multimedia.aes.gestnet_sgsv2.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorListaEquipamientos;
import com.multimedia.aes.gestnet_sgsv2.clases.DataEquipamientos;
import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.UsoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.MarcaCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Potencia;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Index;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TabFragment2 extends Fragment implements View.OnClickListener {

    private View vista;
    private Spinner spTipo,spMarca,spUso,spPotencia,spPuestaMarcha,spTipoEquipamiento;
    private EditText etModelo,etPotenciaFuego,etCodigo,etC0,etTempMaxACS,etCaudalACS,etPotenciaUtil,
            etTempGasesComb,etTempAmbienteLocal,etTempAguaGeneCalorEntrada,etTempAguaGeneCalorSalida;
    private Button btnDespiece,btnAñadirEquip;
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
    private static int alto=0, height;
    private static ListView lvEquipamientos;
    private static ArrayList<DataEquipamientos> arraylistEquipamiento = new ArrayList<>();
    private static AdaptadorListaEquipamientos adaptadorListaEquipamientos;


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
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        height=height/16;
        spTipo = (Spinner)vista.findViewById(R.id.spTipo);
        spMarca = (Spinner)vista.findViewById(R.id.spMarca);
        spUso = (Spinner)vista.findViewById(R.id.spUso);
        spPotencia = (Spinner)vista.findViewById(R.id.spPotencia);
        spPuestaMarcha = (Spinner)vista.findViewById(R.id.spPuestaMarcha);
        spTipoEquipamiento = (Spinner)vista.findViewById(R.id.spTipoEquipamiento);
        etModelo = (EditText)vista.findViewById(R.id.etModelo);
        etPotenciaFuego = (EditText)vista.findViewById(R.id.etPotenciaFuego);
        etCodigo = (EditText)vista.findViewById(R.id.etCodigo);
        etC0 = (EditText)vista.findViewById(R.id.etC0);
        etTempMaxACS = (EditText)vista.findViewById(R.id.etTempMaxACS);
        etCaudalACS = (EditText)vista.findViewById(R.id.etCaudalACS);
        etPotenciaUtil = (EditText)vista.findViewById(R.id.etPotenciaUtil);
        etTempGasesComb = (EditText)vista.findViewById(R.id.etTempGasesComb);
        etTempAmbienteLocal = (EditText)vista.findViewById(R.id.etTempAmbienteLocal);
        etTempAguaGeneCalorEntrada = (EditText)vista.findViewById(R.id.etTempAguaGeneCalorEntrada);
        etTempAguaGeneCalorSalida = (EditText)vista.findViewById(R.id.etTempAguaGeneCalorSalida);

        btnDespiece = (Button)vista.findViewById(R.id.btnDespiece);
        btnAñadirEquip = (Button)vista.findViewById(R.id.btnAñadirEquip);
        lvEquipamientos = (ListView)vista.findViewById(R.id.lvEquipamientos);
        btnAñadirEquip.setOnClickListener(this);
        try {
            listaTipos = TipoCalderaDAO.buscarTodosLosTipoCaldera(getContext());
            tipos = new String[listaTipos.size()+1];
            tipos[0]="--Seleccione un valor--";
            for (int i = 1; i < listaTipos.size()+1; i++) {
                tipos[i]=listaTipos.get(i-1).getNombre_tipo_caldera();
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
            marcas = new String[listaMarcas.size()+1];
            marcas[0]="--Seleccione un valor--";
            for (int i = 1; i < listaMarcas.size()+1; i++) {
                marcas[i]=listaMarcas.get(i-1).getNombre_marca_caldera();
            }
            spMarca.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, marcas));


            listaUso = UsoCalderaDAO.buscarTodosLosUsoCaldera(getContext());
            usos=new String[listaUso.size()+1];
            usos[0]="--Seleccione un valor--";
            for (int i = 1; i < listaUso.size()+1; i++) {
                usos[i]=listaUso.get(i-1).getNombre_uso_caldera();
            }
            spUso.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, usos));

            listaPotencia = PotenciaDAO.buscarTodosLosPotencia(getContext());
            potencias=new String[listaPotencia.size()+1];
            potencias[0]="--Seleccione un valor--";
            for (int i = 1; i < listaPotencia.size()+1; i++) {
                potencias[i]=listaPotencia.get(i-1).getPotencia();
            }
            spPotencia.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, potencias));

            Date d = new Date();
            String s  = String.valueOf(DateFormat.format("yyyy", d.getTime()));
            int año = Integer.parseInt(s);
            puestaMarcha=new String[67];
            puestaMarcha[0]="--Seleccione un valor--";
            int a = 1;
            for (int i = año-65; i <= año; i++) {
                puestaMarcha[a]=String.valueOf(i);
                a++;
            }
            spPuestaMarcha.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, puestaMarcha));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!mantenimiento.getModelo_maquina().equals("null")&&!mantenimiento.getModelo_maquina().equals("")) {
            etModelo.setText(mantenimiento.getModelo_maquina());
        }
        int estado = Integer.parseInt(mantenimiento.getEstado_android());
        if (estado==0){

        }else if (estado==1){

        }else if (estado==2){
            spMarca.setEnabled(false);
            spTipo.setEnabled(false);
            spPuestaMarcha.setEnabled(false);
            spPotencia.setEnabled(false);
            spUso.setEnabled(false);
            etModelo.setEnabled(false);
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

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnAñadirEquip){
            if (!etPotenciaFuego.getText().toString().trim().equals("")&&spTipoEquipamiento.getSelectedItemPosition()!=0){
                alto+=height;
                lvEquipamientos.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto));
                arraylistEquipamiento.add(new DataEquipamientos(etPotenciaFuego.getText().toString(),spTipoEquipamiento.getItemAtPosition(spTipoEquipamiento.getSelectedItemPosition()).toString()));
                adaptadorListaEquipamientos = new AdaptadorListaEquipamientos(getContext(), R.layout.camp_adapter_list_view_equipamientos, arraylistEquipamiento);
                lvEquipamientos.setAdapter(adaptadorListaEquipamientos);
            }else{
                Toast.makeText(getContext(), "Faltan datos en el equipamiento", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public static void borrarArrayProductos(int position, Context context){
        arraylistEquipamiento.remove(position);
        alto-=height;
        lvEquipamientos.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto));
        adaptadorListaEquipamientos = new AdaptadorListaEquipamientos(context, R.layout.camp_adapter_list_view_equipamientos, arraylistEquipamiento);
        lvEquipamientos.setAdapter(adaptadorListaEquipamientos);
    }

    public MantenimientoTerminado guardarDatos(MantenimientoTerminado manten){
        int a = spTipo.getSelectedItemPosition();
        if (a!=0){
            try {
                int fk_tipo_maquina = TipoCalderaDAO.buscarTipoCalderaPorNombre(getContext(),spTipo.getSelectedItem().toString());
                manten.setFk_tipo_maquina(fk_tipo_maquina);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            manten.setFk_tipo_maquina(-1);
        }
        int b = spMarca.getSelectedItemPosition();
        if (b!=0){
            try {
                int fk_marca_maquina = MarcaCalderaDAO.buscarMarcaCalderaPorNombre(getContext(),spMarca.getSelectedItem().toString());
                manten.setFk_marca_maquina(fk_marca_maquina);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            manten.setFk_marca_maquina(-1);
        }
        int c = spMarca.getSelectedItemPosition();
        if (c!=0){
            try {
                int fk_uso_maquina = UsoCalderaDAO.buscarUsoCalderaPorNombre(getContext(),spUso.getSelectedItem().toString());
                manten.setFk_uso_maquina(fk_uso_maquina);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            manten.setFk_uso_maquina(-1);
        }
        int d = spPotencia.getSelectedItemPosition();
        if (d!=0){
            try {
                int fk_potencia_maquina = PotenciaDAO.buscarPotenciaPorNombre(getContext(),spPotencia.getSelectedItem().toString());
                manten.setFk_potencia_maquina(fk_potencia_maquina);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            manten.setFk_potencia_maquina(-1);
        }
        int f = spPuestaMarcha.getSelectedItemPosition();
        if (f!=0){
            String puesta_marcha_maquina = spPotencia.getSelectedItem().toString();
            manten.setPuesta_marcha_maquina(puesta_marcha_maquina);
        }
        if (!etModelo.getText().toString().trim().equals("")){
            String modelo_maquina = etModelo.getText().toString();
            manten.setModelo_maquina(modelo_maquina);
        }
        if (!etCodigo.getText().toString().trim().equals("")){
            String codigo_maquina = etCodigo.getText().toString();
            manten.setCodigo_maquina(codigo_maquina);
        }
        if (!etC0.getText().toString().trim().equals("")){
            String c0_maquina = etC0.getText().toString();
            manten.setC0_maquina(c0_maquina);
        }

        if (!etTempMaxACS.getText().toString().trim().equals("")){
            String temperatura_max_acs = etTempMaxACS.getText().toString();
            manten.setTemperatura_max_acs(temperatura_max_acs);
        }
        if (!etCaudalACS.getText().toString().trim().equals("")){
            String caudal_acs = etCaudalACS.getText().toString();
            manten.setCaudal_acs(caudal_acs);
        }
        if (!etPotenciaUtil.getText().toString().trim().equals("")){
            String potencia_util = etPotenciaUtil.getText().toString();
            manten.setPotencia_util(potencia_util);
        }
        if (!etTempGasesComb.getText().toString().trim().equals("")){
            String temperatura_gases_combustion = etTempGasesComb.getText().toString();
            manten.setTemperatura_gases_combustion(temperatura_gases_combustion);
        }
        if (!etTempAmbienteLocal.getText().toString().trim().equals("")){
            String temperatura_ambiente_local = etTempAmbienteLocal.getText().toString();
            manten.setTemperatura_ambiente_local(temperatura_ambiente_local);
        }
        if (!etTempAguaGeneCalorEntrada.getText().toString().trim().equals("")){
            String temperatura_agua_generador_calor_entrada = etTempAguaGeneCalorEntrada.getText().toString();
            manten.setTemperatura_agua_generador_calor_entrada(temperatura_agua_generador_calor_entrada);
        }
        if (!etTempAguaGeneCalorSalida.getText().toString().trim().equals("")){
            String temperatura_agua_generador_calor_salida = etTempAguaGeneCalorSalida.getText().toString();
            manten.setTemperatura_agua_generador_calor_salida(temperatura_agua_generador_calor_salida);
        }
        if (!arraylistEquipamiento.isEmpty()){
            for (int i = 0; i < arraylistEquipamiento.size(); i++) {
                String potencia = arraylistEquipamiento.get(i).potencia;
                int fk_equipamiento = 0;
                if (arraylistEquipamiento.get(i).descripcion.equals("Cocina")){
                    fk_equipamiento = 1;
                }else if (arraylistEquipamiento.get(i).descripcion.equals("Horno")){
                    fk_equipamiento = 2;
                }else if (arraylistEquipamiento.get(i).descripcion.equals("Horno + Grill")){
                    fk_equipamiento = 3;
                }
                EquipamientoCalderaDAO.newEquipamientoCaldera(getContext(),potencia,fk_equipamiento,mantenimiento.getFk_maquina());
            }
        }
        return manten;
    }

}