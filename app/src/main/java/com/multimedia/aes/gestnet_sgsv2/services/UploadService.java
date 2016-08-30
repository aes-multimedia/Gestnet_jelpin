package com.multimedia.aes.gestnet_sgsv2.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.constants.Constantes;
import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.hilos.HiloSubirImagenes;
import com.multimedia.aes.gestnet_sgsv2.hilos.HiloSubirMantenimientoTerminado;

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

public class UploadService extends IntentService {
    private static final String TAG = UploadService.class.getSimpleName();
    private String host = "192.168.0.228";
    private Tecnico tecnico = null;
    private List<EquipamientoCaldera> equipamientoCalderas = null;
    private String mensaje = "";
    private MantenimientoTerminado mantenimientoTerminado = null;
    private Mantenimiento mantenimiento = null;

    public UploadService() {
        super("UploadService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (Constantes.ACTION_RUN_ISERVICE.equals(action)) {
                handleActionRun();
            }
        }
    }


    private void handleActionRun() {
        try {
            List<MantenimientoTerminado> list = MantenimientoTerminadoDAO.buscarTodosLosMantenimientoTerminados(getBaseContext());
            if (MantenimientoTerminadoDAO.buscarTodosLosMantenimientoTerminados(getBaseContext())!=null){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_sys_download_done)
                        .setContentTitle("Cargando partes")
                        .setContentText("Procesando...");
                //List<MantenimientoTerminado> list = MantenimientoTerminadoDAO.buscarTodosLosMantenimientoTerminados(getBaseContext());
                for (int i = 0; i < list.size() ; i++) {
                    builder.setProgress(list.size(), i, false);
                    startForeground(1, builder.build());
                    Intent localIntent = new Intent(Constantes.ACTION_RUN_ISERVICE)
                            .putExtra(Constantes.EXTRA_PROGRESS, i);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
                    if (!list.get(i).isEnviado()){
                        String mensaje = subir(rellenarJson(list.get(i).getId_mantenimiento_terminado()));
                        MantenimientoTerminadoDAO.actualizarEnviado(getBaseContext(),true,list.get(i).getId_mantenimiento_terminado());
                        Log.d("-----AAAASSSSSAAAA-----", mensaje);
                    }
                    //new HiloSubirImagenes(getActivity()).execute();
                    Thread.sleep(3000);
                }
                // Quitar de primer plano
                stopForeground(true);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getBaseContext(), "hilo destruido", Toast.LENGTH_SHORT).show();
        // Emisión para avisar que se terminó el servicio
        Intent localIntent = new Intent(Constantes.ACTION_PROGRESS_EXIT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
    private String subir(JSONObject msg) throws JSONException, IOException {
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
    public JSONObject rellenarJson(int id) throws JSONException {
        try {
            mantenimientoTerminado = MantenimientoTerminadoDAO.buscarMantenimientoTerminadoPorId(getBaseContext(),id);
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getBaseContext(),mantenimientoTerminado.getFk_parte());
            tecnico = TecnicoDAO.buscarTodosLosTecnicos(getBaseContext()).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject msg = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONArray jsonObject4 = new JSONArray();
        jsonObject1.put("observaciones_visita", "\"\'"+mantenimiento.getObservaciones_usuario()+"\'\"");
        jsonObject1.put("comprobante","0");
        jsonObject1.put("facturadoProveedor","0");
        jsonObject1.put("codigoBarras", "\"\'"+mantenimientoTerminado.getCodigo_barras()+"\'\"");
        jsonObject1.put("fkTipoVisita", "\"\'"+mantenimientoTerminado.getFk_tipo_visita()+"\'\"");
        jsonObject1.put("fkSubTipoVisita", "\"\'"+mantenimientoTerminado.getFk_subtipo_visita()+"\'\"");
        jsonObject1.put("observacionesTecnico", "\"\'"+mantenimiento.getObservaciones()+"\'\"");
        jsonObject1.put("empresa", "\"\'"+mantenimiento.getFk_empresa_usuario()+"\'\"");
        jsonObject1.put("contadorInterno", "\"\'"+mantenimientoTerminado.getContador_interno()+"\'\"");
        jsonObject1.put("codigoInterno", "\"\'"+mantenimientoTerminado.getCodigo_interno()+"\'\"");
        jsonObject1.put("reparacion", "\"\'"+mantenimientoTerminado.getReparacion()+"\'\"");
        jsonObject1.put("fkTipoReparacion", "\"\'"+mantenimientoTerminado.getFk_tipo_reparacion()+"\'\"");
        jsonObject1.put("fechaReparacion", "\"\'"+mantenimientoTerminado.getFecha_reparacion()+"\'\"");
        jsonObject1.put("fkTiempoManoObra", "\"\'"+mantenimientoTerminado.getFk_tiempo_mano_obra()+"\'\"");
        jsonObject1.put("costeMateriales", "\"\'"+mantenimientoTerminado.getCoste_materiales()+"\'\"");
        jsonObject1.put("costeManoDeObraAdicional", "\"\'"+mantenimientoTerminado.getCoste_mano_obra_adicional()+"\'\"");
        jsonObject1.put("codigoBarrasReparacion", "\"\'"+mantenimientoTerminado.getCodigo_barras_reparacion()+"\'\"");
        jsonObject1.put("limpiezaQuemadorCaldera", "\"\'"+mantenimientoTerminado.getLimpieza_quemadores_caldera()+"\'\"");
        jsonObject1.put("revisionVasoExpansion", "\"\'"+mantenimientoTerminado.getRevision_vaso_expansion()+"\'\"");
        jsonObject1.put("regulacionAparatos", "\"\'"+mantenimientoTerminado.getRegulacion_aparatos()+"\'\"");
        jsonObject1.put("compEstanqCierreQuemadoresCalderas", "\"\'"+mantenimientoTerminado.getComprobar_estanqueidad_cierre_quemadores_caldera()+"\'\"");
        jsonObject1.put("revGeneralCalderas", "\"\'"+mantenimientoTerminado.getRevision_calderas_contadores()+"\'\"");
        jsonObject1.put("verCircuitoHidCalefaccion", "\"\'"+mantenimientoTerminado.getVerificacion_circuito_hidraulico_calefaccion()+"\'\"");
        jsonObject1.put("estConexionAparatos", "\"\'"+mantenimientoTerminado.getEstanqueidad_conexion_aparatos()+"\'\"");
        jsonObject1.put("estConductoEvacuacionIRG", "\"\'"+mantenimientoTerminado.getEstanqueidad_conducto_evacuacion_irg()+"\'\"");
        jsonObject1.put("compNivelesAgua", "\"\'"+mantenimientoTerminado.getComprobacion_niveles_agua()+"\'\"");
        jsonObject1.put("tipoConductoEvacuacion", "\"\'"+mantenimientoTerminado.getTipo_conducto_evacuacion()+"\'\"");
        jsonObject1.put("revEstadoAislamientoTermico", "\"\'"+mantenimientoTerminado.getRevision_estado_aislamiento_termico()+"\'\"");
        jsonObject1.put("analisisProdCombustion", "\"\'"+mantenimientoTerminado.getAnalisis_productos_combustion()+"\'\"");
        jsonObject1.put("caudalACS", "\"\'"+mantenimientoTerminado.getCaudal_acs()+"\'\"");
        jsonObject1.put("revSistemaControl", "\"\'"+mantenimientoTerminado.getRevision_sistema_control()+"\'\"");

        jsonObject2.put("fkEstadoVisita","\"\'"+mantenimientoTerminado.getFk_estado_visita()+"\'\"");
        jsonObject2.put("fechaVisita","\"\'"+mantenimiento.getFecha_visita()+"\'\"");
        jsonObject2.put("cartaEnviada","0");
        jsonObject2.put("fechaEnvioCarta","0000-00-00");

        jsonObject3.put("fkTipoCaldera","\"\'"+mantenimientoTerminado.getFk_tipo_maquina()+"\'\"");
        jsonObject3.put("id_maquina","\"\'"+mantenimiento.getFk_maquina()+"\'\"");
        jsonObject3.put("fkMarca","\"\'"+mantenimientoTerminado.getFk_marca_maquina()+"\'\"");
        jsonObject3.put("potencia","\"\'"+mantenimientoTerminado.getFk_potencia_maquina()+"\'\"");
        jsonObject3.put("fkUso","\"\'"+mantenimientoTerminado.getFk_uso_maquina()+"\'\"");
        jsonObject3.put("puestaEnMarcha","\"\'"+mantenimientoTerminado.getPuesta_marcha_maquina()+"\'\"");
        jsonObject3.put("codigo","\"\'"+mantenimientoTerminado.getCodigo_maquina()+"\'\"");
        jsonObject3.put("c0ppm","\"\'"+mantenimientoTerminado.getC0_maquina()+"\'\"");
        jsonObject3.put("tempMaxACS","\"\'"+mantenimientoTerminado.getTemperatura_max_acs()+"\'\"");
        jsonObject3.put("caudalACS","\"\'"+mantenimientoTerminado.getCaudal_acs()+"\'\"");
        jsonObject3.put("potenciaUtil","\"\'"+mantenimientoTerminado.getPotencia_util()+"\'\"");
        jsonObject3.put("tempGasCombustion","\"\'"+mantenimientoTerminado.getTemperatura_gases_combustion()+"\'\"");
        jsonObject3.put("tempAmbLocal","\"\'"+mantenimientoTerminado.getTemperatura_ambiente_local()+"\'\"");
        jsonObject3.put("tempAguaGeneradorCalorEntrada","\"\'"+mantenimientoTerminado.getTemperatura_agua_generador_calor_entrada()+"\'\"");
        jsonObject3.put("tempAguaGeneradorCalorSalida","\"\'"+mantenimientoTerminado.getTemperatura_agua_generador_calor_salida()+"\'\"");
        try {
            equipamientoCalderas = EquipamientoCalderaDAO.buscarEquipamientoCalderaPorIdMaquina(getBaseContext(), mantenimiento.getFk_maquina());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (equipamientoCalderas!=null) {
            for (int i = 0; i < equipamientoCalderas.size(); i++) {
                JSONObject jsonObject5 = new JSONObject();
                jsonObject5.put("potencia", "\"\'"+equipamientoCalderas.get(i).getPotencia_fuegos()+"\'\"");
                jsonObject5.put("fkMaquina", "\"\'"+mantenimiento.getFk_maquina()+"\'\"");
                jsonObject5.put("idTipoEquipamientos", "\"\'"+equipamientoCalderas.get(i).getFk_tipo_equipamiento()+"\'\"");
                jsonObject4.put(jsonObject5);
            }
        }
        msg.put("datos_adicionales",jsonObject1);
        msg.put("sat_partes",jsonObject2);
        msg.put("usuarios_maquinas",jsonObject3);
        msg.put("usuarios_maquinas_equipamientos",jsonObject4);
        return msg;
    }
}