package com.multimedia.aes.gestnet_nucleo.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_partes")
public class Parte {


    public static final String ID_PARTE="_id_parte";
    public static final String FK_USER_CREADOR="fk_user_creador";
    public static final String FK_COMPANIA="fk_compaia";
    public static final String FK_TECNICO="fk_tecnico";
    public static final String FK_USUARIO="fk_usuario";
    public static final String FK_DIRECCION="fk_direccion";
    public static final String FK_MAQUINA="fk_maquina";
    public static final String FECHA_CREACION="fecha_creacion";
    public static final String FECHA_AVISO="fecha_aviso";
    public static final String FECHA_VISITA="fecha_visita";
    public static final String VISITA_DUPLICADA="visita_duplicada";
    public static final String FECHA_REPARACION="fecha_reparacion";
    public static final String NUM_PARTE="num_parte";
    public static final String FK_TIPO="fk_tipo";
    public static final String FK_USER_ASIGNACION="fk_user_asignacion";
    public static final String FK_HORARIO="fk_horario";
    public static final String HORARIO="horario";
    public static final String DURACION="duracion";
    public static final String FIRMANTE="firmante";
    public static final String SOBRE="sobre";
    public static final String FRANJA_HORARIA="franja_horaria";
    public static final String FK_ESTADO="fk_estado";
    public static final String FK_ESTADO_INTERNO="fk_estado_interno";
    public static final String OBSERVACIONES="observaciones";
    public static final String OBSERVACIONESASIGNACION="observacionesasignacion";
    public static final String CONFIRMADO="confirmado";
    public static final String ENTREGADO_POR="entregado_por";
    public static final String RECOGIDO_POR="recogido_por";
    public static final String COMENTARIOS_ENTREGA="comentarios_entrega";
    public static final String FK_FABRICANTE="fk_fabricante";
    public static final String APROBADO_FABRICANTE="aprobado_fabricante";
    public static final String IMPRIMIR="imprimir";
    public static final String FECHA_FACTURA="fecha_factura";
    public static final String NUM_FACTURA="num_factura";
    public static final String FECHA_FACTURA_RECTIFICATIVA="fecha_factura_rectificativa";
    public static final String NUM_FACTURA_RECTIFICATIVA="num_factura_rectificativa";
    public static final String FK_PEND_FACT="fk_pend_fact";
    public static final String NUM_ORDEN_ENDESA="num_orden_endesa";
    public static final String FECHA_MAXIMA_ENDESA="fecha_maxima_endesa";
    public static final String FK_ESTADO_ENDESA="fk_estado_endesa";
    public static final String INSISTENCIA_ENDESA="insistencia_endesa";
    public static final String CONTRATO_ENDESA="contrato_endesa";
    public static final String PRODUCTO_ENDESA="producto_endesa";
    public static final String FK_TIPO_OS0="fk_tipo_os0";
    public static final String FK_TIPO_PRODUCTO="fk_tipo_producto";
    public static final String PAGADO_ENDESA="pagado_endesa";
    public static final String CICLO_LIQ_ENDESA="ciclo_lio_endesa";
    public static final String IMPORTE_PAGO_ENDESA="importe_pago_endesa";
    public static final String FECHA_PAGADO_ENDESA="fecha_pagado_endesa";
    public static final String PAGADO_OPERARIO="pagado_operario";
    public static final String FECHA_ANULADO="fecha_anulado";
    public static final String FECHA_MODIFICACION_TECNICO="fecha_modificacion_tecnico";
    public static final String FK_REMOTO_CENTRAL="fk_remoto_central";
    public static final String FAC_NOMBRE="fac_nombre";
    public static final String FAC_DIRECCION="fac_direccion";
    public static final String FAC_CP="fac_cp";
    public static final String FAC_POBLACION="fac_poblacion";
    public static final String FAC_PROVINCIA="fac_provincia";
    public static final String FAC_DNI="fac_dni";
    public static final String FAC_EMAIL="fac_email";
    public static final String FAC_TELEFONOS="fac_telefono";
    public static final String OTROS_SINTOMAS="otros_sintomas";
    public static final String FECHA_BAJA="fecha_baja";
    public static final String FAC_BAJA_STOCK="fac_baja_stock";
    public static final String ESTADO_ANDROID="estado_android";
    public static final String URGENCIAS="urgencias";
    public static final String LOTE="lote";
    public static final String VALIDAR="validar";
    public static final String LIQUIDADO_A_PROVEEDOR="liquidado_a_provedor";
    public static final String FK_INSTALACION="fk_instalacion";
    public static final String FK_EMERGENCIA="fk_emergencia";
    public static final String MOTIVO_CAMBIO_FECHA_MAXIMA="motivo_cambio_fecha_maximo";
    public static final String BTODOSLOSEQUIPOS="btodoslosequipos";
    public static final String FK_TIPO_INSTALACION="fk_tipo_instalacion";
    public static final String PARTE_FINALIZADO_ANDROID="parte_finalizado_android";
    public static final String COMERCIALIZADORA="comercializadora";
    public static final String PERSONA_CONTACTO="persona_contacto";
    public static final String TEL_CONTACTO="tel_contacto";
    public static final String CNAE="cnae";
    public static final String FK_COMPANIA_PARTE="fk_compania_parte";
    public static final String FECHA_CIERRE="fecha_cierre";
    public static final String NUM_PRESUPUESTO="num_presupuesto";
    public static final String DEFECTOS="defectos";
    public static final String FK_PERIOCIDAD="fk_periodicidad";
    public static final String FRANQUICIA="franquicia";
    public static final String INSPECCION_VISUAL="inspeccion_visual";
    public static final String OTROS_MATAUX="otros_mataux";
    public static final String BINSPECCIONVISUAL="binspeccionvisual";
    public static final String BOTROSMATAUX="botrosmataux";
    public static final String TIPO_VIA="tipo_via";
    public static final String VIA="via";
    public static final String NUMERO_DIRECCION="numero_direccion";
    public static final String ESCALERA_DIRECCION="escalera_direccion";
    public static final String PISO_DIRECCION="piso_direccion";
    public static final String PUERTA_DIRECCION="puerta_direccion";
    public static final String CP_DIRECCION="cp_direccion";
    public static final String MUNICIPIO_DIRECCION="municipio_direccion";
    public static final String PROVINCIA_DIRECCION="provincia_direccion";
    public static final String LATITUD_DIRECCION="latitud_direccion";
    public static final String LONGITUD_DIRECCION="longitud_direccion";
    public static final String NOMBRE_CLIENTE="nombre_cliente";
    public static final String DNI_CLIENTE="dni_cliente";
    public static final String TELEFONO1_CLIENTE="telefono1_cliente";
    public static final String TELEFONO2_CLIENTE="telefono2_cliente";
    public static final String TELEFONO3_CLIENTE="telefono3_cliente";
    public static final String TELEFONO4_CLIENTE="telefono4_cliente";
    public static final String EMAIL_CLIENTE="email_cliente";
    public static final String OBSERVACIONES_CLIENTE="observaciones_cliente";
    public static final  String USER_CREADOR = "user_creador";
    public static final  String TIPO = "tipo";


