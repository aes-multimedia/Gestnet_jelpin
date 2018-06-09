package com.multimedia.aes.gestnet_nucleo.clases;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.multimedia.aes.gestnet_nucleo.nucleo.GaleriaV2.resizeImage;

public class Presupuesto {


    private int fk_usuario;
    private int fk_user_creador;
    private int fk_tecnico_origen;
    private int fk_tipo_presupuesto;
    private int fk_tipo_trabajo;
    private int fk_parte;
    private String observaciones_presupuesto;
    private ArrayList<String> listaImagenes;

    public Presupuesto() {



    }

    public Presupuesto(int fk_usuario, int fk_user_creador, int fk_tecnico_origen, int fk_tipo_presupuesto, int fk_tipo_trabajo, String observaciones_presupuesto, ArrayList<String> listaImagenes) {
        this.fk_usuario = fk_usuario;
        this.fk_user_creador = fk_user_creador;
        this.fk_tecnico_origen = fk_tecnico_origen;
        this.fk_tipo_presupuesto = fk_tipo_presupuesto;
        this.fk_tipo_trabajo = fk_tipo_trabajo;
        this.observaciones_presupuesto = observaciones_presupuesto;
        this.listaImagenes = listaImagenes;
    }


    public int getFk_usuario() {
        return fk_usuario;
    }

    public Presupuesto setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
        return this;
    }

    public int getFk_user_creador() {
        return fk_user_creador;
    }

    public Presupuesto setFk_user_creador(int fk_user_creador) {
        this.fk_user_creador = fk_user_creador;
        return this;
    }

    public int getFk_tecnico_origen() {
        return fk_tecnico_origen;
    }

    public Presupuesto setFk_tecnico_origen(int fk_tecnico_origen) {
        this.fk_tecnico_origen = fk_tecnico_origen;
        return this;
    }

    public int getFk_tipo_presupuesto() {
        return fk_tipo_presupuesto;
    }

    public Presupuesto setFk_tipo_presupuesto(int fk_tipo_presupuesto) {
        this.fk_tipo_presupuesto = fk_tipo_presupuesto;
        return this;
    }

    public int getFk_tipo_trabajo() {
        return fk_tipo_trabajo;
    }

    public Presupuesto setFk_tipo_trabajo(int fk_tipo_trabajo) {
        this.fk_tipo_trabajo = fk_tipo_trabajo;
        return this;
    }

    public String getObservaciones_presupuesto() {
        return observaciones_presupuesto;
    }

    public Presupuesto setObservaciones_presupuesto(String observaciones_presupuesto) {
        this.observaciones_presupuesto = observaciones_presupuesto;
        return this;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;

    }

    public Presupuesto setListaImagenes(ArrayList<String> listaImagenes) {
        this.listaImagenes = listaImagenes;
        return this;
    }

    public int getFk_parte() {
        return fk_parte;
    }

    public Presupuesto setFk_parte(int fk_parte) {
        this.fk_parte = fk_parte;
        return this;
    }

    public void serializarImagenes(){


        ArrayList<String> a = new ArrayList<>();
        if(!listaImagenes.isEmpty()){
            for (int i = 0; i < listaImagenes.size(); i++) {
                File f = new File(listaImagenes.get(i));
                Bitmap b = null;

                try {

                    b = BitmapFactory.decodeStream(new FileInputStream(f));

                } catch (FileNotFoundException e) {
                    Log.e("FileNotFoundException","Presupuestos linea 126");
                    e.printStackTrace();

                }


                b = resizeImage(b);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                a.add(encodedImage);

            }

            this.listaImagenes.clear();
            this.listaImagenes=a;

        }


    }


}
