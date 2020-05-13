package com.example.ayofutsaladmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_futsalkuy.db";
    public static final String TABLE_NAME_USER = "tbl_user";
    public static final String TABLE_NAME_LAPANGAN = "tbl_lapangan";
    public static final String TABLE_NAME_PESANAN = "tbl_pemesanan";
//    COL PESANAN
    public static final String COL_1 = "id_pesanan";
    public static final String COL_2 = "nama";
    public static final String COL_3 = "lapangan";
    public static final String COL_4 = "mulai_jam";
    public static final String COL_5 = "selesai_jam";
    public static final String COL_15 = "tanggal";
    public static final String COL_16 = "catatan";
    public static final String COL_17 = "status_bayar";
//    COL LAPANGAN
    public static final String COL_6 = "id_lap";
    public static final String COL_7 = "name";
    public static final String COL_8 = "status";
//    COL USER
    public static final String COL_9 = "id_user";
    public static final String COL_10 = "name";
    public static final String COL_11 = "point";
    public static final String COL_12 = "nohp";
    public static final String COL_13 = "username";
    public static final String COL_14 = "password";


    public DatabaseHelper(ListViewUser context) {
        super(context, DATABASE_NAME, null, 1);
    }
    public DatabaseHelper(ListViewLapangan context) {
        super(context, DATABASE_NAME, null, 1);
    }
    public DatabaseHelper(ListViewPesanan context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_USER +"(id_user INTEGER, name TEXT, point TEXT, nohp TEXT, username TEXT, password TEXT)");

        db.execSQL("create table " + TABLE_NAME_LAPANGAN +"(id_lap INTEGER, name TEXT, status TEXT)");

        db.execSQL("create table " + TABLE_NAME_PESANAN +"(id_pesanan INTEGER, nama TEXT, lapangan TEXT, mulai_jam TEXT, selesai_jam TEXT, tanggal TEXT, catatan TEXT, status_bayar TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_LAPANGAN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PESANAN);
        onCreate(db);
    }

    public void emptyData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_USER);
        db.execSQL("DELETE FROM " + TABLE_NAME_LAPANGAN);
        db.execSQL("DELETE FROM " + TABLE_NAME_PESANAN);
    }





    // ---- SQLite User
    public boolean insertUser(int id_user, String name, String point, String nohp , String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_9,id_user);
        contentValues.put(COL_10,name);
        contentValues.put(COL_11,point);
        contentValues.put(COL_12,nohp);
        contentValues.put(COL_13,username);
        contentValues.put(COL_14,password);
        long result = db.insert(TABLE_NAME_USER,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_USER,null);
        return res;
    }
    public Cursor getUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_USER+" where id_user = "+id,null);
        return res;
    }

    // ---- SQLite Lapangan
    public boolean insertLapangan(int id_lap, String name, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6,id_lap);
        contentValues.put(COL_7,name);
        contentValues.put(COL_8,status);
        long result = db.insert(TABLE_NAME_LAPANGAN,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllLapangan() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LAPANGAN,null);
        return res;
    }
    public Cursor getLapangan(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LAPANGAN+" where id_lap = "+id,null);
        return res;
    }

    // ---- SQLite Pesanan
    public boolean insertPesanan(int id_pesanan, String nama, String lapangan, String mulai_jam , String selesai_jam, String tanggal, String catatan, String status_bayar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id_pesanan);
        contentValues.put(COL_2,nama);
        contentValues.put(COL_3,lapangan);
        contentValues.put(COL_4,mulai_jam);
        contentValues.put(COL_5,selesai_jam);
        contentValues.put(COL_15,tanggal);
        contentValues.put(COL_16,catatan);
        contentValues.put(COL_17,status_bayar);
        long result = db.insert(TABLE_NAME_PESANAN,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllPesanan() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_PESANAN,null);
        return res;
    }
    public Cursor getPesanan(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_PESANAN+" where id_pesanan = "+id,null);
        return res;
    }
}

