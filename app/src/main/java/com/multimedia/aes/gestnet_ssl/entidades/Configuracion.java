package com.multimedia.aes.gestnet_ssl.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_configuraciones")
public class Configuracion {

    public static final String ID_CONFIGURACION = "_id_configuracion";
    public static final String HORARIOS = "horarios";
    public static final String OPERARIOS = "operarios";
    public static final String DEFINICIONES = "definiciones";
    public static final String EQUIPOS = "equipos";
    public static final String EMPRESAS = "empresas";
    public static final String MARCAS = "marcas";
    public static final String TIPOS_TRABAJO = "tipos_trabajo";
    public static final String TIPOS_PRESUPUESTO = "tipos_presupuesto";
    public static final String CUENTA_BANCARIA = "cuenta_bancaria";
    public static final String FK_COMBUSTION = "fk_combustion";
    public static final String GARANTIA = "garantia";
    public static final String PEDIR = "pedir";
    public static final String USAR = "usar";
    public static final String PRESUPUESTAR = "presupuestar";
    public static final String OPERACION_FINALIZACION = "operacion_finalizacion";
    public static final String PRECIOS_MANO_OBRA = "precios_mano_obra";
    public static final String FORMA_PAGO = "formaPago";
    public static final String DISP_SERVICIO = "disp_servicio";
    public static final String ANALISIS_COMBUSTION = "analisis_combustion";
    public static final String PUESTA_MARCHA = "puesta_marcha";
    public static final String SERVICIO_URGENCIA = "servicio_urgencia";
    public static final String KMS_FINALIZACION = "kms_finalizacion";
    public static final String TRASPASO_MATERIAL = "traspaso_material";
    public static final String PARTE_USUARIO = "parte_usuario";
    public static final String PARTE_AVERIA = "parte_averia";
    public static final String PARTE_INSTALACION = "parte_instalacion";
    public static final String PARTE_MATERIALES = "parte_materiales";
    public static final String PARTE_FINALIZACION = "parte_finalizacion";
    public static final String PARTE_GALERIA = "parte_galeria";
    public static final String MENU_ASIGNACION = "menu_asignacion";
    public static final String MENU_DOCUMENTOS = "menu_documentos";
    public static final String MENU_ALMACEN = "menu_almacen";
    public static final String MENU_CIERRE = "menu_cierre";
    public static final String MENU_UBICACION = "menu_ubicacion";
    public static final String MENU_DATOS_COMPLETOS = "menu_datos_completos";
    public static final String MENU_INFORMAR = "menu_informar";
    public static final String MENU_DATOS_ACTUALIZADOS = "menu_datos_actualizados";
    public static final String MENU_PRESUPUESTO = "menu_presupuesto";
    public static final String REQUIERE_FIRMA = "requiere_firma";
    public static final String USUARIO_CONF = "usuario_conf";
    public static final String PASS_CONF = "pass_conf";
    public static final String INTERSAT = "intersat";
    public static final String GAS_NATURAL = "gas_natural";
    public static final String JLSAT = "jlsat";
    public static final String DURACION_AUTOMATICA = "duracion_automatica";
    public static final String CONTADOR_KM = "contador_km";
    public static final String SUBIDA_INMEDIATA = "bSubidaInmediataImagen";


