package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import java.sql.SQLException;
import java.util.List;

public class MantenimientoDAO extends DBHelperMOS {
	static Dao<Mantenimiento, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMantenimientoDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMantenimiento(Context context,int id_mantenimiento, int fk_user_creador, int fk_tecnico, int fk_usuario,
										   int fk_empresa_usuario, String numero_usuario, String nombre_usuario, String dni_usuario,
										   String telefono1_usuario, String telefono2_usuario, String telefono3_usuario,
										   String telefono4_usuario, String telefono5_usuario, String email_usuario,
										   String moroso_usuario, String observaciones_usuario,
										   int fk_direccion,String direccion, String cod_postal, String provincia, String municipio,
										   int fk_maquina, String fecha_creacion, String fecha_aviso,
										   String fecha_visita, String visita_duplicada, String fecha_reparacion,
										   String num_parte, int fk_tipo, int fk_user_asignacion, int fk_horario,
										   String franja_horaria, int fk_franja_ip, int fk_estado, String observaciones,
										   String observacionesAsignacion, String confirmado, String imprimir,
										   String fecha_factura, String num_factura, String fecha_factura_rectificativa,
										   String num_factura_rectificativa, int fk_pend_fact, String num_orden_endesa,
										   String fecha_maxima_endesa, int fk_estado_endesa, String insistencia_endesa,
										   String contrato_endesa, String producto_endesa, int fk_tipo_os,
										   int fk_tipo_producto, String pagado_endesa, String ciclo_liq_endesa,
										   String importe_pago_endesa, String fecha_pagado_endesa, String pagado_operario,
										   String fecha_anulado, String fecha_modificacion_tecnico, int fk_remoto_central,
										   String fac_nombre, String fac_direccion, String fac_cp, String fac_poblacion,
										   String fac_provincia, String fac_DNI, String fac_email, String fac_telefonos,
										   String otros_sintomas, String fecha_baja, String fac_baja_stock, String estado_android,
										   int fk_tipo_urgencia, String fecha_cierre, String num_lote, String bEnBatch,
										   String cod_visita, String fecha_envio_carta, String bCartaEnviada,
										   String fecha_otro_dia, String fecha_ausente_limite, int fk_carga_archivo,
										   String orden, String historico, int fk_tipo_urgencia_factura,
										   String error_batch, int fk_batch_actual, int fk_efv, String scoring,
										   int fk_categoria_visita, String contador_averias) {
		Mantenimiento m = montarMantenimiento(id_mantenimiento, fk_user_creador, fk_tecnico, fk_usuario,
				fk_empresa_usuario, numero_usuario, nombre_usuario, dni_usuario,
				telefono1_usuario, telefono2_usuario, telefono3_usuario,
				telefono4_usuario, telefono5_usuario, email_usuario,
				moroso_usuario, observaciones_usuario,
				fk_direccion,direccion, cod_postal, provincia, municipio, fk_maquina, fecha_creacion, fecha_aviso,
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
				fk_categoria_visita, contador_averias);
		return crearMantenimiento(m,context);
	}
	public static boolean crearMantenimiento(Mantenimiento m,Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Mantenimiento montarMantenimiento(int id_mantenimiento, int fk_user_creador, int fk_tecnico, int fk_usuario,
													int fk_empresa_usuario, String numero_usuario, String nombre_usuario, String dni_usuario,
													String telefono1_usuario, String telefono2_usuario, String telefono3_usuario,
													String telefono4_usuario, String telefono5_usuario, String email_usuario,
													String moroso_usuario, String observaciones_usuario,
													int fk_direccion, String direccion, String cod_postal, String provincia, String municipio,
													int fk_maquina, String fecha_creacion, String fecha_aviso,
													String fecha_visita, String visita_duplicada, String fecha_reparacion,
													String num_parte, int fk_tipo, int fk_user_asignacion, int fk_horario,
													String franja_horaria, int fk_franja_ip, int fk_estado, String observaciones,
													String observacionesAsignacion, String confirmado, String imprimir,
													String fecha_factura, String num_factura, String fecha_factura_rectificativa,
													String num_factura_rectificativa, int fk_pend_fact, String num_orden_endesa,
													String fecha_maxima_endesa, int fk_estado_endesa, String insistencia_endesa,
													String contrato_endesa, String producto_endesa, int fk_tipo_os,
													int fk_tipo_producto, String pagado_endesa, String ciclo_liq_endesa,
													String importe_pago_endesa, String fecha_pagado_endesa, String pagado_operario,
													String fecha_anulado, String fecha_modificacion_tecnico, int fk_remoto_central,
													String fac_nombre, String fac_direccion, String fac_cp, String fac_poblacion,
													String fac_provincia, String fac_DNI, String fac_email, String fac_telefonos,
													String otros_sintomas, String fecha_baja, String fac_baja_stock, String estado_android,
													int fk_tipo_urgencia, String fecha_cierre, String num_lote, String bEnBatch,
													String cod_visita, String fecha_envio_carta, String bCartaEnviada,
													String fecha_otro_dia, String fecha_ausente_limite, int fk_carga_archivo,
													String orden, String historico, int fk_tipo_urgencia_factura,
													String error_batch, int fk_batch_actual, int fk_efv, String scoring,
													int fk_categoria_visita, String contador_averias) {
		Mantenimiento m =new Mantenimiento(id_mantenimiento, fk_user_creador, fk_tecnico, fk_usuario,
				fk_empresa_usuario, numero_usuario, nombre_usuario, dni_usuario,
				telefono1_usuario, telefono2_usuario, telefono3_usuario,
				telefono4_usuario, telefono5_usuario, email_usuario,
				moroso_usuario, observaciones_usuario,
				fk_direccion,direccion, cod_postal, provincia, municipio, fk_maquina, fecha_creacion, fecha_aviso,
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
				fk_categoria_visita, contador_averias);
		return m;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosMantenimientos(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Mantenimiento, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarMantenimientoPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Mantenimiento, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Mantenimiento> buscarTodosLosMantenimientos(Context context) throws SQLException {
		cargarDao(context);
		List<Mantenimiento> listadoMantenimiento= dao.queryForAll();
		if(listadoMantenimiento.isEmpty()) {
			return null;
		}else{
			return listadoMantenimiento;
		}
	}
	public static Mantenimiento buscarMantenimientoPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Mantenimiento> listadoMantenimiento= dao.queryForEq(Mantenimiento.ID_MANTENIMIENTO, id);
		if(listadoMantenimiento.isEmpty()) {
			return null;
		}else{
			return listadoMantenimiento.get(0);
		}
	}


	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


	/*public void actualizarCif (Context context, String dni, int id_usuario ) throws SQLException
	{
		cargarDao(context);
		UpdateBuilder<Tecnico, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Tecnico.ID_TECNICO,id_usuario);
		updateBuilder.updateColumnValue(Tecnico.DNI_CIF, dni);
		updateBuilder.update();
	}*/
}
