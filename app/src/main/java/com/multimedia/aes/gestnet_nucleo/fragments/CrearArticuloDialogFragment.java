package com.multimedia.aes.gestnet_nucleo.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;


@SuppressLint("ValidFragment")
public class CrearArticuloDialogFragment extends DialogFragment {

    private EditText etNombre,etUnidades,etPrecio,etCoste,etIva;
    private String nombre;
    private int iva,cantidadStock;
    private int unidades;
    private float precio,coste;
    private int idParte;
    private RadioButton radioButtonPedir,radioButtonUsar;
    private Context context;
    private CheckBox chGarantia;
    public CrearArticuloDialogFragment(Context context) {
        this.context = context;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        JSONObject jsonObject = null;
        idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            idParte = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.crear_articulo_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.crear, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        etNombre= getDialog().findViewById(R.id.etNombre);
                        etUnidades=getDialog().findViewById(R.id.etUnidades);
                        etPrecio= getDialog().findViewById(R.id.etPrecio);
                        etCoste= getDialog().findViewById(R.id.etCoste);
                        etIva= getDialog().findViewById(R.id.etIva);
                        radioButtonPedir = getDialog().findViewById(R.id.rButtonPedir);
                        radioButtonUsar = getDialog().findViewById(R.id.rButtonUsar);
                        chGarantia = getDialog().findViewById(R.id.chGarantia);

                    if(etUnidades.getText().toString().equals("")){
                        etUnidades.setText("1");
                    }
                    if(etPrecio.getText().toString().equals("")){
                        etPrecio.setText("0");
                    }
                    if(etCoste.getText().toString().equals("")){
                        etCoste.setText("0");
                    }
                    if(etIva.getText().toString().equals("")) {
                        etIva.setText("0");
                    }



                        if(etNombre.getText().toString().equals("")){
                            Dialogo.errorCrearMaterial(context);

                        }else{
                            nombre=etNombre.getText().toString();
                            unidades=Integer.parseInt(etUnidades.getText().toString());
                            iva=Integer.parseInt(etIva.getText().toString());
                            cantidadStock=Integer.parseInt(etUnidades.getText().toString());
                            precio=Float.parseFloat(etPrecio.getText().toString());
                            coste=Float.parseFloat(etCoste.getText().toString());

                            Articulo a = ArticuloDAO.newArticuloDialogFragment(getContext(),
                                    0,nombre,cantidadStock,"",
                                    "","","","",
                                    0,iva,precio,0,coste,"",0,radioButtonPedir.isChecked(),chGarantia.isChecked());
                            ArticuloParteDAO.newArticuloParte(getContext(),a.getId_articulo(),idParte,unidades);

                        }

                        try {
                            TabFragment6_materiales.llenarMateriales();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        //new HiloCrearArticulo(idParte, nombre, unidades, iva, cantidadStock, precio, coste,context).execute();
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CrearArticuloDialogFragment.this.getDialog().cancel();

                    }
                });
        return builder.create();
    }



    public int isChecked(RadioButton radioButton){
       return  radioButton.isChecked() ? 1 : 0;
    }
}