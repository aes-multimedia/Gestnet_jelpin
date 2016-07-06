package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_mantenimientos")
public class Mantenimiento {

    public static final String ID_MANTENIMIENTO = "_id_mantenimiento";
    public static final String FK_USER_CREADOR = "fk_user_creador";
    public static final String FK_TECNICO = "fk_tecnico";
    public static final String FK_USUARIO = "fk_usuario";
    public static final String FK_EMPRESA_USUARIO = "fk_empresa_usuario";
    public static final String NUMERO_USUARIO = "numero_usuario";
    public static final String NOMBRE_USUARIO = "nombre_usuario";
    public static final String DNI_USUARIO = "dni_usuario";
    public static final String TELEFONO1_USUARIO = "telefono1_usuario";
    public static final String TELEFONO2_USUARIO = "telefono2_usuario";
    public static final String TELEFONO3_USUARIO = "telefono3_usuario";
    public static final String TELEFONO4_USUARIO = "telefono4_usuario";
    public static final String TELEFONO5_USUARIO = "telefono5_usuario";
    public static final String EMAIL_USUARIO = "email_usuario";
    public static final String MOROSO_USUARIO = "moroso_usuario";
    public static final String OBSERVACIONES_USUARIO = "observaciones_usuario";
    public static final String FK_DIRECCION = "fk_direccion";
    public static final String DIRECCION = "direccion";
    public static final String COD_POSTAL = "codigo_postal";
    public static final String PROVINCIA = "provincia";
    public static final String MUNICIPIO = "municipio";
    public static final String FK_MAQUINA = "fk_maquina";
    public static final String FECHA_CREACION = "fecha_creacion";
    public static final String FECHA_AVISO = "fecha_aviso";
    public static final String FECHA_VISITA = "fecha_visita";
    public static final String VISITA_DUPLICADA = "visita_duplicada";
    public static final String FECHA_REPARACION = "fecha_reparacion";
    public static final String NUM_PARTE = "num_parte";
    public static final String FK_TIPO = "fk_tipo";
    public static final String FK_USER_ASIGNACION = "fk_user_asignacion";
    public static final String FK_HORARIO = "fk_horario";
    public static final String FRANJA_HORARIA = "franja_horaria";
    public static final String FK_FRANJA_IP = "fk_franja_ip";
    public static final String FK_ESTADO = "fk_estado";
    public static final String OBSERVACIONES = "observaciones";
    public static final String OBSERVACIONESASIGNACION = "observacionesAsignacion";
    public static final String CONFIRMADO = "confirmado";
    public static final String IMPRIMIR = "imprimir";
    public static final String FECHA_FACTURA = "fecha_factura";
    public static final String NUM_FACTURA = "num_factura";
    public static final String FECHA_FACTURA_RECTIFICATIVA = "fecha_factura_rectificativa";
    public static final String NUM_FACTURA_RECTIFICATIVA = "num_factura_rectificativa";
    public static final String FK_PEND_FACT = "fk_pend_fact";
    public static final String NUM_ORDEN_ENDESA = "num_orden_endesa";
    public static final String FECHA_MAXIMA_ENDESA = "fecha_maxima_endesa";
    public static final String FK_ESTADO_ENDESA = "fk_estado_endesa";
    public static final String INSISTENCIA_ENDESA = "insistencia_endesa";
    public static final String CONTRATO_ENDESA = "contrato_endesa";
    public static final String PRODUCTO_ENDESA = "producto_endesa";
    public static final String FK_TIPO_OS = "fk_tipo_os";
    public static final String FK_TIPO_PRODUCTO = "fk_tipo_producto";
    public static final String PAGADO_ENDESA = "pagado_endesa";
    public static final String CICLO_LIQ_ENDESA = "ciclo_liq_endesa";
    public static final String IMPORTE_PAGO_ENDESA = "importe_pago_endesa";
    public static final String FECHA_PAGADO_ENDESA = "fecha_pagado_endesa";
    public static final String PAGADO_OPERARIO = "pagado_operario";
    public static final String FECHA_ANULADO = "fecha_anulado";
    public static final String FECHA_MODIFICACION_TECNICO = "fecha_modificacion_tecnico";
    public static final String FK_REMOTO_CENTRAL = "fk_remoto_central";
    public static final String FAC_NOMBRE = "fac_nombre";
    public static final String FAC_DIRECCION = "fac_direccion";
    public static final String FAC_CP = "fac_cp";
    public static final String FAC_POBLACION = "fac_poblacion";
    public static final String FAC_PROVINCIA = "fac_provincia";
    public static final String FAC_DNI = "fac_DNI";
    public static final String FAC_EMAIL = "fac_email";
    public static final String FAC_TELEFONOS = "fac_telefonos";
    public static final String OTROS_SINTOMAS = "otros_sintomas";
    public static final String FECHA_BAJA = "fecha_baja";
    public static final String FAC_BAJA_STOCK = "fac_baja_stock";
    public static final String ESTADO_ANDROID = "estado_android";
    public static final String FK_TIPO_URGENCIA = "fk_tipo_urgencia";
    public static final String FECHA_CIERRE = "fecha_cierre";
    public static final String NUM_LOTE = "num_lote";
    public static final String BENBATCH = "bEnBatch";
    public static final String COD_VISITA = "cod_visita";
    public static final String FECHA_ENVIO_CARTA = "fecha_envio_carta";
    public static final String BCARTAENVIADA = "bCartaEnviada";
    public static final String FECHA_OTRO_DIA = "fecha_otro_dia";
    public static final String FECHA_AUSENTE_LIMITE = "fecha_ausente_limite";
    public static final String FK_CARGA_ARCHIVO = "fk_carga_archivo";
    public static final String ORDEN = "orden";
    public static final String HISTORICO = "historico";
    public static final String FK_TIPO_URGENCIA_FACTURA = "fk_tipo_urgencia_factura";
    public static final String ERROR_BATCH = "error_batch";
    public static final String FK_BATCH_ACTUAL = "fk_batch_actual";
    public static final String FK_EFV = "fk_efv";
    public static final String SCORING = "scoring";
    public static final String FK_CATEGORIA_VISITA = "fk_categoria_visita";
    public static final String CONTADOR_AVERIAS = "contador_averias";

