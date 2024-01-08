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
        result += format("Albaran simplificado: ", numParte); //"Factura simplificada: " + numParte + "\n";
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

        double ba = (datosAdicionales.getPreeu_mano_de_obra_precio() * datosAdicionales.getPreeu_mano_de_obra()) +
                datosAdicionales.getPreeu_adicional() +
                datosAdicionales.getPreeu_analisis_combustion() +
                datosAdicionales.getPreeu_disposicion_servicio() + totalArticulos;;

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

        return result;
    }

    @Override
    public String conformeCliente(int id, Context context) throws SQLException {
        Parte parte = ParteDAO.buscarPartePorId(context, id);
        String result = "";
        result += "********************************" + "\n";
        result += "Conforme Cliente" + "\n";
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

        return result;

    }
}
