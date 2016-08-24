package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_mantenimientos_terminados")
    public class MantenimientoTerminado {
        public static final String ID_MANTENIMIENTO_TERMINADO = "_id_mantenimiento_terminado";
        public static final String CODIGO_BARRAS = "codigo_barras";
        public static final String FK_ESTADO_VISITA = "fk_estado_visita";
        public static final String FK_TIPO_VISITA = "fk_tipo_visita";
        public static final String OBSERVACIONES_TECNICO = "observaciones_tecnico";
        public static final String CONTADOR_INTERNO = "contador_interno";
        public static final String CODIGO_INTERNO = "codigo_interno";
        public static final String REPARACION = "reparacion";
        public static final String FK_TIPO_REPARACION = "fk_tipo_reparacion";
        public static final String FECHA_REPARACION = "fecha_reparacion";
        public static final String FK_TIEMPO_MANO_OBRA = "fk_tiempo_mano_obra";
        public static final String COSTE_MATERIALES = "coste_materiales";
        public static final String COSTE_MANO_OBRA = "coste_mano_obra";
        public static final String CODIGO_BARRAS_REPARACION = "codigo_barras_reparacion";
        public static final String FK_TIPO_MAQUINA = "fk_tipo_maquina";
        public static final String FK_MARCA_MAQUINA = "fk_marca_maquina";
        public static final String MODELO_MAQUINA = "modelo_maquina";
        public static final String FK_POTENCIA_MAQUINA = "fk_potencia_maquina";
        public static final String FK_USO_MAQUINA = "fk_uso_maquina";
        public static final String PUESTA_MARCHA_MAQUINA = "puesta_marcha_maquina";

        @DatabaseField(generatedId = true, columnName = ID_MANTENIMIENTO_TERMINADO)
        private int id_mantenimiento_terminado;
        @DatabaseField(columnName = CODIGO_BARRAS)
        private String codigo_barras;
        @DatabaseField(columnName = FK_ESTADO_VISITA)
        private int fk_estado_visita;
        @DatabaseField(columnName = FK_TIPO_VISITA)
        private int fk_tipo_visita;
        @DatabaseField(columnName = OBSERVACIONES_TECNICO)
        private String observaciones_tecnico;
        @DatabaseField(columnName = CONTADOR_INTERNO)
        private String contador_interno;
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
        @DatabaseField(columnName = COSTE_MANO_OBRA)
        private String coste_mano_obra;
        @DatabaseField(columnName = CODIGO_BARRAS_REPARACION)
        private String codigo_barras_reparacion;
        @DatabaseField(columnName = FK_TIPO_MAQUINA)
        private int fk_tipo_maquina;
        @DatabaseField(columnName = FK_MARCA_MAQUINA)
        private int fk_marca_maquina;
        @DatabaseField(columnName = MODELO_MAQUINA)
        private String modelo_maquina;
        @DatabaseField(columnName = FK_POTENCIA_MAQUINA)
        private int fk_potencia_maquina;
        @DatabaseField(columnName = FK_USO_MAQUINA)
        private int fk_uso_maquina;
        @DatabaseField(columnName = PUESTA_MARCHA_MAQUINA)
        private String puesta_marcha_maquina;

        public MantenimientoTerminado() {
        }

        public MantenimientoTerminado(int id_mantenimiento_terminado, String codigo_barras, int fk_estado_visita, int fk_tipo_visita, String observaciones_tecnico, String contador_interno, String codigo_interno, int reparacion, int fk_tipo_reparacion, String fecha_reparacion, int fk_tiempo_mano_obra, String coste_materiales, String coste_mano_obra, String codigo_barras_reparacion, int fk_tipo_maquina, int fk_marca_maquina, String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina, String puesta_marcha_maquina) {
            this.id_mantenimiento_terminado = id_mantenimiento_terminado;
            this.codigo_barras = codigo_barras;
            this.fk_estado_visita = fk_estado_visita;
            this.fk_tipo_visita = fk_tipo_visita;
            this.observaciones_tecnico = observaciones_tecnico;
            this.contador_interno = contador_interno;
            this.codigo_interno = codigo_interno;
            this.reparacion = reparacion;
            this.fk_tipo_reparacion = fk_tipo_reparacion;
            this.fecha_reparacion = fecha_reparacion;
            this.fk_tiempo_mano_obra = fk_tiempo_mano_obra;
            this.coste_materiales = coste_materiales;
            this.coste_mano_obra = coste_mano_obra;
            this.codigo_barras_reparacion = codigo_barras_reparacion;
            this.fk_tipo_maquina = fk_tipo_maquina;
            this.fk_marca_maquina = fk_marca_maquina;
            this.modelo_maquina = modelo_maquina;
            this.fk_potencia_maquina = fk_potencia_maquina;
            this.fk_uso_maquina = fk_uso_maquina;
            this.puesta_marcha_maquina = puesta_marcha_maquina;
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
        public int getFk_estado_visita() {
            return fk_estado_visita;
        }
        public void setFk_estado_visita(int fk_estado_visita) {
            this.fk_estado_visita = fk_estado_visita;
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
        public String getContador_interno() {
            return contador_interno;
        }
        public void setContador_interno(String contador_interno) {
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
        public String getCoste_mano_obra() {
            return coste_mano_obra;
        }
        public void setCoste_mano_obra(String coste_mano_obra) {
            this.coste_mano_obra = coste_mano_obra;
        }
        public String getCodigo_barras_reparacion() {
            return codigo_barras_reparacion;
        }
        public void setCodigo_barras_reparacion(String codigo_barras_reparacion) {
            this.codigo_barras_reparacion = codigo_barras_reparacion;
        }
        public int getFk_tipo_maquina() {
            return fk_tipo_maquina;
        }
        public void setFk_tipo_maquina(int fk_tipo_maquina) {
            this.fk_tipo_maquina = fk_tipo_maquina;
        }
        public int getFk_marca_maquina() {
            return fk_marca_maquina;
        }
        public void setFk_marca_maquina(int fk_marca_maquina) {
            this.fk_marca_maquina = fk_marca_maquina;
        }
        public String getModelo_maquina() {
            return modelo_maquina;
        }
        public void setModelo_maquina(String modelo_maquina) {
            this.modelo_maquina = modelo_maquina;
        }
        public int getFk_potencia_maquina() {
            return fk_potencia_maquina;
        }
        public void setFk_potencia_maquina(int fk_potencia_maquina) {
            this.fk_potencia_maquina = fk_potencia_maquina;
        }
        public int getFk_uso_maquina() {
            return fk_uso_maquina;
        }
        public void setFk_uso_maquina(int fk_uso_maquina) {
            this.fk_uso_maquina = fk_uso_maquina;
        }
        public String getPuesta_marcha_maquina() {
            return puesta_marcha_maquina;
        }
        public void setPuesta_marcha_maquina(String puesta_marcha_maquina) {
        this.puesta_marcha_maquina = puesta_marcha_maquina;
    }
}