    @DatabaseField(id = true, columnName = ID_MANTENIMIENTO)
    private int id_mantenimiento;
    @DatabaseField(columnName = FK_USER_CREADOR)
    private int fk_user_creador;
    @DatabaseField(columnName = FK_TECNICO)
    private int fk_tecnico;
    @DatabaseField(columnName = FK_USUARIO)
    private int fk_usuario;

    @DatabaseField(columnName = FK_EMPRESA_USUARIO)
    private int fk_empresa_usuario;
    @DatabaseField(columnName = NUMERO_USUARIO)
    private String numero_usuario;
    @DatabaseField(columnName = NOMBRE_USUARIO)
    private String nombre_usuario;
    @DatabaseField(columnName = DNI_USUARIO)
    private String dni_usuario;
    @DatabaseField(columnName = TELEFONO1_USUARIO)
    private String telefono1_usuario;
    @DatabaseField(columnName = TELEFONO2_USUARIO)
    private String telefono2_usuario;
    @DatabaseField(columnName = TELEFONO3_USUARIO)
    private String telefono3_usuario;
    @DatabaseField(columnName = TELEFONO4_USUARIO)
    private String telefono4_usuario;
    @DatabaseField(columnName = TELEFONO5_USUARIO)
    private String telefono5_usuario;
    @DatabaseField(columnName = EMAIL_USUARIO)
    private String email_usuario;
    @DatabaseField(columnName = MOROSO_USUARIO)
    private String moroso_usuario;
    @DatabaseField(columnName = OBSERVACIONES_USUARIO)
    private String observaciones_usuario;

