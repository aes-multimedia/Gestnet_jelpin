package com.multimedia.aes.gestnet_sgsv2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class TabFragment1 extends Fragment implements View.OnClickListener {
    private View vista;
    private TextView txtNumOrdenIberdrola,txtTipoIntervencion,txtVenta,txtTipoVisita,
            txtTipoMantenimiento,txtContadorAverias,txtContrato,txtNumParte,txtMaquina,txtFechaLlamada,
            txtFranjaHoraria,txtTipoUrgencia;
    private EditText etObservaciones,etTelefono1,etTelefono2,etTelefono3,etTelefono4;
    private Button btnIniciarParte,btnConfirmarObsTel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_1, container, false);
        Mantenimiento mantenimiento = null;
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getContext(),id);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtNumOrdenIberdrola = (TextView)vista.findViewById(R.id.txtNumOrdenIberdrola);
        txtTipoIntervencion = (TextView)vista.findViewById(R.id.txtTipoIntervencion);
        txtVenta = (TextView)vista.findViewById(R.id.txtVenta);
        txtTipoVisita = (TextView)vista.findViewById(R.id.txtTipoVisita);
        txtTipoMantenimiento = (TextView)vista.findViewById(R.id.txtTipoMantenimiento);
        txtContadorAverias = (TextView)vista.findViewById(R.id.txtContadorAverias);
        txtContrato = (TextView)vista.findViewById(R.id.txtContrato);
        txtNumParte = (TextView)vista.findViewById(R.id.txtNumParte);
        txtMaquina = (TextView)vista.findViewById(R.id.txtMaquina);
        txtFechaLlamada = (TextView)vista.findViewById(R.id.txtFechaLlamada);
        txtFranjaHoraria = (TextView)vista.findViewById(R.id.txtFranjaHoraria);
        txtTipoUrgencia = (TextView)vista.findViewById(R.id.txtTipoUrgencia);

        etObservaciones = (EditText)vista.findViewById(R.id.etObservaciones);
        etTelefono1 = (EditText)vista.findViewById(R.id.etTelefono1);
        etTelefono2 = (EditText)vista.findViewById(R.id.etTelefono2);
        etTelefono3 = (EditText)vista.findViewById(R.id.etTelefono3);
        etTelefono4 = (EditText)vista.findViewById(R.id.etTelefono4);

        btnIniciarParte = (Button)vista.findViewById(R.id.btnIniciarParte);
        btnConfirmarObsTel = (Button)vista.findViewById(R.id.btnConfirmarObsTel);

        btnIniciarParte.setOnClickListener(this);
        btnConfirmarObsTel.setOnClickListener(this);

        txtNumOrdenIberdrola.setText(mantenimiento.getNum_orden_endesa());
        if (mantenimiento.getFk_tipo()==1){
            txtTipoIntervencion.setText("Mantenimiento");
        }else if (mantenimiento.getFk_tipo()==2){
            txtTipoIntervencion.setText("Averia");
        }else if (mantenimiento.getFk_tipo()==3){
            txtTipoIntervencion.setText("Puesta en marcha");
        }else if (mantenimiento.getFk_tipo()==4){
            txtTipoIntervencion.setText("Instalación");
        }else if (mantenimiento.getFk_tipo()==5){
            txtTipoIntervencion.setText("Visita");
        }else if (mantenimiento.getFk_tipo()==6){
            txtTipoIntervencion.setText("Revision");
        }else if (mantenimiento.getFk_tipo()==7){
            txtTipoIntervencion.setText("Presupuesto");
        }

        if (mantenimiento.getPagado_endesa().equals("0")){
            txtVenta.setText("no");
        }else{
            txtVenta.setText("si");
        }
        if (mantenimiento.getFk_categoria_visita()==1){
            txtTipoVisita.setText("Visita reducida");
        }else{
            txtTipoVisita.setText("Visita RITE");
        }
        if (mantenimiento.getFk_efv()==1){
            txtTipoMantenimiento.setText("Servicio Manto Gas Fraccionado");
        }else if (mantenimiento.getFk_efv()==2){
            txtTipoMantenimiento.setText("Servicio de Mantenimiento Gas Independiente");
        }else if (mantenimiento.getFk_efv()==3){
            txtTipoMantenimiento.setText("Servicio de Mantenimiento Gas Calefacción Independiente");
        }else if (mantenimiento.getFk_efv()==4){
            txtTipoMantenimiento.setText("Servicio de Mantenimiento Gas Calefacción Fraccionado");
        }else if (mantenimiento.getFk_efv()==5){
            txtTipoMantenimiento.setText("Servicio de Mantenimiento Gas Ampliado Independiente");
        }else if (mantenimiento.getFk_efv()==6){
            txtTipoMantenimiento.setText("Servicio de Mantenimiento Gas Ampliado");
        }
        txtContadorAverias.setText(mantenimiento.getContador_averias());
        txtContrato.setText(mantenimiento.getContrato_endesa());
        txtNumParte.setText(mantenimiento.getNum_parte());

        txtMaquina.setText("APARICI");
        txtFechaLlamada.setText(mantenimiento.getFecha_aviso());

        if (mantenimiento.getFranja_horaria().equals("0")){
            txtFranjaHoraria.setText("Todo el dia");
        }else if (mantenimiento.getFranja_horaria().equals("1")){
            txtFranjaHoraria.setText("Mañana");
        }else {
            txtFranjaHoraria.setText("Tarde");
        }

        if (mantenimiento.getFk_tipo_urgencia()==1){
            txtTipoUrgencia.setText("MUY URGENTE");
        }else if (mantenimiento.getFk_tipo_urgencia()==2){
            txtTipoUrgencia.setText("URGENTE");
        }else if (mantenimiento.getFk_tipo_urgencia()==3){
            txtTipoUrgencia.setText("BONIFICABLE");
        }else if (mantenimiento.getFk_tipo_urgencia()==4){
            txtTipoUrgencia.setText("NORMAL");
        }else if (mantenimiento.getFk_tipo_urgencia()==5){
            txtTipoUrgencia.setText("PENALIZABLE");
        }


        return vista;
    }

    @Override
    public void onClick(View view) {

    }
}