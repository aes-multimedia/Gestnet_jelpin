package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class TiposVisita {

    public static final String ID_TIPO_VISITA = "_id_tipo_visita";
    public static final String DESCRIPCION = "descripcion";
    public static final String SUB_TIPOS = "bSubTipos";


    @DatabaseField(id = true, columnName = ID_TIPO_VISITA)
    private int id_tipo_visita;
    @DatabaseField(columnName = DESCRIPCION)
    private String descripcion;
    @DatabaseField(columnName = SUB_TIPOS)
    private int bSubTipos;


    public TiposVisita() {
    }

    public TiposVisita(int id_tipo, String descripcion, int bSubTipos) {
        this.id_tipo_visita = id_tipo;
        this.descripcion = descripcion;
        this.bSubTipos = bSubTipos;

    }

    public int getbSubTipos() {
        return bSubTipos;
    }

    public void setbSubTipos(int bSubTipos) {
        this.bSubTipos = bSubTipos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_tipo_visita() {
        return id_tipo_visita;
    }

    public void setId_tipo_visita(int id_tipo_visita) {
        this.id_tipo_visita = id_tipo_visita;
    }
}

