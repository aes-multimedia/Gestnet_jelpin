package com.multimedia.aes.gestnet_ssl.clases;

import static com.multimedia.aes.gestnet_ssl.Utils.FormatStringTicket.format;

import android.content.Context;

import com.multimedia.aes.gestnet_ssl.Utils.FormatStringTicket;
import com.multimedia.aes.gestnet_ssl.dao.AnalisisDAO;
import com.multimedia.aes.gestnet_ssl.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_ssl.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_ssl.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_ssl.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_ssl.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_ssl.dao.MarcaDAO;
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO;
import com.multimedia.aes.gestnet_ssl.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Analisis;
import com.multimedia.aes.gestnet_ssl.entidades.Articulo;
import com.multimedia.aes.gestnet_ssl.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Configuracion;
import com.multimedia.aes.gestnet_ssl.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_ssl.entidades.Maquina;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.entidades.Usuario;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ImpresionFacturaUruena extends Ticket {

    private int idparte;

    @Override
    public String encabezado() throws SQLException {
        String result = "\n";
        result += "********************************" + "\n";
        result += "******ALBARAN SIMPLIFICADO******" + "\n";
        result += "********************************" + "\n";
        return result;
    }

    @Override
    public String cuerpo(int id, Context context) throws SQLException {

        //BAJAR TODA LA INFORMACION QUE FALTA DE LA BBDD Y GUARDARLA EN LA TABLA CORRESPONDIENTE

        Cliente cliente = ClienteDAO.buscarCliente(context);
        Usuario usuario = UsuarioDAO.buscarUsuario(context);
        Parte parte = ParteDAO.buscarPartePorId(context, id);
        idparte = id;
        DatosAdicionales datosAdicionales = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(context, id);


        Maquina maquina = MaquinaDAO.buscarMaquinaPorFkMaquina(context, parte.getFk_maquina());
        List<ArticuloParte> articuloPartes = ArticuloParteDAO.buscarArticuloParteFkParte(context, id);


        ArrayList<Articulo> articulos = new ArrayList<>();
        if (articuloPartes != null) {
            int[] ids = new int[articuloPartes.size()];
            int[] idsAp = new int[articuloPartes.size()];
            for (int i = 0; i < articuloPartes.size(); i++) {
                ids[i] = articuloPartes.get(i).getFk_articulo();
                idsAp[i] = articuloPartes.get(i).getId();
            }

            for (int i = 0; i < ids.length; i++) {
                ArticuloParte ap = ArticuloParteDAO.buscarArticuloPartePorID(context, idsAp[i]);
                Articulo a = ArticuloDAO.buscarArticuloPorID(context, ids[i]);
                a.setIva(ap.getIva());
                a.setCoste(ap.getCoste());
                a.setDescuento(ap.getDescuento());
                a.setTarifa(ap.getTarifa());
                articulos.add(a);
            }
        }

        String result = "";
        //result+="******DATOS DE LA EMPRESA******-"+"\n";
        String nomEmpresa = parte.getNombre_compania();
        result += nomEmpresa + "\n";
        String dirEmpresa = "" + cliente.getNombre_cliente();
        result += dirEmpresa + "\n";
        result += "SAT OFICIAL:\n";
        String cifEmpresa = parte.getCif();
        result += " NIF:" + cifEmpresa + " No SAT 48/EGSE\n";
        String telfEmpresa = parte.getTelefono1();
        result += "TFNO: " + telfEmpresa + "\n";
        String emailEmpresa = parte.getEmail();
        result += "EMAIL: " + emailEmpresa + "\n" + "\n";

        result += "********************************" + "\n";
        result += "********DATOS DEL PARTE*********" + "\n";
        String numParte = String.valueOf(parte.getNum_parte());
        result += format("Factura simplificada: ", numParte); //"Factura simplificada: " + numParte + "\n";
        result += format("Nombre tecnico: ", usuario.getNombreUsuario());//"Nombre tecnico: " + usuario.getNombreUsuario() + "\n";
        SimpleDateFormat dateformar = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechadehoy = dateformar.format(new Date());
        result += "Fecha: " + fechadehoy + "\n";
        result += "\n";
        result += "************CLIENTE*************" + "\n";
        String numCliente = parte.getNumero_cliente();
        result += format("Num. Cliente: ", numCliente);//"Num. Cliente: " + numCliente + "\n";
        String nomCliente = parte.getNombre_cliente();
        result += "Nombre: " + nomCliente + "\n";
        String telfCliente = "";
        if (!parte.getTelefono1_cliente().equals("")) {
            telfCliente += "/" + parte.getTelefono1_cliente() + "/";
        }
        if (!parte.getTelefono2_cliente().equals("")) {
            telfCliente += "/" + parte.getTelefono2_cliente() + "/";
        }
        if (!parte.getTelefono3_cliente().equals("")) {
            telfCliente += "/" + parte.getTelefono3_cliente() + "/";
        }
        if (!parte.getTelefono4_cliente().equals("")) {
            telfCliente += "/" + parte.getTelefono4_cliente() + "/";
        }
        result += "Telefonos: " + telfCliente + "\n";
        /*String fechaAvisoParte = FormatearfechaTimeStamp(parte.getFecha_aviso());
        result += "Fecha aviso: " + fechaAvisoParte + "\n";*/
        String fechaintervencion = FormatearfechaTimeStamp(parte.getFecha_visita());
        result += "F. Intervencion: " + fechaintervencion + "\n";
        /*if (clienteId != 28) {
            result += "Hora de entrada: " + datosAdicionales.getMatem_hora_entrada() + "\n";
            result += "Hora de salida: " + datosAdicionales.getMatem_hora_salida() + "\n";
        }*/
        String dirCliente = "";
        String provincia = "";
        String municipio = "";
        if (!parte.getTipo_via().trim().equals("") && !parte.getTipo_via().trim().equals("null")) {
            dirCliente += parte.getTipo_via() + " ";
        }
        if (!parte.getVia().trim().equals("") && !parte.getVia().trim().equals("null")) {
            dirCliente += parte.getVia() + " ";
        }
        if (!parte.getNumero_direccion().trim().equals("") && !parte.getNumero_direccion().trim().equals("null")) {
            dirCliente += "Nº " + parte.getNumero_direccion() + " ";
        }
        if (!parte.getEscalera_direccion().trim().equals("") && !parte.getEscalera_direccion().trim().equals("null")) {
            dirCliente += "Esc. " + parte.getEscalera_direccion() + " ";
        }
        if (!parte.getPiso_direccion().trim().equals("") && !parte.getPiso_direccion().trim().equals("null")) {
            dirCliente += "Piso " + parte.getPiso_direccion() + " ";
        }
        if (!parte.getPuerta_direccion().trim().equals("") && !parte.getPuerta_direccion().trim().equals("null")) {
            dirCliente += parte.getPuerta_direccion() + " ";
        }
        if (!parte.getMunicipio_direccion().trim().equals("") && !parte.getMunicipio_direccion().trim().equals("null")) {
            municipio += parte.getMunicipio_direccion();
        }
        if (!parte.getProvincia_direccion().trim().equals("") && !parte.getProvincia_direccion().trim().equals("null")) {
            provincia += parte.getProvincia_direccion();
        }
        result += "Direc.: " + dirCliente + "\n";
        result += format("Provincia: ", provincia); //"Provincia: " + provincia + "\n";
        result += format("Población: ", municipio); //"Población: " + municipio + "\n";
        String cifCliente = parte.getDni_cliente();
        result += "NIF/CIF: " + cifCliente + "\n" + "\n";

        result += "**********DISPOSITIVOS**********" + "\n";
        String marca = "";
        if (MarcaDAO.buscarMarcaPorId(context, maquina.getFk_marca()) != null) {
            marca = MarcaDAO.buscarMarcaPorId(context, maquina.getFk_marca()).getNombre_marca();
        } else {
            marca = "Sin Marca";
        }
        result += format("Marca: ", marca); //"Marca: " + marca + "\n";
        String modelo = maquina.getModelo();
        result += "Modelo: " + modelo + "\n";
        String contMant = parte.getContrato_endesa();
        result += "Ctrto. Man: " + contMant + "\n";
        String numSerie = maquina.getNum_serie();
        result += "N. Serie: " + numSerie + "\n";
        String puestaMarchaMaquina = FormatearfechaDate(maquina.getPuesta_marcha());
        result += "Puesta Marcha: " + puestaMarchaMaquina + "\n";
        /*if (clienteId != 28) {

        }
        if (clienteId == 28) {
            result += "Ubicacion: " + maquina.getUbicacion() + "\n";
        }
        result += "\n";*/

        result += "************SINTOMAS************" + "\n\n";
        String sintomas = parte.getSintomas();
        if (sintomas != null && !sintomas.equals("")) {
            result += "Detalle Avería: " + sintomas + "\n";
        }
        /*ArrayList<Analisis> analisises = new ArrayList<>();
        if (AnalisisDAO.buscarAnalisisPorFkMaquinaFkParte(context, parte.getId_parte(), maquina.getId_maquina()) != null) {
            analisises.addAll(AnalisisDAO.buscarAnalisisPorFkMaquinaFkParte(context, parte.getId_parte(), maquina.getId_maquina()));
        }
        if (!analisises.isEmpty()) {
            for (int j = 0; j < analisises.size(); j++) {
                String co_amb = analisises.get(j).getCo_ambiente();
                String co_ambiente = "CO ambiente: " + co_amb + " ppm \n";
                String observaciones_tecnico = "******-ANALISIS******--" + "\n";
                String nom_med = analisises.get(j).getNombre_medicion();
                String nombre_medicion = "Tipo: " + nom_med + "\n";
                String tem_max_acs = maquina.getTemperatura_max_acs();
                String temperatura_max_acs = "T. Max. ACS: " + tem_max_acs + " ºC \n";
                String caud_acs = maquina.getCaudal_acs();
                String caudal_acs = "Caudal ACS: " + caud_acs + " l/min\n";
                String pot_uti = maquina.getPotencia_util();
                String potencia_util = "Potencia útil: " + pot_uti + " Kw\n";
                String tem_agu_ent = maquina.getTemperatura_agua_generador_calor_entrada();
                String temp_agua_entrada = "T. agua entrada: " + tem_agu_ent + " ºC \n";
                String tem_agu_sal = maquina.getTemperatura_agua_generador_calor_salida();
                String temp_agua_salida = "T. agua salida: " + tem_agu_sal + " ºC \n";
                String tem_gas_comb = analisises.get(j).getTemperatura_gases_combustion();
                String temp_gases_combust = "T. gases PDC: " + tem_gas_comb + " ºC \n";
                String rend_apar = analisises.get(j).getRendimiento_aparato();
                String rendimiento_aparato = "Rendimiento aparato: " + rend_apar + " %" + "\n";
                String co_cor = analisises.get(j).getCo_corregido();
                String co_corregido = "CO corregido: " + co_cor + " ppm \n";
                String co2_amb = analisises.get(j).getCo2_ambiente();
                String co2_ambiente = "";
                if (!co2_amb.equals("")) {
                    co2_ambiente = "CO2 ambiente: " + co2_amb + " ppm \n";
                }
                String co = analisises.get(j).getC0_maquina();
                String cO = "CO: " + co + " ppm \n";
                String tir = analisises.get(j).getTiro();
                String tiro = "Tiro: " + tir + " mbar \n";
                String c2 = analisises.get(j).getCo2();
                String co2 = "CO2: " + c2 + " % \n";
                String o02 = analisises.get(j).getO2();
                String o2 = "O2: " + o02 + " % \n";
                String lamb = analisises.get(j).getLambda();
                String lambda = "Lambda: " + lamb + "\n";
                String tmp_amb = analisises.get(j).getTemperatura_ambiente_local();
                String temperatura_Ambiente = "T. Amb.: " + tmp_amb + " ºC\n";
                String num_serie_tex = "";
                String numero_serie_texto = "Num.Serie Equip.Testo: " + "\n" + num_serie_tex + "\n";
                result += observaciones_tecnico + nombre_medicion +
                        temperatura_max_acs + caudal_acs + potencia_util + temp_agua_entrada + temp_agua_salida +
                        temp_gases_combust + co_corregido + o2 + cO + lambda + co2 + temperatura_Ambiente + tiro + rendimiento_aparato + co_ambiente +
                        co2_ambiente;
            }
        }*/


        result += "\n**********INTERVENCION**********" + "\n";
        String operacion = datosAdicionales.getOperacion_efectuada();
        result += "Operacion efectuada: " + operacion + "\n";
        String tipoInter = parte.getTipo();
        result += format("Tipo Intervencion: ", tipoInter); //"Tipo Intervencion: " + tipoInter + "\n";

        String zero = "0.0";
        String duracion = parte.getDuracion();
        if (!duracion.equals(zero)) {
            double tiempo = new Double(duracion);
            DecimalFormat df2 = new DecimalFormat(".##");
            duracion = df2.format(tiempo);
           if  (tiempo >= 1) {
               String[] arr = duracion.split(",");
               String horas = arr[0];
               double min = new Double("0."+arr[1]);
               int minutos = (int)(min * 60);
               duracion = horas + " H " + minutos + " min";
            }else{
               int min = (int)(tiempo*60);
               duracion = min + " min";
            }
            result += format("Duracion: ", duracion); //"Duracion: " + duracion + "\n";
        }

        double totalArticulos = 0;
        if (!articulos.isEmpty()) {
            result += "\n" + "***********MATERIALES***********" + "\n";

            for (Articulo art : articulos) {
                ArticuloParte articuloParte = ArticuloParteDAO.buscarArticuloPartePorFkParteFkArticulo(context, art.getId_articulo(), parte.getId_parte());
                double usados = articuloParte.getUsados();
                double coste = 0;
                if (articuloParte.getFacturar()) {
                    if (!articuloParte.getGarantia()) coste = art.getTarifa();
                    double totalArt = usados * coste;
                    totalArticulos += totalArt;
                    result += "-" + art.getNombre_articulo() + "\n";
                    result += " Uds:" + usados + " PVP:" + coste + " Total:" + totalArt + "\n";
                    if (articuloParte.getEntregado()) result += "(pedido)" + "\n" + "\n";
                    else result += "\n";
                }
            }
        }

        result += "********************************" + "\n";
        result += "TOTAL PARTE" + "\n";
        result += "********************************" + "\n";
        Double manoObra = datosAdicionales.getPreeu_mano_de_obra_precio() * datosAdicionales.getPreeu_mano_de_obra();
        if (manoObra > 0) {
            DecimalFormat df2 = new DecimalFormat(".##");
            String manoObraStr = df2.format(manoObra);
            result += format("Mano Obra: ", manoObraStr + " €");//"Mano Obra: " + manoObraStr + " €" + "\n";
        }
        String dispServi = String.valueOf(datosAdicionales.getPreeu_disposicion_servicio());
        if (!dispServi.equals(zero)) {
            result += format("Disp. servicio: ", dispServi + " €");
        }

        Double otros = datosAdicionales.getPreeu_adicional();
        if (otros > 0) {
            DecimalFormat df2 = new DecimalFormat(".##");
            String otrosStr = df2.format(otros);
            result += format("Otros: ", otrosStr + " €");//"Otros: " + otrosStr + " €" + "\n";
        }

        Double analisiscombustion = datosAdicionales.getPreeu_analisis_combustion();
        if (analisiscombustion > 0) {
            DecimalFormat df2 = new DecimalFormat(".##");
            String analisiscombustionStr = df2.format(analisiscombustion);
            result += format("Analisis de combustion: ", analisiscombustionStr); //"Analisis de combustion: " + analisiscombustionStr + "\n";
        }

        /*String puestaMarcha = String.valueOf(datosAdicionales.getPreeu_puesta_marcha());
        if (!puestaMarcha.equals(zero)) {
            result += "Puesta en marcha: " + puestaMarcha + "\n";
        }*/

        /*String servicioUrgencia = String.valueOf(datosAdicionales.getPreeu_servicio_urgencia());
        if (!servicioUrgencia.equals(zero)) {
            result += "Servicio de urgencia: " + servicioUrgencia + "\n";
        }

        String desplazamiento = "(" + datosAdicionales.getPreeu_km() + "KM/" + datosAdicionales.getPreeu_km_precio() + "): " + datosAdicionales.getPreeu_km_precio_total();
        if (datosAdicionales.getPreeu_km_precio_total() != 0) {
            result += "Desplazamiento " + desplazamiento + "\n";
        }*/

        double ba = (datosAdicionales.getPreeu_mano_de_obra_precio() * datosAdicionales.getPreeu_mano_de_obra()) +
                datosAdicionales.getPreeu_adicional() +
                datosAdicionales.getPreeu_analisis_combustion() +
                datosAdicionales.getPreeu_disposicion_servicio() + totalArticulos;;

        /*if(clienteId != 28){
            result+="TOTAL INTERVENCIONES:  "+String.valueOf(ba)+"\n";
        }*/
        DecimalFormat df2 = new DecimalFormat(".##");

        if (clienteId == 28) {
            result += "\n" + "******SUMA TOTAL DEL AVISO******" + "\n";
            result += format("SUMA TOTAL: ", ba + "  €");//"SUMA TOTAL:  " + ba + " €" + "\n";
        }

        result += format("TOTAL MATERIALES: ", df2.format(totalArticulos) +" €");
        if (clienteId != 28) {
            result += format("TOTAL INTERVENCIONES:  ", ba + " €");
        }
        result += format("BASE IMPONIBLE: ", df2.format(ba) + " €");//"BASE IMPONIBLE: " + df2.format(ba) + " €\n";
        result += format("I.V.A: ", "21.00%");//"I.V.A:" + "21.00%" + "\n";
        double totIva = ba * 0.21;
        String totalIva = df2.format(totIva);
        result += format("TOTAL I.V.A: ", totalIva + " €"); //"TOTAL I.V.A: " + totalIva + " €" + "\n";
        String total = df2.format(totIva + ba);
        result += format("TOTAL: ", total + " €"); //"TOTAL: " + total + " €" + "\n";
        if (FormasPagoDAO.buscarFormasPagoPorId(context, datosAdicionales.getFk_forma_pago()) != null) {
            String formaPago = FormasPagoDAO.buscarFormasPagoPorId(context, datosAdicionales.getFk_forma_pago()).getForma_pago();
            result += "Forma pago: " + formaPago + "\n";
        }
        /*result+="\n"+"********Conforme Cliente********"+"\n";
        result+="*Renuncio a presupuesto previo "+"\n"+
                "autorizando reparacion."+"\n";
        result += "" + "\n";
        result += "" + "\n";*/
        return result;
    }

    @Override
    public String conformeCliente(int id, Context context) throws SQLException {
        Parte parte = ParteDAO.buscarPartePorId(context, id);
        //Log.e("datos cliente Impresion",parte.toString());
        String result = "";
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH) + 1;
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        final int mHour = mcurrentDate.get(Calendar.HOUR_OF_DAY);
        final int mMin = mcurrentDate.get(Calendar.MINUTE);
        result += "********************************" + "\n";
        result += "Conforme Cliente" + "\n";
        /*String fecha = mDay+"/"+mMonth+"/"+mYear+" - "+mHour+":"+mMin;
        if(clienteId != 28){
            result+=fecha+"\n";
        }

        String nombre=parte.getNombre_firmante(),dni=parte.getDni_firmante();
        result+="Nombre y dni: "+nombre+"-"+dni+"\n";
        result+="Firma"+"\n";*/
        result += "\n\n";
        return result;
    }

    @Override
    public String proteccionDatos(Context context) throws SQLException {
        Configuracion c = ConfiguracionDAO.buscarConfiguracion(context);
        Parte parte = ParteDAO.buscarPartePorId(context, idparte);
        String result = "";
        result += "********************************" + "\n";
        if (c.isDisp_servicio()) {
            result += "Esta reparación tiene una garantía de 3 meses. DECRETO 139/1999 DE 7 DE MAYO: DOGAN Nº95 DE 20 DE MAYO"; //parte.getPoliticaPrivacidad();
            result += "\n" + "\n" + "\n";
        }
        return result;
    }

    @Override
    public String conformeTecnico(Context context) throws SQLException {
        Usuario usuario = UsuarioDAO.buscarUsuario(context);
        String result = "";
        result += "********************************" + "\n";
        result += "Fdo Tecnico" + "\n\n\n";
        /*String nombre = usuario.getNombreUsuario();
        result += "Nombre: " + nombre + "\n";
        result += "Firma" + "\n";*/
        return result;
    }

    public String presupuestoAceptado(Context context) throws SQLException {
        DatosAdicionales datosAdicionales = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(context, idparte);
        String result = "";
        result += "********************************" + "\n";
        if(!datosAdicionales.getBaceptapresupuesto())
            result += "Presupuesto no Aceptado" + "\n\n\n";
        else
            result += "Presupuesto Aceptado" + "\n\n\n";

        return result;
    }

    public String renuncioPrespuesto(){
        String result = "";
        result += "********************************" + "\n";
        result += "Renuncio a Presupuesto Previo Autizando la Reparación" + "\n\n\n";

        return result;
    }

    @Override
    public String pie(int id, Context context) {

        Parte parte;
        try {

            parte = ParteDAO.buscarPartePorId(context, id);
        } catch (Exception e) {

            return e.getMessage();
        }

        String politicaPrivacidad = parte.getPoliticaPrivacidad();
        String result = "\n";
        /*result += "*Esta reparacion tiene una " + "\n" +
                "garantia de 3 meses. DECRETO " + "\n" +
                "139/1999 DE 7 DE MAYO: DOGAN " + "\n" +
                "No95 DE 20 DE MAYO." + "\n" + "\n" + politicaPrivacidad + "\n";*/

        return result;

    }
}
