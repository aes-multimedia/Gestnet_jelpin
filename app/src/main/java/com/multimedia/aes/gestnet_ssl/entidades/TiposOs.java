package com.multimedia.aes.gestnet_ssl.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_tiposos")
public class TiposOs {


    public static final String ID  = "_id";
    public static final String ID_TIPO_OS  = "id_tipoOs";
    public static final String NOMBRE_TIPO_OS = "nombre_tipoOs";
    public static final String FK_PARTE_TIPO = "fk_parte_tipo";


    @DatabaseField(generatedId = true ,columnName = ID)              private int id;
    @DatabaseField(columnName = ID_TIPO_OS)                 private int id_tipoOs;
    @DatabaseField(columnName = NOMBRE_TIPO_OS)             private String nombre_tipoOs;
    @DatabaseField(columnName = FK_PARTE_TIPO)              private int fk_parte_tipo;


    public TiposOs(){}
    public TiposOs(int id_tipoOs, String nombre_tipoOs,int fk_parte_tipo) {
        this.id_tipoOs = id_tipoOs;
        this.nombre_tipoOs = nombre_tipoOs;
        this.fk_parte_tipo = fk_parte_tipo;
    }

    public int getId() {
        return id;
    }

    public void setIds(int id) {
        this.id = id;
    }

    public int getId_tipoOs() {
        return id_tipoOs;
    }

    public void setId_tipoOs(int id_tipoOs) {
        this.id_tipoOs = id_tipoOs;
    }

    public String getNombre_tipoOs() {
        return nombre_tipoOs;
    }

    public void setNombre_tipoOs(String nombre_marca) {
        this.nombre_tipoOs = nombre_tipoOs;
    }

    public int getfk_parte_tipo() {
        return fk_parte_tipo;
    }

    public void setfk_parte_tipo(int fk_parte_tipo) {
        this.fk_parte_tipo = fk_parte_tipo;
    }
}
