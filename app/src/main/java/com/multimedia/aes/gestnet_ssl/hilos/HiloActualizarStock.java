package com.multimedia.aes.gestnet_ssl.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.nucleo.Index;

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

public class HiloActualizarStock extends AsyncTask<Void,Void,Void> {
    private int fk_entidad;
    private String mensaje;
    private Context context;
    private ProgressDialog dialog;
    Cliente cliente;

    public HiloActualizarStock(Context context,int fk_entidad) {
        this.fk_entidad = fk_entidad;
        this.context=context;
        try {
            this.cliente= ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Actualizando stock del almacén");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
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
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}')!=-1){
            actualizarStock(mensaje);
            dialog.dismiss();
        }else{
            ((Index) context).sacarMensaje(mensaje);
        }
    }
    private String iniciar() throws JSONException {
        JSONObject msg = new JSONObject();
        msg.put("fkEntidad", fk_entidad);
        URL urlws = null;
        HttpsURLConnection uc = null;
        try {
            urlws = new URL("https://"+cliente.getIp_cliente()+ Constantes.URL_ACTUALZIAR_STOCK_ALMACEN);
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
            error.put("mensaje", "Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
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
            e.printStackTrace();
        }
        return contenido;
    }

    private  void actualizarStock(String mensaje) {

        try {
            JSONArray jsonElements = new JSONArray(mensaje);
            for (int i = 0; i < jsonElements.length(); i++) {


                int id;
                if (jsonElements.getJSONObject(i).getString("id_articulo").equals("") || jsonElements.getJSONObject(i).getString("id_articulo").equals("null"))
                    id = 0;
                else
                    id = jsonElements.getJSONObject(i).getInt("id_articulo");


                double stock;
                if (jsonElements.getJSONObject(i).getString("cantidad").equals("") || jsonElements.getJSONObject(i).getString("cantidad").equals("null"))
                    stock = 0;
                else
                    stock = jsonElements.getJSONObject(i).getDouble("cantidad");




                ArticuloDAO.actualizarStockPorfK(context,id,stock);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

}
