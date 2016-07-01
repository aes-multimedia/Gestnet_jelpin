package com.multimedia.aes.gestnet_sgsv2.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mos_averias")
public class Averia {

    public static final String ID_AVERIA = "_id_averia";
    public static final String IDENTIFICADOR_CARGA = "identificador_carga";
    public static final String ID_SOLICITUD = "id_solicitud";
    public static final String FK_TECNICO = "fk_tecnico";
    public static final String COD_CONTRATO = "cod_contrato";
    public static final String TIPO_SOLICITUD = "tipo_solicitud";
    public static final String DES_TIPO_SOLICITUD = "des_tipo_solicitud";
    public static final String SUBTIPO_SOLICITUD = "subtipo_solicitud";
    public static final String DES_SUBTIPO_SOLICITUD = "des_subtipo_solicitud";
    public static final String FECHA_CREACION = "fecha_creacion";
    public static final String FECHA_CIERRE = "fecha_cierre";
    public static final String ESTADO_SOLICITUD = "estado_solicitud";
    public static final String DES_ESTADO_SOLICITUD = "des_estado_solicitud";
    public static final String SUBESTADO_SOLICITUD = "subestado_solicitud";
    public static final String TELEFONO_CONTACTO = "telefono_contacto";
    public static final String PERSONA_CONTACTO = "persona_contacto";
    public static final String DES_AVERIA = "des_averia";
    public static final String OBSERVACIONES = "observaciones";
    public static final String PROVEEDOR = "proveedor";
    public static final String DESCRIPCION_CANCELACION = "descripcion_cancelacion";
    public static final String URGENCIA = "urgencia";
    public static final String FECHA_LIMITE_VISITA = "fecha_limite_visita";
    public static final String MARCA_CALDERA = "marca_caldera";
    public static final String NOMBRE_PROVINCIA = "nombre_provincia";
    public static final String URGENTE = "urgente";
    public static final String NOMBRE_POBLACION = "nombre_poblacion";
    public static final String NOM_CALLE = "nom_calle";
    public static final String COD_PORTAL = "cod_portal";
    public static final String TIP_PISO = "tip_piso";
    public static final String TIP_MANO = "tip_mano";
    public static final String COD_POSTAL = "cod_postal";
    public static final String CUPS = "cups";
    public static final String ESTADO_ULTIMA_VISITA = "estado_ultima_visita";
    public static final String URGENCIA_ULTIMA_VISITA = "urgencia_ultima_visita";
    public static final String MANT_GAS_CALEFACCION = "mant_gas_calefaccion";
    public static final String MANT_GAS = "mant_gas";
    public static final String HORARIO_CONTACTO = "horario_contacto";
    public static final String OBSERVACIONES_VISITA = "observaciones_visita";
    public static final String TELEFONO_CLIENTE = "telefono_cliente";
    public static final String EFV = "efv";
    public static final String SCORING = "scoring";
    public static final String CONTADOR_AVERIAS = "contador_averias";
    public static final String CATEGORIA_VISITA = "categoria_visita";
    public static final String INICIADO_ANDROID = "iniciado_android";
    public static final String ENVIADO_ANDROID = "enviado_android";
    public static final String CERRADO = "cerrado";
    public static final String CERRADO_ERROR = "cerrado_error";
    public static final String HISTORICO = "historico";

