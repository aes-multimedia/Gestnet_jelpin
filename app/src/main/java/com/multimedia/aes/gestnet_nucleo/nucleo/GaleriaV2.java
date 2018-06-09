package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaImagenes;
import com.multimedia.aes.gestnet_nucleo.clases.DataImagenes;
import com.multimedia.aes.gestnet_nucleo.dao.ImagenDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Imagen;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GaleriaV2 extends AppCompatActivity implements View.OnClickListener, IPickResult{



    private static ListView lvImagenes;
    private static Parte parte = null;
    public static ArrayList<DataImagenes> arraylistImagenes = new ArrayList<>();
    public static List<Imagen> listaImagenes = new ArrayList<>();
    private static Context context;
    private static AdaptadorListaImagenes adaptadorListaImagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_v2);
        GaleriaV2.context = getApplicationContext();
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
    private void inicializar(){

        ImageButton btnAñadirImagen;
        btnAñadirImagen = findViewById(R.id.btnAñadirImagenGaleria);
        lvImagenes = findViewById(R.id.lvImagenes);
        btnAñadirImagen.setOnClickListener(this);
        darValores();
    }




    private void darValores()  {

        arraylistImagenes.clear();
        try {
            listaImagenes= ImagenDAO.buscarImagenPorFk_parte(this,parte.getId_parte());
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
        } catch (OutOfMemoryError memoryError){
            memoryError.printStackTrace();


        }catch (NullPointerException e){
            e.printStackTrace();


        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Bitmap resizeImage(Bitmap bitmap) throws OutOfMemoryError{

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
        try {
            bitmap = resizeImage(bitmap);
        } catch (OutOfMemoryError memoryError){
            memoryError.printStackTrace();
            Dialogo.dialogoError("No hay espacio suficiente en su telefono movil, es probable que las imagenes no puedan ser cargadas debido a esta falta de memoria, porfavor libere espacio",getAppContext());
        }

        String nombre = path.substring(path.lastIndexOf('/')+1,path.length());
        ImagenDAO.newImagen(getAppContext(), nombre, path, parte.getId_parte());
        arraylistImagenes.add(new DataImagenes(path,nombre,bitmap,parte.getId_parte()));
        adaptadorListaImagenes = new AdaptadorListaImagenes(getAppContext(), R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
        lvImagenes.setAdapter(adaptadorListaImagenes);

    }

    public static Context getAppContext() {
        return GaleriaV2.context;
    }

    @Override
    public void onClick(View v) {

        PickImageDialog.build(new PickSetup()
                .setTitle("Selecciona una opción")
                .setCameraButtonText("Camara")
                .setGalleryButtonText("Galeria")
                .setCancelText("CANCELAR")
                .setCancelTextColor(Color.RED)).show(this);

    }

    @Override
    public void onPickResult(PickResult pickResult) {

        result(pickResult.getPath());


    }





}
