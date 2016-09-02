package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_maquinas")
public class Maquina {
    public static final String ID_MAQUINA = "_id_maquina";
    public static final String FK_MANTENIMIENTO = "fk_mantenimiento";
    public static final String FK_TIPO_MAQUINA = "fk_tipo_maquina";
    public static final String FK_MARCA_MAQUINA = "fk_marca_maquina";
    public static final String MODELO_MAQUINA = "modelo_maquina";
    public static final String FK_POTENCIA_MAQUINA = "fk_potencia_maquina";
    public static final String FK_USO_MAQUINA = "fk_uso_maquina";
    public static final String PUESTA_MARCHA_MAQUINA = "puesta_marcha_maquina";
    public static final String CODIGO_MAQUINA = "codigo_maquina";
    public static final String C0_MAQUINA = "c0_maquina";
    public static final String TEMPERATURA_MAX_ACS = "temperatura_max_acs";
    public static final String CAUDAL_ACS = "caudal_acs";
    public static final String POTENCIA_UTIL = "potencia_util";
    public static final String TEMPERATURA_GASES_COMBUSTION = "temperatura_gases_combustion";
    public static final String TEMPERATURA_AMBIENTE_LOCAL = "temperatura_ambiente_local";
    public static final String TEMPERATURA_AGUA_GENERADOR_CALOR_ENTRADA = "temperatura_agua_generador_calor_entrada";
    public static final String TEMPERATURA_AGUA_GENERADOR_CALOR_SALIDA = "temperatura_agua_generador_calor_salida";

    @DatabaseField(generatedId = true, columnName = ID_MAQUINA)
    private int id_maquina;
    @DatabaseField(columnName = FK_MANTENIMIENTO)
    private int fk_mantenimiento;
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
    @DatabaseField(columnName = CODIGO_MAQUINA)
    private String codigo_maquina;
    @DatabaseField(columnName = C0_MAQUINA)
    private String c0_maquina;
    @DatabaseField(columnName = TEMPERATURA_MAX_ACS)
    private String temperatura_max_acs;
    @DatabaseField(columnName = CAUDAL_ACS)
    private String caudal_acs;
    @DatabaseField(columnName = POTENCIA_UTIL)
    private String potencia_util;
    @DatabaseField(columnName = TEMPERATURA_GASES_COMBUSTION)
    private String temperatura_gases_combustion;
    @DatabaseField(columnName = TEMPERATURA_AMBIENTE_LOCAL)
    private String temperatura_ambiente_local;
    @DatabaseField(columnName = TEMPERATURA_AGUA_GENERADOR_CALOR_ENTRADA)
    private String temperatura_agua_generador_calor_entrada;
    @DatabaseField(columnName = TEMPERATURA_AGUA_GENERADOR_CALOR_SALIDA)
    private String temperatura_agua_generador_calor_salida;

    public Maquina() {
    }
    public Maquina(int id_maquina, int fk_mantenimiento, int fk_tipo_maquina, int fk_marca_maquina,
                   String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
                   String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
                   String temperatura_max_acs, String caudal_acs, String potencia_util,
                   String temperatura_gases_combustion, String temperatura_ambiente_local,
                   String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida) {
        this.id_maquina = id_maquina;
        this.fk_mantenimiento = fk_mantenimiento;
        this.fk_tipo_maquina = fk_tipo_maquina;
        this.fk_marca_maquina = fk_marca_maquina;
        this.modelo_maquina = modelo_maquina;
        this.fk_potencia_maquina = fk_potencia_maquina;
        this.fk_uso_maquina = fk_uso_maquina;
        this.puesta_marcha_maquina = puesta_marcha_maquina;
        this.codigo_maquina = codigo_maquina;
        this.c0_maquina = c0_maquina;
        this.temperatura_max_acs = temperatura_max_acs;
        this.caudal_acs = caudal_acs;
        this.potencia_util = potencia_util;
        this.temperatura_gases_combustion = temperatura_gases_combustion;
        this.temperatura_ambiente_local = temperatura_ambiente_local;
        this.temperatura_agua_generador_calor_entrada = temperatura_agua_generador_calor_entrada;
        this.temperatura_agua_generador_calor_salida = temperatura_agua_generador_calor_salida;
    }

    public int getId_maquina() {
        return id_maquina;
    }
    public void setId_maquina(int id_maquina) {
        this.id_maquina = id_maquina;
    }
    public int getFk_mantenimiento() {
        return fk_mantenimiento;
    }
    public void setFk_mantenimiento(int fk_mantenimiento) {
        this.fk_mantenimiento = fk_mantenimiento;
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
    public String getCodigo_maquina() {
        return codigo_maquina;
    }
    public void setCodigo_maquina(String codigo_maquina) {
        this.codigo_maquina = codigo_maquina;
    }
    public String getC0_maquina() {
        return c0_maquina;
    }
    public void setC0_maquina(String c0_maquina) {
        this.c0_maquina = c0_maquina;
    }
    public String getTemperatura_max_acs() {
        return temperatura_max_acs;
    }
    public void setTemperatura_max_acs(String temperatura_max_acs) {
        this.temperatura_max_acs = temperatura_max_acs;
    }
    public String getCaudal_acs() {
        return caudal_acs;
    }
    public void setCaudal_acs(String caudal_acs) {
        this.caudal_acs = caudal_acs;
    }
    public String getPotencia_util() {
        return potencia_util;
    }
    public void setPotencia_util(String potencia_util) {
        this.potencia_util = potencia_util;
    }
    public String getTemperatura_gases_combustion() {
        return temperatura_gases_combustion;
    }
    public void setTemperatura_gases_combustion(String temperatura_gases_combustion) {
        this.temperatura_gases_combustion = temperatura_gases_combustion;
    }
    public String getTemperatura_ambiente_local() {
        return temperatura_ambiente_local;
    }
    public void setTemperatura_ambiente_local(String temperatura_ambiente_local) {
        this.temperatura_ambiente_local = temperatura_ambiente_local;
    }
    public String getTemperatura_agua_generador_calor_entrada() {
        return temperatura_agua_generador_calor_entrada;
    }
    public void setTemperatura_agua_generador_calor_entrada(String temperatura_agua_generador_calor_entrada) {
        this.temperatura_agua_generador_calor_entrada = temperatura_agua_generador_calor_entrada;
    }
    public String getTemperatura_agua_generador_calor_salida() {
        return temperatura_agua_generador_calor_salida;
    }
    public void setTemperatura_agua_generador_calor_salida(String temperatura_agua_generador_calor_salida) {
        this.temperatura_agua_generador_calor_salida = temperatura_agua_generador_calor_salida;
    }
}