    @DatabaseField(id = true, columnName = ID_CONFIGURACION)    private int id_configuracion;
    @DatabaseField(columnName = HORARIOS)                       private boolean horarios;
    @DatabaseField(columnName = OPERARIOS)                      private boolean operarios;
    @DatabaseField(columnName = DEFINICIONES)                   private boolean definiciones;
    @DatabaseField(columnName = EQUIPOS)                        private boolean equipos;
    @DatabaseField(columnName = EMPRESAS)                       private boolean empresas;
    @DatabaseField(columnName = MARCAS)                         private boolean marcas;
    @DatabaseField(columnName = TIPOS_TRABAJO)                  private boolean tipos_trabajo;
    @DatabaseField(columnName = TIPOS_PRESUPUESTO)              private boolean tipos_presupuesto;
    @DatabaseField(columnName = CUENTA_BANCARIA)                private boolean cuenta_bancaria;
    @DatabaseField(columnName = FK_COMBUSTION)                  private boolean fk_combustion;
    @DatabaseField(columnName = GARANTIA)                       private boolean garantia;
    @DatabaseField(columnName = PEDIR)                          private boolean pedir;
    @DatabaseField(columnName = USAR)                           private boolean usar;
    @DatabaseField(columnName = PRESUPUESTAR)                   private boolean presupuestar;
    @DatabaseField(columnName = OPERACION_FINALIZACION)         private boolean operacion_finalizacion;
    @DatabaseField(columnName = PRECIOS_MANO_OBRA)              private boolean precios_mano_obra;
    @DatabaseField(columnName = FORMA_PAGO)                     private boolean formaPago;
    @DatabaseField(columnName = DISP_SERVICIO)                  private boolean disp_servicio;
    @DatabaseField(columnName = ANALISIS_COMBUSTION)            private boolean analisis_combustion;
    @DatabaseField(columnName = PUESTA_MARCHA)                  private boolean puesta_marcha;
    @DatabaseField(columnName = SERVICIO_URGENCIA)              private boolean servicio_urgencia;
    @DatabaseField(columnName = KMS_FINALIZACION)               private boolean kms_finalizacion;
    @DatabaseField(columnName = TRASPASO_MATERIAL)              private boolean traspaso_material;
    @DatabaseField(columnName = PARTE_USUARIO)                  private boolean parte_usuario;
    @DatabaseField(columnName = PARTE_AVERIA)                   private boolean parte_averia;
    @DatabaseField(columnName = PARTE_INSTALACION)              private boolean parte_instalacion;
    @DatabaseField(columnName = PARTE_MATERIALES)               private boolean parte_materiales;
    @DatabaseField(columnName = PARTE_FINALIZACION)             private boolean parte_finalizacion;
    @DatabaseField(columnName = PARTE_GALERIA)                  private boolean parte_galeria;
    @DatabaseField(columnName = MENU_ASIGNACION)                private boolean menu_asignacion;
    @DatabaseField(columnName = MENU_DOCUMENTOS)                private boolean menu_documentos;
    @DatabaseField(columnName = MENU_ALMACEN)                   private boolean menu_almacen;
    @DatabaseField(columnName = MENU_CIERRE)                    private boolean menu_cierre;
    @DatabaseField(columnName = MENU_UBICACION)                 private boolean menu_ubicacion;
    @DatabaseField(columnName = MENU_DATOS_COMPLETOS)           private boolean menu_datos_completos;
    @DatabaseField(columnName = MENU_INFORMAR)                  private boolean menu_informar;
    @DatabaseField(columnName = MENU_DATOS_ACTUALIZADOS)        private boolean menu_datos_actualizados;
    @DatabaseField(columnName = MENU_PRESUPUESTO)               private boolean menu_presupuesto;
    @DatabaseField(columnName = REQUIERE_FIRMA)                 private boolean requiere_firma;
    @DatabaseField(columnName = USUARIO_CONF)                   private boolean usuario_conf;
    @DatabaseField(columnName = PASS_CONF)                      private boolean pass_conf;
    @DatabaseField(columnName = INTERSAT)                       private boolean intersat;
    @DatabaseField(columnName = GAS_NATURAL)                    private boolean gas_natural;
    @DatabaseField(columnName = JLSAT)                          private boolean jlsat;
    @DatabaseField(columnName = DURACION_AUTOMATICA)            private boolean duracion_automatica;
    @DatabaseField(columnName = CONTADOR_KM)                    private boolean contador_km;
    @DatabaseField(columnName = SUBIDA_INMEDIATA)               private boolean bSubidaInmediataImagen;

