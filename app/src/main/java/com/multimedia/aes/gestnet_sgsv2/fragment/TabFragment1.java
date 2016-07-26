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
import com.multimedia.aes.gestnet_sgsv2.nucleo.Index;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TabFragment1 extends Fragment implements View.OnClickListener {
    private View vista;
    private TextView txtNumOrdenIberdrola,txtTipoIntervencion,txtVenta,txtTipoVisita,
            txtTipoMantenimiento,txtContadorAverias,txtContrato,txtFechaLlamada,txtTipoUrgencia,
            txtTipo,txtMarca,txtModelo,txtDireccion;
    private EditText etObservaciones,etTelefono1,etTelefono2,etTelefono3,etTelefono4,etTelefono5,etNombre;
    private Button btnIniciarParte,btnConfirmarObsTel;
    private Mantenimiento mantenimiento = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_1, container, false);
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
        txtFechaLlamada = (TextView)vista.findViewById(R.id.txtFechaLlamada);
        txtTipoUrgencia = (TextView)vista.findViewById(R.id.txtTipoUrgencia);
        txtTipo = (TextView)vista.findViewById(R.id.txtTipo);
        txtMarca = (TextView)vista.findViewById(R.id.txtMarca);
        txtModelo = (TextView)vista.findViewById(R.id.txtModelo);
        txtDireccion = (TextView)vista.findViewById(R.id.txtDireccion);


        etObservaciones = (EditText)vista.findViewById(R.id.etObservaciones);
        etTelefono1 = (EditText)vista.findViewById(R.id.etTelefono1);
        etTelefono2 = (EditText)vista.findViewById(R.id.etTelefono2);
        etTelefono3 = (EditText)vista.findViewById(R.id.etTelefono3);
        etTelefono4 = (EditText)vista.findViewById(R.id.etTelefono4);
        etTelefono5 = (EditText)vista.findViewById(R.id.etTelefono5);
        etNombre = (EditText)vista.findViewById(R.id.etNombre);

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
            txtVenta.setText("No");
        }else{
            txtVenta.setText("Si");
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

        String dateSample = mantenimiento.getFecha_aviso();

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

        txtFechaLlamada.setText(fecha);

        if (mantenimiento.getFk_tipo_urgencia()==1){
            txtTipoUrgencia.setText("MUY URGENTE");
        }else if (mantenimiento.getFk_tipo_urgencia()==2){
            txtTipoUrgencia.setText("URGENTE");
        }else if (mantenimiento.getFk_tipo_urgencia()==4){
            txtTipoUrgencia.setText("NORMAL");
        }else if (mantenimiento.getFk_tipo_urgencia()==5){
            txtTipoUrgencia.setText("PENALIZABLE");
        }else{
            txtTipoUrgencia.setText("BONIFICABLE");
        }
        if (mantenimiento.getTipo_maquina().equals("1")){
            txtTipo.setText("Estanca");
        }else if (mantenimiento.getTipo_maquina().equals("2")){
            txtTipo.setText("Atmosferica");
        }else if (mantenimiento.getTipo_maquina().equals("6")){
            txtTipo.setText("Condensacion");
        }else if (mantenimiento.getTipo_maquina().equals("7")){
            txtTipo.setText("Estilo Mixto");
        }else if (mantenimiento.getTipo_maquina().equals("8")){
            txtTipo.setText("Calentador Gas");
        }else{
            txtTipo.setText("Otras");
        }

        txtModelo.setText(mantenimiento.getModelo_maquina());
        txtMarca.setText(mantenimiento.getMarca_maquina());
        etNombre.setText(mantenimiento.getNombre_usuario());
        txtDireccion.setText(mantenimiento.getDireccion()+" \n "+mantenimiento.getCod_postal()+" \n "+mantenimiento.getProvincia()+" \n "+mantenimiento.getMunicipio());

        etObservaciones.setText(mantenimiento.getObservaciones_usuario());
        etTelefono1.setText(mantenimiento.getTelefono1_usuario());
        etTelefono2.setText(mantenimiento.getTelefono2_usuario());
        etTelefono3.setText(mantenimiento.getTelefono3_usuario());
        etTelefono4.setText(mantenimiento.getTelefono4_usuario());
        etTelefono5.setText(mantenimiento.getTelefono5_usuario());

        int estado = Integer.parseInt(mantenimiento.getEstado_android());
        if (estado==0){

        }else if (estado==1){
            btnIniciarParte.setText("PARTE INICIADO");
            btnIniciarParte.setClickable(false);
        }else if (estado==2){
            btnIniciarParte.setText("PARTE PENDIENTE");
            btnIniciarParte.setClickable(false);
        }else if (estado==3){
            btnIniciarParte.setText("PARTE CERRADO");
            btnIniciarParte.setClickable(false);
            btnConfirmarObsTel.setClickable(false);
            etObservaciones.setEnabled(false);
            etTelefono1.setEnabled(false);
            etTelefono2.setEnabled(false);
            etTelefono3.setEnabled(false);
            etTelefono4.setEnabled(false);
            etTelefono5.setEnabled(false);
            btnConfirmarObsTel.setVisibility(View.GONE);
        }
        return vista;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnIniciarParte){
            try {
                MantenimientoDAO.actualizarEstadoAndroid(getContext(),1,mantenimiento.getId_mantenimiento());
                ((Index)getContext()).activar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if (view.getId() == R.id.btnConfirmarObsTel){
            if (!mantenimiento.getNombre_usuario().equals(etNombre.getText())&&!etNombre.getText().toString().trim().equals("")){
                try {
                    MantenimientoDAO.actualizarNombreUsuario(getContext(),etNombre.getText().toString(),mantenimiento.getId_mantenimiento());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!mantenimiento.getTelefono1_usuario().equals(etTelefono1.getText())&&!etTelefono1.getText().toString().trim().equals("")){
                try {
                    MantenimientoDAO.actualizarTelefono1(getContext(),etTelefono1.getText().toString(),mantenimiento.getId_mantenimiento());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!mantenimiento.getTelefono2_usuario().equals(etTelefono2.getText())&&!etTelefono2.getText().toString().trim().equals("")){
                try {
                    MantenimientoDAO.actualizarTelefono2(getContext(),etTelefono2.getText().toString(),mantenimiento.getId_mantenimiento());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!mantenimiento.getTelefono3_usuario().equals(etTelefono3.getText())&&!etTelefono3.getText().toString().trim().equals("")){
                try {
                    MantenimientoDAO.actualizarTelefono3(getContext(),etTelefono3.getText().toString(),mantenimiento.getId_mantenimiento());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!mantenimiento.getTelefono4_usuario().equals(etTelefono4.getText())&&!etTelefono4.getText().toString().trim().equals("")){
                try {
                    MantenimientoDAO.actualizarTelefono4(getContext(),etTelefono4.getText().toString(),mantenimiento.getId_mantenimiento());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!mantenimiento.getTelefono5_usuario().equals(etTelefono5.getText())&&!etTelefono5.getText().toString().trim().equals("")){
                try {
                    MantenimientoDAO.actualizarTelefono5(getContext(),etTelefono5.getText().toString(),mantenimiento.getId_mantenimiento());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!mantenimiento.getObservaciones_usuario().equals(etObservaciones.getText())&&!etObservaciones.getText().toString().trim().equals("")){
                try {
                    MantenimientoDAO.actualizarObservacionesCliente(getContext(),etObservaciones.getText().toString(),mantenimiento.getId_mantenimiento());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(getContext(), "Actualizado", Toast.LENGTH_SHORT).show();
        }
    }


}