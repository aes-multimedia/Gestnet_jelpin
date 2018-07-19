package com.multimedia.aes.gestnet_nucleo.constantes;

public class Constantes {

    public static final String ERROR = "NOK";
    public static final String SUCCES = "OK";
    public static final String IP_INTERNA = "90.69.105.123:8085";
   // public static final String IP_EXTERNA = "90.69.105.123:8085";
    public static final String IP_EXTERNA = "apicentral.gestnet.es";
    public static final String IP_ARRIBA = "imd.gestnet.es";
    public static final String URL_COD_CLIENTE = "http://"+IP_INTERNA+"/api-gesnet/v1/login/codigo";
    public static final String URL_LOGIN_EXTERNA_PRUEBAS = "http://"+IP_INTERNA+"/api-gesnet/v1/login/login";
    public static final String URL_PARTES_ID_EXTERNAPRUEBAS = "/api-gestnet-datos/v1/partes/buscarPartePorId";
    public static final String URL_PARTES_EXTERNAPRUEBAS = "/api-gestnet-datos/v1/partes/listarPartes";
    public static final String URL_GEOPOS_EXTERNAPRUEBAS = "/api-gestnet-datos/v1/partes/cargaPosicion";
    public static final String URL_ARTICULOS_EXTERNAPRUEBAS = "/api-gestnet-datos/v1/partes/articulosEntidad";
    public static final String URL_PARTES_FECHA = "/api-gestnet-datos/v1/partes/listarPartesPorFecha";
    public static final String URL_PARTES_FK_MAQUINA = "/api-gestnet-datos/v1/partes/listarPartesPorFkMaquina";
    public static final String URL_ALTA_NOTIFICACIONES_EXTERNA_PRUEBAS = "/api-gestnet-datos/v1/partes/activarNotificaciones";
    public static final String URL_INICIAR_PARTE_EXTERNAPRUEBAS ="/api-gestnet-datos/v1/partes/iniciar";
    public static final String URL_CIERRE_DIA_EXTERNAPRUEBAS = "/api-gestnet-datos/v1/partes/cierreDiario";
    public static final String URL_CIERRE_PARTE_EXTERNAPRUEBAS = "/api-gestnet-datos/v1/partes/cierreParte";
    public static final String URL_ACTUALIZA_MAQUINA = "/api-gestnet-datos/v1/partes/actualizaMaquina";
    public static final String URL_CREA_MAQUINA = "/api-gestnet-datos/v1/partes/creaMaquina";
    public static final String URL_CREA_MATERIAL = "/api-gestnet-datos/v1/partes/crearMaterial";
    public static final String URL_BUSCAR_ARTICULOS_POR_NOMBRE = "/api-gestnet-datos/v1/partes/buscarMaterial";
    public static final String URL_BUSCAR_ARTICULO = "/api-gestnet-datos/v1/partes/cogerMaterial";
    public static final String URL_BUSCAR_DOCUMENTOS_PARTE = "/api-gestnet-datos/v1/partes/documentos";
    public static final String URL_BUSCAR_DOCUMENTOS_MODELO = "/api-gestnet-datos/v1/partes/documentosModelo";
    public static final String URL_LISTAR_STOCK_TECNICOS = "/api-gestnet-datos/v1/partes/stockAlmacenes";
    public static final String URL_DATOS_PRESUPUESTO = "/api-gestnet-datos/v1/partes/datosPresupuesto";
    public static final String URL_GUARDAR_PRESUPUESTO = "/api-gestnet-datos/v1/partes/guardarPresupuesto";
    public static final String URL_INTERVENCIONES_ANTERIORES = "/api-gestnet-datos/v1/partes/listarIntervencionesAnteriores";
    public static final String URL_IMAGENES_INTERVENCIONES_ANTERIORES = "/api-gestnet-datos/v1/partes/listarImagenesPorFkParte";
    public static final String URL_ACTUALZIAR_STOCK_ALMACEN = "/api-gestnet-datos/v1/partes/actualizarStockAlmacenTecnico";

    public static final String PATH = "/data/data/com.multimedia.aes.gestnet_nucleo/app_imageDir";

}
