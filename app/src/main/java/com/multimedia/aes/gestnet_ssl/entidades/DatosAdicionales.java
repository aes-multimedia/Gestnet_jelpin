package com.multimedia.aes.gestnet_ssl.entidades;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by acp on 21/08/2017.
 */
@DatabaseTable(tableName = "mos_datos_adicionales")
public class DatosAdicionales {


    public static final String ID_REL="_id_rel";
    public static final String FK_PARTE="fk_parte";
    public static final String FK_FORMA_PAGO="fk_forma_pago";
    public static final String SINTOMAS_AVERIA="sintomas_averia";
    public static final String OPERACION_EFECTUADA="operacion_efectuada";
    public static final String OBSERVACIONES="observaciones";
    public static final String PREEU_DISPOSICION_SERVICIO_SI_NO="preeu_disposicion_servicio_si_no";
    public static final String PREEU_DISPOSICION_SERVICIO="preeu_disposicion_servicio";
    public static final String PREEU_MANO_DE_OBRA_SI_NO="preeu_mano_de_obra_si_no";
    public static final String PREEU_MANO_DE_OBRA_PRECIO="preeu_mano_de_obra_precio";
    public static final String PREEU_MANO_DE_OBRA="preeu_mano_de_obra";
    public static final String PREEU_MATERIALES_SI_NO="preeu_materiales_si_no";
    public static final String PREEU_MATERIALES="preeu_materiales";
    public static final String PREEU_PUESTA_MARCHA_SI_NO="preeu_puesta_marcha_si_no";
    public static final String PREEU_PUESTA_MARCHA="preeu_puesta_marcha";
    public static final String PREEU_SERVICIO_URGENCIA_SI_NO="preeu_servicio_urgencia_si_no";
    public static final String PREEU_SERVICIO_URGENCIA="preeu_servicio_urgencia";
    public static final String PREEU_KM_SI_NO="preeu_km_si_no";
    public static final String PREEU_KM_PRECIO="preeu_km_precio";
    public static final String PREEU_KM="preeu_km";
    public static final String PREEU_KM_PRECIO_TOTAL="preeu_km_precio_total";
    public static final String PREEU_ANALISIS_COMBUSTION="preeu_analisis_combustion";
    public static final String PREEU_ADICIONAL="preeu_adicional";
    public static final String PREEU_ADICIONAL_COSTE="preeu_adicional_coste";
    public static final String PREEU_OTROS_SI_NO="preeu_otros_si_no";
    public static final String PREEU_OTROS_NOMBRE="preeu_otros_nombre";
    public static final String PREEU_ADICIONAL_COSTE_NOMBRE="preeu_adicional_coste_nombre";
    public static final String PREEU_IVA_APLICADO="preeu_iva_aplicado";
    public static final String TOTAL_PPTO="total_ppto";
    public static final String BACEPTAPRESUPUESTO="baceptapresupuesto";
    public static final String CTRLGAS_RENCAL_PORCO2="ctrlgas_rencal_porco2";
    public static final String CTRLGAS_RENCAL_PORO2="ctrlgas_rencal_poro2";
    public static final String CTRLGAS_RENCAL_PPMCOCORREG="ctrlgas_rencal_ppmcocorreg";
    public static final String CTRLGAS_RENCAL_THUMOSGRADOS="ctrlgas_rencal_thumosgrados";
    public static final String CTRLGAS_RENCAL_TAMBIENTEGRADOS="ctrlgas_rencal_tambientegrados";
    public static final String CTRLGAS_RENCAL_POREXCAIRE="ctrlgas_rencal_porexcaire";
    public static final String CTRLGAS_RENCAL_PORRENDIMIENTO="ctrlgas_rencal_porrendimiento";
    public static final String REGQUE_INYECTOR="regque_inyector";
    public static final String REGQUE_AIREPRIMARIO="regque_aireprimario";
    public static final String REGQUE_PRESIONBOMBA="regque_presionbomba";
    public static final String REGQUE_AIRE_LINEA="regque_aire_linea";
    public static final String BCONTROLARAIREVENTILACION="bcontrolaraireventilacion";



