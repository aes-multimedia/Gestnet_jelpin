package com.multimedia.aes.gestnet_ssl.clases;

import android.graphics.Bitmap;

public class DataImagenes {
    public String ruta;
    public String nombre;
    public Bitmap bitmap;
    public int fk,id;
    public boolean galeria,enviado;

    public DataImagenes(int id,String ruta, String nombre, Bitmap bitmap, int fk,boolean galeria,boolean enviado) {
        this.id=id;
        this.ruta=ruta;
        this.nombre = nombre;
        this.bitmap = bitmap;
        this.fk = fk;
        this.galeria = galeria;
        this.enviado = enviado;
    }
}
