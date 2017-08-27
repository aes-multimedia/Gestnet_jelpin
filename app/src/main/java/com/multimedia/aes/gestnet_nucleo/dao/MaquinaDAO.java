package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;

import java.sql.SQLException;
import java.util.List;

public class MaquinaDAO extends DBHelperMOS {
	static Dao<Maquina, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMaquinaDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMaquina(Context context,int id_maquina, int fk_direccion, int fk_modelo, int fk_marca, int fk_tipo_combustion,
									 int fk_protocolo, int fk_instalador, int fk_remoto_central, int fk_tipo, int fk_instalacion,
									 int fk_estado, int fk_contrato_mantenimiento, int fk_gama, int fk_tipo_gama,
									 String fecha_creacion, String modelo, String num_serie, String num_producto, String aparato,
									 String puesta_marcha, String fecha_compra, String fecha_fin_garantia,
									 String mantenimiento_anual, String observaciones, String ubicacion, String tienda_compra,
									 String garantia_extendida, String factura_compra, String refrigerante,
									 boolean bEsInstalacion, String nombre_instalacion, String en_propiedad, String esPrincipal,String situacion) {
		Maquina m = montarMaquina(id_maquina,   fk_direccion,   fk_modelo,   fk_marca,   fk_tipo_combustion,
				fk_protocolo,   fk_instalador,   fk_remoto_central,   fk_tipo,   fk_instalacion,
				fk_estado,   fk_contrato_mantenimiento,   fk_gama,   fk_tipo_gama,
				fecha_creacion,   modelo,   num_serie,   num_producto,   aparato,
				puesta_marcha,   fecha_compra,   fecha_fin_garantia,
				mantenimiento_anual,   observaciones,   ubicacion,   tienda_compra,
				garantia_extendida,   factura_compra,   refrigerante,
				bEsInstalacion,   nombre_instalacion,   en_propiedad,   esPrincipal, situacion);
		return crearMaquina(m, context);
	}

	public static boolean crearMaquina(Maquina m, Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Maquina montarMaquina(int id_maquina, int fk_direccion, int fk_modelo, int fk_marca, int fk_tipo_combustion,
										int fk_protocolo, int fk_instalador, int fk_remoto_central, int fk_tipo, int fk_instalacion,
										int fk_estado, int fk_contrato_mantenimiento, int fk_gama, int fk_tipo_gama,
										String fecha_creacion, String modelo, String num_serie, String num_producto, String aparato,
										String puesta_marcha, String fecha_compra, String fecha_fin_garantia,
										String mantenimiento_anual, String observaciones, String ubicacion, String tienda_compra,
										String garantia_extendida, String factura_compra, String refrigerante,
										boolean bEsInstalacion, String nombre_instalacion, String en_propiedad, String esPrincipal,String situacion) {
		Maquina m = new Maquina(id_maquina,   fk_direccion,   fk_modelo,   fk_marca,   fk_tipo_combustion,
				fk_protocolo,   fk_instalador,   fk_remoto_central,   fk_tipo,   fk_instalacion,
				fk_estado,   fk_contrato_mantenimiento,   fk_gama,   fk_tipo_gama,
				fecha_creacion,   modelo,   num_serie,   num_producto,   aparato,
				puesta_marcha,   fecha_compra,   fecha_fin_garantia,
				mantenimiento_anual,   observaciones,   ubicacion,   tienda_compra,
				garantia_extendida,   factura_compra,   refrigerante,
				bEsInstalacion,   nombre_instalacion,   en_propiedad,   esPrincipal, situacion);
		return m;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodasLasMaquinas(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Maquina, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}

	public static void borrarMaquinaPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Maquina, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Maquina.ID_MAQUINA, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Maquina> buscarTodasLasMaquinas(Context context) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquinas = dao.queryForAll();
		if (listadoMaquinas.isEmpty()) {
			return null;
		} else {
			return listadoMaquinas;
		}
	}

	public static Maquina buscarMaquinaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquinas = dao.queryForEq(Maquina.ID_MAQUINA, id);
		if (listadoMaquinas.isEmpty()) {
			return null;
		} else {
			return listadoMaquinas.get(0);
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


}
