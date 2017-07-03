package com.multimedia.aes.gestnet_nucleo.constantes;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.multimedia.aes.gestnet_nucleo.dao.AveriaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.EquipamientoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.EstadoVisitaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ImagenesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MotivosNoRepDAO;
import com.multimedia.aes.gestnet_nucleo.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.SubTiposVisitaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.TipoEquipamientoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.TipoEstadoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsoCalderaDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Mantenimiento;

import java.sql.SQLException;

public class BBDDConstantes {

	public static final String DATABASE_NAME = "gestnet_sgs.db";
	public static final int DATABASE_VERSION = 2;

	public static Dao<Mantenimiento, Integer> mantenimientoDao;

	public static void cerrarDao() {
		mantenimientoDao = null;
	}

	public static void crearTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.createTable(connectionSource, Mantenimiento.class);
	}

	public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.dropTable(connectionSource, Mantenimiento.class, true);
	}

	public static void borrarDatosTablas(Context context) throws SQLException {
		MantenimientoDAO.borrarTodosLosMantenimientos(context);
	}
}
