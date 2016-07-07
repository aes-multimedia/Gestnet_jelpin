package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_potencias")
public class Potencia {

    public static final String ID_POTENCIA = "_id_potencia";
    public static final String POTENCIA = "potencia";

    @DatabaseField(id = true, columnName = ID_POTENCIA)
    private int id_potencia;
    @DatabaseField(columnName = POTENCIA)
    private String potencia;


    public Potencia() {
    }

    public Potencia(int id_potencia, String potencia) {
        this.id_potencia = id_potencia;
        this.potencia = potencia;
    }

    public int getId_potencia() {
        return id_potencia;
    }

    public void setId_potencia(int id_potencia) {
        this.id_potencia = id_potencia;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }
}
