package com.multimedia.aes.gestnet_ssl.Utils;

import android.content.Context;

import androidx.appcompat.widget.AppCompatCheckBox;

public class CheckBoxMateriales extends AppCompatCheckBox {

    private int idArticulo;
    private String tipo;

    public CheckBoxMateriales(Context context,int idArticulo,String tipo) {
        super(context);
        this.idArticulo=idArticulo;
        this.tipo=tipo;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
