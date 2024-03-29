package com.multimedia.aes.gestnet_ssl.nucleo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_ssl.adaptador.AdaptadorListaImagenes;
import com.multimedia.aes.gestnet_ssl.clases.DataImagenes;
import com.multimedia.aes.gestnet_ssl.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_ssl.dao.ImagenDAO;
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.entidades.Imagen;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.hilos.HiloSubirImagen;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

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
    private static Context thisContext;
    static ImageButton btnSubir;
    private static int fk_tipo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_v2);
        GaleriaV2.context = getApplicationContext();
        thisContext = this;
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
        btnSubir = findViewById(R.id.btnSubirPendientes);
        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(thisContext)
                        .setTitle("Atención")
                        .setMessage("¿Estas seguro de que deseas enviar las imagenes pendientes por subir?")
                        .setPositiveButton("Sí", (dialog, which) -> {
                            if(listaImagenes.size()>0) {
                                for (int i = 0; i < listaImagenes.size(); i++) {
                                    if(!listaImagenes.get(i).isEnviado()){
                                        if (hayConexion(context))
                                            new HiloSubirImagen(context, parte.getId_parte(), listaImagenes.get(i).getId_imagen(), 1).execute();
                                        else
                                        {
                                            Toast.makeText(GaleriaV2.this, "No dispones de conexión a internet para poder subir las imágenes.", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }
                                }
                            }
                            Toast.makeText(GaleriaV2.this, "Imágenes enviadas correctamente.", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        })
                        .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss()).show();


            }
        });
        btnAñadirImagen.setOnClickListener(this);
        darValores();
    }
    public static void darValores()  {
        arraylistImagenes.clear();
        btnSubir.setVisibility(View.INVISIBLE);
        try {
            listaImagenes= ImagenDAO.buscarImagenPorFk_parte(context,parte.getId_parte());
            if(listaImagenes.size()>0) {
                for (Imagen img : listaImagenes) {
                    if(!img.isEnviado() && ConfiguracionDAO.buscarConfiguracion(context).isbSubidaInmediataImagen())
                        btnSubir.setVisibility(View.VISIBLE);
                    arraylistImagenes.add(new DataImagenes(img.getId_imagen(),img.getRuta_imagen(), img.getNombre_imagen(), decodeSampledBitmapFromResource(img.getRuta_imagen(),100,100), parte.getId_parte(),true,false));
                }
                adaptadorListaImagenes = new AdaptadorListaImagenes(getAppContext(), R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
                lvImagenes.setAdapter(adaptadorListaImagenes);
            }
        } catch (OutOfMemoryError | NullPointerException | SQLException memoryError){
            memoryError.printStackTrace();
        }
    }

    public static void borrarArrayImagenes(int position, Context context){
        new AlertDialog.Builder(thisContext)
                .setTitle("Atención")
                .setMessage("¿Estas seguro de que deseas borrar la imagen?")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    try {
                        ImagenDAO.borrarImagenPorRuta(context,arraylistImagenes.get(position).ruta);
                        arraylistImagenes.remove(position);
                        adaptadorListaImagenes = new AdaptadorListaImagenes(context, R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
                        lvImagenes.setAdapter(adaptadorListaImagenes);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss()).show();
    }

    public static void result(String path){
        try {
            String nombre = path.substring(path.lastIndexOf('/')+1,path.length());

            boolean enviado = false;
            ImagenDAO.newImagen(getAppContext(), nombre, path, parte.getId_parte(),-1,true, enviado, fk_tipo);
            if(ConfiguracionDAO.buscarConfiguracion(context).isbSubidaInmediataImagen()){
                if(!hayConexion(context)){
                    Toast.makeText(context, "No dispones de conexión para enviar la imagen en estos momentos.", Toast.LENGTH_LONG).show();
                } else {
                    List<Imagen> a = ImagenDAO.buscarImagenPorFk_parte(context, parte.getId_parte());
                    new HiloSubirImagen(context, parte.getId_parte(), a.get(a.size() -1).getId_imagen(), 1).execute();
                }
            } else {
                darValores();
            }


        } catch (OutOfMemoryError | SQLException memoryError){
            memoryError.printStackTrace();
            //Dialogo.dialogoError("No hay espacio suficiente en su telefono movil, es probable que las imagenes no puedan ser cargadas debido a esta falta de memoria, porfavor libere espacio",getAppContext());
        }
    }
    public static boolean hayConexion(Context c) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (NetworkInfo rede : redes) {
            // Si alguna red tiene conexión, se devuelve true
            if (rede.getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

    public static Context getAppContext() {
        return GaleriaV2.context;
    }

    @Override
    public void onClick(View v) {
        var tmp = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Es esta foto del antes, del después o de un desperfecto?");
        builder.setNeutralButton("Antes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fk_tipo = 1;
                PickImageDialog.build(new PickSetup()
                        .setTitle("Selecciona una opción")
                        .setCameraButtonText("Camara")
                        .setGalleryButtonText("Galeria")
                        .setCancelText("CANCELAR")
                        .setCancelTextColor(Color.RED)).show(tmp);
            }
        });
        builder.setNegativeButton("Después", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fk_tipo = 2;
                PickImageDialog.build(new PickSetup()
                        .setTitle("Selecciona una opción")
                        .setCameraButtonText("Camara")
                        .setGalleryButtonText("Galeria")
                        .setCancelText("CANCELAR")
                        .setCancelTextColor(Color.RED)).show(tmp);
            }
        });
        builder.setPositiveButton("Desperfectos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fk_tipo = 3;
                PickImageDialog.build(new PickSetup()
                        .setTitle("Selecciona una opción")
                        .setCameraButtonText("Camara")
                        .setGalleryButtonText("Galeria")
                        .setCancelText("CANCELAR")
                        .setCancelTextColor(Color.RED)).show(tmp);
            }
        });
        builder.show();

    }

    @Override
    public void onPickResult(PickResult pickResult) {
        result(pickResult.getPath());
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
    public static Bitmap decodeSampledBitmapFromResource(String path, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap b = BitmapFactory.decodeFile(path, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }


}
