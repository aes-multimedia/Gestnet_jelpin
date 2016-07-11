package com.multimedia.aes.gestnet_sgsv2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.j256.ormlite.table.DatabaseTable;
import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.UsoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposReparaciones;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class TabFragment3 extends Fragment {
    private View vista;
    private Spinner estadoVisita,tipoVisita,tipoReparacion,tiempoReparacion;
    private EditText codBarras,observaciones,costeMateriales,manoObra;
    private CheckBox contadorInterno,reparacion;
    private DatePicker fechaReparacion;
    private Button botonFinalizar;
    private List<TiposReparaciones> tiposReparacion;
    private String[] tipos;
    private TextView tvFechaVisita,tvFechaLimite,tvUrgencia;
    private Mantenimiento mantenimiento = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.tab_fragment_3, container, false);

        estadoVisita = (Spinner) vista.findViewById(R.id.spEstado_visita);
        tipoVisita = (Spinner) vista.findViewById(R.id.spTipo_visita);
        codBarras = (EditText) vista.findViewById(R.id.etCodigo_barras);
        observaciones = (EditText) vista.findViewById(R.id.etObservaciones);
        contadorInterno = (CheckBox) vista.findViewById(R.id.cbContador_interno);
        reparacion = (CheckBox) vista.findViewById(R.id.cbReparacion);
        tipoReparacion = (Spinner) vista.findViewById(R.id.spTipo_reparacion);
        tiempoReparacion = (Spinner) vista.findViewById(R.id.spTiempo_reparacion);
        costeMateriales= (EditText) vista.findViewById(R.id.etCosteMateriales);
        manoObra= (EditText) vista.findViewById(R.id.etCosteManoDeObra);
        fechaReparacion = (DatePicker) vista.findViewById(R.id.dpFechaReparacion);
        botonFinalizar=(Button) vista.findViewById(R.id.btnFinalizar);
        try {

            tiposReparacion = TiposReparacionesDAO.buscarTodosLosTiposReparaciones(getContext());
            tipos = new String[tiposReparacion.size()];
            for (int i = 0; i < tiposReparacion.size(); i++) {
                tipos[i]=tiposReparacion.get(i).getAbreviatura();
            }

            tvFechaVisita.setText(mantenimiento.getFecha_visita());
            tvFechaLimite.setText(mantenimiento.getFecha_maxima_endesa());
            tvUrgencia.setText(mantenimiento.getFecha_aviso());


                  } catch (SQLException e) {
            e.printStackTrace();
        }

        return vista;
    }




}