package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.graphics.Bitmap;

public class DataImagenes {
    public String ruta;
    public String nombre;
    public Bitmap bitmap;

    public int fk_parte;

    public DataImagenes(String ruta, String nombre, Bitmap bitmap, int fk_parte) {
        this.ruta=ruta;
        this.nombre = nombre;
        this.bitmap = bitmap;
        this.fk_parte = fk_parte;
    }
}
