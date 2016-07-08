package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;

import java.sql.SQLException;
import java.util.List;

public class TipoCalderaDAO extends DBHelperMOS {


	static Dao<TipoCaldera, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getTipoCalderaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newTipoCaldera(Context context,int id_tipo_caldera, String nombre_tipo_caldera) {
		TipoCaldera t = montarTipoCaldera(id_tipo_caldera, nombre_tipo_caldera);
		return crearTipoCaldera(t,context);
	}
	public static boolean crearTipoCaldera(TipoCaldera t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static TipoCaldera montarTipoCaldera(int id_tipo_caldera, String nombre_tipo_caldera) {
		TipoCaldera t =new TipoCaldera(id_tipo_caldera, nombre_tipo_caldera);
		return t;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosTipoCaldera(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<TipoCaldera, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarTipoCalderaPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<TipoCaldera, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Tecnico.ID_TECNICO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<TipoCaldera> buscarTodosLosTipoCaldera(Context context) throws SQLException {
		cargarDao(context);
		List<TipoCaldera> listadoTipoCaldera= dao.queryForAll();
		if(listadoTipoCaldera.isEmpty()) {
			return null;
		}else{
			return listadoTipoCaldera;
		}
	}
	public static TipoCaldera buscarTipoCalderaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<TipoCaldera> listadoTipoCaldera= dao.queryForEq(Tecnico.ID_TECNICO, id);
		if(listadoTipoCaldera.isEmpty()) {
			return null;
		}else{
			return listadoTipoCaldera.get(0);
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
