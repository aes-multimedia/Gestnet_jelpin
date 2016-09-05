package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

import java.sql.SQLException;
import java.util.List;

public class EquipamientoCalderaDAO extends DBHelperMOS {
	static Dao<EquipamientoCaldera, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getEquipamientoCalderaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newEquipamientoCaldera(Context context,String potencia_fuegos, int fk_tipo_equipamiento, int fk_maquina) {
		EquipamientoCaldera eq = montarEquipamientoCaldera(potencia_fuegos, fk_tipo_equipamiento, fk_maquina);
		return crearEquipamientoCaldera(eq,context);
	}
	public static boolean crearEquipamientoCaldera(EquipamientoCaldera eq,Context context) {
		try {
			cargarDao(context);
			dao.create(eq);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static EquipamientoCaldera montarEquipamientoCaldera(String potencia_fuegos, int fk_tipo_equipamiento, int fk_maquina) {
		EquipamientoCaldera eq =new EquipamientoCaldera(potencia_fuegos, fk_tipo_equipamiento, fk_maquina);
		return eq;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosEquipamientoCaldera(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<EquipamientoCaldera, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarEquipamientoCalderaPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<EquipamientoCaldera, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(EquipamientoCaldera.ID_EQUIPAMIENTO_CALDERA, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<EquipamientoCaldera> buscarTodosLosEquipamientoCaldera(Context context) throws SQLException {
		cargarDao(context);
		List<EquipamientoCaldera> listadoEquipamientoCaldera= dao.queryForAll();
		if(listadoEquipamientoCaldera.isEmpty()) {
			return null;
		}else{
			return listadoEquipamientoCaldera;
		}
	}
	public static EquipamientoCaldera buscarEquipamientoCalderaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<EquipamientoCaldera> listadoEquipamientoCaldera= dao.queryForEq(EquipamientoCaldera.ID_EQUIPAMIENTO_CALDERA, id);
		if(listadoEquipamientoCaldera.isEmpty()){
			return  null;
		}else{
			return listadoEquipamientoCaldera.get(0);
		}
	}
	public static List<EquipamientoCaldera> buscarEquipamientoCalderaPorIdMantenimiento(Context context, int idMaquina) throws SQLException {
		cargarDao(context);
		List<EquipamientoCaldera> listadoEquipamientoCaldera= dao.queryForEq(EquipamientoCaldera.FK_PARTE, idMaquina);
		if(listadoEquipamientoCaldera.isEmpty()){
			return  null;
		}else{
			return listadoEquipamientoCaldera;
		}
	}
}
