package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.multimedia.aes.gestnet_sgsv2.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;;
import android.util.Printer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Bluetooth extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private TextView myLabel;
    private EditText myTextbox;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket mmSocket;
    private BluetoothDevice mmDevice;
    private OutputStream mmOutputStream;
    private InputStream mmInputStream;
    private Thread workerThread;
    private byte[] readBuffer;
    private int readBufferPosition;
    private volatile boolean stopWorker;
    private ArrayList<BluetoothDevice> listaDevice = new ArrayList<>();
    private ArrayList<String> listaNombre = new ArrayList<>();
    private ListView lvNombres;
    private Button openButton, sendButton, closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);
        openButton = (Button) findViewById(R.id.open);
        sendButton = (Button) findViewById(R.id.send);
        closeButton = (Button) findViewById(R.id.close);
        myLabel = (TextView) findViewById(R.id.label);
        lvNombres = (ListView) findViewById(R.id.lvNombres);

        lvNombres.setOnItemClickListener(this);
        openButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
    }
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
        }
    };

    @Override
    public void onClick(View view) {
        try {
            if (view.getId() == R.id.open) {
                listaDevice.clear();
                listaNombre.clear();
                findBT();
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(bReciever, filter);
                mBluetoothAdapter.startDiscovery();
            } else if (view.getId() == R.id.send) {
                printStruk();
            } else if (view.getId() == R.id.close) {
                closeBT();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }
    void openBT() throws IOException {
        try {
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();
            myLabel.setText("Bluetooth Opened");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendData(byte[] bytes) throws IOException {
        mmOutputStream.write(bytes);
    }

    private void printStruk() {
        String fecha = "22/06/2016";
        String hora = "12:06";
        String fecha_hora = "FECHA Y HORA: "+fecha+"-"+hora + "\n\n";
        String datos_cliente = "---------DATOS CLIENTE---------" + "\n";
        String nombre_cliente = "Maria Garcia Hinojosa" + "\n";
        String num_contrato = "000111522";
        String numero_contrato = "N. Contrato: "+num_contrato + "\n";
        String serv = "Mantenimiento Gas";
        String servicio = "Servicio: "+serv+ "\n";
        String dir = "Calle Ribadavia 11,2-A,"+"\n"+"Madrid,Madrid,20156";
        String direccion = "Direccion"+"\n"+dir+"\n\n";
        String datos_tecnico = "---------DATOS TECNICO---------" + "\n";
        String emp = "IBERDROLA";
        String empresa = "Empresa: "+emp+"\n";
        String cif_emp = "02365474S";
        String cif = "CIF: "+cif_emp+"\n";
        String num_emp_mant = "44556678";
        String numero_empresa_mantenedora = "N. Empresa Mantenedora: "+"\n"+num_emp_mant+"\n";
        String tec = "Pedro Buenhombre Lopez";
        String tecnico = "Tecnico: "+tec+"\n";
        String num_insta = "659898741";
        String numero_instalador = "N. Instalador: "+num_insta+"\n\n";
        String datos_averia = "----------DATOS AVERIA----------" + "\n";
        String noti = "21/06/2016";
        String notificada = "Notificada: "+noti+"\n";
        String atend = "18/06/2016-14:00";
        String atendida = "Atendida: "+atend+"\n";
        String prev_repar = "26/06/2016-13:30";
        String prevista_reparacion = "Prevista reparacion: "+"\n"+prev_repar+"\n";
        String repa = "24/06/2016-12:48";
        String reparada = "Reparada: "+repa+"\n";
        String num_solic = "6547952";
        String numero_solicitud = "N. Solicitud: "+num_solic+"\n";
        String cod_ave = "3216565";
        String codigo_averia = "Codigo Averia: "+cod_ave+"\n";
        String desc = "Una averia sin importancia";
        String descripcion = "Descripcion: "+"\n"+desc+"\n";



        String resultado = fecha_hora+datos_cliente+nombre_cliente+numero_contrato+servicio+direccion+
                datos_tecnico+empresa+cif+numero_empresa_mantenedora+tecnico+numero_instalador+datos_averia+
                notificada+atendida+prevista_reparacion+reparada+numero_solicitud+codigo_averia+descripcion;
        try {
            sendData(resultado.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void closeBT() throws IOException {
        try {
            stopWorker = true;
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();
            myLabel.setText("Bluetooth Closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ponerLista() {
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNombre);
        lvNombres.setAdapter(adaptador);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mmDevice = listaDevice.get(adapterView.getPositionForView(view));
        try {
            openBT();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
