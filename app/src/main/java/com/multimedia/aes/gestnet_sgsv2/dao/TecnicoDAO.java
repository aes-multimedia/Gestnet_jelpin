package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import java.sql.SQLException;
import java.util.List;

public class TecnicoDAO extends DBHelperMOS {
	static Dao<Tecnico, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getTecnicoDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newTecnico(Context context, int id_tecnico, String nombre_usuario,
									 String login_usuario, String email, String num_tecnico, int fk_empresa,
									 int fk_almacen, int fk_compañia, int fk_departamento, String apikey) {
		Tecnico t = montarTecnico(id_tecnico, nombre_usuario, login_usuario, email, num_tecnico, fk_empresa, fk_almacen, fk_compañia, fk_departamento, apikey);
		return crearTecnico(t,context);
	}
	public static boolean crearTecnico(Tecnico t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Tecnico montarTecnico(int id_tecnico, String nombre_usuario, String login_usuario,
										String email, String num_tecnico, int fk_empresa, int fk_almacen,
										int fk_compañia, int fk_departamento, String apikey) {
		Tecnico t =new Tecnico(id_tecnico, nombre_usuario, login_usuario, email, num_tecnico, fk_empresa, fk_almacen, fk_compañia, fk_departamento, apikey);
		return t;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosTecnicos(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Tecnico, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarTecnicoPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Tecnico, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Tecnico.ID_TECNICO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Tecnico> buscarTodosLosTecnicos(Context context) throws SQLException {
		cargarDao(context);
		List<Tecnico> listadoTecnico= dao.queryForAll();
		if(listadoTecnico.isEmpty()) {
			return null;
		}else{
			return listadoTecnico;
		}
	}
	public static Tecnico buscarTecnicoPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Tecnico> listadoTecnico= dao.queryForEq(Tecnico.ID_TECNICO, id);
		if(listadoTecnico.isEmpty()) {
			return null;
		}else{
			return listadoTecnico.get(0);
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
