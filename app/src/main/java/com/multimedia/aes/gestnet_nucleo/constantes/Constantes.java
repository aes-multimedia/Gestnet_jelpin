package com.multimedia.aes.gestnet_nucleo.constantes;

public class Constantes {
    public static final String IP_INTERNA = "192.168.0.228:8085";
    public static final String IP_EXTERNA = "80.58.161.135:8085";
    public static final String URL_COD_CLIENTE = "http://"+IP_EXTERNA+"/api-gesnet/v1/login/codigo";
    public static final String URL_LOGIN_EXTERNA_PRUEBAS = "http://"+IP_EXTERNA+"/api-gesnet/v1/login/login";
    public static final String URL_PARTES_ID_EXTERNAPRUEBAS = "http://"+IP_EXTERNA+"/api-gesnet-datos/v1/partes/buscarPartePorId";
    public static final String URL_PARTES_EXTERNAPRUEBAS = "http://"+IP_EXTERNA+"/api-gesnet-datos/v1/partes/listarPartes";
    public static final String URL_GEOPOS_EXTERNAPRUEBAS = "http://"+IP_EXTERNA+"/api-gesnet-datos/v1/partes/cargaPosicion";
    public static final String URL_ARTICULOS_EXTERNAPRUEBAS = "http://"+IP_EXTERNA+"/api-gesnet-datos/v1/partes/articulosEntidad";
    public static final String URL_PARTES_FECHA = "http://"+IP_EXTERNA+"/api-gesnet-datos/v1/partes/listarPartesPorFecha";
    public static final String URL_ALTA_NOTIFICACIONES_EXTERNA_PRUEBAS = "http://"+IP_EXTERNA+"/api-gesnet-datos/v1/partes/activarNotificaciones";
    public static final String URL_INICIAR_PARTE_EXTERNAPRUEBAS = "http://"+IP_EXTERNA+"/api-gesnet-datos/v1/partes/iniciar";
    public static final String URL_CIERRE_DIA_EXTERNAPRUEBAS = "http://"+IP_EXTERNA+"/api-gesnet-datos/v1/partes/cierreDiario";

}
