package com.example.gps.gps;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class bdManager {
    //////////////////////////tabla principal ciclos//////////////////////////
    public static final String TABLE_NAME = "capturas";
    public static final String CN_ID="_id";
    public static final String CN_NAME ="nombre";
    public static final String CN_LATITUD="latitud";
    public static final String CN_LONGITUD="longitud";

    ////////////////////////////Creacion tablas//////////////////////////////

    public static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("
            +CN_ID+" integer primary key autoincrement,"
            +CN_NAME+ " text not null,"
            +CN_LATITUD+" text not null,"
            +CN_LONGITUD+" text not null);";

    private dbGPS dbgps;
    private SQLiteDatabase db;
    /////////////////////////////////////////constructor////////////////////////////////////////////////
    public bdManager(Context context) {

        dbgps = new dbGPS(context);
        db = dbgps.getWritableDatabase();
    }

    ///////////////////////////////////////insertar VALOR//////////////////////////////////////
    public ContentValues generarValor(String nombre, String latitud,String longitud){
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME,nombre);
        valores.put(CN_LATITUD,latitud);
        valores.put(CN_LONGITUD,longitud);
        return valores;
    }
    public void insertarvalor(String nombre, String latitud,String longitud)
    {
        db.insert(TABLE_NAME,null,generarValor(nombre, latitud,longitud));
    }

    /////////////////////////////////////////cargar bd///////////////////////////////////////////
    public Cursor cargardb()
    {
        String[] columnas = new String[]{CN_ID,CN_NAME,CN_LATITUD,CN_LONGITUD};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

}
