package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.MarcaCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;

import java.sql.SQLException;
import java.util.List;

public class MarcaCalderaDAO extends DBHelperMOS {
	static Dao<MarcaCaldera, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMarcaCalderaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMarcaCaldera(Context context,int id_marca_caldera, String nombre_marca_caldera) {
		MarcaCaldera t = montarMarcaCaldera(id_marca_caldera, nombre_marca_caldera);
		return crearMarcaCaldera(t,context);
	}
	public static boolean crearMarcaCaldera(MarcaCaldera t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static MarcaCaldera montarMarcaCaldera(int id_marca_caldera, String nombre_marca_caldera) {
		MarcaCaldera t =new MarcaCaldera(id_marca_caldera, nombre_marca_caldera);
		return t;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosMarcaCaldera(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<MarcaCaldera, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarMarcaCalderaPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<MarcaCaldera, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Tecnico.ID_TECNICO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<MarcaCaldera> buscarTodosLosMarcaCaldera(Context context) throws SQLException {
		cargarDao(context);
		List<MarcaCaldera> listadoMarcaCaldera= dao.queryForAll();
		if(listadoMarcaCaldera.isEmpty()) {
			return null;
		}else{
			return listadoMarcaCaldera;
		}
	}
	public static MarcaCaldera buscarMarcaCalderaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<MarcaCaldera> listadoMarcaCaldera= dao.queryForEq(Tecnico.ID_TECNICO, id);
		if(listadoMarcaCaldera.isEmpty()) {
			return null;
		}else{
			return listadoMarcaCaldera.get(0);
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
