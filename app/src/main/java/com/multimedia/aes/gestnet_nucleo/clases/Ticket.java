package com.multimedia.aes.gestnet_nucleo.clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Calendar;

public abstract class Ticket {





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
        String result="";
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        final int mHour = mcurrentDate.get(Calendar.HOUR_OF_DAY);
        final int mMin = mcurrentDate.get(Calendar.MINUTE);
        result+="-------CONFORME FIRMANTE---------"+"\n";
        String fecha = mDay+"/"+mMonth+"/"+mYear+" - "+mHour+":"+mMin;
        result+=fecha+"\n";
        String nombre=parte.getNombre_cliente(),dni=parte.getDni_cliente();
        result+="Nombre y dni: "+" "+"\n";
        result+="Firma"+"\n";
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
}
