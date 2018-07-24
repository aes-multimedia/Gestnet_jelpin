package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProtocoloAccionDAO extends DBHelperMOS {
	static Dao<ProtocoloAccion, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getProtocoloDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newProtocoloAccion(Context context, int id_protocolo_accion, String valor, int fk_maquina, int fk_parte, int fk_protocolo, String nombre_protocolo, int id_accion, boolean tipo_accion, String descripcion,int orden) throws java.sql.SQLException {
		ProtocoloAccion d = montarProtocoloAccion(id_protocolo_accion,valor, fk_maquina,fk_parte, fk_protocolo, nombre_protocolo,id_accion, tipo_accion, descripcion,orden);
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

	public static ProtocoloAccion montarProtocoloAccion(int id_protocolo_accion, String valor, int fk_maquina, int fk_parte, int fk_protocolo, String nombre_protocolo, int id_accion, boolean tipo_accion, String descripcion,int orden) {
		ProtocoloAccion d = new ProtocoloAccion(id_protocolo_accion,valor, fk_maquina,fk_parte,fk_protocolo, nombre_protocolo,id_accion, tipo_accion,descripcion,orden);
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

	public static void borrarProtocoloPorFkParte(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<ProtocoloAccion, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(ProtocoloAccion.FK_PARTE, id);
		deleteBuilder.delete();

	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<ProtocoloAccion> buscarTodosLosProtocoloAccion(Context context) throws android.database.SQLException {
		List<ProtocoloAccion> listadoProtocoloAccion = null;
		try {
			cargarDao(context);
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

	public static List<ProtocoloAccion>  buscarPrueba(Context context, int id) throws android.database.SQLException, java.sql.SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccion= dao.queryBuilder().orderBy(ProtocoloAccion.ORDEN,true).where().eq(ProtocoloAccion.FK_PARTE,id).query();

		if (listadoProtocoloAccion.isEmpty()) {
			return null;
		} else {
			return listadoProtocoloAccion;
        }
	}
	public static ArrayList<ProtocoloAccion> buscarProtocoloAccionPorFkParte(Context context, int id) throws android.database.SQLException, java.sql.SQLException {
		cargarDao(context);
		ArrayList<ProtocoloAccion> listadoProtocoloAccion = null;
		listadoProtocoloAccion = (ArrayList<ProtocoloAccion>) dao.queryForEq(ProtocoloAccion.FK_PARTE, id);
		if (listadoProtocoloAccion.isEmpty()) {
			return null;
		} else {
			return listadoProtocoloAccion;
		}
	}
	public static List<ProtocoloAccion> buscarProtocoloAccionPorNombreProtocoloFkMaquina(Context context, String nombre, int fk_maquina) throws android.database.SQLException, java.sql.SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccion= dao.queryBuilder().orderBy(ProtocoloAccion.ORDEN,true).where().eq(ProtocoloAccion.NOMBRE_PROTOCOLO,nombre).and().eq(ProtocoloAccion.FK_MAQUINA,fk_maquina).query();
		if(listadoProtocoloAccion.isEmpty()) {
			return null;
		}else{
			return listadoProtocoloAccion;
		}
	}


	public static List<ProtocoloAccion> buscarProtocoloAccionPorFkProtocoloFkMaquina(Context context, int fkProtocolo, int fk_maquina) throws android.database.SQLException, java.sql.SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccion= dao.queryBuilder().orderBy(ProtocoloAccion.ORDEN,true).where().eq(ProtocoloAccion.FK_PROTOCOLO,fkProtocolo).and().eq(ProtocoloAccion.FK_MAQUINA,fk_maquina).query();
		if(listadoProtocoloAccion.isEmpty()) {
			return null;
		}else{
			return listadoProtocoloAccion;
		}
	}

	public static List<ProtocoloAccion> buscarProtocoloAccionPorNombreProtocoloFkParte(Context context, String nombre, int fk_parte) throws android.database.SQLException, java.sql.SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccion= dao.queryBuilder().where().eq(ProtocoloAccion.NOMBRE_PROTOCOLO,nombre).and().eq(ProtocoloAccion.FK_PARTE,fk_parte).and().eq(ProtocoloAccion.FK_MAQUINA,-1).query();
		if(listadoProtocoloAccion.isEmpty()) {
			return null;
		}else{
			return listadoProtocoloAccion;
		}
	}


	public static List<ProtocoloAccion> buscarProtocoloAccionPorNombreProtocolo(Context context, String nombre) throws android.database.SQLException, java.sql.SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccion= dao.queryBuilder().where().eq(ProtocoloAccion.NOMBRE_PROTOCOLO,nombre).query();
		if(listadoProtocoloAccion.isEmpty()) {
			return null;
		}else{
			return listadoProtocoloAccion;
		}
	}



	public static ProtocoloAccion buscarProtocoloAccionPorNombreProtocoloFkMaquinaDescripcion(Context context, String nombre, int fk_maquina,String descripcion) throws android.database.SQLException, java.sql.SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccion= dao.queryBuilder().where().eq(ProtocoloAccion.NOMBRE_PROTOCOLO,nombre).and().eq(ProtocoloAccion.FK_MAQUINA,fk_maquina).and().eq(ProtocoloAccion.DESCRIPCION,descripcion).query();
		if(listadoProtocoloAccion.isEmpty()) {
			return null;
		}else{
			return listadoProtocoloAccion.get(0);
		}
	}
	public static ProtocoloAccion buscarProtocoloAccionPorNombreProtocoloFkParteDescripcion(Context context, String nombre, int fk_parte,String descripcion) throws android.database.SQLException, java.sql.SQLException {
		cargarDao(context);
		List<ProtocoloAccion> listadoProtocoloAccion= dao.queryBuilder().where().eq(ProtocoloAccion.NOMBRE_PROTOCOLO,nombre).and().eq(ProtocoloAccion.FK_PARTE,fk_parte).and().eq(ProtocoloAccion.DESCRIPCION,descripcion).and().eq(ProtocoloAccion.FK_MAQUINA,-1).query();
		if(listadoProtocoloAccion.isEmpty()) {
			return null;
		}else{
			return listadoProtocoloAccion.get(0);
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//
	public static void actualizarProtocoloAccion(Context context, int id_protocolo_accion, String valor, int fk_maquina, int fk_parte, int fk_protocolo, String nombre_protocolo, int id_accion, boolean tipo_accion, String descripcion,int orden) throws SQLException {
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
		updateBuilder.updateColumnValue(ProtocoloAccion.ORDEN,orden);
		updateBuilder.update();
	}
	public static void actualizarValorFkProtocoloIdParte(Context context, String valor, int id_protocolo_accion,int idParte) throws SQLException {
		cargarDao(context);
		UpdateBuilder<ProtocoloAccion, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(ProtocoloAccion.ID_PROTOCOLO_ACCION,id_protocolo_accion).and().eq(ProtocoloAccion.FK_PARTE,idParte).and().eq(ProtocoloAccion.FK_MAQUINA,-1);
		updateBuilder.updateColumnValue(ProtocoloAccion.VALOR,valor);
		updateBuilder.update();
	}

	public static void actualizarValorFkProtocoloFkMaquina(Context context, String valor, int id_protocolo_accion,int fkMaquina) throws SQLException {
		cargarDao(context);
		UpdateBuilder<ProtocoloAccion, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(ProtocoloAccion.ID_PROTOCOLO_ACCION,id_protocolo_accion).and().eq(ProtocoloAccion.FK_MAQUINA,fkMaquina);
		updateBuilder.updateColumnValue(ProtocoloAccion.VALOR,valor);
		updateBuilder.update();
	}


}
