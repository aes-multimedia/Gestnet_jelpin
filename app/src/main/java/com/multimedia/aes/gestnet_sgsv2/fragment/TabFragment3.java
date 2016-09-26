package com.multimedia.aes.gestnet_sgsv2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class TabFragment3  extends Fragment implements View.OnClickListener {

    private View vista;
    private CheckBox cbLimpQuemCal, cbRevVasoExpan,cbRegulApara,cbCompEstanCierrQuemCalen,
            cbRevCaldCont,cbVeriHidraCalef,cbEstanConexApar,cbEstanCondEvacIRG,cbComprNivAgua,
            cbTipoCondEvac,cbReviEstadoAislaTerm,cbAnalProduComb,cbCaudACSCalcPotUtil,cbReviSistContro;
    private Mantenimiento mantenimiento = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_3, container, false);
        cbLimpQuemCal = (CheckBox) vista.findViewById(R.id.cbLimpQuemCal);
        cbRevVasoExpan = (CheckBox) vista.findViewById(R.id.cbRevVasoExpan);
        cbRegulApara = (CheckBox) vista.findViewById(R.id.cbRegulApara);
        cbCompEstanCierrQuemCalen = (CheckBox) vista.findViewById(R.id.cbCompEstanCierrQuemCalen);
        cbRevCaldCont = (CheckBox) vista.findViewById(R.id.cbRevCaldCont);
        cbVeriHidraCalef = (CheckBox) vista.findViewById(R.id.cbVeriHidraCalef);
        cbEstanConexApar = (CheckBox) vista.findViewById(R.id.cbEstanConexApar);
        cbEstanCondEvacIRG = (CheckBox) vista.findViewById(R.id.cbEstanCondEvacIRG);
        cbComprNivAgua = (CheckBox) vista.findViewById(R.id.cbComprNivAgua);
        cbTipoCondEvac = (CheckBox) vista.findViewById(R.id.cbTipoCondEvac);
        cbReviEstadoAislaTerm = (CheckBox) vista.findViewById(R.id.cbReviEstadoAislaTerm);
        cbAnalProduComb = (CheckBox) vista.findViewById(R.id.cbAnalProduComb);
        cbCaudACSCalcPotUtil = (CheckBox) vista.findViewById(R.id.cbCaudACSCalcPotUtil);
        cbReviSistContro = (CheckBox) vista.findViewById(R.id.cbReviSistContro);
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getContext(),id);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int estado = Integer.parseInt(mantenimiento.getEstado_android());
        if (estado==2) {
            cbLimpQuemCal.setEnabled(false);
            cbRevVasoExpan.setEnabled(false);
            cbRegulApara.setEnabled(false);
            cbCompEstanCierrQuemCalen.setEnabled(false);
            cbRevCaldCont.setEnabled(false);
            cbVeriHidraCalef.setEnabled(false);
            cbEstanConexApar.setEnabled(false);
            cbEstanCondEvacIRG.setEnabled(false);
            cbComprNivAgua.setEnabled(false);
            cbTipoCondEvac.setEnabled(false);
            cbReviEstadoAislaTerm.setEnabled(false);
            cbAnalProduComb.setEnabled(false);
            cbCaudACSCalcPotUtil.setEnabled(false);
            cbReviSistContro.setEnabled(false);
        }else if(estado==2){
            cbLimpQuemCal.setEnabled(false);
            cbRevVasoExpan.setEnabled(false);
            cbRegulApara.setEnabled(false);
            cbCompEstanCierrQuemCalen.setEnabled(false);
            cbRevCaldCont.setEnabled(false);
            cbVeriHidraCalef.setEnabled(false);
            cbEstanConexApar.setEnabled(false);
            cbEstanCondEvacIRG.setEnabled(false);
            cbComprNivAgua.setEnabled(false);
            cbTipoCondEvac.setEnabled(false);
            cbReviEstadoAislaTerm.setEnabled(false);
            cbAnalProduComb.setEnabled(false);
            cbCaudACSCalcPotUtil.setEnabled(false);
            cbReviSistContro.setEnabled(false);
        }

        return vista;
    }

    @Override
    public void onClick(View view) {

    }
    public MantenimientoTerminado guardarDatos(MantenimientoTerminado mantenimientoTerminado){
        if(cbLimpQuemCal.isChecked()){
            mantenimientoTerminado.setLimpieza_quemadores_caldera(1);
        }else{
            mantenimientoTerminado.setLimpieza_quemadores_caldera(0);
        }
        if(cbRevVasoExpan.isChecked()){
            mantenimientoTerminado.setRevision_vaso_expansion(1);
        }else{
            mantenimientoTerminado.setRevision_vaso_expansion(0);
        }
        if(cbRegulApara.isChecked()){
            mantenimientoTerminado.setRegulacion_aparatos(1);
        }else{
            mantenimientoTerminado.setRegulacion_aparatos(0);
        }
        if(cbCompEstanCierrQuemCalen.isChecked()){
            mantenimientoTerminado.setComprobar_estanqueidad_cierre_quemadores_caldera(1);
        }else{
            mantenimientoTerminado.setComprobar_estanqueidad_cierre_quemadores_caldera(0);
        }
        if(cbRevCaldCont.isChecked()){
            mantenimientoTerminado.setRevision_calderas_contadores(1);
        }else{
            mantenimientoTerminado.setRevision_calderas_contadores(0);
        }
        if(cbVeriHidraCalef.isChecked()){
            mantenimientoTerminado.setVerificacion_circuito_hidraulico_calefaccion(1);
        }else{
            mantenimientoTerminado.setVerificacion_circuito_hidraulico_calefaccion(0);
        }
        if(cbEstanConexApar.isChecked()){
            mantenimientoTerminado.setEstanqueidad_conexion_aparatos(1);
        }else{
            mantenimientoTerminado.setEstanqueidad_conexion_aparatos(0);
        }
        if(cbEstanCondEvacIRG.isChecked()){
            mantenimientoTerminado.setEstanqueidad_conducto_evacuacion_irg(1);
        }else{
            mantenimientoTerminado.setEstanqueidad_conducto_evacuacion_irg(0);
        }
        if(cbComprNivAgua.isChecked()){
            mantenimientoTerminado.setComprobacion_niveles_agua(1);
        }else{
            mantenimientoTerminado.setComprobacion_niveles_agua(0);
        }
        if(cbTipoCondEvac.isChecked()){
            mantenimientoTerminado.setTipo_conducto_evacuacion(1);
        }else{
            mantenimientoTerminado.setTipo_conducto_evacuacion(0);
        }
        if(cbReviEstadoAislaTerm.isChecked()){
            mantenimientoTerminado.setRevision_estado_aislamiento_termico(1);
        }else{
            mantenimientoTerminado.setRevision_estado_aislamiento_termico(0);
        }
        if(cbAnalProduComb.isChecked()){
            mantenimientoTerminado.setAnalisis_productos_combustion(1);
        }else{
            mantenimientoTerminado.setAnalisis_productos_combustion(0);
        }
        if(cbCaudACSCalcPotUtil.isChecked()){
            mantenimientoTerminado.setCaudal_acs_calculo_potencia(1);
        }else{
            mantenimientoTerminado.setCaudal_acs_calculo_potencia(0);
        }
        if(cbReviSistContro.isChecked()){
            mantenimientoTerminado.setRevision_sistema_control(1);
        }else{
            mantenimientoTerminado.setRevision_sistema_control(0);
        }
        return mantenimientoTerminado;
    }
}
