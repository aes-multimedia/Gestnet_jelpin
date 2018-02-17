package com.multimedia.aes.gestnet_nucleo.adaptador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.clases.DataStock;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by acp on 17/02/2018.
 */

public class AdaptadorListaStock extends ArrayAdapter<DataStock> {
    public AdaptadorListaStock(Context context, ArrayList<DataStock> stocks) {
        super(context, 0, stocks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataStock stock = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_stock, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNombreEntidad);
        TextView tvStock = (TextView) convertView.findViewById(R.id.tvStock);
        // Populate the data into the template view using the data object
        tvName.setText(stock.getNombreEntidad());
        tvStock.setText(String.valueOf(stock.getStock()));
        // Return the completed view to render on screen
        return convertView;
    }
}