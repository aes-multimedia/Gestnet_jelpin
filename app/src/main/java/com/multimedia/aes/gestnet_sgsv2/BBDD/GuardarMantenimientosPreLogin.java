package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;
import com.multimedia.aes.gestnet_sgsv2.nucleo.PreLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarMantenimientosPreLogin {
    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarMantenimientosPreLogin(Context context, String json) {
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
            int id_mantenimiento;
            if (jsonArray.getJSONObject(i).getString("id_parte").equals("null")){
                id_mantenimiento = -1;
            }else{
                id_mantenimiento = jsonArray.getJSONObject(i).getInt("id_parte");
            }
            int fk_user_creador;
            if (jsonArray.getJSONObject(i).getString("fk_user_creador").equals("null")){
                fk_user_creador = -1;
            }else{
                fk_user_creador = jsonArray.getJSONObject(i).getInt("fk_user_creador");
            }
            int fk_tecnico;
            if (jsonArray.getJSONObject(i).getString("fk_tecnico").equals("null")){
                fk_tecnico = -1;
            }else{
                fk_tecnico = jsonArray.getJSONObject(i).getInt("fk_tecnico");
            }
            int fk_usuario;
            if (jsonArray.getJSONObject(i).getString("fk_usuario").equals("null")){
                fk_usuario = -1;
            }else{
                fk_usuario = jsonArray.getJSONObject(i).getInt("fk_usuario");
            }
            int fk_direccion;
            if (jsonArray.getJSONObject(i).getString("fk_direccion").equals("null")){
                fk_direccion = -1;
            }else{
                fk_direccion = jsonArray.getJSONObject(i).getInt("fk_direccion");
            }
            String direccion = jsonArray.getJSONObject(i).getString("direccion");
            String cod_postal = jsonArray.getJSONObject(i).getString("codigo_postal");
            String provincia = jsonArray.getJSONObject(i).getString("provincia");
            String municipio = jsonArray.getJSONObject(i).getString("municipio");
            int fk_maquina;
            if (jsonArray.getJSONObject(i).getString("fk_maquina").equals("null")){
                fk_maquina = -1;
            }else{
                fk_maquina = jsonArray.getJSONObject(i).getInt("fk_maquina");
            }
            String fecha_creacion = jsonArray.getJSONObject(i).getString("fecha_creacion");
            String fecha_aviso = jsonArray.getJSONObject(i).getString("fecha_aviso");
            String fecha_visita = jsonArray.getJSONObject(i).getString("fecha_visita");
            String visita_duplicada = jsonArray.getJSONObject(i).getString("visita_duplicada");
            String fecha_reparacion = jsonArray.getJSONObject(i).getString("fecha_reparacion");
            String num_parte = jsonArray.getJSONObject(i).getString("num_parte");
            int fk_tipo;
            if (jsonArray.getJSONObject(i).getString("fk_tipo").equals("null")){
                fk_tipo = -1;
            }else{
                fk_tipo = jsonArray.getJSONObject(i).getInt("fk_tipo");
            }
            int fk_user_asignacion;
            if (jsonArray.getJSONObject(i).getString("fk_user_asignacion").equals("null")){
                fk_user_asignacion = -1;
            }else{
                fk_user_asignacion = jsonArray.getJSONObject(i).getInt("fk_user_asignacion");
            }
            int fk_horario;
            if (jsonArray.getJSONObject(i).getString("fk_horario").equals("null")){
                fk_horario = -1;
            }else{
                fk_horario = jsonArray.getJSONObject(i).getInt("fk_horario");
            }
            String franja_horaria = jsonArray.getJSONObject(i).getString("franja_horaria");
            int fk_franja_ip;
            if (jsonArray.getJSONObject(i).getString("fk_franja_ip").equals("null")){
                fk_franja_ip = -1;
            }else{
                fk_franja_ip = jsonArray.getJSONObject(i).getInt("fk_franja_ip");
            }
            int fk_estado;
            if (jsonArray.getJSONObject(i).getString("fk_estado").equals("null")){
                fk_estado = -1;
            }else{
                fk_estado = jsonArray.getJSONObject(i).getInt("fk_estado");
            }
            String observaciones = jsonArray.getJSONObject(i).getString("observaciones");
            String observacionesAsignacion = jsonArray.getJSONObject(i).getString("observacionesAsignacion");
            String confirmado = jsonArray.getJSONObject(i).getString("confirmado");
            String imprimir = jsonArray.getJSONObject(i).getString("imprimir");
            String fecha_factura = jsonArray.getJSONObject(i).getString("fecha_factura");
            String num_factura = jsonArray.getJSONObject(i).getString("num_factura");
            String fecha_factura_rectificativa = jsonArray.getJSONObject(i).getString("fecha_factura_rectificativa");
            String num_factura_rectificativa = jsonArray.getJSONObject(i).getString("num_factura_rectificativa");
            int fk_pend_fact;
            if (jsonArray.getJSONObject(i).getString("fk_pend_fact").equals("null")){
                fk_pend_fact = -1;
            }else{
                fk_pend_fact = jsonArray.getJSONObject(i).getInt("fk_pend_fact");
            }
            String num_orden_endesa = jsonArray.getJSONObject(i).getString("num_orden_endesa");
            String fecha_maxima_endesa = jsonArray.getJSONObject(i).getString("fecha_maxima_endesa");
            int fk_estado_endesa;
            if (jsonArray.getJSONObject(i).getString("fk_estado_endesa").equals("null")){
                fk_estado_endesa = -1;
            }else{
                fk_estado_endesa = jsonArray.getJSONObject(i).getInt("fk_estado_endesa");
            }
            String insistencia_endesa = jsonArray.getJSONObject(i).getString("insistencia_endesa");
            String contrato_endesa = jsonArray.getJSONObject(i).getString("contrato_endesa");
            String producto_endesa = jsonArray.getJSONObject(i).getString("producto_endesa");
            int fk_tipo_os;
            if (jsonArray.getJSONObject(i).getString("fk_tipo_os").equals("null")){
                fk_tipo_os = -1;
            }else{
                fk_tipo_os = jsonArray.getJSONObject(i).getInt("fk_tipo_os");
            }
            int fk_tipo_producto;
            if (jsonArray.getJSONObject(i).getString("fk_tipo_producto").equals("null")){
                fk_tipo_producto = -1;
            }else{
                fk_tipo_producto = jsonArray.getJSONObject(i).getInt("fk_tipo_producto");
            }
            String pagado_endesa = jsonArray.getJSONObject(i).getString("pagado_endesa");
            String ciclo_liq_endesa = jsonArray.getJSONObject(i).getString("ciclo_liq_endesa");
            String importe_pago_endesa = jsonArray.getJSONObject(i).getString("importe_pago_endesa");
            String fecha_pagado_endesa = jsonArray.getJSONObject(i).getString("fecha_pagado_endesa");
            String pagado_operario = jsonArray.getJSONObject(i).getString("pagado_operario");
            String fecha_anulado = jsonArray.getJSONObject(i).getString("fecha_anulado");
            String fecha_modificacion_tecnico = jsonArray.getJSONObject(i).getString("fecha_modificacion_tecnico");
            int fk_remoto_central;
            if (jsonArray.getJSONObject(i).getString("fk_remoto_central").equals("null")){
                fk_remoto_central = -1;
            }else{
                fk_remoto_central = jsonArray.getJSONObject(i).getInt("fk_remoto_central");
            }
            String fac_nombre = jsonArray.getJSONObject(i).getString("fac_nombre");
            String fac_direccion = jsonArray.getJSONObject(i).getString("fac_direccion");
            String fac_cp = jsonArray.getJSONObject(i).getString("fac_cp");
            String fac_poblacion = jsonArray.getJSONObject(i).getString("fac_poblacion");
            String fac_provincia = jsonArray.getJSONObject(i).getString("fac_provincia");
            String fac_DNI = jsonArray.getJSONObject(i).getString("fac_DNI");
            String fac_email = jsonArray.getJSONObject(i).getString("fac_email");
            String fac_telefonos = jsonArray.getJSONObject(i).getString("fac_telefonos");
            String otros_sintomas = jsonArray.getJSONObject(i).getString("otros_sintomas");
            String fecha_baja = jsonArray.getJSONObject(i).getString("fecha_baja");
            String fac_baja_stock = jsonArray.getJSONObject(i).getString("fac_baja_stock");
            String estado_android = jsonArray.getJSONObject(i).getString("estado_android");
            int fk_tipo_urgencia;
            if (jsonArray.getJSONObject(i).getString("fk_tipo_urgencia").equals("null")){
                fk_tipo_urgencia = -1;
            }else{
                fk_tipo_urgencia = jsonArray.getJSONObject(i).getInt("fk_tipo_urgencia");
            }
            String fecha_cierre = jsonArray.getJSONObject(i).getString("fecha_cierre");
            String num_lote = jsonArray.getJSONObject(i).getString("num_lote");
            String bEnBatch = jsonArray.getJSONObject(i).getString("bEnBatch");
            String cod_visita = jsonArray.getJSONObject(i).getString("cod_visita");
            String fecha_envio_carta = jsonArray.getJSONObject(i).getString("fecha_envio_carta");
            String bCartaEnviada = jsonArray.getJSONObject(i).getString("bCartaEnviada");
            String fecha_otro_dia = jsonArray.getJSONObject(i).getString("fecha_otro_dia");
            String fecha_ausente_limite = jsonArray.getJSONObject(i).getString("fecha_ausente_limite");
            int fk_carga_archivo;
            if (jsonArray.getJSONObject(i).getString("fk_carga_archivo").equals("null")){
                fk_carga_archivo = -1;
            }else{
                fk_carga_archivo = jsonArray.getJSONObject(i).getInt("fk_carga_archivo");
            }
            String orden = jsonArray.getJSONObject(i).getString("orden");
            String historico = jsonArray.getJSONObject(i).getString("historico");
            int fk_tipo_urgencia_factura;
            if (jsonArray.getJSONObject(i).getString("fk_tipo_urgencia_factura").equals("null")){
                fk_tipo_urgencia_factura = -1;
            }else{
                fk_tipo_urgencia_factura = jsonArray.getJSONObject(i).getInt("fk_tipo_urgencia_factura");
            }
            String error_batch = jsonArray.getJSONObject(i).getString("error_batch");
            int fk_batch_actual;
            if (jsonArray.getJSONObject(i).getString("fk_batch_actual").equals("null")){
                fk_batch_actual = -1;
            }else{
                fk_batch_actual = jsonArray.getJSONObject(i).getInt("fk_batch_actual");
            }
            int fk_efv;
            if (jsonArray.getJSONObject(i).getString("fk_efv").equals("null")){
                fk_efv = -1;
            }else{
                fk_efv = jsonArray.getJSONObject(i).getInt("fk_efv");
            }
            String scoring = jsonArray.getJSONObject(i).getString("scoring");
            int fk_categoria_visita;
            if (jsonArray.getJSONObject(i).getString("fk_categoria_visita").equals("null")){
                fk_categoria_visita = -1;
            }else{
                fk_categoria_visita = jsonArray.getJSONObject(i).getInt("fk_categoria_visita");
            }
            String contador_averias = jsonArray.getJSONObject(i).getString("contador_averias");
            JSONObject jsonObject1 = jsonObject.getJSONArray("cliente").getJSONObject(0);
            int fk_empresa_usuario = jsonObject1.getInt("fk_empresa");
            String numero_usuario = jsonObject1.getString("numero_usuario");
            String nombre_usuario = jsonObject1.getString("nombre_usuario");
            String dni_usuario = jsonObject1.getString("DNI");
            String telefono1_usuario = jsonObject1.getString("telefono1");
            String telefono2_usuario = jsonObject1.getString("telefono2");
            String telefono3_usuario = jsonObject1.getString("telefono3");
            String telefono4_usuario = jsonObject1.getString("telefono4");
            String telefono5_usuario = jsonObject1.getString("otros_telefonos");
            String email_usuario = jsonObject1.getString("email");
            String moroso_usuario = jsonObject1.getString("moroso");
            String observaciones_usuario = jsonObject1.getString("observaciones");
            if (MantenimientoDAO.newMantenimiento(context,id_mantenimiento, fk_user_creador, fk_tecnico, fk_usuario,
                    fk_empresa_usuario, numero_usuario, nombre_usuario, dni_usuario,
                    telefono1_usuario, telefono2_usuario, telefono3_usuario,
                    telefono4_usuario, telefono5_usuario, email_usuario,
                    moroso_usuario, observaciones_usuario,
                    fk_direccion, direccion, cod_postal, provincia, municipio, fk_maquina, fecha_creacion, fecha_aviso,
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
            ((PreLogin)context).siguienteActivity();
        }else{
            ((PreLogin)context).sacarMensaje("error mantenimiento");
        }
    }
}
