package com.multimedia.aes.gestnet_nucleo.fragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DisposicionesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ManoObraDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Disposiciones;
import com.multimedia.aes.gestnet_nucleo.entidades.FormasPago;
import com.multimedia.aes.gestnet_nucleo.entidades.ManoObra;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloCerrarParte;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;


public class TabFragment4_finalizacion extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;
    private EditText etPuestaEnMarcha,etServicioUrgencia,etKmsInicio,etKmsFin,etOperacionEfectuada,etNombreOtros,etPrecioOtros;
    private int horas;
    private Button btnAñadirDuracion,btnFinalizar;
    private String tiempoDuracion;
    private Spinner spFormaPago,spDisposicionServicio,spManoObra;
    private ArrayList <FormasPago> formasPagos = new ArrayList<>();
    private ArrayList <ManoObra> manosObra = new ArrayList<>();
    private ArrayList <Disposiciones> disposicionesServicio = new ArrayList<>();
    private String[] arrayFormasPago,arrayManosObra,arrayDisposiciones;
    private Parte parte = null;
    private Usuario usuario = null;
    private Maquina maquina = null;
    private DatosAdicionales datos = null;
    //METODO
    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment4_finalizacion, container, false);
        JSONObject jsonObject = null;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            idParte = jsonObject.getInt("id");
            parte = ParteDAO.buscarPartePorId(getContext(), idParte);
            usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(getContext(),parte.getFk_tecnico());
            maquina = MaquinaDAO.buscarMaquinaPorId(getContext(),parte.getFk_maquina());
            if(DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(),parte.getId_parte())!=null) {
                datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(), parte.getId_parte());
            }else{
                DatosAdicionales datosAdicionales = new DatosAdicionales();
                datosAdicionales.setFk_parte(parte.getId_parte());
                datos= DatosAdicionalesDAO.crearDatosAdicionalesRet(datosAdicionales,getContext());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inicializar();



       /* BottomNavigationView bottomNavigationView = (BottomNavigationView)
                vista.findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_item1:

                              Toast.makeText(vista.getContext(),"uno",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_item2:
                                Toast.makeText(vista.getContext(),"dos",Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.action_item3:
                                Toast.makeText(vista.getContext(),"tres",Toast.LENGTH_SHORT).show();

                                break;
                        }

                        return true;
                    }
                });

*/

        return vista;

    }


    private void inicializar(){
        etPuestaEnMarcha = (EditText)vista.findViewById(R.id.etPuestaMarcha);
        etServicioUrgencia = (EditText)vista.findViewById(R.id.etServicioUrgencia);
        etKmsInicio = (EditText)vista.findViewById(R.id.etKmsInicio);
        etKmsFin = (EditText)vista.findViewById(R.id.etKmsFin);
        etNombreOtros = (EditText)vista.findViewById(R.id.etNombreOtros);
        etPrecioOtros = (EditText)vista.findViewById(R.id.etPrecioOtros);
        etOperacionEfectuada=(EditText)vista.findViewById(R.id.etOperacionEfectuada);

        btnAñadirDuracion = (Button)vista.findViewById(R.id.btnAñadirDuracion);
        btnAñadirDuracion.setOnClickListener(this);
        spFormaPago = ( Spinner)vista.findViewById(R.id.spFormaPago) ;
       // spFormaPago.setOnClickListener(this);
        spDisposicionServicio = (Spinner) vista.findViewById(R.id.spDisposicionServicio);
     //   spDisposicionServicio.setOnClickListener(this);
        spManoObra = (Spinner) vista.findViewById(R.id.spManoObra);
        btnFinalizar=(Button)vista.findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(this);

        darValores();


    }


    private void darValores(){

        //SPINNER FORMAS PAGO
        if (FormasPagoDAO.buscarTodasLasFormasPago(getContext())!=null){
            formasPagos.addAll(FormasPagoDAO.buscarTodasLasFormasPago(getContext()));
            arrayFormasPago = new String[formasPagos.size()+ 1];
            arrayFormasPago[0]= "--Seleciones un valor--";
            for (int i = 1; i < formasPagos.size() + 1; i++) {
                arrayFormasPago[i] = formasPagos.get(i - 1).getForma_pago();
            }
            spFormaPago.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayFormasPago));

        }


        //SPINNER MANOS DE OBRA
        if(ManoObraDAO.buscarTodasLasManoDeObra(getContext())!=null) {
            manosObra.addAll(ManoObraDAO.buscarTodasLasManoDeObra(getContext()));
            arrayManosObra = new String[manosObra.size() + 1];
            arrayManosObra[0] = "--Seleciones un valor--";
            for (int i = 1; i < manosObra.size() + 1; i++) {
                arrayManosObra[i] = manosObra.get(i - 1).getConcepto();
            }
            spManoObra.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayManosObra));
        }
        //SPINNER DISPOSICIONES SERVICIO
        if(DisposicionesDAO.buscarTodasLasDisposiciones(getContext())!=null){
            disposicionesServicio.addAll(DisposicionesDAO.buscarTodasLasDisposiciones(getContext()));
            arrayDisposiciones = new String[disposicionesServicio.size()+ 1];
            arrayDisposiciones[0]= "--Seleciones un valor--";
            for (int i = 1; i < disposicionesServicio.size() + 1; i++) {
                arrayDisposiciones[i] = disposicionesServicio.get(i - 1).getNombre_disposicion();
            }
            spDisposicionServicio.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayDisposiciones));
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==btnAñadirDuracion.getId()){

            int hour = 0;
            int minute = 0;
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(getContext() , new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    btnAñadirDuracion.setText( selectedHour + " horas " + selectedMinute+" minutos");
                    horas=(selectedHour*60+selectedMinute)*60;

                }
            }, hour, minute, true);
            mTimePicker.setTitle("Selecciona la duración");
            mTimePicker.show();

        }else if(view.getId()==btnFinalizar.getId()){


            double disposicion=-1;
            int formaPago=-1;
            int manoObra=-1;
            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();


            try {
                disposicion = DisposicionesDAO.buscarPrecioDisposicionPorNombre(getContext(),spDisposicionServicio.getSelectedItem().toString());
                formaPago = FormasPagoDAO.buscarIdFormaPagoPorNombre(getContext(),spFormaPago.getSelectedItem().toString());
                manoObra = ManoObraDAO.buscarPrecioManoObraPorNombre(getContext(),spManoObra.getSelectedItem().toString());
                if (ArticuloParteDAO.buscarTodosLosArticuloPartePorFkParte(getContext(),parte.getId_parte())!=null){
                    articuloPartes.addAll(ArticuloParteDAO.buscarTodosLosArticuloPartePorFkParte(getContext(),parte.getId_parte()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

                String puestaMarcha=etPuestaEnMarcha.getText().toString();
                String servicioUrgencia=etServicioUrgencia.getText().toString();
                double kmsInicio=Double.valueOf(etKmsInicio.getText().toString());
                double kmsPrecio=Double.valueOf(etKmsFin.getText().toString());
                String OperacionEfectuada=etOperacionEfectuada.getText().toString();
                String nombreOtros=etNombreOtros.getText().toString();
                double precioAdicional=Double.valueOf(etPrecioOtros.getText().toString());
                double precioTotalArticulos = 0;
                if (!articuloPartes.isEmpty()){
                    precioTotalArticulos=getPrecioTotalArticulosParte(articuloPartes);
                }
            try {
                DatosAdicionalesDAO.actualizarDatosAdicionales(getContext(),datos.getId_rel(),formaPago, puestaMarcha,  disposicion,manoObra, servicioUrgencia, kmsPrecio, kmsInicio, OperacionEfectuada,nombreOtros,precioAdicional,precioTotalArticulos);
                ParteDAO.actualizarParteDuracion(getContext(),parte.getId_parte(),String.valueOf(horas));
                new HiloCerrarParte(getContext(),parte.getId_parte()).execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        }


    private double getPrecioTotalArticulosParte(ArrayList<ArticuloParte> listaArticulos) {
        double precio = 0;
        try {

            for (ArticuloParte articulo : listaArticulos) {
                precio = ArticuloDAO.buscarArticuloPorID(getContext(), articulo.getFk_articulo()).getTarifa();
            }
        }catch (SQLException e){


            Log.w("getPrecioTotal","error al sumar precio articulos parte");
        }

            return precio;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}