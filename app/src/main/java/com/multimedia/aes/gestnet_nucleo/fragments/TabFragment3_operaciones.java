package com.multimedia.aes.gestnet_nucleo.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.field.types.SqlDateType;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Configuracion;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.nucleo.FotosProtocoloAccion;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabFragment3_operaciones extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private View vista;
    private ArrayList<ProtocoloAccion> protocoloAccionArrayList = new ArrayList<>();
    private String[] arrayNombreProtocolos;
    private Spinner spProtocolos;
    private Parte parte = null;
    private Usuario usuario = null;
    private Maquina maquina = null;
    private DatosAdicionales datos = null;
    private LinearLayout llPadre;
    private int posicion = 0;
    private Configuracion configuracion;

    //METODO
    private void inicializarVariables() {
        spProtocolos = vista.findViewById(R.id.spProtocolos);
        spProtocolos.setOnItemSelectedListener(this);
        llPadre = vista.findViewById(R.id.llPadre);
    }
    private void darValores() throws java.sql.SQLException {
        //SPINNER PROTOCOLO
        if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkParte(getContext(), parte.getId_parte()) != null) {
            try {
                protocoloAccionArrayList.addAll(ProtocoloAccionDAO.buscarPrueba(getContext(), parte.getId_parte()));
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            int indice = 0;
            ArrayList<ProtocoloAccion> ordenadosNombre;
            ordenadosNombre = ordenarArrayAccionProtocolosV2(protocoloAccionArrayList);
            ordenadosNombre.add(0, new ProtocoloAccion(-1, "", -1, parte.getId_parte(), -1, "--Selecione un valor--", -1, false, "", -1));
            ProtocoloAccion[] arrayProtocolos = new ProtocoloAccion[ordenadosNombre.size()];
            for (int i = 0; i < arrayProtocolos.length; i++) {
                arrayProtocolos[i] = ordenadosNombre.get(i);
            }
            spProtocolos.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayProtocolos));
        } else {
            ArrayList<ProtocoloAccion> ordenadosNombre = new ArrayList<>();
            ordenadosNombre.add(0, new ProtocoloAccion(-1, "", -1, parte.getId_parte(), -1, "Sin Protocolos", -1, false, "", -1));
            ProtocoloAccion[] arrayProtocolos = new ProtocoloAccion[ordenadosNombre.size()];
            for (int i = 0; i < arrayProtocolos.length; i++) {
                arrayProtocolos[i] = ordenadosNombre.get(i);
            }
            spProtocolos.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayProtocolos));
        }
    }
    private ArrayList<ProtocoloAccion> ordenarArrayAccionProtocolosV2(ArrayList<ProtocoloAccion> protocoloAccionArrayList) {
        ArrayList<ProtocoloAccion> arrayList = new ArrayList<>();
        for (ProtocoloAccion p : protocoloAccionArrayList) {
            if (!arrayListContieneProtocolo(arrayList, p))
                arrayList.add(p);
        }
        return arrayList;
    }
    private boolean arrayListContieneProtocolo(ArrayList<ProtocoloAccion> arrayList, ProtocoloAccion p) {
        boolean esta = false;
        for (ProtocoloAccion pA : arrayList) {
            if (p.getFk_maquina() == pA.getFk_maquina() && p.getFk_protocolo() == pA.getFk_protocolo())
                esta = true;
        }
        return esta;
    }
    private void crearLinearProtocolo(ProtocoloAccion protocolo) {
        llPadre.removeAllViews();
        llPadre.setVisibility(View.VISIBLE);
        try {
            ArrayList<ProtocoloAccion> protocolos = new ArrayList<>();
        if (protocolo.getFk_maquina() != 0 && protocolo.getFk_maquina() != -1) {
            if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkMaquina(getContext(), protocolo.getFk_protocolo(), protocolo.getFk_maquina(), protocolo.getFk_parte()) != null) {
                protocolos.addAll(ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkMaquina(getContext(), protocolo.getFk_protocolo(), protocolo.getFk_maquina(), protocolo.getFk_parte()));
            }
        }else{
            if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParte(getContext(), protocolo.getNombre_protocolo(), parte.getId_parte()) != null) {
                protocolos.addAll(ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParte(getContext(), protocolo.getNombre_protocolo(), parte.getId_parte()));
            }
        }
            for (int i = 0; i < protocolos.size(); i++) {
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setTag(protocolos.get(i).getId_accion());
                linearLayout.setPadding(5, 5, 5, 5);
                if (protocolos.get(i).isTipo_accion()) {
                    LinearLayout linearLayout1 = new LinearLayout(getContext());
                    linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(70, 70);
                    if (configuracion.isParte_galeria()){
                        ImageButton imageButton = new ImageButton(getContext());
                        imageButton.setLayoutParams(lp);
                        imageButton.setBackgroundResource(R.drawable.ic_menu_camera);
                        imageButton.setTag(protocolos.get(i).getId_protocolo_accion());
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent i = new Intent(getActivity(), FotosProtocoloAccion.class);
                                i.putExtra("id",Integer.parseInt(String.valueOf(v.getTag())));
                                getActivity().startActivity(i);
                            }
                        });
                        linearLayout1.addView(imageButton);
                    }
                    CheckBox checkBox = new CheckBox(getContext());
                    checkBox.setHintTextColor(Color.parseColor("#ff9002"));
                    checkBox.setLinkTextColor(Color.parseColor("#ff9002"));
                    checkBox.setText(protocolos.get(i).getDescripcion());
                    if (protocolos.get(i).getValor().equals("1")) {
                        checkBox.setChecked(true);
                    } else {
                        checkBox.setChecked(false);
                    }
                    linearLayout1.addView(checkBox);
                    linearLayout.addView(linearLayout1);
                } else {
                    LinearLayout linearLayout1 = new LinearLayout(getContext());
                    LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    linearLayout1.setLayoutParams(l);
                    linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                    if (configuracion.isParte_galeria()){
                        ImageButton imageButton = new ImageButton(getContext());
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(70, 70);
                        imageButton.setLayoutParams(lp);
                        imageButton.setBackgroundResource(R.drawable.ic_menu_camera);
                        imageButton.setTag(protocolos.get(i).getId_protocolo_accion());
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent i = new Intent(getActivity(), FotosProtocoloAccion.class);
                                i.putExtra("id",Integer.parseInt(String.valueOf(v.getTag())));
                                getActivity().startActivity(i);
                            }
                        });
                        linearLayout1.addView(imageButton);
                    }

                    TextView textView = new TextView(getContext());
                    textView.setTextSize(18);
                    textView.setTextColor(Color.BLACK);
                    textView.setText(protocolos.get(i).getDescripcion());
                    linearLayout.addView(textView);
                    EditText editText = new EditText(getContext());
                    editText.setBackgroundResource(R.drawable.edit_texts_naranja);
                    editText.setPadding(5, 5, 5, 5);
                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    editText.setLayoutParams(lp2);
                    editText.setText(protocolos.get(i).getValor());
                    linearLayout1.addView(editText);
                    linearLayout.addView(linearLayout1);
                }
                llPadre.addView(linearLayout);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void guardarProtocolo() throws SQLException {
        ProtocoloAccion protocoloAccion = (ProtocoloAccion) spProtocolos.getItemAtPosition(posicion);
        if (protocoloAccion.getFk_maquina() != 0 && protocoloAccion.getFk_maquina() != -1) {
            final int childCount = llPadre.getChildCount();
            int fk_maquina = protocoloAccion.getFk_maquina();
            for (int i = 0; i < childCount; i++) {
                View v = llPadre.getChildAt(i);
                LinearLayout ll = (LinearLayout) v;
                final int childCount1 = (ll.getChildCount());
                for (int j = 0; j < childCount1; j++) {
                    View view1 = ll.getChildAt(j);
                    String valor = "";
                    if (view1 instanceof EditText) {
                        EditText et = (EditText) view1;
                        valor = et.getText().toString();
                        if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaIdAccion(getContext(), protocoloAccion.getFk_protocolo(), fk_maquina, Integer.parseInt(ll.getTag().toString())) != null) {
                            int id = ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaIdAccion(getContext(), protocoloAccion.getFk_protocolo(), fk_maquina, Integer.parseInt(ll.getTag().toString())).getId_protocolo_accion();
                            ProtocoloAccionDAO.actualizarValorFkProtocoloFkMaquina(getContext(), valor, id, fk_maquina);
                        }
                    } else if (view1 instanceof CheckBox) {
                        CheckBox cb = (CheckBox) view1;
                        if (cb.isChecked()) {
                            valor = "1";
                        } else {
                            valor = "0";
                        }
                        if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaIdAccion(getContext(), protocoloAccion.getFk_protocolo(), fk_maquina, Integer.parseInt(ll.getTag().toString())) != null) {
                            int id = ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaIdAccion(getContext(), protocoloAccion.getFk_protocolo(), fk_maquina, Integer.parseInt(ll.getTag().toString())).getId_protocolo_accion();
                            ProtocoloAccionDAO.actualizarValorFkProtocoloFkMaquina(getContext(), valor, id, fk_maquina);
                        }
                    }
                }

            }
        } else {
            final int childCount = llPadre.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View v = llPadre.getChildAt(i);
                LinearLayout ll = (LinearLayout) v;
                final int childCount1 = (ll.getChildCount());
                for (int j = 0; j < childCount1; j++) {
                    View view1 = ll.getChildAt(j);
                    String valor = "";
                    if (view1 instanceof EditText) {
                        EditText et = (EditText) view1;
                        valor = et.getText().toString();

                        if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkParteIdAccion(getContext(), protocoloAccion.getFk_protocolo(), parte.getId_parte(), Integer.parseInt(ll.getTag().toString())) != null) {
                            int id = ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkParteIdAccion(getContext(), protocoloAccion.getFk_protocolo(), parte.getId_parte(), Integer.parseInt(ll.getTag().toString())).getId_protocolo_accion();
                            ProtocoloAccionDAO.actualizarValorFkProtocoloIdParte(getContext(), valor, id, parte.getId_parte());
                        }
                    } else if (view1 instanceof CheckBox) {
                        CheckBox cb = (CheckBox) view1;
                        if (cb.isChecked()) {
                            valor = "1";
                        } else {
                            valor = "0";
                        }
                        if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkParteIdAccion(getContext(), protocoloAccion.getFk_protocolo(), parte.getId_parte(), Integer.parseInt(ll.getTag().toString())) != null) {
                            int id = ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkParteIdAccion(getContext(), protocoloAccion.getFk_protocolo(), parte.getId_parte(), Integer.parseInt(ll.getTag().toString())).getId_protocolo_accion();
                            ProtocoloAccionDAO.actualizarValorFkProtocoloIdParte(getContext(), valor, id, parte.getId_parte());
                        }

                    }
                }
            }
        }

    }
    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment3_operaciones, container, false);
        JSONObject jsonObject = null;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            idParte = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            parte = ParteDAO.buscarPartePorId(getContext(), idParte);
            usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(getContext(), parte.getFk_tecnico());
            maquina = MaquinaDAO.buscarMaquinaPorFkMaquina(getContext(), parte.getFk_maquina());
            datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(), parte.getId_parte());
            configuracion = ConfiguracionDAO.buscarTodasLasConfiguraciones(getContext()).get(0);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            inicializarVariables();
            darValores();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return vista;
    }
    @Override
    public void onClick(View view) {
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (llPadre.getChildCount() != 0) {

            try {
                guardarProtocolo();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (position != 0) {
            posicion = position;
            crearLinearProtocolo((ProtocoloAccion) spProtocolos.getSelectedItem());
        } else {
            posicion = 0;
            llPadre.removeAllViews();
            llPadre.setVisibility(View.GONE);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