    public static final String FACT_MATERIALES="fact_materiales";
    public static final String FACT_DISPOSICION_SERVICIO="fact_disposicion_servicio";
    public static final String FACT_MANODEOBRA_PRECIO="fact_manodeobra_precio";
    public static final String FACT_MANODEOBRA="fact_manodeobra";
    public static final String FACT_PUESTA_EN_MARCHA="fact_puesta_en_marcha";
    public static final String FACT_ANALISIS_COMBUSTION="fact_analisis_combustion";
    public static final String FACT_SERVICIO_URGENCIA="fact_servicio_urgencia";
    public static final String FACT_KM="fact_km";
    public static final String FACT_KM_PRECIO="fact_km_precio";
    public static final String FACT_KM_PRECIO_TOTAL="fact_km_precio_total";
    public static final String FACT_ADICIONAL="fact_adicional";
    public static final String FACT_ADICIONAL_COSTE="fact_adicional_coste";
    public static final String FACT_OTROS_NOMBRE="fact_otros_nombre";
    public static final String FACT_ADICIONAL_COSTE_NOMBRE="fact_adicional_coste_nombre";
    public static final String FACT_POR_IVA_APLICADO="fact_por_iva_aplicado";
    public static final String FACT_TOTAL_CON_IVA="fact_total_con_iva";



    public static final String RETENCION="retencion";
    public static final String RETENCION_PORC="retencion_porc";
    public static final String MATEM_HORA_ENTRADA="matem_hora_entrada";
    public static final String MATEM_HORA_SALIDA="matem_hora_salida";
    public static final String OBSERVACIONESPAGO="observacionespago";
    public static final String COBRAR_ANALISIS_COMBUSTION="cobrar_analisis_combustion";


    @DatabaseField(id=true,columnName = ID_REL) private int id_rel;
    @DatabaseField(columnName = FK_PARTE) private int fk_parte;
    @DatabaseField(columnName = FK_FORMA_PAGO) private int fk_forma_pago;
    @DatabaseField(columnName = SINTOMAS_AVERIA) private String sintomas_averia;
    @DatabaseField(columnName = OPERACION_EFECTUADA) private String operacion_efectuada;
    @DatabaseField(columnName = OBSERVACIONES) private String observaciones;
    @DatabaseField(columnName = PREEU_DISPOSICION_SERVICIO_SI_NO) private boolean preeu_disposicion_servicio_si_no;
    @DatabaseField(columnName = PREEU_DISPOSICION_SERVICIO) private double preeu_disposicion_servicio;
    @DatabaseField(columnName = PREEU_MANO_DE_OBRA_SI_NO) private boolean preeu_mano_de_obra_si_no;
    @DatabaseField(columnName = PREEU_MANO_DE_OBRA_PRECIO) private double preeu_mano_de_obra_precio;
    @DatabaseField(columnName = PREEU_MANO_DE_OBRA) private double preeu_mano_de_obra;
    @DatabaseField(columnName = PREEU_MATERIALES_SI_NO) private boolean preeu_materiales_si_no;
    @DatabaseField(columnName = PREEU_MATERIALES) private double preeu_materiales;
    @DatabaseField(columnName = PREEU_PUESTA_MARCHA_SI_NO) private boolean preeu_puesta_marcha_si_no;
    @DatabaseField(columnName = PREEU_PUESTA_MARCHA) private double preeu_puesta_marcha;
    @DatabaseField(columnName = PREEU_SERVICIO_URGENCIA_SI_NO) private boolean preeu_servicio_urgencia_si_no;
    @DatabaseField(columnName = PREEU_SERVICIO_URGENCIA) private double preeu_servicio_urgencia;

    @DatabaseField(columnName = PREEU_KM_SI_NO) private boolean preeu_km_si_no;

    @DatabaseField(columnName = PREEU_KM_PRECIO) private double preeu_km_precio;

    @DatabaseField(columnName = PREEU_KM) private double preeu_km;

    @DatabaseField(columnName = PREEU_KM_PRECIO_TOTAL) private double preeu_km_precio_total;

    @DatabaseField(columnName = PREEU_ANALISIS_COMBUSTION) private double preeu_analisis_combustion;

    @DatabaseField(columnName = PREEU_ADICIONAL) private double preeu_adicional;

    @DatabaseField(columnName = PREEU_ADICIONAL_COSTE) private double preeu_adicional_coste;

    @DatabaseField(columnName = PREEU_OTROS_SI_NO) private boolean preeu_otros_si_no;

    @DatabaseField(columnName = PREEU_OTROS_NOMBRE) private String preeu_otros_nombre;

    @DatabaseField(columnName = PREEU_ADICIONAL_COSTE_NOMBRE) private String preeu_adicional_coste_nombre;

