package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_maquinas")
public class Maquina {
    
    public static final String ID_MAQUINA  = "_id_maquina";
    public static final String FK_DIRECCION  = "fk_direccion";
    public static final String FK_MODELO  = "fk_modelo";
    public static final String FK_MARCA  = "fk_marca";
    public static final String FK_TIPO_COMBUSTION  = "fk_tipo_combustion";
    public static final String FK_PROTOCOLO  = "fk_protocolo";
    public static final String FK_INSTALADOR  = "fk_instalador";
    public static final String FK_REMOTO_CENTRAL  = "fk_remoto_central";
    public static final String FK_TIPO  = "fk_tipo";
    public static final String FK_INSTALACION  = "fk_instalacion";
    public static final String FK_ESTADO  = "fk_estado";
    public static final String FK_CONTRATO_MANTENIMIENTO  = "fk_contrato_mantenimiento";
    public static final String FK_GAMA  = "fk_gama";
    public static final String FK_TIPO_GAMA  = "fk_tipo_gama";
    public static final String FECHA_CREACION  = "fecha_creacion";
    public static final String MODELO  = "modelo";
    public static final String NUM_SERIE  = "num_serie";
    public static final String NUM_PRODUCTO  = "num_producto";
    public static final String APARATO  = "aparato";
    public static final String PUESTA_MARCHA  = "puesta_marcha";
    public static final String FECHA_COMPRA  = "fecha_compra";
    public static final String FECHA_FIN_GARANTIA  = "fecha_fin_garantia";
    public static final String MANTENIMIENTO_ANUAL  = "mantenimiento_anual";
    public static final String OBSERVACIONES  = "observaciones";
    public static final String UBICACION  = "ubicacion";
    public static final String TIENDA_COMPRA  = "tienda_compra";
    public static final String FACTURA_COMPRA  = "factura_compra";
    public static final String GARANTIA_EXTENDIDA  = "garantia_extendida";
    public static final String REFRIGERANTE  = "refrigerante";
    public static final String BESINSTALACION  = "bEsInstalacion";
    public static final String NOMBRE_INSTALACION  = "nombre_instalacion";
    public static final String EN_PROPIEDAD  = "en_propiedad";
    public static final String ESPRINCIPAL  = "esPrincipal";


