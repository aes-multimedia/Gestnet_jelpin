package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Accion;
import com.multimedia.aes.gestnet_nucleo.entidades.Protocolo;

import java.sql.SQLException;
import java.util.List;

public class ProtocoloDAO extends DBHelperMOS {
	static Dao<Protocolo, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getProtocoloDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newProtocolo(Context context, int id_protocolos, String nombre_protocolos, String b_general) {
		Protocolo p = montarProtocolo(id_protocolos, nombre_protocolos, b_general);
		return crearProtocolo(p, context);
	}

	public static boolean crearProtocolo(Protocolo p, Context context) {
		try {
			cargarDao(context);
			dao.create(p);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Protocolo montarProtocolo(int id_protocolos, String nombre_protocolos, String b_general) {
		Protocolo p = new Protocolo(id_protocolos, nombre_protocolos, b_general);
		return p;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosProtocolo(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Protocolo, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}

	public static void borrarProtocoloPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Protocolo, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Protocolo.ID_PROTOCOLO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Protocolo> buscarTodosLosProtocolo(Context context) throws SQLException {
		cargarDao(context);
		List<Protocolo> listadoProtocolos = dao.queryForAll();
		if (listadoProtocolos.isEmpty()) {
			return null;
		} else {
			return listadoProtocolos;
		}
	}

	public static Protocolo buscarProtocoloPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Protocolo> listadoProtocolos = dao.queryForEq(Protocolo.ID_PROTOCOLO, id);
		if (listadoProtocolos.isEmpty()) {
			return null;
		} else {
			return listadoProtocolos.get(0);
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


}
