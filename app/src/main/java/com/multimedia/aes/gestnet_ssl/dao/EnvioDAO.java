package com.multimedia.aes.gestnet_ssl.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_ssl.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_ssl.entidades.Envio;

import java.sql.SQLException;
import java.util.List;

public class EnvioDAO extends DBHelperMOS {
	static Dao<Envio, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getEnvioDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newEnvio(Context context,  String json_envio, String url_envio, String request_envio) {
		Envio p = montarEnvio(json_envio, url_envio, request_envio);
		return crearEnvio(p,context);
	}
	public static boolean crearEnvio(Envio p,Context context) {
		try {
			cargarDao(context);
			dao.create(p);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Envio montarEnvio(String json_envio, String url_envio, String request_envio) {
		Envio p =new Envio(json_envio, url_envio, request_envio);
		return p;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosEnvios(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Envio, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarEnvioPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Envio, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Envio.ID_ENVIO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Envio> buscarTodosLosEnvios(Context context) throws SQLException {
		cargarDao(context);
		List<Envio> listadoEnvio= dao.queryForAll();
		if(listadoEnvio.isEmpty()) {
			return null;
		}else{
			return listadoEnvio;
		}
	}
	public static Envio buscarEnvioPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Envio> listadoEnvio= dao.queryForEq(Envio.ID_ENVIO, id);
		if(listadoEnvio.isEmpty()) {
			return null;
		}else{
			return listadoEnvio.get(0);
		}
	}
	public static List<Envio> buscarEnvioPorFkSolicitud(Context context, int fk) throws SQLException {
		cargarDao(context);
		List<Envio> listadoEnvio= dao.queryForEq(Envio.FK_SOLICITUD, fk);
		if(listadoEnvio.isEmpty()) {
			return null;
		}else{
			return listadoEnvio;
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



}
