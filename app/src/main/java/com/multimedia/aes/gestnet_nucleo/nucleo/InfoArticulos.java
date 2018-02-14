package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class InfoArticulos  extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    private ImageView ivFoto;
    private TextView tvTitulo,tvStock,tvPrecio;
    private CheckBox chkGarantia;
    private Menu menu;
    private int idParte;
    private Articulo articulo;
    private Parte parte;
    private Button btnAñadirMaterial;


    private void inicializarVariables(){
        ivFoto = (ImageView) findViewById(R.id.expandedImage);
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvStock = (TextView) findViewById(R.id.tvStock);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        btnAñadirMaterial=(Button)findViewById(R.id.btnAñadirMaterial);
        btnAñadirMaterial.setOnClickListener(this);

        chkGarantia = ( CheckBox ) findViewById( R.id.chkGarantia );
        chkGarantia.setOnCheckedChangeListener(this);

    }
    private void darValores(){
      //ivFoto.setImageResource(articulo.getImagen());
        tvTitulo.setText(articulo.getNombre_articulo());
        tvStock.setText(String.valueOf(articulo.getStock()));
        tvPrecio.setText(String.valueOf(articulo.getTarifa())+ "\u20ac");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_articulo);
        buscarStockAlmacenes();
        idParte = 0;
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(this));
            idParte = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int id = getIntent().getIntExtra("articuloId",-1);
        try {
            articulo = ArticuloDAO.buscarArticuloPorID(this,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inicializarVariables();
        darValores();
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });



    }

    private void buscarStockAlmacenes() {





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_info) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    @Override
    public void onClick(View v) {
        try {
            if (ArticuloParteDAO.buscarArticuloPartePorFkParteFkArticulo(this,articulo.getId_articulo(),idParte)!=null){
                ArticuloParte articuloParte = ArticuloParteDAO.buscarArticuloPartePorFkParteFkArticulo(this,articulo.getId_articulo(),idParte);
                ArticuloParteDAO.actualizarArticuloParte(this,articuloParte.getId(),articuloParte.getUsados()+1);
            }else{
                if(ArticuloParteDAO.newArticuloParte(this,articulo.getId_articulo(),idParte,1)){
                }
            }
            try {
                ArticuloDAO.actualizarArticulo(this,articulo.getId_articulo(),articulo.getNombre_articulo(),articulo.getStock()-1,articulo.getCoste());
            } catch (SQLException e) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED,returnIntent);
                finish();
                e.printStackTrace();
            }
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK,returnIntent);
            finish();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        try {

        if ( isChecked )
        {
            Toast.makeText(this,"true",Toast.LENGTH_SHORT).show();
                ArticuloDAO.actualizarGarantia(this,articulo.getId_articulo(),true);
        }else{
            Toast.makeText(this,"false",Toast.LENGTH_SHORT).show();
                ArticuloDAO.actualizarGarantia(this,articulo.getId_articulo(),false);
                }
             }   catch (SQLException e) {
            e.printStackTrace();
        }
    }
}