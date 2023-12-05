package com.multimedia.aes.gestnet_ssl.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.EnvioDAO;
import com.multimedia.aes.gestnet_ssl.dao.ImagenDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Imagen;
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

import java.net.HttpURLConnection;

public class HiloImagenesAcciones extends AsyncTask<Void,Void,Void> {


    private Context context;
    private ProgressDialog dialog;
    private String mensaje;
    private int fk_accion,fk_parte,fk_maquina;
    private Cliente cliente;

    public HiloImagenesAcciones(Context context,int fk_accion, int fk_parte, int fk_maquina) {
        this.context=context;
        this.fk_accion=fk_accion;
        this.fk_parte=fk_parte;
        this.fk_maquina=fk_maquina;
        try {
            cliente= ClienteDAO.buscarCliente(context);
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
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando imagenes.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..."+"\n"+"Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        if (mensaje.indexOf('}')!=-1){
            try {
                JSONObject jsonObject = new JSONObject(mensaje);
                if (jsonObject.getInt("estado")==1){
                    ((FotosProtocoloAccion)context).resultOk();
                }else{
                    JSONArray jsonArray = jsonObject.getJSONArray("imagenes");
                    String error = jsonObject.getString("mensaje");
                    ((FotosProtocoloAccion)context).resultErrorImagen(jsonArray,error);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                ((FotosProtocoloAccion)context).resultError();
            }
        }else{
            ((FotosProtocoloAccion)context).resultError();
        }
    }
    private String iniciar() throws JSONException, IOException, SQLException {
        JSONObject msg = new JSONObject();
        msg.put("fk_accion_protocolo", fk_accion);
        msg.put("fk_parte", fk_parte);
        msg.put("fk_maquina", fk_maquina);
        msg.put("imagenes", rellenarJsonImagenes(fk_accion));
        String dataJsonMsg = msg.toString();
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL("http://"+cliente.getIp_cliente()+Constantes.URL_IMAGENES_ACCIONES);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_DIA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_DIA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_DIA,jsonArray.toString());
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
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_DIA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, IOException");
            return error.toString();
        }
        return contenido;
    }
    private JSONArray rellenarJsonImagenes(int fk_accion) throws  IOException, SQLException {
        List<Imagen> arraylistImagenes = new ArrayList<>();
        JSONArray jsa = new JSONArray();
        if(ImagenDAO.buscarImagenPorFkProtocoloAccionNoEnviados(context,fk_accion)!=null) {
            arraylistImagenes.addAll(ImagenDAO.buscarImagenPorFkProtocoloAccionNoEnviados(context, fk_accion));
            for (int i = 0; i < arraylistImagenes.size(); i++) {
                JSONObject jso = new JSONObject();
                File f = new File(arraylistImagenes.get(i).getRuta_imagen());
                Bitmap b;
                b = resizeImage(f.getAbsolutePath());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                b.getByteCount();
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                try {
                    jso.put("nombre", arraylistImagenes.get(i).getNombre_imagen());
                    jso.put("base64", encodedImage);
                    jsa.put(jso);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsa;
    }
    public static Bitmap resizeImage(String file) throws OutOfMemoryError{
        Bitmap bitmap = decodeSampledBitmapFromResource(file,800,600);
        Bitmap BitmapOrg = bitmap;
        return bitmap;
    }
    public static Bitmap decodeSampledBitmapFromResource(String path, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
