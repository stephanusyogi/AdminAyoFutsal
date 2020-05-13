package com.example.ayofutsaladmin;

import org.json.JSONException;
import org.json.JSONObject;

public class Lapangan {
    private int id_lap;
    private String name;
    private String status;
    private int type;
    public static final int INSERT_TYPE=2;
    public static final int UPDATE_TYPE=3;
    public static final int DELETE_TYPE=4;

    public static Lapangan generateInsertObject(String name, String status)
    {
        return new Lapangan (-1, name, status, Lapangan.INSERT_TYPE);
    }

    public static Lapangan generateUpdateObject(int id_lap, String name, String status)
    {
        return new Lapangan (id_lap, name, status, Lapangan.UPDATE_TYPE);
    }

    public static Lapangan generateDeleteObject(int id_lap){
        return new Lapangan (id_lap, null, null, Lapangan.DELETE_TYPE);
    }

    public JSONObject getJsonProduct(){
        JSONObject obj = new JSONObject();
        try {
            switch (type) {
                case Lapangan.INSERT_TYPE:
                    obj.put("name", this.name);
                    obj.put("status", this.status);
                    break;
                case Lapangan.UPDATE_TYPE:
                    obj.put("id_lap", this.id_lap);
                    obj.put("name", this.name);
                    obj.put("status", this.status);
                    break;
                case Lapangan.DELETE_TYPE:
                    obj.put("id_lap",this.id_lap);
                    break;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }

    public String getJsonString() {
        return getJsonProduct().toString();
    }

    // Untuk sqlite (tanpa type)
    public Lapangan(int id_lap, String name, String status){
        this.id_lap = id_lap;
        this.name = name;
        this.status = status;

    }

    // Untuk generate object dan server (dengan type)
    public Lapangan(int id_lap, String name, String status, int type) {
        this.id_lap = id_lap;
        this.name = name;
        this.status = status;
        this.type = type;
    }

    public int getId_lapangan() {
        return id_lap;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
    public int getType() {
        return type;
    }
}
