package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by acp on 29/12/2017.
 */

@DatabaseTable(tableName = "mos_marcas")
public class Marca {


    public static final String ID_MARCA  = "_id_marca";
    public static final String NOMBRE_MARCA  = "nombre_marca";


    @DatabaseField(id = true, columnName = ID_MARCA)      private int id_marca;
    @DatabaseField(columnName = NOMBRE_MARCA)               private String nombre_marca;


    public Marca(){}
    public Marca(int id_marca, String nombre_marca) {
        this.id_marca = id_marca;
        this.nombre_marca = nombre_marca;
    }


    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }
}
