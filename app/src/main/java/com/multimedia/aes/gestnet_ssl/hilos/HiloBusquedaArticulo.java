package com.multimedia.aes.gestnet_ssl.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.dialogo.DialogoBuscarArticulo;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Usuario;
import com.multimedia.aes.gestnet_ssl.fragments.TabFragment6_materiales;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;

import javax.net.ssl.HttpsURLConnection;

import static com.multimedia.aes.gestnet_ssl.fragments.TabFragment6_materiales.guardarArticulo;


public class HiloBusquedaArticulo extends AsyncTask<Void, Void, Void> {

    private String mensaje = "";
    private int id;
    private Context context;
    private ProgressDialog dialog;
    private TabFragment6_materiales tab = null;
    private Cliente cliente;
    private Usuario tecnico;

    public HiloBusquedaArticulo(Context context, int id,TabFragment6_materiales tab) {
        this.context = context;
        this.id = id;
        this.tab = tab;
        try {
            cliente = ClienteDAO.buscarCliente(context);
            tecnico = UsuarioDAO.buscarUsuario(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HiloBusquedaArticulo(Context context, int id) {
        this.context = context;
        this.id = id;
        try {
            cliente = ClienteDAO.buscarCliente(context);
            tecnico = UsuarioDAO.buscarUsuario(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = partes();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}') != -1) {
            if (tab!=null){
               guardarArticulo(mensaje,context,tab);
            }else{
                DialogoBuscarArticulo.guardarArticuloDialogo(mensaje,context);
            }

        } else {
            Dialogo.dialogoError("No se ha devuelto correctamente de la api",context);
        }

    }


    private String partes() throws JSONException {
        JSONObject msg = new JSONObject();
        msg.put("id", id);
        msg.put("entidad", tecnico.getFk_entidad());

        URL urlws = null;
        HttpsURLConnection uc = null;
        try {
            String url = "https://"+cliente.getIp_cliente()+Constantes.URL_BUSCAR_ARTICULO;
            urlws = new URL(url);
            uc = (HttpsURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexión, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexión, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexión, IOException");
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
