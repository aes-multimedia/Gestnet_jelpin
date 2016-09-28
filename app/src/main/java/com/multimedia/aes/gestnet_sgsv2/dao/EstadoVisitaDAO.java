package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.EstadoVisita;

import java.sql.SQLException;
import java.util.List;

public class EstadoVisitaDAO extends DBHelperMOS {
	static Dao<EstadoVisita, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getEstadoVisitaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newEstadoVisita(Context context,int id_estado_visita, String nombre_estado_visita, String codigo_compania) {
		EstadoVisita t = montarEstadoVisita(id_estado_visita, nombre_estado_visita, codigo_compania);
		return crearEstadoVisita(t,context);
	}
	public static boolean crearEstadoVisita(EstadoVisita t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static EstadoVisita montarEstadoVisita(int id_estado_visita, String nombre_estado_visita, String codigo_compania) {
		EstadoVisita t =new EstadoVisita(id_estado_visita, nombre_estado_visita, codigo_compania);
		return t;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosEstadoVisita(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<EstadoVisita, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarEstadoVisitaPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<EstadoVisita, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(EstadoVisita.ID_ESTADO_VISITA, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<EstadoVisita> buscarTodosLosEstadoVisita(Context context) throws SQLException {
		cargarDao(context);
		List<EstadoVisita> listadoEstadoVisita= dao.queryForAll();
		if(listadoEstadoVisita.isEmpty()) {
			return null;
		}else{
			return listadoEstadoVisita;
		}
	}
	public static EstadoVisita buscarEstadoVisitaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<EstadoVisita> listadoEstadoVisita= dao.queryForEq(EstadoVisita.ID_ESTADO_VISITA, id);
		if(listadoEstadoVisita.isEmpty()){
			return  null;
		}else{
			return listadoEstadoVisita.get(0);
		}
	}
	public static int buscarIdEstadoVisitaPorNombre(Context context, String nombre) throws SQLException {
		cargarDao(context);
		List<EstadoVisita> listadoEstadoVisita= dao.queryForEq(EstadoVisita.NOMBRE_ESTADO_VISITA, nombre);
		if(listadoEstadoVisita.isEmpty()){
			return  -1;
		}else{
			return listadoEstadoVisita.get(0).getId_estado_visita();
		}
	}

}
