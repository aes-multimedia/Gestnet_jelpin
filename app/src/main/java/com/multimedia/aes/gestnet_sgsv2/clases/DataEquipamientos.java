package com.multimedia.aes.gestnet_sgsv2.clases;

public class DataEquipamientos {

    public String potencia;
    public int codigo;
    public String descripcion;
    public String co2_ambiente;

    public DataEquipamientos(int codigo, String potencia, String descripcion,String co2_ambiente) {
        this.codigo = codigo;
        this.potencia = potencia;
        this.descripcion = descripcion;
        this.co2_ambiente=co2_ambiente;
    }
}
