package com.multimedia.aes.gestnet_nucleo.clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Impresion {
    ////////////METODOS////////////////
    public static Bitmap loadFirmaClienteFromStorage(int id, Context context) throws SQLException {
        Bitmap b = null;
        try {
            File f = new File(Constantes.PATH, "firmaCliente" + id + ".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static Bitmap loadFirmaTecnicoFromStorage(int id, Context context) throws SQLException {
        Bitmap b = null;
        try {
            File f = new File(Constantes.PATH, "firmaTecnico" + id + ".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static String limpiarAcentos(String texto_entrada) {
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇº€";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcCCE";
        String output = texto_entrada;
        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        return output;
    }
////////////METODOS////////////////

/////////////SEITRON///////////////
    public static String ticketPresupuesto(int id, Context context) throws SQLException {
        //BAJAR TODA LA INFORMACION QUE FALTA DE LA BBDD Y GUARDARLA EN LA TABLA CORRESPONDIENTE

        Cliente cliente = ClienteDAO.buscarTodosLosClientes(context).get(0);
        Usuario usuario = UsuarioDAO.buscarTodosLosUsuarios(context).get(0);
        Parte parte = ParteDAO.buscarPartePorId(context,id);
        String result = "\n";
        result+="--------------------------------"+"\n";
        result+="----------PRESUPUESTO-----------"+"\n";
        result+="--------------------------------"+"\n"+"\n";
        result+="------DATOS DE LA EMPRESA-------"+"\n";
        String nomEmpresa = cliente.getNombre_cliente();
        result+=nomEmpresa+"\n";
        String dirEmpresa = "C/ De Labiano, 12";
        result+=dirEmpresa+"\n";
        String cifEmpresa = "B31737513";
        result+="NIF/CIF: "+cifEmpresa+"\n";
        String telfEmpresa = "948 151 951";
        result+="TFNO: "+telfEmpresa+"\n";
        String emailEmpresa = "imd@imdsl.com";
        result+="EMAIL: "+emailEmpresa+"\n"+"\n";

        result+="--------DATOS DEL PARTE---------"+"\n";
        String numParte = String.valueOf(parte.getNum_parte());
        result+="Num. Parte: "+numParte+"\n";
        String fechaAvisoParte = parte.getFecha_aviso();
        result+="Fecha aviso: "+fechaAvisoParte+"\n";
        String fechaFacturaParte = parte.getFecha_factura();
        result+="Fecha factura: "+fechaFacturaParte+"\n"+"\n";
        String fechaIntervParte = parte.getFecha_visita();
        result+="Fecha intervencion: "+fechaIntervParte+"\n"+"\n";
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
        result+="CIF/DNI"+cifCliente+"\n";
        result+=""+"\n";
        result+=""+"\n";
        result+=""+"\n";
        result+=""+"\n";
        result+=""+"\n";
        result+=""+"\n";
        result+=""+"\n";
        result+=""+"\n";
        result+=""+"\n";
        result+=""+"\n";

        return result;
    }

/////////////SEITRON///////////////

}