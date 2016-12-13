package com.jduenas.petagram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jduenas.petagram.DetalleMascota;
import com.jduenas.petagram.R;
import com.jduenas.petagram.db.ConstructorMascotas;
import com.jduenas.petagram.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jduenas on 25/10/2016.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        final ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
        //mascotaViewHolder.imgFoto.setImageResource(mascota.getUrl_foto());
        Picasso.with(activity)
                .load(mascota.getUrl_foto())
                .placeholder(R.drawable.ic_dog)
                .into(mascotaViewHolder.imgFoto);
        mascotaViewHolder.tvLikesCV.setText(String.valueOf(mascota.getLikes()));

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetalleMascota.class);
                intent.putExtra("url", mascota.getUrl_foto());
                intent.putExtra("likes", mascota.getLikes());
                activity.startActivity(intent);
            }
        });

        /*mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mascota.setLikes(mascota.getLikes()+1);
                //mascotaViewHolder.tvLikesCV.setText(Integer.toString(mascota.getLikes()));

                constructorMascotas.darLikeMascota(mascota);
                mascotaViewHolder.tvLikesCV.setText(Integer.toString(constructorMascotas.obtenerLikesMascota(mascota)));

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        //private TextView tvNombreCV;
        private TextView tvLikesCV;
        //private ImageButton btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFotoCV);
            //tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvLikesCV = (TextView) itemView.findViewById(R.id.tvLikesCV);
            //btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
        }
    }
}
