package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_tipos_equipamientos")
public class TipoEquipamiento {

    public static final String ID_TIPO_EQUIPAMIENTO = "_id_tipo_equipamiento";
    public static final String NOMBRE_TIPO_EQUIPAMIENTO = "nombre_tipo_equipamiento";

    @DatabaseField(id = true, columnName = ID_TIPO_EQUIPAMIENTO)
    private int id_tipo_equipamiento;
    @DatabaseField(columnName = NOMBRE_TIPO_EQUIPAMIENTO)
    private String nombre_tipo_equipamiento;


    public TipoEquipamiento() {
    }

    public TipoEquipamiento(int id_tipo_equipamiento, String nombre_tipo_equipamiento) {
        this.id_tipo_equipamiento = id_tipo_equipamiento;
        this.nombre_tipo_equipamiento = nombre_tipo_equipamiento;
    }

    public int getId_tipo_equipamiento() {
        return id_tipo_equipamiento;
    }
    public void setId_tipo_equipamiento(int id_tipo_equipamiento) {
        this.id_tipo_equipamiento = id_tipo_equipamiento;
    }
    public String getNombre_tipo_equipamiento() {
        return nombre_tipo_equipamiento;
    }
    public void setNombre_tipo_equipamiento(String nombre_tipo_equipamiento) {
        this.nombre_tipo_equipamiento = nombre_tipo_equipamiento;
    }
}
