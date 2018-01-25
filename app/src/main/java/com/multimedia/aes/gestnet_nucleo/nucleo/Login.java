package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarParte;
import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarUsuario;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloLogin;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloNotific;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPartes;
import com.multimedia.aes.gestnet_nucleo.notification.RegisterApp;
import com.multimedia.aes.gestnet_nucleo.progressDialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioLocalizacion;


import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.BLUETOOTH;
import static android.Manifest.permission.BLUETOOTH_ADMIN;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.WAKE_LOCK;
import static android.Manifest.permission.VIBRATE;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class Login extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText etUsuario,etContraseña;
    private Button btnLogin;
    private Usuario usuario;
    private Cliente cliente;
    public static final int REQUEST_PERMISSION = 1;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String TAG = "GCMRelated";
    GoogleCloudMessaging gcm;
    String regid;

    @SuppressLint("MissingPermission")
    public static String getImei(Context c, Activity a) {
        TelephonyManager telephonyManager = (TelephonyManager) c
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
    public boolean checkPermission() {
        int permisoUno= ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);
        int permisoDos= ContextCompat.checkSelfPermission(getApplicationContext(), BLUETOOTH);
        int permisoTres= ContextCompat.checkSelfPermission(getApplicationContext(), BLUETOOTH_ADMIN);
        int permisoCuatro= ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE);
        int permisoCinco= ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int permisoSeis= ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        int permisoSiete= ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int permisoOcho= ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int permisoNueve= ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permisoDiez= ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int permisoOnce= ContextCompat.checkSelfPermission(getApplicationContext(), GET_ACCOUNTS);
        int permisoDoce= ContextCompat.checkSelfPermission(getApplicationContext(), WAKE_LOCK);
        int permisoTrece= ContextCompat.checkSelfPermission(getApplicationContext(), VIBRATE);


        return permisoUno == PackageManager.PERMISSION_GRANTED &&
                permisoDos == PackageManager.PERMISSION_GRANTED &&
                permisoTres == PackageManager.PERMISSION_GRANTED &&
                permisoCuatro == PackageManager.PERMISSION_GRANTED &&
                permisoCinco == PackageManager.PERMISSION_GRANTED &&
                permisoSeis == PackageManager.PERMISSION_GRANTED &&
                permisoSiete == PackageManager.PERMISSION_GRANTED &&
                permisoOcho == PackageManager.PERMISSION_GRANTED &&
                permisoNueve == PackageManager.PERMISSION_GRANTED &&
                permisoDiez == PackageManager.PERMISSION_GRANTED &&
                permisoOnce == PackageManager.PERMISSION_GRANTED &&
                permisoDoce == PackageManager.PERMISSION_GRANTED &&
                permisoTrece == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission() {

        ActivityCompat.requestPermissions(Login.this, new String[]
                {INTERNET, BLUETOOTH, BLUETOOTH_ADMIN, CALL_PHONE,
        ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, CAMERA,
        READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, READ_PHONE_STATE,
        GET_ACCOUNTS,WAKE_LOCK, VIBRATE
                }, REQUEST_PERMISSION);

    }
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(getApplicationContext());
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }
    private SharedPreferences getGCMPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(Login.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }
    private void inicializarVariables()throws SQLException{
        ////EDITTEXTS////
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etUsuario.addTextChangedListener(this);
        etContraseña = (EditText)findViewById(R.id.etContraseña);
        etContraseña.addTextChangedListener(this);
        ////BUTTONS////
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnLogin.setClickable(true);
        btnLogin.setAlpha(0.5f);
        cliente = ClienteDAO.buscarTodosLosClientes(this).get(0);

    }
    public void guardarUsuario(String msg){

        try {
            if (ManagerProgressDialog.getDialog()==null){
                ManagerProgressDialog.abrirDialog(this);
            }
            ManagerProgressDialog.setMensaje(getResources().getString(R.string.guardar_datos));
            JSONObject jsonObject = new JSONObject(msg);
            int estado = jsonObject.getInt("estado");
            if (estado==1){
                new GuardarUsuario(this,msg);
            }else{
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            Dialogo.dialogoError("Error en login",this);
            if (ManagerProgressDialog.getDialog()!=null){
                ManagerProgressDialog.cerrarDialog();
            }

            e.printStackTrace();
        }
    }
    public void inicializarConfiguracion(){
        try {
            if (ManagerProgressDialog.getDialog()==null){
                ManagerProgressDialog.abrirDialog(this);
            }
            ManagerProgressDialog.setMensaje(getResources().getString(R.string.obtener_datos));
            usuario = UsuarioDAO.buscarTodosLosUsuarios(this).get(0);

            new HiloNotific(this,regid,getImei(this,this)).execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void hiloPartes(){
        new HiloPartes(this,usuario.getFk_entidad(),cliente.getIp_cliente(),usuario.getApi_key()).execute();
    }
    public void guardarPartes(String msg){
        try {
            if (ManagerProgressDialog.getDialog()==null){
                ManagerProgressDialog.abrirDialog(this);
            }
            ManagerProgressDialog.setMensaje(getResources().getString(R.string.guardar_datos));
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.getInt("estado")==1){
                new GuardarParte(this,msg);
            }else{
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void irIndex() {

        if(ManagerProgressDialog.getDialog()!=null){
            ManagerProgressDialog.cerrarDialog();
        }
        startService(new Intent(this, ServicioLocalizacion.class));
        Intent i = new Intent(this,Index.class);
        startActivity(i);

    }
    public void sacarMensaje(String msg) {
        if (ManagerProgressDialog.getDialog()!=null){
            ManagerProgressDialog.cerrarDialog();
        }
        Dialogo.dialogoError(msg,this);
        try {
            BBDDConstantes.borrarDatosError(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //////////@OVERRIDE METODS//////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        try {
            inicializarVariables();
            if (checkPlayServices()) {
                gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                regid = getRegistrationId(getApplicationContext());
            }

            if (UsuarioDAO.buscarTodosLosUsuarios(this)!=null) {
                    irIndex();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(checkPermission()){
        }
        else {
            requestPermission();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {

            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    boolean internet = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean bluetooth = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean bluetooth_admin = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean call_phone = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean access_fine_location = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean access_coarse_location = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    boolean camera = grantResults[6] == PackageManager.PERMISSION_GRANTED;
                    boolean read_external_storage = grantResults[7] == PackageManager.PERMISSION_GRANTED;
                    boolean write_external_storage = grantResults[8] == PackageManager.PERMISSION_GRANTED;
                    boolean read_phone_state = grantResults[9] == PackageManager.PERMISSION_GRANTED;
                    boolean get_accounts = grantResults[10] == PackageManager.PERMISSION_GRANTED;
                    boolean wake_lock = grantResults[11] == PackageManager.PERMISSION_GRANTED;
                    boolean vibrate = grantResults[12] == PackageManager.PERMISSION_GRANTED;

                    if (internet && bluetooth && bluetooth_admin&& call_phone&& access_fine_location&& access_coarse_location&& camera&&
                            read_external_storage&& write_external_storage&& read_phone_state&& get_accounts&& wake_lock&& vibrate) {

                    }
                    else {
                        requestPermission();
                    }
                }
                break;
        }
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    ////Desabilita el boton si los campos usuario y contraseña estan vacios
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (etUsuario.getText().toString().trim().isEmpty()&&!etContraseña.getText().toString().isEmpty()){
            btnLogin.setClickable(false);
            btnLogin.setAlpha(0.5f);
        }else{
            btnLogin.setClickable(true);
            btnLogin.setAlpha(1f);
        }
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnLogin){

            if (checkPlayServices()) {
                gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                regid = getRegistrationId(getApplicationContext());

                if (regid.isEmpty()) {
                    new RegisterApp(getApplicationContext(), gcm, getAppVersion(getApplicationContext())).execute();
                }
            } else {
                Log.i(TAG, "No valid Google Play Services APK found.");
            }
            if (ManagerProgressDialog.getDialog()!=null){
                ManagerProgressDialog.abrirDialog(this);
            }
            //ManagerProgressDialog.setMensaje(getResources().getString(R.string.obtener_datos));

            new HiloLogin(etUsuario.getText().toString().trim(),etContraseña.getText().toString().trim(),cliente.getIp_cliente(),this).execute();
        }
    }



}
