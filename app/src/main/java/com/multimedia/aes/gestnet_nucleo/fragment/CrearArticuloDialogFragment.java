package com.multimedia.aes.gestnet_nucleo.fragment;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarArticulos;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloCrearArticulo;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;


public class CrearArticuloDialogFragment extends DialogFragment {

    private EditText etNombre,etUnidades,etPrecio,etCoste,etIva,etCantidadStock;
    private String nombre;
    private int unidades,iva,cantidadStock;
    private float precio,coste;
    private int idParte;
    private Context context;

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
                        etNombre= (EditText)getView().findViewById(R.id.etNombre);
                        etUnidades= (EditText)getView().findViewById(R.id.etUnidades);
                        etPrecio= (EditText)getView().findViewById(R.id.etPrecio);
                        etCoste= (EditText)getView().findViewById(R.id.etCoste);
                        etIva= (EditText)getView().findViewById(R.id.etIva);
                        etCantidadStock= (EditText)getView().findViewById(R.id.etCantidadStock);

                        nombre=etNombre.getText().toString();
                        unidades=Integer.parseInt(etUnidades.getText().toString());
                        iva=Integer.parseInt(etIva.getText().toString());
                        cantidadStock=Integer.parseInt(etCantidadStock.getText().toString());
                        precio=Float.parseFloat(etPrecio.getText().toString());
                        coste=Float.parseFloat(etCoste.getText().toString());
                        ArticuloDAO.newArticulo(getContext(),
                                0,nombre,cantidadStock,"",
                                "","","","",
                                0,iva,precio,0,coste,"",0);
                        new HiloCrearArticulo(idParte, nombre, unidades, iva, cantidadStock, precio, coste).execute();
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CrearArticuloDialogFragment.this.getDialog().cancel();
                        try {
                            TabFragment6_materiales.llenarMateriales();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return builder.create();
    }
}