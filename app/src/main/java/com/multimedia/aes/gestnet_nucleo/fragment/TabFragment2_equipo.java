package com.multimedia.aes.gestnet_nucleo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Marca;

import java.util.ArrayList;


public class TabFragment2_equipo extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private View vista;

    private static Spinner spTipo, spMarca, spUso, spPuestaMarcha, spPotencia,spAnalisisFinalizacion;

    private static EditText etModelo,  etC0, etTempMaxACS, etCaudalACS, etPotenciaUtil,
            etTempGasesComb, etTempAmbienteLocal, etTempAguaGeneCalorEntrada,
            etTempAguaGeneCalorSalida, etCo2Ambiente, etRendimientoAparato, etCoCorregido,
            etCoAmbiente, etTiro, etCo2, etO2, etLambda,etCoAmbienteSoloCocina,etNombreMedicion;
    private Button btnAñadirMaquina,btnDatosTesto,btnCoAmbienteTesto,btnAñadirAnalisis;
    private TextView txtSn;
    private ArrayList<Marca> arrayListMarcas= new ArrayList<>();
    private static ListView lvMaquinas,lvAnalisis;
    private static CheckBox cbCampana, cbMaximaPotencia,cbMinimaPotencia;
    private static LinearLayout llDatosTesto,llMaxMinPotencia,llAnalisisFinalizacion;
    private LinearLayout llMaquina,llSoloCocina;
    private static int alto=0,alto1=0,alto2=0, height=0;
    String[] arrayMarcas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment2_equipo, container, false);
        inicializarVariables();
        try {
            darValores();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return vista;
    }




    private void darValores() throws java.sql.SQLException {
        //SPINNER MARCAS
        if (MarcaDAO.buscarTodasLasMarcas(getContext())!=null){
            try {
                arrayListMarcas.addAll(MarcaDAO.buscarTodasLasMarcas(getContext()));
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            int indice=0;

            arrayMarcas = new String[arrayListMarcas.size() + 1];
            arrayMarcas[0]= "--Seleciones un valor--";
            for (int i = 1; i < arrayListMarcas.size() + 1; i++) {
                if (arrayListMarcas.get(i - 1).getId_marca()!=0||arrayListMarcas.get(i - 1).getId_marca()!=-1){
                    arrayMarcas[i] = arrayListMarcas.get(i - 1).getNombre_marca()+"-"+arrayListMarcas.get(i - 1).getId_marca();
                }
            }
            spMarca.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayMarcas));
        }else {

            arrayMarcas= new String[1];
            arrayMarcas[0]= "Sin protocolos";
            spMarca.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayMarcas));
        }
    }



    public void inicializarVariables(){
        spTipo = (Spinner)vista.findViewById(R.id.spTipo);
        spMarca = (Spinner)vista.findViewById(R.id.spMarca);
        spUso = (Spinner)vista.findViewById(R.id.spUso);
        spPotencia = (Spinner)vista.findViewById(R.id.spPotencia);
        spAnalisisFinalizacion = (Spinner)vista.findViewById(R.id.spAnalisisFinalizacion);
        spPuestaMarcha = (Spinner)vista.findViewById(R.id.spPuestaMarcha);
        etModelo = (EditText)vista.findViewById(R.id.etModelo);
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
        etCoAmbienteSoloCocina = (EditText)vista.findViewById(R.id.etCoAmbienteSoloCocina);
        etNombreMedicion = (EditText)vista.findViewById(R.id.etNombreMedicion);
        cbCampana = (CheckBox)vista.findViewById(R.id.cbCampana);
        cbMaximaPotencia = (CheckBox)vista.findViewById(R.id.cbMaximaPotencia);
        cbMinimaPotencia = (CheckBox)vista.findViewById(R.id.cbMinimaPotencia);
        llDatosTesto = (LinearLayout)vista.findViewById(R.id.llDatosTesto);
        llMaxMinPotencia = (LinearLayout)vista.findViewById(R.id.llMaxMinPotencia);
        llMaquina = (LinearLayout)vista.findViewById(R.id.llMaquina);
        llSoloCocina = (LinearLayout)vista.findViewById(R.id.llSoloCocina);
        llAnalisisFinalizacion = (LinearLayout)vista.findViewById(R.id.llAnalisisFinalizacion);
        btnAñadirMaquina = (Button)vista.findViewById(R.id.btnAñadirMaquina);
        btnDatosTesto = (Button)vista.findViewById(R.id.btnDatosTesto);
        btnCoAmbienteTesto = (Button)vista.findViewById(R.id.btnCoAmbienteTesto);
        btnAñadirAnalisis = (Button)vista.findViewById(R.id.btnAñadirAnalisis);
        lvMaquinas = (ListView)vista.findViewById(R.id.lvMaquinas);
        lvAnalisis = (ListView)vista.findViewById(R.id.lvAnalisis);
        txtSn = (TextView)vista.findViewById(R.id.txtSn);
        btnAñadirMaquina.setOnClickListener(this);
        btnDatosTesto.setOnClickListener(this);
        btnCoAmbienteTesto.setOnClickListener(this);
        btnAñadirAnalisis.setOnClickListener(this);
        cbMaximaPotencia.setOnClickListener(this);
        cbMinimaPotencia.setOnClickListener(this);
        cbCampana.setOnClickListener(this);
        llMaxMinPotencia.setVisibility(View.GONE);
        spTipo.setOnItemSelectedListener(this);
        llDatosTesto.setVisibility(View.GONE);
        alto=0;
        alto1=0;
        alto2=0;
        height=0;
    }




    @Override
    public void onClick(View view) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}