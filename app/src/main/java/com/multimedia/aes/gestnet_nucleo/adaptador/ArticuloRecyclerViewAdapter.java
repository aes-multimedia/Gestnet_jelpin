package com.multimedia.aes.gestnet_nucleo.adaptador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.util.Pair;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.nucleo.InfoArticulos;

import java.util.Collections;
import java.util.List;

/**
 * Created by acp on 05/11/2017.
 */

public class ArticuloRecyclerViewAdapter extends RecyclerView.Adapter<ArticuloViewHolder> {

        List<Articulo> list = Collections.emptyList();
        Context context;
        Activity activity;

public ArticuloRecyclerViewAdapter(List<Articulo> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
        }

@Override
public ArticuloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_articulo, parent, false);
        ArticuloViewHolder holder = new ArticuloViewHolder(v);
        return holder;

        }



@Override
public void onBindViewHolder(final ArticuloViewHolder holder, int position) {


        holder.tvTituloArticulo.setText(list.get(position).getNombre_articulo());
        holder.tvStock.setText(String.valueOf(list.get(position).getStock()));
        holder.tvPrecio.setText(String.valueOf(list.get(position).getTarifa()));
        //holder.ivFoto.setImageResource(list.get(position).getImagen());
        holder.llRow.setTag(list.get(position).getId_articulo());
        holder.llRow.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent I = new Intent(activity,InfoArticulos.class);
        I.putExtra("articuloId",Integer.parseInt(String.valueOf(holder.llRow.getTag())));

      //  Pair<View,String> pair1 = Pair.create(v.findViewById(R.id.ivFoto),"miImagen");
         Pair<View,String> pair2 = Pair.create(v.findViewById(R.id.tvTituloArticulo),"titulo");
        Pair<View,String> pair3 = Pair.create(v.findViewById(R.id.tvPrecio),"precio");
        Pair<View,String> pair4 = Pair.create(v.findViewById(R.id.tvStock),"stock");
       Pair<View,String> pair6 = Pair.create(v.findViewById(R.id.llRow),"llRow");


        ActivityOptionsCompat activityOptionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(activity,pair2,pair3,pair4,pair6);
        activity.startActivity(I,activityOptionsCompat.toBundle());
        }
        /* TODO: Implement abstract overrides. */
        });



        //animate(holder);

        }

@Override
public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
        }

@Override
public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        }

// Insert a new item to the RecyclerView on a predefined position
public void insert(int position, Articulo data) {
        list.add(position, data);
        notifyItemInserted(position);
        }

// Remove a RecyclerView item containing a specified Data object
public void remove(Articulo data){
        int position=list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
        }
}