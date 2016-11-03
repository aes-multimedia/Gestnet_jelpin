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
import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MaquinaMantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoEquipamientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.UsoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Equipamiento;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;
import com.multimedia.aes.gestnet_sgsv2.entities.MaquinaMantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MarcaCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Potencia;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoEquipamiento;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TabFragment2 extends Fragment implements View.OnClickListener {

    private View vista;
    private static Spinner spTipo;
    private static Spinner spMarca;
    private static Spinner spUso;
    private static Spinner spPotencia;
    private static Spinner spPuestaMarcha;
    private static Spinner spTipoEquipamiento;
    private static EditText etModelo;
    private static EditText etPotenciaFuego;
    private static EditText etC0;
    private static EditText etTempMaxACS;
    private static EditText etCaudalACS;
    private static EditText etPotenciaUtil;
    private static EditText etTempGasesComb;
    private static EditText etTempAmbienteLocal;
    private static EditText etTempAguaGeneCalorEntrada;
    private static EditText etTempAguaGeneCalorSalida;
    private static EditText etCo2Ambiente;
    private static EditText etRendimientoAparato;
    private static EditText etCoCorregido;
    private static EditText etCoAmbiente;
    private static EditText etTiro;
    private static EditText etCo2;
    private static EditText etO2;
    private static EditText etLambda;
    private Button btnDespiece,btnAñadirEquip,btnAñadirMaquina;
    private List<TipoCaldera> listaTipos=null;
    private List<MarcaCaldera> listaMarcas=null;
    private List<UsoCaldera> listaUso=null;
    private List<Potencia> listaPotencia=null;
    private List<TipoEquipamiento> listaEquipamientos=null;
    private String[] tipos;
    private String[] marcas;
    private String[] usos;
    private String[] potencias;
    private String[] puestaMarcha;
    private String[] equip;
    private Mantenimiento mantenimiento = null;
    private static int alto=0,alto1=0, height;
    private static ListView lvEquipamientos,lvMaquinas;
    private static ArrayList<DataEquipamientos> arraylistEquipamiento = new ArrayList<>();
    private static AdaptadorListaEquipamientos adaptadorListaEquipamientos;
    private static ArrayList<Maquina> arrayListMaquina = new ArrayList<>();
    private static AdaptadorListaMaquinas adaptadorListaMaquinas;
    private LinearLayout llCo2;
    private List<Equipamiento> equipamientos= null;
    private List<MaquinaMantenimiento>maquinaMantenimientos=null;
    private MaquinaMantenimiento maquina = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_2, container, false);
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getContext(),id);
            maquinaMantenimientos = MaquinaMantenimientoDAO.buscarMaquinaMantenimientoPorIdMantenimiento(getContext(),mantenimiento.getId_mantenimiento());
            maquina = MaquinaMantenimientoDAO.buscarMaquinaMantenimientoPorbprincipal(getContext(),mantenimiento.getId_mantenimiento());
            equipamientos = EquipamientoDAO.buscarEquipamientoPorIdMaquina(getContext(),maquina.getId_maquina());
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

            String tipo=null;
            if (TipoCalderaDAO.buscarTipoCalderaPorId(getContext(), maquina.getFk_tipo_maquina())!=null){
                tipo = TipoCalderaDAO.buscarTipoCalderaPorId(getContext(), maquina.getFk_tipo_maquina()).getNombre_tipo_caldera();
            }
            if (tipo!=null) {
                String myString = tipo;
                ArrayAdapter myAdap = (ArrayAdapter) spTipo.getAdapter();
                int spinnerPosition = myAdap.getPosition(myString);
                spTipo.setSelection(spinnerPosition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            listaMarcas = MarcaCalderaDAO.buscarTodosLosMarcaCaldera(getContext());
            marcas = new String[listaMarcas.size()+1];
            marcas[0]="--Seleccione un valor--";
            for (int i = 1; i < listaMarcas.size()+1; i++) {
                marcas[i]=listaMarcas.get(i-1).getNombre_marca_caldera();
            }
            spMarca.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, marcas));

            String marca = null;
            if (MarcaCalderaDAO.buscarMarcaCalderaPorId(getContext(),maquina.getFk_marca_maquina())!=null){
                marca = MarcaCalderaDAO.buscarMarcaCalderaPorId(getContext(),maquina.getFk_marca_maquina()).getNombre_marca_caldera();
            }
            if (marca!=null) {
                String myString = marca;
                ArrayAdapter myAdap = (ArrayAdapter) spMarca.getAdapter();
                int spinnerPosition = myAdap.getPosition(myString);
                spMarca.setSelection(spinnerPosition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            listaUso = UsoCalderaDAO.buscarTodosLosUsoCaldera(getContext());
            usos=new String[listaUso.size()+1];
            usos[0]="--Seleccione un valor--";
            for (int i = 1; i < listaUso.size()+1; i++) {
                usos[i]=listaUso.get(i-1).getNombre_uso_caldera();
            }
            spUso.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, usos));

            String uso = null;
            int uso1 = maquina.getFk_uso_maquina();
            if (UsoCalderaDAO.buscarUsoCalderaPorId(getContext(), uso1)!=null){
                uso = UsoCalderaDAO.buscarUsoCalderaPorId(getContext(), uso1).getNombre_uso_caldera();
            }
            if (uso!=null) {
                String myString = uso;
                ArrayAdapter myAdap = (ArrayAdapter) spUso.getAdapter();
                int spinnerPosition = myAdap.getPosition(myString);
                spUso.setSelection(spinnerPosition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            listaPotencia = PotenciaDAO.buscarTodosLosPotencia(getContext());
            potencias=new String[listaPotencia.size()+1];
            potencias[0]="--Seleccione un valor--";
            for (int i = 1; i < listaPotencia.size()+1; i++) {
                potencias[i]=listaPotencia.get(i-1).getPotencia();
            }
            spPotencia.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, potencias));

            String potencia = null;
            if (maquina.getFk_potencia_maquina()!=0) {
                int potencia1 = maquina.getFk_potencia_maquina();
                try {
                    potencia = PotenciaDAO.buscarPotenciaPorId(getContext(),potencia1).getPotencia();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (potencia!=null) {
                String myString = potencia;
                ArrayAdapter myAdap = (ArrayAdapter) spPotencia.getAdapter();
                int spinnerPosition = myAdap.getPosition(myString);
                spPotencia.setSelection(spinnerPosition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        String puesta = null;
        if (maquina.getPuesta_marcha_maquina().equals("null")||maquina.getPuesta_marcha_maquina().equals("")) {
        }else{
            puesta = maquina.getPuesta_marcha_maquina();
            puesta = puesta.substring(0,4);
        }
        if (puesta!=null) {
            String myString = puesta;
            ArrayAdapter myAdap = (ArrayAdapter) spPuestaMarcha.getAdapter();
            int spinnerPosition = myAdap.getPosition(myString);
            spPuestaMarcha.setSelection(spinnerPosition);
        }
        if (!maquina.getModelo_maquina().equals("null")&&!maquina.getModelo_maquina().equals("")) {
            etModelo.setText(maquina.getModelo_maquina());
        }
        try {
            listaEquipamientos = TipoEquipamientoDAO.buscarTodosLosTipoEquipamiento(getContext());
            equip=new String[listaEquipamientos.size()+1];
            equip[0]="--Seleccione un valor--";
            for (int i = 1; i < listaEquipamientos.size()+1; i++) {
                equip[i]=listaEquipamientos.get(i-1).getNombre_tipo_equipamiento();
            }
            spTipoEquipamiento.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, equip));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (maquina.getC0_maquina().toString().equals("")||maquina.getC0_maquina().toString().equals("null")||maquina.getC0_maquina().toString().equals("0")){
        }else{
            etC0.setText(maquina.getC0_maquina()+"");
        }
        if (maquina.getTemperatura_max_acs().toString().equals("")||maquina.getTemperatura_max_acs().toString().equals("null")||maquina.getTemperatura_max_acs().toString().equals("0")){
        }else{
            etTempMaxACS.setText(maquina.getTemperatura_max_acs()+"");
        }
        if (maquina.getCaudal_acs().toString().equals("")||maquina.getCaudal_acs().toString().equals("null")||maquina.getCaudal_acs().toString().equals("0")){
        }else{
            etCaudalACS.setText(maquina.getCaudal_acs()+"");
        }
        if (maquina.getPotencia_util().toString().equals("")||maquina.getPotencia_util().toString().equals("null")||maquina.getPotencia_util().toString().equals("0")){
        }else{
            etPotenciaUtil.setText(maquina.getPotencia_util()+"");
        }
        if (maquina.getTemperatura_gases_combustion().toString().equals("")||maquina.getTemperatura_gases_combustion().toString().equals("null")||maquina.getTemperatura_gases_combustion().toString().equals("0")){
        }else{
            etTempGasesComb.setText(maquina.getTemperatura_gases_combustion()+"");
        }
        if (maquina.getTemperatura_ambiente_local().toString().equals("")||maquina.getTemperatura_ambiente_local().toString().equals("null")||maquina.getTemperatura_ambiente_local().toString().equals("0")){
        }else{
            etTempAmbienteLocal.setText(maquina.getTemperatura_ambiente_local()+"");
        }
        if (maquina.getTemperatura_agua_generador_calor_entrada().toString().equals("")||maquina.getTemperatura_agua_generador_calor_entrada().toString().equals("null")||maquina.getTemperatura_agua_generador_calor_entrada().toString().equals("0")){
        }else{
            etTempAguaGeneCalorEntrada.setText(maquina.getTemperatura_agua_generador_calor_entrada()+"");
        }
        if (maquina.getTemperatura_agua_generador_calor_salida().toString().equals("")||maquina.getTemperatura_agua_generador_calor_salida().toString().equals("null")||maquina.getTemperatura_agua_generador_calor_salida().toString().equals("0")){
        }else{
            etTempAguaGeneCalorSalida.setText(maquina.getTemperatura_agua_generador_calor_salida()+"");
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
        if (equipamientos!=null) {
            for (int i = 0; i < equipamientos.size(); i++) {
                String tipo_equipamiento = null;
                try {
                    tipo_equipamiento = TipoEquipamientoDAO.buscarNombreTipoEquipamientoPorId(getContext(), equipamientos.get(i).getFk_tipo_equipamiento());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (tipo_equipamiento != null) {
                    alto += height;
                    lvEquipamientos.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto));
                    arraylistEquipamiento.add(new DataEquipamientos(equipamientos.get(i).getPotencia_fuegos(), tipo_equipamiento, equipamientos.get(i).getCo2_equipamiento()));
                    adaptadorListaEquipamientos = new AdaptadorListaEquipamientos(getContext(), R.layout.camp_adapter_list_view_equipamientos, arraylistEquipamiento);
                    lvEquipamientos.setAdapter(adaptadorListaEquipamientos);
                }

            }
        }
        return vista;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnAñadirEquip){
            if (!etPotenciaFuego.getText().toString().trim().equals("")&&spTipoEquipamiento.getSelectedItemPosition()!=0){
                alto+=height;
                lvEquipamientos.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto));
                arraylistEquipamiento.add(new DataEquipamientos(etPotenciaFuego.getText().toString(),spTipoEquipamiento.getSelectedItem().toString(),etCo2Ambiente.getText().toString()));
                adaptadorListaEquipamientos = new AdaptadorListaEquipamientos(getContext(), R.layout.camp_adapter_list_view_equipamientos, arraylistEquipamiento);
                lvEquipamientos.setAdapter(adaptadorListaEquipamientos);
                spTipoEquipamiento.setSelection(0);
                etPotenciaFuego.setText("");
                etCo2Ambiente.setText("");
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
                    spTipo.setSelection(0);
                    spMarca.setSelection(0);
                    spUso.setSelection(0);
                    spPotencia.setSelection(0);
                    spPuestaMarcha.setSelection(0);
                    etModelo.setText("");
                    etC0.setText("");
                    etTempMaxACS.setText("");
                    etCaudalACS.setText("");
                    etPotenciaUtil.setText("");
                    etTempGasesComb.setText("");
                    etTempAmbienteLocal.setText("");
                    etRendimientoAparato.setText("");
                    etCoCorregido.setText("");
                    etCoAmbiente.setText("");
                    etTiro.setText("");
                    etCo2.setText("");
                    etO2.setText("");
                    etLambda.setText("");
                    etTempAguaGeneCalorEntrada.setText("");
                    etTempAguaGeneCalorSalida.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    public static void borrarArrayEquipamientos(int position, Context context){
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
        lvMaquinas.setAdapter(adaptadorListaMaquinas);
    }

    public MantenimientoTerminado guardarDatos(MantenimientoTerminado mantenimientoTerminado) throws SQLException {
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
                int fk_equipamiento = TipoEquipamientoDAO.buscarTipoEquipamientoPorNombre(getContext(),arraylistEquipamiento.get(i).descripcion);
                String co2 = arraylistEquipamiento.get(i).co2_ambiente;

                EquipamientoCalderaDAO.newEquipamientoCaldera(getContext(),1,potencia,co2,fk_equipamiento,mantenimiento.getId_mantenimiento());
            }
        }
        return mantenimientoTerminado;
    }
    private String llenarMaquina(Maquina m) throws SQLException {
        if (spTipo.getSelectedItemPosition()!=0){
            if (spMarca.getSelectedItemPosition()!=0){
                if (!etModelo.getText().toString().trim().equals("")){
                    if (spPotencia.getSelectedItemPosition()!=0){
                        if (spUso.getSelectedItemPosition()!=0){
                            if (spPuestaMarcha.getSelectedItemPosition()!=0){
                                if (!etC0.getText().toString().trim().equals("")){
                                    if (!etTempMaxACS.getText().toString().trim().equals("")){
                                        if (!etCaudalACS.getText().toString().trim().equals("")){
                                            if (!etPotenciaUtil.getText().toString().trim().equals("")){
                                                if (!etTempGasesComb.getText().toString().trim().equals("")){
                                                    if (!etTempAmbienteLocal.getText().toString().trim().equals("")){
                                                        if (!etTempAguaGeneCalorEntrada.getText().toString().trim().equals("")){
                                                            if (!etTempAguaGeneCalorSalida.getText().toString().trim().equals("")){
                                                                if (!etRendimientoAparato.getText().toString().trim().equals("")){
                                                                    if (!etCoCorregido.getText().toString().trim().equals("")){
                                                                        if (!etCoAmbiente.getText().toString().trim().equals("")){
                                                                            if (!etTiro.getText().toString().trim().equals("")){
                                                                                if (!etCo2.getText().toString().trim().equals("")){
                                                                                    if (!etO2.getText().toString().trim().equals("")){
                                                                                        if (!etLambda.getText().toString().trim().equals("")){
                                                                                            m.setId_maquina(maquina.getId_maquina());
                                                                                            m.setFk_mantenimiento(mantenimiento.getId_mantenimiento());
                                                                                            m.setFk_tipo_maquina(TipoCalderaDAO.buscarTipoCalderaPorNombre(getContext(),spTipo.getSelectedItem().toString()));
                                                                                            m.setFk_marca_maquina(MarcaCalderaDAO.buscarMarcaCalderaPorNombre(getContext(),spMarca.getSelectedItem().toString()));
                                                                                            m.setModelo_maquina(etModelo.getText().toString());
                                                                                            m.setFk_potencia_maquina(PotenciaDAO.buscarPotenciaPorNombre(getContext(),spPotencia.getSelectedItem().toString()));
                                                                                            m.setFk_uso_maquina(UsoCalderaDAO.buscarUsoCalderaPorNombre(getContext(),spUso.getSelectedItem().toString()));
                                                                                            m.setPuesta_marcha_maquina(spPuestaMarcha.getSelectedItem().toString());
                                                                                            m.setCodigo_maquina("01");
                                                                                            m.setC0_maquina(etC0.getText().toString());
                                                                                            m.setTemperatura_max_acs(etTempMaxACS.getText().toString());
                                                                                            m.setCaudal_acs(etCaudalACS.getText().toString());
                                                                                            m.setPotencia_util(etPotenciaUtil.getText().toString());
                                                                                            m.setTemperatura_gases_combustion(etTempGasesComb.getText().toString());
                                                                                            m.setTemperatura_ambiente_local(etTempAmbienteLocal.getText().toString());
                                                                                            m.setTemperatura_agua_generador_calor_entrada(etTempAguaGeneCalorEntrada.getText().toString());
                                                                                            m.setTemperatura_agua_generador_calor_salida(etTempAguaGeneCalorSalida.getText().toString());
                                                                                            m.setRendimiento_aparato(etRendimientoAparato.getText().toString());
                                                                                            m.setCo_corregido(etCoCorregido.getText().toString());
                                                                                            m.setCo_ambiente(etCoAmbiente.getText().toString());
                                                                                            m.setTiro(etTiro.getText().toString());
                                                                                            m.setCo2(etCo2.getText().toString());
                                                                                            m.setO2(etO2.getText().toString());
                                                                                            m.setLambda(etLambda.getText().toString());
                                                                                        }else{
                                                                                            Toast.makeText(getContext(), "Seleccione un lambda", Toast.LENGTH_SHORT).show();
                                                                                            return null;
                                                                                        }
                                                                                    }else{
                                                                                        Toast.makeText(getContext(), "Seleccione un O2", Toast.LENGTH_SHORT).show();
                                                                                        return null;
                                                                                    }
                                                                                }else{
                                                                                    Toast.makeText(getContext(), "Seleccione un Co2", Toast.LENGTH_SHORT).show();
                                                                                    return null;
                                                                                }
                                                                            }else{
                                                                                Toast.makeText(getContext(), "Seleccione un tiro", Toast.LENGTH_SHORT).show();
                                                                                return null;
                                                                            }
                                                                        }else{
                                                                            Toast.makeText(getContext(), "Seleccione un Co ambiente", Toast.LENGTH_SHORT).show();
                                                                            return null;
                                                                        }
                                                                    }else{
                                                                        Toast.makeText(getContext(), "Seleccione un Co corregido", Toast.LENGTH_SHORT).show();
                                                                        return null;
                                                                    }
                                                                }else{
                                                                    Toast.makeText(getContext(), "Seleccione un rendimiento del aparato", Toast.LENGTH_SHORT).show();
                                                                    return null;
                                                                }
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

    public static void rellenarDatosMaquina(Maquina maquina,Context context,int position) {
        try {
            int tipo= maquina.getFk_tipo_maquina();
            String myString = TipoCalderaDAO.buscarTipoCalderaPorId(context,tipo).getNombre_tipo_caldera();
            ArrayAdapter myAdap = (ArrayAdapter) spTipo.getAdapter();
            int spinnerPosition = myAdap.getPosition(myString);
            spTipo.setSelection(spinnerPosition);

            int marca = maquina.getFk_marca_maquina();
            myString = MarcaCalderaDAO.buscarMarcaCalderaPorId(context,marca).getNombre_marca_caldera();
            myAdap = (ArrayAdapter) spMarca.getAdapter();
            spinnerPosition = myAdap.getPosition(myString);
            spMarca.setSelection(spinnerPosition);

            int uso = maquina.getFk_uso_maquina();
            myString = UsoCalderaDAO.buscarUsoCalderaPorId(context, uso).getNombre_uso_caldera();
            myAdap = (ArrayAdapter) spUso.getAdapter();
            spinnerPosition = myAdap.getPosition(myString);
            spUso.setSelection(spinnerPosition);

            int potencia = maquina.getFk_potencia_maquina();
            myString = PotenciaDAO.buscarPotenciaPorId(context,potencia).getPotencia();
            myAdap = (ArrayAdapter) spPotencia.getAdapter();
            spinnerPosition = myAdap.getPosition(myString);
            spPotencia.setSelection(spinnerPosition);

            myString = maquina.getPuesta_marcha_maquina();
            myAdap = (ArrayAdapter) spPuestaMarcha.getAdapter();
            spinnerPosition = myAdap.getPosition(myString);
            spPuestaMarcha.setSelection(spinnerPosition);

            etModelo.setText(maquina.getModelo_maquina());
            etC0.setText(maquina.getC0_maquina());
            etTempMaxACS.setText(maquina.getTemperatura_max_acs());
            etCaudalACS.setText(maquina.getCaudal_acs());
            etPotenciaUtil.setText(maquina.getPotencia_util());
            etTempGasesComb.setText(maquina.getTemperatura_gases_combustion());
            etTempAmbienteLocal.setText(maquina.getTemperatura_ambiente_local());
            etTempAguaGeneCalorEntrada.setText(maquina.getTemperatura_agua_generador_calor_entrada());
            etTempAguaGeneCalorSalida.setText(maquina.getTemperatura_agua_generador_calor_salida());
            etRendimientoAparato.setText(maquina.getRendimiento_aparato());
            etCoCorregido.setText(maquina.getCo_corregido());
            etCoAmbiente.setText(maquina.getCo_ambiente());
            etTiro.setText(maquina.getTiro());
            etCo2.setText(maquina.getCo2());
            etO2.setText(maquina.getO2());
            etLambda.setText(maquina.getLambda());
            arrayListMaquina.remove(position);
            alto1-=height;
            lvMaquinas.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto1));
            adaptadorListaMaquinas = new AdaptadorListaMaquinas(context, R.layout.camp_adapter_list_view_maquinas, arrayListMaquina);
            lvMaquinas.setAdapter(adaptadorListaMaquinas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void rellenarDatosEquipamiento(DataEquipamientos equipamiento,Context context,int position){
        String myString = equipamiento.descripcion;
        ArrayAdapter myAdap = (ArrayAdapter) spTipoEquipamiento.getAdapter();
        int spinnerPosition = myAdap.getPosition(myString);
        spTipoEquipamiento.setSelection(spinnerPosition);

        etPotenciaFuego.setText(equipamiento.potencia);
        etCo2Ambiente.setText(equipamiento.co2_ambiente);

        arraylistEquipamiento.remove(position);
        alto-=height;
        lvEquipamientos.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto));
        adaptadorListaEquipamientos = new AdaptadorListaEquipamientos(context, R.layout.camp_adapter_list_view_equipamientos, arraylistEquipamiento);
        lvEquipamientos.setAdapter(adaptadorListaEquipamientos);
    }
}