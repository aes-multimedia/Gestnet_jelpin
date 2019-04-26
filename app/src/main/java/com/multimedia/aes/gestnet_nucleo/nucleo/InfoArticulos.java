package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaStock;
import com.multimedia.aes.gestnet_nucleo.clases.DataStock;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloStockAlmacenes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class InfoArticulos  extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    private ImageView ivFoto;
    private TextView tvTitulo,tvStock,tvPrecio;
    private EditText tvCantidad;
    private CheckBox chkGarantia;
    private Menu menu;
    private int idParte;
    private Articulo articulo;
    private Parte parte;
    private static ListView lvStockEntidad;
    private Button btnAñadirMaterial,btnPedirMaterial;
    private static ArrayList<DataStock> dataStock;
    private AdaptadorListaStock adapter;
    private static int alto=0,alto1=0,height=0;
    private double unidades;
    private LinearLayout llCantidad,llGarantia;


    private void inicializarVariables(){
        ivFoto =  findViewById(R.id.expandedImage);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvStock = findViewById(R.id.tvStock);
        tvCantidad =  findViewById(R.id.tvCantidad);
        tvPrecio =  findViewById(R.id.tvPrecio);
        btnAñadirMaterial=findViewById(R.id.btnAñadirMaterial);
        llCantidad=findViewById(R.id.llCantidad);
        llGarantia=findViewById(R.id.llGarantia);
        lvStockEntidad=  findViewById(R.id.lvStockEntidad);

        btnAñadirMaterial.setOnClickListener(this);
        btnPedirMaterial=findViewById(R.id.btnPedirMaterial);
        btnPedirMaterial.setOnClickListener(this);


        // Construct the data source
        dataStock = new ArrayList<>();
        // Create the adapter to convert the array to views


    }
    private void darValores(){
      //ivFoto.setImageResource(articulo.getImagen());
        tvTitulo.setText(articulo.getNombre_articulo());

        tvStock.setText(String.valueOf(articulo.getStock()));
        if(articulo.getStock()<0){
            btnAñadirMaterial.setClickable(false);
            btnAñadirMaterial.setEnabled(false);
        }

        tvPrecio.setText(String.valueOf(articulo.getTarifa())+ "\u20ac");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_articulo);
        Display display = getWindowManager().getDefaultDisplay();
        height = display.getHeight()/8;
        dataStock= new ArrayList<>();
        buscarStockAlmacenes();
        chkGarantia = findViewById( R.id.chkGarantia );
        chkGarantia.setOnCheckedChangeListener(this);

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
            if (articulo.isGarantia()) {
                chkGarantia.setChecked(true);
            } else {
                chkGarantia.setChecked(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        inicializarVariables();
        darValores();
        if (getIntent().getIntExtra("sitio",-1)==1){
            llCantidad.setVisibility(View.GONE);
            llGarantia.setVisibility(View.GONE);
            btnAñadirMaterial.setVisibility(View.GONE);
            btnPedirMaterial.setVisibility(View.GONE);
        }

        final Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        AppBarLayout mAppBarLayout =  findViewById(R.id.app_bar);
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

    private void buscarStockAlmacenes()  {
        try {
            articulo = ArticuloDAO.buscarArticuloPorID(this,getIntent().getIntExtra("articuloId",-1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new HiloStockAlmacenes(this,articulo.getFk_articulo()).execute();

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
                    double stock= jsonArray.getJSONObject(i).getDouble("cantidad");

                    DataStock d = new DataStock(idStock, nombreEntidad, fkProducto, stock);
                    dataStock.add(d);

                }
                alto =height * dataStock.size();
                lvStockEntidad.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
                adapter = new AdaptadorListaStock(context, dataStock);
                lvStockEntidad.setAdapter(adapter);

            } else {
                DataStock d = new DataStock(0, "NINGUNA COINCIDENCIA", 0, 0.0);
                dataStock.add(d);
                alto =height * dataStock.size();
                lvStockEntidad.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
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

        if(tvCantidad.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("ATENCIÓN!")
                    .setMessage("Debes introducir una cantidad")
                    .setPositiveButton("Aceptar", (dialog, id) -> dialog.cancel()).show();
        }else {

            String cantidad = tvCantidad.getText().toString();
            if (cantidad.matches("")) {
                unidades = 1;
            } else {
                unidades =  Double.parseDouble(tvCantidad.getText().toString());
            }
            try {
                if (ArticuloParteDAO.buscarArticuloPartePorFkParteFkArticulo(this, articulo.getId_articulo(), idParte) != null) {
                    ArticuloParte articuloParte = ArticuloParteDAO.buscarArticuloPartePorFkParteFkArticulo(this, articulo.getId_articulo(), idParte);
                    ArticuloParteDAO.actualizarArticuloParte(this, articuloParte.getId(), articuloParte.getUsados() + unidades);
                } else {
                    if (ArticuloParteDAO.newArticuloParte(this, articulo.getId_articulo(), idParte, unidades)) {
                    }
                }
                try {

                    if (v.getId() == btnAñadirMaterial.getId()) {
                        ArticuloDAO.actualizarUtilizado(this, articulo.getId_articulo());
                        ArticuloDAO.actualizarArticulo(this, articulo.getId_articulo(), articulo.getNombre_articulo(), articulo.getStock() - Double.valueOf(tvCantidad.getText().toString()), articulo.getTarifa());
                    } else if (v.getId() == btnPedirMaterial.getId()) {
                        try {
                            ArticuloDAO.actualizarEntregado(this, articulo.getId_articulo());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    }
                } catch (SQLException e) {
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                    e.printStackTrace();
                }
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        try {

        if ( isChecked )
        {
                ArticuloDAO.actualizarGarantia(this,articulo.getId_articulo(),true);
        }else{
                ArticuloDAO.actualizarGarantia(this,articulo.getId_articulo(),false);
                }
             }   catch (SQLException e) {
            e.printStackTrace();
        }
    }
}