    @DatabaseField(columnName = PREEU_IVA_APLICADO) private double preeu_iva_aplicado;

    @DatabaseField(columnName = TOTAL_PPTO) private  double total_ppto;

    @DatabaseField(columnName = BACEPTAPRESUPUESTO) private boolean baceptapresupuesto;

    @DatabaseField(columnName = CTRLGAS_RENCAL_PORCO2) private String ctrlgas_rencal_porco2;

    @DatabaseField(columnName = CTRLGAS_RENCAL_PORO2) private String ctrlgas_rencal_poro2;

    @DatabaseField(columnName = CTRLGAS_RENCAL_PPMCOCORREG) private String ctrlgas_rencal_ppmcocorreg;

    @DatabaseField(columnName = CTRLGAS_RENCAL_THUMOSGRADOS) private String ctrlgas_rencal_thumosgrados;

    @DatabaseField(columnName = CTRLGAS_RENCAL_TAMBIENTEGRADOS) private String ctrlgas_rencal_tambientegrados;

    @DatabaseField(columnName = CTRLGAS_RENCAL_POREXCAIRE) private String ctrlgas_rencal_porexcaire;

    @DatabaseField(columnName = CTRLGAS_RENCAL_PORRENDIMIENTO) private String ctrlgas_rencal_porrendimiento;

    @DatabaseField(columnName = REGQUE_INYECTOR) private String regque_inyector;

    @DatabaseField(columnName = REGQUE_AIREPRIMARIO) private String regque_aireprimario;

    @DatabaseField(columnName = REGQUE_PRESIONBOMBA) private String regque_presionbomba;

    @DatabaseField(columnName = REGQUE_AIRE_LINEA) private String regque_aire_linea;

    @DatabaseField(columnName = BCONTROLARAIREVENTILACION) private boolean bcontrolaraireventilacion;

    @DatabaseField(columnName = FACT_MATERIALES) private double fact_materiales;

    @DatabaseField(columnName = FACT_DISPOSICION_SERVICIO) private double fact_disposicion_servicio;

    @DatabaseField(columnName = FACT_MANODEOBRA_PRECIO) private double fact_manodeobra_precio;

    @DatabaseField(columnName = FACT_MANODEOBRA) private double fact_manodeobra;

    @DatabaseField(columnName = FACT_PUESTA_EN_MARCHA) private double fact_puesta_en_marcha;

    @DatabaseField(columnName = FACT_ANALISIS_COMBUSTION) private double fact_analisis_combustion;

    @DatabaseField(columnName = FACT_SERVICIO_URGENCIA) private double fact_servicio_urgencia;

    @DatabaseField(columnName = FACT_KM) private double fact_km;

    @DatabaseField(columnName = FACT_KM_PRECIO) private double fact_km_precio;

    @DatabaseField(columnName = FACT_KM_PRECIO_TOTAL) private double fact_km_precio_total;

    @DatabaseField(columnName = FACT_ADICIONAL) private double fact_adicional;

    @DatabaseField(columnName = FACT_ADICIONAL_COSTE) private double fact_adicional_coste;

    @DatabaseField(columnName = FACT_OTROS_NOMBRE) private String fact_otros_nombre;

    @DatabaseField(columnName = FACT_ADICIONAL_COSTE_NOMBRE) private String fact_adicional_coste_nombre;

    @DatabaseField(columnName = FACT_POR_IVA_APLICADO) private double fact_por_iva_aplicado;
    @DatabaseField(columnName = FACT_TOTAL_CON_IVA) private double fact_total_con_iva;
    @DatabaseField(columnName = RETENCION) private double retencion;

    @DatabaseField(columnName = RETENCION_PORC) private double retencion_porc;

    @DatabaseField(columnName = MATEM_HORA_ENTRADA) private String matem_hora_entrada;

    @DatabaseField(columnName = MATEM_HORA_SALIDA) private String matem_hora_salida;

    @DatabaseField(columnName = OBSERVACIONESPAGO) private String observacionespago;

    @DatabaseField(columnName = COBRAR_ANALISIS_COMBUSTION) private boolean cobrar_analisis_combustion;


    public DatosAdicionales(){}

