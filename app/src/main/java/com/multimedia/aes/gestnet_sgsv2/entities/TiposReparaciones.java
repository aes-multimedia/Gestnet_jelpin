package com.multimedia.aes.gestnet_sgsv2.entities;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


public class TiposReparaciones {



    public static final String ID_TIPO_REPARACION = "_id_tipo_reparacion";
    public static final String CODIGO = "codigo";
    public static final String DESCRIPCION = "descripcion";
    public static final String ABREVIATURA = "abreviatura";

    @DatabaseField(id = true, columnName = ID_TIPO_REPARACION)
    private int id_tipo_reparacion;
    @DatabaseField(columnName = CODIGO)
    private int codigo;
    @DatabaseField(columnName = DESCRIPCION)
    private String descripcion;
    @DatabaseField(columnName = ABREVIATURA)
    private String abreviatura;


    public TiposReparaciones() {
    }

    public TiposReparaciones(int id_tipo_reparacion,int codigo, String descripcion,String abreviatura) {
        this.id_tipo_reparacion = id_tipo_reparacion;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;

    }

    public int getId_tipo_reparacion() {
        return id_tipo_reparacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setId_tipo_reparacion(int id_tipo_reparacion) {
        this.id_tipo_reparacion = id_tipo_reparacion;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


}

