package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class TipoEstado {

    public static final String ID_TIPO = "_id_tipo";
    public static final String NOMBRE_ESTADO = "nombre_estado";

    @DatabaseField(id = true, columnName = ID_TIPO)
    private int id_tipo;
    @DatabaseField(columnName = NOMBRE_ESTADO)
    private String nombre_estado;


    public TipoEstado() {
    }

    public TipoEstado(int id_tipo, String nombre_estado) {
        this.id_tipo = id_tipo;
        this.nombre_estado = nombre_estado;
    }

    public int getId_tipo_estado() {
        return id_tipo;
    }

    public void setId_tipo_estado(int id_tipo) {

        this.id_tipo = id_tipo;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }
}