    @DatabaseField(columnName = FK_DIRECCION)
    private int fk_direccion;
    @DatabaseField(columnName = DIRECCION)
    private String direccion;
    @DatabaseField(columnName = COD_POSTAL)
    private String cod_postal;
    @DatabaseField(columnName = PROVINCIA)
    private String provincia;
    @DatabaseField(columnName = MUNICIPIO)
    private String municipio;
    @DatabaseField(columnName = FK_MAQUINA)
    private int fk_maquina;
    @DatabaseField(columnName = FECHA_CREACION)
    private String fecha_creacion;
    @DatabaseField(columnName = FECHA_AVISO)
    private String fecha_aviso;
    @DatabaseField(columnName = FECHA_VISITA)
    private String fecha_visita;
    @DatabaseField(columnName = VISITA_DUPLICADA)
    private String visita_duplicada;
    @DatabaseField(columnName = FECHA_REPARACION)
    private String fecha_reparacion;
    @DatabaseField(columnName = NUM_PARTE)
    private String num_parte;
    @DatabaseField(columnName = FK_TIPO)
    private int fk_tipo;
    @DatabaseField(columnName = FK_USER_ASIGNACION)
    private int fk_user_asignacion;
    @DatabaseField(columnName = FK_HORARIO)
    private int fk_horario;
    @DatabaseField(columnName = FRANJA_HORARIA)
    private String franja_horaria;
    @DatabaseField(columnName = FK_FRANJA_IP)
    private int fk_franja_ip;
    @DatabaseField(columnName = FK_ESTADO)
    private int fk_estado;
    @DatabaseField(columnName = OBSERVACIONES)
    private String observaciones;
    @DatabaseField(columnName = OBSERVACIONESASIGNACION)
    private String observacionesAsignacion;
    @DatabaseField(columnName = CONFIRMADO)
    private String confirmado;
    @DatabaseField(columnName = IMPRIMIR)
    private String imprimir;
    @DatabaseField(columnName = FECHA_FACTURA)
    private String fecha_factura;
    @DatabaseField(columnName = NUM_FACTURA)
    private String num_factura;
    @DatabaseField(columnName = FECHA_FACTURA_RECTIFICATIVA)
    private String fecha_factura_rectificativa;
    @DatabaseField(columnName = NUM_FACTURA_RECTIFICATIVA)
    private String num_factura_rectificativa;
    @DatabaseField(columnName = FK_PEND_FACT)
    private int fk_pend_fact;
    @DatabaseField(columnName = NUM_ORDEN_ENDESA)
    private String num_orden_endesa;
    @DatabaseField(columnName = FECHA_MAXIMA_ENDESA)
    private String fecha_maxima_endesa;
    @DatabaseField(columnName = FK_ESTADO_ENDESA)
    private int fk_estado_endesa;
    @DatabaseField(columnName = INSISTENCIA_ENDESA)
    private String insistencia_endesa;
    @DatabaseField(columnName = CONTRATO_ENDESA)
    private String contrato_endesa;
    @DatabaseField(columnName = PRODUCTO_ENDESA)
    private String producto_endesa;
    @DatabaseField(columnName = FK_TIPO_OS)
    private int fk_tipo_os;
    @DatabaseField(columnName = FK_TIPO_PRODUCTO)
    private int fk_tipo_producto;
    @DatabaseField(columnName = PAGADO_ENDESA)
    private String pagado_endesa;
    @DatabaseField(columnName = CICLO_LIQ_ENDESA)
    private String ciclo_liq_endesa;
    @DatabaseField(columnName = IMPORTE_PAGO_ENDESA)
    private String importe_pago_endesa;
    @DatabaseField(columnName = FECHA_PAGADO_ENDESA)
    private String fecha_pagado_endesa;
    @DatabaseField(columnName = PAGADO_OPERARIO)
    private String pagado_operario;
    @DatabaseField(columnName = FECHA_ANULADO)
    private String fecha_anulado;
    @DatabaseField(columnName = FECHA_MODIFICACION_TECNICO)
    private String fecha_modificacion_tecnico;
    @DatabaseField(columnName = FK_REMOTO_CENTRAL)
    private int fk_remoto_central;
    @DatabaseField(columnName = FAC_NOMBRE)
    private String fac_nombre;
    @DatabaseField(columnName = FAC_DIRECCION)
    private String fac_direccion;
    @DatabaseField(columnName = FAC_CP)
    private String fac_cp;
    @DatabaseField(columnName = FAC_POBLACION)
    private String fac_poblacion;
    @DatabaseField(columnName = FAC_PROVINCIA)
    private String fac_provincia;
    @DatabaseField(columnName = FAC_DNI)
    private String fac_DNI;
    @DatabaseField(columnName = FAC_EMAIL)
    private String fac_email;
    @DatabaseField(columnName = FAC_TELEFONOS)
    private String fac_telefonos;
    @DatabaseField(columnName = OTROS_SINTOMAS)
    private String otros_sintomas;
    @DatabaseField(columnName = FECHA_BAJA)
    private String fecha_baja;
    @DatabaseField(columnName = FAC_BAJA_STOCK)
    private String fac_baja_stock;
    @DatabaseField(columnName = ESTADO_ANDROID)
    private String estado_android;
    @DatabaseField(columnName = FK_TIPO_URGENCIA)
    private int fk_tipo_urgencia;
    @DatabaseField(columnName = FECHA_CIERRE)
    private String fecha_cierre;
    @DatabaseField(columnName = NUM_LOTE)
    private String num_lote;
    @DatabaseField(columnName = BENBATCH)
    private String bEnBatch;
    @DatabaseField(columnName = COD_VISITA)
    private String cod_visita;
    @DatabaseField(columnName = FECHA_ENVIO_CARTA)
    private String fecha_envio_carta;
    @DatabaseField(columnName = BCARTAENVIADA)
    private String bCartaEnviada;
    @DatabaseField(columnName = FECHA_OTRO_DIA)
    private String fecha_otro_dia;
    @DatabaseField(columnName = FECHA_AUSENTE_LIMITE)
    private String fecha_ausente_limite;
    @DatabaseField(columnName = FK_CARGA_ARCHIVO)
    private int fk_carga_archivo;
    @DatabaseField(columnName = ORDEN)
    private String orden;
    @DatabaseField(columnName = HISTORICO)
    private String historico;
    @DatabaseField(columnName = FK_TIPO_URGENCIA_FACTURA)
    private int fk_tipo_urgencia_factura;
    @DatabaseField(columnName = ERROR_BATCH)
    private String error_batch;
    @DatabaseField(columnName = FK_BATCH_ACTUAL)
    private int fk_batch_actual;
    @DatabaseField(columnName = FK_EFV)
    private int fk_efv;
    @DatabaseField(columnName = SCORING)
    private String scoring;
    @DatabaseField(columnName = FK_CATEGORIA_VISITA)
    private int fk_categoria_visita;
    @DatabaseField(columnName = CONTADOR_AVERIAS)
    private String contador_averias;

