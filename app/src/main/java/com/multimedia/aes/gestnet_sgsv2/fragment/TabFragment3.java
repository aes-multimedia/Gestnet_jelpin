package com.multimedia.aes.gestnet_sgsv2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposReparaciones;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Firmar;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class TabFragment3 extends Fragment implements View.OnClickListener {
    private View vista;
    private Spinner spEstadoVisita, spTipoVisita, spTipoReparacion, spTiempoReparacion;
    private EditText etCodBarras, etObservaciones, etCosteMateriales, etManoObra;
    private CheckBox cbContadorInterno, cbReparacion;
    private DatePicker dpFechaReparacion;
    private Button btnFinalizar;
    private List<TiposReparaciones> tiposReparacion;
    private String[] tipos;
    private TextView tvFechaVisita,tvFechaLimite;
    private Mantenimiento mantenimiento = null;
    private LinearLayout llReparacion;


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
        spTiempoReparacion = (Spinner) vista.findViewById(R.id.spTiempoReparacion);
        etCodBarras = (EditText) vista.findViewById(R.id.etCodigoBarras);
        etObservaciones = (EditText) vista.findViewById(R.id.etObservaciones);
        etCosteMateriales = (EditText) vista.findViewById(R.id.etCosteMateriales);
        etManoObra = (EditText) vista.findViewById(R.id.etCosteManoDeObra);
        cbContadorInterno = (CheckBox) vista.findViewById(R.id.cbContadorInterno);
        cbReparacion = (CheckBox) vista.findViewById(R.id.cbReparacion);
        dpFechaReparacion = (DatePicker) vista.findViewById(R.id.dpFechaReparacion);
        btnFinalizar =(Button) vista.findViewById(R.id.btnFinalizar);
        tvFechaVisita = (TextView)vista.findViewById(R.id.tvFechaVisita);
        tvFechaLimite = (TextView)vista.findViewById(R.id.tvFechaLimite);
        llReparacion = (LinearLayout)vista.findViewById(R.id.llReparacion);
        llReparacion.setVisibility(View.GONE);
        cbReparacion.setOnClickListener(this);
        btnFinalizar.setOnClickListener(this);

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
        }else if (view.getId()==R.id.btnFinalizar){
            Intent i = new Intent(getContext(), Firmar.class);
            getContext().startActivity(i);
            try {
                MantenimientoDAO.actualizarEstadoAndroid(getContext(),3,mantenimiento.getId_mantenimiento());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}