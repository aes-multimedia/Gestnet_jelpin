package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_estado_visita")
public class EstadoVisita {

    public static final String ID_ESTADO_VISITA = "_id_estado_visita";
    public static final String NOMBRE_ESTADO_VISITA = "nombre_estado_visita";
    public static final String CODIGO_COMPAÑIA = "codigo_compania";

    @DatabaseField(id = true, columnName = ID_ESTADO_VISITA)
    private int id_estado_visita;
    @DatabaseField(columnName = NOMBRE_ESTADO_VISITA)
    private String nombre_estado_visita;
    @DatabaseField(columnName = CODIGO_COMPAÑIA)
    private String codigo_compania;


    public EstadoVisita() {
    }

    public EstadoVisita(int id_estado_visita, String nombre_estado_visita, String codigo_compania) {
        this.id_estado_visita = id_estado_visita;
        this.nombre_estado_visita = nombre_estado_visita;
        this.codigo_compania = codigo_compania;
    }

    public int getId_estado_visita() {
        return id_estado_visita;
    }
    public void setId_estado_visita(int id_estado_visita) {
        this.id_estado_visita = id_estado_visita;
    }
    public String getNombre_estado_visita() {
        return nombre_estado_visita;
    }
    public void setNombre_estado_visita(String nombre_estado_visita) {
        this.nombre_estado_visita = nombre_estado_visita;
    }
    public String getCodigo_compania() {
        return codigo_compania;
    }
    public void setCodigo_compania(String codigo_compania) {
        this.codigo_compania = codigo_compania;
    }
}
