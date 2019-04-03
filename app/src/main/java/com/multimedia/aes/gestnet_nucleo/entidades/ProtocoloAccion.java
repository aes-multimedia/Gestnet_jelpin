package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_protocolos_acciones")
public class ProtocoloAccion {


    public static final String ID = "_id";
    public static final String ID_PROTOCOLO_ACCION = "id_protocolo_accion";
    public static final String FK_MAQUINA = "fk_maquina";
    public static final String FK_PARTE = "fk_parte";
    public static final String VALOR = "valor";
    public static final String FK_PROTOCOLO = "fk_protocolo";
    public static final String NOMBRE_PROTOCOLO = "nombre_protocolo";
    public static final String ID_ACCION = "id_accion";
    public static final String TIPO_ACCION = "tipo_accion";
    public static final String ORDEN = "orden";
    public static final String DESCRIPCION = "descripcion";


    @DatabaseField(generatedId = true, columnName = ID)    private int id;

    @DatabaseField(columnName = ID_PROTOCOLO_ACCION)                 private int id_protocolo_accion;
    @DatabaseField(columnName = FK_MAQUINA)                 private int fk_maquina;
    @DatabaseField(columnName = FK_PARTE)                   private int fk_parte;
    @DatabaseField(columnName = VALOR)                      private String valor;
    @DatabaseField(columnName = FK_PROTOCOLO)                   private int fk_protocolo;
    @DatabaseField(columnName = NOMBRE_PROTOCOLO)                   private String nombre_protocolo;
    @DatabaseField(columnName = ID_ACCION)                   private int id_accion;
    @DatabaseField(columnName = TIPO_ACCION)                   private boolean tipo_accion;
    @DatabaseField(columnName = ORDEN)                   private int orden;
    @DatabaseField(columnName = DESCRIPCION)                   private String descripcion;


    public ProtocoloAccion(){}

    public ProtocoloAccion(int id_protocolo_accion, String valor, int fk_maquina, int fk_parte, int fk_protocolo, String nombre_protocolo, int id_accion, boolean tipo_accion, String descripcion,int orden) {
        this.id_protocolo_accion = id_protocolo_accion;
        this.valor = valor;
        this.fk_maquina = fk_maquina;
        this.fk_parte = fk_parte;
        this.fk_protocolo = fk_protocolo;
        this.nombre_protocolo = nombre_protocolo;
        this.id_accion = id_accion;
        this.tipo_accion = tipo_accion;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    public int getId_protocolo_accion() {
        return id_protocolo_accion;
    }
    public void setId_protocolo_accion(int id_protocolo_accion) {
        this.id_protocolo_accion = id_protocolo_accion;
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
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
    public int getFk_protocolo() {
        return fk_protocolo;
    }
    public void setFk_protocolo(int fk_protocolo) {
        this.fk_protocolo = fk_protocolo;
    }
    public String getNombre_protocolo() {
        return nombre_protocolo;
    }
    public void setNombre_protocolo(String nombre_protocolo) {
        this.nombre_protocolo = nombre_protocolo;
    }
    public int getId_accion() {
        return id_accion;
    }
    public void setId_accion(int id_accion) {
        this.id_accion = id_accion;
    }
    public boolean isTipo_accion() {
        return tipo_accion;
    }
    public void setTipo_accion(boolean tipo_accion) {
        this.tipo_accion = tipo_accion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String toString()
    {
        return( getNombre_protocolo() );
    }
}