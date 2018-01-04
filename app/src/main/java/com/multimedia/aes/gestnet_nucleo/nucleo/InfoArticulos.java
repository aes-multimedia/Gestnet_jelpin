package com.multimedia.aes.gestnet_nucleo.nucleo;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;

import java.sql.SQLException;

/**
 * Created by acp on 05/11/2017.
 */

public class InfoArticulos  extends AppCompatActivity {

    private ImageView ivFoto;
    private TextView tvTitulo,tvStock,tvPrecio;
    private Menu menu;
    private Articulo articulo;


    private void inicializarVariables(){
        ivFoto = (ImageView) findViewById(R.id.expandedImage);
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvStock = (TextView) findViewById(R.id.tvStock);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);

    }
    private void darValores(){
//        ivFoto.setImageResource(articulo.getImagen());
        tvTitulo.setText(articulo.getNombre_articulo());
        tvStock.setText(String.valueOf(articulo.getStock()));
        tvPrecio.setText(String.valueOf(articulo.getTarifa()));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_articulo);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

}