package com.multimedia.aes.gestnet_nucleo.constantes;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Configuracion;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.sql.SQLException;

public class BBDDConstantes {

	public static final String DATABASE_NAME = "gestnet_nucleo.db";
	public static final int DATABASE_VERSION = 1;

	public static Dao<Cliente, Integer> clienteDao;
	public static Dao<Usuario, Integer> usuarioDao;
	public static Dao<Parte, Integer> parteDao;
	public static Dao<ProtocoloAccion, Integer> protocoloDao;
	public static Dao<Configuracion, Integer> configuracionDao;
	public static Dao<Maquina, Integer> maquinaDao;

	public static void cerrarDao() {
		clienteDao = null;
		usuarioDao = null;
		parteDao=null;
		protocoloDao=null;
		configuracionDao=null;
		maquinaDao=null;
	}

	public static void crearTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.createTable(connectionSource, Cliente.class);
		TableUtils.createTable(connectionSource, Usuario.class);
		TableUtils.createTable(connectionSource, Parte.class);
		TableUtils.createTable(connectionSource, ProtocoloAccion.class);
		TableUtils.createTable(connectionSource, Configuracion.class);
		TableUtils.createTable(connectionSource, Maquina.class);
	}

	public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.dropTable(connectionSource, Cliente.class, true);
		TableUtils.dropTable(connectionSource, Usuario.class, true);
		TableUtils.dropTable(connectionSource, Parte.class, true);
		TableUtils.dropTable(connectionSource, ProtocoloAccion.class, true);
		TableUtils.dropTable(connectionSource, Configuracion.class, true);
		TableUtils.dropTable(connectionSource, Maquina.class, true);
	}

	public static void borrarDatosTablas(Context context) throws SQLException {
		ClienteDAO.borrarTodosLosClientes(context);
		UsuarioDAO.borrarTodosLosUsuarios(context);
		ParteDAO.borrarTodosLosPartes(context);
		ProtocoloAccionDAO.borrarTodosLosProtocolo(context);
		ConfiguracionDAO.borrarTodasLasConfiguraciones(context);
		MaquinaDAO.borrarTodasLasMaquinas(context);
	}
	public static void borrarDatosError(Context context) throws SQLException {
		UsuarioDAO.borrarTodosLosUsuarios(context);
		ParteDAO.borrarTodosLosPartes(context);
		ProtocoloAccionDAO.borrarTodosLosProtocolo(context);
		ConfiguracionDAO.borrarTodasLasConfiguraciones(context);
		MaquinaDAO.borrarTodasLasMaquinas(context);
	}
}
