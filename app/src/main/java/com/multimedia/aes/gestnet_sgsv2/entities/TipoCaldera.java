package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_tipo_caldera")
public class TipoCaldera {

    public static final String ID_TIPO_CALDERA = "_id_tipo_caldera";
    public static final String NOMBRE_TIPO_CALDERA = "nombre_tipo_caldera";

    @DatabaseField(id = true, columnName = ID_TIPO_CALDERA)
    private int id_tipo_caldera;
    @DatabaseField(columnName = NOMBRE_TIPO_CALDERA)
    private String nombre_tipo_caldera;


    public TipoCaldera() {
    }

    public TipoCaldera(int id_tipo_caldera, String nombre_tipo_caldera) {
        this.id_tipo_caldera = id_tipo_caldera;
        this.nombre_tipo_caldera = nombre_tipo_caldera;
    }

    public int getId_tipo_caldera() {
        return id_tipo_caldera;
    }

    public void setId_tipo_caldera(int id_tipo_caldera) {
        this.id_tipo_caldera = id_tipo_caldera;
    }

    public String getNombre_tipo_caldera() {
        return nombre_tipo_caldera;
    }

    public void setNombre_tipo_caldera(String nombre_tipo_caldera) {
        this.nombre_tipo_caldera = nombre_tipo_caldera;
    }
}
