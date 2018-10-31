package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;


public class ArticuloParte {



    public static final String ID = "_id";
    public static final String FK_ARTICULO = "fk_articulo";
    public static final String FK_PARTE = "fk_parte";
    public static final String USADOS = "usados";


    @DatabaseField(generatedId = true, columnName = ID)     private int id;
    @DatabaseField(columnName = FK_ARTICULO)                private int fk_articulo;
    @DatabaseField(columnName = FK_PARTE)                   private int fk_parte;
    @DatabaseField(columnName = USADOS)                     private double usados;

    public ArticuloParte(){}
    public ArticuloParte(int fk_articulo, int fk_parte,double usados) {
        this.id = id;
        this.fk_articulo = fk_articulo;
        this.fk_parte = fk_parte;
        this.usados = usados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_articulo() {
        return fk_articulo;
    }

    public void setFk_articulo(int fk_articulo) {
        this.fk_articulo = fk_articulo;
    }

    public int getFk_parte() {
        return fk_parte;
    }

    public void setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
    }

    public double getUsados() {

        return usados;
    }

    public void setUsados(double usados) {
        this.usados = usados;
    }
}
