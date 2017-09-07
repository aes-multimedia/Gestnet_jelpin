package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;

import java.sql.SQLException;
import java.util.List;

public class ProtocoloAccionDAO extends DBHelperMOS {
	static Dao<ProtocoloAccion, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getProtocoloDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newProtocoloAccion(Context context, int id_protocolo_accion, boolean valor, int fk_maquina, int fk_parte, int fk_protocolo, String nombre_protocolo, int id_accion, boolean tipo_accion, String descripcion) throws java.sql.SQLException {
		ProtocoloAccion d = montarProtocoloAccion(id_protocolo_accion,valor, fk_maquina,fk_parte, fk_protocolo, nombre_protocolo,id_accion, tipo_accion, descripcion);
		return crearProtocoloAccion(d, context);
	}

	public static boolean crearProtocoloAccion(ProtocoloAccion d, Context context) throws java.sql.SQLException {
		try {
			cargarDao(context);
			dao.create(d);
			return true;
		} catch (android.database.SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ProtocoloAccion montarProtocoloAccion(int id_protocolo_accion, boolean valor, int fk_maquina, int fk_parte, int fk_protocolo, String nombre_protocolo, int id_accion,boolean tipo_accion, String descripcion) {
		ProtocoloAccion d = new ProtocoloAccion(id_protocolo_accion,valor, fk_maquina,fk_parte,fk_protocolo, nombre_protocolo,id_accion, tipo_accion,descripcion);
		return d;
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


	public static List<ProtocoloAccion> buscarTodosLosProtocoloAccion(Context context) throws android.database.SQLException {
		try {
			cargarDao(context);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		List<ProtocoloAccion> listadoProtocoloAccion = null;
		try {
			listadoProtocoloAccion = dao.queryForAll();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		if (listadoProtocoloAccion.isEmpty()) {
			return null;
		} else {
			return listadoProtocoloAccion;
		}
	}

	public static ProtocoloAccion buscarProtocoloAccionPorIdProtocoloAccion(Context context, int id) throws android.database.SQLException, java.sql.SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccion = null;
		try {
			listadoProtocoloAccion = dao.queryForEq(ProtocoloAccion.ID_PROTOCOLO_ACCION, id);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		if (listadoProtocoloAccion.isEmpty()) {
			return null;
		} else {
			return listadoProtocoloAccion.get(0);
		}
	}

	public static List<ProtocoloAccion>  buscarProtocoloAccionPorIdAccion(Context context, int id) throws android.database.SQLException, java.sql.SQLException {
		try {
			cargarDao(context);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		List<ProtocoloAccion> listadoProtocoloAccion = null;
		try {
			listadoProtocoloAccion =  dao.queryForEq(ProtocoloAccion.ID_ACCION, id);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		if (listadoProtocoloAccion.isEmpty()) {
			return null;
		} else {
			return listadoProtocoloAccion;
		}
	}

	public static List<ProtocoloAccion>  buscarProtocoloAccionPorFkProtocolo(Context context, int id) throws android.database.SQLException, java.sql.SQLException {
		try {
			cargarDao(context);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		List<ProtocoloAccion> listadoProtocoloAccion = null;
		try {
			listadoProtocoloAccion =  dao.queryForEq(ProtocoloAccion.FK_PROTOCOLO, id);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		if (listadoProtocoloAccion.isEmpty()) {
			return null;
		} else {
			return listadoProtocoloAccion;
		}
	}

	public static List<ProtocoloAccion>  buscarProtocoloAccionPorFkParte(Context context, int id) throws android.database.SQLException, java.sql.SQLException {
		try {
			cargarDao(context);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		List<ProtocoloAccion> listadoProtocoloAccion = null;
		try {
			listadoProtocoloAccion =  dao.queryForEq(ProtocoloAccion.FK_PARTE, id);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		if (listadoProtocoloAccion.isEmpty()) {
			return null;
		} else {
			return listadoProtocoloAccion;
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//
	public static void actualizarProtocoloAccion(Context context, int id_protocolo_accion, boolean valor, int fk_maquina,int fk_parte, int fk_protocolo, String nombre_protocolo, int id_accion,boolean tipo_accion, String descripcion) throws SQLException {
		cargarDao(context);
		UpdateBuilder<ProtocoloAccion, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(ProtocoloAccion.ID_PROTOCOLO_ACCION,id_protocolo_accion);
		updateBuilder.updateColumnValue(ProtocoloAccion.VALOR,valor);
		updateBuilder.updateColumnValue(ProtocoloAccion.FK_MAQUINA,fk_maquina);
		updateBuilder.updateColumnValue(ProtocoloAccion.FK_PARTE,fk_parte);
		updateBuilder.updateColumnValue(ProtocoloAccion.FK_PROTOCOLO,fk_protocolo);
		updateBuilder.updateColumnValue(ProtocoloAccion.NOMBRE_PROTOCOLO,nombre_protocolo);
		updateBuilder.updateColumnValue(ProtocoloAccion.ID_ACCION,id_accion);
		updateBuilder.updateColumnValue(ProtocoloAccion.TIPO_ACCION,tipo_accion);
		updateBuilder.updateColumnValue(ProtocoloAccion.DESCRIPCION,descripcion);
		updateBuilder.update();
	}

}
