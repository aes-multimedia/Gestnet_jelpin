package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "mos_mano_obra")
public class ManoObra {

    public static final String ID_MANO="id_mano";
    public static final String CONCEPTO="concepto";
    public static final String PRECIO="precio";
    public static final String COSTE="coste";

    @DatabaseField(id=true,columnName = ID_MANO) private int id_mano;
    @DatabaseField(columnName = CONCEPTO) private String concepto;
    @DatabaseField(columnName = PRECIO) private int precio;
    @DatabaseField(columnName = COSTE) private String coste;

    public ManoObra(){}

    public ManoObra(int id_mano, String concepto, int precio, String coste) {
        this.id_mano = id_mano;
        this.concepto = concepto;
        this.precio = precio;
        this.coste = coste;
    }

    public int getId_mano() {
        return id_mano;
    }

    public void setId_mano(int id_mano) {
        this.id_mano = id_mano;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCoste() {
        return coste;
    }

    public void setCoste(String coste) {
        this.coste = coste;
    }
}
