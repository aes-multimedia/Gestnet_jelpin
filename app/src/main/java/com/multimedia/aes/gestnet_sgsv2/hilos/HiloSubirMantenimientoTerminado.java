package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HiloSubirMantenimientoTerminado extends AsyncTask<Void,Void,Void>{
    private String host = "192.168.0.228";
    private MantenimientoTerminado mantenimientoTerminado = null;
    private Mantenimiento mantenimiento = null;
    private Tecnico tecnico = null;
    private List<EquipamientoCaldera> equipamientoCalderas = null;
    private JSONObject msg = new JSONObject();
    private String mensaje = "";
    private Context context;

    public HiloSubirMantenimientoTerminado(Context context,int idParte) {
        this.context=context;
        try {
            mantenimientoTerminado = MantenimientoTerminadoDAO.buscarMantenimientoTerminadoPorId(context,idParte);
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(context,mantenimientoTerminado.getFk_parte());
            tecnico = TecnicoDAO.buscarTodosLosTecnicos(context).get(0);
            equipamientoCalderas = EquipamientoCalderaDAO.buscarEquipamientoCalderaPorIdMaquina(context, mantenimiento.getFk_maquina());
            rellenarJson();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = subir();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
    private String subir() throws JSONException, IOException {
        URL urlws = new URL("http://"+host+"/api-sgs/v1/mantenimientos/carga_datos");
        HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        uc.addRequestProperty("fk_parte", String.valueOf(mantenimientoTerminado.getFk_parte()));
        uc.addRequestProperty("id", String.valueOf(tecnico.getId_tecnico()));
        uc.addRequestProperty("apikey", String.valueOf(tecnico.getApikey()));
        uc.setRequestMethod("POST");
        uc.connect();
        OutputStream os = uc.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(msg.toString());
        osw.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        String contenido = "";
        while ((inputLine = in.readLine()) != null) {
            contenido += inputLine + "\n";
        }
        in.close();
        osw.close();
        return contenido;
    }
    public void rellenarJson() throws JSONException {
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONArray jsonObject4 = new JSONArray();
        jsonObject1.put("observaciones_visita", mantenimiento.getObservaciones_usuario());
        jsonObject1.put("comprobante","");
        jsonObject1.put("facturadoProveedor","");
        jsonObject1.put("codigoBarras", mantenimientoTerminado.getCodigo_barras());
        jsonObject1.put("fkTipoVisita", mantenimientoTerminado.getFk_tipo_visita());
        jsonObject1.put("observacionesTecnico", mantenimiento.getObservaciones());
        jsonObject1.put("empresa", mantenimiento.getFk_empresa_usuario());
        jsonObject1.put("contadorInterno", mantenimientoTerminado.getContador_interno());
        jsonObject1.put("codigoInterno", mantenimientoTerminado.getCodigo_interno());
        jsonObject1.put("reparacion", mantenimientoTerminado.getReparacion());
        jsonObject1.put("fkTipoReparacion", mantenimientoTerminado.getFk_tipo_reparacion());
        jsonObject1.put("fechaReparacion", mantenimientoTerminado.getFecha_reparacion());
        jsonObject1.put("fkTiempoManoObra", mantenimientoTerminado.getFk_tiempo_mano_obra());
        jsonObject1.put("costeMateriales", mantenimientoTerminado.getCoste_materiales());
        jsonObject1.put("costeManoDeObraAdicional", mantenimientoTerminado.getCoste_mano_obra_adicional());
        jsonObject1.put("codigoBarrasReparacion", mantenimientoTerminado.getCodigo_barras_reparacion());
        jsonObject1.put("limpiezaQuemadorCaldera", mantenimientoTerminado.getLimpieza_quemadores_caldera());
        jsonObject1.put("revisionVasoExpansion", mantenimientoTerminado.getRevision_vaso_expansion());
        jsonObject1.put("regulacionAparatos", mantenimientoTerminado.getRegulacion_aparatos());
        jsonObject1.put("compEstanqCierreQuemadoresCalderas", mantenimientoTerminado.getComprobar_estanqueidad_cierre_quemadores_caldera());
        jsonObject1.put("revGeneralCalderas", mantenimientoTerminado.getRevision_calderas_contadores());
        jsonObject1.put("verCircuitoHidCalefaccion", mantenimientoTerminado.getVerificacion_circuito_hidraulico_calefaccion());
        jsonObject1.put("estConexionAparatos", mantenimientoTerminado.getEstanqueidad_conexion_aparatos());
        jsonObject1.put("estConductoEvacuacion", mantenimientoTerminado.getEstanqueidad_conducto_evacuacion_irg());
        jsonObject1.put("compNivelesAgua", mantenimientoTerminado.getComprobacion_niveles_agua());
        jsonObject1.put("tipoConductoEvacuacion", mantenimientoTerminado.getTipo_conducto_evacuacion());
        jsonObject1.put("revEstadoAislamientoTermico", mantenimientoTerminado.getRevision_estado_aislamiento_termico());
        jsonObject1.put("analisisProdCombustion", mantenimientoTerminado.getAnalisis_productos_combustion());
        jsonObject1.put("caudalACS", mantenimientoTerminado.getCaudal_acs());
        jsonObject1.put("revSistemaControl", mantenimientoTerminado.getRevision_sistema_control());

        jsonObject2.put("fkEstadoVisita",mantenimientoTerminado.getFk_estado_visita());
        jsonObject2.put("fechaVisita",mantenimiento.getFecha_visita());
        jsonObject2.put("cartaEnviada","");
        jsonObject2.put("fechaEnvioCarta","");

        jsonObject3.put("fkTipoCaldera",mantenimientoTerminado.getFk_tipo_maquina());
        jsonObject3.put("fkMaquina",mantenimiento.getFk_maquina());
        jsonObject3.put("fkMarca",mantenimientoTerminado.getFk_marca_maquina());
        jsonObject3.put("potencia",mantenimientoTerminado.getFk_potencia_maquina());
        jsonObject3.put("fkUso",mantenimientoTerminado.getFk_uso_maquina());
        jsonObject3.put("puestaEnMarcha",mantenimientoTerminado.getPuesta_marcha_maquina());
        jsonObject3.put("codigo",mantenimientoTerminado.getCodigo_maquina());
        jsonObject3.put("c0ppm",mantenimientoTerminado.getC0_maquina());
        jsonObject3.put("tempMaxACS",mantenimientoTerminado.getTemperatura_max_acs());
        jsonObject3.put("caudalACS",mantenimientoTerminado.getCaudal_acs());
        jsonObject3.put("potenciaUtil",mantenimientoTerminado.getPotencia_util());
        jsonObject3.put("tempGasCombustion",mantenimientoTerminado.getTemperatura_gases_combustion());
        jsonObject3.put("tempAmbLocal",mantenimientoTerminado.getTemperatura_ambiente_local());
        jsonObject3.put("tempAguaGeneradorCalorEntrada",mantenimientoTerminado.getTemperatura_agua_generador_calor_entrada());
        jsonObject3.put("tempAguaGeneradorCalorSalida",mantenimientoTerminado.getTemperatura_agua_generador_calor_salida());
        for (int i = 0; i < equipamientoCalderas.size(); i++) {
            JSONObject jsonObject5 = new JSONObject();
            jsonObject5.put("potencia",equipamientoCalderas.get(i).getPotencia_fuegos());
            jsonObject5.put("fkMaquina",mantenimiento.getFk_maquina());
            jsonObject5.put("idTipoEquipamientos",equipamientoCalderas.get(i).getFk_tipo_equipamiento());
            jsonObject4.put(jsonObject5);
        }
        msg.put("datos_adicionales",jsonObject1);
        msg.put("sat_partes",jsonObject2);
        msg.put("usuarios_maquinas",jsonObject3);
        msg.put("usuarios_maquinas_equipamientos",jsonObject4);
    }
}
