package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by acp on 29/12/2017.
 */

public class TipoCaldera {



    public static final String ID_TIPO_COMBUSTION  = "_id_tipo_combustion";
    public static final String NOMBRE_TIPO_COMBUSTION  = "nombre_tipo_combustion";


    @DatabaseField(id = true, columnName = ID_TIPO_COMBUSTION)      private int id_tipo_combustion;
    @DatabaseField(columnName = NOMBRE_TIPO_COMBUSTION)               private String nombre_tipo_combustion;


    public TipoCaldera() {
    }

    public TipoCaldera(int id_tipo_combustion, String nombre_tipo_combustion) {
        this.id_tipo_combustion = id_tipo_combustion;
        this.nombre_tipo_combustion = nombre_tipo_combustion;
    }

    public int getId_tipo_combustion() {
        return id_tipo_combustion;
    }

    public void setId_tipo_combustion(int id_tipo_combustion) {
        this.id_tipo_combustion = id_tipo_combustion;
    }

    public String getNombre_tipo_combustion() {
        return nombre_tipo_combustion;
    }

    public void setNombre_tipo_combustion(String nombre_tipo_combustion) {
        this.nombre_tipo_combustion = nombre_tipo_combustion;
    }
}
