package com.multimedia.aes.gestnet_nucleo.constantes;

public class Constantes {

    public static final String ERROR = "NOK";
    public static final String SUCCES = "OK";
    public static final String IP_INTERNA = "90.69.105.123:8085";
    public static final String IP_EXTERNA_ABAJO = "192.168.111.228:8085";
    public static final String IP_EXTERNA_ARRIBA = "apicentral.gestnet.es";
    public static final String URL_COD_CLIENTE = "http://"+ IP_EXTERNA_ABAJO +"/api-gesnet/v1/login/codigo";
    public static final String URL_LOGIN = "http://"+ IP_EXTERNA_ABAJO +"/api-gesnet/v1/login/login";
    public static final String URL_PARTES_ID = "/api-gestnet-datos/v1/partes/buscarPartePorId";
    public static final String URL_PARTES_ID_ASIGNAR = "/api-gestnet-datos/v1/partes/asignarPartePorId";
    public static final String URL_PARTES = "/api-gestnet-datos/v1/partes/listarPartes";
    public static final String URL_BUSCAR_PARTES = "/api-gestnet-datos/v1/partes/buscarPartePorNumero";
    public static final String URL_GEOPOS = "/api-gestnet-datos/v1/partes/cargaPosicion";
    public static final String URL_ARTICULOS = "/api-gestnet-datos/v1/partes/articulosEntidad";
    public static final String URL_PARTES_FECHA = "/api-gestnet-datos/v1/partes/listarPartesPorFecha";
    public static final String URL_PARTES_FK_MAQUINA = "/api-gestnet-datos/v1/partes/listarPartesPorFkMaquina";
    public static final String URL_ALTA_NOTIFICACIONES = "/api-gestnet-datos/v1/partes/activarNotificaciones";
    public static final String URL_INICIAR_PARTE ="/api-gestnet-datos/v1/partes/iniciar";
    public static final String URL_CIERRE_DIA = "/api-gestnet-datos/v1/partes/cierreDiario";
    public static final String URL_KILOMETROS = "/api-gestnet-datos/v1/partes/kilometros";
    public static final String URL_CIERRE_PARTE = "/api-gestnet-datos/v1/partes/cierreParte";
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
    public static final String URL_IMAGENES_INTERVENCIONES_ANTERIORES_USUARIO = "/api-gestnet-datos/v1/partes/listarImagenesPorFkUsuario";
    public static final String URL_ACTUALZIAR_STOCK_ALMACEN = "/api-gestnet-datos/v1/partes/actualizarStockAlmacenTecnico";
    public static final String URL_IMAGENES_ACCIONES = "/api-gestnet-datos/v1/partes/imagenAccion";
    public static final String URL_BORRAR_IMAGENES_ACCIONES = "/api-gestnet-datos/v1/partes/borrarImagenAccion";
    public static final String PATH = "/data/data/com.multimedia.aes.gestnet_nucleo/app_imageDir";
}
