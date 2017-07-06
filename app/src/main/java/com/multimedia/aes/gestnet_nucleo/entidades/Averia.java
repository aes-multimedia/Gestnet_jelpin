package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_averias")
public class Averia {

    public static final String ID_AVERIA = "_id_averia";
    public static final String DIRECCION_AVERIA = "direccion_averia";
    public static final String CP_AVERIA = "cp_averia";
    public static final String PROVINCIA_AVERIA = "provincia_averia";


    @DatabaseField(id = true, columnName = ID_AVERIA)
    private int id_averia;
    @DatabaseField(columnName = DIRECCION_AVERIA)
    private String direccion_averia;
    @DatabaseField(columnName = CP_AVERIA)
    private String cp_averia;
    @DatabaseField(columnName = PROVINCIA_AVERIA)
    private String provincia_averia;

    public Averia(){}
    public Averia(int id_averia, String direccion_averia, String cp_averia, String provincia_averia) {
        this.id_averia = id_averia;
        this.direccion_averia = direccion_averia;
        this.cp_averia = cp_averia;
        this.provincia_averia = provincia_averia;
    }

    public int getId_averia() {
        return id_averia;
    }
    public void setId_averia(int id_averia) {
        this.id_averia = id_averia;
    }
    public String getDireccion_averia() {
        return direccion_averia;
    }
    public void setDireccion_averia(String direccion_averia) {
        this.direccion_averia = direccion_averia;
    }
    public String getCp_averia() {
        return cp_averia;
    }
    public void setCp_averia(String cp_averia) {
        this.cp_averia = cp_averia;
    }
    public String getProvincia_averia() {
        return provincia_averia;
    }
    public void setProvincia_averia(String provincia_averia) {
        this.provincia_averia = provincia_averia;
    }
}