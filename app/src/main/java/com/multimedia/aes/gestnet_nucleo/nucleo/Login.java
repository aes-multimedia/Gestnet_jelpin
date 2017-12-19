package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.Manifest;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarParte;
import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarUsuario;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloLogin;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloNotific;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPartes;
import com.multimedia.aes.gestnet_nucleo.notification.RegisterApp;
import com.multimedia.aes.gestnet_nucleo.progressDialog.ManagerProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class Login extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText etUsuario,etContraseña;
    private Button btnLogin;
    private Usuario usuario;
    private Cliente cliente;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1 ;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String TAG = "GCMRelated";
    GoogleCloudMessaging gcm;
    String regid;

    public static String getImei(Context c, Activity a) {
        int permissionWriteCheck = ContextCompat.checkSelfPermission(c, Manifest.permission.READ_PHONE_STATE);
        if (permissionWriteCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(a, Manifest.permission.READ_PHONE_STATE)) {
            } else {
                ActivityCompat.requestPermissions(a,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
            }
        }else{
            TelephonyManager telephonyManager = (TelephonyManager) c
                    .getSystemService(Context.TELEPHONY_SERVICE);
            return telephonyManager.getDeviceId();
        }
        return "";
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
            getImei(this,this);
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
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
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
