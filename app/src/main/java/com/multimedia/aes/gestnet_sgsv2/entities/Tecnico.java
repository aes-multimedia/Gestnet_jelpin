package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_tecnicos")
public class Tecnico {

    public static final String ID_TECNICO = "_id_tecnico";
    public static final String NOMBRE_TECNICO = "nombre_usuario";
    public static final String LOGIN_TECNICO = "login_usuario";
    public static final String EMAIL = "email";
    public static final String NUM_TECNICO = "num_tecnico";
    public static final String FK_EMPRESA = "fk_empresa";
    public static final String FK_ALMACEN = "fk_almacen";
    public static final String FK_COMPAÑIA = "fk_compañia";
    public static final String FK_DEPARTAMENTO = "fk_departamento";
    public static final String APAIKEY = "apikey";

    @DatabaseField(id = true, columnName = ID_TECNICO)
    private int id_tecnico;
    @DatabaseField(columnName = NOMBRE_TECNICO)
    private String nombre_usuario;
    @DatabaseField(columnName = LOGIN_TECNICO)
    private String login_usuario;
    @DatabaseField(columnName = EMAIL)
    private String email;
    @DatabaseField(columnName = NUM_TECNICO)
    private String num_tecnico;
    @DatabaseField(columnName = FK_EMPRESA)
    private String fk_empresa;
    @DatabaseField(columnName = FK_ALMACEN)
    private String fk_almacen;
    @DatabaseField(columnName = FK_COMPAÑIA)
    private String fk_compañia;
    @DatabaseField(columnName = FK_DEPARTAMENTO)
    private String fk_departamento;
    @DatabaseField(columnName = APAIKEY)
    private String apikey;


    public Tecnico() {
    }

    public Tecnico(int id_tecnico, String nombre_usuario, String login_usuario, String email, String num_tecnico,
                   String fk_empresa, String fk_almacen, String fk_compañia, String fk_departamento, String apikey) {
        this.id_tecnico = id_tecnico;
        this.nombre_usuario = nombre_usuario;
        this.login_usuario = login_usuario;
        this.email = email;
        this.num_tecnico = num_tecnico;
        this.fk_empresa = fk_empresa;
        this.fk_almacen = fk_almacen;
        this.fk_compañia = fk_compañia;
        this.fk_departamento = fk_departamento;
        this.apikey = apikey;
    }

    public int getId_tecnico() {
        return id_tecnico;
    }
    public void setId_tecnico(int id_tecnico) {
        this.id_tecnico = id_tecnico;
    }
    public String getNombre_usuario() {
        return nombre_usuario;
    }
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public String getLogin_usuario() {
        return login_usuario;
    }
    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNum_tecnico() {
        return num_tecnico;
    }
    public void setNum_tecnico(String num_tecnico) {
        this.num_tecnico = num_tecnico;
    }
    public String getFk_empresa() {
        return fk_empresa;
    }
    public void setFk_empresa(String fk_empresa) {
        this.fk_empresa = fk_empresa;
    }
    public String getFk_almacen() {
        return fk_almacen;
    }
    public void setFk_almacen(String fk_almacen) {
        this.fk_almacen = fk_almacen;
    }
    public String getFk_compañia() {
        return fk_compañia;
    }
    public void setFk_compañia(String fk_compañia) {
        this.fk_compañia = fk_compañia;
    }
    public String getFk_departamento() {
        return fk_departamento;
    }
    public void setFk_departamento(String fk_departamento) {
        this.fk_departamento = fk_departamento;
    }
    public String getApikey() {
        return apikey;
    }
    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
