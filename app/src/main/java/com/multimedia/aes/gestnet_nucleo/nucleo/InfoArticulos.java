package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaStock;
import com.multimedia.aes.gestnet_nucleo.clases.DataStock;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class InfoArticulos  extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    private ImageView ivFoto;
    private TextView tvTitulo,tvStock,tvPrecio;
    private CheckBox chkGarantia;
    private Menu menu;
    private int idParte;
    private Articulo articulo;
    private Parte parte;
    private static ListView lvStockEntidad;
    private Button btnAñadirMaterial;
    private static ArrayList<DataStock> dataStock;
    private AdaptadorListaStock adapter;


    private void inicializarVariables(){
        ivFoto = (ImageView) findViewById(R.id.expandedImage);
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvStock = (TextView) findViewById(R.id.tvStock);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        btnAñadirMaterial=(Button)findViewById(R.id.btnAñadirMaterial);
        btnAñadirMaterial.setOnClickListener(this);

        chkGarantia = ( CheckBox ) findViewById( R.id.chkGarantia );
        chkGarantia.setOnCheckedChangeListener(this);

        lvStockEntidad= (ListView) findViewById(R.id.lvStockEntidad);

        // Construct the data source
        dataStock = new ArrayList<DataStock>();
        // Create the adapter to convert the array to views


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


    public static void sacarSotck(String mensaje, Context context) {
        try {
            JSONArray jsonArray = new JSONArray(mensaje);
            AdaptadorListaStock adapter=null;
            if (dataStock != null) {
                if (dataStock.size() != 0) {
                    dataStock.clear();
                }
            }
            if (jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {


                    int idStock= jsonArray.getJSONObject(i).getInt("id_stock");
                    String nombreEntidad=jsonArray.getJSONObject(i).getString("nombre_entidad");
                    int fkProducto= jsonArray.getJSONObject(i).getInt("fk_producto");
                    int stock= jsonArray.getJSONObject(i).getInt("cantidad");

                    DataStock d = new DataStock(idStock, nombreEntidad, fkProducto, stock);
                    dataStock.add(d);
                    adapter = new AdaptadorListaStock(context, dataStock);
                    // Attach the adapter to a ListView




                }


                lvStockEntidad.setAdapter(adapter);

            } else {
                DataStock d = new DataStock(0, "NINGUNA COINCIDENCIA", 0, 0);
                dataStock.add(d);
                adapter = new AdaptadorListaStock(context, dataStock);
                lvStockEntidad.setAdapter(adapter);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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