    public Configuracion(){}
    public Configuracion(int id_configuracion, boolean horarios, boolean operarios, boolean definiciones,
                         boolean equipos, boolean empresas, boolean marcas, boolean tipos_trabajo,
                         boolean tipos_presupuesto, boolean cuenta_bancaria, boolean fk_combustion,
                         boolean garantia, boolean pedir, boolean usar, boolean presupuestar,
                         boolean operacion_finalizacion, boolean precios_mano_obra, boolean formaPago,
                         boolean disp_servicio, boolean analisis_combustion, boolean puesta_marcha,
                         boolean servicio_urgencia, boolean kms_finalizacion, boolean traspaso_material,
                         boolean parte_usuario, boolean parte_averia, boolean parte_instalacion,
                         boolean parte_materiales, boolean parte_finalizacion, boolean parte_galeria,
                         boolean menu_asignacion, boolean menu_documentos, boolean menu_almacen,
                         boolean menu_cierre, boolean menu_ubicacion, boolean menu_datos_completos,
                         boolean menu_informar, boolean menu_datos_actualizados, boolean menu_presupuesto,
                         boolean requiere_firma, boolean usuario_conf, boolean pass_conf, boolean intersat,
                         boolean gas_natural, boolean jlsat, boolean duracion_automatica, boolean contador_km,
                         boolean bSubidaInmediataImagen) {
        this.id_configuracion = id_configuracion;
        this.horarios = horarios;
        this.operarios = operarios;
        this.definiciones = definiciones;
        this.equipos = equipos;
        this.empresas = empresas;
        this.marcas = marcas;
        this.tipos_trabajo = tipos_trabajo;
        this.tipos_presupuesto = tipos_presupuesto;
        this.cuenta_bancaria = cuenta_bancaria;
        this.fk_combustion = fk_combustion;
        this.garantia = garantia;
        this.pedir = pedir;
        this.usar = usar;
        this.presupuestar = presupuestar;
        this.operacion_finalizacion = operacion_finalizacion;
        this.precios_mano_obra = precios_mano_obra;
        this.formaPago = formaPago;
        this.disp_servicio = disp_servicio;
        this.analisis_combustion = analisis_combustion;
        this.puesta_marcha = puesta_marcha;
        this.servicio_urgencia = servicio_urgencia;
        this.kms_finalizacion = kms_finalizacion;
        this.traspaso_material = traspaso_material;
        this.parte_usuario = parte_usuario;
        this.parte_averia = parte_averia;
        this.parte_instalacion = parte_instalacion;
        this.parte_materiales = parte_materiales;
        this.parte_finalizacion = parte_finalizacion;
        this.parte_galeria = parte_galeria;
        this.menu_asignacion = menu_asignacion;
        this.menu_documentos = menu_documentos;
        this.menu_almacen = menu_almacen;
        this.menu_cierre = menu_cierre;
        this.menu_ubicacion = menu_ubicacion;
        this.menu_datos_completos = menu_datos_completos;
        this.menu_informar = menu_informar;
        this.menu_datos_actualizados = menu_datos_actualizados;
        this.menu_presupuesto = menu_presupuesto;
        this.requiere_firma = requiere_firma;
        this.usuario_conf = usuario_conf;
        this.pass_conf = pass_conf;
        this.intersat = intersat;
        this.gas_natural = gas_natural;
        this.jlsat = jlsat;
        this.duracion_automatica = duracion_automatica;
        this.contador_km = contador_km;
        this.bSubidaInmediataImagen = bSubidaInmediataImagen;
    }

