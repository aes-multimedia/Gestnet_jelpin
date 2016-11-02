package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarMantenimientosLogin {
    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarMantenimientosLogin(Context context, String json) {
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
            if (jsonArray.getJSONObject(i).getString("id_parte").equals("null")||jsonArray.getJSONObject(i).getString("id_parte").equals("")){
                id_mantenimiento = -1;
            }else{
                id_mantenimiento = jsonArray.getJSONObject(i).getInt("id_parte");
            }
            String hash;
            if (jsonArray.getJSONObject(i).getString("hash").equals("null")){
                hash = "";
            }else{
                hash = jsonArray.getJSONObject(i).getString("hash");
            }
            String cod_barras;
            if (jsonArray.getJSONObject(i).getString("cod_barras").equals("null")){
                cod_barras = "";
            }else{
                cod_barras = jsonArray.getJSONObject(i).getString("cod_barras");
            }
            int fk_user_creador;
            if (jsonArray.getJSONObject(i).getString("fk_user_creador").equals("null")||jsonArray.getJSONObject(i).getString("fk_user_creador").equals("")){
                fk_user_creador = -1;
            }else{
                fk_user_creador = jsonArray.getJSONObject(i).getInt("fk_user_creador");
            }
            int fk_tecnico;
            if (jsonArray.getJSONObject(i).getString("fk_tecnico").equals("null")||jsonArray.getJSONObject(i).getString("fk_tecnico").equals("")){
                fk_tecnico = -1;
            }else{
                fk_tecnico = jsonArray.getJSONObject(i).getInt("fk_tecnico");
            }
            int fk_usuario;
            if (jsonArray.getJSONObject(i).getString("fk_usuario").equals("null")||jsonArray.getJSONObject(i).getString("fk_usuario").equals("")){
                fk_usuario = -1;
            }else{
                fk_usuario = jsonArray.getJSONObject(i).getInt("fk_usuario");
            }
            int fk_direccion;
            if (jsonArray.getJSONObject(i).getString("fk_direccion").equals("null")||jsonArray.getJSONObject(i).getString("fk_direccion").equals("")){
                fk_direccion = -1;
            }else{
                fk_direccion = jsonArray.getJSONObject(i).getInt("fk_direccion");
            }
            String direccion;
            if (jsonArray.getJSONObject(i).getString("direccion").equals("null")){
                direccion = "";
            }else{
                direccion = jsonArray.getJSONObject(i).getString("direccion");
            }
            String cod_postal;
            if (jsonArray.getJSONObject(i).getString("codigo_postal").equals("null")){
                cod_postal = "";
            }else{
                cod_postal = jsonArray.getJSONObject(i).getString("codigo_postal");
            }
            String provincia;
            if (jsonArray.getJSONObject(i).getString("provincia").equals("null")){
                provincia = "";
            }else{
                provincia = jsonArray.getJSONObject(i).getString("provincia");
            }
            String municipio;
            if (jsonArray.getJSONObject(i).getString("municipio").equals("null")){
                municipio = "";
            }else{
                municipio = jsonArray.getJSONObject(i).getString("municipio");
            }
            String latitud;
            if (jsonArray.getJSONObject(i).getString("latitud").equals("null")){
                latitud = "";
            }else{
                latitud = jsonArray.getJSONObject(i).getString("latitud");
            }
            String longitud;
            if (jsonArray.getJSONObject(i).getString("longitud").equals("null")){
                longitud = "";
            }else{
                longitud = jsonArray.getJSONObject(i).getString("longitud");
            }
            int fk_maquina;
            if (jsonArray.getJSONObject(i).getString("fk_maquina").equals("null")||jsonArray.getJSONObject(i).getString("fk_maquina").equals("")){
                fk_maquina = -1;
            }else{
                fk_maquina = jsonArray.getJSONObject(i).getInt("fk_maquina");
            }
            String fecha_creacion;
            if (jsonArray.getJSONObject(i).getString("fecha_creacion").equals("null")){
                fecha_creacion = "";
            }else{
                fecha_creacion = jsonArray.getJSONObject(i).getString("fecha_creacion");
            }
            String fecha_aviso;
            if (jsonArray.getJSONObject(i).getString("fecha_aviso").equals("null")){
                fecha_aviso = "";
            }else{
                fecha_aviso = jsonArray.getJSONObject(i).getString("fecha_aviso");
            }
            String fecha_visita;
            if (jsonArray.getJSONObject(i).getString("fecha_visita").equals("null")){
                fecha_visita = "";
            }else{
                fecha_visita = jsonArray.getJSONObject(i).getString("fecha_visita");
            }
            String visita_duplicada;
            if (jsonArray.getJSONObject(i).getString("visita_duplicada").equals("null")){
                visita_duplicada = "";
            }else{
                visita_duplicada = jsonArray.getJSONObject(i).getString("visita_duplicada");
            }
            String fecha_reparacion;
            if (jsonArray.getJSONObject(i).getString("fecha_reparacion").equals("null")){
                fecha_reparacion = "";
            }else{
                fecha_reparacion = jsonArray.getJSONObject(i).getString("fecha_reparacion");
            }
            String num_parte;
            if (jsonArray.getJSONObject(i).getString("num_parte").equals("null")){
                num_parte = "";
            }else{
                num_parte = jsonArray.getJSONObject(i).getString("num_parte");
            }
            int fk_tipo;
            if (jsonArray.getJSONObject(i).getString("fk_tipo").equals("null")||jsonArray.getJSONObject(i).getString("fk_tipo").equals("")){
                fk_tipo = -1;
            }else{
                fk_tipo = jsonArray.getJSONObject(i).getInt("fk_tipo");
            }
            int fk_user_asignacion;
            if (jsonArray.getJSONObject(i).getString("fk_user_asignacion").equals("null")||jsonArray.getJSONObject(i).getString("fk_user_asignacion").equals("")){
                fk_user_asignacion = -1;
            }else{
                fk_user_asignacion = jsonArray.getJSONObject(i).getInt("fk_user_asignacion");
            }
            int fk_horario;
            if (jsonArray.getJSONObject(i).getString("fk_horario").equals("null")||jsonArray.getJSONObject(i).getString("fk_horario").equals("")){
                fk_horario = -1;
            }else{
                fk_horario = jsonArray.getJSONObject(i).getInt("fk_horario");
            }
            String descripcion_horario;
            if (jsonArray.getJSONObject(i).getString("descripcion_horario").equals("null")){
                descripcion_horario = "";
            }else{
                descripcion_horario = jsonArray.getJSONObject(i).getString("descripcion_horario");
            }
            String franja_horaria;
            if (jsonArray.getJSONObject(i).getString("franja_horaria").equals("null")){
                franja_horaria = "";
            }else{
                franja_horaria = jsonArray.getJSONObject(i).getString("franja_horaria");
            }
            int fk_franja_ip;
            if (jsonArray.getJSONObject(i).getString("fk_franja_ip").equals("null")||jsonArray.getJSONObject(i).getString("fk_franja_ip").equals("")){
                fk_franja_ip = -1;
            }else{
                fk_franja_ip = jsonArray.getJSONObject(i).getInt("fk_franja_ip");
            }
            int fk_estado;
            if (jsonArray.getJSONObject(i).getString("fk_estado").equals("null")||jsonArray.getJSONObject(i).getString("fk_estado").equals("")){
                fk_estado = -1;
            }else{
                fk_estado = jsonArray.getJSONObject(i).getInt("fk_estado");
            }
            String observaciones;
            if (jsonArray.getJSONObject(i).getString("observaciones").equals("null")){
                observaciones = "";
            }else{
                observaciones = jsonArray.getJSONObject(i).getString("observaciones");
            }
            String observacionesAsignacion;
            if (jsonArray.getJSONObject(i).getString("observacionesAsignacion").equals("null")){
                observacionesAsignacion = "";
            }else{
                observacionesAsignacion = jsonArray.getJSONObject(i).getString("observacionesAsignacion");
            }
            String confirmado;
            if (jsonArray.getJSONObject(i).getString("confirmado").equals("null")){
                confirmado = "";
            }else{
                confirmado = jsonArray.getJSONObject(i).getString("confirmado");
            }
            String imprimir;
            if (jsonArray.getJSONObject(i).getString("imprimir").equals("null")){
                imprimir = "";
            }else{
                imprimir = jsonArray.getJSONObject(i).getString("imprimir");
            }
            String fecha_factura;
            if (jsonArray.getJSONObject(i).getString("fecha_factura").equals("null")){
                fecha_factura = "";
            }else{
                fecha_factura = jsonArray.getJSONObject(i).getString("fecha_factura");
            }
            String num_factura;
            if (jsonArray.getJSONObject(i).getString("num_factura").equals("null")){
                num_factura = "";
            }else{
                num_factura = jsonArray.getJSONObject(i).getString("num_factura");
            }
            String fecha_factura_rectificativa;
            if (jsonArray.getJSONObject(i).getString("fecha_factura_rectificativa").equals("null")){
                fecha_factura_rectificativa = "";
            }else{
                fecha_factura_rectificativa = jsonArray.getJSONObject(i).getString("fecha_factura_rectificativa");
            }
            String num_factura_rectificativa;
            if (jsonArray.getJSONObject(i).getString("num_factura_rectificativa").equals("null")){
                num_factura_rectificativa = "";
            }else{
                num_factura_rectificativa = jsonArray.getJSONObject(i).getString("num_factura_rectificativa");
            }
            int fk_pend_fact;
            if (jsonArray.getJSONObject(i).getString("fk_pend_fact").equals("null")||jsonArray.getJSONObject(i).getString("fk_pend_fact").equals("")){
                fk_pend_fact = -1;
            }else{
                fk_pend_fact = jsonArray.getJSONObject(i).getInt("fk_pend_fact");
            }
            String num_orden_endesa;
            if (jsonArray.getJSONObject(i).getString("num_orden_endesa").equals("null")){
                num_orden_endesa = "";
            }else{
                num_orden_endesa = jsonArray.getJSONObject(i).getString("num_orden_endesa");
            }
            String fecha_maxima_endesa;
            if (jsonArray.getJSONObject(i).getString("fecha_maxima_endesa").equals("null")){
                fecha_maxima_endesa = "";
            }else{
                fecha_maxima_endesa = jsonArray.getJSONObject(i).getString("fecha_maxima_endesa");
            }
            int fk_estado_endesa;
            if (jsonArray.getJSONObject(i).getString("fk_estado_endesa").equals("null")||jsonArray.getJSONObject(i).getString("fk_estado_endesa").equals("")){
                fk_estado_endesa = -1;
            }else{
                fk_estado_endesa = jsonArray.getJSONObject(i).getInt("fk_estado_endesa");
            }
            String insistencia_endesa;
            if (jsonArray.getJSONObject(i).getString("insistencia_endesa").equals("null")){
                insistencia_endesa = "";
            }else{
                insistencia_endesa = jsonArray.getJSONObject(i).getString("insistencia_endesa");
            }
            String contrato_endesa;
            if (jsonArray.getJSONObject(i).getString("contrato_endesa").equals("null")){
                contrato_endesa = "";
            }else{
                contrato_endesa = jsonArray.getJSONObject(i).getString("contrato_endesa");
            }
            String producto_endesa;
            if (jsonArray.getJSONObject(i).getString("producto_endesa").equals("null")){
                producto_endesa = "";
            }else{
                producto_endesa = jsonArray.getJSONObject(i).getString("producto_endesa");
            }
            int fk_tipo_os;
            if (jsonArray.getJSONObject(i).getString("fk_tipo_os").equals("null")||jsonArray.getJSONObject(i).getString("fk_tipo_os").equals("")){
                fk_tipo_os = -1;
            }else{
                fk_tipo_os = jsonArray.getJSONObject(i).getInt("fk_tipo_os");
            }
            int fk_tipo_producto;
            if (jsonArray.getJSONObject(i).getString("fk_tipo_producto").equals("null")||jsonArray.getJSONObject(i).getString("fk_tipo_producto").equals("")){
                fk_tipo_producto = -1;
            }else{
                fk_tipo_producto = jsonArray.getJSONObject(i).getInt("fk_tipo_producto");
            }
            String pagado_endesa;
            if (jsonArray.getJSONObject(i).getString("pagado_endesa").equals("null")){
                pagado_endesa = "";
            }else{
                pagado_endesa = jsonArray.getJSONObject(i).getString("pagado_endesa");
            }
            String ciclo_liq_endesa;
            if (jsonArray.getJSONObject(i).getString("ciclo_liq_endesa").equals("null")){
                ciclo_liq_endesa = "";
            }else{
                ciclo_liq_endesa = jsonArray.getJSONObject(i).getString("ciclo_liq_endesa");
            }
            String importe_pago_endesa;
            if (jsonArray.getJSONObject(i).getString("importe_pago_endesa").equals("null")){
                importe_pago_endesa = "";
            }else{
                importe_pago_endesa = jsonArray.getJSONObject(i).getString("importe_pago_endesa");
            }
            String fecha_pagado_endesa;
            if (jsonArray.getJSONObject(i).getString("fecha_pagado_endesa").equals("null")){
                fecha_pagado_endesa = "";
            }else{
                fecha_pagado_endesa = jsonArray.getJSONObject(i).getString("fecha_pagado_endesa");
            }
            String pagado_operario;
            if (jsonArray.getJSONObject(i).getString("pagado_operario").equals("null")){
                pagado_operario = "";
            }else{
                pagado_operario = jsonArray.getJSONObject(i).getString("pagado_operario");
            }
            String fecha_anulado;
            if (jsonArray.getJSONObject(i).getString("fecha_anulado").equals("null")){
                fecha_anulado = "";
            }else{
                fecha_anulado = jsonArray.getJSONObject(i).getString("fecha_anulado");
            }
            String fecha_modificacion_tecnico;
            if (jsonArray.getJSONObject(i).getString("fecha_modificacion_tecnico").equals("null")){
                fecha_modificacion_tecnico = "";
            }else{
                fecha_modificacion_tecnico = jsonArray.getJSONObject(i).getString("fecha_modificacion_tecnico");
            }
            int fk_remoto_central;
            if (jsonArray.getJSONObject(i).getString("fk_remoto_central").equals("null")||jsonArray.getJSONObject(i).getString("fk_remoto_central").equals("")){
                fk_remoto_central = -1;
            }else{
                fk_remoto_central = jsonArray.getJSONObject(i).getInt("fk_remoto_central");
            }
            String fac_nombre;
            if (jsonArray.getJSONObject(i).getString("fac_nombre").equals("null")){
                fac_nombre = "";
            }else{
                fac_nombre = jsonArray.getJSONObject(i).getString("fac_nombre");
            }
            String fac_direccion;
            if (jsonArray.getJSONObject(i).getString("fac_direccion").equals("null")){
                fac_direccion = "";
            }else{
                fac_direccion = jsonArray.getJSONObject(i).getString("fac_direccion");
            }
            String fac_cp;
            if (jsonArray.getJSONObject(i).getString("fac_cp").equals("null")){
                fac_cp = "";
            }else{
                fac_cp = jsonArray.getJSONObject(i).getString("fac_cp");
            }
            String fac_poblacion;
            if (jsonArray.getJSONObject(i).getString("fac_poblacion").equals("null")){
                fac_poblacion = "";
            }else{
                fac_poblacion = jsonArray.getJSONObject(i).getString("fac_poblacion");
            }
            String fac_provincia;
            if (jsonArray.getJSONObject(i).getString("fac_provincia").equals("null")){
                fac_provincia = "";
            }else{
                fac_provincia = jsonArray.getJSONObject(i).getString("fac_provincia");
            }
            String fac_DNI;
            if (jsonArray.getJSONObject(i).getString("fac_DNI").equals("null")){
                fac_DNI = "";
            }else{
                fac_DNI = jsonArray.getJSONObject(i).getString("fac_DNI");
            }
            String fac_email;
            if (jsonArray.getJSONObject(i).getString("fac_email").equals("null")){
                fac_email = "";
            }else{
                fac_email = jsonArray.getJSONObject(i).getString("fac_email");
            }
            String fac_telefonos;
            if (jsonArray.getJSONObject(i).getString("fac_telefonos").equals("null")){
                fac_telefonos = "";
            }else{
                fac_telefonos = jsonArray.getJSONObject(i).getString("fac_telefonos");
            }
            String otros_sintomas;
            if (jsonArray.getJSONObject(i).getString("otros_sintomas").equals("null")){
                otros_sintomas = "";
            }else{
                otros_sintomas = jsonArray.getJSONObject(i).getString("otros_sintomas");
            }
            String fecha_baja = jsonArray.getJSONObject(i).getString("fecha_baja");
            String fac_baja_stock = jsonArray.getJSONObject(i).getString("fac_baja_stock");
            String estado_android = jsonArray.getJSONObject(i).getString("estado_android");
            int fk_tipo_urgencia;
            if (jsonArray.getJSONObject(i).getString("fk_tipo_urgencia").equals("null")||jsonArray.getJSONObject(i).getString("fk_tipo_urgencia").equals("")){
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
            if (jsonArray.getJSONObject(i).getString("fk_carga_archivo").equals("null")||jsonArray.getJSONObject(i).getString("fk_carga_archivo").equals("")){
                fk_carga_archivo = -1;
            }else{
                fk_carga_archivo = jsonArray.getJSONObject(i).getInt("fk_carga_archivo");
            }
            String orden = jsonArray.getJSONObject(i).getString("orden");
            String historico = jsonArray.getJSONObject(i).getString("historico");
            int fk_tipo_urgencia_factura;
            if (jsonArray.getJSONObject(i).getString("fk_tipo_urgencia_factura").equals("null")||jsonArray.getJSONObject(i).getString("fk_tipo_urgencia_factura").equals("null")){
                fk_tipo_urgencia_factura = -1;
            }else{
                fk_tipo_urgencia_factura = jsonArray.getJSONObject(i).getInt("fk_tipo_urgencia_factura");
            }
            String error_batch = jsonArray.getJSONObject(i).getString("error_batch");
            int fk_batch_actual;
            if (jsonArray.getJSONObject(i).getString("fk_batch_actual").equals("null")||jsonArray.getJSONObject(i).getString("fk_batch_actual").equals("null")){
                fk_batch_actual = -1;
            }else{
                fk_batch_actual = jsonArray.getJSONObject(i).getInt("fk_batch_actual");
            }
            int fk_efv;
            if (jsonArray.getJSONObject(i).getString("fk_efv").equals("null")||jsonArray.getJSONObject(i).getString("fk_efv").equals("")){
                fk_efv = -1;
            }else{
                fk_efv = jsonArray.getJSONObject(i).getInt("fk_efv");
            }
            String scoring = jsonArray.getJSONObject(i).getString("scoring");
            int fk_categoria_visita;
            if (jsonArray.getJSONObject(i).getString("fk_categoria_visita").equals("null")||jsonArray.getJSONObject(i).getString("fk_categoria_visita").equals("")){
                fk_categoria_visita = -1;
            }else{
                fk_categoria_visita = jsonArray.getJSONObject(i).getInt("fk_categoria_visita");
            }
            String contador_averias = jsonArray.getJSONObject(i).getString("contador_averias");
            JSONObject jsonObject1 = jsonArray.getJSONObject(i).getJSONArray("cliente").getJSONObject(0);
            int fk_empresa_usuario = jsonObject1.getInt("fk_empresa");
            String numero_usuario;
            if (jsonObject1.getString("numero_usuario").equals("null")){
                numero_usuario  ="";
            }else{
                numero_usuario = jsonObject1.getString("numero_usuario");
            }
            String nombre_usuario;
            if (jsonObject1.getString("nombre_usuario").equals("null")){
                nombre_usuario  ="";
            }else{
                nombre_usuario = jsonObject1.getString("nombre_usuario");
            }
            String dni_usuario;
            if (jsonObject1.getString("DNI").equals("null")){
                dni_usuario  ="";
            }else{
                dni_usuario = jsonObject1.getString("DNI");
            }
            String telefono1_usuario;
            if (jsonObject1.getString("telefono1").equals("null")||jsonObject1.getString("telefono1").equals("0")){
                telefono1_usuario  ="";
            }else{
                telefono1_usuario = jsonObject1.getString("telefono1");
            }
            String telefono2_usuario;
            if (jsonObject1.getString("telefono2").equals("null")||jsonObject1.getString("telefono1").equals("0")){
                telefono2_usuario  ="";
            }else{
                telefono2_usuario = jsonObject1.getString("telefono2");
            }
            String telefono3_usuario;
            if (jsonObject1.getString("telefono3").equals("null")||jsonObject1.getString("telefono1").equals("0")){
                telefono3_usuario  ="";
            }else{
                telefono3_usuario = jsonObject1.getString("telefono3");
            }
            String telefono4_usuario;
            if (jsonObject1.getString("telefono4").equals("null")||jsonObject1.getString("telefono1").equals("0")){
                telefono4_usuario  ="";
            }else{
                telefono4_usuario = jsonObject1.getString("telefono4");
            }
            String telefono5_usuario;
            if (jsonObject1.getString("otros_telefonos").equals("null")||jsonObject1.getString("telefono1").equals("0")){
                telefono5_usuario  ="";
            }else{
                telefono5_usuario = jsonObject1.getString("otros_telefonos");
            }
            String email_usuario;
            if (jsonObject1.getString("email").equals("null")){
                email_usuario  ="";
            }else{
                email_usuario = jsonObject1.getString("email");
            }
            String moroso_usuario;
            if (jsonObject1.getString("moroso").equals("null")){
                moroso_usuario  ="";
            }else{
                moroso_usuario = jsonObject1.getString("moroso");
            }
            String observaciones_usuario;
            if (jsonObject1.getString("observaciones").equals("null")){
                observaciones_usuario  ="";
            }else{
                observaciones_usuario = jsonObject1.getString("observaciones");
            }
            //////////////////////////////////////////////////////////////////////////////////////////////
            jsonObject1 = jsonArray.getJSONObject(i).getJSONArray("maquina").getJSONObject(0);
            String tipo_maquina;
            if (jsonObject1.getString("fk_tipo_caldera").equals("null")){
                tipo_maquina  ="";
            }else{
                tipo_maquina = jsonObject1.getString("fk_tipo_caldera");
            }
            String modelo_maquina;
            if (jsonObject1.getString("modelo").equals("null")){
                modelo_maquina  ="";
            }else{
                modelo_maquina = jsonObject1.getString("modelo");
            }
            String marca_maquina;
            if (jsonObject1.getString("fk_marca").equals("null")){
                marca_maquina  ="";
            }else{
                marca_maquina = jsonObject1.getString("fk_marca");
            }
            int uso_maquina;
            if (jsonObject1.getString("fk_uso").equals("null")||jsonObject1.getString("fk_uso").equals("")||jsonObject1.getString("fk_uso").equals("0")){
                uso_maquina = 3;
            }else{
                uso_maquina = jsonObject1.getInt("fk_uso");
                if (uso_maquina>3){
                    uso_maquina = 3;
                }
            }
            int potencia_maquina = 0;
            if (jsonObject1.getString("potencia").equals("null")||jsonObject1.getString("potencia").equals("")){
                potencia_maquina = 0;
            }else{
                switch (jsonObject1.getString("potencia")){
                    case "20":
                        potencia_maquina = 11;
                        break;
                    case "23":
                        potencia_maquina = 12;
                        break;
                    case "24":
                        potencia_maquina = 13;
                        break;
                    case "27":
                        potencia_maquina = 16;
                        break;
                    case "28":
                        potencia_maquina = 17;
                        break;
                    case "32":
                        potencia_maquina = 22;
                        break;
                    case "34":
                        potencia_maquina = 23;
                        break;
                    case "35":
                        potencia_maquina = 24;
                        break;

                }
            }
            String puesta_marcha_maquina;
            if (jsonObject1.getString("puesta_marcha").equals("null")||jsonObject1.getString("puesta_marcha").equals("")){
                puesta_marcha_maquina = "0000-00-00";
            }else{
                puesta_marcha_maquina = jsonObject1.getString("puesta_marcha");
            }

            int codigo_maquina;
            if (jsonObject1.getString("codigo_maquina").equals("null")){
                codigo_maquina=0;
            }else{
                codigo_maquina=jsonObject1.getInt("codigo_maquina");
            }
            String co_maquina;
            if (jsonObject1.getString("c0ppm").equals("null")||jsonObject1.getString("c0ppm").equals("")||jsonObject1.getString("c0ppm").equals("0")){
                co_maquina="";
            }else{
                co_maquina=jsonObject1.getString("c0ppm");
            }
            String temperatura_max_acs_maquina;
            if (jsonObject1.getString("tempMaxACS").equals("null")||jsonObject1.getString("tempMaxACS").equals("")||jsonObject1.getString("tempMaxACS").equals("0")){
                temperatura_max_acs_maquina="";
            }else{
                temperatura_max_acs_maquina=jsonObject1.getString("tempMaxACS");
            }
            String caudal_acs_maquina;
            if (jsonObject1.getString("caudalACS").equals("null")||jsonObject1.getString("caudalACS").equals("")||jsonObject1.getString("caudalACS").equals("0")){
                caudal_acs_maquina="";
            }else{
                caudal_acs_maquina=jsonObject1.getString("caudalACS");
            }
            String potencia_util_maquina;
            if (jsonObject1.getString("potenciaUtil").equals("null")||jsonObject1.getString("potenciaUtil").equals("")||jsonObject1.getString("potenciaUtil").equals("0")){
                potencia_util_maquina="";
            }else{
                potencia_util_maquina=jsonObject1.getString("potenciaUtil");
            }
            String temperatura_gases_combustion_maquina;
            if (jsonObject1.getString("tempGasCombustion").equals("null")||jsonObject1.getString("tempGasCombustion").equals("")||jsonObject1.getString("tempGasCombustion").equals("0")){
                temperatura_gases_combustion_maquina="";
            }else{
                temperatura_gases_combustion_maquina=jsonObject1.getString("tempGasCombustion");
            }
            String temperatura_ambiente_local_maquina;
            if (jsonObject1.getString("tempAmbLocal").equals("null")||jsonObject1.getString("tempAmbLocal").equals("")||jsonObject1.getString("tempAmbLocal").equals("0")){
                temperatura_ambiente_local_maquina="";
            }else{
                temperatura_ambiente_local_maquina=jsonObject1.getString("tempAmbLocal");
            }
            String temperatura_agua_generador_calor_entrada_maquina;
            if (jsonObject1.getString("tempAguaGeneradorCalorEntrada").equals("null")||jsonObject1.getString("tempAguaGeneradorCalorEntrada").equals("")||jsonObject1.getString("tempAguaGeneradorCalorEntrada").equals("0")){
                temperatura_agua_generador_calor_entrada_maquina="";
            }else{
                temperatura_agua_generador_calor_entrada_maquina=jsonObject1.getString("tempAguaGeneradorCalorEntrada");
            }
            String temperatura_agua_generador_calor_salida_maquina;
            if (jsonObject1.getString("tempAguaGeneradorCalorSalida").equals("null")||jsonObject1.getString("tempAguaGeneradorCalorSalida").equals("")||jsonObject1.getString("tempAguaGeneradorCalorSalida").equals("0")){
                temperatura_agua_generador_calor_salida_maquina="";
            }else{
                temperatura_agua_generador_calor_salida_maquina=jsonObject1.getString("tempAguaGeneradorCalorSalida");
            }

            if (MantenimientoDAO.newMantenimiento(context,id_mantenimiento, hash, cod_barras, fk_user_creador,
                    fk_tecnico, fk_usuario, fk_empresa_usuario, numero_usuario,
                    nombre_usuario, dni_usuario, telefono1_usuario,
                    telefono2_usuario, telefono3_usuario, telefono4_usuario,
                    telefono5_usuario, email_usuario, moroso_usuario,
                    observaciones_usuario, fk_direccion, direccion,
                    cod_postal, provincia, municipio, latitud,
                    longitud, fk_maquina, tipo_maquina, modelo_maquina,
                    marca_maquina, uso_maquina, potencia_maquina,
                    puesta_marcha_maquina, codigo_maquina, co_maquina,
                    temperatura_max_acs_maquina, caudal_acs_maquina,
                    potencia_util_maquina, temperatura_gases_combustion_maquina,
                    temperatura_ambiente_local_maquina,
                    temperatura_agua_generador_calor_entrada_maquina,
                    temperatura_agua_generador_calor_salida_maquina,
                    fecha_creacion,
                    fecha_aviso, fecha_visita, visita_duplicada,
                    fecha_reparacion, num_parte, fk_tipo, fk_user_asignacion,
                    fk_horario, descripcion_horario, franja_horaria,
                    fk_franja_ip, fk_estado, observaciones,
                    observacionesAsignacion, confirmado,
                    imprimir, fecha_factura, num_factura,
                    fecha_factura_rectificativa, num_factura_rectificativa,
                    fk_pend_fact, num_orden_endesa, fecha_maxima_endesa,
                    fk_estado_endesa, insistencia_endesa, contrato_endesa,
                    producto_endesa, fk_tipo_os, fk_tipo_producto, pagado_endesa,
                    ciclo_liq_endesa, importe_pago_endesa, fecha_pagado_endesa,
                    pagado_operario, fecha_anulado, fecha_modificacion_tecnico,
                    fk_remoto_central,  fac_nombre, fac_direccion, fac_cp,
                    fac_poblacion, fac_provincia, fac_DNI, fac_email,
                    fac_telefonos, otros_sintomas, fecha_baja, fac_baja_stock,
                    estado_android, fk_tipo_urgencia, fecha_cierre, num_lote,
                    bEnBatch, cod_visita, fecha_envio_carta, bCartaEnviada,
                    fecha_otro_dia, fecha_ausente_limite, fk_carga_archivo, orden,
                    historico, fk_tipo_urgencia_factura, error_batch, fk_batch_actual,
                    fk_efv, scoring, fk_categoria_visita, contador_averias)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            new GuardarTipoCalderaLogin(context,Json);
        }else{
            ((Login)context).sacarMensaje("error mantenimiento");
        }
    }
}
