package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Potencia;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import java.sql.SQLException;
import java.util.List;

public class PotenciaDAO extends DBHelperMOS {
	static Dao<Potencia, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getPotenciaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newPotencia(Context context,int id_potencia, String potencia) {
		Potencia t = montarPotencia(id_potencia, potencia);
		return crearPotencia(t,context);
	}
	public static boolean crearPotencia(Potencia t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Potencia montarPotencia(int id_potencia, String potencia) {
		Potencia t =new Potencia(id_potencia, potencia);
		return t;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosPotencia(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Potencia, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarPotenciaPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Potencia, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Tecnico.ID_TECNICO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Potencia> buscarTodosLosPotencia(Context context) throws SQLException {
		cargarDao(context);
		List<Potencia> listadoPotencia= dao.queryForAll();
		if(listadoPotencia.isEmpty()) {
			return null;
		}else{
			return listadoPotencia;
		}
	}
	public static Potencia buscarPotenciaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Potencia> listadoPotencia= dao.queryForEq(Potencia.ID_POTENCIA, id);
		if(listadoPotencia.isEmpty()) {
			return null;
		}else{
			return listadoPotencia.get(0);
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
