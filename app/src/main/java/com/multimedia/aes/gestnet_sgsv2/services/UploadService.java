package com.multimedia.aes.gestnet_sgsv2.services;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.constants.Constantes;
import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.ImagenesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Imagenes;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    private String ipInterna = "192.168.0.228";
    private String ipExterna = "80.58.161.135";
    private String puerto = "8085";
    private Tecnico tecnico = null;
    private List<EquipamientoCaldera> equipamientoCalderas = null;
    private MantenimientoTerminado mantenimientoTerminado = null;
    private Mantenimiento mantenimiento = null;
    private String path = "/data/data/com.multimedia.aes.gestnet_sgsv2/app_imageDir";
    private ArrayList<Imagenes> arraylistImagenes = new ArrayList<>();
    private List<Maquina> maquinas;

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
            if (MantenimientoTerminadoDAO.buscarTodosLosMantenimientoTerminados(getBaseContext())!=null){
                List<MantenimientoTerminado> list = MantenimientoTerminadoDAO.buscarTodosLosMantenimientoTerminados(getBaseContext());
                Log.d("-------TAMAÑO-------", String.valueOf(list.size()));
                for (int i = 0; i < list.size() ; i++) {
                    Intent localIntent = new Intent(Constantes.ACTION_RUN_ISERVICE)
                            .putExtra(Constantes.EXTRA_PROGRESS, i);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
                    String mensajemantenimiento = subirMantenimientos(rellenarJsonMantenimientos(list.get(i).getId_mantenimiento_terminado()));
                    Log.d("-MENSAJEMANTENIMIENTO-", mensajemantenimiento);
                    MantenimientoTerminadoDAO.actualizarEnviado(getBaseContext(),true,list.get(i).getId_mantenimiento_terminado());
                    if (!mensajemantenimiento.equals("1")){
                        //String mensajeCerrarIberdrola = subirCerrarIberdrola(rellenarJsonCerrarIberdrola(list.get(i).getId_mantenimiento_terminado()));
                        //String mensajeticket = subirTiket(rellenarJsonTiket());
                        //Log.d("-----MENSAJETICKET-----", mensajeticket);
                        if (ImagenesDAO.buscarImagenPorFk_parte(getBaseContext(),list.get(i).getFk_parte())!=null){
                            String mensajeImagen = subirImagen(rellenarJsonImagenes(list.get(i).getFk_parte()));
                            Log.d("-----MENSAJEIMAGEN-----", mensajeImagen);
                        }else{

                        }

                    }else{
                        Toast.makeText(getBaseContext(), "Fallo en carga de datos, compruebe su conexion", Toast.LENGTH_SHORT).show();
                    }

                    Thread.sleep(3000);
                }
                // Quitar de primer plano
                stopForeground(true);
            }else{
                Toast.makeText(getBaseContext(),"No hay datos para subir", Toast.LENGTH_SHORT).show();
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
        // Emisión para avisar que se terminó el servicio
        Intent localIntent = new Intent(Constantes.ACTION_PROGRESS_EXIT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
    private String subirMantenimientos(JSONObject msg) throws JSONException, IOException {
        Log.d("-----JSON-----", msg.toString());
        URL urlws = new URL("http://"+ ipExterna +":"+puerto+"/api-sgs/v1/mantenimientos/carga_datos");
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
    private String subirCerrarIberdrola(JSONObject msg) throws JSONException, IOException {
        Log.d("-----JSONCERRARIB-----", msg.toString());
        URL urlws = new URL("http://"+ ipExterna +":"+puerto+"/api-sgs/v1/mantenimientos/cerrar");
        HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        //uc.addRequestProperty("fk_parte", String.valueOf(mantenimientoTerminado.getFk_parte()));
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
    private String subirTiket(JSONObject msg) throws JSONException, IOException {
        Log.d("JSON",msg.toString());
        URL urlws = new URL("http://"+ ipExterna +":"+puerto+"/api-sgs/v1/mantenimientos/carga_imagen");
        HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        uc.addRequestProperty("fk_parte", String.valueOf(mantenimiento.getId_mantenimiento()));
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
    private String subirImagen(JSONObject msg)throws JSONException, IOException{
        URL urlws = new URL("http://"+ ipExterna +":"+puerto+"/api-sgs/v1/mantenimientos/foto");
        HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.addRequestProperty("fk_parte",String.valueOf(mantenimiento.getId_mantenimiento()));
        uc.addRequestProperty("apikey",tecnico.getApikey());
        uc.addRequestProperty("id",String.valueOf(tecnico.getId_tecnico()));
        uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
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
    private JSONObject rellenarJsonMantenimientos(int id) throws JSONException, SQLException {
        mantenimientoTerminado = MantenimientoTerminadoDAO.buscarMantenimientoTerminadoPorId(getBaseContext(),id);
        mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getBaseContext(),mantenimientoTerminado.getFk_parte());
        tecnico = TecnicoDAO.buscarTodosLosTecnicos(getBaseContext()).get(0);
        maquinas = MaquinaDAO.buscarMaquinaPorFkMantenimiento(getBaseContext(),mantenimiento.getId_mantenimiento());
        Maquina maquina = maquinas.get(0);
        JSONObject msg = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONArray jsonObject4 = new JSONArray();
        jsonObject1.put("observaciones_visita", mantenimiento.getObservaciones_usuario());
        jsonObject1.put("comprobante","0");
        jsonObject1.put("facturadoProveedor","0");
        jsonObject1.put("codigoBarras", mantenimientoTerminado.getCodigo_barras());
        jsonObject1.put("fkTipoVisita", mantenimientoTerminado.getFk_tipo_visita());
        jsonObject1.put("fkSubTipoVisita", mantenimientoTerminado.getFk_subtipo_visita());
        jsonObject1.put("observacionesTecnico", mantenimiento.getObservaciones());
        jsonObject1.put("empresa", mantenimiento.getFk_empresa_usuario());
        jsonObject1.put("contadorInterno", mantenimientoTerminado.getContador_interno());
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
        jsonObject1.put("estConductoEvacuacionIRG", mantenimientoTerminado.getEstanqueidad_conducto_evacuacion_irg());
        jsonObject1.put("compNivelesAgua", mantenimientoTerminado.getComprobacion_niveles_agua());
        jsonObject1.put("tipoConductoEvacuacion", mantenimientoTerminado.getTipo_conducto_evacuacion());
        jsonObject1.put("revEstadoAislamientoTermico", mantenimientoTerminado.getRevision_estado_aislamiento_termico());
        jsonObject1.put("analisisProdCombustion", mantenimientoTerminado.getAnalisis_productos_combustion());
        jsonObject1.put("caudalACS", maquina.getCaudal_acs());
        jsonObject1.put("revSistemaControl", mantenimientoTerminado.getRevision_sistema_control());
        String base64 = loadTicketFromStorage();
        jsonObject1.put("base64", base64);

        jsonObject2.put("fkEstadoVisita",mantenimientoTerminado.getFk_estado_visita());
        jsonObject2.put("fechaVisita",mantenimiento.getFecha_visita());
        jsonObject2.put("cartaEnviada","0");
        jsonObject2.put("fechaEnvioCarta","0000-00-00");

        jsonObject3.put("fkTipoCaldera",maquina.getFk_tipo_maquina());
        jsonObject3.put("id_maquina",mantenimiento.getFk_maquina());
        jsonObject3.put("fkMarca",maquina.getFk_marca_maquina());
        jsonObject3.put("potencia",maquina.getFk_potencia_maquina());
        jsonObject3.put("fkUso",maquina.getFk_uso_maquina());
        jsonObject3.put("puestaEnMarcha",maquina.getPuesta_marcha_maquina());
        jsonObject3.put("codigo",maquina.getCodigo_maquina());
        jsonObject3.put("c0ppm",maquina.getC0_maquina());
        jsonObject3.put("tempMaxACS",maquina.getTemperatura_max_acs());
        jsonObject3.put("caudalACS",maquina.getCaudal_acs());
        jsonObject3.put("potenciaUtil",maquina.getPotencia_util());
        jsonObject3.put("tempGasCombustion",maquina.getTemperatura_gases_combustion());
        jsonObject3.put("tempAmbLocal",maquina.getTemperatura_ambiente_local());
        jsonObject3.put("tempAguaGeneradorCalorEntrada",maquina.getTemperatura_agua_generador_calor_entrada());
        jsonObject3.put("tempAguaGeneradorCalorSalida",maquina.getTemperatura_agua_generador_calor_salida());
        try {
            equipamientoCalderas = EquipamientoCalderaDAO.buscarEquipamientoCalderaPorIdMantenimiento(getBaseContext(), mantenimiento.getId_mantenimiento());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (equipamientoCalderas!=null) {
            for (int i = 0; i < equipamientoCalderas.size(); i++) {
                JSONObject jsonObject5 = new JSONObject();
                jsonObject5.put("potencia", equipamientoCalderas.get(i).getPotencia_fuegos());
                jsonObject5.put("fk_maquina", mantenimiento.getFk_maquina());
                jsonObject5.put("idTipoEquipamientos", equipamientoCalderas.get(i).getFk_tipo_equipamiento());
                jsonObject4.put(jsonObject5);
            }
        }
        msg.put("datos_adicionales",jsonObject1);
        msg.put("sat_partes",jsonObject2);
        msg.put("usuarios_maquinas",jsonObject3);
        msg.put("usuarios_maquinas_equipamientos",jsonObject4);
        return msg;
    }
    private JSONObject rellenarJsonTiket() throws JSONException, IOException {
        JSONObject jsonObjectTicket = new JSONObject();
        JSONObject msg = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        String base64 = loadTicketFromStorage();
        String logo = loadLogoFromStorage();
        String firma = loadFirmaFromStorage();
        jsonObject.put("name","ticket");
        jsonObject.put("base64",base64);
        String fecha = mantenimientoTerminado.getFecha_ticket();
        String hora = mantenimientoTerminado.getHora_ticket();
        String nombre_cliente = mantenimiento.getNombre_usuario() + "\n";
        String dn = "02365984K";
        String num_contrato = "000111522";
        String serv = "Mantenimiento Gas";
        String dir = "Calle Ribadavia 11,2-A,"+"\n"+"Madrid,Madrid,20156";
        String emp = "IBERDROLA";
        String noti = "21/06/2016";
        String cif_emp = "02365474S";
        String num_emp_mant = "44556678";
        String tec = "Pedro Buenhombre Lopez";
        String num_insta = "659898741";
        String atend = "18/06/2016-14:00";
        String prev_repar = "26/06/2016-13:30";
        String repa = "24/06/2016-12:48";
        String num_solic = "6547952";
        String cod_ave = "3216565";
        String desc = "Una averia sin importancia";
        String despl = "5 horas:  24 euros";
        String piez = "Junta caldera  5 euros";
        String man_obra = "2 horas: 20 euros";
        String otr = "6 Km 31 euros"+"\n"+"2 Km 12 euros";
        String desc_preiva = "0%";
        String mat = "6 piezas: 29 euros";
        String pres_tot_siniva = "95 euros";
        String iv = "21%";
        String pres_tot_coniva = "102 euros";
        String otr_desc = "0%";
        String tot = "102 euros";
        String obs_tecnico = "La maquina es antigüa";
        String fec_recep = "22/06/2016-13:00";
        String fec_acep = "22/06/2016-13:00";
        String fec_conf = "22/06/2016-13:00";
        String obs_cliente = "muy majo el tecnico";
        jsonObjectTicket.put("fecha",fecha);
        jsonObjectTicket.put("hora",hora);
        jsonObjectTicket.put("nombre_cliente",nombre_cliente);
        jsonObjectTicket.put("dni_cliente",dn);
        jsonObjectTicket.put("numero_contrato",num_contrato);
        jsonObjectTicket.put("servicio",serv);
        jsonObjectTicket.put("direccion",dir);
        jsonObjectTicket.put("empresa",emp);
        jsonObjectTicket.put("cif_empresa",cif_emp);
        jsonObjectTicket.put("numero_empresa_mantenedora",num_emp_mant);
        jsonObjectTicket.put("tecnico",tec);
        jsonObjectTicket.put("numero_instalador",num_insta);
        jsonObjectTicket.put("notificada",noti);
        jsonObjectTicket.put("atendida",atend);
        jsonObjectTicket.put("prevista_reparacion",prev_repar);
        jsonObjectTicket.put("reparada",repa);
        jsonObjectTicket.put("numero_solicitud",num_solic);
        jsonObjectTicket.put("codigo_averia",cod_ave);
        jsonObjectTicket.put("descripcion_averia",desc);
        jsonObjectTicket.put("piezas",piez);
        jsonObjectTicket.put("mano_obra",man_obra);
        jsonObjectTicket.put("desplazamiento",despl);
        jsonObjectTicket.put("otros",otr);
        jsonObjectTicket.put("descuento_preiva",desc_preiva);
        jsonObjectTicket.put("materiales",mat);
        jsonObjectTicket.put("presupuesto_sin_iva",pres_tot_siniva);
        jsonObjectTicket.put("iva",iv);
        jsonObjectTicket.put("presupuesto_con_iva",pres_tot_coniva);
        jsonObjectTicket.put("otros_descuentos",otr_desc);
        jsonObjectTicket.put("total",tot);
        jsonObjectTicket.put("observaciones_tecnico",obs_tecnico);
        jsonObjectTicket.put("fecha_recepcion",fec_recep);
        jsonObjectTicket.put("fecha_aceptacion",fec_acep);
        jsonObjectTicket.put("fecha_conforme",fec_conf);
        jsonObjectTicket.put("observacion_cliente",obs_cliente);
        msg.put("images",jsonObject);
        msg.put("ticket",jsonObjectTicket);
        msg.put("logo",logo);
        msg.put("firma",firma);
        return msg;
    }
    private JSONObject rellenarJsonCerrarIberdrola(int id) throws JSONException, SQLException {
        mantenimientoTerminado = MantenimientoTerminadoDAO.buscarMantenimientoTerminadoPorId(getBaseContext(),id);
        mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getBaseContext(),mantenimientoTerminado.getFk_parte());
        tecnico = TecnicoDAO.buscarTodosLosTecnicos(getBaseContext()).get(0);
        maquinas = MaquinaDAO.buscarMaquinaPorFkMantenimiento(getBaseContext(),mantenimiento.getId_mantenimiento());
        equipamientoCalderas = EquipamientoCalderaDAO.buscarEquipamientoCalderaPorIdMantenimiento(getBaseContext(),mantenimiento.getId_mantenimiento());
        Maquina maquina = maquinas.get(0);
        JSONObject msg = new JSONObject();
        msg.put("codigoContrato",mantenimiento.getNum_orden_endesa());
        msg.put("codigoVisita",mantenimiento.getCod_visita());
        msg.put("telefonoContacto1", mantenimiento.getTelefono1_usuario());
        msg.put("telefonoContacto2", mantenimiento.getTelefono2_usuario());
        msg.put("modeloCaldera", maquina.getModelo_maquina());
        msg.put("uso", maquina.getFk_uso_maquina());
        msg.put("potencia", maquina.getPotencia_util());
        msg.put("anio", maquina.getPuesta_marcha_maquina());
        msg.put("descripcionMarcaCaldera", MarcaCalderaDAO.buscarNombreMarcaCalderaPorId(getBaseContext(),maquina.getFk_marca_maquina()));
        msg.put("estadoVisita", mantenimiento.getFk_estado_endesa());
        msg.put("fechaVisita", mantenimiento.getFecha_visita());
        msg.put("observacionesVisita", mantenimiento.getObservaciones());
        msg.put("recepcionComprobante", "1");
        msg.put("facturadoProveedor", "0");
        msg.put("fechaFactura", "2016-09-26");
        msg.put("numFactura", mantenimiento.getNum_factura());
        msg.put("codigoBarrasVisita", "569821435156964121");
        msg.put("cartaEnviada", "0");
        msg.put("fechaEnvioCarta", "2016-09-26");
        msg.put("tipoVisita", TiposVisitaDAO.buscarNombreTipoVisitaPorId(getBaseContext(),mantenimientoTerminado.getFk_tipo_visita()));
        msg.put("idTecnico", tecnico.getId_tecnico());
        msg.put("proveedor", "ICISA");
        msg.put("observacionesTecnico", mantenimientoTerminado.getObservaciones_tecnico());
        msg.put("contadorInterno", mantenimientoTerminado.getContador_interno());
        String rep="";
        String codBarr="";
        if (mantenimientoTerminado.isInsitu()){
            rep = "1";
            codBarr="569821435156964121";
        }else{
            rep = "0";
        }
        msg.put("tieneReparacion", rep);
        msg.put("idTipoReparacion", mantenimientoTerminado.getFk_tipo_reparacion());
        msg.put("fechaReparacion", mantenimiento.getFecha_visita());
        msg.put("idTiempoManoObra", mantenimientoTerminado.getFk_tiempo_mano_obra());
        msg.put("costeMateriales", mantenimientoTerminado.getCoste_materiales());
        msg.put("importemanoObra", mantenimientoTerminado.getCoste_mano_obra());
        msg.put("costeMaterialesCliente", mantenimientoTerminado.getCoste_materiales());
        msg.put("fechaFacturaReparacion", mantenimiento.getFecha_visita());
        msg.put("numeroFacturaReparacion", "");
        msg.put("codigoBarrasReparacion", codBarr);
        msg.put("tipoEquipamiento", equipamientoCalderas.get(0).getFk_tipo_equipamiento());
        msg.put("potenciaEquipamiento", equipamientoCalderas.get(0).getPotencia_fuegos());
        msg.put("tipoProceso", "");
        msg.put("nombreFichero", "");
        msg.put("contenidoFichero", "");
        return msg;
    }
    private JSONObject rellenarJsonImagenes(int id) throws JSONException, IOException, SQLException {
        arraylistImagenes.addAll(ImagenesDAO.buscarImagenPorFk_parte(getBaseContext(),id));
        JSONObject js = new JSONObject();
        JSONArray jsa = new JSONArray();
        for (int i = 0; i <arraylistImagenes.size(); i++) {
            File f=new File(arraylistImagenes.get(i).getRuta_imagen());
            Bitmap b = null;
            b = BitmapFactory.decodeStream(new FileInputStream(f));
            JSONObject jso = new JSONObject();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            try {
                jso.put("name",arraylistImagenes.get(i).getNombre_imagen());
                jso.put("base64",encodedImage);
                jsa.put(jso);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            js.put("imagen",jsa);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js;
    }
    private String loadLogoFromStorage() throws IOException {
        InputStream bitmap = null;
        bitmap =  getAssets().open("logo.png");
        Bitmap btmp= BitmapFactory.decodeStream(bitmap);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        btmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
        return encodedImage;
    }
    private String loadFirmaFromStorage(){
        Bitmap b=null;
        try {
            File f=new File(path, "firma"+mantenimiento.getId_mantenimiento()+".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
        return encodedImage;
    }
    private String loadTicketFromStorage(){
        Bitmap b=null;
        try {
            File f=new File(path, "ticket"+mantenimiento.getId_mantenimiento()+".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
        return encodedImage;
    }
}