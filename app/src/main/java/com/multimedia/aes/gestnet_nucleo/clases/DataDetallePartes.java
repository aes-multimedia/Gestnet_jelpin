package com.multimedia.aes.gestnet_nucleo.clases;

public class DataDetallePartes {
    private String direccion,numContrato,ordenEndesa,nombreCliente;
    private int id;
///PREUBA GIT
    public DataDetallePartes(int id, String direccion, String numContrato, String ordenEndesa, String nombreCliente) {
        this.id = id;
        this.direccion = direccion;
        this.numContrato = numContrato;
        this.ordenEndesa = ordenEndesa;
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNumContrato() {
        return numContrato;
    }
    public void setNumContrato(String numContrato) {
        this.numContrato = numContrato;
    }
    public String getOrdenEndesa() {
        return ordenEndesa;
    }
    public void setOrdenEndesa(String ordenEndesa) {
        this.ordenEndesa = ordenEndesa;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
