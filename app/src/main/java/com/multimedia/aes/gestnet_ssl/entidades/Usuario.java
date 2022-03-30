package com.multimedia.aes.gestnet_ssl.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_usuarios")
public class Usuario {

    public static final String ID_USUARIO = "_id_usuario";
    public static final String FK_CLIENTE = "fk_cliente";
    public static final String FK_ENTIDAD = "fk_entidad";
    public static final String FK_USER = "fk_user";
    public static final String NOMBRE_USUARIO = "nombre_usuario";
    public static final String ESTADO_ACTIVO = "estado_activo";
    public static final String API_KEY = "api_key";
    public static final String FIRMA = "firma";


    @DatabaseField(id = true, columnName = ID_USUARIO)  private int id_usuario;
    @DatabaseField(columnName = FK_CLIENTE)             private int fk_cliente;
    @DatabaseField(columnName = FK_ENTIDAD)             private int fk_entidad;
    @DatabaseField(columnName = FK_USER)                private int fk_user;
    @DatabaseField(columnName = NOMBRE_USUARIO)         private String nombre_usuario;
    @DatabaseField(columnName = ESTADO_ACTIVO)          private String estado_activo;
    @DatabaseField(columnName = API_KEY)                private String api_key;
    @DatabaseField(columnName = FIRMA)                  private String firma;

    public Usuario() {
    }


    public Usuario(int id_usuario, int fk_cliente, int fk_entidad, int fk_user, String nombre_usuario, String estado_activo, String api_key,String firma) {
        this.id_usuario = id_usuario;
        this.fk_cliente = fk_cliente;
        this.fk_entidad = fk_entidad;
        this.fk_user = fk_user;
        this.nombre_usuario = nombre_usuario;
        this.estado_activo = estado_activo;
        this.api_key = api_key;
        this.firma = firma;
    }


    public static String getIdUsuario() {
        return ID_USUARIO;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public int getFk_cliente() {
        return fk_cliente;
    }
    public int getFk_entidad() {
        return fk_entidad;
    }
    public int getFk_user() {
        return fk_user;
    }
    public String getNombreUsuario() {
        return nombre_usuario;
    }
    public String getEstado_activo() {
        return estado_activo;
    }
    public String getApi_key() {
        return api_key;
    }
    public void setFk_cliente(int fk_cliente) {
        this.fk_cliente = fk_cliente;
    }
    public void setFk_entidad(int fk_entidad) {
        this.fk_entidad = fk_entidad;
    }
    public void setFk_user(int fk_user) {
        this.fk_user = fk_user;
    }
    public void setNombreUsuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public void setEstado_activo(String estado_activo) {
        this.estado_activo = estado_activo;
    }
    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
    public String getFirma() {
        return firma;
    }
    public void setFirma(String firma) {
        this.firma = firma;
    }
}
