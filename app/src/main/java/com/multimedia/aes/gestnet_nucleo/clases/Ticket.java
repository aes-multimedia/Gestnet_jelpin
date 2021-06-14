package com.multimedia.aes.gestnet_nucleo.clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Configuracion;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

public abstract class Ticket {

    int clienteId;

public void setCliente( Context context){

    try{
        Cliente cliente = ClienteDAO.buscarCliente(context);
        clienteId = cliente.getId_cliente();
    }catch(SQLException e){

        e.printStackTrace();
    }
}




    public Bitmap loadFirmaClienteFromStorage(int id, Context context) throws SQLException {

        Bitmap b = null;
        try {
            File f = new File(Constantes.PATH, "firmaCliente" + id + ".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }

    public Bitmap loadFirmaTecnicoFromStorage(int id, Context context) throws SQLException {
        Bitmap b = null;
        try {
            File f = new File(Constantes.PATH, "firmaTecnico.png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }


    public String limpiarAcentos(String texto_entrada) {
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇº€";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcCCE";
        String output = texto_entrada;
        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        return output;
    }

    public String conformeCliente (int id, Context context) throws SQLException {
        Parte parte = ParteDAO.buscarPartePorId(context,id);
        //Log.e("datos cliente Impresion",parte.toString());
        String result="";
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH)+1;
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        final int mHour = mcurrentDate.get(Calendar.HOUR_OF_DAY);
        final int mMin = mcurrentDate.get(Calendar.MINUTE);
        result+="---------CONFORME FIRMANTE---------"+"\n";
        String fecha = mDay+"/"+mMonth+"/"+mYear+" - "+mHour+":"+mMin;
        if(clienteId != 28){
            result+=fecha+"\n";
        }

        String nombre=parte.getNombre_firmante(),dni=parte.getDni_firmante();
        result+="Nombre y dni: "+nombre+"-"+dni+"\n";
        result+="Firma"+"\n";
        return result;
    }
    public String proteccionDatos(Context context) throws SQLException {
        Configuracion c = ConfiguracionDAO.buscarConfiguracion(context);
        Cliente cli = ClienteDAO.buscarCliente(context);
        String result="";
        if (c.isDisp_servicio()){
            result+= cli.getProteccion_datos();
            result+= "\n"+"\n"+"\n";
        }
        return result;
    }
    public String conformeTecnico (Context context) throws SQLException {
        Usuario usuario = UsuarioDAO.buscarUsuario(context);
        String result="";
        result+="\n"+"-------FIRMADO TECNICO----------"+"\n";
        String nombre=usuario.getNombreUsuario();
        result+="Nombre: "+nombre+"\n";
        result+="Firma"+"\n";
        return result;
    }

    public  String encabezado() throws SQLException{

        return null;
    }



    public  String cuerpo(int id, Context context) throws SQLException{
        return null;
    }


    public  String pie(){
        return null;
    }


    public abstract String pie(int id, Context context);


    public String FormatearfechaTimeStamp(String fechaRecibida){

        if (!fechaRecibida.equals("")){
            String fechaFormateada="";

            String[] recogerFecha1=fechaRecibida.split(" ");
            String fechaIngles=recogerFecha1[0];
            String[] recogerFecha2=fechaIngles.split("-");
            fechaFormateada=recogerFecha2[2]+"-"+recogerFecha2[1]+"-"+recogerFecha2[0];
            return  fechaFormateada;
        }else{
            return "";
        }
    }
    public String FormatearfechaDate(String fechaRecibida){
        if (!fechaRecibida.equals("")){
            String fechaFormateada="";
            String fechaIngles=fechaRecibida;
            Log.d("fechaRecibida",fechaRecibida);
            String[] recogerFecha2=fechaIngles.split("-");
            fechaFormateada=recogerFecha2[2]+"-"+recogerFecha2[1]+"-"+recogerFecha2[0];
            return  fechaFormateada;
        }else{
            return "";
        }
    }

}

