package com.multimedia.aes.gestnet_nucleo.constantes;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.multimedia.aes.gestnet_nucleo.dao.AnalisisDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DisposicionesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.EnvioDAO;
import com.multimedia.aes.gestnet_nucleo.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ImagenDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ManoObraDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Configuracion;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Disposiciones;
import com.multimedia.aes.gestnet_nucleo.entidades.Envio;
import com.multimedia.aes.gestnet_nucleo.entidades.FormasPago;
import com.multimedia.aes.gestnet_nucleo.entidades.Imagen;
import com.multimedia.aes.gestnet_nucleo.entidades.ManoObra;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Marca;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.entidades.TipoCaldera;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.sql.SQLException;

public class BBDDConstantes {

	public static final String DATABASE_NAME = "gestnet_nucleo.db";
	public static final int DATABASE_VERSION = 9;

	public static Dao<Cliente, Integer> clienteDao;
	public static Dao<Usuario, Integer> usuarioDao;
	public static Dao<Parte, Integer> parteDao;
	public static Dao<ProtocoloAccion, Integer> protocoloDao;
	public static Dao<Configuracion, Integer> configuracionDao;
	public static Dao<Maquina, Integer> maquinaDao;
	public static Dao<DatosAdicionales, Integer> datosAdicionalesDao;
	public static Dao<Disposiciones, Integer> disposicionesDao;
	public static Dao<ManoObra, Integer> manoObrasDao;
	public static Dao<FormasPago, Integer> formasPagoDao;
	public static Dao<Articulo, Integer> articuloDao;
	public static Dao<Marca, Integer> marcaDao;
	public static Dao<TipoCaldera, Integer> tipoCalderaDao;
	public static Dao<ArticuloParte, Integer> tipoArticuloParteDao;
	public static Dao<Analisis, Integer> analisisDao;
	public static Dao<Imagen, Integer> imagenDao;
	public static Dao<Envio, Integer> envioDao;

	public static void cerrarDao() {
		clienteDao = null;
		usuarioDao = null;
		parteDao=null;
		protocoloDao=null;
		configuracionDao=null;
		maquinaDao=null;
		datosAdicionalesDao=null;
		disposicionesDao=null;
		manoObrasDao=null;
		formasPagoDao=null;
		articuloDao=null;
		marcaDao=null;
		tipoCalderaDao=null;
		tipoArticuloParteDao=null;
		analisisDao=null;
		imagenDao=null;
		envioDao=null;


	}

