package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorAverias;
import com.multimedia.aes.gestnet_nucleo.entidades.Averia;

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
    private SwipeRefreshLayout srl;
    private ImageView ivIncidencias;
    private LinearLayout cuerpo;
    private AdaptadorAverias adaptadorAverias;
    private ArrayList<Averia> arrayListAveria = new ArrayList<>();
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
        setTitle("Averias");
        srl = (SwipeRefreshLayout) findViewById(R.id.lllistview);
        srl.setOnRefreshListener(this);
        lvIndex = (ListView) findViewById(R.id.lvIndex);
        lvIndex.setOnItemClickListener(this);
        ivIncidencias = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivIncidencias);
        ivIncidencias.setOnClickListener(this);
        cuerpo = (LinearLayout) findViewById(R.id.cuerpo);
        Averia a = new Averia(1,"Calle Falsa 123","25698","Madrid-Madrid");
        arrayListAveria.add(a);
        adaptadorAverias = new AdaptadorAverias(this, R.layout.camp_adapter_list_view_averia, arrayListAveria);
        lvIndex.setAdapter(adaptadorAverias);
    }

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

        /*if (id == R.id.documentos) {
        } else if (id == R.id.averias) {
            /*JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("parte", 1);
                GestorSharedPreferences.clearSharedPreferencesPartes(this);
                GestorSharedPreferences.setJsonPartes(GestorSharedPreferences.getSharedPreferencesPartes(this), jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recreate();
        /*} else if (id == R.id.almacen) {*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        JSONObject jsonObject = new JSONObject();
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onClick(View view) {
    }
}
