package com.jduenas.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleMascota extends AppCompatActivity {

    private static final String KEY_EXTRA_URL_FOTO = "url";
    private static final String KEY_EXTRA_LIKES = "likes";
    private ImageView imgFotoDetalle;
    private TextView tvLikesDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_mascota);


        Bundle extras   = getIntent().getExtras();
        String urlFoto  = extras.getString(KEY_EXTRA_URL_FOTO);
        int likes = extras.getInt(KEY_EXTRA_LIKES);

        tvLikesDetalle = (TextView) findViewById(R.id.tvLikesDetalle);
        tvLikesDetalle.setText(String.valueOf(likes));

        imgFotoDetalle = (ImageView) findViewById(R.id.imgFotoDetalle);
        Picasso.with(this)
                .load(urlFoto)
                .placeholder(R.drawable.ic_dog)
                .into(imgFotoDetalle);
    }
}