    @DatabaseField(id = true, columnName = ID_AVERIA)
    private int id_averia;
    @DatabaseField(columnName = IDENTIFICADOR_CARGA)
    private String identificador_carga;
    @DatabaseField(columnName = ID_SOLICITUD)
    private String id_solicitud;
    @DatabaseField(columnName = FK_TECNICO)
    private int fk_tecnico;
    @DatabaseField(columnName = COD_CONTRATO)
    private String cod_contrato;
    @DatabaseField(columnName = TIPO_SOLICITUD)
    private String tipo_solicitud;
    @DatabaseField(columnName = DES_TIPO_SOLICITUD)
    private String des_tipo_solicitud;
    @DatabaseField(columnName = SUBTIPO_SOLICITUD)
    private String subtipo_solicitud;
    @DatabaseField(columnName = DES_SUBTIPO_SOLICITUD)
    private String des_subtipo_solicitud;
    @DatabaseField(columnName = FECHA_CREACION)
    private String fecha_creacion;
    @DatabaseField(columnName = FECHA_CIERRE)
    private String fecha_cierre;
    @DatabaseField(columnName = ESTADO_SOLICITUD)
    private String estado_solicitud;
    @DatabaseField(columnName = DES_ESTADO_SOLICITUD)
    private String des_estado_solicitud;
    @DatabaseField(columnName = SUBESTADO_SOLICITUD)
    private String subestado_solicitud;
    @DatabaseField(columnName = TELEFONO_CONTACTO)
    private String telefono_contacto;
    @DatabaseField(columnName = PERSONA_CONTACTO)
    private String persona_contacto;
    @DatabaseField(columnName = DES_AVERIA)
    private String des_averia;
    @DatabaseField(columnName = OBSERVACIONES)
    private String observaciones;
    @DatabaseField(columnName = PROVEEDOR)
    private String proveedor;
    @DatabaseField(columnName = DESCRIPCION_CANCELACION)
    private String descripcion_cancelacion;
    @DatabaseField(columnName = URGENCIA)
    private String urgencia;
    @DatabaseField(columnName = FECHA_LIMITE_VISITA)
    private String fecha_limite_visita;
    @DatabaseField(columnName = MARCA_CALDERA)
    private String marca_caldera;
    @DatabaseField(columnName = NOMBRE_PROVINCIA)
    private String nombre_provincia;
    @DatabaseField(columnName = URGENTE)
    private String urgente;
    @DatabaseField(columnName = NOMBRE_POBLACION)
    private String nombre_poblacion;
    @DatabaseField(columnName = NOM_CALLE)
    private String nom_calle;
    @DatabaseField(columnName = COD_PORTAL)
    private String cod_portal;
    @DatabaseField(columnName = TIP_PISO)
    private String tip_piso;
    @DatabaseField(columnName = TIP_MANO)
    private String tip_mano;
    @DatabaseField(columnName = COD_POSTAL)
    private String cod_postal;
    @DatabaseField(columnName = CUPS)
    private String cups;
    @DatabaseField(columnName = ESTADO_ULTIMA_VISITA)
    private String estado_ultima_visita;
    @DatabaseField(columnName = URGENCIA_ULTIMA_VISITA)
    private String urgencia_ultima_visita;
    @DatabaseField(columnName = MANT_GAS_CALEFACCION)
    private String mant_gas_calefaccion;
    @DatabaseField(columnName = MANT_GAS)
    private String mant_gas;
    @DatabaseField(columnName = HORARIO_CONTACTO)
    private String horario_contacto;
    @DatabaseField(columnName = OBSERVACIONES_VISITA)
    private String observaciones_visita;
    @DatabaseField(columnName = TELEFONO_CLIENTE)
    private String telefono_cliente;
    @DatabaseField(columnName = EFV)
    private String efv;
    @DatabaseField(columnName = SCORING)
    private String scoring;
    @DatabaseField(columnName = CONTADOR_AVERIAS)
    private String contador_averias;
    @DatabaseField(columnName = CATEGORIA_VISITA)
    private String categoria_visita;
    @DatabaseField(columnName = INICIADO_ANDROID)
    private String iniciado_android;
    @DatabaseField(columnName = ENVIADO_ANDROID)
    private String enviado_android;
    @DatabaseField(columnName = CERRADO)
    private String cerrado;
    @DatabaseField(columnName = CERRADO_ERROR)
    private String cerrado_error;
    @DatabaseField(columnName = HISTORICO)
    private String historico;

    public Averia(){
    }

