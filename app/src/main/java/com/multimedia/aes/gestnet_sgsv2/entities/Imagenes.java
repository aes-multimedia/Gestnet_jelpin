package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_imagenes")
public class Imagenes {

    public static final String ID_IMAGEN = "_id_imagen";
    public static final String NOMBRE_IMAGEN = "nombre_imagen";
    public static final String RUTA_IMAGEN = "ruta_imagen";
    public static final String FK_PARTE = "fk_parte";

    @DatabaseField(id = true, columnName = ID_IMAGEN)
    private int id_imagen;
    @DatabaseField(columnName = NOMBRE_IMAGEN)
    private String nombre_imagen;
    @DatabaseField(columnName = RUTA_IMAGEN)
    private String ruta_imagen;
    @DatabaseField(columnName = FK_PARTE)
    private int fk_parte;

    public Imagenes() {
    }
    public Imagenes(int id_imagen, String nombre_imagen, String ruta_imagen, int fk_parte) {
        this.id_imagen = id_imagen;
        this.nombre_imagen = nombre_imagen;
        this.ruta_imagen = ruta_imagen;
        this.fk_parte = fk_parte;
    }

    public int getId_imagen() {
        return id_imagen;
    }
    public void setId_imagen(int id_imagen) {
        this.id_imagen = id_imagen;
    }
    public String getNombre_imagen() {
        return nombre_imagen;
    }
    public void setNombre_imagen(String nombre_imagen) {
        this.nombre_imagen = nombre_imagen;
    }
    public String getRuta_imagen() {
        return ruta_imagen;
    }
    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
    public int getFk_parte() {
        return fk_parte;
    }
    public void setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
    }
}
