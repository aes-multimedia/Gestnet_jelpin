package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarMantenimientos {
    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarMantenimientos(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonMantenimiento();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonMantenimiento() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("mantenimientos");
        for (int i = 0; i < jsonArray.length(); i++) {
            int id_mantenimiento = jsonObject.getInt("id_parte");
            int fk_user_creador = jsonObject.getInt("fk_user_creador");
            int fk_tecnico = jsonObject.getInt("fk_tecnico");
            int fk_usuario = jsonObject.getInt("fk_usuario");
            int fk_direccion = jsonObject.getInt("fk_direccion");
            int fk_maquina = jsonObject.getInt("fk_maquina");
            String fecha_creacion = jsonObject.getString("fecha_creacion");
            String fecha_aviso = jsonObject.getString("fecha_aviso");
            String fecha_visita = jsonObject.getString("fecha_visita");
            String visita_duplicada = jsonObject.getString("visita_duplicada");
            String fecha_reparacion = jsonObject.getString("fecha_reparacion");
            String num_parte = jsonObject.getString("num_parte");
            int fk_tipo = jsonObject.getInt("fk_tipo");
            int fk_user_asignacion = jsonObject.getInt("fk_user_asignacion");
            int fk_horario = jsonObject.getInt("fk_horario");
            String franja_horaria = jsonObject.getString("franja_horaria");
            int fk_franja_ip = jsonObject.getInt("fk_franja_ip");
            int fk_estado = jsonObject.getInt("fk_estado");
            String observaciones = jsonObject.getString("observaciones");
            String observacionesAsignacion = jsonObject.getString("observacionesAsignacion");
            String confirmado = jsonObject.getString("confirmado");
            String imprimir = jsonObject.getString("imprimir");
            String fecha_factura = jsonObject.getString("fecha_factura");
            String num_factura = jsonObject.getString("num_factura");
            String fecha_factura_rectificativa = jsonObject.getString("fecha_factura_rectificativa");
            String num_factura_rectificativa = jsonObject.getString("num_factura_rectificativa");
            int fk_pend_fact = jsonObject.getInt("fk_pend_fact");
            String num_orden_endesa = jsonObject.getString("num_orden_endesa");
            String fecha_maxima_endesa = jsonObject.getString("fecha_maxima_endesa");
            int fk_estado_endesa = jsonObject.getInt("fk_estado_endesa");
            String insistencia_endesa = jsonObject.getString("insistencia_endesa");
            String contrato_endesa = jsonObject.getString("contrato_endesa");
            String producto_endesa = jsonObject.getString("producto_endesa");
            int fk_tipo_os = jsonObject.getInt("fk_tipo_os");
            int fk_tipo_producto = jsonObject.getInt("fk_tipo_producto");
            String pagado_endesa = jsonObject.getString("pagado_endesa");
            String ciclo_liq_endesa = jsonObject.getString("ciclo_liq_endesa");
            String importe_pago_endesa = jsonObject.getString("importe_pago_endesa");
            String fecha_pagado_endesa = jsonObject.getString("fecha_pagado_endesa");
            String pagado_operario = jsonObject.getString("pagado_operario");
            String fecha_anulado = jsonObject.getString("fecha_anulado");
            String fecha_modificacion_tecnico = jsonObject.getString("fecha_modificacion_tecnico");
            int fk_remoto_central = jsonObject.getInt("fk_remoto_central");
            String fac_nombre = jsonObject.getString("fac_nombre");
            String fac_direccion = jsonObject.getString("fac_direccion");
            String fac_cp = jsonObject.getString("fac_cp");
            String fac_poblacion = jsonObject.getString("fac_poblacion");
            String fac_provincia = jsonObject.getString("fac_provincia");
            String fac_DNI = jsonObject.getString("fac_DNI");
            String fac_email = jsonObject.getString("fac_email");
            String fac_telefonos = jsonObject.getString("fac_telefonos");
            String otros_sintomas = jsonObject.getString("otros_sintomas");
            String fecha_baja = jsonObject.getString("fecha_baja");
            String fac_baja_stock = jsonObject.getString("fac_baja_stock");
            String estado_android = jsonObject.getString("estado_android");
            int fk_tipo_urgencia = jsonObject.getInt("fk_tipo_urgencia");
            String fecha_cierre = jsonObject.getString("fecha_cierre");
            String num_lote = jsonObject.getString("num_lote");
            String bEnBatch = jsonObject.getString("bEnBatch");
            String cod_visita = jsonObject.getString("cod_visita");
            String fecha_envio_carta = jsonObject.getString("fecha_envio_carta");
            String bCartaEnviada = jsonObject.getString("bCartaEnviada");
            String fecha_otro_dia = jsonObject.getString("fecha_otro_dia");
            String fecha_ausente_limite = jsonObject.getString("fecha_ausente_limite");
            int fk_carga_archivo = jsonObject.getInt("fk_carga_archivo");
            String orden = jsonObject.getString("orden");
            String historico = jsonObject.getString("historico");
            int fk_tipo_urgencia_factura = jsonObject.getInt("fk_tipo_urgencia_factura");
            String error_batch = jsonObject.getString("error_batch");
            int fk_batch_actual = jsonObject.getInt("fk_batch_actual");
            int fk_efv = jsonObject.getInt("fk_efv");
            String scoring = jsonObject.getString("scoring");
            int fk_categoria_visita = jsonObject.getInt("fk_categoria_visita");
            String contador_averias = jsonObject.getString("contador_averias");
            if (MantenimientoDAO.newMantenimiento(context,id_mantenimiento, fk_user_creador, fk_tecnico, fk_usuario,
                    fk_direccion, fk_maquina, fecha_creacion, fecha_aviso,
                    fecha_visita, visita_duplicada, fecha_reparacion,
                    num_parte, fk_tipo, fk_user_asignacion, fk_horario,
                    franja_horaria, fk_franja_ip, fk_estado, observaciones,
                    observacionesAsignacion, confirmado, imprimir,
                    fecha_factura, num_factura, fecha_factura_rectificativa,
                    num_factura_rectificativa, fk_pend_fact, num_orden_endesa,
                    fecha_maxima_endesa, fk_estado_endesa, insistencia_endesa,
                    contrato_endesa, producto_endesa, fk_tipo_os,
                    fk_tipo_producto, pagado_endesa, ciclo_liq_endesa,
                    importe_pago_endesa, fecha_pagado_endesa, pagado_operario,
                    fecha_anulado, fecha_modificacion_tecnico, fk_remoto_central,
                    fac_nombre, fac_direccion, fac_cp, fac_poblacion,
                    fac_provincia, fac_DNI, fac_email, fac_telefonos,
                    otros_sintomas, fecha_baja, fac_baja_stock, estado_android,
                    fk_tipo_urgencia, fecha_cierre, num_lote, bEnBatch,
                    cod_visita, fecha_envio_carta, bCartaEnviada,
                    fecha_otro_dia, fecha_ausente_limite, fk_carga_archivo,
                    orden, historico, fk_tipo_urgencia_factura,
                    error_batch, fk_batch_actual, fk_efv, scoring,
                    fk_categoria_visita, contador_averias)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            ((Login)context).sacarMensaje("mantenimiento creado");
        }else{
            ((Login)context).sacarMensaje("error mantenimiento");
        }
    }
}