    public Averia(int id_averia, String identificador_carga, String id_solicitud, int fk_tecnico,
                  String cod_contrato, String tipo_solicitud, String des_tipo_solicitud, String subtipo_solicitud,
                  String des_subtipo_solicitud, String fecha_creacion, String fecha_cierre, String estado_solicitud,
                  String des_estado_solicitud, String subestado_solicitud, String telefono_contacto,
                  String persona_contacto, String des_averia, String observaciones, String proveedor,
                  String descripcion_cancelacion, String urgencia, String fecha_limite_visita,
                  String marca_caldera, String nombre_provincia, String urgente, String nombre_poblacion,
                  String nom_calle, String cod_portal, String tip_piso, String tip_mano, String cod_postal,
                  String cups, String estado_ultima_visita, String urgencia_ultima_visita, String mant_gas_calefaccion,
                  String mant_gas, String horario_contacto, String observaciones_visita, String telefono_cliente,
                  String efv, String scoring, String contador_averias, String categoria_visita, String iniciado_android,
                  String enviado_android, String cerrado, String cerrado_error, String historico) {
        this.id_averia = id_averia;
        this.identificador_carga = identificador_carga;
        this.id_solicitud = id_solicitud;
        this.fk_tecnico = fk_tecnico;
        this.cod_contrato = cod_contrato;
        this.tipo_solicitud = tipo_solicitud;
        this.des_tipo_solicitud = des_tipo_solicitud;
        this.subtipo_solicitud = subtipo_solicitud;
        this.des_subtipo_solicitud = des_subtipo_solicitud;
        this.fecha_creacion = fecha_creacion;
        this.fecha_cierre = fecha_cierre;
        this.estado_solicitud = estado_solicitud;
        this.des_estado_solicitud = des_estado_solicitud;
        this.subestado_solicitud = subestado_solicitud;
        this.telefono_contacto = telefono_contacto;
        this.persona_contacto = persona_contacto;
        this.des_averia = des_averia;
        this.observaciones = observaciones;
        this.proveedor = proveedor;
        this.descripcion_cancelacion = descripcion_cancelacion;
        this.urgencia = urgencia;
        this.fecha_limite_visita = fecha_limite_visita;
        this.marca_caldera = marca_caldera;
        this.nombre_provincia = nombre_provincia;
        this.urgente = urgente;
        this.nombre_poblacion = nombre_poblacion;
        this.nom_calle = nom_calle;
        this.cod_portal = cod_portal;
        this.tip_piso = tip_piso;
        this.tip_mano = tip_mano;
        this.cod_postal = cod_postal;
        this.cups = cups;
        this.estado_ultima_visita = estado_ultima_visita;
        this.urgencia_ultima_visita = urgencia_ultima_visita;
        this.mant_gas_calefaccion = mant_gas_calefaccion;
        this.mant_gas = mant_gas;
        this.horario_contacto = horario_contacto;
        this.observaciones_visita = observaciones_visita;
        this.telefono_cliente = telefono_cliente;
        this.efv = efv;
        this.scoring = scoring;
        this.contador_averias = contador_averias;
        this.categoria_visita = categoria_visita;
        this.iniciado_android = iniciado_android;
        this.enviado_android = enviado_android;
        this.cerrado = cerrado;
        this.cerrado_error = cerrado_error;
        this.historico = historico;
    }

