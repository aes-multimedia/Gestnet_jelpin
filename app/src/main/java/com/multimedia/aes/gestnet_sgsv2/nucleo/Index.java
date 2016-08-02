package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorAverias;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorMantenimientos;
import com.multimedia.aes.gestnet_sgsv2.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_sgsv2.dao.AveriaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Averia;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.fragment.FragmentBluetooth;
import com.multimedia.aes.gestnet_sgsv2.fragment.FragmentMantenimiento;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

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
    JSONObject jsonObjectTicket = new JSONObject();
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

        try {
            arrayListMantenimiento = new ArrayList(MantenimientoDAO.buscarTodosLosMantenimientos(this));
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
            setTitle("Averias");
            adaptadorAveria = new AdaptadorAverias(this, R.layout.camp_adapter_list_view_averia, arrayListAveria);
            lvIndex.setAdapter(adaptadorAveria);
        }else if(parte==2){
            setTitle("Mantenimientos");
            adaptadorMantenimientos = new AdaptadorMantenimientos(this, R.layout.camp_adapter_list_view_mantenimiento, arrayListMantenimiento);
            lvIndex.setAdapter(adaptadorMantenimientos);
        }

        try {

            generarTexto1();
            JSONObject msg = new JSONObject();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","ticket");
            jsonObject.put("base64","base64");
            msg.put("images",jsonObject);
            msg.put("ticket",jsonObjectTicket);
            msg.put("logo","logo");
            msg.put("firma","firma");
            Log.d("JSON",msg.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void generarTexto1() throws JSONException {
        String fecha = "22/06/2016";
        String hora = "12:06";
        String nombre_cliente = "Maria Garcia Hinojosa" + "\n";
        String dn = "02365984K";
        String num_contrato = "000111522";
        String serv = "Mantenimiento Gas";
        String dir = "Calle Ribadavia 11,2-A,"+"\n"+"Madrid,Madrid,20156";
        String emp = "IBERDROLA";
        String noti = "21/06/2016";
        String cif_emp = "02365474S";
        String num_emp_mant = "44556678";
        String tec = "Pedro Buenhombre Lopez";
        String num_insta = "659898741";
        String atend = "18/06/2016-14:00";
        String prev_repar = "26/06/2016-13:30";
        String repa = "24/06/2016-12:48";
        String num_solic = "6547952";
        String cod_ave = "3216565";
        String desc = "Una averia sin importancia";
        String despl = "5 horas:  24 euros";
        String piez = "Junta caldera  5 euros";
        String man_obra = "2 horas: 20 euros";
        String otr = "6 Km 31 euros"+"\n"+"2 Km 12 euros";
        String desc_preiva = "0%";
        String mat = "6 piezas: 29 euros";
        String pres_tot_siniva = "95 euros";
        String iv = "21%";
        String pres_tot_coniva = "102 euros";
        String otr_desc = "0%";
        String tot = "102 euros";
        String obs_tecnico = "La maquina es antigüa";
        String fec_recep = "22/06/2016-13:00";
        String fec_acep = "22/06/2016-13:00";
        String fec_conf = "22/06/2016-13:00";
        String obs_cliente = "muy majo el tecnico";
        jsonObjectTicket.put("fecha",fecha);
        jsonObjectTicket.put("hora",hora);
        jsonObjectTicket.put("nombre_cliente",nombre_cliente);
        jsonObjectTicket.put("dni_cliente",dn);
        jsonObjectTicket.put("numero_contrato",num_contrato);
        jsonObjectTicket.put("servicio",serv);
        jsonObjectTicket.put("direccion",dir);
        jsonObjectTicket.put("empresa",emp);
        jsonObjectTicket.put("cif_empresa",cif_emp);
        jsonObjectTicket.put("numero_empresa_mantenedora",num_emp_mant);
        jsonObjectTicket.put("tecnico",tec);
        jsonObjectTicket.put("numero_instalador",num_insta);
        jsonObjectTicket.put("notificada",noti);
        jsonObjectTicket.put("atendida",atend);
        jsonObjectTicket.put("prevista_reparacion",prev_repar);
        jsonObjectTicket.put("reparada",repa);
        jsonObjectTicket.put("numero_solicitud",num_solic);
        jsonObjectTicket.put("codigo_veria",cod_ave);
        jsonObjectTicket.put("descripcion_averia",desc);
        jsonObjectTicket.put("piezas",piez);
        jsonObjectTicket.put("mano_obra",man_obra);
        jsonObjectTicket.put("desplazamiento",despl);
        jsonObjectTicket.put("otros",otr);
        jsonObjectTicket.put("descuento_preiva",desc_preiva);
        jsonObjectTicket.put("materiales",mat);
        jsonObjectTicket.put("presupuesto_sin_iva",pres_tot_siniva);
        jsonObjectTicket.put("iva",iv);
        jsonObjectTicket.put("presupuesto_con_iva",pres_tot_coniva);
        jsonObjectTicket.put("otros_descuentos",otr_desc);
        jsonObjectTicket.put("total",tot);
        jsonObjectTicket.put("observaciones_tecnico",obs_tecnico);
        jsonObjectTicket.put("fecha_recepcion",fec_recep);
        jsonObjectTicket.put("fecha_aceptacion",fec_acep);
        jsonObjectTicket.put("fecha_conforme",fec_conf);
        jsonObjectTicket.put("observacion_cliente",obs_cliente);
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

        if (id == R.id.documentos) {
        } else if (id == R.id.averias) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("parte", 1);
                GestorSharedPreferences.clearSharedPreferencesPartes(this);
                GestorSharedPreferences.setJsonPartes(GestorSharedPreferences.getSharedPreferencesPartes(this), jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recreate();
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

}