    public DatosAdicionales(int id_rel, int fk_parte, int fk_forma_pago, String sintomas_averia, String operacion_efectuada,
                            String observaciones, boolean preeu_disposicion_servicio_si_no, double preeu_disposicion_servicio,
                            boolean preeu_mano_de_obra_si_no, double preeu_mano_de_obra_precio, double preeu_mano_de_obra,
                            boolean preeu_materiales_si_no, double preeu_materiales, boolean preeu_puesta_marcha_si_no,
                            double preeu_puesta_marcha, boolean preeu_servicio_urgencia_si_no, double preeu_servicio_urgencia,
                            boolean preeu_km_si_no, double preeu_km_precio, double preeu_km, double preeu_km_precio_total,
                            double preeu_analisis_combustion, double preeu_adicional, double preeu_adicional_coste, boolean preeu_otros_si_no,
                            String preeu_otros_nombre, String preeu_adicional_coste_nombre, double preeu_iva_aplicado,
                            double total_ppto, boolean baceptapresupuesto, String ctrlgas_rencal_porco2, String ctrlgas_rencal_poro2,
                            String ctrlgas_rencal_ppmcocorreg, String ctrlgas_rencal_thumosgrados, String ctrlgas_rencal_tambientegrados,
                            String ctrlgas_rencal_porexcaire, String ctrlgas_rencal_porrendimiento, String regque_inyector,
                            String regque_aireprimario, String regque_presionbomba, String regque_aire_linea, boolean bcontrolaraireventilacion,
                            double fact_materiales, double fact_disposicion_servicio, double fact_manodeobra_precio,
                            double fact_manodeobra, double fact_puesta_en_marcha, double fact_analisis_combustion, double fact_servicio_urgencia,
                            double fact_km, double fact_km_precio, double fact_km_precio_total, double fact_adicional,
                            double fact_adicional_coste, String fact_otros_nombre, String fact_adicional_coste_nombre, double fact_por_iva_aplicado,
                            double fact_total_con_iva, double retencion, double retencion_porc, String matem_hora_entrada,
                            String matem_hora_salida, String observacionespago, boolean cobrar_analisis_combustion) {
        this.id_rel = id_rel;
        this.fk_parte = fk_parte;
        this.fk_forma_pago = fk_forma_pago;
        this.sintomas_averia = sintomas_averia;
        this.operacion_efectuada = operacion_efectuada;
        this.observaciones = observaciones;
        this.preeu_disposicion_servicio_si_no = preeu_disposicion_servicio_si_no;
        this.preeu_disposicion_servicio = preeu_disposicion_servicio;
        this.preeu_mano_de_obra_si_no = preeu_mano_de_obra_si_no;
        this.preeu_mano_de_obra_precio = preeu_mano_de_obra_precio;
        this.preeu_mano_de_obra = preeu_mano_de_obra;
        this.preeu_materiales_si_no = preeu_materiales_si_no;
        this.preeu_materiales = preeu_materiales;
        this.preeu_puesta_marcha_si_no = preeu_puesta_marcha_si_no;
        this.preeu_puesta_marcha = preeu_puesta_marcha;
        this.preeu_servicio_urgencia_si_no = preeu_servicio_urgencia_si_no;
        this.preeu_servicio_urgencia = preeu_servicio_urgencia;
        this.preeu_km_si_no = preeu_km_si_no;
        this.preeu_km_precio = preeu_km_precio;
        this.preeu_km = preeu_km;
        this.preeu_km_precio_total = preeu_km_precio_total;
        this.preeu_analisis_combustion = preeu_analisis_combustion;
        this.preeu_adicional = preeu_adicional;
        this.preeu_adicional_coste = preeu_adicional_coste;
        this.preeu_otros_si_no = preeu_otros_si_no;
        this.preeu_otros_nombre = preeu_otros_nombre;
        this.preeu_adicional_coste_nombre = preeu_adicional_coste_nombre;
        this.preeu_iva_aplicado = preeu_iva_aplicado;
        this.total_ppto = total_ppto;
        this.baceptapresupuesto = baceptapresupuesto;
        this.ctrlgas_rencal_porco2 = ctrlgas_rencal_porco2;
        this.ctrlgas_rencal_poro2 = ctrlgas_rencal_poro2;
        this.ctrlgas_rencal_ppmcocorreg = ctrlgas_rencal_ppmcocorreg;
        this.ctrlgas_rencal_thumosgrados = ctrlgas_rencal_thumosgrados;
        this.ctrlgas_rencal_tambientegrados = ctrlgas_rencal_tambientegrados;
        this.ctrlgas_rencal_porexcaire = ctrlgas_rencal_porexcaire;
        this.ctrlgas_rencal_porrendimiento = ctrlgas_rencal_porrendimiento;
        this.regque_inyector = regque_inyector;
        this.regque_aireprimario = regque_aireprimario;
        this.regque_presionbomba = regque_presionbomba;
        this.regque_aire_linea = regque_aire_linea;
        this.bcontrolaraireventilacion = bcontrolaraireventilacion;
        this.fact_materiales = fact_materiales;
        this.fact_disposicion_servicio = fact_disposicion_servicio;
        this.fact_manodeobra_precio = fact_manodeobra_precio;
        this.fact_manodeobra = fact_manodeobra;
        this.fact_puesta_en_marcha = fact_puesta_en_marcha;
        this.fact_analisis_combustion = fact_analisis_combustion;
        this.fact_servicio_urgencia = fact_servicio_urgencia;
        this.fact_km = fact_km;
        this.fact_km_precio = fact_km_precio;
        this.fact_km_precio_total = fact_km_precio_total;
        this.fact_adicional = fact_adicional;
        this.fact_adicional_coste = fact_adicional_coste;
        this.fact_otros_nombre = fact_otros_nombre;
        this.fact_adicional_coste_nombre = fact_adicional_coste_nombre;
        this.fact_por_iva_aplicado = fact_por_iva_aplicado;
        this.fact_total_con_iva = fact_total_con_iva;
        this.retencion = retencion;
        this.retencion_porc = retencion_porc;
        this.matem_hora_entrada = matem_hora_entrada;
        this.matem_hora_salida = matem_hora_salida;
        this.observacionespago = observacionespago;
        this.cobrar_analisis_combustion = cobrar_analisis_combustion;
    }





