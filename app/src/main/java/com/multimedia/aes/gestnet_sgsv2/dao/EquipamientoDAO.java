package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Equipamiento;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;

import java.sql.SQLException;
import java.util.List;

public class EquipamientoDAO extends DBHelperMOS {
	static Dao<Equipamiento, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getEquipamientoDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newEquipamiento(Context context,int id_equipamiento, int fk_maquina, int fk_tipo_equipamiento, String potencia_fuegos, String codigo_equipamiento, String co2_equipamiento) {
		Equipamiento eq = montarEquipamiento(id_equipamiento, fk_maquina, fk_tipo_equipamiento, potencia_fuegos, codigo_equipamiento, co2_equipamiento);
		return crearEquipamiento(eq,context);
	}
	public static Equipamiento newEquipamientoRet(Context context,int id_equipamiento, int fk_maquina, int fk_tipo_equipamiento, String potencia_fuegos, String codigo_equipamiento, String co2_equipamiento) {
		Equipamiento eq = montarEquipamiento(id_equipamiento, fk_maquina, fk_tipo_equipamiento, potencia_fuegos, codigo_equipamiento, co2_equipamiento);
		return crearEquipamientoRet(eq,context);
	}
	public static boolean crearEquipamiento(Equipamiento eq,Context context) {
		try {
			cargarDao(context);
			dao.create(eq);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Equipamiento crearEquipamientoRet(Equipamiento eq,Context context) {
		try {
			cargarDao(context);
			dao.create(eq);
			return eq;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Equipamiento montarEquipamiento(int id_equipamiento, int fk_maquina, int fk_tipo_equipamiento, String potencia_fuegos, String codigo_equipamiento, String co2_equipamiento) {
		Equipamiento eq =new Equipamiento(id_equipamiento, fk_maquina, fk_tipo_equipamiento, potencia_fuegos, codigo_equipamiento, co2_equipamiento);
		return eq;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosEquipamientos(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Equipamiento, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarEquipamientoPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Equipamiento, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Equipamiento.ID_EQUIPAMIENTO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Equipamiento> buscarTodosLosEquipamientos(Context context) throws SQLException {
		cargarDao(context);
		List<Equipamiento> listadoEquipamiento= dao.queryForAll();
		if(listadoEquipamiento.isEmpty()) {
			return null;
		}else{
			return listadoEquipamiento;
		}
	}
	public static Equipamiento buscarEquipamientoPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Equipamiento> listadoEquipamiento= dao.queryForEq(Equipamiento.ID_EQUIPAMIENTO, id);
		if(listadoEquipamiento.isEmpty()){
			return  null;
		}else{
			return listadoEquipamiento.get(0);
		}
	}
	public static List<Equipamiento> buscarEquipamientoPorIdMaquina(Context context, int idMaquina) throws SQLException {
		cargarDao(context);
		List<Equipamiento> listadoEquipamiento= dao.queryForEq(Equipamiento.FK_MAQUINA, idMaquina);
		if(listadoEquipamiento.isEmpty()){
			return  null;
		}else{
			return listadoEquipamiento;
		}
	}
	//____________FUNCIONES ACTUALIZAR__________________________//
	public static void actualizarEquipamiento(Context context, int fkTipo,String potencia,int fk) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Equipamiento, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Equipamiento.FK_EQUIPAMIENTO,fk);
		updateBuilder.updateColumnValue(Equipamiento.FK_TIPO_EQUIPAMIENTO,fkTipo);
		updateBuilder.updateColumnValue(Equipamiento.POTENCIA_FUEGOS,potencia);
		updateBuilder.update();
	}
}
