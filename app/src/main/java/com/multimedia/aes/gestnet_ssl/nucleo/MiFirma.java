package com.multimedia.aes.gestnet_ssl.nucleo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Usuario;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class MiFirma extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private Cliente cliente;
    private Button btnFirmar,btnSalir;
    private ImageView ivMiFirma;

    //METODOS
    private void inicializar() {
        //BUTTON
        btnFirmar = (Button) findViewById(R.id.btnFirmar);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        //IMAGEVIEW
        ivMiFirma = (ImageView) findViewById(R.id.ivMiFirma);
        //ONCLICK
        btnFirmar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

    }

    private void darValores() throws SQLException {
        if (!usuario.getFirma().equals("")) {
            ivMiFirma.setImageBitmap(loadFirmaTecnicoFromStorage(this));
        }else{
            btnFirmar.setVisibility(View.VISIBLE);
            ivMiFirma.setVisibility(View.GONE);
        }
    }

    public static Bitmap loadFirmaTecnicoFromStorage(Context context) throws SQLException {
        Bitmap b = null;
        try {
            File f = new File(Constantes.PATH, "firmaTecnico.png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }


    //OVERRIDE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_firma);
        try {
            usuario = UsuarioDAO.buscarUsuario(this);
            cliente = ClienteDAO.buscarCliente(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inicializar();
        try {
            darValores();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnFirmar) {
            Intent i = new Intent(this, FirmaTecnico.class);
            startActivityForResult(i, 99);
        }else if (v.getId() == R.id.btnSalir) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 99) {
            if (resultCode == Activity.RESULT_OK) {
                ivMiFirma.setVisibility(View.VISIBLE);
                Bitmap bitmap = null;
                try {
                    bitmap = loadFirmaTecnicoFromStorage(this);
                    ivMiFirma.setImageBitmap(bitmap);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                try {
                    UsuarioDAO.actualizarFirma(this,usuario.getId_usuario(),encodedImage);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
