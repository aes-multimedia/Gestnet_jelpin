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
													String codigo_barras_reparacion,int limpieza_quemadores_caldera, int revision_vaso_expansion,
													int regulacion_aparatos, int comprobar_estanqueidad_cierre_quemadores_caldera,
													int revision_calderas_contadores, int verificacion_circuito_hidraulico_calefaccion,
													int estanqueidad_conexion_aparatos, int estanqueidad_conducto_evacuacion_irg,
													int comprobacion_niveles_agua, int tipo_conducto_evacuacion,
													int revision_estado_aislamiento_termico, int analisis_productos_combustion,
													int caudal_acs_calculo_potencia, int revision_sistema_control,boolean enviado,boolean maquina) {
		MantenimientoTerminado m = montarMantenimientoTerminado(id_mantenimiento_terminado, fk_parte, codigo_barras,
																fk_estado_visita, fk_tipo_visita, fk_subtipo_visita, observaciones_tecnico,
																contador_interno, codigo_interno, reparacion,
																fk_tipo_reparacion, fecha_reparacion, fk_tiempo_mano_obra,
																coste_materiales, coste_mano_obra, coste_mano_obra_adicional,
																codigo_barras_reparacion,limpieza_quemadores_caldera, revision_vaso_expansion,
																regulacion_aparatos, comprobar_estanqueidad_cierre_quemadores_caldera,
																revision_calderas_contadores, verificacion_circuito_hidraulico_calefaccion,
																estanqueidad_conexion_aparatos, estanqueidad_conducto_evacuacion_irg,
																comprobacion_niveles_agua, tipo_conducto_evacuacion,
																revision_estado_aislamiento_termico, analisis_productos_combustion,
																caudal_acs_calculo_potencia, revision_sistema_control,enviado,maquina);
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
																	  String codigo_barras_reparacion,int limpieza_quemadores_caldera, int revision_vaso_expansion,
																	  int regulacion_aparatos, int comprobar_estanqueidad_cierre_quemadores_caldera,
																	  int revision_calderas_contadores, int verificacion_circuito_hidraulico_calefaccion,
																	  int estanqueidad_conexion_aparatos, int estanqueidad_conducto_evacuacion_irg,
																	  int comprobacion_niveles_agua, int tipo_conducto_evacuacion,
																	  int revision_estado_aislamiento_termico, int analisis_productos_combustion,
																	  int caudal_acs_calculo_potencia, int revision_sistema_control,boolean enviado,boolean maquina) {
		MantenimientoTerminado m =new MantenimientoTerminado(id_mantenimiento_terminado, fk_parte, codigo_barras,
															fk_estado_visita, fk_tipo_visita, fk_subtipo_visita, observaciones_tecnico,
															contador_interno, codigo_interno, reparacion,
															fk_tipo_reparacion, fecha_reparacion, fk_tiempo_mano_obra,
															coste_materiales, coste_mano_obra, coste_mano_obra_adicional,
															codigo_barras_reparacion,limpieza_quemadores_caldera, revision_vaso_expansion,
															regulacion_aparatos, comprobar_estanqueidad_cierre_quemadores_caldera,
															revision_calderas_contadores, verificacion_circuito_hidraulico_calefaccion,
															estanqueidad_conexion_aparatos, estanqueidad_conducto_evacuacion_irg,
															comprobacion_niveles_agua, tipo_conducto_evacuacion,
															revision_estado_aislamiento_termico, analisis_productos_combustion,
															caudal_acs_calculo_potencia, revision_sistema_control,enviado,maquina);
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
	public static MantenimientoTerminado buscarMantenimientoTerminadoPorfkParte(Context context, int id) throws SQLException {
		cargarDao(context);
		List<MantenimientoTerminado> listadoMantenimientoTerminado= dao.queryForEq(MantenimientoTerminado.Fk_PARTE, id);
		if(listadoMantenimientoTerminado.isEmpty()){
			return  null;
		}else{
			return listadoMantenimientoTerminado.get(0);
		}
	}
	//__________FUNCIONES DE ACTUALIZAR______________________//
	public static void actualizarEnviado(Context context, boolean enviado, int id_mantenimiento) throws SQLException {
		cargarDao(context);
		UpdateBuilder<MantenimientoTerminado, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(MantenimientoTerminado.ID_MANTENIMIENTO_TERMINADO,id_mantenimiento);
		updateBuilder.updateColumnValue(MantenimientoTerminado.ENVIADO,enviado);
		updateBuilder.update();
	}
}
