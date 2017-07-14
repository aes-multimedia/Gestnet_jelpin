package com.multimedia.aes.gestnet_nucleo.constantes;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.sql.SQLException;

public class BBDDConstantes {

	public static final String DATABASE_NAME = "gestnet_nucleo.db";
	public static final int DATABASE_VERSION = 1;

	public static Dao<Cliente, Integer> clienteDao;
	public static Dao<Usuario, Integer> usuarioDao;
	public static Dao<Parte, Integer> parteDao;

	public static void cerrarDao() {
		clienteDao = null;
		usuarioDao = null;
		parteDao=null;
	}

	public static void crearTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.createTable(connectionSource, Cliente.class);
		TableUtils.createTable(connectionSource, Usuario.class);
		TableUtils.createTable(connectionSource, Parte.class);
	}

	public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.dropTable(connectionSource, Cliente.class, true);
		TableUtils.dropTable(connectionSource, Usuario.class, true);
		TableUtils.dropTable(connectionSource, Parte.class, true);
	}

	public static void borrarDatosTablas(Context context) throws SQLException {
		ClienteDAO.borrarTodosLosClientes(context);
		UsuarioDAO.borrarTodosLosUsuarios(context);
		ParteDAO.borrarTodosLosPartes(context);
	}
}
