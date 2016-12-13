package com.jduenas.petagram.pojo;

/**
 * Created by jduenas on 25/10/2016.
 */
public class Mascota {

    private String id;
    private String nombreCompleto;
    private int likes;
    private String url_foto;

    public Mascota() {
    }

    public Mascota(String nombreCompleto, int likes, String url_foto) {
        this.nombreCompleto = nombreCompleto;
        this.likes = likes;
        this.url_foto = url_foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }
}
