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
    public static final String OBSERVACIONES_TECNICO = "observaciones_tecnico";
    public static final String CONTADOR_INTERNO = "contador_interno";
    public static final String CODIGO_INTERNO = "codigo_interno";
    public static final String REPARACION = "reparacion";
    public static final String FK_TIPO_REPARACION = "fk_tipo_reparacion";
    public static final String FECHA_REPARACION = "fecha_reparacion";
    public static final String FK_TIEMPO_MANO_OBRA = "fk_tiempo_mano_obra";
    public static final String COSTE_MATERIALES = "coste_materiales";
    public static final String COSTE_MANO_OBRA = "coste_mano_obra";
    public static final String COSTE_MANO_OBRA_ADICIONAL = "coste_mano_obra_adicional";
    public static final String CODIGO_BARRAS_REPARACION = "codigo_barras_reparacion";
    public static final String FK_TIPO_MAQUINA = "fk_tipo_maquina";
    public static final String FK_MARCA_MAQUINA = "fk_marca_maquina";
    public static final String MODELO_MAQUINA = "modelo_maquina";
    public static final String FK_POTENCIA_MAQUINA = "fk_potencia_maquina";
    public static final String FK_USO_MAQUINA = "fk_uso_maquina";
    public static final String PUESTA_MARCHA_MAQUINA = "puesta_marcha_maquina";
    public static final String CODIGO_MAQUINA = "codigo_maquina";
    public static final String C0_MAQUINA = "c0_maquina";
    public static final String TEMPERATURA_MAX_ACS = "temperatura_max_acs";
    public static final String CAUDAL_ACS = "caudal_acs";
    public static final String POTENCIA_UTIL = "potencia_util";
    public static final String TEMPERATURA_GASES_COMBUSTION = "temperatura_gases_combustion";
    public static final String TEMPERATURA_AMBIENTE_LOCAL = "temperatura_ambiente_local";
    public static final String TEMPERATURA_AGUA_GENERADOR_CALOR_ENTRADA = "temperatura_agua_generador_calor_entrada";
    public static final String TEMPERATURA_AGUA_GENERADOR_CALOR_SALIDA = "temperatura_agua_generador_calor_salida";
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
    @DatabaseField(columnName = OBSERVACIONES_TECNICO)
    private String observaciones_tecnico;
    @DatabaseField(columnName = CONTADOR_INTERNO)
    private int contador_interno;
    @DatabaseField(columnName = CODIGO_INTERNO)
    private String codigo_interno;
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
    @DatabaseField(columnName = FK_TIPO_MAQUINA)
    private int fk_tipo_maquina;
    @DatabaseField(columnName = FK_MARCA_MAQUINA)
    private int fk_marca_maquina;
    @DatabaseField(columnName = MODELO_MAQUINA)
    private String modelo_maquina;
    @DatabaseField(columnName = FK_POTENCIA_MAQUINA)
    private int fk_potencia_maquina;
    @DatabaseField(columnName = FK_USO_MAQUINA)
    private int fk_uso_maquina;
    @DatabaseField(columnName = PUESTA_MARCHA_MAQUINA)
    private String puesta_marcha_maquina;
    @DatabaseField(columnName = CODIGO_MAQUINA)
    private String codigo_maquina;
    @DatabaseField(columnName = C0_MAQUINA)
    private String c0_maquina;
    @DatabaseField(columnName = TEMPERATURA_MAX_ACS)
    private String temperatura_max_acs;
    @DatabaseField(columnName = CAUDAL_ACS)
    private String caudal_acs;
    @DatabaseField(columnName = POTENCIA_UTIL)
    private String potencia_util;
    @DatabaseField(columnName = TEMPERATURA_GASES_COMBUSTION)
    private String temperatura_gases_combustion;
    @DatabaseField(columnName = TEMPERATURA_AMBIENTE_LOCAL)
    private String temperatura_ambiente_local;
    @DatabaseField(columnName = TEMPERATURA_AGUA_GENERADOR_CALOR_ENTRADA)
    private String temperatura_agua_generador_calor_entrada;
    @DatabaseField(columnName = TEMPERATURA_AGUA_GENERADOR_CALOR_SALIDA)
    private String temperatura_agua_generador_calor_salida;
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

    public MantenimientoTerminado() {
    }

    public MantenimientoTerminado(int id_mantenimiento_terminado, int fk_parte, String codigo_barras,
                                  int fk_estado_visita, int fk_tipo_visita, String observaciones_tecnico,
                                  int contador_interno, String codigo_interno, int reparacion,
                                  int fk_tipo_reparacion, String fecha_reparacion, int fk_tiempo_mano_obra,
                                  String coste_materiales, String coste_mano_obra, String coste_mano_obra_adicional,
                                  String codigo_barras_reparacion, int fk_tipo_maquina, int fk_marca_maquina,
                                  String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
                                  String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
                                  String temperatura_max_acs, String caudal_acs, String potencia_util,
                                  String temperatura_gases_combustion, String temperatura_ambiente_local,
                                  String temperatura_agua_generador_calor_entrada,
                                  String temperatura_agua_generador_calor_salida,
                                  int limpieza_quemadores_caldera, int revision_vaso_expansion,
                                  int regulacion_aparatos, int comprobar_estanqueidad_cierre_quemadores_caldera,
                                  int revision_calderas_contadores, int verificacion_circuito_hidraulico_calefaccion,
                                  int estanqueidad_conexion_aparatos, int estanqueidad_conducto_evacuacion_irg,
                                  int comprobacion_niveles_agua, int tipo_conducto_evacuacion,
                                  int revision_estado_aislamiento_termico, int analisis_productos_combustion,
                                  int caudal_acs_calculo_potencia, int revision_sistema_control,boolean enviado) {
        this.id_mantenimiento_terminado = id_mantenimiento_terminado;
        this.fk_parte = fk_parte;
        this.codigo_barras = codigo_barras;
        this.fk_estado_visita = fk_estado_visita;
        this.fk_tipo_visita = fk_tipo_visita;
        this.observaciones_tecnico = observaciones_tecnico;
        this.contador_interno = contador_interno;
        this.codigo_interno = codigo_interno;
        this.reparacion = reparacion;
        this.fk_tipo_reparacion = fk_tipo_reparacion;
        this.fecha_reparacion = fecha_reparacion;
        this.fk_tiempo_mano_obra = fk_tiempo_mano_obra;
        this.coste_materiales = coste_materiales;
        this.coste_mano_obra = coste_mano_obra;
        this.coste_mano_obra_adicional = coste_mano_obra_adicional;
        this.codigo_barras_reparacion = codigo_barras_reparacion;
        this.fk_tipo_maquina = fk_tipo_maquina;
        this.fk_marca_maquina = fk_marca_maquina;
        this.modelo_maquina = modelo_maquina;
        this.fk_potencia_maquina = fk_potencia_maquina;
        this.fk_uso_maquina = fk_uso_maquina;
        this.puesta_marcha_maquina = puesta_marcha_maquina;
        this.codigo_maquina = codigo_maquina;
        this.c0_maquina = c0_maquina;
        this.temperatura_max_acs = temperatura_max_acs;
        this.caudal_acs = caudal_acs;
        this.potencia_util = potencia_util;
        this.temperatura_gases_combustion = temperatura_gases_combustion;
        this.temperatura_ambiente_local = temperatura_ambiente_local;
        this.temperatura_agua_generador_calor_entrada = temperatura_agua_generador_calor_entrada;
        this.temperatura_agua_generador_calor_salida = temperatura_agua_generador_calor_salida;
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
    public String getCodigo_interno() {
        return codigo_interno;
    }
    public void setCodigo_interno(String codigo_interno) {
        this.codigo_interno = codigo_interno;
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
    public int getFk_tipo_maquina() {
        return fk_tipo_maquina;
    }
    public void setFk_tipo_maquina(int fk_tipo_maquina) {
        this.fk_tipo_maquina = fk_tipo_maquina;
    }
    public int getFk_marca_maquina() {
        return fk_marca_maquina;
    }
    public void setFk_marca_maquina(int fk_marca_maquina) {
        this.fk_marca_maquina = fk_marca_maquina;
    }
    public String getModelo_maquina() {
        return modelo_maquina;
    }
    public void setModelo_maquina(String modelo_maquina) {
        this.modelo_maquina = modelo_maquina;
    }
    public int getFk_potencia_maquina() {
        return fk_potencia_maquina;
    }
    public void setFk_potencia_maquina(int fk_potencia_maquina) {
        this.fk_potencia_maquina = fk_potencia_maquina;
    }
    public int getFk_uso_maquina() {
        return fk_uso_maquina;
    }
    public void setFk_uso_maquina(int fk_uso_maquina) {
        this.fk_uso_maquina = fk_uso_maquina;
    }
    public String getPuesta_marcha_maquina() {
        return puesta_marcha_maquina;
    }
    public void setPuesta_marcha_maquina(String puesta_marcha_maquina) {
    this.puesta_marcha_maquina = puesta_marcha_maquina;
    }
    public String getCodigo_maquina() {
        return codigo_maquina;
    }
    public void setCodigo_maquina(String codigo_maquina) {
        this.codigo_maquina = codigo_maquina;
    }
    public String getC0_maquina() {
        return c0_maquina;
    }
    public void setC0_maquina(String c0_maquina) {
    this.c0_maquina = c0_maquina;
    }
    public String getTemperatura_max_acs() {
        return temperatura_max_acs;
    }
    public void setTemperatura_max_acs(String temperatura_max_acs) {
        this.temperatura_max_acs = temperatura_max_acs;
    }
    public String getCaudal_acs() {
        return caudal_acs;
    }
    public void setCaudal_acs(String caudal_acs) {
        this.caudal_acs = caudal_acs;
    }
    public String getPotencia_util() {
        return potencia_util;
    }
    public void setPotencia_util(String potencia_util) {
        this.potencia_util = potencia_util;
    }
    public String getTemperatura_gases_combustion() {
        return temperatura_gases_combustion;
    }
    public void setTemperatura_gases_combustion(String temperatura_gases_combustion) {
        this.temperatura_gases_combustion = temperatura_gases_combustion;
    }
    public String getTemperatura_ambiente_local() {
        return temperatura_ambiente_local;
    }
    public void setTemperatura_ambiente_local(String temperatura_ambiente_local) {
        this.temperatura_ambiente_local = temperatura_ambiente_local;
    }
    public String getTemperatura_agua_generador_calor_entrada() {
        return temperatura_agua_generador_calor_entrada;
    }
    public void setTemperatura_agua_generador_calor_entrada(String temperatura_agua_generador_calor_entrada) {
        this.temperatura_agua_generador_calor_entrada = temperatura_agua_generador_calor_entrada;
    }
    public String getTemperatura_agua_generador_calor_salida() {
        return temperatura_agua_generador_calor_salida;
    }
    public void setTemperatura_agua_generador_calor_salida(String temperatura_agua_generador_calor_salida) {
    this.temperatura_agua_generador_calor_salida = temperatura_agua_generador_calor_salida;
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
}