    @DatabaseField(id = true, columnName = ID_PARTE)    private int id_parte;
    //DATOS DEL PARTE
    @DatabaseField(columnName = FK_USER_CREADOR)                private int fk_user_creador;
    @DatabaseField(columnName = FK_COMPANIA)                    private int fk_compania;
    @DatabaseField(columnName = FK_TECNICO)                     private int fk_tecnico;
    @DatabaseField(columnName = FK_USUARIO)                     private int fk_usuario;
    @DatabaseField(columnName = FK_DIRECCION)                   private int fk_direccion;
    @DatabaseField(columnName = FK_MAQUINA)                     private int fk_maquina;
    @DatabaseField(columnName = FECHA_CREACION)                 private String fecha_creacion;
    @DatabaseField(columnName = FECHA_AVISO)                    private String fecha_aviso;
    @DatabaseField(columnName = FECHA_VISITA)                   private String fecha_visita;
    @DatabaseField(columnName = VISITA_DUPLICADA)               private boolean visita_duplicada;
    @DatabaseField(columnName = FECHA_REPARACION)               private String fecha_reparacion;
    @DatabaseField(columnName = NUM_PARTE)                      private int num_parte;
    @DatabaseField(columnName = FK_TIPO)                        private int fk_tipo;
    @DatabaseField(columnName = FK_USER_ASIGNACION)             private int fk_user_asignacion;
    @DatabaseField(columnName = FK_HORARIO)                     private int fk_horario;
    @DatabaseField(columnName = HORARIO)                        private String horario;
    @DatabaseField(columnName = DURACION)                       private String duracion;
    @DatabaseField(columnName = FIRMANTE)                       private String firmante;
    @DatabaseField(columnName = SOBRE)                          private String sobre;
    @DatabaseField(columnName = FRANJA_HORARIA)                 private int franja_horaria;
    @DatabaseField(columnName = FK_ESTADO)                      private int fk_estado;
    @DatabaseField(columnName = FK_ESTADO_INTERNO)              private int fk_estado_interno;
    @DatabaseField(columnName = OBSERVACIONES)                  private String observaciones;
    @DatabaseField(columnName = OBSERVACIONESASIGNACION)        private String observacionesasignacion;
    @DatabaseField(columnName = CONFIRMADO)                     private int confirmado;
    @DatabaseField(columnName = ENTREGADO_POR)                  private String entregado_por;
    @DatabaseField(columnName = RECOGIDO_POR)                   private String recogido_por;
    @DatabaseField(columnName = COMENTARIOS_ENTREGA)            private String comentarios_entrega;
    @DatabaseField(columnName = FK_FABRICANTE)                  private int fk_fabricante;
    @DatabaseField(columnName = APROBADO_FABRICANTE)            private String aprobado_fabricante;
    @DatabaseField(columnName = IMPRIMIR)                       private boolean imprimir;
    @DatabaseField(columnName = FECHA_FACTURA)                  private String fecha_factura;
    @DatabaseField(columnName = NUM_FACTURA)                    private String num_factura;
    @DatabaseField(columnName = FECHA_FACTURA_RECTIFICATIVA)    private String fecha_factura_rectificativa;
    @DatabaseField(columnName = NUM_FACTURA_RECTIFICATIVA)      private String num_factura_rectificativa;
    @DatabaseField(columnName = FK_PEND_FACT)                   private int fk_pend_fact;
    @DatabaseField(columnName = NUM_ORDEN_ENDESA)               private String num_orden_endesa;
    @DatabaseField(columnName = FECHA_MAXIMA_ENDESA)            private String fecha_maxima_endesa;
    @DatabaseField(columnName = FK_ESTADO_ENDESA)               private int fk_estado_endesa;
    @DatabaseField(columnName = INSISTENCIA_ENDESA)             private int insistencia_endesa;
    @DatabaseField(columnName = CONTRATO_ENDESA)                private String contrato_endesa;
    @DatabaseField(columnName = PRODUCTO_ENDESA)                private String producto_endesa;
    @DatabaseField(columnName = FK_TIPO_OS0)                    private int  fk_tipo_os0;
    @DatabaseField(columnName = FK_TIPO_PRODUCTO)               private int fk_tipo_producto;
    @DatabaseField(columnName = PAGADO_ENDESA)                  private boolean pagado_endesa;
    @DatabaseField(columnName = CICLO_LIQ_ENDESA)               private String ciclo_liq_endesa;
    @DatabaseField(columnName = IMPORTE_PAGO_ENDESA)            private double importe_pago_endesa;
    @DatabaseField(columnName = FECHA_PAGADO_ENDESA)            private String fecha_pagado_endesa;
    @DatabaseField(columnName = PAGADO_OPERARIO)                private boolean pagado_operario;
    @DatabaseField(columnName = FECHA_ANULADO)                  private String fecha_anulado;
    @DatabaseField(columnName = FECHA_MODIFICACION_TECNICO)     private String fecha_modificacion_tecnico;
    @DatabaseField(columnName = FK_REMOTO_CENTRAL)              private int fk_remoto_central;
    @DatabaseField(columnName = FAC_NOMBRE)                     private String fac_nombre;
    @DatabaseField(columnName = FAC_DIRECCION)                  private String fac_direccion;
    @DatabaseField(columnName = FAC_CP)                         private String fac_cp;
    @DatabaseField(columnName = FAC_POBLACION)                  private String fac_poblacion;
    @DatabaseField(columnName = FAC_PROVINCIA)                  private String fac_provincia;
    @DatabaseField(columnName = FAC_DNI)                        private String fac_dni;
    @DatabaseField(columnName = FAC_EMAIL)                      private String fac_email;
    @DatabaseField(columnName = FAC_TELEFONOS)                  private String fac_telefonos;
    @DatabaseField(columnName = OTROS_SINTOMAS)                 private String otros_sintomas;
    @DatabaseField(columnName = FECHA_BAJA)                     private String fecha_baja;
    @DatabaseField(columnName = FAC_BAJA_STOCK)                 private boolean fac_baja_stock;
    @DatabaseField(columnName = ESTADO_ANDROID)                 private boolean estado_android;
    @DatabaseField(columnName = URGENCIAS)                      private boolean urgencias;
    @DatabaseField(columnName = LOTE)                           private String lote;
    @DatabaseField(columnName = VALIDAR)                        private boolean validar;
    @DatabaseField(columnName = LIQUIDADO_A_PROVEEDOR)          private boolean liquidado_a_proveedor;
    @DatabaseField(columnName = FK_INSTALACION)                 private int fk_instalacion;
    @DatabaseField(columnName = FK_EMERGENCIA)                  private int fk_emergencia;
    @DatabaseField(columnName = MOTIVO_CAMBIO_FECHA_MAXIMA)     private String motivo_cambio_fecha_maxima;
    @DatabaseField(columnName = BTODOSLOSEQUIPOS)               private boolean btodoslosequipos;
    @DatabaseField(columnName = FK_TIPO_INSTALACION)            private int fk_tipo_instalacion;
    @DatabaseField(columnName = PARTE_FINALIZADO_ANDROID)       private boolean parte_finalizado_android;
    @DatabaseField(columnName = COMERCIALIZADORA)               private String comercializadora;
    @DatabaseField(columnName = PERSONA_CONTACTO)               private String persona_contacto;
    @DatabaseField(columnName = TEL_CONTACTO)                   private String tel_contacto;
    @DatabaseField(columnName = CNAE)                           private String cnae;
    @DatabaseField(columnName = FK_COMPANIA_PARTE)              private int fk_compania_parte;
    @DatabaseField(columnName = FECHA_CIERRE)                   private String fecha_cierre;
    @DatabaseField(columnName = NUM_PRESUPUESTO)                private String num_presupuesto;
    @DatabaseField(columnName = DEFECTOS)                       private String defectos;
    @DatabaseField(columnName = FK_PERIOCIDAD)                  private int fk_periocidad;
    @DatabaseField(columnName = FRANQUICIA)                     private double franquicia;
    @DatabaseField(columnName = INSPECCION_VISUAL)              private String inspeccion_visual;
    @DatabaseField(columnName = OTROS_MATAUX)                   private String otros_mataux;
    @DatabaseField(columnName = BINSPECCIONVISUAL)              private boolean binspeccionvisual;
    @DatabaseField(columnName = BOTROSMATAUX)                   private boolean botrosmataux;
    @DatabaseField(columnName = USER_CREADOR)                   private String user_creador;
    @DatabaseField(columnName = TIPO)                            private String tipo;
    //DATOS DE LA DIRECCION
    @DatabaseField(columnName = TIPO_VIA)                       private String tipo_via;
    @DatabaseField(columnName = VIA)                            private String via;
    @DatabaseField(columnName = NUMERO_DIRECCION)               private String numero_direccion;
    @DatabaseField(columnName = ESCALERA_DIRECCION)             private String escalera_direccion;
    @DatabaseField(columnName = PISO_DIRECCION)                 private String piso_direccion;
    @DatabaseField(columnName = PUERTA_DIRECCION)               private String puerta_direccion;
    @DatabaseField(columnName = CP_DIRECCION)                   private String cp_direccion;
    @DatabaseField(columnName = MUNICIPIO_DIRECCION)            private String municipio_direccion;
    @DatabaseField(columnName = PROVINCIA_DIRECCION)            private String provincia_direccion;
    @DatabaseField(columnName = LATITUD_DIRECCION)              private String latitud_direccion;
    @DatabaseField(columnName = LONGITUD_DIRECCION)             private String longitud_direccion;
    //DATOS DEL CLIENTE
    @DatabaseField(columnName = NOMBRE_CLIENTE)                 private String nombre_cliente;
    @DatabaseField(columnName = DNI_CLIENTE)                    private String dni_cliente;
    @DatabaseField(columnName = TELEFONO1_CLIENTE)              private String telefono1_cliente;
    @DatabaseField(columnName = TELEFONO2_CLIENTE)              private String telefono2_cliente;
    @DatabaseField(columnName = TELEFONO3_CLIENTE)              private String telefono3_cliente;
    @DatabaseField(columnName = TELEFONO4_CLIENTE)              private String telefono4_cliente;
    @DatabaseField(columnName = EMAIL_CLIENTE)                  private String email_cliente;
    @DatabaseField(columnName = OBSERVACIONES_CLIENTE)          private String observaciones_cliente;



