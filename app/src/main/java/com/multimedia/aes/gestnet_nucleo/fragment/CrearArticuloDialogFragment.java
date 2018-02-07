package com.multimedia.aes.gestnet_nucleo.fragment;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarArticulos;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;


public class CrearArticuloDialogFragment extends DialogFragment {

    private EditText etNombre,etUnidades,etPrecio,etCoste,etIva,etCantidadStock;
    private String nombre;
    private int unidades,iva,cantidadStock;
    private float precio,coste;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();






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




                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CrearArticuloDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}