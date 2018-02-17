package com.multimedia.aes.gestnet_nucleo.clases;

/**
 * Created by acp on 17/02/2018.
 */

public class DataStock {

    private int idStock;
    private String nombreEntidad;
    private int fkProducto;
    private int stock;

    public DataStock(int idStock, String nombreEntidad, int fkProducto, int stock) {
        this.idStock = idStock;
        this.nombreEntidad = nombreEntidad;
        this.fkProducto = fkProducto;
        this.stock = stock;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public int getFkProducto() {
        return fkProducto;
    }

    public void setFkProducto(int fkProducto) {
        this.fkProducto = fkProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}