    public Parte(){}

    public Parte(int id_parte, int fk_user_creador, int fk_compania, int fk_tecnico, int fk_usuario,
                 int fk_direccion, int fk_maquina, String fecha_creacion, String fecha_aviso,
                 String fecha_visita, boolean visita_duplicada, String fecha_reparacion, int num_parte,
                 int fk_tipo, int fk_user_asignacion, int fk_horario, String horario, String duracion,
                 String firmante, String sobre, int franja_horaria, int fk_estado, int fk_estado_interno,
                 String observaciones, String observacionesasignacion, int confirmado, String entregado_por,
                 String recogido_por, String comentarios_entrega, int fk_fabricante, String aprobado_fabricante,
                 boolean imprimir, String fecha_factura, String num_factura, String fecha_factura_rectificativa,
                 String num_factura_rectificativa, int fk_pend_fact, String num_orden_endesa,
                 String fecha_maxima_endesa, int fk_estado_endesa, int insistencia_endesa,
                 String contrato_endesa, String producto_endesa, int fk_tipo_os0, int fk_tipo_producto,
                 boolean pagado_endesa, String ciclo_liq_endesa, double importe_pago_endesa,
                 String fecha_pagado_endesa, boolean pagado_operario, String fecha_anulado,
                 String fecha_modificacion_tecnico, int fk_remoto_central, String fac_nombre,
                 String fac_direccion, String fac_cp, String fac_poblacion, String fac_provincia,
                 String fac_dni, String fac_email, String fac_telefonos, String otros_sintomas,
                 String fecha_baja, boolean fac_baja_stock, boolean estado_android, boolean urgencias,
                 String lote, boolean validar, boolean liquidado_a_proveedor, int fk_instalacion,
                 int fk_emergencia, String motivo_cambio_fecha_maxima, boolean btodoslosequipos,
                 int fk_tipo_instalacion, boolean parte_finalizado_android, String comercializadora,
                 String persona_contacto, String tel_contacto, String cnae, int fk_compania_parte,
                 String fecha_cierre, String num_presupuesto, String defectos, int fk_periocidad,
                 double franquicia, String inspeccion_visual, String otros_mataux, boolean binspeccionvisual,
                 boolean botrosmataux, String tipo_via, String via, String numero_direccion,
                 String escalera_direccion, String piso_direccion, String puerta_direccion,
                 String cp_direccion, String municipio_direccion, String provincia_direccion,
                 String latitud_direccion, String longitud_direccion, String nombre_cliente,
                 String dni_cliente, String telefono1_cliente, String telefono2_cliente,
                 String telefono3_cliente, String telefono4_cliente, String email_cliente,
                 String observaciones_cliente,String user_creador,String tipo) {
        this.id_parte = id_parte;
        this.fk_user_creador = fk_user_creador;
        this.fk_compania = fk_compania;
        this.fk_tecnico = fk_tecnico;
        this.fk_usuario = fk_usuario;
        this.fk_direccion = fk_direccion;
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
        this.horario = horario;
        this.duracion = duracion;
        this.firmante = firmante;
        this.sobre = sobre;
        this.franja_horaria = franja_horaria;
        this.fk_estado = fk_estado;
        this.fk_estado_interno = fk_estado_interno;
        this.observaciones = observaciones;
        this.observacionesasignacion = observacionesasignacion;
        this.confirmado = confirmado;
        this.entregado_por = entregado_por;
        this.recogido_por = recogido_por;
        this.comentarios_entrega = comentarios_entrega;
        this.fk_fabricante = fk_fabricante;
        this.aprobado_fabricante = aprobado_fabricante;
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
        this.fk_tipo_os0 = fk_tipo_os0;
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
        this.fac_dni = fac_dni;
        this.fac_email = fac_email;
        this.fac_telefonos = fac_telefonos;
        this.otros_sintomas = otros_sintomas;
        this.fecha_baja = fecha_baja;
        this.fac_baja_stock = fac_baja_stock;
        this.estado_android = estado_android;
        this.urgencias = urgencias;
        this.lote = lote;
        this.validar = validar;
        this.liquidado_a_proveedor = liquidado_a_proveedor;
        this.fk_instalacion = fk_instalacion;
        this.fk_emergencia = fk_emergencia;
        this.motivo_cambio_fecha_maxima = motivo_cambio_fecha_maxima;
        this.btodoslosequipos = btodoslosequipos;
        this.fk_tipo_instalacion = fk_tipo_instalacion;
        this.parte_finalizado_android = parte_finalizado_android;
        this.comercializadora = comercializadora;
        this.persona_contacto = persona_contacto;
        this.tel_contacto = tel_contacto;
        this.cnae = cnae;
        this.fk_compania_parte = fk_compania_parte;
        this.fecha_cierre = fecha_cierre;
        this.num_presupuesto = num_presupuesto;
        this.defectos = defectos;
        this.fk_periocidad = fk_periocidad;
        this.franquicia = franquicia;
        this.inspeccion_visual = inspeccion_visual;
        this.otros_mataux = otros_mataux;
        this.binspeccionvisual = binspeccionvisual;
        this.botrosmataux = botrosmataux;
        this.tipo_via = tipo_via;
        this.via = via;
        this.numero_direccion = numero_direccion;
        this.escalera_direccion = escalera_direccion;
        this.piso_direccion = piso_direccion;
        this.puerta_direccion = puerta_direccion;
        this.cp_direccion = cp_direccion;
        this.municipio_direccion = municipio_direccion;
        this.provincia_direccion = provincia_direccion;
        this.latitud_direccion = latitud_direccion;
        this.longitud_direccion = longitud_direccion;
        this.nombre_cliente = nombre_cliente;
        this.dni_cliente = dni_cliente;
        this.telefono1_cliente = telefono1_cliente;
        this.telefono2_cliente = telefono2_cliente;
        this.telefono3_cliente = telefono3_cliente;
        this.telefono4_cliente = telefono4_cliente;
        this.email_cliente = email_cliente;
        this.observaciones_cliente = observaciones_cliente;
        this.user_creador = user_creador;
        this.tipo=tipo;
    }

