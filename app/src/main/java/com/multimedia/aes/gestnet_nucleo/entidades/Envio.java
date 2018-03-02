package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_envios")
public class Envio {

    public static final String ID_ENVIO = "_id_envio";
    public static final String FK_SOLICITUD = "fk_solicitud";
    public static final String JSON_ENVIO = "json_envio";
    public static final String URL_ENVIO = "url_envio";
    public static final String REQUEST_ENVIO = "request_envio";


    @DatabaseField(generatedId = true, columnName = ID_ENVIO)     private int id_envio;
    @DatabaseField(columnName = FK_SOLICITUD)                     private int fk_solicitud;
    @DatabaseField(columnName = JSON_ENVIO)                       private String json_envio;
    @DatabaseField(columnName = URL_ENVIO)                        private String url_envio;
    @DatabaseField(columnName = REQUEST_ENVIO)                    private String request_envio;


    public Envio() {}

    public Envio(int fk_solicitud, String json_envio, String url_envio, String request_envio) {
        this.fk_solicitud = fk_solicitud;
        this.json_envio = json_envio;
        this.url_envio = url_envio;
        this.request_envio = request_envio;
    }

    public int getId_envio() {
        return id_envio;
    }
    public void setId_envio(int id_envio) {
        this.id_envio = id_envio;
    }
    public int getFk_solicitud() {
        return fk_solicitud;
    }
    public void setFk_solicitud(int fk_solicitud) {
        this.fk_solicitud = fk_solicitud;
    }
    public String getJson_envio() {
        return json_envio;
    }
    public void setJson_envio(String json_envio) {
        this.json_envio = json_envio;
    }
    public String getUrl_envio() {
        return url_envio;
    }
    public void setUrl_envio(String url_envio) {
        this.url_envio = url_envio;
    }
    public String getRequest_envio() {
        return request_envio;
    }
    public void setRequest_envio(String request_envio) {
        this.request_envio = request_envio;
    }
}
