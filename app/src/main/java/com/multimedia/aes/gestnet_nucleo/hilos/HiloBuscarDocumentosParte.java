package com.multimedia.aes.gestnet_nucleo.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.fragments.TabFragment6_materiales;
import com.multimedia.aes.gestnet_nucleo.nucleo.DocumentosParte;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;

import static com.multimedia.aes.gestnet_nucleo.fragments.TabFragment6_materiales.guardarArticulo;

public class HiloBuscarDocumentosParte extends AsyncTask<Void, Void, Void> {

    private String mensaje = "";
    private int fkParte;
    private Context context;
    private ProgressDialog dialog;
    private Cliente cliente;


    public HiloBuscarDocumentosParte(Context context, int id) {
        this.context = context;
        this.fkParte = id;
        try {
            cliente = ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = buscarDocumentos();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}')!=-1){
                ((DocumentosParte)context).mostrarDocumentos(mensaje,context);
        }else{

                ((DocumentosParte)context).sacarMensaje("No se ha devuelto correctamente de la api");
            }

        }




    private String buscarDocumentos() throws JSONException {
        JSONObject msg = new JSONObject();
        msg.put("fk_parte", fkParte);
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            String url = "http://"+cliente.getIp_cliente()+ Constantes.URL_BUSCAR_DOCUMENTOS_PARTE;
            urlws = new URL(url);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            msg.put("estado", 5);
            msg.put("mensaje", "Error de conexión, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            msg.put("estado", 5);
            msg.put("mensaje", "Error de conexión, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONObject error = new JSONObject();
            msg.put("estado", 5);
            msg.put("mensaje", "Error de conexión, IOException");
            return error.toString();
        }
        String contenido = "";
        OutputStream os = null;
        try {
            os = uc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(msg.toString());
            osw.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                contenido += inputLine + "\n";
            }
            in.close();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            msg.put("estado", 5);
            msg.put("mensaje", "Error de conexión, error en lectura");
            contenido = error.toString();
        }
        return contenido;
    }


}
