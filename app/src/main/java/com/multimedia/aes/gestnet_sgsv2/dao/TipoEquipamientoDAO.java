package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoEquipamiento;

import java.sql.SQLException;
import java.util.List;

public class TipoEquipamientoDAO extends DBHelperMOS {


	static Dao<TipoEquipamiento, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getTipoEquipamientoDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newTipoEquipamiento(Context context,int id_tipo_caldera, String nombre_tipo_caldera) {
		TipoEquipamiento t = montarTipoEquipamiento(id_tipo_caldera, nombre_tipo_caldera);
		return crearTipoEquipamiento(t,context);
	}
	public static boolean crearTipoEquipamiento(TipoEquipamiento t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static TipoEquipamiento montarTipoEquipamiento(int id_tipo_caldera, String nombre_tipo_caldera) {
		TipoEquipamiento t =new TipoEquipamiento(id_tipo_caldera, nombre_tipo_caldera);
		return t;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosTipoEquipamiento(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<TipoEquipamiento, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarTipoEquipamientoPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<TipoEquipamiento, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(TipoEquipamiento.ID_TIPO_EQUIPAMIENTO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<TipoEquipamiento> buscarTodosLosTipoEquipamiento(Context context) throws SQLException {
		cargarDao(context);
		List<TipoEquipamiento> listadoTipoEquipamiento= dao.queryForAll();
		if(listadoTipoEquipamiento.isEmpty()) {
			return null;
		}else{
			return listadoTipoEquipamiento;
		}
	}
	public static TipoEquipamiento buscarTipoEquipamientoPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<TipoEquipamiento> listadoTipoEquipamiento= dao.queryForEq(TipoEquipamiento.ID_TIPO_EQUIPAMIENTO, id);
		if(listadoTipoEquipamiento.isEmpty()) {
			return null;
		}else{
			return listadoTipoEquipamiento.get(0);
		}
	}
	public static int buscarTipoEquipamientoPorNombre(Context context, String nombre) throws SQLException {
		cargarDao(context);
		List<TipoEquipamiento> listadoTipoEquipamiento= dao.queryForEq(TipoEquipamiento.NOMBRE_TIPO_EQUIPAMIENTO, nombre);
		if(listadoTipoEquipamiento.isEmpty()) {
			return 0;
		}else{
			return listadoTipoEquipamiento.get(0).getId_tipo_equipamiento();
		}
	}
	public static String buscarNombreTipoEquipamientoPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<TipoEquipamiento> listadoTipoEquipamiento= dao.queryForEq(TipoEquipamiento.ID_TIPO_EQUIPAMIENTO, id);
		if(listadoTipoEquipamiento.isEmpty()) {
			return null;
		}else{
			return listadoTipoEquipamiento.get(0).getNombre_tipo_equipamiento();
		}
	}


	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



}
