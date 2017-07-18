package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_acciones")
public class Acciones {

    public static final String ID_ACCION = "_id_protocolos";
    public static final String FK_PROTOCOLO = "fk_protocolo";
    public static final String DESCRIPCION_ACCION = "descripcion_accion";
    public static final String PERIOCIDAD_ACCION = "periocidad_accion";
    public static final String TIPO_ACCION = "tipo_accion";



    @DatabaseField(id = true, columnName = ID_ACCION)
    private int id_protocolos;
    @DatabaseField(columnName = FK_PROTOCOLO)
    private int fk_protocolo;
    @DatabaseField(columnName = DESCRIPCION_ACCION)
    private String descripcion_accion;
    @DatabaseField(columnName = PERIOCIDAD_ACCION)
    private String periocidad_accion;
    @DatabaseField(columnName = TIPO_ACCION)
    private String tipo_accion;
    public Acciones(){}
    public Acciones(int id_protocolos, int fk_protocolo, String descripcion_accion, String periocidad_accion,
                    String tipo_accion) {
        this.id_protocolos = id_protocolos;
        this.fk_protocolo = fk_protocolo;
        this.descripcion_accion = descripcion_accion;
        this.periocidad_accion = periocidad_accion;
        this.tipo_accion = tipo_accion;
    }

    public int getId_protocolos() {
        return id_protocolos;
    }
    public void setId_protocolos(int id_protocolos) {
        this.id_protocolos = id_protocolos;
    }
    public int getFk_protocolo() {
        return fk_protocolo;
    }
    public void setFk_protocolo(int fk_protocolo) {
        this.fk_protocolo = fk_protocolo;
    }
    public String getDescripcion_accion() {
        return descripcion_accion;
    }
    public void setDescripcion_accion(String descripcion_accion) {
        this.descripcion_accion = descripcion_accion;
    }
    public String getPeriocidad_accion() {
        return periocidad_accion;
    }
    public void setPeriocidad_accion(String periocidad_accion) {
        this.periocidad_accion = periocidad_accion;
    }
    public String getTipo_accion() {
        return tipo_accion;
    }
    public void setTipo_accion(String tipo_accion) {
        this.tipo_accion = tipo_accion;
    }
}