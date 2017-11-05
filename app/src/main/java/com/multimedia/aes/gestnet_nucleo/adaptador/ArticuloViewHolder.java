package com.multimedia.aes.gestnet_nucleo.adaptador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;

/**
 * Created by acp on 05/11/2017.
 */

public class ArticuloViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView tvTituloArticulo,tvStock,tvPrecio;
    ImageView ivFoto;
    LinearLayout llRow;

    public ArticuloViewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        tvTituloArticulo = (TextView) itemView.findViewById(R.id.tvTituloArticulo);
        tvStock = (TextView) itemView.findViewById(R.id.tvStock);
        tvPrecio = (TextView) itemView.findViewById(R.id.tvPrecio);
        ivFoto = (ImageView) itemView.findViewById(R.id.ivFoto);
        llRow = (LinearLayout) itemView.findViewById(R.id.llRow);


    }
}
