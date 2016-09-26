package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_motivos_no_rep")
public class MotivosNoRep {

    public static final String ID_MOTIVO_CANCELACION = "_id_motivo_cancelacion";
    public static final String MOTIVO = "motivo";
    public static final String BCODIGO = "bcodigo";

    @DatabaseField(id = true, columnName = ID_MOTIVO_CANCELACION)
    private int id_motivo_cancelacion;
    @DatabaseField(columnName = MOTIVO)
    private String motivo;
    @DatabaseField(columnName = BCODIGO)
    private int bcodigo;


    public MotivosNoRep() {
    }

    public MotivosNoRep(int id_motivo_cancelacion, String motivo, int bcodigo) {
        this.id_motivo_cancelacion = id_motivo_cancelacion;
        this.motivo = motivo;
        this.bcodigo = bcodigo;
    }

    public int getId_motivo_cancelacion() {
        return id_motivo_cancelacion;
    }
    public void setId_motivo_cancelacion(int id_motivo_cancelacion) {
        this.id_motivo_cancelacion = id_motivo_cancelacion;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public int getBcodigo() {
        return bcodigo;
    }
    public void setBcodigo(int bcodigo) {
        this.bcodigo = bcodigo;
    }
}
