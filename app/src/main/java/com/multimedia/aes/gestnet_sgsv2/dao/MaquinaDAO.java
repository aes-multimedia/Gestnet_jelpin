package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

import java.sql.SQLException;
import java.util.List;

public class MaquinaDAO extends DBHelperMOS {
	static Dao<Maquina, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMaquinaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMaquina(Context context,Maquina m) {
		return crearMaquina(m,context);
	}
	public static boolean crearMaquina(Maquina m,Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Maquina montarMaquina() {
		Maquina m =new Maquina();
		return m;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodasLasMaquinas(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Maquina, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarMaquinaID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Maquina, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Maquina.ID_MAQUINA, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Maquina> buscarTodasLasMaquinas(Context context) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquina= dao.queryForAll();
		if(listadoMaquina.isEmpty()) {
			return null;
		}else{
			return listadoMaquina;
		}
	}
	public static Maquina buscarMaquinaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquina= dao.queryForEq(Maquina.ID_MAQUINA, id);
		if(listadoMaquina.isEmpty()){
			return  null;
		}else{
			return listadoMaquina.get(0);
		}
	}
	public static List<Maquina> buscarMaquinaPorFkMantenimiento(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquina= dao.queryForEq(Maquina.FK_MANTENIMIENTO, id);
		if(listadoMaquina.isEmpty()){
			return  null;
		}else{
			return listadoMaquina;
		}
	}
}
