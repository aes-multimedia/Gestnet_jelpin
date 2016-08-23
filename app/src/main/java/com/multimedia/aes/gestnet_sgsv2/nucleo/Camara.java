package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.fragment.TabFragment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Camara extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivFoto;
    private Button btnTomarFoto,btnGuardarImagen,btnGuardar;
    private String salida,nombre;
    private EditText etNombre;
    private LinearLayout llNombre;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camara);
        ivFoto = (ImageView)findViewById(R.id.ivFoto);
        btnTomarFoto = (Button)findViewById(R.id.btnTomarFoto);
        btnGuardarImagen = (Button)findViewById(R.id.btnGuardarImagen);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        etNombre = (EditText)findViewById(R.id.etNombre);
        llNombre = (LinearLayout)findViewById(R.id.llNombre);
        llNombre.setVisibility(View.GONE);
        btnTomarFoto.setOnClickListener(this);
        btnGuardarImagen.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnGuardarImagen.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnGuardarImagen){
            llNombre.setVisibility(View.VISIBLE);
        }else if(v.getId()==R.id.btnTomarFoto){
            llNombre.setVisibility(View.GONE);
            getCamera();
        }else if (v.getId()==R.id.btnGuardar){
            if (etNombre.getText().toString().trim().equals("")){
                Toast.makeText(this, "Rellene el nombre del documento", Toast.LENGTH_SHORT).show();
            }else{
                nombre=etNombre.getText().toString();
                guardarImagen();
            }
        }
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       if (requestCode == 01 && resultCode==RESULT_OK){
           bitmap = (Bitmap) data.getExtras().get("data");
           ivFoto.setImageBitmap(bitmap);
           btnGuardarImagen.setVisibility(View.VISIBLE);
       }
   }

    private void guardarImagen (){
        ContextWrapper cw = new ContextWrapper(this);
        File dirImages = cw.getDir("Imagenes", Context.MODE_PRIVATE);
        File myPath = new File(dirImages,  nombre + ".png");
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(myPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TabFragment3.result(myPath.getAbsolutePath());
        finish();
    }
    private void getCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,01);
    }

}