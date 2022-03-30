package com.multimedia.aes.gestnet_ssl.clases;

public class TipoTrabajo {

   private int id_tipo_trabajo;
   private String descripcion_tipo_trabajo;

    public TipoTrabajo(int id_tipo_trabajo, String descripcion_tipo_trabajo) {
        this.id_tipo_trabajo = id_tipo_trabajo;
        this.descripcion_tipo_trabajo = descripcion_tipo_trabajo;
    }

    public int getId_tipo_trabajo() {
        return id_tipo_trabajo;
    }

    public String getDescripcion_tipo_trabajo() {
        return descripcion_tipo_trabajo;
    }
}