	public static void crearTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.createTable(connectionSource, Cliente.class);
		TableUtils.createTable(connectionSource, Usuario.class);
		TableUtils.createTable(connectionSource, Parte.class);
		TableUtils.createTable(connectionSource, ProtocoloAccion.class);
		TableUtils.createTable(connectionSource, Configuracion.class);
		TableUtils.createTable(connectionSource, Maquina.class);
		TableUtils.createTable(connectionSource, DatosAdicionales.class);
		TableUtils.createTable(connectionSource, Disposiciones.class);
		TableUtils.createTable(connectionSource, ManoObra.class);
		TableUtils.createTable(connectionSource, FormasPago.class);
		TableUtils.createTable(connectionSource, Articulo.class);
		TableUtils.createTable(connectionSource, Marca.class);
		TableUtils.createTable(connectionSource, TipoCaldera.class);
		TableUtils.createTable(connectionSource, ArticuloParte.class);
		TableUtils.createTable(connectionSource, Analisis.class);
		TableUtils.createTable(connectionSource, Imagen.class);
		TableUtils.createTable(connectionSource, Envio.class);
	}

	public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.dropTable(connectionSource, Cliente.class, true);
		TableUtils.dropTable(connectionSource, Usuario.class, true);
		TableUtils.dropTable(connectionSource, Parte.class, true);
		TableUtils.dropTable(connectionSource, ProtocoloAccion.class, true);
		TableUtils.dropTable(connectionSource, Configuracion.class, true);
		TableUtils.dropTable(connectionSource, Maquina.class, true);
		TableUtils.dropTable(connectionSource, DatosAdicionales.class, true);
		TableUtils.dropTable(connectionSource, Disposiciones.class, true);
		TableUtils.dropTable(connectionSource, ManoObra.class, true);
		TableUtils.dropTable(connectionSource, FormasPago.class, true);
		TableUtils.dropTable(connectionSource, Articulo.class, true);
		TableUtils.dropTable(connectionSource, Marca.class, true);
		TableUtils.dropTable(connectionSource, TipoCaldera.class, true);
		TableUtils.dropTable(connectionSource, ArticuloParte.class, true);
		TableUtils.dropTable(connectionSource, Analisis.class, true);
		TableUtils.dropTable(connectionSource, Imagen.class, true);
		TableUtils.dropTable(connectionSource, Envio.class, true);


	}

	public synchronized static void borrarDatosTablas(Context context) throws SQLException {

		ClienteDAO.borrarTodosLosClientes(context);
		UsuarioDAO.borrarTodosLosUsuarios(context);
		ParteDAO.borrarTodosLosPartes(context);
		ProtocoloAccionDAO.borrarTodosLosProtocolo(context);
		ConfiguracionDAO.borrarTodasLasConfiguraciones(context);
		MaquinaDAO.borrarTodasLasMaquinas(context);
		ManoObraDAO.borrarTodasLasManoDeObra(context);
		DatosAdicionalesDAO.borrarTodosLosDatosAdicionales(context);
		FormasPagoDAO.borrarTodasLasFormasPago(context);
		DisposicionesDAO.borrarTodasLasDisposiciones(context);
		ArticuloDAO.borrarTodosLosArticulos(context);
		MarcaDAO.borrarTodasLasMarcas(context);
		TipoCalderaDAO.borrarTodasLosTipoCaldera(context);
		ArticuloParteDAO.borrarTodosLosArticuloParte(context);
		AnalisisDAO.borrarTodasLasAnalisis(context);
		ImagenDAO.borrarTodasLasImagenes(context);
		EnvioDAO.borrarTodosLosEnvios(context);

	}
	public static void borrarDatosError(Context context) throws SQLException {
		UsuarioDAO.borrarTodosLosUsuarios(context);
		ParteDAO.borrarTodosLosPartes(context);
		ProtocoloAccionDAO.borrarTodosLosProtocolo(context);
		ConfiguracionDAO.borrarTodasLasConfiguraciones(context);
		MaquinaDAO.borrarTodasLasMaquinas(context);
		DatosAdicionalesDAO.borrarTodosLosDatosAdicionales(context);
		ManoObraDAO.borrarTodasLasManoDeObra(context);
		FormasPagoDAO.borrarTodasLasFormasPago(context);
		DisposicionesDAO.borrarTodasLasDisposiciones(context);
		ArticuloDAO.borrarTodosLosArticulos(context);
		MarcaDAO.borrarTodasLasMarcas(context);
		TipoCalderaDAO.borrarTodasLosTipoCaldera(context);
		ArticuloParteDAO.borrarTodosLosArticuloParte(context);
		AnalisisDAO.borrarTodasLasAnalisis(context);
		ImagenDAO.borrarTodasLasImagenes(context);
		EnvioDAO.borrarTodosLosEnvios(context);
	}
	public static void borrarDatosTablasPorDia(Context context) throws SQLException {
		ParteDAO.borrarTodosLosPartes(context);
		ProtocoloAccionDAO.borrarTodosLosProtocolo(context);
		MaquinaDAO.borrarTodasLasMaquinas(context);
		DatosAdicionalesDAO.borrarTodosLosDatosAdicionales(context);
		AnalisisDAO.borrarTodasLasAnalisis(context);
		//ArticuloDAO.borrarTodosLosArticulos(context);
		//ArticuloParteDAO.borrarTodosLosArticuloParte(context);

	}
}
