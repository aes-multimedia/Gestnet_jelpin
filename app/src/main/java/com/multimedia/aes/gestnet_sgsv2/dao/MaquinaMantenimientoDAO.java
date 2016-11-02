package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;
import com.multimedia.aes.gestnet_sgsv2.entities.MaquinaMantenimiento;

import java.sql.SQLException;
import java.util.List;

public class MaquinaMantenimientoDAO extends DBHelperMOS {
	static Dao<MaquinaMantenimiento, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMaquinaMantenimientoDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMaquinaMantenimiento(Context context,int id_maquina,int fk_parte, int fk_tipo_maquina, int fk_marca_maquina,
												  String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
												  String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
												  String temperatura_max_acs, String caudal_acs, String potencia_util,
												  String temperatura_gases_combustion, String temperatura_ambiente_local,
												  String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida,
												  String rendimiento_aparato, String co_corregido, String co_ambiente, String tiro, String co2, String o2, String lambda,int bPrincipal) {
		MaquinaMantenimiento m = montarMaquinaMantenimiento(id_maquina,fk_parte, fk_tipo_maquina, fk_marca_maquina,
				modelo_maquina, fk_potencia_maquina, fk_uso_maquina,
				puesta_marcha_maquina, codigo_maquina, c0_maquina,
				temperatura_max_acs, caudal_acs, potencia_util,
				temperatura_gases_combustion, temperatura_ambiente_local,
				temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida,
				rendimiento_aparato, co_corregido, co_ambiente, tiro, co2, o2, lambda,bPrincipal);
		return crearMaquinaMantenimiento(m,context);
	}
	public static boolean crearMaquinaMantenimiento(MaquinaMantenimiento m,Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static MaquinaMantenimiento montarMaquinaMantenimiento(int id_maquina,int fk_parte, int fk_tipo_maquina, int fk_marca_maquina,
																  String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
																  String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
																  String temperatura_max_acs, String caudal_acs, String potencia_util,
																  String temperatura_gases_combustion, String temperatura_ambiente_local,
																  String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida,
																  String rendimiento_aparato, String co_corregido, String co_ambiente, String tiro, String co2, String o2, String lambda,int bPrincipal) {
		MaquinaMantenimiento m =new MaquinaMantenimiento(id_maquina,fk_parte, fk_tipo_maquina, fk_marca_maquina,
				modelo_maquina, fk_potencia_maquina, fk_uso_maquina,
				puesta_marcha_maquina, codigo_maquina, c0_maquina,
				temperatura_max_acs, caudal_acs, potencia_util,
				temperatura_gases_combustion, temperatura_ambiente_local,
				temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida,
				rendimiento_aparato, co_corregido, co_ambiente, tiro, co2, o2, lambda,bPrincipal);
		return m;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodasLasMaquinaMantenimiento(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<MaquinaMantenimiento, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarMaquinaMantenimientoID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<MaquinaMantenimiento, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(MaquinaMantenimiento.ID_MAQUINA, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<MaquinaMantenimiento> buscarTodasLasMaquinaMantenimiento(Context context) throws SQLException {
		cargarDao(context);
		List<MaquinaMantenimiento> listadoMaquina= dao.queryForAll();
		if(listadoMaquina.isEmpty()) {
			return null;
		}else{
			return listadoMaquina;
		}
	}
	public static MaquinaMantenimiento buscarMaquinaMantenimientoPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<MaquinaMantenimiento> listadoMaquina= dao.queryForEq(MaquinaMantenimiento.ID_MAQUINA, id);
		if(listadoMaquina.isEmpty()){
			return  null;
		}else{
			return listadoMaquina.get(0);
		}
	}
	public static List<MaquinaMantenimiento> buscarMaquinaMantenimientoPorIdMantenimiento(Context context, int id) throws SQLException {
		cargarDao(context);
		List<MaquinaMantenimiento> listadoMaquina= dao.queryForEq(MaquinaMantenimiento.FK_PARTE, id);
		if(listadoMaquina.isEmpty()){
			return  null;
		}else{
			return listadoMaquina;
		}
	}
	public static MaquinaMantenimiento buscarMaquinaMantenimientoPorbprincipal(Context context, int id) throws SQLException {
		cargarDao(context);
		List<MaquinaMantenimiento> listadoMaquina= dao.queryForEq(MaquinaMantenimiento.ID_MAQUINA, id);
		MaquinaMantenimiento m=null;
		for (int i = 0; i < listadoMaquina.size(); i++) {
			if (listadoMaquina.get(i).getbPrincipal()==1){
				m=listadoMaquina.get(i);
			}
		}
		if(m==null){
			return  null;
		}else{
			return m;
		}
	}
}
