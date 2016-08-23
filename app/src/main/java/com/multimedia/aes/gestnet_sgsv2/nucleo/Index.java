package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorAverias;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorListaImagenes;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorMantenimientos;
import com.multimedia.aes.gestnet_sgsv2.clases.DataImagenes;
import com.multimedia.aes.gestnet_sgsv2.com.google.zxing.integration.android.IntentIntegrator;
import com.multimedia.aes.gestnet_sgsv2.com.google.zxing.integration.android.IntentResult;
import com.multimedia.aes.gestnet_sgsv2.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_sgsv2.dao.AveriaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Averia;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.fragment.FragmentBluetooth;
import com.multimedia.aes.gestnet_sgsv2.fragment.FragmentMantenimiento;
import com.multimedia.aes.gestnet_sgsv2.fragment.TabFragment3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private ListView lvIndex;
    private AdaptadorMantenimientos adaptadorMantenimientos;
    private AdaptadorAverias adaptadorAveria;
    private ArrayList<Mantenimiento> arrayListMantenimiento = new ArrayList();
    private ArrayList<Averia> arrayListAveria = new ArrayList();
    private SwipeRefreshLayout srl;
    private ImageView ivIncidencias, ivLlamarAes;
    private LinearLayout cuerpo;
    private int parte = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonPartes(GestorSharedPreferences.getSharedPreferencesPartes(this));
            parte = jsonObject.getInt("parte");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jo= new JSONObject();
        String dia = null;
        try {
            jo = GestorSharedPreferences.getJsonDia(GestorSharedPreferences.getSharedPreferencesDia(this));
            dia = jo.getString("dia");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            if (jo.toString().equals("{}")){
                rellenarArrayMantenimientoFecha(getDateTime());
            }else{
                rellenarArrayMantenimientoFecha(dia);
            }
            arrayListAveria = new ArrayList(AveriaDAO.buscarTodasLasAverias(this));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        srl = (SwipeRefreshLayout) findViewById(R.id.lllistview);
        srl.setOnRefreshListener(this);
        lvIndex = (ListView) findViewById(R.id.lvIndex);
        lvIndex.setOnItemClickListener(this);
        ivIncidencias = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivIncidencias);
        ivLlamarAes = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivLlamarAes);
        ivIncidencias.setOnClickListener(this);
        ivLlamarAes.setOnClickListener(this);
        cuerpo = (LinearLayout) findViewById(R.id.cuerpo);

        if (parte==1){
            /*setTitle("Averias");
            adaptadorAveria = new AdaptadorAverias(this, R.layout.camp_adapter_list_view_averia, arrayListAveria);
            lvIndex.setAdapter(adaptadorAveria);*/
        }else{
            setTitle("Mantenimientos");
            adaptadorMantenimientos = new AdaptadorMantenimientos(this, R.layout.camp_adapter_list_view_mantenimiento, arrayListMantenimiento);
            lvIndex.setAdapter(adaptadorMantenimientos);
        }

    }

    public void rellenarArrayMantenimientoFecha(String fecha) throws SQLException {
        if (MantenimientoDAO.buscarMantenimientosPorFechas(this, fecha)!=null) {
            arrayListMantenimiento.addAll(MantenimientoDAO.buscarMantenimientosPorFechas(this, fecha));
        }else{
            Toast.makeText(Index.this, "No hay mantenimientos ese dia", Toast.LENGTH_SHORT).show();
            arrayListMantenimiento.addAll(MantenimientoDAO.buscarTodosLosMantenimientos(this));
        }
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(); return dateFormat.format(date); }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            recreate();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.documentos) {
        } else if (id == R.id.averias) {
            /*JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("parte", 1);
                GestorSharedPreferences.clearSharedPreferencesPartes(this);
                GestorSharedPreferences.setJsonPartes(GestorSharedPreferences.getSharedPreferencesPartes(this), jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recreate();*/
        } else if (id == R.id.mantenimientos) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("parte", 2);
                GestorSharedPreferences.clearSharedPreferencesPartes(this);
                GestorSharedPreferences.setJsonPartes(GestorSharedPreferences.getSharedPreferencesPartes(this), jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recreate();
        } else if (id == R.id.almacen) {

        } else if (id == R.id.buscar_parte) {

        } else if (id == R.id.ajustes) {
        } else if (id == R.id.cambiarFecha) {
            Calendar mcurrentDate = Calendar.getInstance();
            int mYear = mcurrentDate.get(Calendar.YEAR);
            int mMonth = mcurrentDate.get(Calendar.MONTH);
            int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog mDatePicker;
            mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    selectedmonth = selectedmonth + 1;
                    String day = selectedday+"";
                    String month = selectedmonth+"";
                    if (selectedday<10){
                        day="0"+selectedday;
                    }
                    if (selectedmonth<10){
                        month = "0"+selectedmonth;
                    }
                    String year=selectedyear+"";
                    JSONObject js = new JSONObject();
                    try {
                        js.put("dia",day+"-"+month+"-"+year);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    GestorSharedPreferences.setJsonDia(GestorSharedPreferences.getSharedPreferencesDia(Index.this),js);
                    recreate();
                }
            }, mYear, mMonth, mDay);
            mDatePicker.setTitle("Select Date");
            mDatePicker.show();
        } else if (id == R.id.cerrar_sesion) {
            try {
                BBDDConstantes.borrarDatosTablas(this);
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                finish();
                GestorSharedPreferences.clearSharedPreferencesTecnico(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", (String) view.getTag());
            GestorSharedPreferences.clearSharedPreferencesMantenimiento(this);
            GestorSharedPreferences.setJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(this), jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        srl.setVisibility(View.GONE);
        Class fragmentClass = FragmentMantenimiento.class;
        Fragment fragment;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.cuerpo, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        srl.setRefreshing(true);
        //adaptadorMantenimientos = new AdaptadorMantenimientos(this, R.layout.camp_adapter_list_view_mantenimiento, arrayListMantenimiento);
        //lvIndex.setAdapter(adaptadorMantenimientos);
        srl.setRefreshing(false);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivIncidencias) {
            Intent i = new Intent(this, Incidencias.class);
            startActivity(i);
        } else if (view.getId() == R.id.ivLlamarAes) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:664783563"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        }
    }
    public void activar(){
        cuerpo.removeAllViews();
        Class fragmentClass = FragmentMantenimiento.class;
        Fragment fragment;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.cuerpo, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void ticket(String msg){
        Toast.makeText(Index.this, msg+" /", Toast.LENGTH_SHORT).show();
        Toast.makeText(Index.this, msg+" /", Toast.LENGTH_SHORT).show();
        Toast.makeText(Index.this, msg+" /", Toast.LENGTH_SHORT).show();
        Toast.makeText(Index.this, msg+" /", Toast.LENGTH_SHORT).show();
        Class fragmentClass = FragmentBluetooth.class;
        Fragment fragment;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.cuerpo, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
