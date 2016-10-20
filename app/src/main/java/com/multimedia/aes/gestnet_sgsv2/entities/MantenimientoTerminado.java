package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_mantenimientos_terminados")
public class MantenimientoTerminado {
    public static final String ID_MANTENIMIENTO_TERMINADO = "_id_mantenimiento_terminado";
    public static final String Fk_PARTE = "fk_parte";
    public static final String CODIGO_BARRAS = "codigo_barras";
    public static final String FK_ESTADO_VISITA = "fk_estado_visita";
    public static final String FK_TIPO_VISITA = "fk_tipo_visita";
    public static final String FK_SUBTIPO_VISITA = "fk_subtipo_visita";
    public static final String OBSERVACIONES_TECNICO = "observaciones_tecnico";
    public static final String CONTADOR_INTERNO = "contador_interno";
    public static final String REPARACION = "reparacion";
    public static final String FK_TIPO_REPARACION = "fk_tipo_reparacion";
    public static final String FECHA_REPARACION = "fecha_reparacion";
    public static final String FK_TIEMPO_MANO_OBRA = "fk_tiempo_mano_obra";
    public static final String COSTE_MATERIALES = "coste_materiales";
    public static final String COSTE_MANO_OBRA = "coste_mano_obra";
    public static final String COSTE_MANO_OBRA_ADICIONAL = "coste_mano_obra_adicional";
    public static final String CODIGO_BARRAS_REPARACION = "codigo_barras_reparacion";
    public static final String LIMPIEZA_QUEMADORES_CALDERA = "limpieza_quemadores_caldera";
    public static final String REVISION_VASO_EXPANSION = "revision_vaso_expansion";
    public static final String REGULACION_APARATOS = "regulacion_aparatos";
    public static final String COMPROBAR_ESTANQUEIDAD_CIERRE_QUEMADORES_CALDERA = "comprobar_estanqueidad_cierre_quemadores_caldera";
    public static final String REVISION_CALDERAS_CONTADORES = "revision_calderas_contadores";
    public static final String VERIFICACION_CIRCUITO_HIDRAULICO_CALEFACCION = "verificacion_circuito_hidraulico_calefaccion";
    public static final String ESTANQUEIDAD_CONEXION_APARATOS = "estanqueidad_conexion_aparatos";
    public static final String ESTANQUEIDAD_CONDUCTO_EVACUACION_IRG = "estanqueidad_conducto_evacuacion_irg";
    public static final String COMPROBACION_NIVELES_AGUA = "comprobacion_niveles_agua";
    public static final String TIPO_CONDUCTO_EVACUACION = "tipo_conducto_evacuacion";
    public static final String REVISION_ESTADO_AISLAMIENTO_TERMICO = "revision_estado_aislamiento_termico";
    public static final String ANALISIS_PRODUCTOS_COMBUSTION = "analisis_productos_combustion";
    public static final String CAUDAL_ACS_CALCULO_POTENCIA = "caudal_acs_calculo_potencia";
    public static final String REVISION_SISTEMA_CONTROL = "revision_sistema_control";
    public static final String ENVIADO = "enviado";
    public static final String MAQUINA = "maquina";
    public static final String ACCIONES = "acciones";
    public static final String ANOMALIA = "anomalia";
    public static final String ISITU = "insitu";
    public static final String FK_MOTIVOS_NO_REP = "fk_motivos_no_rep";
    public static final String OBS_REPARACION_IBERDROLA = "obs_reparacion_iberdrola";
    public static final String COD_VISITA_PLATAFORMA = "cod_visita_plataforma";
    public static final String FECHA_TICKET = "fecha_ticket";
    public static final String HORA_TICKET = "hora_ticket";
    public static final String PRECINTADO = "precintado";

