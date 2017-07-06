package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_usuarios")
public class Usuario {

    public static final String ID_USUARIO = "_id_usuario";
    public static final String FK_CLIENTE = "fk_cliente";
    public static final String FK_ENTIDAD = "fk_entidad";
    public static final String FK_USER = "fk_user";
    public static final String USUARIO = "usuario";
    public static final String ESTADO_ACTIVO = "estado_activo";
    public static final String API_KEY = "api_key";


    @DatabaseField(id = true, columnName = ID_USUARIO)
    private int id_usuario;
    @DatabaseField(columnName = FK_CLIENTE)
    private String fk_cleinte;
    @DatabaseField(columnName = FK_ENTIDAD)
    private String fk_entidad;
    @DatabaseField(columnName = FK_USER)
    private String fk_user;
    @DatabaseField(columnName = USUARIO)
    private String usuario;
    @DatabaseField(columnName = ESTADO_ACTIVO)
    private String estado_activo;
    @DatabaseField(columnName = API_KEY)
    private String api_key;

    public Usuario() {
    }


    public Usuario(int id_usuario, String fk_cleinte, String fk_entidad, String fk_user, String usuario, String estado_activo, String api_key) {
        this.id_usuario = id_usuario;
        this.fk_cleinte = fk_cleinte;
        this.fk_entidad = fk_entidad;
        this.fk_user = fk_user;
        this.usuario = usuario;
        this.estado_activo = estado_activo;
        this.api_key = api_key;
    }


    public static String getIdUsuario() {
        return ID_USUARIO;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getFk_cleinte() {
        return fk_cleinte;
    }

    public String getFk_entidad() {
        return fk_entidad;
    }

    public String getFk_user() {
        return fk_user;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEstado_activo() {
        return estado_activo;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setFk_cleinte(String fk_cleinte) {
        this.fk_cleinte = fk_cleinte;
    }

    public void setFk_entidad(String fk_entidad) {
        this.fk_entidad = fk_entidad;
    }

    public void setFk_user(String fk_user) {
        this.fk_user = fk_user;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setEstado_activo(String estado_activo) {
        this.estado_activo = estado_activo;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
