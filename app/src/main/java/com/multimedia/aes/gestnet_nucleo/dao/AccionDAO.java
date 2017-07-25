package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Accion;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;

import java.sql.SQLException;
import java.util.List;

public class AccionDAO extends DBHelperMOS {
	static Dao<Accion, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getAccionDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newAccion(Context context,int id_accion, int fk_protocolo, String descripcion_accion, String periocidad_accion,
									String tipo_accion) {
		Accion a = montarAccion(id_accion, fk_protocolo, descripcion_accion, periocidad_accion, tipo_accion);
		return crearAccion(a, context);
	}

	public static boolean crearAccion(Accion a, Context context) {
		try {
			cargarDao(context);
			dao.create(a);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Accion montarAccion(int id_accion, int fk_protocolo, String descripcion_accion, String periocidad_accion,
									  String tipo_accion) {
		Accion a = new Accion(id_accion, fk_protocolo, descripcion_accion, periocidad_accion, tipo_accion);
		return a;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodasLasAcciones(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Accion, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}

	public static void borrarAccionPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Accion, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Accion.ID_ACCION, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Accion> buscarTodasLasAcciones(Context context) throws SQLException {
		cargarDao(context);
		List<Accion> listadoAcciones = dao.queryForAll();
		if (listadoAcciones.isEmpty()) {
			return null;
		} else {
			return listadoAcciones;
		}
	}

	public static Accion buscarAccionPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Accion> listadoAcciones = dao.queryForEq(Accion.ID_ACCION, id);
		if (listadoAcciones.isEmpty()) {
			return null;
		} else {
			return listadoAcciones.get(0);
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


}
