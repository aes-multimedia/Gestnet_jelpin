package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

import java.sql.SQLException;
import java.util.List;

public class UsoCalderaDAO extends DBHelperMOS {
	static Dao<UsoCaldera, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getUsoCalderaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newUsoCaldera(Context context,int id_tipo_caldera, String nombre_tipo_caldera) {
		UsoCaldera t = montarUsoCaldera(id_tipo_caldera, nombre_tipo_caldera);
		return crearUsoCaldera(t,context);
	}
	public static boolean crearUsoCaldera(UsoCaldera t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static UsoCaldera montarUsoCaldera(int id_tipo_caldera, String nombre_tipo_caldera) {
		UsoCaldera t =new UsoCaldera(id_tipo_caldera, nombre_tipo_caldera);
		return t;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosUsoCaldera(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<UsoCaldera, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarUsoCalderaPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<UsoCaldera, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Tecnico.ID_TECNICO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<UsoCaldera> buscarTodosLosUsoCaldera(Context context) throws SQLException {
		cargarDao(context);
		List<UsoCaldera> listadoUsoCaldera= dao.queryForAll();
		if(listadoUsoCaldera.isEmpty()) {
			return null;
		}else{
			return listadoUsoCaldera;
		}
	}
	public static UsoCaldera buscarUsoCalderaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<UsoCaldera> listadoUsoCaldera= dao.queryForEq(Tecnico.ID_TECNICO, id);
		if(listadoUsoCaldera.isEmpty()) {
			return null;
		}else{
			return listadoUsoCaldera.get(0);
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
