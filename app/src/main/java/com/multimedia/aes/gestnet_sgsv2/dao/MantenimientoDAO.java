package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
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

	public static boolean newMantenimiento(Context context,int id_mantenimiento,String hash, String base64 ,int fk_user_creador, int fk_tecnico, int fk_usuario,
										   int fk_empresa_usuario, String numero_usuario, String nombre_usuario, String dni_usuario,
										   String telefono1_usuario, String telefono2_usuario, String telefono3_usuario,
										   String telefono4_usuario, String telefono5_usuario, String email_usuario,
										   String moroso_usuario, String observaciones_usuario,
										   int fk_direccion,String direccion, String cod_postal, String provincia, String municipio,String latitud,String longitud,
										   int fk_maquina, String tipo_maquina, String modelo_maquina,String marca_maquina,
										   int uso_maquina,String puesta_marcha_maquina, String fecha_creacion, String fecha_aviso,
										   String fecha_visita, String visita_duplicada, String fecha_reparacion,
										   String num_parte, int fk_tipo, int fk_user_asignacion, int fk_horario,String descripcion_horario,
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
		Mantenimiento m = montarMantenimiento(id_mantenimiento, hash,base64, fk_user_creador, fk_tecnico, fk_usuario,
				fk_empresa_usuario, numero_usuario, nombre_usuario, dni_usuario,
				telefono1_usuario, telefono2_usuario, telefono3_usuario,
				telefono4_usuario, telefono5_usuario, email_usuario,
				moroso_usuario, observaciones_usuario,
				fk_direccion,direccion, cod_postal, provincia, municipio, latitud, longitud, fk_maquina,
				tipo_maquina, modelo_maquina, marca_maquina, uso_maquina,
				puesta_marcha_maquina,fecha_creacion, fecha_aviso,
				fecha_visita, visita_duplicada, fecha_reparacion,
				num_parte, fk_tipo, fk_user_asignacion, fk_horario, descripcion_horario,
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
	public static Mantenimiento montarMantenimiento(int id_mantenimiento,String hash, String base64, int fk_user_creador, int fk_tecnico, int fk_usuario,
													int fk_empresa_usuario, String numero_usuario, String nombre_usuario, String dni_usuario,
													String telefono1_usuario, String telefono2_usuario, String telefono3_usuario,
													String telefono4_usuario, String telefono5_usuario, String email_usuario,
													String moroso_usuario, String observaciones_usuario,
													int fk_direccion, String direccion, String cod_postal, String provincia, String municipio,String latitud,String longitud,
													int fk_maquina,String tipo_maquina, String modelo_maquina,String marca_maquina,
													int uso_maquina,String puesta_marcha_maquina,String fecha_creacion, String fecha_aviso,
													String fecha_visita, String visita_duplicada, String fecha_reparacion,
													String num_parte, int fk_tipo, int fk_user_asignacion, int fk_horario,String descripcion_horario,
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
		Mantenimiento m =new Mantenimiento(id_mantenimiento, hash,base64, fk_user_creador, fk_tecnico, fk_usuario,
				fk_empresa_usuario, numero_usuario, nombre_usuario, dni_usuario,
				telefono1_usuario, telefono2_usuario, telefono3_usuario,
				telefono4_usuario, telefono5_usuario, email_usuario,
				moroso_usuario, observaciones_usuario,
				fk_direccion,direccion, cod_postal, provincia, municipio, latitud, longitud, fk_maquina,
				tipo_maquina, modelo_maquina, marca_maquina, uso_maquina,
				puesta_marcha_maquina,fecha_creacion, fecha_aviso,
				fecha_visita, visita_duplicada, fecha_reparacion,
				num_parte, fk_tipo, fk_user_asignacion, fk_horario,descripcion_horario,
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
	public static List<Mantenimiento> buscarMantenimientosPorFechas(Context context, String fecha)throws SQLException {
		cargarDao(context);
		List<Mantenimiento> listadoMantenimiento= dao.queryBuilder().where().between(Mantenimiento.FECHA_VISITA,fecha+" 00:00:00",fecha+" 23:59:59").query();
		if(listadoMantenimiento.isEmpty()) {
			return null;
		}else{
			return listadoMantenimiento;
		}
	}
	public static List<Mantenimiento> buscarMantenimientosPorFecha(Context context, String fecha)throws SQLException {
		cargarDao(context);
		List<Mantenimiento> listadoMantenimiento= dao.queryForAll();
		List<Mantenimiento> mantenimientos = dao.queryForAll();
		mantenimientos.clear();
		for (int i = 0; i < listadoMantenimiento.size(); i++) {
			String fec = listadoMantenimiento.get(i).getFecha_visita();
			if (fec.split(" ").equals(fecha)){
				mantenimientos.add(listadoMantenimiento.get(i));
			}
		}
		if(mantenimientos.isEmpty()) {
			return null;
		}else{
			return mantenimientos;
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


	public static void actualizarMantenimiento(Context context, Mantenimiento mantenimiento ) throws SQLException
	{
		int id = mantenimiento.getId_mantenimiento();
		String hash = mantenimiento.getHash();
		int fk_user_creador = mantenimiento.getFk_user_creador();
		int fk_tecnico = mantenimiento.getFk_tecnico();
		int fk_usuario = mantenimiento.getFk_usuario();
		int fk_empresa_usuario = mantenimiento.getFk_empresa_usuario();
		String numero_usuario = mantenimiento.getNumero_usuario();
		String nombre_usuario = mantenimiento.getNombre_usuario();
		String dni_usuario = mantenimiento.getDni_usuario();
		String telefono1_usuario = mantenimiento.getTelefono1_usuario();
		String telefono2_usuario = mantenimiento.getTelefono2_usuario();
		String telefono3_usuario = mantenimiento.getTelefono3_usuario();
		String telefono4_usuario = mantenimiento.getTelefono4_usuario();
		String telefono5_usuario = mantenimiento.getTelefono5_usuario();
		String email_usuario = mantenimiento.getEmail_usuario();
		String moroso_usuario = mantenimiento.getMoroso_usuario();
		String observaciones_usuario = mantenimiento.getObservaciones_usuario();
		int fk_direccion = mantenimiento.getFk_direccion();
		String direccion = mantenimiento.getDireccion();
		String cod_postal = mantenimiento.getCod_postal();
		String provincia = mantenimiento.getProvincia();
		String municipio = mantenimiento.getMunicipio();
		int fk_maquina = mantenimiento.getFk_maquina();
		String tipo_maquina = mantenimiento.getTipo_maquina();
		String modelo_maquina = mantenimiento.getModelo_maquina();
		String marca_maquina = mantenimiento.getMarca_maquina();
		int uso_maquina = mantenimiento.getUso_maquina();
		String fecha_creacion = mantenimiento.getFecha_creacion();
		String fecha_aviso = mantenimiento.getFecha_aviso();
		String fecha_visita = mantenimiento.getFecha_visita();
		String visita_duplicada = mantenimiento.getVisita_duplicada();
		String fecha_reparacion = mantenimiento.getFecha_reparacion();
		String num_parte = mantenimiento.getNum_parte();
		int fk_tipo = mantenimiento.getFk_tipo();
		int fk_user_asignacion = mantenimiento.getFk_user_asignacion();
		int fk_horario = mantenimiento.getFk_horario();
		String franja_horaria = mantenimiento.getFranja_horaria();
		int fk_franja_ip = mantenimiento.getFk_franja_ip();
		int fk_estado = mantenimiento.getFk_estado();
		String observaciones = mantenimiento.getObservaciones();
		String observacionesAsignacion = mantenimiento.getObservacionesAsignacion();
		String confirmado = mantenimiento.getConfirmado();
		String imprimir = mantenimiento.getImprimir();
		String fecha_factura = mantenimiento.getFecha_factura();
		String num_factura = mantenimiento.getNum_factura();
		String fecha_factura_rectificativa = mantenimiento.getFecha_factura_rectificativa();
		String num_factura_rectificativa = mantenimiento.getNum_factura_rectificativa();
		int fk_pend_fact = mantenimiento.getFk_pend_fact();
		String num_orden_endesa = mantenimiento.getNum_orden_endesa();
		String fecha_maxima_endesa = mantenimiento.getFecha_maxima_endesa();
		int fk_estado_endesa = mantenimiento.getFk_estado_endesa();
		String insistencia_endesa = mantenimiento.getInsistencia_endesa();
		String contrato_endesa = mantenimiento.getContrato_endesa();
		String producto_endesa = mantenimiento.getProducto_endesa();
		int fk_tipo_os = mantenimiento.getFk_tipo_os();
		int fk_tipo_producto = mantenimiento.getFk_tipo_producto();
		String pagado_endesa = mantenimiento.getPagado_endesa();
		String ciclo_liq_endesa = mantenimiento.getCiclo_liq_endesa();
		String importe_pago_endesa = mantenimiento.getImporte_pago_endesa();
		String fecha_pagado_endesa = mantenimiento.getFecha_pagado_endesa();
		String pagado_operario = mantenimiento.getPagado_operario();
		String fecha_anulado = mantenimiento.getFecha_anulado();
		String fecha_modificacion_tecnico = mantenimiento.getFecha_modificacion_tecnico();
		int fk_remoto_central = mantenimiento.getFk_remoto_central();
		String fac_nombre = mantenimiento.getFac_nombre();
		String fac_direccion = mantenimiento.getFac_direccion();
		String fac_cp = mantenimiento.getFac_cp();
		String fac_poblacion = mantenimiento.getFac_poblacion();
		String fac_provincia = mantenimiento.getFac_provincia();
		String fac_DNI = mantenimiento.getFac_DNI();
		String fac_email = mantenimiento.getFac_email();
		String fac_telefonos = mantenimiento.getFac_telefonos();
		String otros_sintomas = mantenimiento.getOtros_sintomas();
		String fecha_baja = mantenimiento.getFecha_baja();
		String fac_baja_stock = mantenimiento.getFac_baja_stock();
		String estado_android = mantenimiento.getEstado_android();
		int fk_tipo_urgencia = mantenimiento.getFk_tipo_urgencia();
		String fecha_cierre = mantenimiento.getFecha_cierre();
		String num_lote = mantenimiento.getNum_lote();
		String bEnBatch = mantenimiento.getbEnBatch();
		String cod_visita = mantenimiento.getCod_visita();
		String fecha_envio_carta = mantenimiento.getFecha_envio_carta();
		String bCartaEnviada = mantenimiento.getbCartaEnviada();
		String fecha_otro_dia = mantenimiento.getFecha_otro_dia();
		String fecha_ausente_limite = mantenimiento.getHash();
		int fk_carga_archivo = mantenimiento.getFk_carga_archivo();
		String orden = mantenimiento.getOrden();
		String historico = mantenimiento.getHistorico();
		int fk_tipo_urgencia_factura = mantenimiento.getFk_tipo_urgencia_factura();
		String error_batch = mantenimiento.getError_batch();
		int fk_batch_actual = mantenimiento.getFk_batch_actual();
		int fk_efv = mantenimiento.getFk_efv();
		String scoring = mantenimiento.getScoring();
		int fk_categoria_visita = mantenimiento.getFk_categoria_visita();
		String contador_averias = mantenimiento.getContador_averias();
		cargarDao(context);
		UpdateBuilder<Mantenimiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO,id);
		updateBuilder.updateColumnValue(Mantenimiento.HASH,hash );
		updateBuilder.updateColumnValue(Mantenimiento.FK_USER_CREADOR, fk_user_creador);
		updateBuilder.updateColumnValue(Mantenimiento.FK_TECNICO, fk_tecnico);
		updateBuilder.updateColumnValue(Mantenimiento.FK_USUARIO, fk_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.FK_EMPRESA_USUARIO, fk_empresa_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.NUMERO_USUARIO, numero_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.NOMBRE_USUARIO, nombre_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.DNI_USUARIO, dni_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO1_USUARIO, telefono1_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO2_USUARIO, telefono2_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO3_USUARIO, telefono3_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO4_USUARIO, telefono4_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO5_USUARIO, telefono5_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.EMAIL_USUARIO, email_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.MOROSO_USUARIO, moroso_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.OBSERVACIONES_USUARIO, observaciones_usuario);
		updateBuilder.updateColumnValue(Mantenimiento.FK_DIRECCION, fk_direccion);
		updateBuilder.updateColumnValue(Mantenimiento.DIRECCION, direccion);
		updateBuilder.updateColumnValue(Mantenimiento.COD_POSTAL, cod_postal);
		updateBuilder.updateColumnValue(Mantenimiento.PROVINCIA, provincia);
		updateBuilder.updateColumnValue(Mantenimiento.MUNICIPIO, municipio);
		updateBuilder.updateColumnValue(Mantenimiento.FK_MAQUINA, fk_maquina);
		updateBuilder.updateColumnValue(Mantenimiento.TIPO_MAQUINA, tipo_maquina);
		updateBuilder.updateColumnValue(Mantenimiento.MODELO_MAQUINA, modelo_maquina);
		updateBuilder.updateColumnValue(Mantenimiento.MARCA_MAQUINA, marca_maquina);
		updateBuilder.updateColumnValue(Mantenimiento.USO_MAQUINA,uso_maquina );
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_CREACION, fecha_creacion);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_AVISO, fecha_aviso);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_VISITA, fecha_visita);
		updateBuilder.updateColumnValue(Mantenimiento.VISITA_DUPLICADA, visita_duplicada);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_REPARACION, fecha_reparacion);
		updateBuilder.updateColumnValue(Mantenimiento.NUM_PARTE, num_parte);
		updateBuilder.updateColumnValue(Mantenimiento.FK_TIPO, fk_tipo);
		updateBuilder.updateColumnValue(Mantenimiento.FK_USER_ASIGNACION, fk_user_asignacion);
		updateBuilder.updateColumnValue(Mantenimiento.FK_HORARIO, fk_horario);
		updateBuilder.updateColumnValue(Mantenimiento.FRANJA_HORARIA, franja_horaria);
		updateBuilder.updateColumnValue(Mantenimiento.FK_FRANJA_IP, fk_franja_ip);
		updateBuilder.updateColumnValue(Mantenimiento.FK_ESTADO, fk_estado);
		updateBuilder.updateColumnValue(Mantenimiento.OBSERVACIONES, observaciones);
		updateBuilder.updateColumnValue(Mantenimiento.OBSERVACIONESASIGNACION, observacionesAsignacion);
		updateBuilder.updateColumnValue(Mantenimiento.CONFIRMADO, confirmado);
		updateBuilder.updateColumnValue(Mantenimiento.IMPRIMIR, imprimir);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_FACTURA, fecha_factura);
		updateBuilder.updateColumnValue(Mantenimiento.NUM_FACTURA, num_factura);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_FACTURA_RECTIFICATIVA, fecha_factura_rectificativa);
		updateBuilder.updateColumnValue(Mantenimiento.NUM_FACTURA_RECTIFICATIVA, num_factura_rectificativa);
		updateBuilder.updateColumnValue(Mantenimiento.FK_PEND_FACT, fk_pend_fact);
		updateBuilder.updateColumnValue(Mantenimiento.NUM_ORDEN_ENDESA, num_orden_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_MAXIMA_ENDESA, fecha_maxima_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.FK_ESTADO_ENDESA, fk_estado_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.INSISTENCIA_ENDESA, insistencia_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.CONTRATO_ENDESA, contrato_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.PRODUCTO_ENDESA, producto_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.FK_TIPO_OS, fk_tipo_os);
		updateBuilder.updateColumnValue(Mantenimiento.FK_TIPO_PRODUCTO, fk_tipo_producto);
		updateBuilder.updateColumnValue(Mantenimiento.PAGADO_ENDESA, pagado_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.CICLO_LIQ_ENDESA, ciclo_liq_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.IMPORTE_PAGO_ENDESA, importe_pago_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_PAGADO_ENDESA, fecha_pagado_endesa);
		updateBuilder.updateColumnValue(Mantenimiento.PAGADO_OPERARIO, pagado_operario);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_ANULADO, fecha_anulado);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_MODIFICACION_TECNICO, fecha_modificacion_tecnico);
		updateBuilder.updateColumnValue(Mantenimiento.FK_REMOTO_CENTRAL, fk_remoto_central);
		updateBuilder.updateColumnValue(Mantenimiento.FAC_NOMBRE, fac_nombre);
		updateBuilder.updateColumnValue(Mantenimiento.FAC_DIRECCION, fac_direccion);
		updateBuilder.updateColumnValue(Mantenimiento.FAC_CP, fac_cp);
		updateBuilder.updateColumnValue(Mantenimiento.FAC_POBLACION, fac_poblacion);
		updateBuilder.updateColumnValue(Mantenimiento.FAC_PROVINCIA, fac_provincia);
		updateBuilder.updateColumnValue(Mantenimiento.FAC_DNI, fac_DNI);
		updateBuilder.updateColumnValue(Mantenimiento.FAC_EMAIL, fac_email);
		updateBuilder.updateColumnValue(Mantenimiento.FAC_TELEFONOS, fac_telefonos);
		updateBuilder.updateColumnValue(Mantenimiento.OTROS_SINTOMAS, otros_sintomas);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_BAJA, fecha_baja);
		updateBuilder.updateColumnValue(Mantenimiento.FAC_BAJA_STOCK, fac_baja_stock);
		updateBuilder.updateColumnValue(Mantenimiento.ESTADO_ANDROID, estado_android);
		updateBuilder.updateColumnValue(Mantenimiento.FK_TIPO_URGENCIA, fk_tipo_urgencia);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_CIERRE, fecha_cierre);
		updateBuilder.updateColumnValue(Mantenimiento.NUM_LOTE, num_lote);
		updateBuilder.updateColumnValue(Mantenimiento.BENBATCH, bEnBatch);
		updateBuilder.updateColumnValue(Mantenimiento.COD_VISITA, cod_visita);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_ENVIO_CARTA, fecha_envio_carta);
		updateBuilder.updateColumnValue(Mantenimiento.BCARTAENVIADA, bCartaEnviada);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_OTRO_DIA, fecha_otro_dia);
		updateBuilder.updateColumnValue(Mantenimiento.FECHA_AUSENTE_LIMITE, fecha_ausente_limite);
		updateBuilder.updateColumnValue(Mantenimiento.FK_CARGA_ARCHIVO, fk_carga_archivo);
		updateBuilder.updateColumnValue(Mantenimiento.ORDEN, orden);
		updateBuilder.updateColumnValue(Mantenimiento.HISTORICO, historico);
		updateBuilder.updateColumnValue(Mantenimiento.FK_TIPO_URGENCIA_FACTURA, fk_tipo_urgencia_factura);
		updateBuilder.updateColumnValue(Mantenimiento.ERROR_BATCH, error_batch);
		updateBuilder.updateColumnValue(Mantenimiento.FK_BATCH_ACTUAL, fk_batch_actual);
		updateBuilder.updateColumnValue(Mantenimiento.FK_EFV, fk_efv);
		updateBuilder.updateColumnValue(Mantenimiento.SCORING, scoring);
		updateBuilder.updateColumnValue(Mantenimiento.FK_CATEGORIA_VISITA, fk_categoria_visita);
		updateBuilder.updateColumnValue(Mantenimiento.CONTADOR_AVERIAS, contador_averias);
		updateBuilder.update();
	}

	public static void actualizarEstadoAndroid(Context context, int i, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Mantenimiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO,id_mantenimiento);
		updateBuilder.updateColumnValue(Mantenimiento.ESTADO_ANDROID,i);
		updateBuilder.update();
	}
	public static void actualizarNombreUsuario(Context context, String nombre, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Mantenimiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO,id_mantenimiento);
		updateBuilder.updateColumnValue(Mantenimiento.NOMBRE_USUARIO,nombre);
		updateBuilder.update();
	}
	public static void actualizarTelefono1(Context context, String tlf1, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Mantenimiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO,id_mantenimiento);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO1_USUARIO,tlf1);
		updateBuilder.update();
	}
	public static void actualizarTelefono2(Context context, String tlf2, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Mantenimiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO,id_mantenimiento);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO2_USUARIO,tlf2);
		updateBuilder.update();
	}
	public static void actualizarTelefono3(Context context, String tlf3, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Mantenimiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO,id_mantenimiento);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO3_USUARIO,tlf3);
		updateBuilder.update();
	}
	public static void actualizarTelefono4(Context context, String tlf4, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Mantenimiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO,id_mantenimiento);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO4_USUARIO,tlf4);
		updateBuilder.update();
	}
	public static void actualizarTelefono5(Context context, String tlf5, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Mantenimiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO,id_mantenimiento);
		updateBuilder.updateColumnValue(Mantenimiento.TELEFONO5_USUARIO,tlf5);
		updateBuilder.update();
	}
	public static void actualizarObservacionesCliente(Context context, String obs, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Mantenimiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Mantenimiento.ID_MANTENIMIENTO,id_mantenimiento);
		updateBuilder.updateColumnValue(Mantenimiento.OBSERVACIONES_USUARIO,obs);
		updateBuilder.update();
	}
}