    public int getId_averia() {
        return id_averia;
    }
    public void setId_averia(int id_averia) {
        this.id_averia = id_averia;
    }
    public String getIdentificador_carga() {
        return identificador_carga;
    }
    public void setIdentificador_carga(String identificador_carga) {
        this.identificador_carga = identificador_carga;
    }
    public String getId_solicitud() {
        return id_solicitud;
    }
    public void setId_solicitud(String id_solicitud) {
        this.id_solicitud = id_solicitud;
    }
    public int getFk_tecnico() {
        return fk_tecnico;
    }
    public void setFk_tecnico(int fk_tecnico) {
        this.fk_tecnico = fk_tecnico;
    }
    public String getCod_contrato() {
        return cod_contrato;
    }
    public void setCod_contrato(String cod_contrato) {
        this.cod_contrato = cod_contrato;
    }
    public String getTipo_solicitud() {
        return tipo_solicitud;
    }
    public void setTipo_solicitud(String tipo_solicitud) {
        this.tipo_solicitud = tipo_solicitud;
    }
    public String getDes_tipo_solicitud() {
        return des_tipo_solicitud;
    }
    public void setDes_tipo_solicitud(String des_tipo_solicitud) {
        this.des_tipo_solicitud = des_tipo_solicitud;
    }
    public String getSubtipo_solicitud() {
        return subtipo_solicitud;
    }
    public void setSubtipo_solicitud(String subtipo_solicitud) {
        this.subtipo_solicitud = subtipo_solicitud;
    }
    public String getDes_subtipo_solicitud() {
        return des_subtipo_solicitud;
    }
    public void setDes_subtipo_solicitud(String des_subtipo_solicitud) {
        this.des_subtipo_solicitud = des_subtipo_solicitud;
    }
    public String getFecha_creacion() {
        return fecha_creacion;
    }
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    public String getFecha_cierre() {
        return fecha_cierre;
    }
    public void setFecha_cierre(String fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }
    public String getEstado_solicitud() {
        return estado_solicitud;
    }
    public void setEstado_solicitud(String estado_solicitud) {
        this.estado_solicitud = estado_solicitud;
    }
    public String getDes_estado_solicitud() {
        return des_estado_solicitud;
    }
    public void setDes_estado_solicitud(String des_estado_solicitud) {
        this.des_estado_solicitud = des_estado_solicitud;
    }
    public String getSubestado_solicitud() {
        return subestado_solicitud;
    }
    public void setSubestado_solicitud(String subestado_solicitud) {
        this.subestado_solicitud = subestado_solicitud;
    }
    public String getTelefono_contacto() {
        return telefono_contacto;
    }
    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }
    public String getPersona_contacto() {
        return persona_contacto;
    }
    public void setPersona_contacto(String persona_contacto) {
        this.persona_contacto = persona_contacto;
    }
    public String getDes_averia() {
        return des_averia;
    }
    public void setDes_averia(String des_averia) {
        this.des_averia = des_averia;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    public String getDescripcion_cancelacion() {
        return descripcion_cancelacion;
    }
    public void setDescripcion_cancelacion(String descripcion_cancelacion) {
        this.descripcion_cancelacion = descripcion_cancelacion;
    }
    public String getUrgencia() {
        return urgencia;
    }
    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }
    public String getFecha_limite_visita() {
        return fecha_limite_visita;
    }
    public void setFecha_limite_visita(String fecha_limite_visita) {
        this.fecha_limite_visita = fecha_limite_visita;
    }
    public String getMarca_caldera() {
        return marca_caldera;
    }
    public void setMarca_caldera(String marca_caldera) {
        this.marca_caldera = marca_caldera;
    }
    public String getNombre_provincia() {
        return nombre_provincia;
    }
    public void setNombre_provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }
    public String getUrgente() {
        return urgente;
    }
    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }
    public String getNombre_poblacion() {
        return nombre_poblacion;
    }
    public void setNombre_poblacion(String nombre_poblacion) {
        this.nombre_poblacion = nombre_poblacion;
    }
    public String getNom_calle() {
        return nom_calle;
    }
    public void setNom_calle(String nom_calle) {
        this.nom_calle = nom_calle;
    }
    public String getCod_portal() {
        return cod_portal;
    }
    public void setCod_portal(String cod_portal) {
        this.cod_portal = cod_portal;
    }
    public String getTip_piso() {
        return tip_piso;
    }
    public void setTip_piso(String tip_piso) {
        this.tip_piso = tip_piso;
    }
    public String getTip_mano() {
        return tip_mano;
    }
    public void setTip_mano(String tip_mano) {
        this.tip_mano = tip_mano;
    }
    public String getCod_postal() {
        return cod_postal;
    }
    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }
    public String getCups() {
        return cups;
    }
    public void setCups(String cups) {
        this.cups = cups;
    }
    public String getEstado_ultima_visita() {
        return estado_ultima_visita;
    }
    public void setEstado_ultima_visita(String estado_ultima_visita) {
        this.estado_ultima_visita = estado_ultima_visita;
    }
    public String getUrgencia_ultima_visita() {
        return urgencia_ultima_visita;
    }
    public void setUrgencia_ultima_visita(String urgencia_ultima_visita) {
        this.urgencia_ultima_visita = urgencia_ultima_visita;
    }
    public String getMant_gas_calefaccion() {
        return mant_gas_calefaccion;
    }
    public void setMant_gas_calefaccion(String mant_gas_calefaccion) {
        this.mant_gas_calefaccion = mant_gas_calefaccion;
    }
    public String getMant_gas() {
        return mant_gas;
    }
    public void setMant_gas(String mant_gas) {
        this.mant_gas = mant_gas;
    }
    public String getHorario_contacto() {
        return horario_contacto;
    }
    public void setHorario_contacto(String horario_contacto) {
        this.horario_contacto = horario_contacto;
    }
    public String getObservaciones_visita() {
        return observaciones_visita;
    }
    public void setObservaciones_visita(String observaciones_visita) {
        this.observaciones_visita = observaciones_visita;
    }
    public String getTelefono_cliente() {
        return telefono_cliente;
    }
    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }
    public String getEfv() {
        return efv;
    }
    public void setEfv(String efv) {
        this.efv = efv;
    }
    public String getScoring() {
        return scoring;
    }
    public void setScoring(String scoring) {
        this.scoring = scoring;
    }
    public String getContador_averias() {
        return contador_averias;
    }
    public void setContador_averias(String contador_averias) {
        this.contador_averias = contador_averias;
    }
    public String getCategoria_visita() {
        return categoria_visita;
    }
    public void setCategoria_visita(String categoria_visita) {
        this.categoria_visita = categoria_visita;
    }
    public String getIniciado_android() {
        return iniciado_android;
    }
    public void setIniciado_android(String iniciado_android) {
        this.iniciado_android = iniciado_android;
    }
    public String getEnviado_android() {
        return enviado_android;
    }
    public void setEnviado_android(String enviado_android) {
        this.enviado_android = enviado_android;
    }
    public String getCerrado() {
        return cerrado;
    }
    public void setCerrado(String cerrado) {
        this.cerrado = cerrado;
    }
    public String getCerrado_error() {
        return cerrado_error;
    }
    public void setCerrado_error(String cerrado_error) {
        this.cerrado_error = cerrado_error;
    }
    public String getHistorico() {
        return historico;
    }
    public void setHistorico(String historico) {
        this.historico = historico;
    }
}
