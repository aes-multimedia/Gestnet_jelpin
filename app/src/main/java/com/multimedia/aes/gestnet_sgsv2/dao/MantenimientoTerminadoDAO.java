package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

import java.sql.SQLException;
import java.util.List;

public class MantenimientoTerminadoDAO extends DBHelperMOS {
	static Dao<MantenimientoTerminado, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMantenimientoTerminadoDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMantenimientoTerminado(Context context, MantenimientoTerminado m){
		return crearMantenimientoTerminado(m,context);
	}
	public static boolean newMantenimientoTerminado(Context context,int id_mantenimiento_terminado, int fk_parte, String codigo_barras,
													int fk_estado_visita, int fk_tipo_visita, int fk_subtipo_visita, String observaciones_tecnico,
													int contador_interno, String codigo_interno, int reparacion,
													int fk_tipo_reparacion, String fecha_reparacion, int fk_tiempo_mano_obra,
													String coste_materiales, String coste_mano_obra, String coste_mano_obra_adicional,
													String codigo_barras_reparacion, int fk_tipo_maquina, int fk_marca_maquina,
													String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
													String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
													String temperatura_max_acs, String caudal_acs, String potencia_util,
													String temperatura_gases_combustion, String temperatura_ambiente_local,
													String temperatura_agua_generador_calor_entrada,
													String temperatura_agua_generador_calor_salida,
													int limpieza_quemadores_caldera, int revision_vaso_expansion,
													int regulacion_aparatos, int comprobar_estanqueidad_cierre_quemadores_caldera,
													int revision_calderas_contadores, int verificacion_circuito_hidraulico_calefaccion,
													int estanqueidad_conexion_aparatos, int estanqueidad_conducto_evacuacion_irg,
													int comprobacion_niveles_agua, int tipo_conducto_evacuacion,
													int revision_estado_aislamiento_termico, int analisis_productos_combustion,
													int caudal_acs_calculo_potencia, int revision_sistema_control,boolean enviado) {
		MantenimientoTerminado m = montarMantenimientoTerminado(id_mantenimiento_terminado, fk_parte, codigo_barras,
																fk_estado_visita, fk_tipo_visita, fk_subtipo_visita, observaciones_tecnico,
																contador_interno, codigo_interno, reparacion,
																fk_tipo_reparacion, fecha_reparacion, fk_tiempo_mano_obra,
																coste_materiales, coste_mano_obra, coste_mano_obra_adicional,
																codigo_barras_reparacion, fk_tipo_maquina, fk_marca_maquina,
																modelo_maquina, fk_potencia_maquina, fk_uso_maquina,
																puesta_marcha_maquina, codigo_maquina, c0_maquina,
																temperatura_max_acs, caudal_acs, potencia_util,
																temperatura_gases_combustion, temperatura_ambiente_local,
																temperatura_agua_generador_calor_entrada,
																temperatura_agua_generador_calor_salida,
																limpieza_quemadores_caldera, revision_vaso_expansion,
																regulacion_aparatos, comprobar_estanqueidad_cierre_quemadores_caldera,
																revision_calderas_contadores, verificacion_circuito_hidraulico_calefaccion,
																estanqueidad_conexion_aparatos, estanqueidad_conducto_evacuacion_irg,
																comprobacion_niveles_agua, tipo_conducto_evacuacion,
																revision_estado_aislamiento_termico, analisis_productos_combustion,
																caudal_acs_calculo_potencia, revision_sistema_control,enviado);
		return crearMantenimientoTerminado(m,context);
	}
	public static boolean crearMantenimientoTerminado(MantenimientoTerminado m,Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static MantenimientoTerminado montarMantenimientoTerminado(int id_mantenimiento_terminado, int fk_parte, String codigo_barras,
																	  int fk_estado_visita, int fk_tipo_visita, int fk_subtipo_visita, String observaciones_tecnico,
																	  int contador_interno, String codigo_interno, int reparacion,
																	  int fk_tipo_reparacion, String fecha_reparacion, int fk_tiempo_mano_obra,
																	  String coste_materiales, String coste_mano_obra, String coste_mano_obra_adicional,
																	  String codigo_barras_reparacion, int fk_tipo_maquina, int fk_marca_maquina,
																	  String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
																	  String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
																	  String temperatura_max_acs, String caudal_acs, String potencia_util,
																	  String temperatura_gases_combustion, String temperatura_ambiente_local,
																	  String temperatura_agua_generador_calor_entrada,
																	  String temperatura_agua_generador_calor_salida,
																	  int limpieza_quemadores_caldera, int revision_vaso_expansion,
																	  int regulacion_aparatos, int comprobar_estanqueidad_cierre_quemadores_caldera,
																	  int revision_calderas_contadores, int verificacion_circuito_hidraulico_calefaccion,
																	  int estanqueidad_conexion_aparatos, int estanqueidad_conducto_evacuacion_irg,
																	  int comprobacion_niveles_agua, int tipo_conducto_evacuacion,
																	  int revision_estado_aislamiento_termico, int analisis_productos_combustion,
																	  int caudal_acs_calculo_potencia, int revision_sistema_control,boolean enviado) {
		MantenimientoTerminado m =new MantenimientoTerminado(id_mantenimiento_terminado, fk_parte, codigo_barras,
															fk_estado_visita, fk_tipo_visita, fk_subtipo_visita, observaciones_tecnico,
															contador_interno, codigo_interno, reparacion,
															fk_tipo_reparacion, fecha_reparacion, fk_tiempo_mano_obra,
															coste_materiales, coste_mano_obra, coste_mano_obra_adicional,
															codigo_barras_reparacion, fk_tipo_maquina, fk_marca_maquina,
															modelo_maquina, fk_potencia_maquina, fk_uso_maquina,
															puesta_marcha_maquina, codigo_maquina, c0_maquina,
															temperatura_max_acs, caudal_acs, potencia_util,
															temperatura_gases_combustion, temperatura_ambiente_local,
															temperatura_agua_generador_calor_entrada,
															temperatura_agua_generador_calor_salida,
															limpieza_quemadores_caldera, revision_vaso_expansion,
															regulacion_aparatos, comprobar_estanqueidad_cierre_quemadores_caldera,
															revision_calderas_contadores, verificacion_circuito_hidraulico_calefaccion,
															estanqueidad_conexion_aparatos, estanqueidad_conducto_evacuacion_irg,
															comprobacion_niveles_agua, tipo_conducto_evacuacion,
															revision_estado_aislamiento_termico, analisis_productos_combustion,
															caudal_acs_calculo_potencia, revision_sistema_control,enviado);
		return m;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosMantenimientoTerminados(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<MantenimientoTerminado, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarMantenimientoTerminadoPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<MantenimientoTerminado, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(MantenimientoTerminado.ID_MANTENIMIENTO_TERMINADO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<MantenimientoTerminado> buscarTodosLosMantenimientoTerminados(Context context) throws SQLException {
		cargarDao(context);
		List<MantenimientoTerminado> listadoMantenimientoTerminado= dao.queryForEq(MantenimientoTerminado.ENVIADO, false);
		if(listadoMantenimientoTerminado.isEmpty()) {
			return null;
		}else{
			return listadoMantenimientoTerminado;
		}
	}
	public static MantenimientoTerminado buscarMantenimientoTerminadoPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<MantenimientoTerminado> listadoMantenimientoTerminado= dao.queryForEq(MantenimientoTerminado.ID_MANTENIMIENTO_TERMINADO, id);
		if(listadoMantenimientoTerminado.isEmpty()){
			return  null;
		}else{
			return listadoMantenimientoTerminado.get(0);
		}
	}

	public static void actualizarEnviado(Context context, boolean enviado, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<MantenimientoTerminado, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(MantenimientoTerminado.ID_MANTENIMIENTO_TERMINADO,id_mantenimiento);
		updateBuilder.updateColumnValue(MantenimientoTerminado.ENVIADO,enviado);
		updateBuilder.update();
	}
}
