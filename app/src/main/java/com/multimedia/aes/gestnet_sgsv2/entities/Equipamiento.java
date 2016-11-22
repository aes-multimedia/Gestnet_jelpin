package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_equipamientos")
public class Equipamiento {

    public static final String ID_EQUIPAMIENTO = "_id_equipamiento";
    public static final String FK_EQUIPAMIENTO = "fk_equipamiento";
    public static final String FK_MAQUINA = "fk_maquina";
    public static final String FK_TIPO_EQUIPAMIENTO = "fk_tipo_equipamiento";
    public static final String POTENCIA_FUEGOS = "potencia_fuegos";
    public static final String CODIGO_EQUIPAMIENTO = "codigo_equipamiento";
    public static final String CO2_EQUIPAMIENTO = "co2_equipamiento";

    @DatabaseField(generatedId = true, columnName = ID_EQUIPAMIENTO)
    private int id_equipamiento;
    @DatabaseField(columnName = FK_EQUIPAMIENTO)
    private int fk_equipamiento;
    @DatabaseField(columnName = FK_MAQUINA)
    private int fk_maquina;
    @DatabaseField(columnName = FK_TIPO_EQUIPAMIENTO)
    private int fk_tipo_equipamiento;
    @DatabaseField(columnName = POTENCIA_FUEGOS)
    private String potencia_fuegos;
    @DatabaseField(columnName = CODIGO_EQUIPAMIENTO)
    private String codigo_equipamiento;
    @DatabaseField(columnName = CO2_EQUIPAMIENTO)
    private String co2_equipamiento;

    public Equipamiento(){}
    public Equipamiento(int id_equipamiento,int fk_equipamiento, int fk_maquina, int fk_tipo_equipamiento, String potencia_fuegos, String codigo_equipamiento, String co2_equipamiento) {
        this.id_equipamiento = id_equipamiento;
        this.fk_equipamiento = fk_equipamiento;
        this.fk_maquina = fk_maquina;
        this.fk_tipo_equipamiento = fk_tipo_equipamiento;
        this.potencia_fuegos = potencia_fuegos;
        this.codigo_equipamiento = codigo_equipamiento;
        this.co2_equipamiento = co2_equipamiento;
    }
    public Equipamiento(int fk_equipamiento, int fk_maquina, int fk_tipo_equipamiento, String potencia_fuegos, String codigo_equipamiento, String co2_equipamiento) {
        this.fk_equipamiento = fk_equipamiento;
        this.fk_maquina = fk_maquina;
        this.fk_tipo_equipamiento = fk_tipo_equipamiento;
        this.potencia_fuegos = potencia_fuegos;
        this.codigo_equipamiento = codigo_equipamiento;
        this.co2_equipamiento = co2_equipamiento;
    }

    public int getId_equipamiento() {
        return id_equipamiento;
    }
    public void setId_equipamiento(int id_equipamiento) {
        this.id_equipamiento = id_equipamiento;
    }
    public int getFk_equipamiento() {
        return fk_equipamiento;
    }
    public void setFk_equipamiento(int fk_equipamiento) {
        this.fk_equipamiento = fk_equipamiento;
    }
    public int getFk_maquina() {
        return fk_maquina;
    }
    public void setFk_maquina(int fk_maquina) {
        this.fk_maquina = fk_maquina;
    }
    public int getFk_tipo_equipamiento() {
        return fk_tipo_equipamiento;
    }
    public void setFk_tipo_equipamiento(int fk_tipo_equipamiento) {
        this.fk_tipo_equipamiento = fk_tipo_equipamiento;
    }
    public String getPotencia_fuegos() {
        return potencia_fuegos;
    }
    public void setPotencia_fuegos(String potencia_fuegos) {
        this.potencia_fuegos = potencia_fuegos;
    }
    public String getCodigo_equipamiento() {
        return codigo_equipamiento;
    }
    public void setCodigo_equipamiento(String codigo_equipamiento) {
        this.codigo_equipamiento = codigo_equipamiento;
    }
    public String getCo2_equipamiento() {
        return co2_equipamiento;
    }
    public void setCo2_equipamiento(String co2_equipamiento) {
        this.co2_equipamiento = co2_equipamiento;
    }
}
