package com.jduenas.petagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jduenas.petagram.R;
import com.jduenas.petagram.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jduenas on 02/11/2016.
 */

public class PerfilMascotaAdaptador extends RecyclerView.Adapter<PerfilMascotaAdaptador.PerfilMascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity activity;

    public PerfilMascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public PerfilMascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_fotos,parent,false);
        return new PerfilMascotaAdaptador.PerfilMascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PerfilMascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        //holder.imgFoto.setImageResource(mascota.getFoto());
        Picasso.with(activity)
                .load(mascota.getUrl_foto())
                .placeholder(R.drawable.ic_dog)
                .into(holder.imgFoto);
        holder.tvLikesCV.setText(Integer.toString(mascota.getLikes()));

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilMascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvLikesCV;

        public PerfilMascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFotosPerfilCV);
            tvLikesCV = (TextView) itemView.findViewById(R.id.tvLikesFotosPerfilCV);
        }
    }
}