    @DatabaseField(generatedId = true, columnName = ID_MANTENIMIENTO_TERMINADO)
    private int id_mantenimiento_terminado;
    @DatabaseField(columnName = Fk_PARTE)
    private int fk_parte;
    @DatabaseField(columnName = CODIGO_BARRAS)
    private String codigo_barras;
    @DatabaseField(columnName = FK_ESTADO_VISITA)
    private int fk_estado_visita;
    @DatabaseField(columnName = FK_TIPO_VISITA)
    private int fk_tipo_visita;
    @DatabaseField(columnName = FK_SUBTIPO_VISITA)
    private int fk_subtipo_visita;
    @DatabaseField(columnName = OBSERVACIONES_TECNICO)
    private String observaciones_tecnico;
    @DatabaseField(columnName = CONTADOR_INTERNO)
    private int contador_interno;
    @DatabaseField(columnName = REPARACION)
    private int reparacion;
    @DatabaseField(columnName = FK_TIPO_REPARACION)
    private int fk_tipo_reparacion;
    @DatabaseField(columnName = FECHA_REPARACION)
    private String fecha_reparacion;
    @DatabaseField(columnName = FK_TIEMPO_MANO_OBRA)
    private int fk_tiempo_mano_obra;
    @DatabaseField(columnName = COSTE_MATERIALES)
    private String coste_materiales;
    @DatabaseField(columnName = COSTE_MANO_OBRA)
    private String coste_mano_obra;
    @DatabaseField(columnName = COSTE_MANO_OBRA_ADICIONAL)
    private String coste_mano_obra_adicional;
    @DatabaseField(columnName = CODIGO_BARRAS_REPARACION)
    private String codigo_barras_reparacion;
    @DatabaseField(columnName = LIMPIEZA_QUEMADORES_CALDERA)
    private int limpieza_quemadores_caldera;
    @DatabaseField(columnName = REVISION_VASO_EXPANSION)
    private int revision_vaso_expansion;
    @DatabaseField(columnName = REGULACION_APARATOS)
    private int regulacion_aparatos;
    @DatabaseField(columnName = COMPROBAR_ESTANQUEIDAD_CIERRE_QUEMADORES_CALDERA)
    private int comprobar_estanqueidad_cierre_quemadores_caldera;
    @DatabaseField(columnName = REVISION_CALDERAS_CONTADORES)
    private int revision_calderas_contadores;
    @DatabaseField(columnName = VERIFICACION_CIRCUITO_HIDRAULICO_CALEFACCION)
    private int verificacion_circuito_hidraulico_calefaccion;
    @DatabaseField(columnName = ESTANQUEIDAD_CONEXION_APARATOS)
    private int estanqueidad_conexion_aparatos;
    @DatabaseField(columnName = ESTANQUEIDAD_CONDUCTO_EVACUACION_IRG)
    private int estanqueidad_conducto_evacuacion_irg;
    @DatabaseField(columnName = COMPROBACION_NIVELES_AGUA)
    private int comprobacion_niveles_agua;
    @DatabaseField(columnName = TIPO_CONDUCTO_EVACUACION)
    private int tipo_conducto_evacuacion;
    @DatabaseField(columnName = REVISION_ESTADO_AISLAMIENTO_TERMICO)
    private int revision_estado_aislamiento_termico;
    @DatabaseField(columnName = ANALISIS_PRODUCTOS_COMBUSTION)
    private int analisis_productos_combustion;
    @DatabaseField(columnName = CAUDAL_ACS_CALCULO_POTENCIA)
    private int caudal_acs_calculo_potencia;
    @DatabaseField(columnName = REVISION_SISTEMA_CONTROL)
    private int revision_sistema_control;
    @DatabaseField(columnName = ENVIADO)
    private boolean enviado;
    @DatabaseField(columnName = MAQUINA)
    private boolean maquina;
    @DatabaseField(columnName = ACCIONES)
    private boolean acciones;
    @DatabaseField(columnName = ANOMALIA)
    private boolean anomalia;
    @DatabaseField(columnName = ISITU)
    private boolean insitu;
    @DatabaseField(columnName = FK_MOTIVOS_NO_REP)
    private int fk_motivos_no_rep;
    @DatabaseField(columnName = OBS_REPARACION_IBERDROLA)
    private String obs_reparacion_iberdrola;
    @DatabaseField(columnName = COD_VISITA_PLATAFORMA)
    private String cod_visita_plataforma;
    @DatabaseField(columnName = FECHA_TICKET)
    private String fecha_ticket;
    @DatabaseField(columnName = HORA_TICKET)
    private String hora_ticket;
    @DatabaseField(columnName = PRECINTADO)
    private int precintado;

    public MantenimientoTerminado() {
    }

