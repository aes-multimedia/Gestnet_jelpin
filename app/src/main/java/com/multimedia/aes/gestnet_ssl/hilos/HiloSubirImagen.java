package com.multimedia.aes.gestnet_ssl.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.EnvioDAO;
import com.multimedia.aes.gestnet_ssl.dao.ImagenDAO;
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Imagen;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.nucleo.FotosProtocoloAccion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HiloSubirImagen extends AsyncTask<Void,Void,Void> {
    private Context context;
    private ProgressDialog dialog;
    private String mensaje;
    private int fk_parte, id_imagen;
    private Cliente cliente;
    private Parte parte;

    public HiloSubirImagen(Context context, int fk_parte, int id_imagen) {
        this.context=context;
        this.fk_parte=fk_parte;
        this.id_imagen = id_imagen;
        try {
            cliente= ClienteDAO.buscarCliente(context);
            parte = ParteDAO.buscarPartePorId(context, fk_parte);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}')!=-1){
            try {
                JSONObject jsonObject = new JSONObject(mensaje);
                if (jsonObject.getInt("estado")==0){
                    ImagenDAO.actualizarEnviado(context, id_imagen, true);
                }else{
                    Toast.makeText(context, "Error al enviar una imagen.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException | SQLException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error al enviar una imagen.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "El servidor no ha respondido correctamente.", Toast.LENGTH_SHORT).show();
        }
    }
    private String iniciar() throws JSONException, IOException, SQLException {
        JSONObject msg = new JSONObject();
        msg.put("fk_parte", fk_parte);
        msg.put("imagenes", rellenarJsonImagenes(parte));
        String dataJsonMsg = msg.toString();
        URL urlws = null;
        HttpsURLConnection uc = null;
        try {
            urlws = new URL("https://"+cliente.getIp_cliente()+ Constantes.URL_IMAGEN_PARTE);
            uc = (HttpsURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"https://"+cliente.getIp_cliente()+Constantes.URL_IMAGEN_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"https://"+cliente.getIp_cliente()+Constantes.URL_IMAGEN_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"https://"+cliente.getIp_cliente()+Constantes.URL_IMAGEN_PARTE,jsonArray.toString());
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
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"https://"+cliente.getIp_cliente()+Constantes.URL_IMAGEN_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, IOException");
            return error.toString();
        }
        return contenido;
    }
    private JSONArray rellenarJsonImagenes(Parte parte) throws  IOException, SQLException {
        List<Imagen> arraylistImagenes = new ArrayList<>();
        JSONArray jsa = new JSONArray();
        if(ImagenDAO.buscarImagenPorFk_parte(context,fk_parte)!=null) {
            arraylistImagenes.add(ImagenDAO.buscarImagenPorId(context, id_imagen));
            for (int i = 0; i < arraylistImagenes.size(); i++) {
                JSONObject jso = new JSONObject();
                File f = new File(arraylistImagenes.get(i).getRuta_imagen());
                Bitmap b;
                b=ShrinkBitmap(f.getAbsolutePath(),300,300);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                b.getByteCount();

                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                try {
                    jso.put("fk_parte", arraylistImagenes.get(i).getFk_parte());
                    jso.put("nombre", arraylistImagenes.get(i).getNombre_imagen());
                    jso.put("base64", encodedImage);
                    jso.put("firma", "0");
                    jsa.put(jso);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsa;
    }

    private Bitmap ShrinkBitmap(String file, int width, int height){
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bitmap = null;
        int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/(float)height);
        int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/(float)width);
        if (heightRatio > 1 || widthRatio > 1)
        {
            if (heightRatio > widthRatio)
            {
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
            }
        }
        bmpFactoryOptions.inJustDecodeBounds = false;

        try {
            bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        } catch (OutOfMemoryError e) {
            try {
                bmpFactoryOptions.inSampleSize = 2;
                bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);

            } catch(Exception eExc) {
                eExc.printStackTrace();
            }
            e.printStackTrace();
        }
        return bitmap;
    }

}
