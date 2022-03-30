package com.multimedia.aes.gestnet_ssl.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_analisis")
public class Analisis {
    public static final String ID_ANALISIS = "_id_analisis";
    public static final String FK_MAQUINA = "fk_maquina";
    public static final String FK_PARTE = "fk_parte";
    public static final String C0_MAQUINA = "c0_maquina";
    public static final String TEMPERATURA_GASES_COMBUSTION = "temperatura_gases_combustion";
    public static final String TEMPERATURA_AMBIENTE_LOCAL = "temperatura_ambiente_local";
    public static final String RENDIMIENTO_APARATO = "rendimiento_aparato";
    public static final String CO_CORREGIDO = "co_corregido";
    public static final String CO_AMBIENTE = "co_ambiente";
    public static final String CO2_AMBIENTE = "co2_ambiente";
    public static final String TIRO = "tiro";
    public static final String CO2 = "co2";
    public static final String O2 = "o2";
    public static final String LAMBDA = "lambda";
    public static final String BCAMPANA = "bCampana";
    public static final String BMAXIMA_POTENCIA = "bMaxima_potencia";
    public static final String BMINIMA_POTENCIA = "bMinima_potencia";
    public static final String NOMBRE_MEDICION = "nombre_medicion";
    public static final String BUSAR_FINALIZACION = "usar_impresion";

    @DatabaseField(generatedId = true, columnName = ID_ANALISIS)    private int id_analisis;
    @DatabaseField(columnName = FK_MAQUINA)                         private int fk_maquina;
    @DatabaseField(columnName = FK_PARTE)                           private int fk_parte;
    @DatabaseField(columnName = C0_MAQUINA)                         private String c0_maquina;
    @DatabaseField(columnName = TEMPERATURA_GASES_COMBUSTION)       private String temperatura_gases_combustion;
    @DatabaseField(columnName = TEMPERATURA_AMBIENTE_LOCAL)         private String temperatura_ambiente_local;
    @DatabaseField(columnName = RENDIMIENTO_APARATO)                private String rendimiento_aparato;
    @DatabaseField(columnName = CO_CORREGIDO)                       private String co_corregido;
    @DatabaseField(columnName = CO_AMBIENTE)                        private String co_ambiente;
    @DatabaseField(columnName = CO2_AMBIENTE)                       private String co2_ambiente;
    @DatabaseField(columnName = TIRO)                               private String tiro;
    @DatabaseField(columnName = CO2)                                private String co2;
    @DatabaseField(columnName = O2)                                 private String o2;
    @DatabaseField(columnName = LAMBDA)                             private String lambda;
    @DatabaseField(columnName = BCAMPANA)                           private int bCampana;
    @DatabaseField(columnName = NOMBRE_MEDICION)                    private String nombre_medicion;
    @DatabaseField(columnName = BMAXIMA_POTENCIA)                   private int bMaxima_potencia;
    @DatabaseField(columnName = BMINIMA_POTENCIA)                   private int bMinima_potencia;
    @DatabaseField(columnName = BUSAR_FINALIZACION)                 private int bUsar_finalizacion;

    public Analisis(){}
    public Analisis(int fk_maquina, int fk_parte, String c0_maquina, String temperatura_gases_combustion,
                    String temperatura_ambiente_local, String rendimiento_aparato, String co_corregido,
                    String co_ambiente, String co2_ambiente, String tiro, String co2, String o2, String lambda,
                    int bCampana, String nombre_medicion, int bMaxima_potencia, int bMinima_potencia, int bUsar_finalizacion) {
        this.fk_maquina = fk_maquina;
        this.fk_parte = fk_parte;
        this.c0_maquina = c0_maquina;
        this.temperatura_gases_combustion = temperatura_gases_combustion;
        this.temperatura_ambiente_local = temperatura_ambiente_local;
        this.rendimiento_aparato = rendimiento_aparato;
        this.co_corregido = co_corregido;
        this.co_ambiente = co_ambiente;
        this.co2_ambiente = co2_ambiente;
        this.tiro = tiro;
        this.co2 = co2;
        this.o2 = o2;
        this.lambda = lambda;
        this.bCampana = bCampana;
        this.nombre_medicion = nombre_medicion;
        this.bMaxima_potencia = bMaxima_potencia;
        this.bMinima_potencia = bMinima_potencia;
        this.bUsar_finalizacion = bUsar_finalizacion;
    }

    public int getId_analisis() {
        return id_analisis;
    }
    public void setId_analisis(int id_analisis) {
        this.id_analisis = id_analisis;
    }
    public int getFk_maquina() {
        return fk_maquina;
    }
    public void setFk_maquina(int fk_maquina) {
        this.fk_maquina = fk_maquina;
    }
    public int getFk_parte() {
        return fk_parte;
    }
    public void setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
    }
    public String getC0_maquina() {
        return c0_maquina;
    }
    public void setC0_maquina(String c0_maquina) {
        this.c0_maquina = c0_maquina;
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
    public String getRendimiento_aparato() {
        return rendimiento_aparato;
    }
    public void setRendimiento_aparato(String rendimiento_aparato) {
        this.rendimiento_aparato = rendimiento_aparato;
    }
    public String getCo_corregido() {
        return co_corregido;
    }
    public void setCo_corregido(String co_corregido) {
        this.co_corregido = co_corregido;
    }
    public String getCo_ambiente() {
        return co_ambiente;
    }
    public void setCo_ambiente(String co_ambiente) {
        this.co_ambiente = co_ambiente;
    }
    public String getCo2_ambiente() {
        return co2_ambiente;
    }
    public void setCo2_ambiente(String co2_ambiente) {
        this.co2_ambiente = co2_ambiente;
    }
    public String getTiro() {
        return tiro;
    }
    public void setTiro(String tiro) {
        this.tiro = tiro;
    }
    public String getCo2() {
        return co2;
    }
    public void setCo2(String co2) {
        this.co2 = co2;
    }
    public String getO2() {
        return o2;
    }
    public void setO2(String o2) {
        this.o2 = o2;
    }
    public String getLambda() {
        return lambda;
    }
    public void setLambda(String lambda) {
        this.lambda = lambda;
    }
    public int getbCampana() {
        return bCampana;
    }
    public void setbCampana(int bCampana) {
        this.bCampana = bCampana;
    }
    public String getNombre_medicion() {
        return nombre_medicion;
    }
    public void setNombre_medicion(String nombre_medicion) {
        this.nombre_medicion = nombre_medicion;
    }
    public int getbMaxima_potencia() {
        return bMaxima_potencia;
    }
    public void setbMaxima_potencia(int bMaxima_potencia) {
        this.bMaxima_potencia = bMaxima_potencia;
    }
    public int getbMinima_potencia() {
        return bMinima_potencia;
    }
    public void setbMinima_potencia(int bMinima_potencia) {
        this.bMinima_potencia = bMinima_potencia;
    }
    public int getbUsar_finalizacion() {
        return bUsar_finalizacion;
    }
    public void setbUsar_finalizacion(int bUsar_finalizacion) {
        this.bUsar_finalizacion = bUsar_finalizacion;
    }
}