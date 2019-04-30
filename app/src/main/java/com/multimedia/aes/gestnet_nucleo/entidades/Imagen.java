package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_imagenes")
public class Imagen {

    public static final String ID_IMAGEN = "_id_imagen";
    public static final String NOMBRE_IMAGEN = "nombre_imagen";
    public static final String RUTA_IMAGEN = "ruta_imagen";
    public static final String FK_PARTE = "fk_parte";
    public static final String FK_ACCION_PROTOCOLO = "fk_accion_protocolo";
    public static final String GALERIA = "galeria";
    public static final String ENVIADO = "enviado";

    @DatabaseField(generatedId = true, columnName = ID_IMAGEN)  private int id_imagen;
    @DatabaseField(columnName = NOMBRE_IMAGEN)                  private String nombre_imagen;
    @DatabaseField(columnName = RUTA_IMAGEN)                    private String ruta_imagen;
    @DatabaseField(columnName = FK_PARTE)                       private int fk_parte;
    @DatabaseField(columnName = FK_ACCION_PROTOCOLO)            private int fk_accion_protocolo;
    @DatabaseField(columnName = GALERIA)                        private boolean galeria;
    @DatabaseField(columnName = ENVIADO)                        private boolean enviado;

    public Imagen() {
    }
    public Imagen(String nombre_imagen, String ruta_imagen, int fk_parte, int fk_accion_protocolo,boolean galeria,boolean enviado) {
        this.nombre_imagen = nombre_imagen;
        this.ruta_imagen = ruta_imagen;
        this.fk_parte = fk_parte;
        this.fk_accion_protocolo = fk_accion_protocolo;
        this.galeria = galeria;
        this.enviado = enviado;
    }

    public int getId_imagen() {
        return id_imagen;
    }
    public void setId_imagen(int id_imagen) {
        this.id_imagen = id_imagen;
    }
    public String getNombre_imagen() {
        return nombre_imagen;
    }
    public void setNombre_imagen(String nombre_imagen) {
        this.nombre_imagen = nombre_imagen;
    }
    public String getRuta_imagen() {
        return ruta_imagen;
    }
    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
    public int getFk_parte() {
        return fk_parte;
    }
    public void setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
    }
    public int getFk_accion_protocolo() {
        return fk_accion_protocolo;
    }
    public void setFk_accion_protocolo(int fk_accion_protocolo) {
        this.fk_accion_protocolo = fk_accion_protocolo;
    }
    public boolean isGaleria() {
        return galeria;
    }
    public void setGaleria(boolean galeria) {
        this.galeria = galeria;
    }
    public boolean isEnviado() {
        return enviado;
    }
    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
}