    @DatabaseField(id = true, columnName = ID_MAQUINA)private int id_maquina;
    @DatabaseField(columnName = FK_DIRECCION) private int fk_direccion;
    @DatabaseField(columnName = FK_MODELO) private int fk_modelo;
    @DatabaseField(columnName = FK_MARCA) private int fk_marca;
    @DatabaseField(columnName = FK_TIPO_COMBUSTION) private int fk_tipo_combustion;
    @DatabaseField(columnName = FK_PROTOCOLO) private int fk_protocolo;
    @DatabaseField(columnName = FK_INSTALADOR) private int fk_instalador;
    @DatabaseField(columnName = FK_REMOTO_CENTRAL) private int fk_remoto_central;
    @DatabaseField(columnName = FK_TIPO) private int fk_tipo;
    @DatabaseField(columnName = FK_INSTALACION) private int fk_instalacion;
    @DatabaseField(columnName = FK_ESTADO) private int fk_estado;
    @DatabaseField(columnName = FK_CONTRATO_MANTENIMIENTO) private int fk_contrato_mantenimiento;
    @DatabaseField(columnName = FK_GAMA) private int fk_gama;
    @DatabaseField(columnName = FK_TIPO_GAMA) private int fk_tipo_gama;
    @DatabaseField(columnName = FECHA_CREACION) private String fecha_creacion;
    @DatabaseField(columnName = MODELO) private String modelo;
    @DatabaseField(columnName = NUM_SERIE) private String num_serie;
    @DatabaseField(columnName = NUM_PRODUCTO) private String num_producto;
    @DatabaseField(columnName = APARATO) private String aparato;
    @DatabaseField(columnName = PUESTA_MARCHA) private String puesta_marcha;
    @DatabaseField(columnName = FECHA_COMPRA) private String fecha_compra;
    @DatabaseField(columnName = FECHA_FIN_GARANTIA) private String fecha_fin_garantia;
    @DatabaseField(columnName = MANTENIMIENTO_ANUAL) private String mantenimiento_anual;
    @DatabaseField(columnName = OBSERVACIONES) private String observaciones;
    @DatabaseField(columnName = UBICACION) private String ubicacion;
    @DatabaseField(columnName = TIENDA_COMPRA) private String tienda_compra;
    @DatabaseField(columnName = GARANTIA_EXTENDIDA) private String garantia_extendida;
    @DatabaseField(columnName = FACTURA_COMPRA) private String factura_compra;
    @DatabaseField(columnName = REFRIGERANTE) private String refrigerante;
    @DatabaseField(columnName = BESINSTALACION) private boolean bEsInstalacion;
    @DatabaseField(columnName = NOMBRE_INSTALACION) private String nombre_instalacion;
    @DatabaseField(columnName = EN_PROPIEDAD) private String en_propiedad;
    @DatabaseField(columnName = ESPRINCIPAL) private String esPrincipal;
    public Maquina(){}
    public Maquina(int id_maquina, int fk_direccion, int fk_modelo, int fk_marca, int fk_tipo_combustion,
                   int fk_protocolo, int fk_instalador, int fk_remoto_central, int fk_tipo, int fk_instalacion,
                   int fk_estado, int fk_contrato_mantenimiento, int fk_gama, int fk_tipo_gama,
                   String fecha_creacion, String modelo, String num_serie, String num_producto, String aparato,
                   String puesta_marcha, String fecha_compra, String fecha_fin_garantia,
                   String mantenimiento_anual, String observaciones, String ubicacion, String tienda_compra,
                   String garantia_extendida, String factura_compra, String refrigerante,
                   boolean bEsInstalacion, String nombre_instalacion, String en_propiedad, String esPrincipal) {
        this.id_maquina = id_maquina;
        this.fk_direccion = fk_direccion;
        this.fk_modelo = fk_modelo;
        this.fk_marca = fk_marca;
        this.fk_tipo_combustion = fk_tipo_combustion;
        this.fk_protocolo = fk_protocolo;
        this.fk_instalador = fk_instalador;
        this.fk_remoto_central = fk_remoto_central;
        this.fk_tipo = fk_tipo;
        this.fk_instalacion = fk_instalacion;
        this.fk_estado = fk_estado;
        this.fk_contrato_mantenimiento = fk_contrato_mantenimiento;
        this.fk_gama = fk_gama;
        this.fk_tipo_gama = fk_tipo_gama;
        this.fecha_creacion = fecha_creacion;
        this.modelo = modelo;
        this.num_serie = num_serie;
        this.num_producto = num_producto;
        this.aparato = aparato;
        this.puesta_marcha = puesta_marcha;
        this.fecha_compra = fecha_compra;
        this.fecha_fin_garantia = fecha_fin_garantia;
        this.mantenimiento_anual = mantenimiento_anual;
        this.observaciones = observaciones;
        this.ubicacion = ubicacion;
        this.tienda_compra = tienda_compra;
        this.garantia_extendida = garantia_extendida;
        this.factura_compra = factura_compra;
        this.refrigerante = refrigerante;
        this.bEsInstalacion = bEsInstalacion;
        this.nombre_instalacion = nombre_instalacion;
        this.en_propiedad = en_propiedad;
        this.esPrincipal = esPrincipal;
    }

    public int getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(int id_maquina) {
        this.id_maquina = id_maquina;
    }

    public int getFk_direccion() {
        return fk_direccion;
    }

    public void setFk_direccion(int fk_direccion) {
        this.fk_direccion = fk_direccion;
    }

    public int getFk_modelo() {
        return fk_modelo;
    }

    public void setFk_modelo(int fk_modelo) {
        this.fk_modelo = fk_modelo;
    }

    public int getFk_marca() {
        return fk_marca;
    }

    public void setFk_marca(int fk_marca) {
        this.fk_marca = fk_marca;
    }

    public int getFk_tipo_combustion() {
        return fk_tipo_combustion;
    }

