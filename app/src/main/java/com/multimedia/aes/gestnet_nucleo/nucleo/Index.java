package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarParte;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorPartes;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorPartes_trenc;
import com.multimedia.aes.gestnet_nucleo.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.EnvioDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.dialogo.DialogoBuscarArticulo;
import com.multimedia.aes.gestnet_nucleo.dialogo.DialogoKilometros;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Configuracion;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Envio;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.fragments.FragmentImpresion;
import com.multimedia.aes.gestnet_nucleo.fragments.FragmentPartes;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloActualizarStock;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloIniciarParte;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloNoEnviados;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPartes;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPartesId;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPorFecha;
import com.multimedia.aes.gestnet_nucleo.notification.GcmIntentService;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioArticulos;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioLocalizacion;

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
    private AdaptadorPartes adaptadorPartes;
    private AdaptadorPartes_trenc adaptadorPartes_trenc;
    private ArrayList<Parte> arrayListParte = new ArrayList<>();
    private String fecha;
    private Usuario u;
    private Cliente c;

    //METODO
    private void inicializarVariables() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        srl = findViewById(R.id.lllistview);
        srl.setOnRefreshListener(this);
        lvIndex = findViewById(R.id.lvIndex);
        lvIndex.setOnItemClickListener(this);
        ivIncidencias = navigationView.getHeaderView(0).findViewById(R.id.ivIncidencias);
        ivIncidencias.setOnClickListener(this);
        cuerpo = findViewById(R.id.cuerpo);




    }

    public void sacarMensaje(String msg) {
        srl.setRefreshing(false);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
            "Aceptar",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    finish();
                }
            });
        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);
        alert11.setCanceledOnTouchOutside(false);
        alert11.show();

    }

    public void guardarPartes(String msg) {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.getInt("estado") == 1 || jsonObject.getInt("estado") == 272) {
                new GuardarParte(this, msg).execute();
            } else {
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void datosActualizados() {
        Intent intent = new Intent(this, Index.class);
        /*intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
        getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getIntent().removeExtra("metodo");
        getIntent().removeExtra("notId");
        startActivity(intent);
        finish();


        //recreate();
    }

    public void impresion() {
        Class fragmentClass = FragmentImpresion.class;
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

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void actualizarFecha(String fecha) {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        inicializarVariables();
        fecha = getDateTime();
        try {
            u = UsuarioDAO.buscarUsuario(this);
            c = ClienteDAO.buscarCliente(this);
            if (ArticuloDAO.buscarTodosLosArticulos(this) == null) {
                startService(new Intent(this, ServicioArticulos.class));
            } else {
                startService(new Intent(this, ServicioLocalizacion.class));
            }
            arrayListParte.clear();
            if (ParteDAO.buscarTodosLosPartes(this) != null) {
                arrayListParte.addAll(ParteDAO.buscarTodosLosPartes(this));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject jo;
        String dia;
        try {
            jo = GestorSharedPreferences.getJsonDia(GestorSharedPreferences.getSharedPreferencesDia(this));
            dia = jo.getString("dia");
            fecha = dia;
            setTitle("Avisos" + " " + fecha);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        if(c.getId_cliente()==28){
            adaptadorPartes_trenc = new AdaptadorPartes_trenc(this, R.layout.camp_adapter_list_view_parte_trenc, arrayListParte);
            lvIndex.setAdapter(adaptadorPartes_trenc);
        }else{
            adaptadorPartes = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_parte, arrayListParte);
            lvIndex.setAdapter(adaptadorPartes);
        }

        Intent intent = getIntent();
        if (intent != null) {

            int metodo = intent.getIntExtra("metodo", 0);
            int notId = intent.getIntExtra("notiId", 0);
            int id = intent.getIntExtra("id", 0);
            switch (metodo) {
                case 1:
                    break;
                case 2:
                    try {
                        if (ParteDAO.buscarPartePorId(this, id) != null) {
                            arrayListParte.clear();
                            int direccion = ParteDAO.buscarPartePorId(this, id).getFk_direccion();
                            ProtocoloAccionDAO.borrarProtocoloPorFkParte(this, id);
                            DatosAdicionalesDAO.borrarDatosAdicionalesPorFkParte(this, id);
                            MaquinaDAO.borrarMaquinaPorFkDireccion(this, direccion);
                            ParteDAO.borrarPartePorID(this, id);
                            if (ParteDAO.buscarTodosLosPartes(this) != null) {
                                arrayListParte.addAll(ParteDAO.buscarTodosLosPartes(this));
                            }
                            adaptadorPartes = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_parte, arrayListParte);
                            lvIndex.setAdapter(adaptadorPartes);
                            Dialogo.dialogoError("Un Parte de Intervención ha sido eliminado de su ruta diaria", this);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    new HiloPartesId(this, u.getFk_entidad(), id, c.getIp_cliente(), u.getApi_key()).execute();
                    break;
                case 4:
                    try {
                        arrayListParte.clear();
                        ProtocoloAccionDAO.borrarTodosLosProtocolo(this);
                        DatosAdicionalesDAO.borrarTodosLosDatosAdicionales(this);
                        MaquinaDAO.borrarTodasLasMaquinas(this);
                        ParteDAO.borrarTodosLosPartes(this);
                        adaptadorPartes = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_parte, arrayListParte);
                        lvIndex.setAdapter(adaptadorPartes);
                        Dialogo.dialogoError("ruta borrada", this);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;


            }
            if (notId != 0) {
                GcmIntentService.cerrarNotificacion(notId);
            }
        }
        try {
            Configuracion co = ConfiguracionDAO.buscarTodasLasConfiguraciones(this).get(0);
            if (ConfiguracionDAO.buscarTodasLasConfiguraciones(this) != null && ConfiguracionDAO.buscarTodasLasConfiguraciones(this).get(0).isKms_finalizacion()) {
                JSONObject kil = new JSONObject();
                try {
                    kil = GestorSharedPreferences.getJsonKilometros(GestorSharedPreferences.getSharedPreferencesKilometros(this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (kil == null || kil.toString().equals("{}")) {
                    DialogoKilometros dialog = new DialogoKilometros().newInstance(u.getFk_entidad());
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.setCancelable(false);
                    dialog.show(ft, "DialogoKilometros");
                } else {
                    try {
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = sdf.format(c.getTime());
                        if (!kil.getString("fecha").equals(strDate)) {
                            DialogoKilometros dialog = new DialogoKilometros().newInstance(0);
                            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                            dialog.setCancelable(false);
                            dialog.show(ft, "DialogoKilometros");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            finish();
            startActivity(getIntent());
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.averias) {
            finish();
            startActivity(getIntent());

        } else if (id == R.id.mi_firma) {
            Intent i = new Intent(this, MiFirma.class);
            startActivity(i);
        } else if (id == R.id.cierre_dia) {
            Intent i = new Intent(this, CierreDia.class);
            startActivity(i);
        } else if (id == R.id.aviso_guardia) {
            String ip = c.getIp_cliente();
            String fk_tecnico = u.getFk_entidad() + "";
            String url = "http://" + ip + "/webservices/webview/avisoGuardia.php?fk_tecnico=" + fk_tecnico;
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else if (id == R.id.cambiar_fecha) {
            try {
                List<Parte> part = ParteDAO.buscarTodosLosPartes(this);
                //if (part==null){
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        String day = selectedday + "";
                        int monthReal = selectedmonth + 1;
                        String month = monthReal + "";
                        if (selectedday < 10) {
                            day = "0" + selectedday;
                        }
                        if (monthReal < 10) {
                            //Los meses en android van de 0 a 11
                            month = "0" + monthReal;
                        }
                        String year = selectedyear + "";
                        JSONObject js = new JSONObject();
                        try {
                            js.put("dia", day + "-" + month + "-" + year);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        GestorSharedPreferences.setJsonDia(GestorSharedPreferences.getSharedPreferencesDia(Index.this), js);
                        try {
                            BBDDConstantes.borrarDatosTablasPorDia(Index.this);
                            GestorSharedPreferences.clearSharedPreferencesParte(Index.this);
                            String fecha = year + "-" + month + "-" + day;
                            new HiloPorFecha(Index.this, u.getFk_entidad(), fecha, c.getIp_cliente()).execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.cierres_pendientes) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("¿Se enviaran todos los cambios pendientes, desea continuar?");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                "Enviar cambios.",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            if (EnvioDAO.buscarTodosLosEnvios(Index.this) != null) {
                                List<Envio> envios = EnvioDAO.buscarTodosLosEnvios(Index.this);
                                for (Envio envio : envios) {
                                    new HiloNoEnviados(Index.this, envio.getId_envio()).execute();
                                }
                            } else {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(Index.this);
                                builder1.setMessage("No hay Cambios pendientes.");
                                builder1.setCancelable(true);
                                builder1.setPositiveButton(
                                    "Aceptar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                                AlertDialog alert11 = builder1.create();
                                alert11.setCanceledOnTouchOutside(false);
                                alert11.show();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        dialog.cancel();
                    }
                });
            builder1.setNegativeButton(
                "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
            AlertDialog alert11 = builder1.create();
            alert11.setCanceledOnTouchOutside(false);
            alert11.show();

        } else if (id == R.id.actualizar_stock) {
            int fkEntidad = u.getFk_entidad();
            try {
                new HiloActualizarStock(this, fkEntidad).execute();
            } catch (Exception e) {

                ProgressDialog dialog;
                dialog = new ProgressDialog(this);
                dialog.setTitle("Error al actualizar almacén");
                dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setIndeterminate(true);
                dialog.show();
            }

        } else if (id == R.id.buscar_articulos) {
            DialogoBuscarArticulo dialog = new DialogoBuscarArticulo();
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            dialog.setCancelable(false);
            dialog.show(ft, "DialogoBuscarArticulo");
        } else if (id == R.id.cerrar_sesion) {
            try {
                stopService(new Intent(this, ServicioLocalizacion.class));
                BBDDConstantes.borrarDatosTablas(this);
                Intent i = new Intent(this, PreLogin.class);
                startActivity(i);
                finish();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, @NonNull View view , int i, long l) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", view.getTag());
            GestorSharedPreferences.clearSharedPreferencesParte(this);
            GestorSharedPreferences.setJsonParte(GestorSharedPreferences.getSharedPreferencesParte(this), jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        srl.setVisibility(View.GONE);
        Parte parte = null;
        try {
            parte = ParteDAO.buscarPartePorId(this, Integer.parseInt(view.getTag().toString()));
            if (parte.getFk_tipo() == 4 && ClienteDAO.buscarCliente(this).getId_cliente() == 30) {
                iniciarParte(parte);
                Cliente cliente = null;
                try {
                    cliente = ClienteDAO.buscarCliente(this);
                    String url = "http://" + cliente.getIp_cliente() + "/webservices/webview/trabajos_obra.php?fk_parte=" + parte.getId_parte();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                Class fragmentClass = FragmentPartes.class;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void iniciarParte(@NonNull Parte parte) throws SQLException {
        if (parte.getEstado_android() == 0) {
            DatosAdicionales datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(this, parte.getId_parte());
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            datos.setMatem_hora_entrada(formattedDate);
            try {
                DatosAdicionalesDAO.actualizarHoraEntrada(this, datos.getId_rel(), formattedDate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            new HiloIniciarParte(this, parte, 1, 2).execute();
        }
    }

    @Override
    public void onRefresh() {
        Calendar calendar = Calendar.getInstance();
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int m = calendar.get(Calendar.MONTH) + 1;
        int y = calendar.get(Calendar.YEAR);

        String fechar = String.valueOf(d + "-" + m + "-" + y);


        List<Envio> envios = null;
        try {
            envios = EnvioDAO.buscarTodosLosEnvios(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (envios != null && envios.size() > 0) {

            new AlertDialog.Builder(this).setMessage("Por favor, antes de continuar envie los cierres pendientes.")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface hi, int dd) {

                            srl.setRefreshing(false);
                        }
                    }
                ).show();

        } else {


            JSONObject js = new JSONObject();
            try {
                js.put("dia", fechar);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GestorSharedPreferences.setJsonDia(GestorSharedPreferences.getSharedPreferencesDia(Index.this), js);
            try {
                BBDDConstantes.borrarDatosTablasPorDia(this);
                srl.setRefreshing(true);
                new HiloPartes(this, u.getFk_entidad(), c.getIp_cliente(), u.getApi_key()).execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onClick(View v) {


    }
}