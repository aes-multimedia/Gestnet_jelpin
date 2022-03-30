package com.multimedia.aes.gestnet_ssl.entidades;

/**
 * Created by acp on 25/08/2017.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "mos_disposiciones")
public class Disposiciones {



    public static final String ID_DISPOSICION_SERVICIO="id_disposicion_servicio";
    public static final String NOMBRE_DISPOSICION="nombre_disposicion";
    public static final String COSTE_DISPOSICION="coste_disposicion";
    public static final String PRECIO_DISPOSICION="precio_disposicion";



    @DatabaseField(id=true,columnName = ID_DISPOSICION_SERVICIO) private int id_disposicion_servicio;
    @DatabaseField(columnName = NOMBRE_DISPOSICION) private String nombre_disposicion;
    @DatabaseField(columnName = COSTE_DISPOSICION) private double coste_disposicion;
    @DatabaseField(columnName = PRECIO_DISPOSICION) private double precio_disposicion;



    public Disposiciones(){}


    public Disposiciones(int id_disposicion_servicio, String nombre_disposicion, double coste_disposicion, double precio_disposicion) {
        this.id_disposicion_servicio = id_disposicion_servicio;
        this.nombre_disposicion = nombre_disposicion;
        this.coste_disposicion = coste_disposicion;
        this.precio_disposicion = precio_disposicion;
    }

    public int getId_disposicion_servicio() {
        return id_disposicion_servicio;
    }

    public void setId_disposicion_servicio(int id_disposicion_servicio) {
        this.id_disposicion_servicio = id_disposicion_servicio;
    }

    public String getNombre_disposicion() {
        return nombre_disposicion;
    }

    public void setNombre_disposicion(String nombre_disposicion) {
        this.nombre_disposicion = nombre_disposicion;
    }

    public double getCoste_disposicion() {
        return coste_disposicion;
    }

    public void setCoste_disposicion(double coste_disposicion) {
        this.coste_disposicion = coste_disposicion;
    }

    public double getPrecio_disposicion() {
        return precio_disposicion;
    }

    public void setPrecio_disposicion(double precio_disposicion) {
        this.precio_disposicion = precio_disposicion;
    }
}
