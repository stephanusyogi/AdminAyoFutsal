package com.example.ayofutsaladmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_ayofutsal.db";
    public static final String TABLE_NAME_USER = "tbl_user";
    public static final String COL_1 = "id_pesanan";
    public static final String COL_2 = "nama";
    public static final String COL_3 = "lapangan";
    public static final String COL_4 = "mulai_jam";
    public static final String COL_5 = "selesai_jam";
    public static final String COL_6 = "tanggal";
    public static final String COL_7 = "catatan";
    public static final String COL_8 = "status_bayar";
    public static final String COL_9 = "id_user";
    public static final String COL_10 = "name";
    public static final String COL_11 = "point";
    public static final String COL_12 = "nohp";


    public DatabaseHelper(ListViewUser context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        db.execSQL("create table " + TABLE_NAME_USER +"(id_user INTEGER, name TEXT, point TEXT, nohp TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_USER);
        onCreate(db);
    }

    public void emptyData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_USER);
    }





    // ---- SQLite Lapangan
    public boolean insertUser(int id_user, String name, String point, String nohp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_9,id_user);
        contentValues.put(COL_10,name);
        contentValues.put(COL_11,point);
        contentValues.put(COL_12,nohp);
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
}

