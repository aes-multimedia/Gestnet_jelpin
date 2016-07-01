package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.dao.AveriaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarAverias {
    private static String Json;
    private static Context context;
    private static boolean bien=true;

    public GuardarAverias(Context context, String json) {
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
            if (jsonObject.getString("id_averia").equals("null")){
                id_averia = -1;
            }else{
                id_averia = jsonObject.getInt("id_averia");
            }
            String identificador_carga = jsonObject.getString("identificador_carga");
            String id_solicitud = jsonObject.getString("id_solicitud");
            /*SEGUIR AQUI if (jsonObject.getString("id_averia").equals("null")){
            id_averia = -1;
        }else{
            id_averia = jsonObject.getInt("id_averia");
        }*/
            int fk_tecnico = jsonObject.getInt("fk_tecnico");
            String cod_contrato = jsonObject.getString("cod_contrato");
            String tipo_solicitud = jsonObject.getString("tipo_solicitud");
            String des_tipo_solicitud = jsonObject.getString("des_tipo_solicitud");
            String subtipo_solicitud = jsonObject.getString("subtipo_solicitud");
            String des_subtipo_solicitud = jsonObject.getString("des_subtipo_solicitud");
            String fecha_creacion = jsonObject.getString("fecha_creacion");
            String fecha_cierre = jsonObject.getString("fecha_cierre");
            String estado_solicitud = jsonObject.getString("estado_solicitud");
            String des_estado_solicitud = jsonObject.getString("des_estado_solicitud");
            String subestado_solicitud = jsonObject.getString("subestado_solicitud");
            String telefono_contacto = jsonObject.getString("telefono_contacto");
            String persona_contacto = jsonObject.getString("persona_contacto");
            String des_averia = jsonObject.getString("des_averia");
            String observaciones = jsonObject.getString("observaciones");
            String proveedor = jsonObject.getString("proveedor");
            String descripcion_cancelacion = jsonObject.getString("descripcion_cancelacion");
            String urgencia = jsonObject.getString("urgencia");
            String fecha_limite_visita = jsonObject.getString("fecha_limite_visita");
            String marca_caldera = jsonObject.getString("marca_caldera");
            String nombre_provincia = jsonObject.getString("nombre_provincia");
            String urgente = jsonObject.getString("urgente");
            String nombre_poblacion = jsonObject.getString("nombre_poblacion");
            String nom_calle = jsonObject.getString("nom_calle");
            String cod_portal = jsonObject.getString("cod_portal");
            String tip_piso = jsonObject.getString("tip_piso");
            String tip_mano = jsonObject.getString("tip_mano");
            String cod_postal = jsonObject.getString("cod_postal");
            String cups = jsonObject.getString("cups");
            String estado_ultima_visita = jsonObject.getString("estado_ultima_visita");
            String urgencia_ultima_visita = jsonObject.getString("urgencia_ultima_visita");
            String mant_gas_calefaccion = jsonObject.getString("mant_gas_calefaccion");
            String mant_gas = jsonObject.getString("mant_gas");
            String horario_contacto = jsonObject.getString("horario_contacto");
            String observaciones_visita = jsonObject.getString("observaciones_visita");
            String telefono_cliente = jsonObject.getString("telefono_cliente");
            String efv = jsonObject.getString("efv");
            String scoring = jsonObject.getString("scoring");
            String contador_averias = jsonObject.getString("contador_averias");
            String categoria_visita = jsonObject.getString("categoria_visita");
            String iniciado_android = jsonObject.getString("iniciado_android");
            String enviado_android = jsonObject.getString("enviado_android");
            String cerrado = jsonObject.getString("cerrado");
            String cerrado_error = jsonObject.getString("cerrado_error");
            String historico = jsonObject.getString("historico");
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
            ((Login)context).sacarMensaje("averia creado");
            new GuardarMantenimientos(context,Json);
        }else{
            ((Login)context).sacarMensaje("error en averia");

        }

    }
}
