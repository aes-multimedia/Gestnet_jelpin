package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_protocolos")
public class Protocolo {

    public static final String ID_PROTOCOLO = "_id_protocolos";
    public static final String NOMBRE_PROTOCOLO = "nombre_protocolos";
    public static final String BGENERAL = "b_general";



    @DatabaseField(id = true, columnName = ID_PROTOCOLO)    private int id_protocolos;
    @DatabaseField(columnName = NOMBRE_PROTOCOLO)           private String nombre_protocolos;
    @DatabaseField(columnName = BGENERAL)                   private String b_general;

    public Protocolo(){}
    public Protocolo(int id_protocolos, String nombre_protocolos, String b_general) {
        this.id_protocolos = id_protocolos;
        this.nombre_protocolos = nombre_protocolos;
        this.b_general = b_general;
    }

    public int getId_protocolos() {
        return id_protocolos;
    }
    public void setId_protocolos(int id_protocolos) {
        this.id_protocolos = id_protocolos;
    }
    public String getNombre_protocolos() {
        return nombre_protocolos;
    }
    public void setNombre_protocolos(String nombre_protocolos) {
        this.nombre_protocolos = nombre_protocolos;
    }
    public String getB_general() {
        return b_general;
    }
    public void setB_general(String b_general) {
        this.b_general = b_general;
    }
}