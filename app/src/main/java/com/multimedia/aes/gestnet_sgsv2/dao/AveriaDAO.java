package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Averia;

import java.sql.SQLException;
import java.util.List;

public class AveriaDAO extends DBHelperMOS {
	static Dao<Averia, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getAveriaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newAveria(Context context,int id_averia, String identificador_carga, String id_solicitud, String fk_tecnico,
									String cod_contrato, String tipo_solicitud, String des_tipo_solicitud, String subtipo_solicitud,
									String des_subtipo_solicitud, String fecha_creacion, String fecha_cierre, String estado_solicitud,
									String des_estado_solicitud, String subestado_solicitud, String telefono_contacto,
									String persona_contacto, String des_averia, String observaciones, String proveedor,
									String descripcion_cancelacion, String urgencia, String fecha_limite_visita,
									String marca_caldera, String nombre_provincia, String urgente, String nombre_poblacion,
									String nom_calle, String cod_portal, String tip_piso, String tip_mano, String cod_postal,
									String cups, String estado_ultima_visita, String urgencia_ultima_visita, String mant_gas_calefaccion,
									String mant_gas, String horario_contacto, String observaciones_visita, String telefono_cliente,
									String efv, String scoring, String contador_averias, String categoria_visita, String iniciado_android,
									String enviado_android, String cerrado, String cerrado_error, String historico) {
		Averia a = montarAveria(id_averia, identificador_carga, id_solicitud, fk_tecnico,
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
				enviado_android, cerrado, cerrado_error, historico);
		return crearAveria(a,context);
	}
	public static boolean crearAveria(Averia u,Context context) {
		try {
			cargarDao(context);
			dao.create(u);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Averia montarAveria(int id_averia, String identificador_carga, String id_solicitud, String fk_tecnico,
									  String cod_contrato, String tipo_solicitud, String des_tipo_solicitud, String subtipo_solicitud,
									  String des_subtipo_solicitud, String fecha_creacion, String fecha_cierre, String estado_solicitud,
									  String des_estado_solicitud, String subestado_solicitud, String telefono_contacto,
									  String persona_contacto, String des_averia, String observaciones, String proveedor,
									  String descripcion_cancelacion, String urgencia, String fecha_limite_visita,
									  String marca_caldera, String nombre_provincia, String urgente, String nombre_poblacion,
									  String nom_calle, String cod_portal, String tip_piso, String tip_mano, String cod_postal,
									  String cups, String estado_ultima_visita, String urgencia_ultima_visita, String mant_gas_calefaccion,
									  String mant_gas, String horario_contacto, String observaciones_visita, String telefono_cliente,
									  String efv, String scoring, String contador_averias, String categoria_visita, String iniciado_android,
									  String enviado_android, String cerrado, String cerrado_error, String historico) {
		Averia a =new Averia(id_averia, identificador_carga, id_solicitud, fk_tecnico,
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
				enviado_android, cerrado, cerrado_error, historico);
		return a;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLasAverias(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Averia, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarAveriaPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Averia, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Averia.ID_AVERIA, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Averia> buscarTodasLasAverias(Context context) throws SQLException {
		cargarDao(context);
		List<Averia> listadoAveria= dao.queryForAll();
		if(listadoAveria.isEmpty()) {
			return null;
		}else{
			return listadoAveria;
		}
	}
	public static Averia buscarAveriaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Averia> listadoAveria= dao.queryForEq(Averia.ID_AVERIA, id);
		if(listadoAveria.isEmpty()) {
			return null;
		}else{
			return listadoAveria.get(0);
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
