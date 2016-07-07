package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_uso_caldera")
public class UsoCaldera {

    public static final String ID_USO_CALDERA = "_id_uso_caldera";
    public static final String NOMBRE_USO_CALDERA = "nombre_uso_caldera";

    @DatabaseField(id = true, columnName = ID_USO_CALDERA)
    private int id_uso_caldera;
    @DatabaseField(columnName = NOMBRE_USO_CALDERA)
    private String nombre_uso_caldera;


    public UsoCaldera() {
    }

    public UsoCaldera(int id_uso_caldera, String nombre_uso_caldera) {
        this.id_uso_caldera = id_uso_caldera;
        this.nombre_uso_caldera = nombre_uso_caldera;
    }

    public int getId_uso_caldera() {
        return id_uso_caldera;
    }

    public void setId_uso_caldera(int id_uso_caldera) {
        this.id_uso_caldera = id_uso_caldera;
    }

    public String getNombre_uso_caldera() {
        return nombre_uso_caldera;
    }

    public void setNombre_uso_caldera(String nombre_uso_caldera) {
        this.nombre_uso_caldera = nombre_uso_caldera;
    }
}
