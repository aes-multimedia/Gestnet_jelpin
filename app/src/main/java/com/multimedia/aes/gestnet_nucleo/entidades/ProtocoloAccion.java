package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_protocolos_acciones")
public class ProtocoloAccion {

    public static final String ID_PROTOCOLO_ACCION = "_id_protocolo_accion";
    public static final String FK_MAQUINA = "fk_maquina";
    public static final String FK_PROTOCOLO = "fk_protocolo";
    public static final String DESCRIPCION = "descripcion";
    public static final String NOMBRE_PROTOCOLO = "nombre_protocolo";



    @DatabaseField(id = true, columnName = ID_PROTOCOLO_ACCION)    private int id_protocolo_accion;
    @DatabaseField(columnName = FK_MAQUINA)           private int fk_maquina;
    @DatabaseField(columnName = FK_PROTOCOLO)                   private int fk_protocolo;
    @DatabaseField(columnName = DESCRIPCION)                   private String descripcion;
    @DatabaseField(columnName = NOMBRE_PROTOCOLO)                   private String nombre_protocolo;

    public ProtocoloAccion(){}
    public ProtocoloAccion(int id_protocolo_accion, int fk_maquina, int fk_protocolo, String descripcion, String nombre_protocolo) {
        this.id_protocolo_accion = id_protocolo_accion;
        this.fk_maquina = fk_maquina;
        this.fk_protocolo = fk_protocolo;
        this.descripcion = descripcion;
        this.nombre_protocolo = nombre_protocolo;
    }

    public int getId_protocolo_accion() {
        return id_protocolo_accion;
    }

    public void setId_protocolo_accion(int id_protocolo_accion) {
        this.id_protocolo_accion = id_protocolo_accion;
    }

    public int getFk_maquina() {
        return fk_maquina;
    }

    public void setFk_maquina(int fk_maquina) {
        this.fk_maquina = fk_maquina;
    }

    public int getFk_protocolo() {
        return fk_protocolo;
    }

    public void setFk_protocolo(int fk_protocolo) {
        this.fk_protocolo = fk_protocolo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre_protocolo() {
        return nombre_protocolo;
    }

    public void setNombre_protocolo(String nombre_protocolo) {
        this.nombre_protocolo = nombre_protocolo;
    }
}