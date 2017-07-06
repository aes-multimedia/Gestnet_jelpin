package com.multimedia.aes.gestnet_nucleo.constantes;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;

import java.sql.SQLException;

public class BBDDConstantes {

	public static final String DATABASE_NAME = "gestnet_nucleo.db";
	public static final int DATABASE_VERSION = 1;

	public static Dao<Cliente, Integer> clienteDao;

	public static void cerrarDao() {
		clienteDao = null;
	}

	public static void crearTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.createTable(connectionSource, Cliente.class);
	}

	public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.dropTable(connectionSource, Cliente.class, true);
	}

	public static void borrarDatosTablas(Context context) throws SQLException {
		ClienteDAO.borrarTodosLosClientes(context);
	}
}
