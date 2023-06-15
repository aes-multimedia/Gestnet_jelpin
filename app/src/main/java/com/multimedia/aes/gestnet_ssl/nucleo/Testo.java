package com.multimedia.aes.gestnet_ssl.nucleo;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.CommunicationInterface;
import com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.ConvertHelper;
import com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.MeasureValue;
import com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.MeasureValueFactoryInterface;
import com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.ReplyCallBack;
import com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.SendCallBack;
import com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.TransmissionFormat;
import com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.enums.MeasurerTypes;
import com.multimedia.aes.gestnet_ssl.TESTO.MeasurerCommunicator;
import com.multimedia.aes.gestnet_ssl.progressDialog.ManagerProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Testo extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnBuscarAnalizador;
    private ListView lvAnalizador;
    private BluetoothAdapter mBluetoothAdapter;
    public static BluetoothDevice bluetoothDeviceMeasurer = null;
    private BluetoothDevice mmDevice;
    private ArrayList<BluetoothDevice> listaDevice = new ArrayList<>();
    private ArrayList<String> listaNombre = new ArrayList<>();
    private String pr = "";
    private int serialNumber;
    private boolean viejo = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testo);
        btnBuscarAnalizador=(Button)findViewById(R.id.btnBuscarAnalizador);
        lvAnalizador=(ListView)findViewById(R.id.lvAnalizador);

        btnBuscarAnalizador.setOnClickListener(this);
        lvAnalizador.setOnItemClickListener(this);
        try {
        JSONObject jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(this));
        int id = jsonObject.getInt("id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnBuscarAnalizador){
            listaDevice.clear();
            listaNombre.clear();
            findBT();
        }

    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, int position, long id) {
        mmDevice = listaDevice.get(parent.getPositionForView(view));
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("¿Que tipo analizador es?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Analizador pantalla a color",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        viejo = false;
                        connectMeasurer(mmDevice.getAddress());
                        //btnBuscarAnalizador.setVisibility(View.GONE);
                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton(
                "Analizador pantalla B/N",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        viejo = true;
                        connectMeasurer(mmDevice.getAddress());
                        //btnBuscarAnalizador.setVisibility(View.GONE);
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);
        alert11.show();
    }
    void findBT() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if (mBluetoothAdapter == null) {
            }

            if (!mBluetoothAdapter.isEnabled()) {

                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    listaDevice.add(device);
                    listaNombre.add(device.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ponerLista();
    }
    public void ponerLista() {
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNombre);
        lvAnalizador.setAdapter(adaptador);
    }
    private void connectMeasurer(String address){
        try{
            bluetoothDeviceMeasurer = mmDevice;
        } catch(IllegalArgumentException ex){
            Toast.makeText(this, R.string.title_not_connected,
                    Toast.LENGTH_SHORT).show();
            bluetoothDeviceMeasurer = null;
        } catch(Exception ex){
            Toast.makeText(this, R.string.title_not_connected,
                    Toast.LENGTH_SHORT).show();
            bluetoothDeviceMeasurer = null;
            //Toast.makeText(this, "connectMeasurer: ERROR: " + ex.toString(), Toast.LENGTH_SHORT).show();
        }
        try{


            if (bluetoothDeviceMeasurer != null)
                if (MeasurerCommunicator.getInstance().communicator != null) {
                    if (viejo){
                        MeasurerCommunicator.getInstance().communicator.setMeasurerType(MeasurerTypes.Testo_330_1_LL);
                    }else{
                        MeasurerCommunicator.getInstance().communicator.setMeasurerType(MeasurerTypes.Testo_330_2_LL);
                    }
                    MeasurerCommunicator.getInstance().communicator.connect(bluetoothDeviceMeasurer);
                    MeasurerCommunicator.getInstance().setContext(this);
                }

        }catch(Exception ex){
            //Toast.makeText(this, "connectMeasurer: cannot connect to device " + address + ". Error: " + ex, Toast.LENGTH_LONG).show();
        }

    }

    public static BluetoothDevice getBluetoothDeviceMeasurer() {
        return bluetoothDeviceMeasurer;
    }
    public Button getBtnBuscarAnalizador() {
        return btnBuscarAnalizador;
    }
    public ListView getLvAnalizador() {
        return lvAnalizador;
    }

    private void getValues(){
        //sendCommandToGetSerialNumber();
        try{
            if (MeasurerCommunicator.getInstance().communicator != null){
                if (MeasurerCommunicator.getInstance().communicator.getState() == CommunicationInterface.STATE_CONNECTED) {
                    if (viejo){
                        int i = MeasurerCommunicator.getInstance().communicator.getState();
                        if (i == CommunicationInterface.STATE_LISTEN||i == CommunicationInterface.STATE_CONNECTED) {
                            byte[] send = MeasurerCommunicator.getInstance().communicator.getCommandInterface().getViewValues(0x00);
                            MeasurerCommunicator.getInstance().communicator.write(send, sendCallBack, replyCallBackWithValues);
                            ManagerProgressDialog.cerrarDialog();
                        }else if (i == CommunicationInterface.STATE_DISCONNECTED){
                            connectMeasurer(mmDevice.getAddress());
                            byte[] send = MeasurerCommunicator.getInstance().communicator.getCommandInterface().getViewValues(0x00);
                            MeasurerCommunicator.getInstance().communicator.write(send, sendCallBack, replyCallBackWithValues);
                            ManagerProgressDialog.cerrarDialog();
                        }
                    }else {
                        sendCommandToGetSerialNumber();
                    }
                }
            }

        }catch(Exception ex){
            //Toast.makeText(this, "getValues. error=" + ex.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void sendCommandToGetSerialNumber(){
        // Check that we're actually connected before trying anything
        if (MeasurerCommunicator.getInstance().communicator != null){
            if (MeasurerCommunicator.getInstance().communicator.getState() == CommunicationInterface.STATE_CONNECTED) {
                byte[] send = MeasurerCommunicator.getInstance().communicator.getCommandInterface().getSerialNumber();
                //.getNumberViewValues();
                MeasurerCommunicator.getInstance().communicator.write(send, sendCallBack, replyCallBackSerialNumber);

            }
        }
    }
    private ReplyCallBack replyCallBackSerialNumber = new ReplyCallBack() {
        public void reply(List<TransmissionFormat> tfList) {
            // 4 bytes containing the serial number
            int factor = 1;
            int sn = 0;
            for (TransmissionFormat item : tfList){
                for (byte b : item.getParams()){
                    int i = b;
                    if( i< 0 ) i = 256 + i;
                    sn += factor * i;
                    factor = factor << 8;
                }
            }
            // the variable sn contains now the serial number of the device
            String.valueOf(sn);
            serialNumber = sn;
            try{
                if (MeasurerCommunicator.getInstance().communicator != null){
                    int i = MeasurerCommunicator.getInstance().communicator.getState();
                    if (i == CommunicationInterface.STATE_LISTEN||i == CommunicationInterface.STATE_CONNECTED) {
                        byte[] send = MeasurerCommunicator.getInstance().communicator.getCommandInterface().getViewValues(0x00);
                        MeasurerCommunicator.getInstance().communicator.write(send, sendCallBack, replyCallBackWithValues);
                        ManagerProgressDialog.cerrarDialog();
                    }else if (i == CommunicationInterface.STATE_DISCONNECTED){
                        connectMeasurer(mmDevice.getAddress());
                        byte[] send = MeasurerCommunicator.getInstance().communicator.getCommandInterface().getViewValues(0x00);
                        MeasurerCommunicator.getInstance().communicator.write(send, sendCallBack, replyCallBackWithValues);
                        ManagerProgressDialog.cerrarDialog();
                    }
                }

            }catch(Exception ex){
                //Toast.makeText(getBaseContext(), "getValues. error=" + ex.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };

    private SendCallBack sendCallBack = new SendCallBack() {
        public void send(byte[] buffer) {
            String hexWrite = ConvertHelper.convertByteArrayToHexString(buffer);
        }
    };
    private ReplyCallBack replyCallBackWithValues = new ReplyCallBack() {
        public void reply(List<TransmissionFormat> tfList) {
            try{
                //pr.trim();
                MeasureValueFactoryInterface measureValueFactory = MeasurerCommunicator.getInstance().communicator.getMeasureValueFactoryInterface(tfList);
                String text = "";
                int cnt = 0;
                ArrayList<MeasureValue> items = measureValueFactory.getValues();
                int cont = 0;
                for (MeasureValue measureValue : items){
                    if (measureValue.getIdent()!=null||measureValue.getIdentId()!=0) {
                        cont++;
                    }
                }
                String [] datos = new String [cont+1];
                for (MeasureValue measureValue : items){
                    if (measureValue != null){
                        if (measureValue.getIdent()!=null) {
                            datos[cnt] = measureValue.getIdent().toString() + "&" + measureValue.getMeasureValueAsFormattedString();
                            cnt++;
                        }else{
                            datos[cnt] = String.valueOf(measureValue.getIdentId() + "&" + measureValue.getMeasureValueAsFormattedString());
                            cnt++;
                        }
                    }
                }
                datos[datos.length-1] = String.valueOf(serialNumber);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",datos);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            } catch(Exception ex){
                Log.d("AAAAAAAA",ex.toString());
            }
        }
    };
    private final BroadcastReceiver bReciever = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                boolean noEsta = true;
                for (int i = 0; i < listaNombre.size(); i++) {
                    if (listaNombre.get(i).equals(device.getName())) {
                        noEsta = false;
                        break;
                    }
                }
                if (noEsta) {
                    listaDevice.add(device);
                    listaNombre.add(device.getName());
                }
            }
            ponerLista();
            unregisterReceiver(bReciever);
        }
    };
    @Override
    public void onStart() {
        super.onStart();
        if (MeasurerCommunicator.getInstance().communicator == null) {
            MeasurerCommunicator.getInstance().initCommunicator();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (MeasurerCommunicator.getInstance().communicator != null)
            MeasurerCommunicator.getInstance().communicator.stop();
        MeasurerCommunicator.getInstance().communicator = null;
    }
    public void desconectado(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",1);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    public void conected(){
        getValues();
    }
    public void noConected(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("En este momento el analizador testo no esta disponible, intentelo mas tarde");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getBtnBuscarAnalizador().setVisibility(View.VISIBLE);
                        getLvAnalizador().setVisibility(View.VISIBLE);
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);
        alert11.show();
    }

}
