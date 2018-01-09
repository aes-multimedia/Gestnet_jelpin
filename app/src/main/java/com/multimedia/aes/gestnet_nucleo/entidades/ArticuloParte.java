package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
/**
 * Created by acp on 09/01/2018.
 */

public class ArticuloParte {



    public static final String ID = "_id";
    public static final String FK_ARTICULO = "fk_articulo";
    public static final String FK_PARTE = "fk_parte";


    @DatabaseField(id = true, columnName = ID)  private int id;
    @DatabaseField(columnName = FK_ARTICULO)         private int fk_articulo;
    @DatabaseField(columnName = FK_PARTE)         private int fk_parte;

    public ArticuloParte(){}
    public ArticuloParte(int id, int fk_articulo, int fk_parte) {
        this.id = id;
        this.fk_articulo = fk_articulo;
        this.fk_parte = fk_parte;
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



}
