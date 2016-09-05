package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_equipamientos_caldera")
public class EquipamientoCaldera {

    public static final String ID_EQUIPAMIENTO_CALDERA = "_id_equipamiento_caldera";
    public static final String POTENCIA_FUEGOS = "potencia_fuegos";
    public static final String FK_TIPO_EQUIPAMIENTO = "fk_tipo_equipamiento";
    public static final String FK_PARTE = "fk_parte";

    @DatabaseField(generatedId = true, columnName = ID_EQUIPAMIENTO_CALDERA)
    private int id_equipamiento_caldera;
    @DatabaseField(columnName = POTENCIA_FUEGOS)
    private String potencia_fuegos;
    @DatabaseField(columnName = FK_TIPO_EQUIPAMIENTO)
    private int fk_tipo_equipamiento;
    @DatabaseField(columnName = FK_PARTE)
    private int fk_parte;

    public EquipamientoCaldera() {
    }

    public EquipamientoCaldera(String potencia_fuegos, int fk_tipo_equipamiento, int fk_parte) {
        this.potencia_fuegos = potencia_fuegos;
        this.fk_tipo_equipamiento = fk_tipo_equipamiento;
        this.fk_parte = fk_parte;
    }

    public int getId_equipamiento_caldera() {
        return id_equipamiento_caldera;
    }
    public void setId_equipamiento_caldera(int id_equipamiento_caldera) {
        this.id_equipamiento_caldera = id_equipamiento_caldera;
    }
    public String getPotencia_fuegos() {
        return potencia_fuegos;
    }
    public void setPotencia_fuegos(String potencia_fuegos) {
        this.potencia_fuegos = potencia_fuegos;
    }
    public int getFk_tipo_equipamiento() {
        return fk_tipo_equipamiento;
    }
    public void setFk_tipo_equipamiento(int fk_tipo_equipamiento) {
        this.fk_tipo_equipamiento = fk_tipo_equipamiento;
    }
    public int getFk_parte() {
        return fk_parte;
    }
    public void setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
    }
}
