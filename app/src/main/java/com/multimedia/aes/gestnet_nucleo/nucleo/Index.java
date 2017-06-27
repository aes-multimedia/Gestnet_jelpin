package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_nucleo.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_nucleo.entities.Mantenimiento;
import com.multimedia.aes.gestnet_nucleo.fragment.FragmentBluetooth;
import com.multimedia.aes.gestnet_nucleo.fragment.FragmentMantenimiento;
import com.multimedia.aes.gestnet_nucleo.services.ConectionService;
import com.multimedia.aes.gestnet_nucleo.services.UploadService;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private Intent intentConectionService;
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
        IntentFilter filter = new IntentFilter(
                Constantes.ACTION_RUN_ISERVICE);
        filter.addAction(Constantes.ACTION_RUN_SERVICE);
        filter.addAction(Constantes.ACTION_MEMORY_EXIT);
        filter.addAction(Constantes.ACTION_PROGRESS_EXIT);
        ResponseReceiver receiver =
                new ResponseReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                receiver,
                filter);
        intentConectionService = new Intent(
                getApplicationContext(), ConectionService.class);
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
            //arrayListAveria = new ArrayList(AveriaDAO.buscarTodasLasAverias(this));
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
            List<Mantenimiento>m=MantenimientoDAO.buscarMantenimientosPorFechas(this, fecha);
            if (m==null){
                Toast.makeText(this, "No hay partes para hoy", Toast.LENGTH_SHORT).show();
            }else {
                arrayListMantenimiento.addAll(m);
            }
        }else if (MantenimientoDAO.buscarMantenimientosPorFechas(this, getDateTime())!=null){
            List<Mantenimiento>m=MantenimientoDAO.buscarMantenimientosPorFechas(this, getDateTime());
            if (m==null){
                Toast.makeText(this, "No hay partes para hoy", Toast.LENGTH_SHORT).show();
            }else {
                arrayListMantenimiento.addAll(m);
            }
        }else{
            List<Mantenimiento>m=MantenimientoDAO.buscarTodosLosMantenimientos(this);
            if (m==null){
                Toast.makeText(this, "No hay partes para hoy", Toast.LENGTH_SHORT).show();
            }else {
                arrayListMantenimiento.addAll(m);
            }
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

        } else if (id == R.id.subir_datos) {
            strtService();
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
    public void ticket(){
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
    public void strtService(){
        startService(intentConectionService);
    }
    public void stpService(){
        stopService(intentConectionService);
    }
    private class ResponseReceiver extends BroadcastReceiver {

        private ResponseReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Constantes.ACTION_RUN_SERVICE:
                    if (intent.getStringExtra(Constantes.EXTRA_MEMORY).equals("1")){
                        stpService();
                        Intent in = new Intent(Index.this, UploadService.class);
                        in.setAction(Constantes.ACTION_RUN_ISERVICE);
                        startService(in);
                    }
                    break;

                case Constantes.ACTION_RUN_ISERVICE:
                    break;

                case Constantes.ACTION_MEMORY_EXIT:
                    break;

                case Constantes.ACTION_PROGRESS_EXIT:
                    break;
            }
        }
    }
}
