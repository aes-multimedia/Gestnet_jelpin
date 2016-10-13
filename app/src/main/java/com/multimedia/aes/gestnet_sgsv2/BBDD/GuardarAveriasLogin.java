package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.AveriaDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GuardarAveriasLogin {
    private static String Json;
    private static Context context;
    private static boolean bien=true;

    public GuardarAveriasLogin(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonAverias();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonAverias() throws JSONException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("averias");
        for (int i = 0; i < jsonArray.length(); i++) {
            int id_averia;
            if (jsonArray.getJSONObject(i).getString("id_averia").equals("null")){
                id_averia = -1;
            }else{
                id_averia = jsonArray.getJSONObject(i).getInt("id_averia");
            }
            String identificador_carga = jsonArray.getJSONObject(i).getString("identificador_carga");
            String id_solicitud = jsonArray.getJSONObject(i).getString("id_solicitud");
            int fk_tecnico;
            if (jsonArray.getJSONObject(i).getString("fk_tecnico").equals("null")||jsonArray.getJSONObject(i).getString("fk_tecnico").equals("")){
                fk_tecnico = -1;
            }else{
                fk_tecnico = jsonArray.getJSONObject(i).getInt("id_averia");
            }
            String cod_contrato;
            if (jsonArray.getJSONObject(i).getString("cod_contrato").equals("null")){
                cod_contrato = "";
            }else{
                cod_contrato = jsonArray.getJSONObject(i).getString("cod_contrato");
            }
            String tipo_solicitud;
            if (jsonArray.getJSONObject(i).getString("tipo_solicitud").equals("null")){
                tipo_solicitud = "";
            }else{
                tipo_solicitud = jsonArray.getJSONObject(i).getString("tipo_solicitud");
            }
            String des_tipo_solicitud;
            if (jsonArray.getJSONObject(i).getString("des_tipo_solicitud").equals("null")){
                des_tipo_solicitud = "";
            }else{
                des_tipo_solicitud = jsonArray.getJSONObject(i).getString("des_tipo_solicitud");
            }
            String subtipo_solicitud;
            if (jsonArray.getJSONObject(i).getString("subtipo_solicitud").equals("null")){
                subtipo_solicitud = "";
            }else{
                subtipo_solicitud = jsonArray.getJSONObject(i).getString("subtipo_solicitud");
            }
            String des_subtipo_solicitud;
            if (jsonArray.getJSONObject(i).getString("des_subtipo_solicitud").equals("null")){
                des_subtipo_solicitud = "";
            }else{
                des_subtipo_solicitud = jsonArray.getJSONObject(i).getString("des_subtipo_solicitud");
            }
            String fecha_creacion;
            if (jsonArray.getJSONObject(i).getString("fecha_creacion").equals("null")){
                fecha_creacion = "";
            }else{
                fecha_creacion = jsonArray.getJSONObject(i).getString("fecha_creacion");
            }
            String fecha_cierre;
            if (jsonArray.getJSONObject(i).getString("fecha_cierre").equals("null")){
                fecha_cierre = "";
            }else{
                fecha_cierre = jsonArray.getJSONObject(i).getString("fecha_cierre");
            }
            String estado_solicitud;
            if (jsonArray.getJSONObject(i).getString("estado_solicitud").equals("null")){
                estado_solicitud = "";
            }else{
                estado_solicitud = jsonArray.getJSONObject(i).getString("estado_solicitud");
            }
            String des_estado_solicitud;
            if (jsonArray.getJSONObject(i).getString("des_estado_solicitud").equals("null")){
                des_estado_solicitud = "";
            }else{
                des_estado_solicitud = jsonArray.getJSONObject(i).getString("des_estado_solicitud");
            }
            String subestado_solicitud;
            if (jsonArray.getJSONObject(i).getString("subestado_solicitud").equals("null")){
                subestado_solicitud = "";
            }else{
                subestado_solicitud = jsonArray.getJSONObject(i).getString("subestado_solicitud");
            }
            String telefono_contacto;
            if (jsonArray.getJSONObject(i).getString("telefono_contacto").equals("null")){
                telefono_contacto = "";
            }else{
                telefono_contacto = jsonArray.getJSONObject(i).getString("telefono_contacto");
            }
            String persona_contacto;
            if (jsonArray.getJSONObject(i).getString("persona_contacto").equals("null")){
                persona_contacto = "";
            }else{
                persona_contacto = jsonArray.getJSONObject(i).getString("persona_contacto");
            }
            String des_averia;
            if (jsonArray.getJSONObject(i).getString("des_averia").equals("null")){
                des_averia = "";
            }else{
                des_averia = jsonArray.getJSONObject(i).getString("des_averia");
            }
            String observaciones;
            if (jsonArray.getJSONObject(i).getString("observaciones").equals("null")){
                observaciones = "";
            }else{
                observaciones = jsonArray.getJSONObject(i).getString("observaciones");
            }
            String proveedor;
            if (jsonArray.getJSONObject(i).getString("proveedor").equals("null")){
                proveedor = "";
            }else{
                proveedor = jsonArray.getJSONObject(i).getString("proveedor");
            }
            String descripcion_cancelacion;
            if (jsonArray.getJSONObject(i).getString("descripcion_cancelacion").equals("null")){
                descripcion_cancelacion = "";
            }else{
                descripcion_cancelacion = jsonArray.getJSONObject(i).getString("descripcion_cancelacion");
            }
            String urgencia;
            if (jsonArray.getJSONObject(i).getString("urgencia").equals("null")){
                urgencia = "";
            }else{
                urgencia = jsonArray.getJSONObject(i).getString("urgencia");
            }
            String fecha_limite_visita;
            if (jsonArray.getJSONObject(i).getString("fecha_limite_visita").equals("null")){
                fecha_limite_visita = "";
            }else{
                fecha_limite_visita = jsonArray.getJSONObject(i).getString("fecha_limite_visita");
            }
            String marca_caldera;
            if (jsonArray.getJSONObject(i).getString("marca_caldera").equals("null")){
                marca_caldera = "";
            }else{
                marca_caldera = jsonArray.getJSONObject(i).getString("marca_caldera");
            }
            String nombre_provincia;
            if (jsonArray.getJSONObject(i).getString("nombre_provincia").equals("null")){
                nombre_provincia = "";
            }else{
                nombre_provincia = jsonArray.getJSONObject(i).getString("nombre_provincia");
            }
            String urgente;
            if (jsonArray.getJSONObject(i).getString("urgente").equals("null")){
                urgente = "";
            }else{
                urgente = jsonArray.getJSONObject(i).getString("urgente");
            }
            String nombre_poblacion;
            if (jsonArray.getJSONObject(i).getString("nombre_poblacion").equals("null")){
                nombre_poblacion = "";
            }else{
                nombre_poblacion = jsonArray.getJSONObject(i).getString("nombre_poblacion");
            }
            String nom_calle;
            if (jsonArray.getJSONObject(i).getString("nom_calle").equals("null")){
                nom_calle = "";
            }else{
                nom_calle = jsonArray.getJSONObject(i).getString("nom_calle");
            }
            String cod_portal;
            if (jsonArray.getJSONObject(i).getString("cod_portal").equals("null")){
                cod_portal = "";
            }else{
                cod_portal = jsonArray.getJSONObject(i).getString("cod_portal");
            }
            String tip_piso;
            if (jsonArray.getJSONObject(i).getString("tip_piso").equals("null")){
                tip_piso = "";
            }else{
                tip_piso = jsonArray.getJSONObject(i).getString("tip_piso");
            }
            String tip_mano;
            if (jsonArray.getJSONObject(i).getString("tip_mano").equals("null")){
                tip_mano = "";
            }else{
                tip_mano = jsonArray.getJSONObject(i).getString("tip_mano");
            }
            String cod_postal;
            if (jsonArray.getJSONObject(i).getString("cod_postal").equals("null")){
                cod_postal = "";
            }else{
                cod_postal = jsonArray.getJSONObject(i).getString("cod_postal");
            }
            String cups;
            if (jsonArray.getJSONObject(i).getString("cups").equals("null")){
                cups = "";
            }else{
                cups = jsonArray.getJSONObject(i).getString("cups");
            }
            String estado_ultima_visita;
            if (jsonArray.getJSONObject(i).getString("estado_ultima_visita").equals("null")){
                estado_ultima_visita = "";
            }else{
                estado_ultima_visita = jsonArray.getJSONObject(i).getString("estado_ultima_visita");
            }
            String urgencia_ultima_visita;
            if (jsonArray.getJSONObject(i).getString("urgencia_ultima_visita").equals("null")){
                urgencia_ultima_visita = "";
            }else{
                urgencia_ultima_visita = jsonArray.getJSONObject(i).getString("urgencia_ultima_visita");
            }
            String mant_gas_calefaccion;
            if (jsonArray.getJSONObject(i).getString("mant_gas_calefaccion").equals("null")){
                mant_gas_calefaccion = "";
            }else{
                mant_gas_calefaccion = jsonArray.getJSONObject(i).getString("mant_gas_calefaccion");
            }
            String mant_gas;
            if (jsonArray.getJSONObject(i).getString("mant_gas").equals("null")){
                mant_gas = "";
            }else{
                mant_gas = jsonArray.getJSONObject(i).getString("mant_gas");
            }
            String horario_contacto;
            if (jsonArray.getJSONObject(i).getString("horario_contacto").equals("null")){
                horario_contacto = "";
            }else{
                horario_contacto = jsonArray.getJSONObject(i).getString("horario_contacto");
            }
            String observaciones_visita;
            if (jsonArray.getJSONObject(i).getString("observaciones_visita").equals("null")){
                observaciones_visita = "";
            }else{
                observaciones_visita = jsonArray.getJSONObject(i).getString("observaciones_visita");
            }
            String telefono_cliente;
            if (jsonArray.getJSONObject(i).getString("telefono_cliente").equals("null")){
                telefono_cliente = "";
            }else{
                telefono_cliente = jsonArray.getJSONObject(i).getString("telefono_cliente");
            }
            String efv;
            if (jsonArray.getJSONObject(i).getString("efv").equals("null")){
                efv = "";
            }else{
                efv = jsonArray.getJSONObject(i).getString("efv");
            }
            String scoring;
            if (jsonArray.getJSONObject(i).getString("scoring").equals("null")){
                scoring = "";
            }else{
                scoring = jsonArray.getJSONObject(i).getString("scoring");
            }
            String contador_averias;
            if (jsonArray.getJSONObject(i).getString("contador_averias").equals("null")){
                contador_averias = "";
            }else{
                contador_averias = jsonArray.getJSONObject(i).getString("contador_averias");
            }
            String categoria_visita;
            if (jsonArray.getJSONObject(i).getString("categoria_visita").equals("null")){
                categoria_visita = "";
            }else{
                categoria_visita = jsonArray.getJSONObject(i).getString("categoria_visita");
            }
            String iniciado_android;
            if (jsonArray.getJSONObject(i).getString("iniciado_android").equals("null")){
                iniciado_android = "";
            }else{
                iniciado_android = jsonArray.getJSONObject(i).getString("iniciado_android");
            }
            String enviado_android;
            if (jsonArray.getJSONObject(i).getString("enviado_android").equals("null")){
                enviado_android = "";
            }else{
                enviado_android = jsonArray.getJSONObject(i).getString("enviado_android");
            }
            String cerrado;
            if (jsonArray.getJSONObject(i).getString("cerrado").equals("null")){
                cerrado = "";
            }else{
                cerrado = jsonArray.getJSONObject(i).getString("cerrado");
            }
            String cerrado_error = jsonArray.getJSONObject(i).getString("cerrado_error");
            if (jsonArray.getJSONObject(i).getString("cerrado_error").equals("null")){
                cerrado_error = "";
            }else{
                cerrado_error = jsonArray.getJSONObject(i).getString("cerrado_error");
            }
            String historico;
            if (jsonArray.getJSONObject(i).getString("historico").equals("null")){
                historico = "";
            }else{
                historico = jsonArray.getJSONObject(i).getString("historico");
            }
            if (AveriaDAO.newAveria(context,id_averia, identificador_carga, id_solicitud, fk_tecnico,
                    cod_contrato, tipo_solicitud, des_tipo_solicitud, subtipo_solicitud,
                    des_subtipo_solicitud, fecha_creacion, fecha_cierre, estado_solicitud,
                    des_estado_solicitud, subestado_solicitud, telefono_contacto,
                    persona_contacto, des_averia, observaciones, proveedor,
                    descripcion_cancelacion, urgencia, fecha_limite_visita,
                    marca_caldera, nombre_provincia, urgente, nombre_poblacion,
                    nom_calle, cod_portal, tip_piso, tip_mano, cod_postal,
                    cups, estado_ultima_visita, urgencia_ultima_visita, mant_gas_calefaccion,
                    mant_gas, horario_contacto, observaciones_visita, telefono_cliente,
                    efv, scoring, contador_averias, categoria_visita, iniciado_android,
                    enviado_android, cerrado, cerrado_error, historico)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            ManagerProgressDialog.guardarDatosMantenimiento(context);
            new GuardarMantenimientosLogin(context,Json);
        }else{
            ((Login)context).sacarMensaje("error en averia");

        }

    }
}
