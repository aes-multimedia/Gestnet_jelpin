package com.multimedia.aes.gestnet_nucleo.clases;

public class Intervencion {


    private String tecnico,fecha_visita,otros_sintomas,operacion_efectuada;
    private double facturado;
    private int id_parte;

    public Intervencion() {

    }

    public int getId_parte() {
        return id_parte;
    }

    public Intervencion setId_parte(int id_parte) {
        this.id_parte = id_parte;
        return this;
    }

    public String getTecnico() {
        return tecnico;
    }

    public Intervencion setTecnico(String tecnico) {
        this.tecnico = tecnico;
        return this;
    }

    public String getFecha_visita() {
        return fecha_visita;

    }

    public Intervencion setFecha_visita(String fecha_visita) {
        this.fecha_visita = fecha_visita;
        return this;
    }

    public String getOtros_sintomas() {
        return otros_sintomas;
    }

    public Intervencion setOtros_sintomas(String otros_sintomas) {
        this.otros_sintomas = otros_sintomas;
        return this;
    }

    public String getOperacion_efectuada() {
        return operacion_efectuada;
    }

    public Intervencion setOperacion_efectuada(String operacion_efectuada) {
        this.operacion_efectuada = operacion_efectuada;
        return this;
    }

    public double getFacturado() {
        return facturado;
    }

    public Intervencion setFacturado(double facturado) {
        this.facturado = facturado;
        return this;
    }
}
