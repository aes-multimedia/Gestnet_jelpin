package com.multimedia.aes.gestnet_sgsv2.constants;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.multimedia.aes.gestnet_sgsv2.dao.AveriaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Averia;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import java.sql.SQLException;

public class BBDDConstantes {

	public static final String DATABASE_NAME = "gestnet_sgs.db";
	public static final int DATABASE_VERSION = 1;

	public static Dao<Tecnico, Integer> tecnicoDao;
	public static Dao<Averia, Integer> averiaDao;
	public static Dao<Mantenimiento, Integer> mantenimientoDao;

	public static void cerrarDao() {
		tecnicoDao = null;
		averiaDao = null;
		mantenimientoDao = null;
	}

	public static void crearTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.createTable(connectionSource, Tecnico.class);
		TableUtils.createTable(connectionSource, Averia.class);
		TableUtils.createTable(connectionSource, Mantenimiento.class);

	}

	public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.dropTable(connectionSource, Tecnico.class, true);
		TableUtils.dropTable(connectionSource, Averia.class, true);
		TableUtils.dropTable(connectionSource, Mantenimiento.class, true);

	}

	public static void borrarDatosTablas(Context context) throws SQLException {
		TecnicoDAO.borrarTodosLosTecnicos(context);
		AveriaDAO.borrarTodosLasAverias(context);
		MantenimientoDAO.borrarTodosLosMantenimientos(context);

	}
}
