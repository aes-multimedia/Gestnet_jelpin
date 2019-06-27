package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaImagenes;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaImagenesAccion;
import com.multimedia.aes.gestnet_nucleo.clases.DataImagenes;
import com.multimedia.aes.gestnet_nucleo.dao.ImagenDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Imagen;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloBorrarImagenesAcciones;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloImagenesAcciones;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FotosProtocoloAccion extends AppCompatActivity implements View.OnClickListener, IPickResult {

    private static RecyclerView lvImagenes;
    private static GridLayoutManager layoutManager;

    private ImageView ivAtras;
    private ImageView btnAñadirImagenGaleria;
    private static ImageView btnEnviar;
    private TextView txtProtocolo, txtAccion;
    public static ArrayList<DataImagenes> arraylistImagenes = new ArrayList<>();
    private static AdaptadorListaImagenesAccion adaptadorListaImagenes;
    public static List<Imagen> listaImagenes = new ArrayList<>();
    private int id;
    private static ProtocoloAccion protocoloAccion;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fotos_protocolo_accion);
        context = this;
        id = getIntent().getIntExtra("id", -1);
        try {
            if (id != -1 && ProtocoloAccionDAO.buscarProtocoloAccionPorIdProtocoloAccion(this, id) != null) {
                protocoloAccion = ProtocoloAccionDAO.buscarProtocoloAccionPorIdProtocoloAccion(this, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inicializarVariables();
        darValorVariables();
    }

    private void darValorVariables() {
        txtProtocolo.setText(protocoloAccion.getNombre_protocolo());
        txtAccion.setText(protocoloAccion.getDescripcion());
        llenarLista();
    }

    private void inicializarVariables() {
        ivAtras = findViewById(R.id.ivAtras);
        txtProtocolo = findViewById(R.id.txtProtocolo);
        txtAccion = findViewById(R.id.txtAccion);
        btnAñadirImagenGaleria = findViewById(R.id.btnAñadirImagenGaleria);
        lvImagenes = findViewById(R.id.lvImagenes);
        btnEnviar = findViewById(R.id.btnEnviar);

        ivAtras.setOnClickListener(this);
        btnAñadirImagenGaleria.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);
    }

    private static void llenarLista() {
        arraylistImagenes.clear();
        listaImagenes.clear();
        boolean enviado = true;
        try {
            if (ImagenDAO.buscarImagenPorFkProtocoloAccion(context, protocoloAccion.getId_protocolo_accion())!=null){
                listaImagenes = ImagenDAO.buscarImagenPorFkProtocoloAccion(context, protocoloAccion.getId_protocolo_accion());
            }
            if (listaImagenes.size() > 0) {
                for (Imagen img : listaImagenes) {
                    arraylistImagenes.add(new DataImagenes(img.getId_imagen(), img.getRuta_imagen(), img.getNombre_imagen(), decodeSampledBitmapFromResource(img.getRuta_imagen(), 100, 100), protocoloAccion.getId_protocolo_accion(), img.isGaleria(), img.isEnviado()));
                    if (!img.isEnviado()) {
                        enviado = false;
                    }
                }
            }
            if (listaImagenes.size()==1){
                layoutManager = new GridLayoutManager(context, 1);
            }else{
                layoutManager = new GridLayoutManager(context, 2);
            }

            lvImagenes.setHasFixedSize(true);

            lvImagenes.setLayoutManager(layoutManager);
            adaptadorListaImagenes = new AdaptadorListaImagenesAccion(arraylistImagenes,context);
            lvImagenes.setAdapter(adaptadorListaImagenes);
        } catch (OutOfMemoryError memoryError) {
            memoryError.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (enviado) {
            btnEnviar.setVisibility(View.GONE);
        } else {
            btnEnviar.setVisibility(View.VISIBLE);
        }
    }

    public static Bitmap resizeImage(Bitmap bitmap) throws OutOfMemoryError {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        if (width > 1000 && height > 1000) {
            int newWidth = (width * 50) / 100;
            int newHeight = (height * 50) / 100;
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                    width, height, matrix, true);
            return resizedBitmap;
        } else {
            return bitmap;
        }
    }

    public static void borrarArrayImagenes(int position, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Atención")
                .setMessage("¿Estas seguro de que deseas borrar la imagen, tambien se borrara de gestnet?")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    if (arraylistImagenes.get(position).enviado) {
                        new HiloBorrarImagenesAcciones(context, protocoloAccion.getId_protocolo_accion(), arraylistImagenes.get(position).nombre, arraylistImagenes.get(position).id,protocoloAccion.getFk_parte(),protocoloAccion.getFk_maquina()).execute();
                    } else {
                        try {
                            ImagenDAO.borrarImagenPorRuta(context, arraylistImagenes.get(position).ruta);
                            llenarLista();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    dialog.dismiss();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss()).show();
    }

    public static void result(String path) {
        try {
            String nombre = path.substring(path.lastIndexOf('/') + 1, path.length());
            ImagenDAO.newImagen(context, nombre, path, -1, protocoloAccion.getId_protocolo_accion(), false, false);
            llenarLista();
        } catch (OutOfMemoryError memoryError) {
            memoryError.printStackTrace();
            //Dialogo.dialogoError("No hay espacio suficiente en su telefono movil, es probable que las imagenes no puedan ser cargadas debido a esta falta de memoria, porfavor libere espacio",getAppContext());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivAtras) {
            if (comprobarEnviado()) {
                finish();
            } else {
                Dialogo.dialogoError("Hay imagenes sin enviar, porfavor envialas.", this);
            }

        } else if (v.getId() == R.id.btnAñadirImagenGaleria) {
            PickImageDialog.build(new PickSetup()
                    .setTitle("Selecciona una opción")
                    .setCameraButtonText("Camara")
                    .setGalleryButtonText("Galeria")
                    .setCancelText("CANCELAR")
                    .setCancelTextColor(Color.RED)).show(this);
        } else if (v.getId() == R.id.btnEnviar) {
            new HiloImagenesAcciones(this,protocoloAccion.getId_protocolo_accion(),protocoloAccion.getFk_parte(),protocoloAccion.getFk_maquina()).execute();
        }
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
        BitmapFactory.decodeFile(path, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    private boolean comprobarEnviado() {
        boolean result = true;
        for (int i = 0; i < arraylistImagenes.size(); i++) {
            if (!arraylistImagenes.get(i).enviado) {
                result = false;
            }
        }

        return result;
    }

    @Override
    public void onBackPressed() {
        if (comprobarEnviado()) {
            super.onBackPressed();
        } else {
            Dialogo.dialogoError("Hay imagenes sin enviar, porfavor envialas.", this);
        }

    }

    public void resultOk() {
        try {
            List<Imagen> images = ImagenDAO.buscarImagenPorFkProtocoloAccionNoEnviados(context, protocoloAccion.getId_protocolo_accion());
            for (int i = 0; i < images.size(); i++) {
                ImagenDAO.actualizarEnviado(this, images.get(i).getId_imagen(), true);
            }
            Dialogo.dialogoError("Imagenes subidas correctamente.", this);
            llenarLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void resultOkBorrar(int id_imagen) {
        try {
            ImagenDAO.borrarImagenPorID(this, id_imagen);
            Dialogo.dialogoError("Imagen borrada correctamente.", this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        llenarLista();
    }

    public void resultError() {
        Dialogo.dialogoError("Ha habido algun error en la subida de imagenes, porfavor intentelo mas tarde.", this);
    }

    public void resultErrorImagen(JSONArray jsonArray,String error) {
        Dialogo.dialogoError(error, this);
        try {
            List<Imagen> images = ImagenDAO.buscarImagenPorFkProtocoloAccionNoEnviados(context, protocoloAccion.getId_protocolo_accion());
            for (int i = 0; i < images.size(); i++) {
                boolean bien = true;
                for (int j = 0; j < jsonArray.length(); j++) {
                    if (images.get(i).getNombre_imagen().equals(jsonArray.getString(j))){
                        bien = false;
                    }
                }
                if (bien){
                    ImagenDAO.actualizarEnviado(this, images.get(i).getId_imagen(), true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        llenarLista();
    }

    public void resultErrorBorrar() {
        Dialogo.dialogoError("Ha habido algun error en el borrado de imagenes, porfavor intentelo mas tarde.", this);
    }
}
