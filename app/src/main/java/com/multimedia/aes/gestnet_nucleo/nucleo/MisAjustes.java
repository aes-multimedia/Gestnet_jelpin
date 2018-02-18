package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class MisAjustes extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuario;
    private Cliente cliente;
    private Button btnFirmar,btnSalir,btnAvisoGuardia;
    private ImageView ivMiFirma;

    //METODOS
    private void inicializar() {
        //BUTTON
        btnFirmar = (Button) findViewById(R.id.btnFirmar);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnAvisoGuardia = (Button) findViewById(R.id.btnAvisoGuardia);
        //IMAGEVIEW
        ivMiFirma = (ImageView) findViewById(R.id.ivMiFirma);
        //ONCLICK
        btnFirmar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        btnAvisoGuardia.setOnClickListener(this);
    }

    private void darValores() throws SQLException {
        if (!usuario.getFirma().equals("")) {
            btnFirmar.setVisibility(View.GONE);
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
    private void avisoGuardia(){
        String ip = cliente.getIp_cliente();
        String fk_tecnico = usuario.getFk_entidad()+"";
        String url = "http://"+ip+"/webservices/webview/avisoGuardia.php?fk_tecnico="+fk_tecnico;
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        //
    }

    //OVERRIDE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_ajustes);
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
        }else if (v.getId() == R.id.btnAvisoGuardia) {
            avisoGuardia();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 99) {
            if (resultCode == Activity.RESULT_OK) {
                btnFirmar.setVisibility(View.GONE);
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