    public int getId_configuracion() {
        return id_configuracion;
    }
    public void setId_configuracion(int id_configuracion) {
        this.id_configuracion = id_configuracion;
    }
    public boolean isHorarios() {
        return horarios;
    }
    public void setHorarios(boolean horarios) {
        this.horarios = horarios;
    }
    public boolean isOperarios() {
        return operarios;
    }
    public void setOperarios(boolean operarios) {
        this.operarios = operarios;
    }
    public boolean isDefiniciones() {
        return definiciones;
    }
    public void setDefiniciones(boolean definiciones) {
        this.definiciones = definiciones;
    }
    public boolean isEquipos() {
        return equipos;
    }
    public void setEquipos(boolean equipos) {
        this.equipos = equipos;
    }
    public boolean isEmpresas() {
        return empresas;
    }
    public void setEmpresas(boolean empresas) {
        this.empresas = empresas;
    }
    public boolean isMarcas() {
        return marcas;
    }
    public void setMarcas(boolean marcas) {
        this.marcas = marcas;
    }
    public boolean isTipos_trabajo() {
        return tipos_trabajo;
    }
    public void setTipos_trabajo(boolean tipos_trabajo) {
        this.tipos_trabajo = tipos_trabajo;
    }
    public boolean isTipos_presupuesto() {
        return tipos_presupuesto;
    }
    public void setTipos_presupuesto(boolean tipos_presupuesto) {
        this.tipos_presupuesto = tipos_presupuesto;
    }
    public boolean isCuenta_bancaria() {
        return cuenta_bancaria;
    }
    public void setCuenta_bancaria(boolean cuenta_bancaria) {
        this.cuenta_bancaria = cuenta_bancaria;
    }
    public boolean isFk_combustion() {
        return fk_combustion;
    }
    public void setFk_combustion(boolean fk_combustion) {
        this.fk_combustion = fk_combustion;
    }
    public boolean isGarantia() {
        return garantia;
    }
    public void setGarantia(boolean garantia) {
        this.garantia = garantia;
    }
    public boolean isPedir() {
        return pedir;
    }
    public void setPedir(boolean pedir) {
        this.pedir = pedir;
    }
    public boolean isUsar() {
        return usar;
    }
    public void setUsar(boolean usar) {
        this.usar = usar;
    }
    public boolean isPresupuestar() {
        return presupuestar;
    }
    public void setPresupuestar(boolean presupuestar) {
        this.presupuestar = presupuestar;
    }
    public boolean isOperacion_finalizacion() {
        return operacion_finalizacion;
    }
    public void setOperacion_finalizacion(boolean operacion_finalizacion) {
        this.operacion_finalizacion = operacion_finalizacion;
    }
    public boolean isPrecios_mano_obra() {
        return precios_mano_obra;
    }
    public void setPrecios_mano_obra(boolean precios_mano_obra) {
        this.precios_mano_obra = precios_mano_obra;
    }
    public boolean isFormaPago() {
        return formaPago;
    }
    public void setFormaPago(boolean formaPago) {
        this.formaPago = formaPago;
    }
    public boolean isDisp_servicio() {
        return disp_servicio;
    }
    public void setDisp_servicio(boolean disp_servicio) {
        this.disp_servicio = disp_servicio;
    }
    public boolean isAnalisis_combustion() {
        return analisis_combustion;
    }
    public void setAnalisis_combustion(boolean analisis_combustion) {
        this.analisis_combustion = analisis_combustion;
    }
    public boolean isPuesta_marcha() {
        return puesta_marcha;
    }
    public void setPuesta_marcha(boolean puesta_marcha) {
        this.puesta_marcha = puesta_marcha;
    }
    public boolean isServicio_urgencia() {
        return servicio_urgencia;
    }
    public void setServicio_urgencia(boolean servicio_urgencia) {
        this.servicio_urgencia = servicio_urgencia;
    }
    public boolean isKms_finalizacion() {
        return kms_finalizacion;
    }
    public void setKms_finalizacion(boolean kms_finalizacion) {
        this.kms_finalizacion = kms_finalizacion;
    }
    public boolean isTraspaso_material() {
        return traspaso_material;
    }
    public void setTraspaso_material(boolean traspaso_material) {
        this.traspaso_material = traspaso_material;
    }
    public boolean isParte_usuario() {
        return parte_usuario;
    }
    public void setParte_usuario(boolean parte_usuario) {
        this.parte_usuario = parte_usuario;
    }
    public boolean isParte_averia() {
        return parte_averia;
    }
    public void setParte_averia(boolean parte_averia) {
        this.parte_averia = parte_averia;
    }
    public boolean isParte_instalacion() {
        return parte_instalacion;
    }
    public void setParte_instalacion(boolean parte_instalacion) {
        this.parte_instalacion = parte_instalacion;
    }
    public boolean isParte_materiales() {
        return parte_materiales;
    }
    public void setParte_materiales(boolean parte_materiales) {
        this.parte_materiales = parte_materiales;
    }
    public boolean isParte_finalizacion() {
        return parte_finalizacion;
    }
    public void setParte_finalizacion(boolean parte_finalizacion) {
        this.parte_finalizacion = parte_finalizacion;
    }
    public boolean isParte_galeria() {
        return parte_galeria;
    }
    public void setParte_galeria(boolean parte_galeria) {
        this.parte_galeria = parte_galeria;
    }
    public boolean isMenu_asignacion() {
        return menu_asignacion;
    }
    public void setMenu_asignacion(boolean menu_asignacion) {
        this.menu_asignacion = menu_asignacion;
    }
    public boolean isMenu_documentos() {
        return menu_documentos;
    }
    public void setMenu_documentos(boolean menu_documentos) {
        this.menu_documentos = menu_documentos;
    }
    public boolean isMenu_almacen() {
        return menu_almacen;
    }
    public void setMenu_almacen(boolean menu_almacen) {
        this.menu_almacen = menu_almacen;
    }
    public boolean isMenu_cierre() {
        return menu_cierre;
    }
    public void setMenu_cierre(boolean menu_cierre) {
        this.menu_cierre = menu_cierre;
    }
    public boolean isMenu_ubicacion() {
        return menu_ubicacion;
    }
    public void setMenu_ubicacion(boolean menu_ubicacion) {
        this.menu_ubicacion = menu_ubicacion;
    }
    public boolean isMenu_datos_completos() {
        return menu_datos_completos;
    }
    public void setMenu_datos_completos(boolean menu_datos_completos) {
        this.menu_datos_completos = menu_datos_completos;
    }
    public boolean isMenu_informar() {
        return menu_informar;
    }
    public void setMenu_informar(boolean menu_informar) {
        this.menu_informar = menu_informar;
    }
    public boolean isMenu_datos_actualizados() {
        return menu_datos_actualizados;
    }
    public void setMenu_datos_actualizados(boolean menu_datos_actualizados) {
        this.menu_datos_actualizados = menu_datos_actualizados;
    }
    public boolean isMenu_presupuesto() {
        return menu_presupuesto;
    }
    public void setMenu_presupuesto(boolean menu_presupuesto) {
        this.menu_presupuesto = menu_presupuesto;
    }
    public boolean isRequiere_firma() {
        return requiere_firma;
    }
    public void setRequiere_firma(boolean requiere_firma) {
        this.requiere_firma = requiere_firma;
    }
    public boolean isUsuario_conf() {
        return usuario_conf;
    }
    public void setUsuario_conf(boolean usuario_conf) {
        this.usuario_conf = usuario_conf;
    }
    public boolean isPass_conf() {
        return pass_conf;
    }
    public void setPass_conf(boolean pass_conf) {
        this.pass_conf = pass_conf;
    }
    public boolean isIntersat() {
        return intersat;
    }
    public void setIntersat(boolean intersat) {
        this.intersat = intersat;
    }
    public boolean isGas_natural() {
        return gas_natural;
    }
    public void setGas_natural(boolean gas_natural) {
        this.gas_natural = gas_natural;
    }
    public boolean isJlsat() {
        return jlsat;
    }
    public void setJlsat(boolean jlsat) {
        this.jlsat = jlsat;
    }
    public boolean isDuracion_automatica() {
        return duracion_automatica;
    }
    public void setDuracion_automatica(boolean duracion_automatica) {
        this.duracion_automatica = duracion_automatica;
    }
    public boolean isContador_km() {
        return contador_km;
    }
    public void setContador_km(boolean contador_km) {
        this.contador_km = contador_km;
    }

    public boolean isbSubidaInmediataImagen() {
        return bSubidaInmediataImagen;
    }

    public void setbSubidaInmediataImagen(boolean bSubidaInmediataImagen) {
        this.bSubidaInmediataImagen = bSubidaInmediataImagen;
    }
}