    public int getId_parte() {
        return id_parte;
    }
    public void setId_parte(int id_parte) {
        this.id_parte = id_parte;
    }
    public int getFk_user_creador() {
        return fk_user_creador;
    }
    public void setFk_user_creador(int fk_user_creador) {
        this.fk_user_creador = fk_user_creador;
    }
    public int getFk_compania() {
        return fk_compania;
    }
    public void setFk_compania(int fk_compania) {
        this.fk_compania = fk_compania;
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
    public int getFk_direccion() {
        return fk_direccion;
    }
    public void setFk_direccion(int fk_direccion) {
        this.fk_direccion = fk_direccion;
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
    public boolean isVisita_duplicada() {
        return visita_duplicada;
    }
    public void setVisita_duplicada(boolean visita_duplicada) {
        this.visita_duplicada = visita_duplicada;
    }
    public String getFecha_reparacion() {
        return fecha_reparacion;
    }
    public void setFecha_reparacion(String fecha_reparacion) {
        this.fecha_reparacion = fecha_reparacion;
    }
    public int getNum_parte() {
        return num_parte;
    }
    public void setNum_parte(int num_parte) {
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
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public String getDuracion() {
        return duracion;
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    public String getFirmante() {
        return firmante;
    }
    public void setFirmante(String firmante) {
        this.firmante = firmante;
    }
    public String getSobre() {
        return sobre;
    }
    public void setSobre(String sobre) {
        this.sobre = sobre;
    }
    public int getFranja_horaria() {
        return franja_horaria;
    }
    public void setFranja_horaria(int franja_horaria) {
        this.franja_horaria = franja_horaria;
    }
    public int getFk_estado() {
        return fk_estado;
    }
    public void setFk_estado(int fk_estado) {
        this.fk_estado = fk_estado;
    }
    public int getFk_estado_interno() {
        return fk_estado_interno;
    }
    public void setFk_estado_interno(int fk_estado_interno) {
        this.fk_estado_interno = fk_estado_interno;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getObservacionesasignacion() {
        return observacionesasignacion;
    }
    public void setObservacionesasignacion(String observacionesasignacion) {
        this.observacionesasignacion = observacionesasignacion;
    }
    public int getConfirmado() {
        return confirmado;
    }
    public void setConfirmado(int confirmado) {
        this.confirmado = confirmado;
    }
    public String getEntregado_por() {
        return entregado_por;
    }
    public void setEntregado_por(String entregado_por) {
        this.entregado_por = entregado_por;
    }
    public String getRecogido_por() {
        return recogido_por;
    }
    public void setRecogido_por(String recogido_por) {
        this.recogido_por = recogido_por;
    }
    public String getComentarios_entrega() {
        return comentarios_entrega;
    }
    public void setComentarios_entrega(String comentarios_entrega) {
        this.comentarios_entrega = comentarios_entrega;
    }
    public int getFk_fabricante() {
        return fk_fabricante;
    }
    public void setFk_fabricante(int fk_fabricante) {
        this.fk_fabricante = fk_fabricante;
    }
    public String getAprobado_fabricante() {
        return aprobado_fabricante;
    }
    public void setAprobado_fabricante(String aprobado_fabricante) {
        this.aprobado_fabricante = aprobado_fabricante;
    }
    public boolean isImprimir() {
        return imprimir;
    }
    public void setImprimir(boolean imprimir) {
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
    public int getInsistencia_endesa() {
        return insistencia_endesa;
    }
    public void setInsistencia_endesa(int insistencia_endesa) {
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
    public int getFk_tipo_os0() {
        return fk_tipo_os0;
    }
    public void setFk_tipo_os0(int fk_tipo_os0) {
        this.fk_tipo_os0 = fk_tipo_os0;
    }
    public int getFk_tipo_producto() {
        return fk_tipo_producto;
    }
    public void setFk_tipo_producto(int fk_tipo_producto) {
        this.fk_tipo_producto = fk_tipo_producto;
    }
    public boolean isPagado_endesa() {
        return pagado_endesa;
    }
    public void setPagado_endesa(boolean pagado_endesa) {
        this.pagado_endesa = pagado_endesa;
    }
    public String getCiclo_liq_endesa() {
        return ciclo_liq_endesa;
    }
    public void setCiclo_liq_endesa(String ciclo_liq_endesa) {
        this.ciclo_liq_endesa = ciclo_liq_endesa;
    }
    public double getImporte_pago_endesa() {
        return importe_pago_endesa;
    }
    public void setImporte_pago_endesa(double importe_pago_endesa) {
        this.importe_pago_endesa = importe_pago_endesa;
    }
    public String getFecha_pagado_endesa() {
        return fecha_pagado_endesa;
    }
    public void setFecha_pagado_endesa(String fecha_pagado_endesa) {
        this.fecha_pagado_endesa = fecha_pagado_endesa;
    }
    public boolean isPagado_operario() {
        return pagado_operario;
    }
    public void setPagado_operario(boolean pagado_operario) {
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
    public String getFac_dni() {
        return fac_dni;
    }
    public void setFac_dni(String fac_dni) {
        this.fac_dni = fac_dni;
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
    public boolean isFac_baja_stock() {
        return fac_baja_stock;
    }
    public void setFac_baja_stock(boolean fac_baja_stock) {
        this.fac_baja_stock = fac_baja_stock;
    }
    public boolean isEstado_android() {
        return estado_android;
    }
    public void setEstado_android(boolean estado_android) {
        this.estado_android = estado_android;
    }
    public boolean isUrgencias() {
        return urgencias;
    }
    public void setUrgencias(boolean urgencias) {
        this.urgencias = urgencias;
    }
    public String getLote() {
        return lote;
    }
    public void setLote(String lote) {
        this.lote = lote;
    }
    public boolean isValidar() {
        return validar;
    }
    public void setValidar(boolean validar) {
        this.validar = validar;
    }
    public boolean isLiquidado_a_proveedor() {
        return liquidado_a_proveedor;
    }
    public void setLiquidado_a_proveedor(boolean liquidado_a_proveedor) {
        this.liquidado_a_proveedor = liquidado_a_proveedor;
    }
    public int getFk_instalacion() {
        return fk_instalacion;
    }
    public void setFk_instalacion(int fk_instalacion) {
        this.fk_instalacion = fk_instalacion;
    }
    public int getFk_emergencia() {
        return fk_emergencia;
    }
    public void setFk_emergencia(int fk_emergencia) {
        this.fk_emergencia = fk_emergencia;
    }
    public String getMotivo_cambio_fecha_maxima() {
        return motivo_cambio_fecha_maxima;
    }
    public void setMotivo_cambio_fecha_maxima(String motivo_cambio_fecha_maxima) {
        this.motivo_cambio_fecha_maxima = motivo_cambio_fecha_maxima;
    }
    public boolean isBtodoslosequipos() {
        return btodoslosequipos;
    }
    public void setBtodoslosequipos(boolean btodoslosequipos) {
        this.btodoslosequipos = btodoslosequipos;
    }
    public int getFk_tipo_instalacion() {
        return fk_tipo_instalacion;
    }
    public void setFk_tipo_instalacion(int fk_tipo_instalacion) {
        this.fk_tipo_instalacion = fk_tipo_instalacion;
    }
    public boolean isParte_finalizado_android() {
        return parte_finalizado_android;
    }
    public void setParte_finalizado_android(boolean parte_finalizado_android) {
        this.parte_finalizado_android = parte_finalizado_android;
    }
    public String getComercializadora() {
        return comercializadora;
    }
    public void setComercializadora(String comercializadora) {
        this.comercializadora = comercializadora;
    }
    public String getPersona_contacto() {
        return persona_contacto;
    }
    public void setPersona_contacto(String persona_contacto) {
        this.persona_contacto = persona_contacto;
    }
    public String getTel_contacto() {
        return tel_contacto;
    }
    public void setTel_contacto(String tel_contacto) {
        this.tel_contacto = tel_contacto;
    }
    public String getCnae() {
        return cnae;
    }
    public void setCnae(String cnae) {
        this.cnae = cnae;
    }
    public int getFk_compania_parte() {
        return fk_compania_parte;
    }
    public void setFk_compania_parte(int fk_compania_parte) {
        this.fk_compania_parte = fk_compania_parte;
    }
    public String getFecha_cierre() {
        return fecha_cierre;
    }
    public void setFecha_cierre(String fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }
    public String getNum_presupuesto() {
        return num_presupuesto;
    }
    public void setNum_presupuesto(String num_presupuesto) {
        this.num_presupuesto = num_presupuesto;
    }
    public String getDefectos() {
        return defectos;
    }
    public void setDefectos(String defectos) {
        this.defectos = defectos;
    }
    public int getFk_periocidad() {
        return fk_periocidad;
    }
    public void setFk_periocidad(int fk_periocidad) {
        this.fk_periocidad = fk_periocidad;
    }
    public double getFranquicia() {
        return franquicia;
    }
    public void setFranquicia(double franquicia) {
        this.franquicia = franquicia;
    }
    public String getInspeccion_visual() {
        return inspeccion_visual;
    }
    public void setInspeccion_visual(String inspeccion_visual) {
        this.inspeccion_visual = inspeccion_visual;
    }
    public String getOtros_mataux() {
        return otros_mataux;
    }
    public void setOtros_mataux(String otros_mataux) {
        this.otros_mataux = otros_mataux;
    }
    public boolean isBinspeccionvisual() {
        return binspeccionvisual;
    }
    public void setBinspeccionvisual(boolean binspeccionvisual) {
        this.binspeccionvisual = binspeccionvisual;
    }
    public boolean isBotrosmataux() {
        return botrosmataux;
    }
    public void setBotrosmataux(boolean botrosmataux) {
        this.botrosmataux = botrosmataux;
    }
    public String getTipo_via() {
        return tipo_via;
    }
    public void setTipo_via(String tipo_via) {
        this.tipo_via = tipo_via;
    }
    public String getVia() {
        return via;
    }
    public void setVia(String via) {
        this.via = via;
    }
    public String getNumero_direccion() {
        return numero_direccion;
    }
    public void setNumero_direccion(String numero_direccion) {
        this.numero_direccion = numero_direccion;
    }
    public String getEscalera_direccion() {
        return escalera_direccion;
    }
    public void setEscalera_direccion(String escalera_direccion) {
        this.escalera_direccion = escalera_direccion;
    }
    public String getPiso_direccion() {
        return piso_direccion;
    }
    public void setPiso_direccion(String piso_direccion) {
        this.piso_direccion = piso_direccion;
    }
    public String getPuerta_direccion() {
        return puerta_direccion;
    }
    public void setPuerta_direccion(String puerta_direccion) {
        this.puerta_direccion = puerta_direccion;
    }
    public String getCp_direccion() {
        return cp_direccion;
    }
    public void setCp_direccion(String cp_direccion) {
        this.cp_direccion = cp_direccion;
    }
    public String getMunicipio_direccion() {
        return municipio_direccion;
    }
    public void setMunicipio_direccion(String municipio_direccion) {
        this.municipio_direccion = municipio_direccion;
    }
    public String getProvincia_direccion() {
        return provincia_direccion;
    }
    public void setProvincia_direccion(String provincia_direccion) {
        this.provincia_direccion = provincia_direccion;
    }
    public String getLatitud_direccion() {
        return latitud_direccion;
    }
    public void setLatitud_direccion(String latitud_direccion) {
        this.latitud_direccion = latitud_direccion;
    }
    public String getLongitud_direccion() {
        return longitud_direccion;
    }
    public void setLongitud_direccion(String longitud_direccion) {
        this.longitud_direccion = longitud_direccion;
    }
    public String getNombre_cliente() {
        return nombre_cliente;
    }
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }
    public String getDni_cliente() {
        return dni_cliente;
    }
    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }
    public String getTelefono1_cliente() {
        return telefono1_cliente;
    }
    public void setTelefono1_cliente(String telefono1_cliente) {
        this.telefono1_cliente = telefono1_cliente;
    }
    public String getTelefono2_cliente() {
        return telefono2_cliente;
    }
    public void setTelefono2_cliente(String telefono2_cliente) {
        this.telefono2_cliente = telefono2_cliente;
    }
    public String getTelefono3_cliente() {
        return telefono3_cliente;
    }
    public void setTelefono3_cliente(String telefono3_cliente) {
        this.telefono3_cliente = telefono3_cliente;
    }
    public String getTelefono4_cliente() {
        return telefono4_cliente;
    }
    public void setTelefono4_cliente(String telefono4_cliente) {
        this.telefono4_cliente = telefono4_cliente;
    }
    public String getEmail_cliente() {
        return email_cliente;
    }
    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }
    public String getObservaciones_cliente() {
        return observaciones_cliente;
    }
    public void setObservaciones_cliente(String observaciones_cliente) {
        this.observaciones_cliente = observaciones_cliente;
    }

    public String getUser_creador() {
        return user_creador;
    }

    public void setUser_creador(String user_creador) {
        this.user_creador = user_creador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}