    public Mantenimiento(){
    }


    public Mantenimiento(int id_mantenimiento, int fk_user_creador, int fk_tecnico, int fk_usuario,
                         int fk_empresa_usuario, String numero_usuario, String nombre_usuario, String dni_usuario,
                         String telefono1_usuario, String telefono2_usuario, String telefono3_usuario,
                         String telefono4_usuario, String telefono5_usuario, String email_usuario,
                         String moroso_usuario, String observaciones_usuario,int fk_direccion,
                         String direccion, String cod_postal, String provincia, String municipio,
                         int fk_maquina, String fecha_creacion, String fecha_aviso,
                         String fecha_visita, String visita_duplicada, String fecha_reparacion,
                         String num_parte, int fk_tipo, int fk_user_asignacion, int fk_horario,
                         String franja_horaria, int fk_franja_ip, int fk_estado, String observaciones,
                         String observacionesAsignacion, String confirmado, String imprimir,
                         String fecha_factura, String num_factura, String fecha_factura_rectificativa,
                         String num_factura_rectificativa, int fk_pend_fact, String num_orden_endesa,
                         String fecha_maxima_endesa, int fk_estado_endesa, String insistencia_endesa,
                         String contrato_endesa, String producto_endesa, int fk_tipo_os,
                         int fk_tipo_producto, String pagado_endesa, String ciclo_liq_endesa,
                         String importe_pago_endesa, String fecha_pagado_endesa, String pagado_operario,
                         String fecha_anulado, String fecha_modificacion_tecnico, int fk_remoto_central,
                         String fac_nombre, String fac_direccion, String fac_cp, String fac_poblacion,
                         String fac_provincia, String fac_DNI, String fac_email, String fac_telefonos,
                         String otros_sintomas, String fecha_baja, String fac_baja_stock, String estado_android,
                         int fk_tipo_urgencia, String fecha_cierre, String num_lote, String bEnBatch,
                         String cod_visita, String fecha_envio_carta, String bCartaEnviada,
                         String fecha_otro_dia, String fecha_ausente_limite, int fk_carga_archivo,
                         String orden, String historico, int fk_tipo_urgencia_factura,
                         String error_batch, int fk_batch_actual, int fk_efv, String scoring,
                         int fk_categoria_visita, String contador_averias) {
        this.id_mantenimiento = id_mantenimiento;
        this.fk_user_creador = fk_user_creador;
        this.fk_tecnico = fk_tecnico;
        this.fk_usuario = fk_usuario;

        this.fk_empresa_usuario = fk_empresa_usuario;
        this.numero_usuario = numero_usuario;
        this.nombre_usuario = nombre_usuario;
        this.dni_usuario = dni_usuario;
        this.telefono1_usuario = telefono1_usuario;
        this.telefono2_usuario = telefono2_usuario;
        this.telefono3_usuario = telefono3_usuario;
        this.telefono4_usuario = telefono4_usuario;
        this.telefono5_usuario = telefono5_usuario;
        this.email_usuario = email_usuario;
        this.moroso_usuario = moroso_usuario;
        this.observaciones_usuario = observaciones_usuario;

        this.fk_direccion = fk_direccion;
        this.direccion = direccion;
        this.cod_postal = cod_postal;
        this.provincia = provincia;
        this.municipio = municipio;
        this.fk_maquina = fk_maquina;
        this.fecha_creacion = fecha_creacion;
        this.fecha_aviso = fecha_aviso;
        this.fecha_visita = fecha_visita;
        this.visita_duplicada = visita_duplicada;
        this.fecha_reparacion = fecha_reparacion;
        this.num_parte = num_parte;
        this.fk_tipo = fk_tipo;
        this.fk_user_asignacion = fk_user_asignacion;
        this.fk_horario = fk_horario;
        this.franja_horaria = franja_horaria;
        this.fk_franja_ip = fk_franja_ip;
        this.fk_estado = fk_estado;
        this.observaciones = observaciones;
        this.observacionesAsignacion = observacionesAsignacion;
        this.confirmado = confirmado;
        this.imprimir = imprimir;
        this.fecha_factura = fecha_factura;
        this.num_factura = num_factura;
        this.fecha_factura_rectificativa = fecha_factura_rectificativa;
        this.num_factura_rectificativa = num_factura_rectificativa;
        this.fk_pend_fact = fk_pend_fact;
        this.num_orden_endesa = num_orden_endesa;
        this.fecha_maxima_endesa = fecha_maxima_endesa;
        this.fk_estado_endesa = fk_estado_endesa;
        this.insistencia_endesa = insistencia_endesa;
        this.contrato_endesa = contrato_endesa;
        this.producto_endesa = producto_endesa;
        this.fk_tipo_os = fk_tipo_os;
        this.fk_tipo_producto = fk_tipo_producto;
        this.pagado_endesa = pagado_endesa;
        this.ciclo_liq_endesa = ciclo_liq_endesa;
        this.importe_pago_endesa = importe_pago_endesa;
        this.fecha_pagado_endesa = fecha_pagado_endesa;
        this.pagado_operario = pagado_operario;
        this.fecha_anulado = fecha_anulado;
        this.fecha_modificacion_tecnico = fecha_modificacion_tecnico;
        this.fk_remoto_central = fk_remoto_central;
        this.fac_nombre = fac_nombre;
        this.fac_direccion = fac_direccion;
        this.fac_cp = fac_cp;
        this.fac_poblacion = fac_poblacion;
        this.fac_provincia = fac_provincia;
        this.fac_DNI = fac_DNI;
        this.fac_email = fac_email;
        this.fac_telefonos = fac_telefonos;
        this.otros_sintomas = otros_sintomas;
        this.fecha_baja = fecha_baja;
        this.fac_baja_stock = fac_baja_stock;
        this.estado_android = estado_android;
        this.fk_tipo_urgencia = fk_tipo_urgencia;
        this.fecha_cierre = fecha_cierre;
        this.num_lote = num_lote;
        this.bEnBatch = bEnBatch;
        this.cod_visita = cod_visita;
        this.fecha_envio_carta = fecha_envio_carta;
        this.bCartaEnviada = bCartaEnviada;
        this.fecha_otro_dia = fecha_otro_dia;
        this.fecha_ausente_limite = fecha_ausente_limite;
        this.fk_carga_archivo = fk_carga_archivo;
        this.orden = orden;
        this.historico = historico;
        this.fk_tipo_urgencia_factura = fk_tipo_urgencia_factura;
        this.error_batch = error_batch;
        this.fk_batch_actual = fk_batch_actual;
        this.fk_efv = fk_efv;
        this.scoring = scoring;
        this.fk_categoria_visita = fk_categoria_visita;
        this.contador_averias = contador_averias;
    }

