package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

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

public class HiloSubirMantenimientoTerminado extends AsyncTask<Void,Void,Void>{
    private String host = "192.168.0.228";
    private MantenimientoTerminado mantenimientoTerminado = null;
    private Mantenimiento mantenimiento = null;
    private Tecnico tecnico = null;
    JSONObject msg = new JSONObject();

    public HiloSubirMantenimientoTerminado(Context context,int idParte) {
        try {
            mantenimientoTerminado = MantenimientoTerminadoDAO.buscarMantenimientoTerminadoPorId(context,idParte);
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(context,mantenimientoTerminado.getFk_parte());
            tecnico = TecnicoDAO.buscarTodosLosTecnicos(context).get(0);
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
            subir();
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
    }
    private String subir() throws JSONException, IOException {
        URL urlws = new URL("http://"+host+"/api-sgs/v1/mantenimientos/carga_imagen");
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
        JSONObject jsonObject4 = new JSONObject();
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
        jsonObject1.put("costeManoDeObraAdicional", "");
        jsonObject1.put("codigoBarrasReparacion", mantenimientoTerminado.getCodigo_barras_reparacion());
        jsonObject1.put("limpiezaQuemadorCaldera", "");
        jsonObject1.put("revisionVasoExpansion", "");
        jsonObject1.put("regulacionAparatos", "");
        jsonObject1.put("compEstanqCierreQuemadoresCalderas", "");
        jsonObject1.put("revGeneralCalderas", "");
        jsonObject1.put("verCircuitoHidCalderas", "");
        jsonObject1.put("verCircuitoHidCalefaccion", "");
        jsonObject1.put("estConexionAparatos", "");
        jsonObject1.put("estConductoEvacuacion", "");
        jsonObject1.put("revEstadoAislamientoTermico", "");
        jsonObject1.put("AnalisisProdCombustion", "");
        jsonObject1.put("caudalACS", "");
        jsonObject1.put("revSistemaControl", "");

        jsonObject2.put("fkEstadoVisita",mantenimientoTerminado.getFk_estado_visita());
        jsonObject2.put("fechaVisita",mantenimiento.getFecha_visita());
        jsonObject2.put("cartaEnviada","");
        jsonObject2.put("fechaEnvioCarta","");

        jsonObject3.put("$fkTipoCaldera",mantenimientoTerminado.getFk_tipo_maquina());
        jsonObject3.put("$fkMarca",mantenimientoTerminado.getFk_marca_maquina());
        jsonObject3.put("$potencia",mantenimientoTerminado.getFk_potencia_maquina());
        jsonObject3.put("$fkUso",mantenimientoTerminado.getFk_uso_maquina());
        jsonObject3.put("$puestaEnMarcha",mantenimientoTerminado.getPuesta_marcha_maquina());
        jsonObject3.put("$codigo","");
        jsonObject3.put("$c0ppm","");
        jsonObject3.put("$tempMaxACS","");
        jsonObject3.put("$caudalACS","");
        jsonObject3.put("$potenciaUtil","");
        jsonObject3.put("$tempGasCombustion","");
        jsonObject3.put("$tempAmbLocal","");
        jsonObject3.put("$tempAguaGeneradorCalorEntrada","");
        jsonObject3.put("$tempAguaGeneradorCalorSalida","");

        jsonObject4.put("$potencia","");
        jsonObject4.put("$idTipoEquipamientos","");

        msg.put("datos_adicionales",jsonObject1);
        msg.put("sat_partes",jsonObject2);
        msg.put("usuarios_maquinas",jsonObject3);
        msg.put("usuarios_maquinas_equipamientos",jsonObject4);
    }
}
