package com.multimedia.aes.gestnet_nucleo.clases;

import android.content.Context;

import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ImpresionPresupuesto extends Ticket {


    @Override
    public String encabezado() throws SQLException {
        String result = "\n";
        String encabezado="";

        encabezado="PRESUPUESTO";

        result+="--------------------------------"+"\n";
        result+="----------"+encabezado+"-----------"+"\n";
        result+="--------------------------------"+"\n"+"\n";
        return result;
    }

    @Override
    public String cuerpo(int id, Context context) throws SQLException {
        //BAJAR TODA LA INFORMACION QUE FALTA DE LA BBDD Y GUARDARLA EN LA TABLA CORRESPONDIENTE
        Cliente cliente = ClienteDAO.buscarCliente(context);
        Usuario usuario = UsuarioDAO.buscarUsuario(context);
        Parte parte = ParteDAO.buscarPartePorId(context,id);
        DatosAdicionales datosAdicionales = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(context,id);
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));


        Maquina maquina = MaquinaDAO.buscarMaquinaPorFkMaquina(context,parte.getFk_maquina());
        List<ArticuloParte> articuloPartes = ArticuloParteDAO.buscarArticuloParteFkParte(context,id);


        ArrayList<Articulo> articulos = new ArrayList<>();
        if(articuloPartes != null) {
            int [] ids = new int [articuloPartes.size()];
            for (int i = 0; i < articuloPartes.size(); i++) {
                ids[i] = articuloPartes.get(i).getFk_articulo();
            }

            for (int i = 0; i < ids.length; i++) {
                articulos.add(ArticuloDAO.buscarArticuloPorID(context,ids[i]));
            }
        }

        String result = "\n";
        result+="------DATOS DE LA EMPRESA-------"+"\n";
        String nomEmpresa = cliente.getNombre_cliente();
        result+=nomEmpresa+"\n";
        String dirEmpresa = parte.getNombre_compania();
        result+=dirEmpresa+"\n";
        String cifEmpresa = parte.getCif();
        result+="NIF/CIF: "+cifEmpresa+"\n";
        String telfEmpresa = parte.getTelefono1();
        result+="TFNO: "+telfEmpresa+"\n";
        String emailEmpresa = parte.getEmail();
        result+="EMAIL: "+emailEmpresa+"\n"+"\n";

        result+="--------DATOS DEL PARTE---------"+"\n";
        String numParte = String.valueOf(parte.getNum_parte());
        result+="Num. Parte: "+numParte+"\n";
        String fechaAvisoParte = parte.getFecha_aviso();
        String fechaAvisoParteFormateado=FormatearfechaTimeStamp(fechaAvisoParte);
        result+="Fecha aviso: "+fechaAvisoParteFormateado+"\n";
        String fechaFacturaParte = parte.getFecha_factura();
        result+="Fecha factura: "+fechaFacturaParte+"\n";
        String fechaIntervParte = parte.getFecha_visita();
        String fechaIntervParteFormateada= FormatearfechaDate(fechaIntervParte);
        result+="Fecha intervencion: "+fechaIntervParteFormateada+"\n"+"\n";
        result+="-------DATOS DEL CLIENTE--------"+"\n";
        String numCliente = "";
        result+="Num. Cliente: "+numCliente+"\n";
        String nomCliente = parte.getNombre_cliente();
        result+="Nombre: "+nomCliente+"\n";
        String telfCliente = "";
        if (!parte.getTelefono1_cliente().equals("")){
            telfCliente += parte.getTelefono1_cliente()+" ";
        }
        if (!parte.getTelefono2_cliente().equals("")){
            telfCliente += parte.getTelefono2_cliente()+" ";
        }
        if (!parte.getTelefono3_cliente().equals("")) {
            telfCliente += parte.getTelefono3_cliente()+" ";
        }
        if (!parte.getTelefono4_cliente().equals("")){
            telfCliente += parte.getTelefono4_cliente()+" ";
        }
        result+="TFNS: "+telfCliente+"\n";
        String dirCliente = "";
        if (!parte.getTipo_via().trim().equals("")&&!parte.getTipo_via().trim().equals("null")){
            dirCliente+=parte.getTipo_via()+" ";
        }
        if (!parte.getVia().trim().equals("")&&!parte.getVia().trim().equals("null")){
            dirCliente+=parte.getVia()+" ";
        }
        if (!parte.getNumero_direccion().trim().equals("")&&!parte.getNumero_direccion().trim().equals("null")){
            dirCliente+="Nº "+parte.getNumero_direccion()+" ";
        }
        if (!parte.getEscalera_direccion().trim().equals("")&&!parte.getEscalera_direccion().trim().equals("null")){
            dirCliente+="Esc. "+parte.getEscalera_direccion()+" ";
        }
        if (!parte.getPiso_direccion().trim().equals("")&&!parte.getPiso_direccion().trim().equals("null")){
            dirCliente+="Piso "+parte.getPiso_direccion()+" ";
        }
        if (!parte.getPuerta_direccion().trim().equals("")&&!parte.getPuerta_direccion().trim().equals("null")){
            dirCliente+=parte.getPuerta_direccion()+" ";
        }
        if (!parte.getMunicipio_direccion().trim().equals("")&&!parte.getMunicipio_direccion().trim().equals("null")){
            dirCliente+="("+parte.getMunicipio_direccion()+"-";
        }
        if (!parte.getProvincia_direccion().trim().equals("")&&!parte.getProvincia_direccion().trim().equals("null")){
            dirCliente+=parte.getProvincia_direccion()+")";
        }
        result+=dirCliente+"\n";
        String cifCliente = parte.getDni_cliente();
        result+="CIF/DNI: "+cifCliente+"\n"+"\n";
        result+="------DATOS DE LA MAQUINA-------"+"\n";
        String marca = "";
        if (MarcaDAO.buscarMarcaPorId(context,maquina.getFk_marca())!=null){
            marca = MarcaDAO.buscarMarcaPorId(context,maquina.getFk_marca()).getNombre_marca();
        }else{
            marca = "Sin Marca";
        }
        result+="Marca: "+marca+"\n";
        String modelo = maquina.getModelo();
        result+="Modelo: "+modelo+"\n";
        String contMant = parte.getContrato_endesa();
        result+="Ctrto. Man: "+contMant+"\n";
        String numSerie = maquina.getNum_serie();
        result+="N. Serie: "+numSerie+"\n";
        String puestaMarchaMaquina = maquina.getPuesta_marcha();
        String puestaMarcaaMaquinaFormateada=FormatearfechaDate(puestaMarchaMaquina);
        result+="Puesta Marcha: "+puestaMarcaaMaquinaFormateada+"\n"+"\n";
        result+="----------INTERVENCION----------"+"\n";
        String operacion = datosAdicionales.getOperacion_efectuada();
        result+="Operacion: "+operacion+"\n";
        String tipoInter = parte.getTipo();
        result+="Tipo Intervencion: "+tipoInter+"\n";
        String duracion = parte.getDuracion();
        result+="Duracion: "+duracion+"\n";
        String manoObra = String.valueOf(datosAdicionales.getPreeu_mano_de_obra_precio()*datosAdicionales.getPreeu_mano_de_obra());
        result+="Mano Obra: "+manoObra+" €"+"\n";
        String dispServi = String.valueOf(datosAdicionales.getPreeu_disposicion_servicio());
        result+="Disp. servicio: "+dispServi+" €"+"\n";
        String otros = String.valueOf(datosAdicionales.getPreeu_adicional());
        result+="Otros: "+otros+" €"+"\n";
        String analisiscombustion = String.valueOf(datosAdicionales.getPreeu_analisis_combustion());
        result+="Analisis de combustion: "+analisiscombustion+"\n";
        String puestaMarcha = String.valueOf(datosAdicionales.getPreeu_puesta_marcha());
        result+="Puesta en marcha: "+puestaMarcha+"\n";
        String servicioUrgencia = String.valueOf(datosAdicionales.getPreeu_servicio_urgencia());
        result+="Servicio de urgencia: "+servicioUrgencia+"\n";
        String desplazamiento = "("+datosAdicionales.getPreeu_km()+"KM/"+datosAdicionales.getPreeu_km_precio()+"): "+datosAdicionales.getPreeu_km_precio_total();
        result+="Desplazamiento "+desplazamiento+"\n";
        if (FormasPagoDAO.buscarFormasPagoPorId(context,datosAdicionales.getFk_forma_pago())!=null){
            String formaPago = FormasPagoDAO.buscarFormasPagoPorId(context,datosAdicionales.getFk_forma_pago()).getForma_pago();
            result+="Forma pago: "+formaPago+"\n";
        }
        double ba = (datosAdicionales.getPreeu_mano_de_obra_precio()*datosAdicionales.getPreeu_mano_de_obra())+datosAdicionales.getPreeu_disposicion_servicio()+
                datosAdicionales.getPreeu_adicional_coste()+datosAdicionales.getFact_adicional()+datosAdicionales.getPreeu_analisis_combustion()+datosAdicionales.getPreeu_puesta_marcha()+
                datosAdicionales.getPreeu_servicio_urgencia()+(datosAdicionales.getPreeu_km()*datosAdicionales.getPreeu_km_precio());
        result+="TOTAL INTERVENCIONES:  "+String.valueOf(ba)+"\n";
        if (!articulos.isEmpty()){
            result+="\n"+"-----------MATERIALES-----------"+"\n";
            double totalArticulos = 0;
            for (Articulo art:articulos) {
                ArticuloParte articuloParte = ArticuloParteDAO.buscarArticuloPartePorFkParteFkArticulo(context, art.getId_articulo(), parte.getId_parte());
                int usados = articuloParte.getUsados();
                double coste = 0;
                if(art.isPresupuestar()) {
                    if (!art.isGarantia()) coste = art.getTarifa();
                    double totalArt = usados * coste;
                    totalArticulos += totalArt;
                    result += "-" + art.getNombre_articulo() + "\n";
                    result += " Uds:" + usados + " PVP:" + coste + " Total:" + totalArt + "\n" ;
                    if(art.isEntregado()==1)result += "(pedido)"+ "\n" + "\n";
                    else result += "\n";
                }
            }
            result+="TOTAL MATERIALES: "+totalArticulos+" €"+"\n";
            result+="TOTAL INTERVENCIONES:  "+String.valueOf(ba)+"\n";
            ba+=totalArticulos;
        }

        String base = String.valueOf(ba);
        result+="BASE IMPONIBLE: "+base+" €"+"\n";
        result+="I.V.A:"+"21.00%"+"\n";
        DecimalFormat df2 = new DecimalFormat(".##");
        double totIva = ba*0.21;
        String totalIva = String.valueOf(df2.format(totIva));
        result+="TOTAL I.V.A: "+totalIva+"\n";
        String total = String.valueOf(df2.format(totIva+ba));
        result+="TOTAL: "+total+"\n"+"\n";
        result+="---CONFORME FINAL DEL FIRMANTE---"+"\n";
        result+="*Renuncio a presupuesto previo "+"\n"+
                "autorizando la reparacion."+"\n";
        result+=""+"\n";
        result+=""+"\n";
        return result;




    }

    @Override
    public String pie(int id, Context context) {
        Parte parte;
        try {

            parte = ParteDAO.buscarPartePorId(context,id);
        }catch (Exception e){

            return e.getMessage();
        }

        String politicaPrivacidad=parte.getPoliticaPrivacidad();
        String result = "\n";
        result+="*Esta reparacion tiene una "+"\n"+
                "garantia de 3 meses. DECRETO "+"\n"+
                "139/1999 DE 7 DE MAYO: DOGAN "+"\n"+
                "No95 DE 20 DE MAYO."+"\n"+"\n"+politicaPrivacidad+"\n";

        return result;
    }







}
