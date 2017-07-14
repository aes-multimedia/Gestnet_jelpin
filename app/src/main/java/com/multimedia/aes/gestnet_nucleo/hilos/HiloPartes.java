package com.multimedia.aes.gestnet_nucleo.hilos;

import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

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

/**
 * Created by acp on 13/07/2017.
 */

public class HiloPartes extends AsyncTask<Void,Void,Void>{


        private String mensaje="";
        private String apikey,tecnico,servidor;
        private Context context;
        private Cliente cliente;

        public HiloPartes(String apikey, String tecnico, String servidor, Context context) {
            this.apikey = apikey;
            this.tecnico = "314";
            this.servidor = "192.168.0.228:8085";
            this.context = context;
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
            if (mensaje.indexOf('}')!=-1){
                ((Index)context).guardarParte(mensaje);
            }else{
                ((Index)context).sacarMensaje("No se ha devuelto correctamente de la api");
            }

        }

        private String partes() throws JSONException{
            JSONObject msg = new JSONObject();
            msg.put("tecnico",tecnico);
            URL urlws = null;
            HttpURLConnection uc = null;
            try {
                urlws = new URL(Constantes.URL_PARTES);
                uc = (HttpURLConnection) urlws.openConnection();
                uc.setDoOutput(true);
                uc.setDoInput(true);
                uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                uc.setRequestMethod("POST");
                uc.connect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                JSONObject error = new JSONObject();
                msg.put("estado",5);
                msg.put("mensaje","Error de conexión, URL malformada");
                return error.toString();
            } catch (ProtocolException e) {
                e.printStackTrace();
                JSONObject error = new JSONObject();
                msg.put("estado",5);
                msg.put("mensaje","Error de conexión, error de protocolo");
                return error.toString();
            } catch (IOException e) {
                JSONObject error = new JSONObject();
                msg.put("estado",5);
                msg.put("mensaje","Error de conexión, IOException");
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
                msg.put("estado",5);
                msg.put("mensaje","Error de conexión, error en lectura");
                contenido = error.toString();
            }


            return contenido;
        }
}
