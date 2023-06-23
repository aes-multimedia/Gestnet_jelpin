package com.multimedia.aes.gestnet_ssl.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.clases.DataImagenes;
import com.multimedia.aes.gestnet_ssl.nucleo.FotosProtocoloAccion;

import java.util.ArrayList;
public class AdaptadorListaImagenesAccion extends RecyclerView.Adapter<AdaptadorListaImagenesAccion.ViewHolder> {

    private ArrayList<DataImagenes> arrayList;
    private Context context;


    public AdaptadorListaImagenesAccion(ArrayList<DataImagenes> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    public void onItemClick(int position) {
        FotosProtocoloAccion.borrarArrayImagenes(position,context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImagen;
        public ImageView ivBorrar;
        public ImageView ivEnviar;

        public ViewHolder(View vCard) {
            super(vCard);
            ivImagen = vCard.findViewById(R.id.ivImagen);
            ivBorrar = vCard.findViewById(R.id.ivBorrar);
            ivEnviar = vCard.findViewById(R.id.ivEnviar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.camp_adapter_list_view_imagenes_accion, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ivBorrar.setTag(position);
        holder.ivBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(position);
            }
        });
        if (arrayList.get(position).enviado){
            holder.ivEnviar.setBackgroundResource(R.drawable.ic_done_green);
        }else{
            holder.ivEnviar.setBackgroundResource(R.drawable.ic_done_red);
        }
        holder.ivImagen.setImageBitmap(arrayList.get(position).bitmap);
    }

    @Override
    public int getItemCount() {
        if (arrayList!=null){
            return arrayList.size();
        }else{
            return 0;
        }

    }


}