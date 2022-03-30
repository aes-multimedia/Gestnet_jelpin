package com.multimedia.aes.gestnet_ssl.clases;

public class Operacion {

    private int id_protocolo_accion;
    private int fk_maquina;
    private int fk_parte;
    private String valor;
    private int fk_protocolo;
    private String nombre_protocolo;
    private int id_accion;
    private boolean tipo_accion;
    private int orden;
    private String descripcion;



    public int getId_protocolo_accion() {
        return id_protocolo_accion;
    }

    public void setId_protocolo_accion(int id_protocolo_accion) {
        this.id_protocolo_accion = id_protocolo_accion;
    }

    public int getFk_maquina() {
        return fk_maquina;
    }

    public void setFk_maquina(int fk_maquina) {
        this.fk_maquina = fk_maquina;
    }

    public int getFk_parte() {
        return fk_parte;
    }

    public void setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getFk_protocolo() {
        return fk_protocolo;
    }

    public void setFk_protocolo(int fk_protocolo) {
        this.fk_protocolo = fk_protocolo;
    }

    public String getNombre_protocolo() {
        return nombre_protocolo;
    }

    public void setNombre_protocolo(String nombre_protocolo) {
        this.nombre_protocolo = nombre_protocolo;
    }

    public int getId_accion() {
        return id_accion;
    }

    public void setId_accion(int id_accion) {
        this.id_accion = id_accion;
    }

    public boolean isTipo_accion() {
        return tipo_accion;
    }

    public void setTipo_accion(boolean tipo_accion) {
        this.tipo_accion = tipo_accion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
