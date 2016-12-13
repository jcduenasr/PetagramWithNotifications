package com.jduenas.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jduenas.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by jduenas on 09/11/2016.
 */

public class BaseDatos extends SQLiteOpenHelper{

    private Context context;
    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCrearTablaMascota = "CREATE TABLE "+ConstantesBaseDatos.TABLE_MASCOTA+"("+
                                        ConstantesBaseDatos.TABLE_MASCOTA_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                        ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE    + " TEXT, "+
                                        ConstantesBaseDatos.TABLE_MASCOTA_FOTO      + " INTEGER "+
                                        ")";
        String queryCrearTablaMascotaLikes = "CREATE TABLE "+ConstantesBaseDatos.TABLE_MASCOTA_LIKES+"("+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA      + " INTEGER, "+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES    + " INTEGER, "+
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES "+ ConstantesBaseDatos.TABLE_MASCOTA +"("+ConstantesBaseDatos.TABLE_MASCOTA_ID+")"+
                ")";
        sqLiteDatabase.execSQL(queryCrearTablaMascota);
        sqLiteDatabase.execSQL(queryCrearTablaMascotaLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+ ConstantesBaseDatos.TABLE_MASCOTA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+ ConstantesBaseDatos.TABLE_MASCOTA_LIKES);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT *FROM "+ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
            Mascota currentMascota = new Mascota();
            //currentMascota.setId(registros.getInt(0));
            //currentMascota.setNombre(registros.getString(1));
            //currentMascota.setFoto(registros.getInt(2));
            /*String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES+") as numerolikes"+
                                " FROM "+ConstantesBaseDatos.TABLE_MASCOTA_LIKES+
                                " WHERE "+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA+"="+currentMascota.getId();
            Cursor registroLikes = db.rawQuery(queryLikes,null);
            if (registroLikes.moveToNext()){
                currentMascota.setLikes(registroLikes.getInt(0));
            }*/
            mascotas.add(currentMascota);
        }
        db.close();
        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA,null, contentValues);
        db.close();
    }

    public  void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA_LIKES,null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;
        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES+")"+
                        " FROM "+ConstantesBaseDatos.TABLE_MASCOTA_LIKES+
                        " WHERE "+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA+"="+mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);
        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        return likes;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT m.*, (SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES+")"+
                                        " FROM "+ConstantesBaseDatos.TABLE_MASCOTA_LIKES+
                                        " WHERE "+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA+"=m.id) as likes"+
                        " FROM "+ConstantesBaseDatos.TABLE_MASCOTA+" m"+
                        " ORDER BY likes DESC"+
                        " LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
           /* Mascota currentMascota = new Mascota();
            currentMascota.setId(registros.getInt(0));
            currentMascota.setNombre(registros.getString(1));
            currentMascota.setFoto(registros.getInt(2));

            mascotas.add(currentMascota);*/
        }
        db.close();
        return mascotas;
    }
}
