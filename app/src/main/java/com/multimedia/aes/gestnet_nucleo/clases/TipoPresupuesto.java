package com.multimedia.aes.gestnet_nucleo.clases;

public class TipoPresupuesto {

    private int id_instalacion_presupuesto;
    private String nombre_instalacion;

    public TipoPresupuesto(int id_instalacion_presupuesto, String nombre_instalacion) {
        this.id_instalacion_presupuesto = id_instalacion_presupuesto;
        this.nombre_instalacion = nombre_instalacion;
    }

    public int getId_instalacion_presupuesto() {
        return id_instalacion_presupuesto;
    }

    public void setId_instalacion_presupuesto(int id_instalacion_presupuesto) {
        this.id_instalacion_presupuesto = id_instalacion_presupuesto;
    }

    public String getNombre_instalacion() {
        return nombre_instalacion;
    }

    public void setNombre_instalacion(String nombre_instalacion) {
        this.nombre_instalacion = nombre_instalacion;
    }
}
