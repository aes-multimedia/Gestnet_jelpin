package com.multimedia.aes.gestnet_nucleo.clases;

import android.graphics.Bitmap;

public class DataArticulos {
    private String nombre;
    private int id;


    public DataArticulos(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
