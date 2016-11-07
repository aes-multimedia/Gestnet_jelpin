package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.CommunicationInterface;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.ConvertHelper;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.MeasureValue;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.MeasureValueFactoryInterface;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.ReplyCallBack;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.SendCallBack;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.TransmissionFormat;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.enums.MeasurerTypes;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.enums.Units;
import com.multimedia.aes.gestnet_sgsv2.TESTO.MeasurerCommunicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Testo extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnBuscarAnalizador,btnCogerDatos;
    private ListView lvAnalizador;
    private BluetoothAdapter mBluetoothAdapter;
    public static BluetoothDevice bluetoothDeviceMeasurer = null;
    private BluetoothDevice mmDevice;
    private ArrayList<BluetoothDevice> listaDevice = new ArrayList<>();
    private ArrayList<String> listaNombre = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testo);
        btnBuscarAnalizador=(Button)findViewById(R.id.btnBuscarAnalizador);
        btnCogerDatos=(Button)findViewById(R.id.btnCogerDatos);
        lvAnalizador=(ListView)findViewById(R.id.lvAnalizador);

        btnBuscarAnalizador.setOnClickListener(this);
        btnCogerDatos.setOnClickListener(this);
        lvAnalizador.setOnItemClickListener(this);
        btnCogerDatos.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnBuscarAnalizador){
            listaDevice.clear();
            listaNombre.clear();
            findBT();
        }else if (v.getId()==R.id.btnCogerDatos){
            getValues();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mmDevice = listaDevice.get(parent.getPositionForView(view));
        connectMeasurer(mmDevice.getAddress());
        btnCogerDatos.setVisibility(View.VISIBLE);
        btnBuscarAnalizador.setVisibility(View.GONE);
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
            Toast.makeText(this, "connectMeasurer: ERROR: " + ex.toString(), Toast.LENGTH_SHORT).show();
        }

        try{
            if (bluetoothDeviceMeasurer != null)
                if (MeasurerCommunicator.getInstance().communicator != null) {
                    MeasurerCommunicator.getInstance().communicator.setMeasurerType(
                            MeasurerTypes.Testo_330_2_LL
                    );
                    MeasurerCommunicator.getInstance().communicator
                            .connect(bluetoothDeviceMeasurer);

                }
        }catch(Exception ex){
            Toast.makeText(this, "connectMeasurer: cannot connect to device " + address + ". Error: " + ex, Toast.LENGTH_LONG).show();
        }

    }
    private void getValues(){
        try{
            if (MeasurerCommunicator.getInstance().communicator != null){
                if (MeasurerCommunicator.getInstance().communicator.getState() == CommunicationInterface.STATE_CONNECTED) {

                    byte[] send = MeasurerCommunicator.getInstance().communicator.getCommandInterface().getViewValues(0x00);
                    MeasurerCommunicator.getInstance().communicator.write(send, sendCallBack, replyCallBackWithValues);
                }
            }

        }catch(Exception ex){
            Toast.makeText(this, "getValues. error=" + ex.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private SendCallBack sendCallBack = new SendCallBack() {
        public void send(byte[] buffer) {
            String hexWrite = ConvertHelper.convertByteArrayToHexString(buffer);
        }
    };
    private ReplyCallBack replyCallBackWithValues = new ReplyCallBack() {
        public void reply(List<TransmissionFormat> tfList) {

            try{
                MeasureValueFactoryInterface measureValueFactory =
                        MeasurerCommunicator.getInstance().communicator.getMeasureValueFactoryInterface(tfList);

                String text = "";
                int cnt = 0;
                ArrayList<MeasureValue> items = measureValueFactory.getValues();
                for (MeasureValue measureValue : items){
                    if (measureValue != null){
                        cnt++;
                        text += cnt + ".\t";
                        text += measureValue.getMeasureValueAsFormattedString();

                        int unitId = measureValue.getUnitId();
                        String unitText = Units.getText(unitId);
                        if (unitText == null) unitText = "";
                        text += "\t" + unitText;
                        text += "\n";
                    }
                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",text);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            } catch(Exception ex){
                Toast.makeText(Testo.this, "replyCallBackWithValues. error=" + ex.toString(), Toast.LENGTH_LONG).show();
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
}
