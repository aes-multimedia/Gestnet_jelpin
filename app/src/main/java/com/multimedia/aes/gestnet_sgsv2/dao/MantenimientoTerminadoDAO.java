package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
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

	public static boolean newMantenimientoTerminado(Context context) {
		MantenimientoTerminado m = montarMantenimientoTerminado();
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
	public static MantenimientoTerminado montarMantenimientoTerminado() {
		MantenimientoTerminado m =new MantenimientoTerminado();
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
		List<MantenimientoTerminado> listadoMantenimientoTerminado= dao.queryForAll();
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

}
