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
            if (jsonArray.getJSONObject(i).getString("fk_tecnico").equals("null")){
                fk_tecnico = -1;
            }else{
                fk_tecnico = jsonArray.getJSONObject(i).getInt("id_averia");
            }
            String cod_contrato = jsonArray.getJSONObject(i).getString("cod_contrato");
            String tipo_solicitud = jsonArray.getJSONObject(i).getString("tipo_solicitud");
            String des_tipo_solicitud = jsonArray.getJSONObject(i).getString("des_tipo_solicitud");
            String subtipo_solicitud = jsonArray.getJSONObject(i).getString("subtipo_solicitud");
            String des_subtipo_solicitud = jsonArray.getJSONObject(i).getString("des_subtipo_solicitud");
            String fecha_creacion = jsonArray.getJSONObject(i).getString("fecha_creacion");
            String fecha_cierre = jsonArray.getJSONObject(i).getString("fecha_cierre");
            String estado_solicitud = jsonArray.getJSONObject(i).getString("estado_solicitud");
            String des_estado_solicitud = jsonArray.getJSONObject(i).getString("des_estado_solicitud");
            String subestado_solicitud = jsonArray.getJSONObject(i).getString("subestado_solicitud");
            String telefono_contacto = jsonArray.getJSONObject(i).getString("telefono_contacto");
            String persona_contacto = jsonArray.getJSONObject(i).getString("persona_contacto");
            String des_averia = jsonArray.getJSONObject(i).getString("des_averia");
            String observaciones = jsonArray.getJSONObject(i).getString("observaciones");
            String proveedor = jsonArray.getJSONObject(i).getString("proveedor");
            String descripcion_cancelacion = jsonArray.getJSONObject(i).getString("descripcion_cancelacion");
            String urgencia = jsonArray.getJSONObject(i).getString("urgencia");
            String fecha_limite_visita = jsonArray.getJSONObject(i).getString("fecha_limite_visita");
            String marca_caldera = jsonArray.getJSONObject(i).getString("marca_caldera");
            String nombre_provincia = jsonArray.getJSONObject(i).getString("nombre_provincia");
            String urgente = jsonArray.getJSONObject(i).getString("urgente");
            String nombre_poblacion = jsonArray.getJSONObject(i).getString("nombre_poblacion");
            String nom_calle = jsonArray.getJSONObject(i).getString("nom_calle");
            String cod_portal = jsonArray.getJSONObject(i).getString("cod_portal");
            String tip_piso = jsonArray.getJSONObject(i).getString("tip_piso");
            String tip_mano = jsonArray.getJSONObject(i).getString("tip_mano");
            String cod_postal = jsonArray.getJSONObject(i).getString("cod_postal");
            String cups = jsonArray.getJSONObject(i).getString("cups");
            String estado_ultima_visita = jsonArray.getJSONObject(i).getString("estado_ultima_visita");
            String urgencia_ultima_visita = jsonArray.getJSONObject(i).getString("urgencia_ultima_visita");
            String mant_gas_calefaccion = jsonArray.getJSONObject(i).getString("mant_gas_calefaccion");
            String mant_gas = jsonArray.getJSONObject(i).getString("mant_gas");
            String horario_contacto = jsonArray.getJSONObject(i).getString("horario_contacto");
            String observaciones_visita = jsonArray.getJSONObject(i).getString("observaciones_visita");
            String telefono_cliente = jsonArray.getJSONObject(i).getString("telefono_cliente");
            String efv = jsonArray.getJSONObject(i).getString("efv");
            String scoring = jsonArray.getJSONObject(i).getString("scoring");
            String contador_averias = jsonArray.getJSONObject(i).getString("contador_averias");
            String categoria_visita = jsonArray.getJSONObject(i).getString("categoria_visita");
            String iniciado_android = jsonArray.getJSONObject(i).getString("iniciado_android");
            String enviado_android = jsonArray.getJSONObject(i).getString("enviado_android");
            String cerrado = jsonArray.getJSONObject(i).getString("cerrado");
            String cerrado_error = jsonArray.getJSONObject(i).getString("cerrado_error");
            String historico = jsonArray.getJSONObject(i).getString("historico");
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
