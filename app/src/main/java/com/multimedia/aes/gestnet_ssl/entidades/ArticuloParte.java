package com.multimedia.aes.gestnet_ssl.entidades;

import com.j256.ormlite.field.DatabaseField;


public class ArticuloParte {



    public static final String ID = "_id";
    public static final String FK_ARTICULO = "fk_articulo";
    public static final String FK_PARTE = "fk_parte";
    public static final String FK_ITEM_GESTNET = "fk_item_gestnet";
    public static final String USADOS = "usados";
    public static final String GARANTIA = "garantia";
    public static final String ENTREGADO ="entregado";
    public static final String PRESUPUESTAR="presupuestar";
    public static final String FACTURAR = "facturar";
    public static final String IVA = "iva";
    public static final String TARIFA = "tarifa";
    public static final String DESCUENTO = "descuento";
    public static final String COSTE = "coste";
    public static final String N_SERIE = "n_serie";



    @DatabaseField(generatedId = true, columnName = ID)     private int id;
    @DatabaseField(columnName = FK_ARTICULO)                private int fk_articulo;
    @DatabaseField(columnName = FK_PARTE)                   private int fk_parte;
    @DatabaseField(columnName = FK_ITEM_GESTNET)            private int fk_item_gestnet;
    @DatabaseField(columnName = USADOS)                     private double usados;
    @DatabaseField(columnName = GARANTIA)                   private boolean garantia;
    @DatabaseField(columnName = ENTREGADO)                  private boolean entregado;
    @DatabaseField(columnName = PRESUPUESTAR)               private boolean presupuestar;
    @DatabaseField(columnName = FACTURAR)                   private boolean facturar;
    @DatabaseField(columnName = IVA)                        private double iva;
    @DatabaseField(columnName = TARIFA)                     private double tarifa;
    @DatabaseField(columnName = DESCUENTO)                  private double descuento;
    @DatabaseField(columnName = COSTE)                      private double coste;
    @DatabaseField(columnName = N_SERIE)                    private String n_serie;

    public ArticuloParte(){}
    public ArticuloParte(int fk_articulo, int fk_parte,int fk_item_gestnet,double usados,boolean entregado,boolean garantia,double iva,double tarifa,double descuento,double coste) {
        this.id = id;
        this.fk_articulo = fk_articulo;
        this.fk_parte = fk_parte;
        this.fk_item_gestnet = fk_item_gestnet;
        this.usados = usados;
        this.garantia = garantia;
        this.entregado = entregado;
        this.presupuestar=true;
        this.facturar=true;
        this.iva=iva;
        this.tarifa=tarifa;
        this.descuento=descuento;
        this.coste=coste;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getFk_articulo() {
        return fk_articulo;
    }
    public void setFk_articulo(int fk_articulo) {
        this.fk_articulo = fk_articulo;
    }

    public int getFk_parte() {
        return fk_parte;
    }
    public void setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
    }

    public int getFk_item_gestnet() {
        return fk_item_gestnet;
    }
    public void setFk_item_gestnet(int fk_item_gestnet) {
        this.fk_item_gestnet = fk_item_gestnet;
    }

    public double getUsados() { return usados; }
    public void setUsados(double usados) {
        this.usados = usados;
    }

    public boolean getGarantia() { return garantia; }
    public void setGarantia(boolean garantia) {
        this.garantia = garantia;
    }

    public boolean getEntregado() { return entregado; }
    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public boolean getPresupuestar() { return presupuestar; }
    public void setPresupuestar(boolean presupuestar) {
        this.presupuestar = presupuestar;
    }


    public boolean getFacturar() { return facturar; }
    public void setFacturar(boolean facturar) {
        this.facturar = facturar;
    }

    public double getIva() { return iva; }
    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getCoste() { return coste; }
    public void setCoste(double coste) {
        this.coste = coste;
    }

    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTarifa() { return tarifa; }
    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public String getN_serie() {
        return n_serie;
    }

    public void setN_serie(String n_serie) {
        this.n_serie = n_serie;
    }
}
