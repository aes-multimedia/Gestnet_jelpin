package com.multimedia.aes.gestnet_ssl.entidades;

/**
 * Created by acp on 01/09/2017.
 */


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "mos_formas_pago")
public class FormasPago {





    public static final String ID_FORMA_PAGO="id_forma_pago";
    public static final String FORMA_PAGO="forma_pago";
    public static final String FINANCIADO="financiado";
    public static final String MOSTRAR_CUENTA="mostrar_cuenta";
    public static final String SUMAR_DIAS="sumar_dias";
    public static final String B_APARECER_EN_INFORME="bAparecerEnInforme";
    public static final String MOSTRARCUENTA="mostrarcuenta";




    @DatabaseField(id=true,columnName = ID_FORMA_PAGO) private int id_forma_pago;
    @DatabaseField(columnName = FORMA_PAGO) private String forma_pago;
    @DatabaseField(columnName = FINANCIADO) private int financiado;
    @DatabaseField(columnName = MOSTRAR_CUENTA) private  boolean mostrar_cuenta;
    @DatabaseField(columnName = SUMAR_DIAS) private boolean sumar_dias;
    @DatabaseField(columnName = B_APARECER_EN_INFORME) private boolean bAparecerEnInforme;
    @DatabaseField(columnName = MOSTRARCUENTA) private boolean mostrarcuenta;



    public FormasPago(){}


    public FormasPago(int id_forma_pago, String forma_pago, int financiado, boolean mostrar_cuenta, boolean sumar_dias, boolean bAparecerEnInforme, boolean mostrarcuenta) {
        this.id_forma_pago = id_forma_pago;
        this.forma_pago = forma_pago;
        this.financiado = financiado;
        this.mostrar_cuenta = mostrar_cuenta;
        this.sumar_dias = sumar_dias;
        this.bAparecerEnInforme = bAparecerEnInforme;
        this.mostrarcuenta = mostrarcuenta;
    }

    public int getId_forma_pago() {
        return id_forma_pago;
    }

    public void setId_forma_pago(int id_forma_pago) {
        this.id_forma_pago = id_forma_pago;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public int getFinanciado() {
        return financiado;
    }

    public void setFinanciado(int financiado) {
        this.financiado = financiado;
    }

    public boolean isMostrar_cuenta() {
        return mostrar_cuenta;
    }

    public void setMostrar_cuenta(boolean mostrar_cuenta) {
        this.mostrar_cuenta = mostrar_cuenta;
    }

    public boolean isSumar_dias() {
        return sumar_dias;
    }

    public void setSumar_dias(boolean sumar_dias) {
        this.sumar_dias = sumar_dias;
    }

    public boolean isbAparecerEnInforme() {
        return bAparecerEnInforme;
    }

    public void setbAparecerEnInforme(boolean bAparecerEnInforme) {
        this.bAparecerEnInforme = bAparecerEnInforme;
    }

    public boolean isMostrarcuenta() {
        return mostrarcuenta;
    }

    public void setMostrarcuenta(boolean mostrarcuenta) {
        this.mostrarcuenta = mostrarcuenta;
    }


}
