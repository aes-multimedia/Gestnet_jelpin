package com.multimedia.aes.gestnet_nucleo.fragments;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.Utils.HeapSort;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

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
    private Button btnFinalizar;
    private String selected;

    //METODO
    private void inicializarVariables(){
        spProtocolos=(Spinner)vista.findViewById(R.id.spProtocolos);
        spProtocolos.setOnItemSelectedListener(this);
        llPadre = (LinearLayout) vista.findViewById(R.id.llPadre);
        btnFinalizar = (Button) vista.findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(this);
    }
    private void darValores() throws java.sql.SQLException {




        //SPINNER FORMAS PAGO
        if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkParte(getContext(),parte.getId_parte())!=null){
            try {
                protocoloAccionArrayList.addAll(ProtocoloAccionDAO.buscarPrueba(getContext(),parte.getId_parte()));
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            int indice=0;

           // ArrayList<ProtocoloAccion> lista = new HeapSort().sort(protocoloAccionArrayList);


            //ordenarArrayAccionProtocolos(protocoloAccionArrayList,indice);
            ArrayList <ProtocoloAccion> ordenadosNombre=new ArrayList<>();
            ordenadosNombre=ordenarArrayAccionProtocolosV2(protocoloAccionArrayList);


            arrayNombreProtocolos = new String[ordenadosNombre.size()+ 1];
            arrayNombreProtocolos[0]= "--Seleciones un valor--";
            for (int i = 1; i < ordenadosNombre.size() + 1; i++) {
                if (ordenadosNombre.get(i - 1).getFk_maquina()!=0&&ordenadosNombre.get(i - 1).getFk_maquina()!=-1){
                    arrayNombreProtocolos[i] = ordenadosNombre.get(i - 1).getNombre_protocolo()+"-"+ordenadosNombre.get(i - 1).getFk_maquina();
                }else{
                    arrayNombreProtocolos[i] = ordenadosNombre.get(i - 1).getNombre_protocolo();
                }
            }
            spProtocolos.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayNombreProtocolos));
        }else {
            arrayNombreProtocolos = new String[1];
            arrayNombreProtocolos[0]= "Sin protocolos";
            spProtocolos.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayNombreProtocolos));
        }
    }


    private void ordenarArrayAccionProtocolos(ArrayList<ProtocoloAccion> protocoloAccionArrayList,int indice) {

        if(protocoloAccionArrayList.size()>1 && indice < protocoloAccionArrayList.size() -1) {
            if (protocoloAccionArrayList.get(indice).getFk_protocolo() == protocoloAccionArrayList.get(indice + 1).getFk_protocolo() &&
                    protocoloAccionArrayList.get(indice).getFk_maquina() == protocoloAccionArrayList.get(indice + 1).getFk_maquina()) {
                protocoloAccionArrayList.remove(indice +1);
                ordenarArrayAccionProtocolos(protocoloAccionArrayList, indice );
            } else {
                ordenarArrayAccionProtocolos(protocoloAccionArrayList, indice + 1);
            }
        }
    }



    private ArrayList<ProtocoloAccion> ordenarArrayAccionProtocolosV2(ArrayList<ProtocoloAccion> protocoloAccionArrayList) {
        ArrayList<ProtocoloAccion>arrayList= new ArrayList<>();

        for (ProtocoloAccion p:protocoloAccionArrayList){
            if(!arrayListContieneProtocolo(arrayList,p))
                arrayList.add(p);

        }


            return arrayList;
    }



    private boolean arrayListContieneProtocolo( ArrayList<ProtocoloAccion>arrayList, ProtocoloAccion p){
        boolean esta=false;


        for (ProtocoloAccion pA: arrayList) {
            if(p.getFk_maquina()==pA.getFk_maquina() && p.getFk_protocolo()==pA.getFk_protocolo())
                esta=true;
        }

        return esta;
    }


    private void crearLinearProtocolo(String protocolo){
        llPadre.removeAllViews();
        llPadre.setVisibility(View.VISIBLE);
        btnFinalizar.setVisibility(View.VISIBLE);
        if (protocolo.contains("-")){
            String [] a = protocolo.split("-");
            try {
                if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquina(getContext(),a[0],Integer.parseInt(a[1]))!=null){
                    ArrayList<ProtocoloAccion> protocolos = new ArrayList<>();
                    protocolos.addAll(ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquina(getContext(),a[0],Integer.parseInt(a[1])));
                    for (int i = 0; i < protocolos.size(); i++) {
                        LinearLayout linearLayout = new LinearLayout(getContext());
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        linearLayout.setPadding(5,5,5,5);
                        if (protocolos.get(i).isTipo_accion()){
                            CheckBox checkBox = new CheckBox(getContext());
                            checkBox.setHintTextColor(Color.parseColor("#ff9002"));
                            checkBox.setLinkTextColor(Color.parseColor("#ff9002"));
                            checkBox.setText(protocolos.get(i).getDescripcion());
                            if (protocolos.get(i).getValor().equals("1")){
                                checkBox.setChecked(true);
                            }else{
                                checkBox.setChecked(false);
                            }
                            linearLayout.addView(checkBox);
                        }else{
                            TextView textView = new TextView(getContext());
                            textView.setTextSize(18);
                            textView.setTextColor(Color.BLACK);
                            textView.setText(protocolos.get(i).getDescripcion());
                            linearLayout.addView(textView);
                            EditText editText = new EditText(getContext());
                            editText.setBackgroundResource(R.drawable.edit_texts_naranja);
                            editText.setPadding(5,5,5,5);
                            editText.setText(protocolos.get(i).getValor());
                            linearLayout.addView(editText);
                        }
                        llPadre.addView(linearLayout);
                    }
                }
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParte(getContext(),protocolo,parte.getId_parte())!=null){
                    ArrayList<ProtocoloAccion> protocolos = new ArrayList<>();
                    protocolos.addAll(ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParte(getContext(),protocolo,parte.getId_parte()));
                    for (int i = 0; i < protocolos.size(); i++) {
                        LinearLayout linearLayout = new LinearLayout(getContext());
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        linearLayout.setPadding(5,5,5,5);
                        if (protocolos.get(i).isTipo_accion()){
                            CheckBox checkBox = new CheckBox(getContext());
                            checkBox.setHintTextColor(Color.parseColor("#ff9002"));
                            checkBox.setLinkTextColor(Color.parseColor("#ff9002"));
                            checkBox.setText(protocolos.get(i).getDescripcion());
                            if (protocolos.get(i).getValor().equals("1")){
                                checkBox.setChecked(true);
                            }else{
                                checkBox.setChecked(false);
                            }
                            linearLayout.addView(checkBox);
                        }else{
                            TextView textView = new TextView(getContext());
                            textView.setTextSize(18);
                            textView.setTextColor(Color.BLACK);
                            textView.setText(protocolos.get(i).getDescripcion());
                            linearLayout.addView(textView);
                            EditText editText = new EditText(getContext());
                            editText.setBackgroundResource(R.drawable.edit_texts_naranja);
                            editText.setPadding(5,5,5,5);
                            editText.setText(protocolos.get(i).getValor());
                            linearLayout.addView(editText);
                        }
                        llPadre.addView(linearLayout);
                    }
                }
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
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
            usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(getContext(),parte.getFk_tecnico());
            maquina = MaquinaDAO.buscarMaquinaPorFkMaquina(getContext(),parte.getFk_maquina());
            datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(),parte.getId_parte());
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
        selected=spProtocolos.getSelectedItem().toString();
        if (view.getId()==R.id.btnFinalizar){
            if (spProtocolos.getSelectedItem().toString().contains("-")){
                final int childCount = llPadre.getChildCount();
                String [] a = spProtocolos.getSelectedItem().toString().split("-");
                String nombre = a[0];
                int fk_maquina = Integer.parseInt(a[1]);
                for (int i = 0; i < childCount; i++) {
                    View v = llPadre.getChildAt(i);
                    LinearLayout ll = (LinearLayout)v;
                    final int childCount1 = (ll.getChildCount());
                    for (int j = 0; j <childCount1; j++) {
                        View view1 = ll.getChildAt(j);
                        String valor = "";
                        String descripcion = "";
                        if (view1 instanceof EditText){
                            EditText et = (EditText)view1;
                            valor = et.getText().toString();
                            View v1 = ll.getChildAt(j-1);
                            TextView txt = (TextView)v1;
                            descripcion = txt.getText().toString();
                            try {
                                if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaDescripcion(getContext(),nombre,fk_maquina,descripcion)!=null){
                                    int id = ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaDescripcion(getContext(),nombre,fk_maquina,descripcion).getId_protocolo_accion();
                                    ProtocoloAccionDAO.actualizarValor(getContext(),valor,id);
                                }

                            } catch (java.sql.SQLException e) {
                                e.printStackTrace();
                            }
                        }else if (view1 instanceof CheckBox){
                            CheckBox cb = (CheckBox)view1;
                            descripcion = cb.getText().toString();
                            if (cb.isChecked()){
                                valor = "1";
                            }else{
                                valor = "0";
                            }
                            try {
                                if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaDescripcion(getContext(),nombre,fk_maquina,descripcion)!=null){
                                    int id = ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaDescripcion(getContext(),nombre,fk_maquina,descripcion).getId_protocolo_accion();
                                    ProtocoloAccionDAO.actualizarValor(getContext(),valor,id);
                                }

                            } catch (java.sql.SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }else{
                final int childCount = llPadre.getChildCount();
                String nombre = spProtocolos.getSelectedItem().toString();
                for (int i = 0; i < childCount; i++) {
                    View v = llPadre.getChildAt(i);
                    LinearLayout ll = (LinearLayout)v;
                    final int childCount1 = (ll.getChildCount());
                    for (int j = 0; j <childCount1; j++) {
                        View view1 = ll.getChildAt(j);
                        String valor = "";
                        String descripcion = "";
                        if (view1 instanceof EditText){
                            EditText et = (EditText)view1;
                            valor = et.getText().toString();
                            View v1 = ll.getChildAt(j-1);
                            TextView txt = (TextView)v1;
                            descripcion = txt.getText().toString();
                            try {
                                if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParteDescripcion(getContext(),nombre,parte.getId_parte(),descripcion)!=null){
                                    int id = ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParteDescripcion(getContext(),nombre,parte.getId_parte(),descripcion).getId_protocolo_accion();
                                    ProtocoloAccionDAO.actualizarValor(getContext(),valor,id);
                                }

                            } catch (java.sql.SQLException e) {
                                e.printStackTrace();
                            }
                        }else if (view1 instanceof CheckBox){
                            CheckBox cb = (CheckBox)view1;
                            descripcion = cb.getText().toString();
                            if (cb.isChecked()){
                                valor = "1";
                            }else{
                                valor = "0";
                            }
                            try {
                                if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParteDescripcion(getContext(),nombre,parte.getId_parte(),descripcion)!=null){
                                    int id = ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParteDescripcion(getContext(),nombre,parte.getId_parte(),descripcion).getId_protocolo_accion();
                                    ProtocoloAccionDAO.actualizarValor(getContext(),valor,id);
                                }

                            } catch (java.sql.SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }



            Dialogo.dialogoError("Datos Guardados",getContext());
        }
        try {
            List<ProtocoloAccion> listaProto = ProtocoloAccionDAO.buscarProtocoloAccionPorFkParte(getContext(),parte.getId_parte());
            boolean empt = listaProto.isEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position!=0){
            crearLinearProtocolo(spProtocolos.getSelectedItem().toString());
        }else{
            llPadre.removeAllViews();
            llPadre.setVisibility(View.GONE);
            btnFinalizar.setVisibility(View.GONE);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
