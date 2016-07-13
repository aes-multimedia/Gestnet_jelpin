package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Sergio on 13/07/2016.
 */
public class SubTiposVisita {






    public static final String ID_SUBTIPO_VISITA = "_id_subtipo";
    public static final String FK_TIPO_VISITA = "fk_tipo_visita";
    public static final String CODIGO = "codigo";
    public static final String DESCRIPCION = "descripcion";
    public static final String DESCRIPCION_TICKET = "descripcion_ticket";
    public static final String ACTIVO = "activo";


    @DatabaseField(id = true, columnName = ID_SUBTIPO_VISITA)
    private int id_subtipo;
    @DatabaseField(columnName = FK_TIPO_VISITA)
    private int fk_tipo_visita;
    @DatabaseField(columnName = CODIGO)
    private String codigo;
    @DatabaseField(columnName = DESCRIPCION)
    private String descripcion;
    @DatabaseField(columnName = DESCRIPCION_TICKET)
    private String descripcion_ticket;
    @DatabaseField(columnName = ACTIVO)
    private int activo;

    public SubTiposVisita() {
    }

    public SubTiposVisita(int id_subtipo, int activo, String descripcion_ticket, String descripcion, String codigo, int fk_tipo_visita) {
        this.id_subtipo = id_subtipo;
        this.activo = activo;
        this.descripcion_ticket = descripcion_ticket;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.fk_tipo_visita = fk_tipo_visita;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getDescripcion_ticket() {
        return descripcion_ticket;
    }

    public void setDescripcion_ticket(String descripcion_ticket) {
        this.descripcion_ticket = descripcion_ticket;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_subtipo() {
        return id_subtipo;
    }

    public void setId_subtipo(int id_subtipo) {
        this.id_subtipo = id_subtipo;
    }

    public int getFk_tipo_visita() {
        return fk_tipo_visita;
    }

    public void setFk_tipo_visita(int fk_tipo_visita) {
        this.fk_tipo_visita = fk_tipo_visita;
    }
}

