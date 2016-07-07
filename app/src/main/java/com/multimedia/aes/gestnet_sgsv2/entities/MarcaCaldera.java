package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_marca_calderas")
public class MarcaCaldera {

    public static final String ID_MARCA_CALDERA = "_id_marca_caldera";
    public static final String NOMBRE_MARCA_CALDERA = "nombre_marca_caldera";

    @DatabaseField(id = true, columnName = ID_MARCA_CALDERA)
    private int id_marca_caldera;
    @DatabaseField(columnName = NOMBRE_MARCA_CALDERA)
    private String nombre_marca_caldera;


    public MarcaCaldera() {
    }

    public MarcaCaldera(int id_marca_caldera, String nombre_marca_caldera) {
        this.id_marca_caldera = id_marca_caldera;
        this.nombre_marca_caldera = nombre_marca_caldera;
    }

    public int getId_marca_caldera() {
        return id_marca_caldera;
    }

    public void setId_marca_caldera(int id_marca_caldera) {
        this.id_marca_caldera = id_marca_caldera;
    }

    public String getNombre_marca_caldera() {
        return nombre_marca_caldera;
    }

    public void setNombre_marca_caldera(String nombre_marca_caldera) {
        this.nombre_marca_caldera = nombre_marca_caldera;
    }
}
