package com.multimedia.aes.gestnet_ssl.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_clientes")
public class Cliente {

    public static final String ID_CLIENTE = "_id_cliente";
    public static final String NOMBRE_CLIENTE = "nombre_cliente";
    public static final String COLOR_CLIENTE = "color_cliente";
    public static final String LOGO_CLIENTE = "logo_cliente";
    public static final String DIR_DOCUMENTOS="dir_documentos";
    public static final String IP_CLIENTE = "ip_cliente";
    public static final String COD_CLIENTE = "cod_cliente";
    public static final String PROTECCION_DATOS = "proteccion_datos";


    @DatabaseField(id = true, columnName = ID_CLIENTE)  private int id_cliente;
    @DatabaseField(columnName = NOMBRE_CLIENTE)         private String nombre_cliente;
    @DatabaseField(columnName = COLOR_CLIENTE)          private String color_cliente;
    @DatabaseField(columnName = LOGO_CLIENTE)           private String logo_cliente;
    @DatabaseField(columnName = IP_CLIENTE)             private String ip_cliente;
    @DatabaseField(columnName = COD_CLIENTE)            private String cod_cliente;
    @DatabaseField(columnName = DIR_DOCUMENTOS)         private String dir_documentos;
    @DatabaseField(columnName = PROTECCION_DATOS)       private String proteccion_datos;

    public Cliente (){}
    public Cliente(int id_cliente, String nombre_cliente, String color_cliente, String logo_cliente, String ip_cliente, String cod_cliente,String dir_documentos,String proteccion_datos) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.color_cliente = color_cliente;
        this.logo_cliente = logo_cliente;
        this.ip_cliente = ip_cliente;
        this.cod_cliente = cod_cliente;
        this.dir_documentos = dir_documentos;
        this.proteccion_datos = proteccion_datos;
    }

    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    public String getNombre_cliente() {
        return nombre_cliente;
    }
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }
    public String getColor_cliente() {
        return color_cliente;
    }
    public void setColor_cliente(String color_cliente) {
        this.color_cliente = color_cliente;
    }
    public String getLogo_cliente() {
        return logo_cliente;
    }
    public void setLogo_cliente(String logo_cliente) {
        this.logo_cliente = logo_cliente;
    }
    public String getIp_cliente() {
        return ip_cliente;
    }
    public void setIp_cliente(String ip_cliente) {
        this.ip_cliente = ip_cliente;
    }
    public String getCod_cliente() {
        return cod_cliente;
    }
    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }
    public String getDir_documentos() {
        return dir_documentos;
    }
    public void setDir_documentos(String dir_documentos) {
        this.dir_documentos = dir_documentos;
    }
    public String getProteccion_datos() {
        return proteccion_datos;
    }
    public void setProteccion_datos(String proteccion_datos) {
        this.proteccion_datos = proteccion_datos;
    }
}