    public int getId_mantenimiento() {
        return id_mantenimiento;
    }
    public void setId_mantenimiento(int id_mantenimiento) {
        this.id_mantenimiento = id_mantenimiento;
    }
    public int getFk_user_creador() {
        return fk_user_creador;
    }
    public void setFk_user_creador(int fk_user_creador) {
        this.fk_user_creador = fk_user_creador;
    }
    public int getFk_tecnico() {
        return fk_tecnico;
    }
    public void setFk_tecnico(int fk_tecnico) {
        this.fk_tecnico = fk_tecnico;
    }
    public int getFk_usuario() {
        return fk_usuario;
    }
    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }
    public static String getFkUserCreador() {
        return FK_USER_CREADOR;
    }
    public int getFk_empresa_usuario() {
        return fk_empresa_usuario;
    }
    public void setFk_empresa_usuario(int fk_empresa_usuario) {
        this.fk_empresa_usuario = fk_empresa_usuario;
    }
    public String getNumero_usuario() {
        return numero_usuario;
    }
    public void setNumero_usuario(String numero_usuario) {
        this.numero_usuario = numero_usuario;
    }
    public String getNombre_usuario() {
        return nombre_usuario;
    }
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public String getDni_usuario() {
        return dni_usuario;
    }
    public void setDni_usuario(String dni_usuario) {
        this.dni_usuario = dni_usuario;
    }
    public String getTelefono1_usuario() {
        return telefono1_usuario;
    }
    public void setTelefono1_usuario(String telefono1_usuario) {
        this.telefono1_usuario = telefono1_usuario;
    }
    public String getTelefono2_usuario() {
        return telefono2_usuario;
    }
    public void setTelefono2_usuario(String telefono2_usuario) {
        this.telefono2_usuario = telefono2_usuario;
    }
    public String getTelefono3_usuario() {
        return telefono3_usuario;
    }
    public void setTelefono3_usuario(String telefono3_usuario) {
        this.telefono3_usuario = telefono3_usuario;
    }
    public String getTelefono4_usuario() {
        return telefono4_usuario;
    }
    public void setTelefono4_usuario(String telefono4_usuario) {
        this.telefono4_usuario = telefono4_usuario;
    }
    public String getTelefono5_usuario() {
        return telefono5_usuario;
    }
    public void setTelefono5_usuario(String telefono5_usuario) {
        this.telefono5_usuario = telefono5_usuario;
    }
    public String getEmail_usuario() {
        return email_usuario;
    }
    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }
    public String getMoroso_usuario() {
        return moroso_usuario;
    }
    public void setMoroso_usuario(String moroso_usuario) {
        this.moroso_usuario = moroso_usuario;
    }
    public String getObservaciones_usuario() {
        return observaciones_usuario;
    }
    public void setObservaciones_usuario(String observaciones_usuario) {
        this.observaciones_usuario = observaciones_usuario;
    }
    public int getFk_direccion() {
        return fk_direccion;
    }
    public void setFk_direccion(int fk_direccion) {
        this.fk_direccion = fk_direccion;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public static String getIdMantenimiento() {
        return ID_MANTENIMIENTO;
    }
    public String getCod_postal() {
        return cod_postal;
    }
    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getMunicipio() {
        return municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public int getFk_maquina() {
        return fk_maquina;
    }
    public void setFk_maquina(int fk_maquina) {
        this.fk_maquina = fk_maquina;
    }
    public String getFecha_creacion() {
        return fecha_creacion;
    }
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    public String getFecha_aviso() {
        return fecha_aviso;
    }
    public void setFecha_aviso(String fecha_aviso) {
        this.fecha_aviso = fecha_aviso;
    }
    public String getFecha_visita() {
        return fecha_visita;
    }
    public void setFecha_visita(String fecha_visita) {
        this.fecha_visita = fecha_visita;
    }
    public String getVisita_duplicada() {
        return visita_duplicada;
    }
    public void setVisita_duplicada(String visita_duplicada) {
        this.visita_duplicada = visita_duplicada;
    }
    public String getFecha_reparacion() {
        return fecha_reparacion;
    }
    public void setFecha_reparacion(String fecha_reparacion) {
        this.fecha_reparacion = fecha_reparacion;
    }
    public String getNum_parte() {
        return num_parte;
    }
    public void setNum_parte(String num_parte) {
        this.num_parte = num_parte;
    }
    public int getFk_tipo() {
        return fk_tipo;
    }
    public void setFk_tipo(int fk_tipo) {
        this.fk_tipo = fk_tipo;
    }
    public int getFk_user_asignacion() {
        return fk_user_asignacion;
    }
    public void setFk_user_asignacion(int fk_user_asignacion) {
        this.fk_user_asignacion = fk_user_asignacion;
    }
    public int getFk_horario() {
        return fk_horario;
    }
    public void setFk_horario(int fk_horario) {
        this.fk_horario = fk_horario;
    }
    public String getFranja_horaria() {
        return franja_horaria;
    }
    public void setFranja_horaria(String franja_horaria) {
        this.franja_horaria = franja_horaria;
    }
    public int getFk_franja_ip() {
        return fk_franja_ip;
    }
    public void setFk_franja_ip(int fk_franja_ip) {
        this.fk_franja_ip = fk_franja_ip;
    }
    public int getFk_estado() {
        return fk_estado;
    }
    public void setFk_estado(int fk_estado) {
        this.fk_estado = fk_estado;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getObservacionesAsignacion() {
        return observacionesAsignacion;
    }
    public void setObservacionesAsignacion(String observacionesAsignacion) {
        this.observacionesAsignacion = observacionesAsignacion;
    }
    public String getConfirmado() {
        return confirmado;
    }
    public void setConfirmado(String confirmado) {
        this.confirmado = confirmado;
    }
    public String getImprimir() {
        return imprimir;
    }
    public void setImprimir(String imprimir) {
        this.imprimir = imprimir;
    }
    public String getFecha_factura() {
        return fecha_factura;
    }
    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }
    public String getNum_factura() {
        return num_factura;
    }
    public void setNum_factura(String num_factura) {
        this.num_factura = num_factura;
    }
    public String getFecha_factura_rectificativa() {
        return fecha_factura_rectificativa;
    }
    public void setFecha_factura_rectificativa(String fecha_factura_rectificativa) {
        this.fecha_factura_rectificativa = fecha_factura_rectificativa;
    }
    public String getNum_factura_rectificativa() {
        return num_factura_rectificativa;
    }
    public void setNum_factura_rectificativa(String num_factura_rectificativa) {
        this.num_factura_rectificativa = num_factura_rectificativa;
    }
    public int getFk_pend_fact() {
        return fk_pend_fact;
    }
    public void setFk_pend_fact(int fk_pend_fact) {
        this.fk_pend_fact = fk_pend_fact;
    }
    public String getNum_orden_endesa() {
        return num_orden_endesa;
    }
    public void setNum_orden_endesa(String num_orden_endesa) {
        this.num_orden_endesa = num_orden_endesa;
    }
    public String getFecha_maxima_endesa() {
        return fecha_maxima_endesa;
    }
    public void setFecha_maxima_endesa(String fecha_maxima_endesa) {
        this.fecha_maxima_endesa = fecha_maxima_endesa;
    }
    public int getFk_estado_endesa() {
        return fk_estado_endesa;
    }
    public void setFk_estado_endesa(int fk_estado_endesa) {
        this.fk_estado_endesa = fk_estado_endesa;
    }
    public String getInsistencia_endesa() {
        return insistencia_endesa;
    }
    public void setInsistencia_endesa(String insistencia_endesa) {
        this.insistencia_endesa = insistencia_endesa;
    }
    public String getContrato_endesa() {
        return contrato_endesa;
    }
    public void setContrato_endesa(String contrato_endesa) {
        this.contrato_endesa = contrato_endesa;
    }
    public String getProducto_endesa() {
        return producto_endesa;
    }
    public void setProducto_endesa(String producto_endesa) {
        this.producto_endesa = producto_endesa;
    }
    public int getFk_tipo_os() {
        return fk_tipo_os;
    }
    public void setFk_tipo_os(int fk_tipo_os) {
        this.fk_tipo_os = fk_tipo_os;
    }
    public int getFk_tipo_producto() {
        return fk_tipo_producto;
    }
    public void setFk_tipo_producto(int fk_tipo_producto) {
        this.fk_tipo_producto = fk_tipo_producto;
    }
    public String getPagado_endesa() {
        return pagado_endesa;
    }
    public void setPagado_endesa(String pagado_endesa) {
        this.pagado_endesa = pagado_endesa;
    }
    public String getCiclo_liq_endesa() {
        return ciclo_liq_endesa;
    }
    public void setCiclo_liq_endesa(String ciclo_liq_endesa) {
        this.ciclo_liq_endesa = ciclo_liq_endesa;
    }
    public String getImporte_pago_endesa() {
        return importe_pago_endesa;
    }
    public void setImporte_pago_endesa(String importe_pago_endesa) {
        this.importe_pago_endesa = importe_pago_endesa;
    }
    public String getFecha_pagado_endesa() {
        return fecha_pagado_endesa;
    }
    public void setFecha_pagado_endesa(String fecha_pagado_endesa) {
        this.fecha_pagado_endesa = fecha_pagado_endesa;
    }
    public String getPagado_operario() {
        return pagado_operario;
    }
    public void setPagado_operario(String pagado_operario) {
        this.pagado_operario = pagado_operario;
    }
    public String getFecha_anulado() {
        return fecha_anulado;
    }
    public void setFecha_anulado(String fecha_anulado) {
        this.fecha_anulado = fecha_anulado;
    }
    public String getFecha_modificacion_tecnico() {
        return fecha_modificacion_tecnico;
    }
    public void setFecha_modificacion_tecnico(String fecha_modificacion_tecnico) {
        this.fecha_modificacion_tecnico = fecha_modificacion_tecnico;
    }
    public int getFk_remoto_central() {
        return fk_remoto_central;
    }
    public void setFk_remoto_central(int fk_remoto_central) {
        this.fk_remoto_central = fk_remoto_central;
    }
    public String getFac_nombre() {
        return fac_nombre;
    }
    public void setFac_nombre(String fac_nombre) {
        this.fac_nombre = fac_nombre;
    }
    public String getFac_direccion() {
        return fac_direccion;
    }
    public void setFac_direccion(String fac_direccion) {
        this.fac_direccion = fac_direccion;
    }
    public String getFac_cp() {
        return fac_cp;
    }
    public void setFac_cp(String fac_cp) {
        this.fac_cp = fac_cp;
    }
    public String getFac_poblacion() {
        return fac_poblacion;
    }
    public void setFac_poblacion(String fac_poblacion) {
        this.fac_poblacion = fac_poblacion;
    }
    public String getFac_provincia() {
        return fac_provincia;
    }
    public void setFac_provincia(String fac_provincia) {
        this.fac_provincia = fac_provincia;
    }
    public String getFac_DNI() {
        return fac_DNI;
    }
    public void setFac_DNI(String fac_DNI) {
        this.fac_DNI = fac_DNI;
    }
    public String getFac_email() {
        return fac_email;
    }
    public void setFac_email(String fac_email) {
        this.fac_email = fac_email;
    }
    public String getFac_telefonos() {
        return fac_telefonos;
    }
    public void setFac_telefonos(String fac_telefonos) {
        this.fac_telefonos = fac_telefonos;
    }
    public String getOtros_sintomas() {
        return otros_sintomas;
    }
    public void setOtros_sintomas(String otros_sintomas) {
        this.otros_sintomas = otros_sintomas;
    }
    public String getFecha_baja() {
        return fecha_baja;
    }
    public void setFecha_baja(String fecha_baja) {
        this.fecha_baja = fecha_baja;
    }
    public String getFac_baja_stock() {
        return fac_baja_stock;
    }
    public void setFac_baja_stock(String fac_baja_stock) {
        this.fac_baja_stock = fac_baja_stock;
    }
    public String getEstado_android() {
        return estado_android;
    }
    public void setEstado_android(String estado_android) {
        this.estado_android = estado_android;
    }
    public int getFk_tipo_urgencia() {
        return fk_tipo_urgencia;
    }
    public void setFk_tipo_urgencia(int fk_tipo_urgencia) {
        this.fk_tipo_urgencia = fk_tipo_urgencia;
    }
    public String getFecha_cierre() {
        return fecha_cierre;
    }
    public void setFecha_cierre(String fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }
    public String getNum_lote() {
        return num_lote;
    }
    public void setNum_lote(String num_lote) {
        this.num_lote = num_lote;
    }
    public String getbEnBatch() {
        return bEnBatch;
    }
    public void setbEnBatch(String bEnBatch) {
        this.bEnBatch = bEnBatch;
    }
    public String getCod_visita() {
        return cod_visita;
    }
    public void setCod_visita(String cod_visita) {
        this.cod_visita = cod_visita;
    }
    public String getFecha_envio_carta() {
        return fecha_envio_carta;
    }
    public void setFecha_envio_carta(String fecha_envio_carta) {
        this.fecha_envio_carta = fecha_envio_carta;
    }
    public String getbCartaEnviada() {
        return bCartaEnviada;
    }
    public void setbCartaEnviada(String bCartaEnviada) {
        this.bCartaEnviada = bCartaEnviada;
    }
    public String getFecha_otro_dia() {
        return fecha_otro_dia;
    }
    public void setFecha_otro_dia(String fecha_otro_dia) {
        this.fecha_otro_dia = fecha_otro_dia;
    }
    public String getFecha_ausente_limite() {
        return fecha_ausente_limite;
    }
    public void setFecha_ausente_limite(String fecha_ausente_limite) {
        this.fecha_ausente_limite = fecha_ausente_limite;
    }
    public int getFk_carga_archivo() {
        return fk_carga_archivo;
    }
    public void setFk_carga_archivo(int fk_carga_archivo) {
        this.fk_carga_archivo = fk_carga_archivo;
    }
    public String getOrden() {
        return orden;
    }
    public void setOrden(String orden) {
        this.orden = orden;
    }
    public String getHistorico() {
        return historico;
    }
    public void setHistorico(String historico) {
        this.historico = historico;
    }
    public int getFk_tipo_urgencia_factura() {
        return fk_tipo_urgencia_factura;
    }
    public void setFk_tipo_urgencia_factura(int fk_tipo_urgencia_factura) {
        this.fk_tipo_urgencia_factura = fk_tipo_urgencia_factura;
    }
    public String getError_batch() {
        return error_batch;
    }
    public void setError_batch(String error_batch) {
        this.error_batch = error_batch;
    }
    public int getFk_batch_actual() {
        return fk_batch_actual;
    }
    public void setFk_batch_actual(int fk_batch_actual) {
        this.fk_batch_actual = fk_batch_actual;
    }
    public int getFk_efv() {
        return fk_efv;
    }
    public void setFk_efv(int fk_efv) {
        this.fk_efv = fk_efv;
    }
    public String getScoring() {
        return scoring;
    }
    public void setScoring(String scoring) {
        this.scoring = scoring;
    }
    public int getFk_categoria_visita() {
        return fk_categoria_visita;
    }
    public void setFk_categoria_visita(int fk_categoria_visita) {
        this.fk_categoria_visita = fk_categoria_visita;
    }
    public String getContador_averias() {
        return contador_averias;
    }
    public void setContador_averias(String contador_averias) {
        this.contador_averias = contador_averias;
    }
}
