package com.multimedia.aes.gestnet_sgsv2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.SubTiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.SubTiposVisita;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposReparaciones;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposVisita;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Index;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class TabFragment3 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;
    private Spinner spEstadoVisita, spTipoVisita, spTipoReparacion,  spSubTipoVisita;
    private EditText etCodBarras, etObservaciones, etCosteMateriales, etManoObra;
    private CheckBox cbContadorInterno, cbReparacion;
    private DatePicker dpFechaReparacion;
    private TimePicker tpTiempoReparacion;
    private Button btnFinalizar,btnImprimir;
    private List<TiposReparaciones> tiposReparacion;
    private String[] tipos;
    private TextView tvFechaVisita,tvFechaLimite,txtFinalizado;
    private Mantenimiento mantenimiento = null;
    private LinearLayout llReparacion;
    private List<TiposVisita> listaTiposVisita=null;
    private List<SubTiposVisita> listaSubTiposVista=null;
    private String tiposVisita [];
    private TiposVisita tipoVisita;
    private LinearLayout linearSubtipos;
    private String subTiposVisita[];
    private ScrollView scFinalizar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_3, container, false);
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getContext(),id);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        spEstadoVisita = (Spinner) vista.findViewById(R.id.spEstadoVisita);
        spTipoVisita = (Spinner) vista.findViewById(R.id.spTipoVisita);
        spTipoReparacion = (Spinner) vista.findViewById(R.id.spTipoReparacion);
        tpTiempoReparacion = (TimePicker) vista.findViewById(R.id.tpTiempoReparacion);
        etCodBarras = (EditText) vista.findViewById(R.id.etCodigoBarras);
        etObservaciones = (EditText) vista.findViewById(R.id.etObservaciones);
        etCosteMateriales = (EditText) vista.findViewById(R.id.etCosteMateriales);
        etManoObra = (EditText) vista.findViewById(R.id.etCosteManoDeObra);
        cbContadorInterno = (CheckBox) vista.findViewById(R.id.cbContadorInterno);
        cbReparacion = (CheckBox) vista.findViewById(R.id.cbReparacion);
        dpFechaReparacion = (DatePicker) vista.findViewById(R.id.dpFechaReparacion);
        btnFinalizar =(Button) vista.findViewById(R.id.btnFinalizar);
        btnImprimir =(Button) vista.findViewById(R.id.btnImprimir);
        tvFechaVisita = (TextView)vista.findViewById(R.id.tvFechaVisita);
        tvFechaLimite = (TextView)vista.findViewById(R.id.tvFechaLimite);
        txtFinalizado = (TextView)vista.findViewById(R.id.txtFinalizado);
        llReparacion = (LinearLayout)vista.findViewById(R.id.llReparacion);
        llReparacion.setVisibility(View.GONE);
        spSubTipoVisita = (Spinner)vista.findViewById(R.id.spSubTipoVisita);
        linearSubtipos = (LinearLayout)vista.findViewById(R.id.linearSubtipos);
        scFinalizar = (ScrollView)vista.findViewById(R.id.scFinalizar);
        cbReparacion.setOnClickListener(this);
        btnFinalizar.setOnClickListener(this);
        btnImprimir.setOnClickListener(this);
        spTipoVisita.setOnItemSelectedListener(this);

        String dateSample = mantenimiento.getFecha_visita();
        String oldFormat = "dd-MM-yyyy HH:mm:ss";
        String newFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
        String fecha = "";
        try {
            fecha = sdf2.format(sdf1.parse(dateSample));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvFechaVisita.setText(fecha);

        dateSample = mantenimiento.getFecha_maxima_endesa();
        try {
            fecha = sdf2.format(sdf1.parse(dateSample));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvFechaLimite.setText(fecha);


        try{
            listaTiposVisita = TiposVisitaDAO.buscarTodosLosTipoVisita(getContext());
            tiposVisita = new String[listaTiposVisita.size()];
            for (int i = 0; i < listaTiposVisita.size(); i++) {
                tiposVisita[i]=listaTiposVisita.get(i).getDescripcion();
            }
            spTipoVisita.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tiposVisita));

        }catch (SQLException e){
            e.printStackTrace();
        }


        try {
            tiposReparacion = TiposReparacionesDAO.buscarTodosLosTiposReparaciones(getContext());
            tipos = new String[tiposReparacion.size()];
            for (int i = 0; i < tiposReparacion.size(); i++) {
                tipos[i]=tiposReparacion.get(i).getAbreviatura();
            }
            spTipoReparacion.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipos));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int estado = Integer.parseInt(mantenimiento.getEstado_android());
        if (estado==0){

        }else if (estado==1){
            btnImprimir.setVisibility(View.GONE);
            scFinalizar.setVisibility(View.VISIBLE);
            txtFinalizado.setVisibility(View.GONE);
        }else if (estado==2){

        }else if (estado==3){
            scFinalizar.setVisibility(View.GONE);
            btnImprimir.setVisibility(View.VISIBLE);
            txtFinalizado.setVisibility(View.VISIBLE);
        }
        return vista;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.cbReparacion){
            if (cbReparacion.isChecked()){
                llReparacion.setVisibility(View.VISIBLE);
            }else{
                llReparacion.setVisibility(View.GONE);
            }
        }else if (view.getId()==R.id.btnFinalizar) {
            ((Index)getContext()).ticket();
            try {
                MantenimientoDAO.actualizarEstadoAndroid(getContext(), 2, mantenimiento.getId_mantenimiento());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (view.getId()==R.id.btnImprimir){
            ((Index)getContext()).ticket();
        }



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (view.getId()==R.id.spTipoVisita) {
            if (i == 2) {
                try {
                    listaSubTiposVista = SubTiposVisitaDAO.buscarSubTiposVisitaPorTipo(getContext(), 3);
                    subTiposVisita = new String[listaSubTiposVista.size()];
                    for (int j = 0; j < listaSubTiposVista.size(); j++) {
                        subTiposVisita[j] = listaSubTiposVista.get(j).getCodigo();
                    }
                    spSubTipoVisita.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, subTiposVisita));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                linearSubtipos.setVisibility(View.VISIBLE);
            } else if (i == 4) {
                try {
                    listaSubTiposVista = SubTiposVisitaDAO.buscarSubTiposVisitaPorTipo(getContext(), 5);
                    subTiposVisita = new String[listaSubTiposVista.size()];
                    for (int j = 0; j < listaSubTiposVista.size(); j++) {
                        subTiposVisita[j] = listaSubTiposVista.get(j).getCodigo();
                    }
                    spSubTipoVisita.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, subTiposVisita));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                linearSubtipos.setVisibility(View.VISIBLE);
            } else if (i == 5) {
                try {
                    listaSubTiposVista = SubTiposVisitaDAO.buscarSubTiposVisitaPorTipo(getContext(), 6);
                    subTiposVisita = new String[listaSubTiposVista.size()];
                    for (int j = 0; j < listaSubTiposVista.size(); j++) {
                        subTiposVisita[j] = listaSubTiposVista.get(j).getCodigo();
                    }
                    spSubTipoVisita.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, subTiposVisita));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                linearSubtipos.setVisibility(View.VISIBLE);
            } else {
                linearSubtipos.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}