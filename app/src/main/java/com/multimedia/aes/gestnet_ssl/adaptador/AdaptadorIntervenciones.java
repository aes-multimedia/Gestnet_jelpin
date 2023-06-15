package com.multimedia.aes.gestnet_ssl.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.clases.Intervencion;
import com.multimedia.aes.gestnet_ssl.nucleo.FotosIntervenciones;

import java.util.ArrayList;

public class AdaptadorIntervenciones extends ArrayAdapter implements View.OnClickListener {

    private Context context;
    private int view;
    private ArrayList<Intervencion> intervencionArrayList;


    public AdaptadorIntervenciones(Context context, int view, ArrayList<Intervencion> intervencionArrayList) {
        super(context, view, intervencionArrayList);
        this.context=context;
        this.view=view;
        this.intervencionArrayList=intervencionArrayList;
         }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(view, null);
        }
        TextView tvTecnico,tvFecha,tvSintomas,tvOperacionEfectuada,tvTotal,tvNumParte,tvnumParteLabel;
        ConstraintLayout linearLayout  =  item.findViewById(R.id.lLRowIntervenciones);
        linearLayout.setTag(String.valueOf(intervencionArrayList.get(position).getId_parte()));

        tvTecnico=item.findViewById(R.id.tvTecnico);
        tvFecha=item.findViewById(R.id.tvFecha);
        tvSintomas=item.findViewById(R.id.tvSintomas);
        tvOperacionEfectuada=item.findViewById(R.id.tvOperacionEfectuada);
        tvTotal=item.findViewById(R.id.tvTotal);
        tvNumParte = item.findViewById(R.id.tvNumero);
        tvnumParteLabel = item.findViewById(R.id.numParteLabel);
        String numParte = intervencionArrayList.get(position).getNum_parte();

        tvTecnico.setText(intervencionArrayList.get(position).getTecnico());
        tvFecha.setText(intervencionArrayList.get(position).getFecha_visita());
        tvSintomas.setText(intervencionArrayList.get(position).getOtros_sintomas());
        tvOperacionEfectuada.setText(intervencionArrayList.get(position).getOperacion_efectuada());
        tvTotal.setText(String.valueOf(intervencionArrayList.get(position).getFacturado()));
        tvNumParte.setText(String.valueOf(intervencionArrayList.get(position).getNum_parte()));

        if(numParte.equals("")){
            tvNumParte.setVisibility(View.GONE);
            tvnumParteLabel.setVisibility(View.GONE);
        }


        linearLayout.setOnClickListener(this);

        return item;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.lLRowIntervenciones){
                int parte = Integer.parseInt(String.valueOf(v.getTag()));
                Intent i = new Intent(context, FotosIntervenciones.class);
                i.putExtra("id_parte",parte);
                getContext().startActivity(i);
        }



    }
}
