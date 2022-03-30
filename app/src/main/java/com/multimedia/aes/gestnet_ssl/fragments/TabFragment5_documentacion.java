package com.multimedia.aes.gestnet_ssl.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_ssl.adaptador.AdaptadorListaImagenes;
import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_ssl.dao.ImagenDAO;
import com.multimedia.aes.gestnet_ssl.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO;
import com.multimedia.aes.gestnet_ssl.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_ssl.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_ssl.entidades.Imagen;
import com.multimedia.aes.gestnet_ssl.entidades.Maquina;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.entidades.Usuario;
import com.multimedia.aes.gestnet_ssl.nucleo.Camara;
import com.multimedia.aes.gestnet_ssl.clases.DataImagenes;
import com.multimedia.aes.gestnet_ssl.nucleo.Index;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TabFragment5_documentacion extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;
    private TextView txtNombreFirma;
    private Button btnFirmaCliente,btnArchivo,btnFoto;
    private ImageView ivFirmaCliente;
    private LinearLayout llFirmaCliente;
    private static ListView lvImagenes;
    private static Parte parte = null;
    private Usuario usuario = null;
    private Maquina maquina = null;
    private DatosAdicionales datos = null;
    private static AdaptadorListaImagenes adaptadorListaImagenes;
    public static ArrayList<DataImagenes> arraylistImagenes = new ArrayList<>();
    public static int alto=0, height;
    private static Context context;
    public static List<Imagen> listaImagenes = new ArrayList<>();
    //METODO
    private void inicializar(){
        //TEXTVIEW

        btnArchivo = (Button)vista.findViewById(R.id.btnArchivo);
        btnFoto = (Button)vista.findViewById(R.id.btnFoto);
        //IMAGEVIEW

        //LINEARLAYOUT

        //LISTVIEW
        lvImagenes = (ListView)vista.findViewById(R.id.lvImagenes);
        //ONCLICK
        btnArchivo.setOnClickListener(this);
        btnFoto.setOnClickListener(this);
        darValores();
    }
    private void darValores(){
        context = getContext();
        Display display = ((Index)getContext()).getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        height=height/16;

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
    public static void result(String path){
        File image = new File(path);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        bitmap = resizeImage(bitmap);
        String nombre = path.substring(path.lastIndexOf('/')+1,path.length());
        ImagenDAO.newImagen(context, nombre, path, parte.getId_parte(),-1,false,false);
        llenarLista();
    }
    public static void llenarLista(){
        arraylistImagenes.clear();
        try {
            listaImagenes= ImagenDAO.buscarImagenPorFk_parte(context,parte.getId_parte());
            if(listaImagenes.size()>0) {
                for (Imagen img : listaImagenes) {
                    arraylistImagenes.add(new DataImagenes(img.getId_imagen(),img.getRuta_imagen(), img.getNombre_imagen(), decodeSampledBitmapFromResource(img.getRuta_imagen(),100,100), parte.getId_parte(),true,false));
                    alto+=height;
                }
                adaptadorListaImagenes = new AdaptadorListaImagenes(context, R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
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
    public static void borrarArrayImagenes(int position, Context context){
        arraylistImagenes.remove(position);
        alto-=height;
        lvImagenes.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
        adaptadorListaImagenes = new AdaptadorListaImagenes(context, R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
        lvImagenes.setAdapter(adaptadorListaImagenes);
    }
    public void hacerFoto(){
        Intent i = new Intent(getContext(), Camara.class);
        startActivity(i);
    }
    public static Bitmap loadFirmaClienteFromStorage(int id, Context context) throws SQLException {
        Bitmap b = null;
        try {
            File f = new File(Constantes.PATH, "firmaCliente" + id + ".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }
    private void cogerFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
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
    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment5_documentacion, container, false);
        JSONObject jsonObject = null;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            idParte = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            parte = ParteDAO.buscarPartePorId(getContext(), idParte);
            usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(getContext(),parte.getFk_tecnico());
            maquina = MaquinaDAO.buscarMaquinaPorFkMaquina(getContext(),parte.getFk_maquina());
            datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(),parte.getId_parte());
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        inicializar();
        return vista;
    }
    @Override
    public void onClick(View view) {

    if (view.getId()==R.id.btnFoto){
            hacerFoto();
        }else if (view.getId()==R.id.btnArchivo){
            cogerFoto();
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 99) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bitmap = null;
                try {
                    bitmap = loadFirmaClienteFromStorage(parte.getId_parte(), getContext());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                ivFirmaCliente.setImageBitmap(bitmap);
                try {
                    ParteDAO.actualizarFirma64(getContext(),parte.getId_parte(),encodedImage);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    parte = ParteDAO.buscarPartePorId(getContext(),parte.getId_parte());
                    txtNombreFirma.setText(parte.getNombre_firmante());
                    btnFirmaCliente.setVisibility(View.GONE);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
            }
        }else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                result(getPath(selectedImage));
            }
        }
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
}