    public void setFk_tipo_combustion(int fk_tipo_combustion) {
        this.fk_tipo_combustion = fk_tipo_combustion;
    }

    public int getFk_protocolo() {
        return fk_protocolo;
    }

    public void setFk_protocolo(int fk_protocolo) {
        this.fk_protocolo = fk_protocolo;
    }

    public int getFk_instalador() {
        return fk_instalador;
    }

    public void setFk_instalador(int fk_instalador) {
        this.fk_instalador = fk_instalador;
    }

    public int getFk_remoto_central() {
        return fk_remoto_central;
    }

    public void setFk_remoto_central(int fk_remoto_central) {
        this.fk_remoto_central = fk_remoto_central;
    }

    public int getFk_tipo() {
        return fk_tipo;
    }

    public void setFk_tipo(int fk_tipo) {
        this.fk_tipo = fk_tipo;
    }

    public int getFk_instalacion() {
        return fk_instalacion;
    }

    public void setFk_instalacion(int fk_instalacion) {
        this.fk_instalacion = fk_instalacion;
    }

    public int getFk_estado() {
        return fk_estado;
    }

    public void setFk_estado(int fk_estado) {
        this.fk_estado = fk_estado;
    }

    public int getFk_contrato_mantenimiento() {
        return fk_contrato_mantenimiento;
    }

    public void setFk_contrato_mantenimiento(int fk_contrato_mantenimiento) {
        this.fk_contrato_mantenimiento = fk_contrato_mantenimiento;
    }

    public int getFk_gama() {
        return fk_gama;
    }

    public void setFk_gama(int fk_gama) {
        this.fk_gama = fk_gama;
    }

    public int getFk_tipo_gama() {
        return fk_tipo_gama;
    }

    public void setFk_tipo_gama(int fk_tipo_gama) {
        this.fk_tipo_gama = fk_tipo_gama;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.num_serie = num_serie;
    }

    public String getNum_producto() {
        return num_producto;
    }

    public void setNum_producto(String num_producto) {
        this.num_producto = num_producto;
    }

    public String getAparato() {
        return aparato;
    }

    public void setAparato(String aparato) {
        this.aparato = aparato;
    }

    public String getPuesta_marcha() {
        return puesta_marcha;
    }

    public void setPuesta_marcha(String puesta_marcha) {
        this.puesta_marcha = puesta_marcha;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public String getFecha_fin_garantia() {
        return fecha_fin_garantia;
    }

    public void setFecha_fin_garantia(String fecha_fin_garantia) {
        this.fecha_fin_garantia = fecha_fin_garantia;
    }

    public String getMantenimiento_anual() {
        return mantenimiento_anual;
    }

    public void setMantenimiento_anual(String mantenimiento_anual) {
        this.mantenimiento_anual = mantenimiento_anual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTienda_compra() {
        return tienda_compra;
    }

    public void setTienda_compra(String tienda_compra) {
        this.tienda_compra = tienda_compra;
    }

    public String getGarantia_extendida() {
        return garantia_extendida;
    }

    public void setGarantia_extendida(String garantia_extendida) {
        this.garantia_extendida = garantia_extendida;
    }

    public String getFactura_compra() {
        return factura_compra;
    }

    public void setFactura_compra(String factura_compra) {
        this.factura_compra = factura_compra;
    }

    public String getRefrigerante() {
        return refrigerante;
    }

    public void setRefrigerante(String refrigerante) {
        this.refrigerante = refrigerante;
    }

    public boolean isbEsInstalacion() {
        return bEsInstalacion;
    }

    public void setbEsInstalacion(boolean bEsInstalacion) {
        this.bEsInstalacion = bEsInstalacion;
    }

    public String getNombre_instalacion() {
        return nombre_instalacion;
    }

    public void setNombre_instalacion(String nombre_instalacion) {
        this.nombre_instalacion = nombre_instalacion;
    }

    public String getEn_propiedad() {
        return en_propiedad;
    }

    public void setEn_propiedad(String en_propiedad) {
        this.en_propiedad = en_propiedad;
    }

    public String getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(String esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
}