    public MantenimientoTerminado(int id_mantenimiento_terminado, int fk_parte, String codigo_barras,
                                  int fk_estado_visita, int fk_tipo_visita, int fk_subtipo_visita, String observaciones_tecnico,
                                  int contador_interno, int reparacion,
                                  int fk_tipo_reparacion, String fecha_reparacion, int fk_tiempo_mano_obra,
                                  String coste_materiales, String coste_mano_obra, String coste_mano_obra_adicional,
                                  String codigo_barras_reparacion,int limpieza_quemadores_caldera, int revision_vaso_expansion,
                                  int regulacion_aparatos, int comprobar_estanqueidad_cierre_quemadores_caldera,
                                  int revision_calderas_contadores, int verificacion_circuito_hidraulico_calefaccion,
                                  int estanqueidad_conexion_aparatos, int estanqueidad_conducto_evacuacion_irg,
                                  int comprobacion_niveles_agua, int tipo_conducto_evacuacion,
                                  int revision_estado_aislamiento_termico, int analisis_productos_combustion,
                                  int caudal_acs_calculo_potencia, int revision_sistema_control,boolean enviado,boolean maquina,
                                  boolean acciones,boolean anomalia,boolean insitu,int fk_motivos_no_rep,String obs_reparacion_iberdrola,
                                  String cod_visita_plataforma,String fecha_ticket,String hora_ticket,int precintado) {
        this.id_mantenimiento_terminado = id_mantenimiento_terminado;
        this.fk_parte = fk_parte;
        this.codigo_barras = codigo_barras;
        this.fk_estado_visita = fk_estado_visita;
        this.fk_tipo_visita = fk_tipo_visita;
        this.fk_subtipo_visita = fk_subtipo_visita;
        this.observaciones_tecnico = observaciones_tecnico;
        this.contador_interno = contador_interno;
        this.reparacion = reparacion;
        this.fk_tipo_reparacion = fk_tipo_reparacion;
        this.fecha_reparacion = fecha_reparacion;
        this.fk_tiempo_mano_obra = fk_tiempo_mano_obra;
        this.coste_materiales = coste_materiales;
        this.coste_mano_obra = coste_mano_obra;
        this.coste_mano_obra_adicional = coste_mano_obra_adicional;
        this.codigo_barras_reparacion = codigo_barras_reparacion;
        this.limpieza_quemadores_caldera = limpieza_quemadores_caldera;
        this.revision_vaso_expansion = revision_vaso_expansion;
        this.regulacion_aparatos = regulacion_aparatos;
        this.comprobar_estanqueidad_cierre_quemadores_caldera = comprobar_estanqueidad_cierre_quemadores_caldera;
        this.revision_calderas_contadores = revision_calderas_contadores;
        this.verificacion_circuito_hidraulico_calefaccion = verificacion_circuito_hidraulico_calefaccion;
        this.estanqueidad_conexion_aparatos = estanqueidad_conexion_aparatos;
        this.estanqueidad_conducto_evacuacion_irg = estanqueidad_conducto_evacuacion_irg;
        this.comprobacion_niveles_agua = comprobacion_niveles_agua;
        this.tipo_conducto_evacuacion = tipo_conducto_evacuacion;
        this.revision_estado_aislamiento_termico = revision_estado_aislamiento_termico;
        this.analisis_productos_combustion = analisis_productos_combustion;
        this.caudal_acs_calculo_potencia = caudal_acs_calculo_potencia;
        this.revision_sistema_control = revision_sistema_control;
        this.enviado = enviado;
        this.maquina = maquina;
        this.acciones = acciones;
        this.anomalia = anomalia;
        this.insitu = insitu;
        this.fk_motivos_no_rep = fk_motivos_no_rep;
        this.obs_reparacion_iberdrola = obs_reparacion_iberdrola;
        this.cod_visita_plataforma = cod_visita_plataforma;
        this.fecha_ticket = fecha_ticket;
        this.hora_ticket = hora_ticket;
        this.precintado = precintado;
    }

