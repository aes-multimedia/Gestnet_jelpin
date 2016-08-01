package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

public class HiloSubirTicket extends AsyncTask<Void,Void,Void>{
    private String host = "192.168.0.228";
    private String mensaje="";
    private Activity activity;
    private String path = "/data/data/com.multimedia.aes.gestnet_sgsv2/app_imageDir";
    private Mantenimiento mantenimiento=null;
    JSONObject jsonObjectTicket = new JSONObject();

    public HiloSubirTicket(Activity activity) {
        this.activity = activity;
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(activity));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(activity,id);
            generarTexto1();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = subir();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        } catch (IOException e) {
            mensaje = "IOException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(activity, "Imagen ticket subida", Toast.LENGTH_SHORT).show();
    }

    private String subir() throws JSONException, IOException {
        JSONObject msg = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        String base64 = loadTicketFromStorage();
        String logo = loadLogoFromStorage();
        String firma = loadFirmaFromStorage();
        jsonObject.put("name","ticket");
        jsonObject.put("base64",base64);
        msg.put("images",jsonObject);
        msg.put("ticket",jsonObjectTicket);
        msg.put("logo",logo);
        msg.put("firma",firma);
        URL urlws = new URL("http://"+host+"/api-sgs/v1/mantenimientos/carga_imagen");
        HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        uc.addRequestProperty("fk_parte", String.valueOf(mantenimiento.getFk_tecnico()));
        uc.setRequestMethod("POST");
        uc.connect();
        OutputStream os = uc.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(msg.toString());
        osw.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        String contenido = "";
        while ((inputLine = in.readLine()) != null) {
            contenido += inputLine + "\n";
        }
        in.close();
        osw.close();
        return contenido;
    }
    private void generarTexto1() throws JSONException {
        String fecha = "22/06/2016";
        String hora = "12:06";
        String nombre_cliente = "Maria Garcia Hinojosa" + "\n";
        String num_contrato = "000111522";
        String serv = "Mantenimiento Gas";
        String dir = "Calle Ribadavia 11,2-A,"+"\n"+"Madrid,Madrid,20156";
        String emp = "IBERDROLA";
        String noti = "21/06/2016";
        String cif_emp = "02365474S";
        String num_emp_mant = "44556678";
        String tec = "Pedro Buenhombre Lopez";
        String num_insta = "659898741";
        String atend = "18/06/2016-14:00";
        String prev_repar = "26/06/2016-13:30";
        String repa = "24/06/2016-12:48";
        String num_solic = "6547952";
        String cod_ave = "3216565";
        String desc = "Una averia sin importancia";
        String despl = "5 horas:  24 euros";
        String piez = "Junta caldera  5 euros";
        String man_obra = "2 horas: 20 euros";
        String otr = "6 Km 31 euros"+"\n"+"2 Km 12 euros";
        String desc_preiva = "0%";
        String mat = "6 piezas: 29 euros";
        String pres_tot_siniva = "95 euros";
        String iv = "21%";
        String pres_tot_coniva = "102 euros";
        String otr_desc = "0%";
        String tot = "102 euros";
        String obs_tecnico = "La maquina es antigüa";
        String fec_recep = "22/06/2016-13:00";
        String fec_acep = "22/06/2016-13:00";
        String fec_conf = "22/06/2016-13:00";
        String obs_cliente = "muy majo el tecnico";
        jsonObjectTicket.put("fecha",fecha);
        jsonObjectTicket.put("hora",hora);
        jsonObjectTicket.put("nombre_cliente",nombre_cliente);
        jsonObjectTicket.put("numero_contrato",num_contrato);
        jsonObjectTicket.put("servicio",serv);
        jsonObjectTicket.put("direccion",dir);
        jsonObjectTicket.put("empresa",emp);
        jsonObjectTicket.put("cif_empresa",cif_emp);
        jsonObjectTicket.put("numero_empresa_mantenedora",num_emp_mant);
        jsonObjectTicket.put("tecnico",tec);
        jsonObjectTicket.put("numero_instalador",num_insta);
        jsonObjectTicket.put("notificada",noti);
        jsonObjectTicket.put("atendida",atend);
        jsonObjectTicket.put("prevista_reparacion",prev_repar);
        jsonObjectTicket.put("reparada",repa);
        jsonObjectTicket.put("numero_solicitud",num_solic);
        jsonObjectTicket.put("codigo_veria",cod_ave);
        jsonObjectTicket.put("descripcion_averia",desc);
        jsonObjectTicket.put("piezas",piez);
        jsonObjectTicket.put("mano_obra",man_obra);
        jsonObjectTicket.put("desplazamiento",despl);
        jsonObjectTicket.put("otros",otr);
        jsonObjectTicket.put("descuento_preiva",desc_preiva);
        jsonObjectTicket.put("materiales",mat);
        jsonObjectTicket.put("presupuesto_sin_iva",pres_tot_siniva);
        jsonObjectTicket.put("iva",iv);
        jsonObjectTicket.put("presupuesto_con_iva",pres_tot_coniva);
        jsonObjectTicket.put("otros_descuentos",otr_desc);
        jsonObjectTicket.put("total",tot);
        jsonObjectTicket.put("observaciones_tecnico",obs_tecnico);
        jsonObjectTicket.put("fecha_recepcion",fec_recep);
        jsonObjectTicket.put("fecha_aceptacion",fec_acep);
        jsonObjectTicket.put("fecha_conforme",fec_conf);
        jsonObjectTicket.put("fecha_conforme",obs_cliente);
    }
    private String loadLogoFromStorage() throws IOException {
        InputStream bitmap = null;
        bitmap =  activity.getAssets().open("logo.png");
        Bitmap btmp= BitmapFactory.decodeStream(bitmap);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        btmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
        return encodedImage;
    }
    private String loadFirmaFromStorage(){
        Bitmap b=null;
        try {
            File f=new File(path, "firma.png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
        return encodedImage;
    }
    private String loadTicketFromStorage(){
        Bitmap b=null;
        try {
            File f=new File(path, "ticket"+mantenimiento.getId_mantenimiento()+".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
        return encodedImage;
    }
}
