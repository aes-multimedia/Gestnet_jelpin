package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Display;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaImagenes;
import com.multimedia.aes.gestnet_nucleo.clases.DataImagenes;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ImagenDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Imagen;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Galeria extends AppCompatActivity implements View.OnClickListener{





    private static ListView lvImagenes;
    private static Parte parte = null;
    public static ArrayList<DataImagenes> arraylistImagenes = new ArrayList<>();
    public static List<Imagen> listaImagenes = new ArrayList<>();
    private static Context context;
    private static AdaptadorListaImagenes adaptadorListaImagenes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        Galeria.context = getApplicationContext();
        JSONObject jsonObject = null;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(this));
            idParte = jsonObject.getInt("id");
            parte = ParteDAO.buscarPartePorId(this, idParte);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inicializar();

    }

    public static Context getAppContext() {
        return Galeria.context;
    }

    private void inicializar(){

        Button btnArchivo,btnFoto;
        btnArchivo = findViewById(R.id.btnArchivo);
        btnFoto = findViewById(R.id.btnFoto);
        lvImagenes = findViewById(R.id.lvImagenes);
        btnArchivo.setOnClickListener(this);
        btnFoto.setOnClickListener(this);
        darValores();
    }
    private void darValores()  {

        arraylistImagenes.clear();
        try {
            listaImagenes=ImagenDAO.buscarImagenPorFk_parte(this,parte.getId_parte());
            if(listaImagenes.size()>0) {
                for (Imagen img : listaImagenes) {
                    File image = new File(img.getRuta_imagen());
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
                    bitmap = resizeImage(bitmap);
                    arraylistImagenes.add(new DataImagenes(img.getRuta_imagen(), img.getNombre_imagen(), bitmap, parte.getId_parte()));
                }
                adaptadorListaImagenes = new AdaptadorListaImagenes(getAppContext(), R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
                lvImagenes.setAdapter(adaptadorListaImagenes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();


        }

    }


    public void hacerFoto(){
        Intent i = new Intent(this, Camara.class);
        startActivity(i);
    }
    private void cogerFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                result(getPath(selectedImage));
            }
        }
    }

    public static String getPath(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        assert cursor!=null;
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);

        }
        cursor.close();
        return res;
    }

    public static void borrarArrayImagenes(int position, Context context){
        try {
        ImagenDAO.borrarImagenPorRuta(context,arraylistImagenes.get(position).ruta);
        arraylistImagenes.remove(position);
        adaptadorListaImagenes = new AdaptadorListaImagenes(context, R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
        lvImagenes.setAdapter(adaptadorListaImagenes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void result(String path){

        File image = new File(path);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        bitmap = resizeImage(bitmap);
        String nombre = path.substring(path.lastIndexOf('/')+1,path.length());
        ImagenDAO.newImagen(getAppContext(), nombre, path, parte.getId_parte());
        arraylistImagenes.add(new DataImagenes(path,nombre,bitmap,parte.getId_parte()));
        adaptadorListaImagenes = new AdaptadorListaImagenes(getAppContext(), R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
        lvImagenes.setAdapter(adaptadorListaImagenes);

    }

    public static Bitmap resizeImage(Bitmap bitmap) {

        Bitmap BitmapOrg = bitmap;

        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();

        if(width>1000&&height>1000) {
            int newWidth = (width * 50) / 100;
            int newHeight = (height * 50) / 100;

            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            Matrix matrix = new Matrix();

            matrix.postScale(scaleWidth, scaleHeight);

            Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                    width, height, matrix, true);

            return resizedBitmap;
        }else if (width>1500&&height>1500) {
            int newWidth = (width * 50) / 100;
            int newHeight = (height * 50) / 100;

            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            Matrix matrix = new Matrix();

            matrix.postScale(scaleWidth, scaleHeight);

            Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                    width, height, matrix, true);

            return resizedBitmap;
        }else if (width>2000&&height>2000) {
            int newWidth = (width * 50) / 100;
            int newHeight = (height * 50) / 100;

            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            Matrix matrix = new Matrix();

            matrix.postScale(scaleWidth, scaleHeight);

            Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                    width, height, matrix, true);

            return resizedBitmap;
        }else{
            return bitmap;
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.btnFoto){
            hacerFoto();
        }else if (view.getId()==R.id.btnArchivo){
            cogerFoto();
        }

    }




}
