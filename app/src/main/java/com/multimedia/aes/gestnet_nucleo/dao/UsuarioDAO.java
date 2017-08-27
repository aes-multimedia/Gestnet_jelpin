package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO extends DBHelperMOS {
	static Dao<Usuario, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getUsuarioDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newUsuario(Context context, int id_usuario, int fk_cleinte, int fk_entidad, int fk_user, String nombre_usuario, String estado_activo, String api_key) {
		Usuario u = montarUsuario(id_usuario, fk_cleinte, fk_entidad, fk_user, nombre_usuario, estado_activo, api_key);
		return crearUsuario(u,context);
	}
	public static boolean crearUsuario(Usuario u,Context context) {
		try {
			cargarDao(context);
			dao.create(u);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Usuario montarUsuario(int id_usuario, int fk_cleinte, int fk_entidad, int fk_user, String nombre_usuario, String estado_activo, String api_key) {
		Usuario u =new Usuario(id_usuario, fk_cleinte, fk_entidad, fk_user, nombre_usuario, estado_activo, api_key);
		return u;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosUsuarios(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Usuario, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarUsuarioPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Usuario, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Usuario.ID_USUARIO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Usuario> buscarTodosLosUsuarios(Context context) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuarios= dao.queryForAll();
		if(listadoUsuarios.isEmpty()) {
			return null;
		}else{
			return listadoUsuarios;
		}
	}
	public static Usuario buscarUsuarioPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuarios= dao.queryForEq(Usuario.ID_USUARIO, id);
		if(listadoUsuarios.isEmpty()) {
			return null;
		}else{
			return listadoUsuarios.get(0);
		}
	}


	public static Usuario buscarUsuarioPorFkEntidad(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuarios= dao.queryForEq(Usuario.FK_ENTIDAD, id);
		if(listadoUsuarios.isEmpty()) {
			return null;
		}else{
			return listadoUsuarios.get(0);
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


	public static void actualizarUsuario(Context context, Usuario usuario ) throws SQLException
	{
		int id = usuario.getId_usuario();
		int fk_cliente  = usuario.getFk_cliente();
		int fk_entidad = usuario.getFk_entidad();
		int fk_user = usuario.getFk_user();
		String nombre_usuario = usuario.getNombreUsuario();
		String estado_activo = usuario.getEstado_activo();
		String api_key = usuario.getApi_key();


		cargarDao(context);
		UpdateBuilder<Usuario, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(usuario.ID_USUARIO,id);
		updateBuilder.updateColumnValue(usuario.FK_CLIENTE, fk_cliente);
		updateBuilder.updateColumnValue(usuario.FK_ENTIDAD, fk_entidad);
		updateBuilder.updateColumnValue(usuario.FK_USER, fk_user);
		updateBuilder.updateColumnValue(usuario.NOMBRE_USUARIO, nombre_usuario);
		updateBuilder.updateColumnValue(usuario.ESTADO_ACTIVO, estado_activo);
		updateBuilder.updateColumnValue(usuario.API_KEY, api_key);
		updateBuilder.update();
	}
}
