package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_mantenimientos_terminados")
    public class MantenimientoTerminado {
        public static final String ID_MANTENIMIENTO_TERMINADO = "_id_mantenimiento_terminado";
        public static final String CODIGO_BARRAS = "codigo_barras";
        public static final String FK_TIPO_VISITA = "fk_tipo_visita";
        public static final String OBSERVACIONES_TECNICO = "observaciones_tecnico";
        public static final String CONTADOR_INTERNO = "contador_interno";
        public static final String CODIGO_INTERNO = "codigo_interno";
        public static final String REPARACION = "reparacion";
        public static final String FK_TIPO_REPARACION = "fk_tipo_reparacion";
        public static final String FECHA_REPARACION = "fecha_reparacion";
        public static final String FK_TIEMPO_MANO_OBRA = "fk_tiempo_mano_obra";
        public static final String COSTE_MATERIALES = "coste_materiales";
        public static final String CODIGO_BARRAS_REPARACION = "codigo_barras_reparacion";

    @DatabaseField(id = true, columnName = ID_MANTENIMIENTO_TERMINADO)
    private int id_mantenimiento_terminado;
    @DatabaseField(columnName = CODIGO_BARRAS)
    private String codigo_barras;
    @DatabaseField(columnName = FK_TIPO_VISITA)
    private int fk_tipo_visita;
    @DatabaseField(columnName = OBSERVACIONES_TECNICO)
    private String observaciones_tecnico;
    @DatabaseField(columnName = CONTADOR_INTERNO)
    private int contador_interno;
    @DatabaseField(columnName = CODIGO_INTERNO)
    private String codigo_interno;
    @DatabaseField(columnName = REPARACION)
    private int reparacion;
    @DatabaseField(columnName = FK_TIPO_REPARACION)
    private int fk_tipo_reparacion;
    @DatabaseField(columnName = FECHA_REPARACION)
    private String fecha_reparacion;
    @DatabaseField(columnName = FK_TIEMPO_MANO_OBRA)
    private int fk_tiempo_mano_obra;
    @DatabaseField(columnName = COSTE_MATERIALES)
    private String coste_materiales;
    @DatabaseField(columnName = CODIGO_BARRAS_REPARACION)
    private String codigo_barras_reparacion;

    public MantenimientoTerminado() {
    }
    public MantenimientoTerminado(int id_mantenimiento_terminado, String codigo_barras, int fk_tipo_visita, String observaciones_tecnico, int contador_interno, String codigo_interno, int reparacion, int fk_tipo_reparacion, String fecha_reparacion, int fk_tiempo_mano_obra, String coste_materiales, String codigo_barras_reparacion) {
        this.id_mantenimiento_terminado = id_mantenimiento_terminado;
        this.codigo_barras = codigo_barras;
        this.fk_tipo_visita = fk_tipo_visita;
        this.observaciones_tecnico = observaciones_tecnico;
        this.contador_interno = contador_interno;
        this.codigo_interno = codigo_interno;
        this.reparacion = reparacion;
        this.fk_tipo_reparacion = fk_tipo_reparacion;
        this.fecha_reparacion = fecha_reparacion;
        this.fk_tiempo_mano_obra = fk_tiempo_mano_obra;
        this.coste_materiales = coste_materiales;
        this.codigo_barras_reparacion = codigo_barras_reparacion;
    }

    public int getId_mantenimiento_terminado() {
        return id_mantenimiento_terminado;
    }
    public void setId_mantenimiento_terminado(int id_mantenimiento_terminado) {
        this.id_mantenimiento_terminado = id_mantenimiento_terminado;
    }
    public String getCodigo_barras() {
        return codigo_barras;
    }
    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }
    public int getFk_tipo_visita() {
        return fk_tipo_visita;
    }
    public void setFk_tipo_visita(int fk_tipo_visita) {
        this.fk_tipo_visita = fk_tipo_visita;
    }
    public String getObservaciones_tecnico() {
        return observaciones_tecnico;
    }
    public void setObservaciones_tecnico(String observaciones_tecnico) {
        this.observaciones_tecnico = observaciones_tecnico;
    }
    public int getContador_interno() {
        return contador_interno;
    }
    public void setContador_interno(int contador_interno) {
        this.contador_interno = contador_interno;
    }
    public String getCodigo_interno() {
        return codigo_interno;
    }
    public void setCodigo_interno(String codigo_interno) {
        this.codigo_interno = codigo_interno;
    }
    public int getReparacion() {
        return reparacion;
    }
    public void setReparacion(int reparacion) {
        this.reparacion = reparacion;
    }
    public int getFk_tipo_reparacion() {
        return fk_tipo_reparacion;
    }
    public void setFk_tipo_reparacion(int fk_tipo_reparacion) {
        this.fk_tipo_reparacion = fk_tipo_reparacion;
    }
    public String getFecha_reparacion() {
        return fecha_reparacion;
    }
    public void setFecha_reparacion(String fecha_reparacion) {
        this.fecha_reparacion = fecha_reparacion;
    }
    public int getFk_tiempo_mano_obra() {
        return fk_tiempo_mano_obra;
    }
    public void setFk_tiempo_mano_obra(int fk_tiempo_mano_obra) {
        this.fk_tiempo_mano_obra = fk_tiempo_mano_obra;
    }
    public String getCoste_materiales() {
        return coste_materiales;
    }
    public void setCoste_materiales(String coste_materiales) {
        this.coste_materiales = coste_materiales;
    }
    public String getCodigo_barras_reparacion() {
        return codigo_barras_reparacion;
    }
    public void setCodigo_barras_reparacion(String codigo_barras_reparacion) {
        this.codigo_barras_reparacion = codigo_barras_reparacion;
    }
}
