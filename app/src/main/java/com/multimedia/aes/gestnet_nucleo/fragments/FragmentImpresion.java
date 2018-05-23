package com.multimedia.aes.gestnet_nucleo.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.clases.Impresion;
import com.multimedia.aes.gestnet_nucleo.clases.Impresora;
import com.multimedia.aes.gestnet_nucleo.clases.PrinterCommunicator;
import com.multimedia.aes.gestnet_nucleo.clases.Ticket;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterFactory;
import com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper;
import com.multimedia.aes.gestnet_nucleo.progressDialog.ManagerProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class FragmentImpresion extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mmDevice;
    private ArrayList<BluetoothDevice> listaDevice = new ArrayList<>();
    private ArrayList<String> listaNombre = new ArrayList<>();
    private ListView lvNombres;
    private Button openButton, sendButton, closeButton,btnOtra;
    private TextView txtImpreso,txtImpreso2,txtImpreso3;
    private LinearLayout llImpreso,llBotones;
    private Impresora impresora;
    private ImageView ivLogo,ivFirma1,ivFirma2;
    private View vista;
    private ScrollView scTicket;
    private Parte parte;
    private Usuario usuario;
    private Ticket ticket;


    //METODOS
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
            if (ManagerProgressDialog.getDialog()!=null) {
                ManagerProgressDialog.cerrarDialog();
            }
            getContext().unregisterReceiver(bReciever);
        }
    };
    private class PrinterConnectTask extends android.os.AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(final String... arg0) {
            int count = arg0.length;
            if (count <= 0)
                return null;
            final String address = arg0[0];

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    connectPrinter(address);
                }
            });
            return null;
        } // doInBackground

    } // PrinterConnectTask
    private void connectPrinter(String address) {
        try {
            PrinterCommunicator.getInstance().printerMacAddress = address;
            PrinterCommunicator.getInstance().btPrinterHelper
                    .connectTo(PrinterCommunicator.getInstance().printerMacAddress);
            if (PrinterCommunicator.getInstance().btPrinterHelper.isConnected()) {
                PrinterHelper helper = (PrinterHelper) PrinterCommunicator
                        .getInstance().btPrinterHelper;
                PrinterFactory factory = new PrinterFactory(helper);
                try {
                    PrinterCommunicator.getInstance().printer = factory
                            .getPrinter(address);
                } catch (Exception e) {
                }
            }
        } catch (Exception ex) {
            Log.e("ERROR", "connectPrinter: cannot connect to device " + address
                    + ". Error: " + ex);
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
        ponerLista();
    }
    public void ponerLista() {
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listaNombre);
        lvNombres.setAdapter(adaptador);
    }
    private Bitmap generarImagen() throws IOException {
        InputStream bitmap = null;
        bitmap =  getContext().getAssets().open("logo.png");
        Bitmap btmp= BitmapFactory.decodeStream(bitmap);
        return btmp;
    }

    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vista = inflater.inflate(R.layout.bluetooth_impresion, container, false);
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            int id = jsonObject.getInt("id");
            parte = ParteDAO.buscarPartePorId(getContext(),id);
            usuario = UsuarioDAO.buscarUsuario(getContext());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        openButton = (Button) vista.findViewById(R.id.open);
        sendButton = (Button) vista.findViewById(R.id.send);
        closeButton = (Button) vista.findViewById(R.id.close);
        btnOtra = (Button) vista.findViewById(R.id.btnOtra);
        lvNombres = (ListView) vista.findViewById(R.id.lvNombres);
        txtImpreso = (TextView) vista.findViewById(R.id.txtImpreso);
        llImpreso = (LinearLayout) vista.findViewById(R.id.llImpreso);
        llBotones = (LinearLayout) vista.findViewById(R.id.llBotones);
        txtImpreso2 = (TextView) vista.findViewById(R.id.txtImpreso2);
        txtImpreso3 = (TextView) vista.findViewById(R.id.txtImpreso3);
        ivLogo = (ImageView) vista.findViewById(R.id.ivLogo);
        ivFirma1 = (ImageView) vista.findViewById(R.id.ivFirmaUno);
        ivFirma2 = (ImageView) vista.findViewById(R.id.ivFirmaDos);

        scTicket = (ScrollView) vista.findViewById(R.id.scTicket);

        lvNombres.setOnItemClickListener(this);
        openButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
        btnOtra.setOnClickListener(this);
        sendButton.setVisibility(View.GONE);
        closeButton.setVisibility(View.GONE);
        llImpreso.setVisibility(View.VISIBLE);
        scTicket.setVisibility(View.VISIBLE);
        lvNombres.setVisibility(View.GONE);
        btnOtra.setVisibility(View.GONE);
        try {
            ivLogo.setImageBitmap(generarImagen());

        } catch (IOException e) {
            e.printStackTrace();
        }
        String impreso = "";
        String impreso2 = "";
        try {


            impreso+= Impresion.encabezadoPresupuesto();
            impreso+= Impresion.ticket(parte.getId_parte(),getContext());
            impreso+= Impresion.piePresupuesto();
            impreso+= Impresion.conformeCliente(parte.getId_parte(),getContext());
            impreso2+= Impresion.conformeTecnico(getContext());


        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtImpreso.setText(impreso);
        txtImpreso2.setText(impreso2);
        findBT();
        return vista;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.open) {
            btnOtra.setVisibility(View.VISIBLE);
            listaDevice.clear();
            listaNombre.clear();
            findBT();
            lvNombres.setVisibility(View.VISIBLE);
            llImpreso.setVisibility(View.GONE);
            scTicket.setVisibility(View.GONE);
            sendButton.setVisibility(View.GONE);
            closeButton.setVisibility(View.GONE);
            openButton.setVisibility(View.GONE);
        } else if (view.getId() == R.id.send) {
            llBotones.setVisibility(View.VISIBLE);
            if (parte.getFirma64().equals("")||parte.getFirma64()==null){
                Dialogo.dialogoError("Falta firma del cliente.(Pestaña de Documentos)",getContext());
            }else{
                byte[] decodedBytes = Base64.decode(parte.getFirma64(), 0);
                Bitmap bfirma =  BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                ivFirma2.setImageBitmap(bfirma);
                if (usuario.getFirma().equals("")||usuario.getFirma()==null){
                    Dialogo.dialogoError("Falta firma del tecnico.(Mis Ajustes-Mi firma)",getContext());
                }else{
                    decodedBytes = Base64.decode(usuario.getFirma(), 0);
                    bfirma =  BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                    ivFirma1.setImageBitmap(bfirma);
                    sendButton.setVisibility(View.GONE);
                    closeButton.setVisibility(View.VISIBLE);
                    openButton.setVisibility(View.GONE);
                    lvNombres.setVisibility(View.GONE);
                    llImpreso.setVisibility(View.VISIBLE);
                    scTicket.setVisibility(View.VISIBLE);
                }
            }
        } else if (view.getId() == R.id.close) {
            closeButton.setVisibility(View.GONE);
            if (parte.getTicket()!=null){
                impresora = new Impresora(getActivity(),mmDevice,getContext());
                impresora.imprimir();
            }else{
                Bitmap bitmap1 = Bitmap.createBitmap( llImpreso.getWidth(), llImpreso.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap1);
                llImpreso.draw(canvas);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                try {
                    if (ParteDAO.actualizarTicket(getContext(),parte.getId_parte(),encodedImage)) {
                        impresora = new Impresora(getActivity(), mmDevice,getContext());
                        impresora.imprimir();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }else if (view.getId() == R.id.btnOtra) {
            ManagerProgressDialog.abrirDialog(getContext());
            ManagerProgressDialog.buscandoBluetooth(getContext());
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            getContext().registerReceiver(bReciever, filter);
            mBluetoothAdapter.startDiscovery();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        llBotones.setVisibility(View.VISIBLE);
        lvNombres.setVisibility(View.GONE);
        llImpreso.setVisibility(View.VISIBLE);
        scTicket.setVisibility(View.VISIBLE);
        sendButton.setVisibility(View.VISIBLE);
        closeButton.setVisibility(View.GONE);
        openButton.setVisibility(View.GONE);
        btnOtra.setVisibility(View.GONE);
        String impreso = "";
        String impreso2 = "";
        try {
            impreso+= Impresion.encabezadoPresupuesto();
            impreso+= Impresion.ticket(parte.getId_parte(),getContext());
            impreso+= Impresion.piePresupuesto();
            impreso+= Impresion.conformeCliente(parte.getId_parte(),getContext());
            impreso2+= Impresion.conformeTecnico(getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtImpreso.setText(impreso);
        txtImpreso2.setText(impreso2);
        try {
            ivLogo.setImageBitmap(generarImagen());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmDevice = listaDevice.get(adapterView.getPositionForView(view));
        Dialogo.impresoraSeleccionada(getContext());
        if (parte.getFirma64()!=null){
            sendButton.setText("Imprimir");
        }
    }
}
