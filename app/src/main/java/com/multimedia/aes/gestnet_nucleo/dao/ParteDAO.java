package com.multimedia.aes.gestnet_nucleo.dao;

/**
 * Created by acp on 12/07/2017.
 */



import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;

import java.sql.SQLException;
import java.util.List;


public class ParteDAO extends DBHelperMOS{

    static Dao<Parte, Integer> dao;

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getParteDAO();
    }


//__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newParte(Context context,int id_parte, int fk_user_creador, int fk_compania, int fk_tecnico, int fk_usuario,
                                   int fk_direccion, int fk_maquina, String fecha_creacion, String fecha_aviso,
                                   String fecha_visita, boolean visita_duplicada, String fecha_reparacion, int num_parte,
                                   int fk_tipo, int fk_user_asignacion, int fk_horario, String horario, String duracion,
                                   String firmante, String sobre, int franja_horaria, int fk_estado, int fk_estado_interno,
                                   String observaciones, String observacionesasignacion, int confirmado, String entregado_por,
                                   String recogido_por, String comentarios_entrega, int fk_fabricante, String aprobado_fabricante,
                                   boolean imprimir, String fecha_factura, String num_factura, String fecha_factura_rectificativa,
                                   String num_factura_rectificativa, int fk_pend_fact, String num_orden_endesa,
                                   String fecha_maxima_endesa, int fk_estado_endesa, int insistencia_endesa,
                                   String contrato_endesa, String producto_endesa, int fk_tipo_os0, int fk_tipo_producto,
                                   boolean pagado_endesa, String ciclo_liq_endesa, double importe_pago_endesa,
                                   String fecha_pagado_endesa, boolean pagado_operario, String fecha_anulado,
                                   String fecha_modificacion_tecnico, int fk_remoto_central, String fac_nombre,
                                   String fac_direccion, String fac_cp, String fac_poblacion, String fac_provincia,
                                   String fac_dni, String fac_email, String fac_telefonos, String otros_sintomas,
                                   String fecha_baja, boolean fac_baja_stock, boolean estado_android, boolean urgencias,
                                   String lote, boolean validar, boolean liquidado_a_proveedor, int fk_instalacion,
                                   int fk_emergencia, String motivo_cambio_fecha_maxima, boolean btodoslosequipos,
                                   int fk_tipo_instalacion, boolean parte_finalizado_android, String comercializadora,
                                   String persona_contacto, String tel_contacto, String cnae, int fk_compania_parte,
                                   String fecha_cierre, String num_presupuesto, String defectos, int fk_periocidad,
                                   double franquicia, String inspeccion_visual, String otros_mataux, boolean binspeccionvisual,
                                   boolean botrosmataux, String tipo_via, String via, String numero_direccion,
                                   String escalera_direccion, String piso_direccion, String puerta_direccion,
                                   String cp_direccion, String municipio_direccion, String provincia_direccion,
                                   String latitud_direccion, String longitud_direccion, String nombre_cliente,
                                   String dni_cliente, String telefono1_cliente, String telefono2_cliente,
                                   String telefono3_cliente, String telefono4_cliente, String email_cliente,
                                   String observaciones_cliente,String user_creador,String tipo) {
        Parte p = montarParte(id_parte, fk_user_creador, fk_compania, fk_tecnico, fk_usuario,
                fk_direccion, fk_maquina, fecha_creacion, fecha_aviso,
                fecha_visita, visita_duplicada, fecha_reparacion, num_parte,
                fk_tipo, fk_user_asignacion, fk_horario, horario, duracion,
                firmante, sobre, franja_horaria, fk_estado, fk_estado_interno,
                observaciones, observacionesasignacion, confirmado, entregado_por,
                recogido_por, comentarios_entrega, fk_fabricante, aprobado_fabricante,
                imprimir, fecha_factura, num_factura, fecha_factura_rectificativa,
                num_factura_rectificativa, fk_pend_fact, num_orden_endesa,
                fecha_maxima_endesa, fk_estado_endesa, insistencia_endesa,
                contrato_endesa, producto_endesa, fk_tipo_os0, fk_tipo_producto,
                pagado_endesa, ciclo_liq_endesa, importe_pago_endesa,
                fecha_pagado_endesa, pagado_operario, fecha_anulado,
                fecha_modificacion_tecnico, fk_remoto_central, fac_nombre,
                fac_direccion, fac_cp, fac_poblacion, fac_provincia,
                fac_dni, fac_email, fac_telefonos, otros_sintomas,
                fecha_baja, fac_baja_stock, estado_android, urgencias,
                lote, validar, liquidado_a_proveedor, fk_instalacion,
                fk_emergencia, motivo_cambio_fecha_maxima, btodoslosequipos,
                fk_tipo_instalacion, parte_finalizado_android, comercializadora,
                persona_contacto, tel_contacto, cnae, fk_compania_parte,
                fecha_cierre, num_presupuesto, defectos, fk_periocidad,
                franquicia, inspeccion_visual, otros_mataux, binspeccionvisual,
                botrosmataux, tipo_via, via, numero_direccion,
                escalera_direccion, piso_direccion, puerta_direccion,
                cp_direccion, municipio_direccion, provincia_direccion,
                latitud_direccion, longitud_direccion, nombre_cliente,
                dni_cliente, telefono1_cliente, telefono2_cliente,
                telefono3_cliente, telefono4_cliente, email_cliente,
                observaciones_cliente,user_creador,tipo);
        return crearParte(p,context);
    }
    public static boolean crearParte(Parte p,Context context) {
        try {
            cargarDao(context);
            dao.create(p);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Parte montarParte(int id_parte, int fk_user_creador, int fk_compania, int fk_tecnico, int fk_usuario,
                                    int fk_direccion, int fk_maquina, String fecha_creacion, String fecha_aviso,
                                    String fecha_visita, boolean visita_duplicada, String fecha_reparacion, int num_parte,
                                    int fk_tipo, int fk_user_asignacion, int fk_horario, String horario, String duracion,
                                    String firmante, String sobre, int franja_horaria, int fk_estado, int fk_estado_interno,
                                    String observaciones, String observacionesasignacion, int confirmado, String entregado_por,
                                    String recogido_por, String comentarios_entrega, int fk_fabricante, String aprobado_fabricante,
                                    boolean imprimir, String fecha_factura, String num_factura, String fecha_factura_rectificativa,
                                    String num_factura_rectificativa, int fk_pend_fact, String num_orden_endesa,
                                    String fecha_maxima_endesa, int fk_estado_endesa, int insistencia_endesa,
                                    String contrato_endesa, String producto_endesa, int fk_tipo_os0, int fk_tipo_producto,
                                    boolean pagado_endesa, String ciclo_liq_endesa, double importe_pago_endesa,
                                    String fecha_pagado_endesa, boolean pagado_operario, String fecha_anulado,
                                    String fecha_modificacion_tecnico, int fk_remoto_central, String fac_nombre,
                                    String fac_direccion, String fac_cp, String fac_poblacion, String fac_provincia,
                                    String fac_dni, String fac_email, String fac_telefonos, String otros_sintomas,
                                    String fecha_baja, boolean fac_baja_stock, boolean estado_android, boolean urgencias,
                                    String lote, boolean validar, boolean liquidado_a_proveedor, int fk_instalacion,
                                    int fk_emergencia, String motivo_cambio_fecha_maxima, boolean btodoslosequipos,
                                    int fk_tipo_instalacion, boolean parte_finalizado_android, String comercializadora,
                                    String persona_contacto, String tel_contacto, String cnae, int fk_compania_parte,
                                    String fecha_cierre, String num_presupuesto, String defectos, int fk_periocidad,
                                    double franquicia, String inspeccion_visual, String otros_mataux, boolean binspeccionvisual,
                                    boolean botrosmataux, String tipo_via, String via, String numero_direccion,
                                    String escalera_direccion, String piso_direccion, String puerta_direccion,
                                    String cp_direccion, String municipio_direccion, String provincia_direccion,
                                    String latitud_direccion, String longitud_direccion, String nombre_cliente,
                                    String dni_cliente, String telefono1_cliente, String telefono2_cliente,
                                    String telefono3_cliente, String telefono4_cliente, String email_cliente,
                                    String observaciones_cliente,String user_creador,String tipo) {
        Parte p =new Parte(id_parte, fk_user_creador, fk_compania, fk_tecnico, fk_usuario,
                fk_direccion, fk_maquina, fecha_creacion, fecha_aviso,
                fecha_visita, visita_duplicada, fecha_reparacion, num_parte,
                fk_tipo, fk_user_asignacion, fk_horario, horario, duracion,
                firmante, sobre, franja_horaria, fk_estado, fk_estado_interno,
                observaciones, observacionesasignacion, confirmado, entregado_por,
                recogido_por, comentarios_entrega, fk_fabricante, aprobado_fabricante,
                imprimir, fecha_factura, num_factura, fecha_factura_rectificativa,
                num_factura_rectificativa, fk_pend_fact, num_orden_endesa,
                fecha_maxima_endesa, fk_estado_endesa, insistencia_endesa,
                contrato_endesa, producto_endesa, fk_tipo_os0, fk_tipo_producto,
                pagado_endesa, ciclo_liq_endesa, importe_pago_endesa,
                fecha_pagado_endesa, pagado_operario, fecha_anulado,
                fecha_modificacion_tecnico, fk_remoto_central, fac_nombre,
                fac_direccion, fac_cp, fac_poblacion, fac_provincia,
                fac_dni, fac_email, fac_telefonos, otros_sintomas,
                fecha_baja, fac_baja_stock, estado_android, urgencias,
                lote, validar, liquidado_a_proveedor, fk_instalacion,
                fk_emergencia, motivo_cambio_fecha_maxima, btodoslosequipos,
                fk_tipo_instalacion, parte_finalizado_android, comercializadora,
                persona_contacto, tel_contacto, cnae, fk_compania_parte,
                fecha_cierre, num_presupuesto, defectos, fk_periocidad,
                franquicia, inspeccion_visual, otros_mataux, binspeccionvisual,
                botrosmataux, tipo_via, via, numero_direccion,
                escalera_direccion, piso_direccion, puerta_direccion,
                cp_direccion, municipio_direccion, provincia_direccion,
                latitud_direccion, longitud_direccion, nombre_cliente,
                dni_cliente, telefono1_cliente, telefono2_cliente,
                telefono3_cliente, telefono4_cliente, email_cliente,
                observaciones_cliente,user_creador,tipo);
        return p;
    }


    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodosLosPartes(Context context) throws SQLException {
        cargarDao(context);
        DeleteBuilder<Parte, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }
    public static void borrarPartePorID(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<Parte, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(Parte.ID_PARTE, id);
        deleteBuilder.delete();
    }


    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<Parte> buscarTodosLosPartes(Context context) throws SQLException {
        cargarDao(context);
        List<Parte> listadoPartes= dao.queryForAll();
        if(listadoPartes.isEmpty()) {
            return null;
        }else{
            return listadoPartes;
        }
    }
    public static Parte buscarPartePorId(Context context, int id) throws SQLException {
        cargarDao(context);
        List<Parte> listadoPartes= dao.queryForEq(Parte.ID_PARTE, id);
        if(listadoPartes.isEmpty()) {
            return null;
        }else{
            return listadoPartes.get(0);
        }
    }


    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


    public static void actualizarParte(Context context, Parte parte ) throws SQLException {
        int id = parte.getId_parte();
        int fk_user_creador=parte.getFk_user_creador();
        int fk_compania=parte.getFk_compania();
        int fk_tecnico=parte.getFk_tecnico();
        int fk_usuario=parte.getFk_usuario();
        int fk_direccion=parte.getFk_direccion();
        int fk_maquina=parte.getFk_maquina();
        String fecha_creacion=parte.getFecha_creacion();
        String fecha_aviso=parte.getFecha_aviso();
        String fecha_visita=parte.getFecha_visita();
        boolean visita_duplicada=parte.isVisita_duplicada();
        String fecha_reparacion=parte.getFecha_reparacion();
        int num_parte=parte.getNum_parte();
        int fk_tipo=parte.getFk_tipo();
        int fk_user_asignacion=parte.getFk_user_asignacion();
        int fk_horario=parte.getFk_horario();
        String horario=parte.getHorario();
        String  duracion=parte.getDuracion();
        String firmante=parte.getFirmante();
        String sobre=parte.getSobre();
        int franja_horaria=parte.getFranja_horaria();
        int fk_estado=parte.getFk_estado();
        int fk_estado_interno=parte.getFk_estado_interno();
        String observaciones=parte.getObservaciones();
        String observacionesasignacion=parte.getObservacionesasignacion();
        int confirmado=parte.getConfirmado();
        String entregado_por=parte.getEntregado_por();
        String recogido_por=parte.getRecogido_por();
        String comentarios_entrega=parte.getComentarios_entrega();
        int fk_fabricante=parte.getFk_fabricante();
        String aprobado_fabricante=parte.getAprobado_fabricante();
        boolean imprimir=parte.isImprimir();
        String fecha_factura=parte.getFecha_factura();
        String num_factura=parte.getNum_factura();
        String fecha_factura_rectificativa=parte.getFecha_factura_rectificativa();
        String num_factura_rectificativa=parte.getNum_factura_rectificativa();
        int fk_pend_fact=parte.getFk_pend_fact();
        String num_orden_endesa=parte.getNum_orden_endesa();
        String fecha_maxima_endesa=parte.getFecha_maxima_endesa();
        int fk_estado_endesa=parte.getFk_estado_endesa();
        int insistencia_endesa=parte.getInsistencia_endesa();
        String contrato_endesa=parte.getContrato_endesa();
        String producto_endesa=parte.getProducto_endesa();
        int fk_tipo_os=parte.getFk_tipo_os0();
        int fk_tipo_producto=parte.getFk_tipo_producto();
        boolean pagado_endesa=parte.isPagado_endesa();
        String ciclo_liq_endesa=parte.getCiclo_liq_endesa();
        double importe_pago_endesa=parte.getImporte_pago_endesa();
        String fecha_pagado_endesa=parte.getFecha_pagado_endesa();
        boolean pagado_operario=parte.isPagado_operario();
        String fecha_anulado=parte.getFecha_anulado();
        String fecha_modificacion_tecnico=parte.getFecha_modificacion_tecnico();
        int fk_remoto_central=parte.getFk_remoto_central();
        String fac_nombre=parte.getFac_nombre();
        String fac_direccion=parte.getFac_direccion();
        String fac_cp=parte.getFac_cp();
        String fac_poblacion=parte.getFac_poblacion();
        String fac_provincia=parte.getFac_provincia();
        String fac_dni=parte.getFac_dni();
        String fac_email=parte.getFac_email();
        String fac_telefonos=parte.getFac_telefonos();
        String otros_sintomas=parte.getOtros_sintomas();
        String fecha_baja=parte.getFecha_baja();
        boolean fac_baja_stock=parte.isFac_baja_stock();
        boolean estado_android=parte.isEstado_android();
        boolean urgencias=parte.isUrgencias();
        String lote=parte.getLote();
        boolean validar=parte.isValidar();
        boolean liquidado_a_proveedor=parte.isLiquidado_a_proveedor();
        int fk_instalacion=parte.getFk_instalacion();
        int fk_emergencia=parte.getFk_emergencia();
        String motivo_cambio_fecha_maxima=parte.getMotivo_cambio_fecha_maxima();
        boolean btodoslosequipos=parte.isBtodoslosequipos();
        int fk_tipo_instalacion=parte.getFk_tipo_instalacion();
        boolean parte_finalizado_android=parte.isParte_finalizado_android();
        String comercializadora=parte.getComercializadora();
        String persona_contacto=parte.getPersona_contacto();
        String tel_contacto=parte.getTel_contacto();
        String cnae=parte.getCnae();
        int fk_compania_parte=parte.getFk_compania_parte();
        String fecha_cierre=parte.getFecha_cierre();
        String num_presupuesto=parte.getNum_presupuesto();
        String defectos=parte.getDefectos();
        int fk_periocidad=parte.getFk_periocidad();
        double franquicia=parte.getFranquicia();
        String inspeccion_visual=parte.getInspeccion_visual();
        String otros_mataux=parte.getOtros_mataux();
        boolean binspeccionvisual=parte.isBinspeccionvisual();
        boolean botrosmataux=parte.isBotrosmataux();
        String user_creador=parte.getUser_creador();
        String tipo=parte.getTipo();
        cargarDao(context);
        UpdateBuilder<Parte, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(parte.ID_PARTE,id);
        updateBuilder.updateColumnValue(parte.FK_USER_CREADOR, fk_user_creador);
        updateBuilder.updateColumnValue(parte.FK_COMPANIA, fk_compania);
        updateBuilder.updateColumnValue(parte.FK_TECNICO, fk_tecnico);
        updateBuilder.updateColumnValue(parte.FK_USUARIO, fk_usuario);
        updateBuilder.updateColumnValue(parte.FK_DIRECCION, fk_direccion);
        updateBuilder.updateColumnValue(parte.FK_MAQUINA, fk_maquina);
        updateBuilder.updateColumnValue(parte.FECHA_CREACION, fecha_creacion);
        updateBuilder.updateColumnValue(parte.FECHA_AVISO, fecha_aviso);
        updateBuilder.updateColumnValue(parte.FECHA_VISITA, fecha_visita);
        updateBuilder.updateColumnValue(parte.VISITA_DUPLICADA, visita_duplicada);
        updateBuilder.updateColumnValue(parte.FECHA_REPARACION, fecha_reparacion);
        updateBuilder.updateColumnValue(parte.NUM_PARTE, num_parte);
        updateBuilder.updateColumnValue(parte.FK_TIPO, fk_tipo);
        updateBuilder.updateColumnValue(parte.FK_USER_ASIGNACION, fk_user_asignacion);
        updateBuilder.updateColumnValue(parte.FK_HORARIO, fk_horario);
        updateBuilder.updateColumnValue(parte.HORARIO, horario);
        updateBuilder.updateColumnValue(parte.DURACION, duracion);
        updateBuilder.updateColumnValue(parte.FIRMANTE, firmante);
        updateBuilder.updateColumnValue(parte.SOBRE, sobre);
        updateBuilder.updateColumnValue(parte.FRANJA_HORARIA, franja_horaria);
        updateBuilder.updateColumnValue(parte.FK_ESTADO, fk_estado);
        updateBuilder.updateColumnValue(parte.FK_ESTADO_INTERNO, fk_estado_interno);
        updateBuilder.updateColumnValue(parte.OBSERVACIONES, observaciones);
        updateBuilder.updateColumnValue(parte.OBSERVACIONESASIGNACION, observacionesasignacion);
        updateBuilder.updateColumnValue(parte.CONFIRMADO, confirmado);
        updateBuilder.updateColumnValue(parte.ENTREGADO_POR, entregado_por);
        updateBuilder.updateColumnValue(parte.RECOGIDO_POR, recogido_por);
        updateBuilder.updateColumnValue(parte.COMENTARIOS_ENTREGA, comentarios_entrega);
        updateBuilder.updateColumnValue(parte.FK_FABRICANTE, fk_fabricante);
        updateBuilder.updateColumnValue(parte.APROBADO_FABRICANTE, aprobado_fabricante);
        updateBuilder.updateColumnValue(parte.IMPRIMIR, imprimir);
        updateBuilder.updateColumnValue(parte.FECHA_FACTURA, fecha_factura);
        updateBuilder.updateColumnValue(parte.NUM_FACTURA, num_factura);
        updateBuilder.updateColumnValue(parte.FECHA_FACTURA_RECTIFICATIVA, fecha_factura_rectificativa);
        updateBuilder.updateColumnValue(parte.NUM_FACTURA_RECTIFICATIVA, num_factura_rectificativa);
        updateBuilder.updateColumnValue(parte.FK_PEND_FACT, fk_pend_fact);
        updateBuilder.updateColumnValue(parte.NUM_ORDEN_ENDESA, num_orden_endesa);
        updateBuilder.updateColumnValue(parte.FECHA_MAXIMA_ENDESA, fecha_maxima_endesa);
        updateBuilder.updateColumnValue(parte.FK_ESTADO_ENDESA, fk_estado_endesa);
        updateBuilder.updateColumnValue(parte.INSISTENCIA_ENDESA, insistencia_endesa);
        updateBuilder.updateColumnValue(parte.CONTRATO_ENDESA, contrato_endesa);
        updateBuilder.updateColumnValue(parte.PRODUCTO_ENDESA, producto_endesa);
        updateBuilder.updateColumnValue(parte.FK_TIPO_OS0, fk_tipo_os);
        updateBuilder.updateColumnValue(parte.FK_TIPO_PRODUCTO, fk_tipo_producto);
        updateBuilder.updateColumnValue(parte.PAGADO_ENDESA, pagado_endesa);
        updateBuilder.updateColumnValue(parte.CICLO_LIQ_ENDESA, ciclo_liq_endesa);
        updateBuilder.updateColumnValue(parte.IMPORTE_PAGO_ENDESA, importe_pago_endesa);
        updateBuilder.updateColumnValue(parte.FECHA_PAGADO_ENDESA, fecha_pagado_endesa);
        updateBuilder.updateColumnValue(parte.PAGADO_OPERARIO, pagado_operario);
        updateBuilder.updateColumnValue(parte.FECHA_ANULADO, fecha_anulado);
        updateBuilder.updateColumnValue(parte.FECHA_MODIFICACION_TECNICO, fecha_modificacion_tecnico);
        updateBuilder.updateColumnValue(parte.FK_REMOTO_CENTRAL, fk_remoto_central);
        updateBuilder.updateColumnValue(parte.FAC_NOMBRE, fac_nombre);
        updateBuilder.updateColumnValue(parte.FAC_DIRECCION, fac_direccion);
        updateBuilder.updateColumnValue(parte.FAC_CP, fac_cp);
        updateBuilder.updateColumnValue(parte.FAC_DIRECCION, fac_direccion);
        updateBuilder.updateColumnValue(parte.FAC_POBLACION, fac_poblacion);
        updateBuilder.updateColumnValue(parte.FAC_PROVINCIA, fac_provincia);
        updateBuilder.updateColumnValue(parte.FAC_DNI, fac_dni);
        updateBuilder.updateColumnValue(parte.FAC_EMAIL, fac_email);
        updateBuilder.updateColumnValue(parte.FAC_TELEFONOS, fac_telefonos);
        updateBuilder.updateColumnValue(parte.OTROS_SINTOMAS, otros_sintomas);
        updateBuilder.updateColumnValue(parte.FECHA_BAJA, fecha_baja);
        updateBuilder.updateColumnValue(parte.FAC_BAJA_STOCK, fac_baja_stock);
        updateBuilder.updateColumnValue(parte.ESTADO_ANDROID, estado_android);
        updateBuilder.updateColumnValue(parte.URGENCIAS, urgencias);
        updateBuilder.updateColumnValue(parte.LOTE, lote);
        updateBuilder.updateColumnValue(parte.VALIDAR, validar);
        updateBuilder.updateColumnValue(parte.LIQUIDADO_A_PROVEEDOR, liquidado_a_proveedor);
        updateBuilder.updateColumnValue(parte.FK_INSTALACION, fk_instalacion);
        updateBuilder.updateColumnValue(parte.FK_EMERGENCIA, fk_emergencia);
        updateBuilder.updateColumnValue(parte.MOTIVO_CAMBIO_FECHA_MAXIMA, motivo_cambio_fecha_maxima);
        updateBuilder.updateColumnValue(parte.BTODOSLOSEQUIPOS, btodoslosequipos);
        updateBuilder.updateColumnValue(parte.FK_TIPO_INSTALACION, fk_tipo_instalacion);
        updateBuilder.updateColumnValue(parte.PARTE_FINALIZADO_ANDROID, parte_finalizado_android);
        updateBuilder.updateColumnValue(parte.COMERCIALIZADORA, comercializadora);
        updateBuilder.updateColumnValue(parte.PERSONA_CONTACTO, persona_contacto);
        updateBuilder.updateColumnValue(parte.TEL_CONTACTO, tel_contacto);
        updateBuilder.updateColumnValue(parte.CNAE, cnae);
        updateBuilder.updateColumnValue(parte.FK_COMPANIA_PARTE, fk_compania_parte);
        updateBuilder.updateColumnValue(parte.FECHA_CIERRE, fecha_cierre);
        updateBuilder.updateColumnValue(parte.NUM_PRESUPUESTO, num_presupuesto);
        updateBuilder.updateColumnValue(parte.DEFECTOS, defectos);
        updateBuilder.updateColumnValue(parte.FK_PERIOCIDAD, fk_periocidad);
        updateBuilder.updateColumnValue(parte.FRANQUICIA, franquicia);
        updateBuilder.updateColumnValue(parte.INSPECCION_VISUAL, inspeccion_visual);
        updateBuilder.updateColumnValue(parte.OTROS_MATAUX, otros_mataux);
        updateBuilder.updateColumnValue(parte.BINSPECCIONVISUAL, binspeccionvisual);
        updateBuilder.updateColumnValue(parte.BOTROSMATAUX, botrosmataux);
        updateBuilder.updateColumnValue(parte.USER_CREADOR, user_creador);
        updateBuilder.updateColumnValue(parte.TIPO, tipo);



        updateBuilder.update();
    }
    public static void actualizarParte(Context context, int id_parte, int fk_user_creador, int fk_compania, int fk_tecnico, int fk_usuario,
                                       int fk_direccion, int fk_maquina, String fecha_creacion, String fecha_aviso,
                                       String fecha_visita, boolean visita_duplicada, String fecha_reparacion, int num_parte,
                                       int fk_tipo, int fk_user_asignacion, int fk_horario, String horario, String duracion,
                                       String firmante, String sobre, int franja_horaria, int fk_estado, int fk_estado_interno,
                                       String observaciones, String observacionesasignacion, int confirmado, String entregado_por,
                                       String recogido_por, String comentarios_entrega, int fk_fabricante, String aprobado_fabricante,
                                       boolean imprimir, String fecha_factura, String num_factura, String fecha_factura_rectificativa,
                                       String num_factura_rectificativa, int fk_pend_fact, String num_orden_endesa,
                                       String fecha_maxima_endesa, int fk_estado_endesa, int insistencia_endesa,
                                       String contrato_endesa, String producto_endesa, int fk_tipo_os, int fk_tipo_producto,
                                       boolean pagado_endesa, String ciclo_liq_endesa, double importe_pago_endesa,
                                       String fecha_pagado_endesa, boolean pagado_operario, String fecha_anulado,
                                       String fecha_modificacion_tecnico, int fk_remoto_central, String fac_nombre,
                                       String fac_direccion, String fac_cp, String fac_poblacion, String fac_provincia,
                                       String fac_dni, String fac_email, String fac_telefonos, String otros_sintomas,
                                       String fecha_baja, boolean fac_baja_stock, boolean estado_android, boolean urgencias,
                                       String lote, boolean validar, boolean liquidado_a_proveedor, int fk_instalacion,
                                       int fk_emergencia, String motivo_cambio_fecha_maxima, boolean btodoslosequipos,
                                       int fk_tipo_instalacion, boolean parte_finalizado_android, String comercializadora,
                                       String persona_contacto, String tel_contacto, String cnae, int fk_compania_parte,
                                       String fecha_cierre, String num_presupuesto, String defectos, int fk_periocidad,
                                       double franquicia, String inspeccion_visual, String otros_mataux, boolean binspeccionvisual,
                                       boolean botrosmataux, String tipo_via, String via, String numero_direccion,
                                       String escalera_direccion, String piso_direccion, String puerta_direccion,
                                       String cp_direccion, String municipio_direccion, String provincia_direccion,
                                       String latitud_direccion, String longitud_direccion, String nombre_cliente,
                                       String dni_cliente, String telefono1_cliente, String telefono2_cliente,
                                       String telefono3_cliente, String telefono4_cliente, String email_cliente,
                                       String observaciones_cliente,String user_creador,String tipo) throws SQLException
    {

        cargarDao(context);
        UpdateBuilder<Parte, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Parte.ID_PARTE,id_parte);
        updateBuilder.updateColumnValue(Parte.FK_USER_CREADOR, fk_user_creador);
        updateBuilder.updateColumnValue(Parte.FK_COMPANIA, fk_compania);
        updateBuilder.updateColumnValue(Parte.FK_TECNICO, fk_tecnico);
        updateBuilder.updateColumnValue(Parte.FK_USUARIO, fk_usuario);
        updateBuilder.updateColumnValue(Parte.FK_DIRECCION, fk_direccion);
        updateBuilder.updateColumnValue(Parte.FK_MAQUINA, fk_maquina);
        updateBuilder.updateColumnValue(Parte.FECHA_CREACION, fecha_creacion);
        updateBuilder.updateColumnValue(Parte.FECHA_AVISO, fecha_aviso);
        updateBuilder.updateColumnValue(Parte.FECHA_VISITA, fecha_visita);
        updateBuilder.updateColumnValue(Parte.VISITA_DUPLICADA, visita_duplicada);
        updateBuilder.updateColumnValue(Parte.FECHA_REPARACION, fecha_reparacion);
        updateBuilder.updateColumnValue(Parte.NUM_PARTE, num_parte);
        updateBuilder.updateColumnValue(Parte.FK_TIPO, fk_tipo);
        updateBuilder.updateColumnValue(Parte.FK_USER_ASIGNACION, fk_user_asignacion);
        updateBuilder.updateColumnValue(Parte.FK_HORARIO, fk_horario);
        updateBuilder.updateColumnValue(Parte.HORARIO, horario);
        updateBuilder.updateColumnValue(Parte.DURACION, duracion);
        updateBuilder.updateColumnValue(Parte.FIRMANTE, firmante);
        updateBuilder.updateColumnValue(Parte.SOBRE, sobre);
        updateBuilder.updateColumnValue(Parte.FRANJA_HORARIA, franja_horaria);
        updateBuilder.updateColumnValue(Parte.FK_ESTADO, fk_estado);
        updateBuilder.updateColumnValue(Parte.FK_ESTADO_INTERNO, fk_estado_interno);
        updateBuilder.updateColumnValue(Parte.OBSERVACIONES, observaciones);
        updateBuilder.updateColumnValue(Parte.OBSERVACIONESASIGNACION, observacionesasignacion);
        updateBuilder.updateColumnValue(Parte.CONFIRMADO, confirmado);
        updateBuilder.updateColumnValue(Parte.ENTREGADO_POR, entregado_por);
        updateBuilder.updateColumnValue(Parte.RECOGIDO_POR, recogido_por);
        updateBuilder.updateColumnValue(Parte.COMENTARIOS_ENTREGA, comentarios_entrega);
        updateBuilder.updateColumnValue(Parte.FK_FABRICANTE, fk_fabricante);
        updateBuilder.updateColumnValue(Parte.APROBADO_FABRICANTE, aprobado_fabricante);
        updateBuilder.updateColumnValue(Parte.IMPRIMIR, imprimir);
        updateBuilder.updateColumnValue(Parte.FECHA_FACTURA, fecha_factura);
        updateBuilder.updateColumnValue(Parte.NUM_FACTURA, num_factura);
        updateBuilder.updateColumnValue(Parte.FECHA_FACTURA_RECTIFICATIVA, fecha_factura_rectificativa);
        updateBuilder.updateColumnValue(Parte.NUM_FACTURA_RECTIFICATIVA, num_factura_rectificativa);
        updateBuilder.updateColumnValue(Parte.FK_PEND_FACT, fk_pend_fact);
        updateBuilder.updateColumnValue(Parte.NUM_ORDEN_ENDESA, num_orden_endesa);
        updateBuilder.updateColumnValue(Parte.FECHA_MAXIMA_ENDESA, fecha_maxima_endesa);
        updateBuilder.updateColumnValue(Parte.FK_ESTADO_ENDESA, fk_estado_endesa);
        updateBuilder.updateColumnValue(Parte.INSISTENCIA_ENDESA, insistencia_endesa);
        updateBuilder.updateColumnValue(Parte.CONTRATO_ENDESA, contrato_endesa);
        updateBuilder.updateColumnValue(Parte.PRODUCTO_ENDESA, producto_endesa);
        updateBuilder.updateColumnValue(Parte.FK_TIPO_OS0, fk_tipo_os);
        updateBuilder.updateColumnValue(Parte.FK_TIPO_PRODUCTO, fk_tipo_producto);
        updateBuilder.updateColumnValue(Parte.PAGADO_ENDESA, pagado_endesa);
        updateBuilder.updateColumnValue(Parte.CICLO_LIQ_ENDESA, ciclo_liq_endesa);
        updateBuilder.updateColumnValue(Parte.IMPORTE_PAGO_ENDESA, importe_pago_endesa);
        updateBuilder.updateColumnValue(Parte.FECHA_PAGADO_ENDESA, fecha_pagado_endesa);
        updateBuilder.updateColumnValue(Parte.PAGADO_OPERARIO, pagado_operario);
        updateBuilder.updateColumnValue(Parte.FECHA_ANULADO, fecha_anulado);
        updateBuilder.updateColumnValue(Parte.FECHA_MODIFICACION_TECNICO, fecha_modificacion_tecnico);
        updateBuilder.updateColumnValue(Parte.FK_REMOTO_CENTRAL, fk_remoto_central);
        updateBuilder.updateColumnValue(Parte.FAC_NOMBRE, fac_nombre);
        updateBuilder.updateColumnValue(Parte.FAC_DIRECCION, fac_direccion);
        updateBuilder.updateColumnValue(Parte.FAC_CP, fac_cp);
        updateBuilder.updateColumnValue(Parte.FAC_DIRECCION, fac_direccion);
        updateBuilder.updateColumnValue(Parte.FAC_POBLACION, fac_poblacion);
        updateBuilder.updateColumnValue(Parte.FAC_PROVINCIA, fac_provincia);
        updateBuilder.updateColumnValue(Parte.FAC_DNI, fac_dni);
        updateBuilder.updateColumnValue(Parte.FAC_EMAIL, fac_email);
        updateBuilder.updateColumnValue(Parte.FAC_TELEFONOS, fac_telefonos);
        updateBuilder.updateColumnValue(Parte.OTROS_SINTOMAS, otros_sintomas);
        updateBuilder.updateColumnValue(Parte.FECHA_BAJA, fecha_baja);
        updateBuilder.updateColumnValue(Parte.FAC_BAJA_STOCK, fac_baja_stock);
        updateBuilder.updateColumnValue(Parte.ESTADO_ANDROID, estado_android);
        updateBuilder.updateColumnValue(Parte.URGENCIAS, urgencias);
        updateBuilder.updateColumnValue(Parte.LOTE, lote);
        updateBuilder.updateColumnValue(Parte.VALIDAR, validar);
        updateBuilder.updateColumnValue(Parte.LIQUIDADO_A_PROVEEDOR, liquidado_a_proveedor);
        updateBuilder.updateColumnValue(Parte.FK_INSTALACION, fk_instalacion);
        updateBuilder.updateColumnValue(Parte.FK_EMERGENCIA, fk_emergencia);
        updateBuilder.updateColumnValue(Parte.MOTIVO_CAMBIO_FECHA_MAXIMA, motivo_cambio_fecha_maxima);
        updateBuilder.updateColumnValue(Parte.BTODOSLOSEQUIPOS, btodoslosequipos);
        updateBuilder.updateColumnValue(Parte.FK_TIPO_INSTALACION, fk_tipo_instalacion);
        updateBuilder.updateColumnValue(Parte.PARTE_FINALIZADO_ANDROID, parte_finalizado_android);
        updateBuilder.updateColumnValue(Parte.COMERCIALIZADORA, comercializadora);
        updateBuilder.updateColumnValue(Parte.PERSONA_CONTACTO, persona_contacto);
        updateBuilder.updateColumnValue(Parte.TEL_CONTACTO, tel_contacto);
        updateBuilder.updateColumnValue(Parte.CNAE, cnae);
        updateBuilder.updateColumnValue(Parte.FK_COMPANIA_PARTE, fk_compania_parte);
        updateBuilder.updateColumnValue(Parte.FECHA_CIERRE, fecha_cierre);
        updateBuilder.updateColumnValue(Parte.NUM_PRESUPUESTO, num_presupuesto);
        updateBuilder.updateColumnValue(Parte.DEFECTOS, defectos);
        updateBuilder.updateColumnValue(Parte.FK_PERIOCIDAD, fk_periocidad);
        updateBuilder.updateColumnValue(Parte.FRANQUICIA, franquicia);
        updateBuilder.updateColumnValue(Parte.INSPECCION_VISUAL, inspeccion_visual);
        updateBuilder.updateColumnValue(Parte.OTROS_MATAUX, otros_mataux);
        updateBuilder.updateColumnValue(Parte.BINSPECCIONVISUAL, binspeccionvisual);
        updateBuilder.updateColumnValue(Parte.BOTROSMATAUX, botrosmataux);
        updateBuilder.updateColumnValue(Parte.TIPO_VIA, tipo_via);
        updateBuilder.updateColumnValue(Parte.VIA, via);
        updateBuilder.updateColumnValue(Parte.NUMERO_DIRECCION, numero_direccion);
        updateBuilder.updateColumnValue(Parte.ESCALERA_DIRECCION, escalera_direccion);
        updateBuilder.updateColumnValue(Parte.PISO_DIRECCION, piso_direccion);
        updateBuilder.updateColumnValue(Parte.PUERTA_DIRECCION, puerta_direccion);
        updateBuilder.updateColumnValue(Parte.CP_DIRECCION, cp_direccion);
        updateBuilder.updateColumnValue(Parte.MUNICIPIO_DIRECCION, municipio_direccion);
        updateBuilder.updateColumnValue(Parte.PROVINCIA_DIRECCION, provincia_direccion);
        updateBuilder.updateColumnValue(Parte.LATITUD_DIRECCION, latitud_direccion);
        updateBuilder.updateColumnValue(Parte.LONGITUD_DIRECCION, longitud_direccion);
        updateBuilder.updateColumnValue(Parte.NOMBRE_CLIENTE, nombre_cliente);
        updateBuilder.updateColumnValue(Parte.DNI_CLIENTE, dni_cliente);
        updateBuilder.updateColumnValue(Parte.TELEFONO1_CLIENTE, telefono1_cliente);
        updateBuilder.updateColumnValue(Parte.TELEFONO2_CLIENTE, telefono2_cliente);
        updateBuilder.updateColumnValue(Parte.TELEFONO3_CLIENTE, telefono3_cliente);
        updateBuilder.updateColumnValue(Parte.TELEFONO4_CLIENTE, telefono4_cliente);
        updateBuilder.updateColumnValue(Parte.EMAIL_CLIENTE, email_cliente);
        updateBuilder.updateColumnValue(Parte.OBSERVACIONES_CLIENTE, observaciones_cliente);
        updateBuilder.updateColumnValue(Parte.USER_CREADOR, user_creador);
        updateBuilder.updateColumnValue(Parte.TIPO, tipo);



        updateBuilder.update();
    }



}
