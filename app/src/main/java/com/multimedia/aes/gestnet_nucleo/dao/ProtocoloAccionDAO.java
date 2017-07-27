package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;

import java.sql.SQLException;
import java.util.List;

public class ProtocoloAccionDAO extends DBHelperMOS {
	static Dao<ProtocoloAccion, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getProtocoloDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newProtocolo(Context context, int id_protocolo_accion, int fk_maquina, int fk_protocolo, int descripcion, int nombre_protocolo) {
		ProtocoloAccion p = montarProtocolo(id_protocolo_accion, fk_maquina, fk_protocolo, descripcion, nombre_protocolo);
		return crearProtocolo(p, context);
	}

	public static boolean crearProtocolo(ProtocoloAccion p, Context context) {
		try {
			cargarDao(context);
			dao.create(p);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ProtocoloAccion montarProtocolo(int id_protocolo_accion, int fk_maquina, int fk_protocolo, int descripcion, int nombre_protocolo) {
		ProtocoloAccion p = new ProtocoloAccion(id_protocolo_accion, fk_maquina, fk_protocolo, descripcion, nombre_protocolo);
		return p;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosProtocolo(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<ProtocoloAccion, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}

	public static void borrarProtocoloPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<ProtocoloAccion, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(ProtocoloAccion.ID_PROTOCOLO_ACCION, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<ProtocoloAccion> buscarTodosLosProtocolo(Context context) throws SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccions = dao.queryForAll();
		if (listadoProtocoloAccions.isEmpty()) {
			return null;
		} else {
			return listadoProtocoloAccions;
		}
	}

	public static ProtocoloAccion buscarProtocoloPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccions = dao.queryForEq(ProtocoloAccion.ID_PROTOCOLO_ACCION, id);
		if (listadoProtocoloAccions.isEmpty()) {
			return null;
		} else {
			return listadoProtocoloAccions.get(0);
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


}
