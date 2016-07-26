package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class Firmar extends Activity implements View.OnClickListener {
    private Button btnGuardar,btnBorrar;
    private ImageView ivFirma;
    private FrameLayout frFirma;
    private String path = "/data/data/com.multimedia.aes.gestnet_sgsv2.nucleo/app_imageDir";
    private char chEuro = '€';
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firmar);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        ivFirma = (ImageView) findViewById(R.id.ivFirma);
        frFirma = (FrameLayout) findViewById(R.id.frFirma);
        btnGuardar.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnBorrar){
            recreate();
        }else if (view.getId()==R.id.btnGuardar){
            Bitmap bitmap = Bitmap.createBitmap( frFirma.getWidth(), frFirma.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            frFirma.draw(canvas);
            bitmap = redimensionarImagenMaximo(bitmap,320,320);
            saveToInternalSorage(bitmap);
            finish();
        }
    }
    public Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth, float newHeigth){
        //Redimensionamos
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeigth) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
    }
    private String saveToInternalSorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"firma.jpg");

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }
    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, "firma.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ivFirma.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