    public int getId_rel() {
        return id_rel;
    }


    public int getFk_parte() {
        return fk_parte;
    }

    public void setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
    }

    public int getFk_forma_pago() {
        return fk_forma_pago;
    }

    public void setFk_forma_pago(int fk_forma_pago) {
        this.fk_forma_pago = fk_forma_pago;
    }

    public String getSintomas_averia() {
        return sintomas_averia;
    }

    public void setSintomas_averia(String sintomas_averia) {
        this.sintomas_averia = sintomas_averia;
    }

    public String getOperacion_efectuada() {
        return operacion_efectuada;
    }

    public void setOperacion_efectuada(String operacion_efectuada) {
        this.operacion_efectuada = operacion_efectuada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isPreeu_disposicion_servicio_si_no() {
        return preeu_disposicion_servicio_si_no;
    }

    public boolean isPreeu_mano_de_obra_si_no() {
        return preeu_mano_de_obra_si_no;
    }

    public boolean isPreeu_materiales_si_no() {
        return preeu_materiales_si_no;
    }

    public boolean isPreeu_puesta_marcha_si_no() {
        return preeu_puesta_marcha_si_no;
    }

    public boolean isPreeu_servicio_urgencia_si_no() {
        return preeu_servicio_urgencia_si_no;
    }

    public boolean isPreeu_km_si_no() {
        return preeu_km_si_no;
    }

    public boolean isPreeu_otros_si_no() {
        return preeu_otros_si_no;
    }

    public boolean isBaceptapresupuesto() {
        return baceptapresupuesto;
    }

    public boolean isBcontrolaraireventilacion() {
        return bcontrolaraireventilacion;
    }

    public boolean isCobrar_analisis_combustion() {
        return cobrar_analisis_combustion;
    }

    public void setId_rel(int id_rel) {
        this.id_rel = id_rel;
    }

    public boolean getPreeu_disposicion_servicio_si_no() {
        return preeu_disposicion_servicio_si_no;
    }

    public void setPreeu_disposicion_servicio_si_no(boolean preeu_disposicion_servicio_si_no) {
        this.preeu_disposicion_servicio_si_no = preeu_disposicion_servicio_si_no;
    }

    public double getPreeu_disposicion_servicio() {
        return preeu_disposicion_servicio;
    }

    public void setPreeu_disposicion_servicio(double preeu_disposicion_servicio) {
        this.preeu_disposicion_servicio = preeu_disposicion_servicio;
    }

    public boolean getPreeu_mano_de_obra_si_no() {
        return preeu_mano_de_obra_si_no;
    }

    public void setPreeu_mano_de_obra_si_no(boolean preeu_mano_de_obra_si_no) {
        this.preeu_mano_de_obra_si_no = preeu_mano_de_obra_si_no;
    }

    public double getPreeu_mano_de_obra_precio() {
        return preeu_mano_de_obra_precio;
    }

    public void setPreeu_mano_de_obra_precio(double preeu_mano_de_obra_precio) {
        this.preeu_mano_de_obra_precio = preeu_mano_de_obra_precio;
    }

    public double getPreeu_mano_de_obra() {
        return preeu_mano_de_obra;
    }

    public void setPreeu_mano_de_obra(double preeu_mano_de_obra) {
        this.preeu_mano_de_obra = preeu_mano_de_obra;
    }

    public boolean getPreeu_materiales_si_no() {
        return preeu_materiales_si_no;
    }

    public void setPreeu_materiales_si_no(boolean preeu_materiales_si_no) {
        this.preeu_materiales_si_no = preeu_materiales_si_no;
    }

    public double getPreeu_materiales() {
        return preeu_materiales;
    }

    public void setPreeu_materiales(double preeu_materiales) {
        this.preeu_materiales = preeu_materiales;
    }

    public boolean getPreeu_puesta_marcha_si_no() {
        return preeu_puesta_marcha_si_no;
    }

    public void setPreeu_puesta_marcha_si_no(boolean preeu_puesta_marcha_si_no) {
        this.preeu_puesta_marcha_si_no = preeu_puesta_marcha_si_no;
    }

    public double getPreeu_puesta_marcha() {
        return preeu_puesta_marcha;
    }

    public void setPreeu_puesta_marcha(double preeu_puesta_marcha) {
        this.preeu_puesta_marcha = preeu_puesta_marcha;
    }

    public boolean getPreeu_servicio_urgencia_si_no() {
        return preeu_servicio_urgencia_si_no;
    }

    public void setPreeu_servicio_urgencia_si_no(boolean preeu_servicio_urgencia_si_no) {
        this.preeu_servicio_urgencia_si_no = preeu_servicio_urgencia_si_no;
    }

    public double getPreeu_servicio_urgencia() {
        return preeu_servicio_urgencia;
    }

    public void setPreeu_servicio_urgencia(double preeu_servicio_urgencia) {
        this.preeu_servicio_urgencia = preeu_servicio_urgencia;
    }

    public boolean getPreeu_km_si_no() {
        return preeu_km_si_no;
    }

    public void setPreeu_km_si_no(boolean preeu_km_si_no) {
        this.preeu_km_si_no = preeu_km_si_no;
    }

    public double getPreeu_km_precio() {
        return preeu_km_precio;
    }

    public void setPreeu_km_precio(double preeu_km_precio) {
        this.preeu_km_precio = preeu_km_precio;
    }

    public double getPreeu_km() {
        return preeu_km;
    }

    public void setPreeu_km(double preeu_km) {
        this.preeu_km = preeu_km;
    }

    public double getPreeu_km_precio_total() {
        return preeu_km_precio_total;
    }

    public void setPreeu_km_precio_total(double preeu_km_precio_total) {
        this.preeu_km_precio_total = preeu_km_precio_total;
    }

    public double getPreeu_analisis_combustion() {
        return preeu_analisis_combustion;
    }

    public void setPreeu_analisis_combustion(double preeu_analisis_combustion) {
        this.preeu_analisis_combustion = preeu_analisis_combustion;
    }

    public double getPreeu_adicional() {
        return preeu_adicional;
    }

    public void setPreeu_adicional(double preeu_adicional) {
        this.preeu_adicional = preeu_adicional;
    }

    public double getPreeu_adicional_coste() {
        return preeu_adicional_coste;
    }

    public void setPreeu_adicional_coste(double preeu_adicional_coste) {
        this.preeu_adicional_coste = preeu_adicional_coste;
    }

    public boolean getPreeu_otros_si_no() {
        return preeu_otros_si_no;
    }

    public void setPreeu_otros_si_no(boolean preeu_otros_si_no) {
        this.preeu_otros_si_no = preeu_otros_si_no;
    }

    public String getPreeu_otros_nombre() {
        return preeu_otros_nombre;
    }

    public void setPreeu_otros_nombre(String preeu_otros_nombre) {
        this.preeu_otros_nombre = preeu_otros_nombre;
    }

    public String getPreeu_adicional_coste_nombre() {
        return preeu_adicional_coste_nombre;
    }

    public void setPreeu_adicional_coste_nombre(String preeu_adicional_coste_nombre) {
        this.preeu_adicional_coste_nombre = preeu_adicional_coste_nombre;
    }

    public double getPreeu_iva_aplicado() {
        return preeu_iva_aplicado;
    }

    public void setPreeu_iva_aplicado(double preeu_iva_aplicado) {
        this.preeu_iva_aplicado = preeu_iva_aplicado;
    }

    public double getTotal_ppto() {
        return total_ppto;
    }

    public void setTotal_ppto(double total_ppto) {
        this.total_ppto = total_ppto;
    }

    public boolean getBaceptapresupuesto() {
        return baceptapresupuesto;
    }

    public void setBaceptapresupuesto(boolean baceptapresupuesto) {
        this.baceptapresupuesto = baceptapresupuesto;
    }

    public String getCtrlgas_rencal_porco2() {
        return ctrlgas_rencal_porco2;
    }

    public void setCtrlgas_rencal_porco2(String ctrlgas_rencal_porco2) {
        this.ctrlgas_rencal_porco2 = ctrlgas_rencal_porco2;
    }

    public String getCtrlgas_rencal_poro2() {
        return ctrlgas_rencal_poro2;
    }

    public void setCtrlgas_rencal_poro2(String ctrlgas_rencal_poro2) {
        this.ctrlgas_rencal_poro2 = ctrlgas_rencal_poro2;
    }

    public String getCtrlgas_rencal_ppmcocorreg() {
        return ctrlgas_rencal_ppmcocorreg;
    }

    public void setCtrlgas_rencal_ppmcocorreg(String ctrlgas_rencal_ppmcocorreg) {
        this.ctrlgas_rencal_ppmcocorreg = ctrlgas_rencal_ppmcocorreg;
    }

    public String getCtrlgas_rencal_thumosgrados() {
        return ctrlgas_rencal_thumosgrados;
    }

    public void setCtrlgas_rencal_thumosgrados(String ctrlgas_rencal_thumosgrados) {
        this.ctrlgas_rencal_thumosgrados = ctrlgas_rencal_thumosgrados;
    }

    public String getCtrlgas_rencal_tambientegrados() {
        return ctrlgas_rencal_tambientegrados;
    }

    public void setCtrlgas_rencal_tambientegrados(String ctrlgas_rencal_tambientegrados) {
        this.ctrlgas_rencal_tambientegrados = ctrlgas_rencal_tambientegrados;
    }

    public String getCtrlgas_rencal_porexcaire() {
        return ctrlgas_rencal_porexcaire;
    }

    public void setCtrlgas_rencal_porexcaire(String ctrlgas_rencal_porexcaire) {
        this.ctrlgas_rencal_porexcaire = ctrlgas_rencal_porexcaire;
    }

    public String getCtrlgas_rencal_porrendimiento() {
        return ctrlgas_rencal_porrendimiento;
    }

    public void setCtrlgas_rencal_porrendimiento(String ctrlgas_rencal_porrendimiento) {
        this.ctrlgas_rencal_porrendimiento = ctrlgas_rencal_porrendimiento;
    }

    public String getRegque_inyector() {
        return regque_inyector;
    }

    public void setRegque_inyector(String regque_inyector) {
        this.regque_inyector = regque_inyector;
    }

    public String getRegque_aireprimario() {
        return regque_aireprimario;
    }

    public void setRegque_aireprimario(String regque_aireprimario) {
        this.regque_aireprimario = regque_aireprimario;
    }

    public String getRegque_presionbomba() {
        return regque_presionbomba;
    }

    public void setRegque_presionbomba(String regque_presionbomba) {
        this.regque_presionbomba = regque_presionbomba;
    }

    public String getRegque_aire_linea() {
        return regque_aire_linea;
    }

    public void setRegque_aire_linea(String regque_aire_linea) {
        this.regque_aire_linea = regque_aire_linea;
    }

    public boolean getBcontrolaraireventilacion() {
        return bcontrolaraireventilacion;
    }

    public void setBcontrolaraireventilacion(boolean bcontrolaraireventilacion) {
        this.bcontrolaraireventilacion = bcontrolaraireventilacion;
    }

    public double getFact_materiales() {
        return fact_materiales;
    }

    public void setFact_materiales(double fact_materiales) {
        this.fact_materiales = fact_materiales;
    }

    public double getFact_disposicion_servicio() {
        return fact_disposicion_servicio;
    }

    public void setFact_disposicion_servicio(double fact_disposicion_servicio) {
        this.fact_disposicion_servicio = fact_disposicion_servicio;
    }

    public double getFact_manodeobra_precio() {
        return fact_manodeobra_precio;
    }

    public void setFact_manodeobra_precio(double fact_manodeobra_precio) {
        this.fact_manodeobra_precio = fact_manodeobra_precio;
    }

    public double getFact_manodeobra() {
        return fact_manodeobra;
    }

    public void setFact_manodeobra(double fact_manodeobra) {
        this.fact_manodeobra = fact_manodeobra;
    }

    public double getFact_puesta_en_marcha() {
        return fact_puesta_en_marcha;
    }

    public void setFact_puesta_en_marcha(double fact_puesta_en_marcha) {
        this.fact_puesta_en_marcha = fact_puesta_en_marcha;
    }

    public double getFact_analisis_combustion() {
        return fact_analisis_combustion;
    }

    public void setFact_analisis_combustion(double fact_analisis_combustion) {
        this.fact_analisis_combustion = fact_analisis_combustion;
    }

    public double getFact_servicio_urgencia() {
        return fact_servicio_urgencia;
    }

    public void setFact_servicio_urgencia(double fact_servicio_urgencia) {
        this.fact_servicio_urgencia = fact_servicio_urgencia;
    }

    public double getFact_km() {
        return fact_km;
    }

    public void setFact_km(double fact_km) {
        this.fact_km = fact_km;
    }

    public double getFact_km_precio() {
        return fact_km_precio;
    }

    public void setFact_km_precio(double fact_km_precio) {
        this.fact_km_precio = fact_km_precio;
    }

    public double getFact_km_precio_total() {
        return fact_km_precio_total;
    }

    public void setFact_km_precio_total(double fact_km_precio_total) {
        this.fact_km_precio_total = fact_km_precio_total;
    }

    public double getFact_adicional() {
        return fact_adicional;
    }

    public void setFact_adicional(double fact_adicional) {
        this.fact_adicional = fact_adicional;
    }

    public double getFact_adicional_coste() {
        return fact_adicional_coste;
    }

    public void setFact_adicional_coste(double fact_adicional_coste) {
        this.fact_adicional_coste = fact_adicional_coste;
    }

    public String getFact_otros_nombre() {
        return fact_otros_nombre;
    }

    public void setFact_otros_nombre(String fact_otros_nombre) {
        this.fact_otros_nombre = fact_otros_nombre;
    }

    public String getFact_adicional_coste_nombre() {
        return fact_adicional_coste_nombre;
    }

    public void setFact_adicional_coste_nombre(String fact_adicional_coste_nombre) {
        this.fact_adicional_coste_nombre = fact_adicional_coste_nombre;
    }

    public double getFact_por_iva_aplicado() {
        return fact_por_iva_aplicado;
    }

    public void setFact_por_iva_aplicado(double fact_por_iva_aplicado) {
        this.fact_por_iva_aplicado = fact_por_iva_aplicado;
    }

    public double getFact_total_con_iva() {
        return fact_total_con_iva;
    }

    public void setFact_total_con_iva(double fact_total_con_iva) {
        this.fact_total_con_iva = fact_total_con_iva;
    }

    public double getRetencion() {
        return retencion;
    }

    public void setRetencion(double retencion) {
        this.retencion = retencion;
    }

    public double getRetencion_porc() {
        return retencion_porc;
    }

    public void setRetencion_porc(double retencion_porc) {
        this.retencion_porc = retencion_porc;
    }

    public String getMatem_hora_entrada() {
        return matem_hora_entrada;
    }

    public void setMatem_hora_entrada(String matem_hora_entrada) {
        this.matem_hora_entrada = matem_hora_entrada;
    }

    public String getMatem_hora_salida() {
        return matem_hora_salida;
    }

    public void setMatem_hora_salida(String matem_hora_salida) {
        this.matem_hora_salida = matem_hora_salida;
    }

    public String getObservacionespago() {
        return observacionespago;
    }

    public void setObservacionespago(String observacionespago) {
        this.observacionespago = observacionespago;
    }

    public boolean getCobrar_analisis_combustion() {
        return cobrar_analisis_combustion;
    }

    public void setCobrar_analisis_combustion(boolean cobrar_analisis_combustion) {
        this.cobrar_analisis_combustion = cobrar_analisis_combustion;
    }
}
