package com.multimedia.aes.gestnet_ssl.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.EnvioDAO;
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO;
import com.multimedia.aes.gestnet_ssl.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.entidades.Usuario;
import com.multimedia.aes.gestnet_ssl.fragments.FragmentPartes;

import org.json.JSONArray;
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

public class HiloMailParte extends AsyncTask<Void,Void,Void> {

    private String mensaje;
    private Context context;
    private int fk_parte;
    private ProgressDialog dialog;
    private Cliente cliente;
    private Usuario usuario;
    private  String email;
    private  String data;
    private  FragmentPartes tabPartes;

    public HiloMailParte(Context context, int fk_parte, String email) {
        this.context = context;
        this.fk_parte = fk_parte;
        try {
            Parte parte = ParteDAO.buscarPartePorId(context,fk_parte);
            this.email = email;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            cliente= ClienteDAO.buscarCliente(context);
            usuario = UsuarioDAO.buscarUsuario(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Enviando Email.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = iniciar();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}') != -1) {
            try {
                JSONObject result = new JSONObject(mensaje);
                int estado = result.getInt("estado");
                dialog.cancel();
                if(estado == 1){
                    Dialogo.dialogoError("El correo electrónico ha sido enviado correctamente a <br><br><b>"+email+"</b><br><br>Gracias.",context);
                }else{

                    Dialogo.dialogoError("El correo electrónico no ha podido ser enviado a <br><br>"+email+"<br><br>Por favor compruebe la dirección de correo.<br><br>Gracias.",context);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            dialog.cancel();
            Dialogo.dialogoError("No se ha devuelto correctamente de la api",context);
        }
    }

    private String iniciar() throws JSONException, SQLException{
        JSONObject msg = new JSONObject();
            msg.put("fk_parte",fk_parte);
            msg.put("email",email);
            data = msg.toString();

        /* Log.w("JSON_SUBIDA", String.valueOf(msg));*/
        URL urlws = null;
        HttpsURLConnection uc = null;
        try {
            urlws = new URL("https://"+cliente.getIp_cliente()+ Constantes.URL_MAIL_PARTE);
            uc = (HttpsURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"https://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 404);
            error.put("mensaje", "Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"https://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 505);
            error.put("mensaje", "Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"https://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 436);
            error.put("mensaje", "Error de conexion, IOException");
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
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"https://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, IOException");
            return error.toString();
        }
        return contenido;
    }
}
