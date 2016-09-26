package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.MotivosNoRep;

import java.sql.SQLException;
import java.util.List;

public class MotivosNoRepDAO extends DBHelperMOS {
	static Dao<MotivosNoRep, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMotivosNoRepDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMotivosNoRep(Context context,int id,String motivo, int bCodigo) {
		MotivosNoRep m = montarMotivosNoRep(id, motivo, bCodigo);
		return crearMotivosNoRep(m,context);
	}
	public static boolean crearMotivosNoRep(MotivosNoRep m,Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static MotivosNoRep montarMotivosNoRep(int id,String motivo, int bCodigo) {
		MotivosNoRep t =new MotivosNoRep(id, motivo, bCodigo);
		return t;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosMotivosNoRep(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<MotivosNoRep, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarMotivosNoRepPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<MotivosNoRep, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(MotivosNoRep.ID_MOTIVO_CANCELACION, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<MotivosNoRep> buscarTodosLosMotivosNoRep(Context context) throws SQLException {
		cargarDao(context);
		List<MotivosNoRep> listadoMotivosNoRep= dao.queryForAll();
		if(listadoMotivosNoRep.isEmpty()) {
			return null;
		}else{
			return listadoMotivosNoRep;
		}
	}
	public static MotivosNoRep buscarMotivosNoRepPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<MotivosNoRep> listadoMotivosNoRep= dao.queryForEq(MotivosNoRep.ID_MOTIVO_CANCELACION, id);
		if(listadoMotivosNoRep.isEmpty()){
			return  null;
		}else{
			return listadoMotivosNoRep.get(0);
		}
	}

	public static int buscarMotivosNoRepMotivo(Context context, String nombre) throws SQLException {
		cargarDao(context);
		List<MotivosNoRep> listadoMotivosNoRep= dao.queryForEq(MotivosNoRep.MOTIVO, nombre);
		if(listadoMotivosNoRep.isEmpty()){
			return  0;
		}else{
			return listadoMotivosNoRep.get(0).getId_motivo_cancelacion();
		}
	}
}
