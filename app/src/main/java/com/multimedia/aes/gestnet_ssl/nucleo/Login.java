package com.multimedia.aes.gestnet_ssl.nucleo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
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
import com.multimedia.aes.gestnet_ssl.BBDD.GuardarParte;
import com.multimedia.aes.gestnet_ssl.BBDD.GuardarUsuario;
import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Usuario;
import com.multimedia.aes.gestnet_ssl.hilos.HiloLogin;
import com.multimedia.aes.gestnet_ssl.hilos.HiloNotific;
import com.multimedia.aes.gestnet_ssl.hilos.HiloPartes;


import static android.Manifest.permission.BLUETOOTH_CONNECT;
import static android.Manifest.permission.BLUETOOTH_SCAN;
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
    String regid;
    private Activity activity;

    @SuppressLint("MissingPermission")
    public static String getImeiDevice(Context c, Activity a) {
        TelephonyManager telephonyManager = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
        String imei ;
        String imei2 ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //imei = telephonyManager.getImei();
            imei = Settings.Secure.getString(c.getContentResolver(), Settings.Secure.ANDROID_ID);

        } else {
            imei = telephonyManager.getDeviceId();
        }

        return imei;
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public boolean checkPermission() {
        int permisoUno= ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);
        int permisoDos= ContextCompat.checkSelfPermission(getApplicationContext(), BLUETOOTH);
        int permisoTres= ContextCompat.checkSelfPermission(getApplicationContext(), BLUETOOTH_ADMIN);
        int permisoCuatro= ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE);
        int permisoCinco= ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int permisoSeis= ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        int permisoSiete= ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        /*int permisoOcho= ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int permisoNueve= ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);*/
        int permisoDiez= ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int permisoOnce= ContextCompat.checkSelfPermission(getApplicationContext(), GET_ACCOUNTS);
        int permisoDoce= ContextCompat.checkSelfPermission(getApplicationContext(), WAKE_LOCK);
        int permisoTrece= ContextCompat.checkSelfPermission(getApplicationContext(), VIBRATE);
        int permisoCartoce= ContextCompat.checkSelfPermission(getApplicationContext(), BLUETOOTH_SCAN);
        int permisoQuince= ContextCompat.checkSelfPermission(getApplicationContext(), BLUETOOTH_CONNECT);
        return permisoUno == PackageManager.PERMISSION_GRANTED &&
                permisoDos == PackageManager.PERMISSION_GRANTED &&
                permisoTres == PackageManager.PERMISSION_GRANTED &&
                permisoCuatro == PackageManager.PERMISSION_GRANTED &&
                permisoCinco == PackageManager.PERMISSION_GRANTED &&
                permisoSeis == PackageManager.PERMISSION_GRANTED &&
                permisoSiete == PackageManager.PERMISSION_GRANTED &&
                /*permisoOcho == PackageManager.PERMISSION_GRANTED &&
                permisoNueve == PackageManager.PERMISSION_GRANTED &&*/
                permisoDiez == PackageManager.PERMISSION_GRANTED &&
                permisoOnce == PackageManager.PERMISSION_GRANTED &&
                permisoDoce == PackageManager.PERMISSION_GRANTED &&
                permisoTrece == PackageManager.PERMISSION_GRANTED &&
                permisoCartoce == PackageManager.PERMISSION_GRANTED &&
                permisoQuince == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission() {

        ActivityCompat.requestPermissions(Login.this, new String[]
                {INTERNET, BLUETOOTH, BLUETOOTH_ADMIN, BLUETOOTH_SCAN, BLUETOOTH_CONNECT,  CALL_PHONE,
                        ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, CAMERA,
                        READ_PHONE_STATE,
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
        cliente = ClienteDAO.buscarCliente(this);

    }
    public void guardarUsuario(String msg){

        try {
            JSONObject jsonObject = new JSONObject(msg);
            int estado = jsonObject.getInt("estado");
            if (estado==1){
                new GuardarUsuario(this,msg).execute();
            }else{
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            Dialogo.dialogoError("Error en login",this);
            e.printStackTrace();
        }
    }
    public void inicializarConfiguracion(){
        try {
            usuario = UsuarioDAO.buscarUsuario(this);
            String imei ;
            imei = getImeiDevice(this,this);
            if (imei == null) {
                imei = getImeiDevice(this,this);
            }else{
                if(imei.equals("")){
                    imei = getImeiDevice(this,this);
                }
            }
            new HiloNotific(this,regid,imei).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hiloPartes(){
        new HiloPartes(this,usuario.getFk_entidad(),cliente.getIp_cliente(),usuario.getApi_key()).execute();
    }
    public void guardarPartes(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.getInt("estado")==1||jsonObject.getInt("estado")==272){
                new GuardarParte(this,msg).execute();
            }else{
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void irIndex() {
        Intent i = new Intent(this,Index.class);
        startActivity(i);
    }
    public void sacarMensaje(String msg) {
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
        activity=this;
        try {
            inicializarVariables();

            if (UsuarioDAO.buscarUsuario(this)!=null) {
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
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    boolean internet = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean bluetooth = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean bluetooth_admin = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    boolean call_phone = true;
                    boolean access_fine_location = true;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                        call_phone = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                        access_fine_location = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    }


                    boolean access_coarse_location = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    boolean camera = grantResults[6] == PackageManager.PERMISSION_GRANTED;
                    //TODO: a partir de android 11 no se tiene acceso a estos permisos
                    boolean read_external_storage = true;
                    boolean write_external_storage = true;
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
                        read_external_storage = grantResults[7] == PackageManager.PERMISSION_GRANTED;
                        write_external_storage = grantResults[8] == PackageManager.PERMISSION_GRANTED;
                    }

                    boolean read_phone_state = grantResults[9] == PackageManager.PERMISSION_GRANTED;
                    boolean get_accounts = grantResults[10] == PackageManager.PERMISSION_GRANTED;
                    boolean wake_lock = grantResults[11] == PackageManager.PERMISSION_GRANTED;
                    boolean vibrate = grantResults[12] == PackageManager.PERMISSION_GRANTED;

                    if (internet && bluetooth && bluetooth_admin && call_phone && access_fine_location && access_coarse_location && camera &&
                            read_external_storage && write_external_storage && read_phone_state && get_accounts && wake_lock && vibrate) {

                    } else {
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

            new HiloLogin(etUsuario.getText().toString().trim(),etContraseña.getText().toString().trim(),cliente.getIp_cliente(),this).execute();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        activity.finish();

    }
}