    public int getId_mantenimiento_terminado() {
            return id_mantenimiento_terminado;
        }
    public void setId_mantenimiento_terminado(int id_mantenimiento_terminado) {
        this.id_mantenimiento_terminado = id_mantenimiento_terminado;
    }
    public int getFk_parte() {
        return fk_parte;
    }
    public void setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
    }
    public String getCodigo_barras() {
        return codigo_barras;
    }
    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }
    public int getFk_estado_visita() {
        return fk_estado_visita;
    }
    public void setFk_estado_visita(int fk_estado_visita) {
        this.fk_estado_visita = fk_estado_visita;
    }
    public int getFk_tipo_visita() {
        return fk_tipo_visita;
    }
    public void setFk_tipo_visita(int fk_tipo_visita) {
        this.fk_tipo_visita = fk_tipo_visita;
    }
    public int getFk_subtipo_visita() {
        return fk_subtipo_visita;
    }
    public void setFk_subtipo_visita(int fk_subtipo_visita) {
        this.fk_subtipo_visita = fk_subtipo_visita;
    }
    public String getObservaciones_tecnico() {
        return observaciones_tecnico;
    }
    public void setObservaciones_tecnico(String observaciones_tecnico) {
        this.observaciones_tecnico = observaciones_tecnico;
    }
    public int getContador_interno() {
        return contador_interno;
    }
    public void setContador_interno(int contador_interno) {
        this.contador_interno = contador_interno;
    }
    public int getReparacion() {
        return reparacion;
    }
    public void setReparacion(int reparacion) {
        this.reparacion = reparacion;
    }
    public int getFk_tipo_reparacion() {
        return fk_tipo_reparacion;
    }
    public void setFk_tipo_reparacion(int fk_tipo_reparacion) {
        this.fk_tipo_reparacion = fk_tipo_reparacion;
    }
    public String getFecha_reparacion() {
        return fecha_reparacion;
    }
    public void setFecha_reparacion(String fecha_reparacion) {
        this.fecha_reparacion = fecha_reparacion;
    }
    public int getFk_tiempo_mano_obra() {
        return fk_tiempo_mano_obra;
    }
    public void setFk_tiempo_mano_obra(int fk_tiempo_mano_obra) {
        this.fk_tiempo_mano_obra = fk_tiempo_mano_obra;
    }
    public String getCoste_materiales() {
        return coste_materiales;
    }
    public void setCoste_materiales(String coste_materiales) {
        this.coste_materiales = coste_materiales;
    }
    public String getCoste_mano_obra() {
        return coste_mano_obra;
    }
    public void setCoste_mano_obra(String coste_mano_obra) {
        this.coste_mano_obra = coste_mano_obra;
    }
    public String getCoste_mano_obra_adicional() {
        return coste_mano_obra_adicional;
    }
    public void setCoste_mano_obra_adicional(String coste_mano_obra_adicional) {
        this.coste_mano_obra_adicional = coste_mano_obra_adicional;
    }
    public String getCodigo_barras_reparacion() {
        return codigo_barras_reparacion;
    }
    public void setCodigo_barras_reparacion(String codigo_barras_reparacion) {
        this.codigo_barras_reparacion = codigo_barras_reparacion;
    }
    public int getLimpieza_quemadores_caldera() {
        return limpieza_quemadores_caldera;
    }
    public void setLimpieza_quemadores_caldera(int limpieza_quemadores_caldera) {
        this.limpieza_quemadores_caldera = limpieza_quemadores_caldera;
    }
    public int getRevision_vaso_expansion() {
        return revision_vaso_expansion;
    }
    public void setRevision_vaso_expansion(int revision_vaso_expansion) {
        this.revision_vaso_expansion = revision_vaso_expansion;
    }
    public int getRegulacion_aparatos() {
        return regulacion_aparatos;
    }
    public void setRegulacion_aparatos(int regulacion_aparatos) {
        this.regulacion_aparatos = regulacion_aparatos;
    }
    public int getComprobar_estanqueidad_cierre_quemadores_caldera() {
        return comprobar_estanqueidad_cierre_quemadores_caldera;
    }
    public void setComprobar_estanqueidad_cierre_quemadores_caldera(int comprobar_estanqueidad_cierre_quemadores_caldera) {
        this.comprobar_estanqueidad_cierre_quemadores_caldera = comprobar_estanqueidad_cierre_quemadores_caldera;
    }
    public int getRevision_calderas_contadores() {
        return revision_calderas_contadores;
    }
    public void setRevision_calderas_contadores(int revision_calderas_contadores) {
        this.revision_calderas_contadores = revision_calderas_contadores;
    }
    public int getVerificacion_circuito_hidraulico_calefaccion() {
        return verificacion_circuito_hidraulico_calefaccion;
    }
    public void setVerificacion_circuito_hidraulico_calefaccion(int verificacion_circuito_hidraulico_calefaccion) {
        this.verificacion_circuito_hidraulico_calefaccion = verificacion_circuito_hidraulico_calefaccion;
    }
    public int getEstanqueidad_conexion_aparatos() {
        return estanqueidad_conexion_aparatos;
    }
    public void setEstanqueidad_conexion_aparatos(int estanqueidad_conexion_aparatos) {
        this.estanqueidad_conexion_aparatos = estanqueidad_conexion_aparatos;
    }
    public int getEstanqueidad_conducto_evacuacion_irg() {
        return estanqueidad_conducto_evacuacion_irg;
    }
    public void setEstanqueidad_conducto_evacuacion_irg(int estanqueidad_conducto_evacuacion_irg) {
        this.estanqueidad_conducto_evacuacion_irg = estanqueidad_conducto_evacuacion_irg;
    }
    public int getComprobacion_niveles_agua() {
        return comprobacion_niveles_agua;
    }
    public void setComprobacion_niveles_agua(int comprobacion_niveles_agua) {
        this.comprobacion_niveles_agua = comprobacion_niveles_agua;
    }
    public int getTipo_conducto_evacuacion() {
        return tipo_conducto_evacuacion;
    }
    public void setTipo_conducto_evacuacion(int tipo_conducto_evacuacion) {
        this.tipo_conducto_evacuacion = tipo_conducto_evacuacion;
    }
    public int getRevision_estado_aislamiento_termico() {
        return revision_estado_aislamiento_termico;
    }
    public void setRevision_estado_aislamiento_termico(int revision_estado_aislamiento_termico) {
        this.revision_estado_aislamiento_termico = revision_estado_aislamiento_termico;
    }
    public int getAnalisis_productos_combustion() {
        return analisis_productos_combustion;
    }
    public void setAnalisis_productos_combustion(int analisis_productos_combustion) {
        this.analisis_productos_combustion = analisis_productos_combustion;
    }
    public int getCaudal_acs_calculo_potencia() {
        return caudal_acs_calculo_potencia;
    }
    public void setCaudal_acs_calculo_potencia(int caudal_acs_calculo_potencia) {
        this.caudal_acs_calculo_potencia = caudal_acs_calculo_potencia;
    }
    public int getRevision_sistema_control() {
        return revision_sistema_control;
    }
    public void setRevision_sistema_control(int revision_sistema_control) {
        this.revision_sistema_control = revision_sistema_control;
    }
    public boolean isEnviado() {
        return enviado;
    }
    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
    public boolean isMaquina() {
        return maquina;
    }
    public void setMaquina(boolean maquina) {
        this.maquina = maquina;
    }
    public boolean isAnomalia() {
        return anomalia;
    }
    public void setAnomalia(boolean anomalia) {
        this.anomalia = anomalia;
    }
    public boolean isInsitu() {
        return insitu;
    }
    public void setInsitu(boolean insitu) {
        this.insitu = insitu;
    }
    public boolean isAcciones() {
        return acciones;
    }
    public void setAcciones(boolean acciones) {
        this.acciones = acciones;
    }
    public int getFk_motivos_no_rep() {
        return fk_motivos_no_rep;
    }
    public void setFk_motivos_no_rep(int fk_motivos_no_rep) {
        this.fk_motivos_no_rep = fk_motivos_no_rep;
    }
    public String getObs_reparacion_iberdrola() {
        return obs_reparacion_iberdrola;
    }
    public void setObs_reparacion_iberdrola(String obs_reparacion_iberdrola) {
        this.obs_reparacion_iberdrola = obs_reparacion_iberdrola;
    }
    public String getCod_visita_plataforma() {
        return cod_visita_plataforma;
    }
    public void setCod_visita_plataforma(String cod_visita_plataforma) {
        this.cod_visita_plataforma = cod_visita_plataforma;
    }
    public String getFecha_ticket() {
        return fecha_ticket;
    }
    public void setFecha_ticket(String fecha_ticket) {
        this.fecha_ticket = fecha_ticket;
    }
    public String getHora_ticket() {
        return hora_ticket;
    }
    public void setHora_ticket(String hora_ticket) {
        this.hora_ticket = hora_ticket;
    }
    public int getPrecintado() {
        return precintado;
    }
    public void setPrecintado(int precintado) {
        this.precintado = precintado;
    }
}
