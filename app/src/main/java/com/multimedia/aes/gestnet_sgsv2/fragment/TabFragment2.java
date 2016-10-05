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
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorListaMaquinas;
import com.multimedia.aes.gestnet_sgsv2.clases.DataEquipamientos;
import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.UsoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;
import com.multimedia.aes.gestnet_sgsv2.entities.MarcaCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Potencia;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

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
            etTempGasesComb,etTempAmbienteLocal,etTempAguaGeneCalorEntrada,etTempAguaGeneCalorSalida,
            etCo2Ambiente,etRendimientoAparato,etCoCorregido,etCoAmbiente,etTiro,etCo2,etO2,etLambda;
    private Button btnDespiece,btnAñadirEquip,btnAñadirMaquina;
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
    private static int alto=0,alto1=0, height;
    private static ListView lvEquipamientos,lvMaquinas;
    private static ArrayList<DataEquipamientos> arraylistEquipamiento = new ArrayList<>();
    private static AdaptadorListaEquipamientos adaptadorListaEquipamientos;
    private static ArrayList<Maquina> arrayListMaquina = new ArrayList<>();
    private static AdaptadorListaMaquinas adaptadorListaMaquinas;
    private LinearLayout llCo2;


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
        etCo2Ambiente = (EditText)vista.findViewById(R.id.etCo2Ambiente);
        etRendimientoAparato = (EditText)vista.findViewById(R.id.etRendimientoAparato);
        etCoCorregido = (EditText)vista.findViewById(R.id.etCoCorregido);
        etCoAmbiente = (EditText)vista.findViewById(R.id.etCoAmbiente);
        etTiro = (EditText)vista.findViewById(R.id.etTiro);
        etCo2 = (EditText)vista.findViewById(R.id.etCo2);
        etO2 = (EditText)vista.findViewById(R.id.etO2);
        etLambda = (EditText)vista.findViewById(R.id.etLambda);

        btnDespiece = (Button)vista.findViewById(R.id.btnDespiece);
        btnAñadirEquip = (Button)vista.findViewById(R.id.btnAñadirEquip);
        btnAñadirMaquina = (Button)vista.findViewById(R.id.btnAñadirMaquina);
        lvEquipamientos = (ListView)vista.findViewById(R.id.lvEquipamientos);
        lvMaquinas = (ListView)vista.findViewById(R.id.lvMaquinas);
        llCo2 = (LinearLayout) vista.findViewById(R.id.llCo2);
        btnAñadirEquip.setOnClickListener(this);
        btnAñadirMaquina.setOnClickListener(this);
        try {
            listaTipos = TipoCalderaDAO.buscarTodosLosTipoCaldera(getContext());
            tipos = new String[listaTipos.size()+1];
            tipos[0]="--Seleccione un valor--";
            for (int i = 1; i < listaTipos.size()+1; i++) {
                tipos[i]=listaTipos.get(i-1).getNombre_tipo_caldera();
            }
            spTipo.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipos));

           /* String tipo = null;
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
            }*/

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
            etCodigo.setEnabled(false);
            etC0.setEnabled(false);
            etTempMaxACS.setEnabled(false);
            etCaudalACS.setEnabled(false);
            etPotenciaUtil.setEnabled(false);
            etTempGasesComb.setEnabled(false);
            etTempAmbienteLocal.setEnabled(false);
            etTempAguaGeneCalorEntrada.setEnabled(false);
            etTempAguaGeneCalorSalida.setEnabled(false);
            spTipoEquipamiento.setEnabled(false);
            etPotenciaFuego.setEnabled(false);
            btnAñadirMaquina.setEnabled(false);
            btnAñadirEquip.setEnabled(false);
        }else if (estado==3){
            spMarca.setEnabled(false);
            spTipo.setEnabled(false);
            spPuestaMarcha.setEnabled(false);
            spPotencia.setEnabled(false);
            spUso.setEnabled(false);
            etModelo.setEnabled(false);
            etCodigo.setEnabled(false);
            etC0.setEnabled(false);
            etTempMaxACS.setEnabled(false);
            etCaudalACS.setEnabled(false);
            etPotenciaUtil.setEnabled(false);
            etTempGasesComb.setEnabled(false);
            etTempAmbienteLocal.setEnabled(false);
            etTempAguaGeneCalorEntrada.setEnabled(false);
            etTempAguaGeneCalorSalida.setEnabled(false);
            spTipoEquipamiento.setEnabled(false);
            etPotenciaFuego.setEnabled(false);
            btnAñadirMaquina.setEnabled(false);
            btnAñadirEquip.setEnabled(false);
        }
        return vista;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnAñadirEquip){
            if (!etPotenciaFuego.getText().toString().trim().equals("")&&spTipoEquipamiento.getSelectedItemPosition()!=0){
                alto+=height;
                lvEquipamientos.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto));
                arraylistEquipamiento.add(new DataEquipamientos(etPotenciaFuego.getText().toString(),spTipoEquipamiento.getItemAtPosition(spTipoEquipamiento.getSelectedItemPosition()).toString(),etCo2Ambiente.getText().toString()));
                adaptadorListaEquipamientos = new AdaptadorListaEquipamientos(getContext(), R.layout.camp_adapter_list_view_equipamientos, arraylistEquipamiento);
                lvEquipamientos.setAdapter(adaptadorListaEquipamientos);
            }else{
                Toast.makeText(getContext(), "Faltan datos en el equipamiento", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId()==R.id.btnAñadirMaquina){
            Maquina m = new Maquina();
            try {
                if (llenarMaquina(m)!=null){
                    alto1+=height;
                    lvMaquinas.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto1));
                    arrayListMaquina.add(m);
                    adaptadorListaMaquinas = new AdaptadorListaMaquinas(getContext(), R.layout.camp_adapter_list_view_maquinas, arrayListMaquina);
                    lvMaquinas.setAdapter(adaptadorListaMaquinas);
                    /*spTipo.setSelection(0);
                    spMarca.setSelection(0);
                    spUso.setSelection(0);
                    spPotencia.setSelection(0);
                    spPuestaMarcha.setSelection(0);
                    spTipoEquipamiento.setSelection(0);
                    etModelo.setText("");
                    etPotenciaFuego.setText("");
                    etCodigo.setText("");
                    etC0.setText("");
                    etTempMaxACS.setText("");
                    etCaudalACS.setText("");
                    etPotenciaUtil.setText("");
                    etTempGasesComb.setText("");
                    etTempAmbienteLocal.setText("");
                    etTempAguaGeneCalorEntrada.setText("");
                    etTempAguaGeneCalorSalida.setText("");*/
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
    public static void borrarArrayMaquinas(int position, Context context){
        arrayListMaquina.remove(position);
        alto1-=height;
        lvMaquinas.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto1));
        adaptadorListaMaquinas = new AdaptadorListaMaquinas(context, R.layout.camp_adapter_list_view_maquinas, arrayListMaquina);
        lvMaquinas.setAdapter(adaptadorListaEquipamientos);
    }

    public MantenimientoTerminado guardarDatos(MantenimientoTerminado mantenimientoTerminado){
        if (!arrayListMaquina.isEmpty()){
            for (int i = 0; i < arrayListMaquina.size(); i++) {
                MaquinaDAO.newMaquina(getContext(),arrayListMaquina.get(i));
            }
            arrayListMaquina.clear();
            mantenimientoTerminado.setMaquina(true);
        }else{
            mantenimientoTerminado.setMaquina(false);
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
                String co2 = arraylistEquipamiento.get(i).co2_ambiente;
                EquipamientoCalderaDAO.newEquipamientoCaldera(getContext(),potencia,fk_equipamiento,mantenimiento.getId_mantenimiento(),co2);
            }
        }
        return mantenimientoTerminado;
    }
    private String llenarMaquina(Maquina m) throws SQLException {
        m.setFk_mantenimiento(mantenimiento.getId_mantenimiento());
        if (spTipo.getSelectedItemPosition()!=0){
            m.setFk_tipo_maquina(TipoCalderaDAO.buscarTipoCalderaPorNombre(getContext(),spTipo.getSelectedItem().toString()));
            if (spMarca.getSelectedItemPosition()!=0){
                m.setFk_marca_maquina(MarcaCalderaDAO.buscarMarcaCalderaPorNombre(getContext(),spMarca.getSelectedItem().toString()));
                if (!etModelo.getText().toString().trim().equals("")){
                    m.setModelo_maquina(etModelo.getText().toString());
                    if (spPotencia.getSelectedItemPosition()!=0){
                        m.setFk_potencia_maquina(PotenciaDAO.buscarPotenciaPorNombre(getContext(),spPotencia.getSelectedItem().toString()));
                        if (spUso.getSelectedItemPosition()!=0){
                            m.setFk_uso_maquina(UsoCalderaDAO.buscarUsoCalderaPorNombre(getContext(),spUso.getSelectedItem().toString()));
                            if (spPuestaMarcha.getSelectedItemPosition()!=0){
                                m.setPuesta_marcha_maquina(spPuestaMarcha.getSelectedItem().toString());
                                if (!etCodigo.getText().toString().trim().equals("")){
                                    m.setCodigo_maquina(etCodigo.getText().toString());
                                    if (!etC0.getText().toString().trim().equals("")){
                                        m.setC0_maquina(etC0.getText().toString());
                                        if (!etTempMaxACS.getText().toString().trim().equals("")){
                                            m.setTemperatura_max_acs(etTempMaxACS.getText().toString());
                                            if (!etCaudalACS.getText().toString().trim().equals("")){
                                                m.setCaudal_acs(etCaudalACS.getText().toString());
                                                if (!etPotenciaUtil.getText().toString().trim().equals("")){
                                                    m.setPotencia_util(etPotenciaUtil.getText().toString());
                                                    if (!etTempGasesComb.getText().toString().trim().equals("")){
                                                        m.setTemperatura_gases_combustion(etTempGasesComb.getText().toString());
                                                        if (!etTempAmbienteLocal.getText().toString().trim().equals("")){
                                                            m.setTemperatura_ambiente_local(etTempAmbienteLocal.getText().toString());
                                                            if (!etTempAguaGeneCalorEntrada.getText().toString().trim().equals("")){
                                                                m.setTemperatura_agua_generador_calor_entrada(etTempAguaGeneCalorEntrada.getText().toString());
                                                                if (!etTempAguaGeneCalorSalida.getText().toString().trim().equals("")){
                                                                    m.setTemperatura_agua_generador_calor_salida(etTempAguaGeneCalorSalida.getText().toString());
                                                                    m.setRendimiento_aparato(etRendimientoAparato.getText().toString());
                                                                    m.setCo_corregido(etCoCorregido.getText().toString());
                                                                    m.setCo_ambiente(etCoAmbiente.getText().toString());
                                                                    m.setTiro(etTiro.getText().toString());
                                                                    m.setCo2(etCo2.getText().toString());
                                                                    m.setO2(etO2.getText().toString());
                                                                    m.setLambda(etLambda.getText().toString());
                                                                }else{
                                                                    Toast.makeText(getContext(), "Seleccione una temperatura del generador de calor salida", Toast.LENGTH_SHORT).show();
                                                                    return null;
                                                                }
                                                            }else{
                                                                Toast.makeText(getContext(), "Seleccione una temperatura del generador de calor entrada", Toast.LENGTH_SHORT).show();
                                                                return null;
                                                            }
                                                        }else{
                                                            Toast.makeText(getContext(), "Seleccione una temperatura ambiente local", Toast.LENGTH_SHORT).show();
                                                            return null;
                                                        }
                                                    }else{
                                                        Toast.makeText(getContext(), "Seleccione una temperatura de gases de combustion", Toast.LENGTH_SHORT).show();
                                                        return null;
                                                    }
                                                }else{
                                                    Toast.makeText(getContext(), "Seleccione una potencia util", Toast.LENGTH_SHORT).show();
                                                    return null;
                                                }
                                            }else{
                                                Toast.makeText(getContext(), "Seleccione un caudal acs", Toast.LENGTH_SHORT).show();
                                                return null;
                                            }
                                        }else{
                                            Toast.makeText(getContext(), "Seleccione una temperatura maxima de acs", Toast.LENGTH_SHORT).show();
                                            return null;
                                        }
                                    }else{
                                        Toast.makeText(getContext(), "Seleccione un CO", Toast.LENGTH_SHORT).show();
                                        return null;
                                    }
                                }else{
                                    Toast.makeText(getContext(), "Seleccione un codigo", Toast.LENGTH_SHORT).show();
                                    return null;
                                }
                            }else{
                                Toast.makeText(getContext(), "Seleccione una puesta en marcha", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                        }else{
                            Toast.makeText(getContext(), "Seleccione un uso", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                    }else{
                        Toast.makeText(getContext(), "Seleccione una potencia", Toast.LENGTH_SHORT).show();
                        return null;
                    }
                }else{
                    Toast.makeText(getContext(), "Seleccione un modelo", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }else{
                Toast.makeText(getContext(), "Seleccione una marca", Toast.LENGTH_SHORT).show();
                return null;
            }
        }else{
            Toast.makeText(getContext(), "Seleccione un tipo", Toast.LENGTH_SHORT).show();
            return null;
        }
        return "";
    }
}