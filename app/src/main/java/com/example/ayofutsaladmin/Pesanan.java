package com.example.ayofutsaladmin;

import org.json.JSONException;
import org.json.JSONObject;

public class Pesanan {
    private int id_pesanan;
    private String nama;
    private String lapangan;
    private String mulai_jam;
    private String selesai_jam;
    private String tanggal;
    private String catatan;
    private String status_bayar;
    private int type;
    public static final int INSERT_TYPE=2;
    public static final int UPDATE_TYPE=3;
    public static final int DELETE_TYPE=4;

    public static Pesanan generateInsertObject(String nama, String lapangan, String mulai_jam, String selesai_jam, String tanggal, String catatan, String status_bayar)
    {
        return new Pesanan (-1, nama, lapangan, mulai_jam, selesai_jam, tanggal, catatan, status_bayar, Pesanan.INSERT_TYPE);
    }

    public static Pesanan generateUpdateObject(int id_pesanan, String nama, String lapangan, String mulai_jam, String selesai_jam, String tanggal, String catatan, String status_bayar)
    {
        return new Pesanan (id_pesanan, nama, lapangan, mulai_jam, selesai_jam, tanggal, catatan, status_bayar, Pesanan.UPDATE_TYPE);
    }

    public static Pesanan generateDeleteObject(int id_pesanan){
        return new Pesanan (id_pesanan, null, null, null,null, null, null, null, Pesanan.DELETE_TYPE);
    }

    public JSONObject getJsonProduct(){
        JSONObject obj = new JSONObject();
        try {
            switch (type) {
                case Pesanan.INSERT_TYPE:
                    obj.put("nama", this.nama);
                    obj.put("lapangan", this.lapangan);
                    obj.put("mulai_jam", this.mulai_jam);
                    obj.put("selesai_jam", this.selesai_jam);
                    obj.put("tanggal", this.tanggal);
                    obj.put("catatan", this.catatan);
                    obj.put("status_bayar", this.status_bayar);
                    break;
                case Pesanan.UPDATE_TYPE:
                    obj.put("id_pesanan", this.id_pesanan);
                    obj.put("nama", this.nama);
                    obj.put("lapangan", this.lapangan);
                    obj.put("mulai_jam", this.mulai_jam);
                    obj.put("selesai_jam", this.selesai_jam);
                    obj.put("tanggal", this.tanggal);
                    obj.put("catatan", this.catatan);
                    obj.put("status_bayar", this.status_bayar);
                    break;
                case Pesanan.DELETE_TYPE:
                    obj.put("id_pesanan", this.id_pesanan);
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
    public Pesanan(int id_pesanan, String nama, String lapangan, String mulai_jam, String selesai_jam, String tanggal, String catatan, String status_bayar){
        this.id_pesanan = id_pesanan;
        this.nama = nama;
        this.lapangan = lapangan;
        this.mulai_jam = mulai_jam;
        this.selesai_jam = selesai_jam;
        this.tanggal = tanggal;
        this.catatan = catatan;
        this.status_bayar = status_bayar;
    }

    // Untuk generate object dan server (dengan type)
    public Pesanan(int id_pesanan, String nama, String lapangan, String mulai_jam, String selesai_jam, String tanggal, String catatan, String status_bayar,int type) {
        this.id_pesanan = id_pesanan;
        this.nama = nama;
        this.lapangan = lapangan;
        this.mulai_jam = mulai_jam;
        this.selesai_jam = selesai_jam;
        this.tanggal = tanggal;
        this.catatan = catatan;
        this.status_bayar = status_bayar;
        this.type = type;
    }

    public int getId_pesanan() {
        return id_pesanan;
    }

    public String getNama() {
        return nama;
    }

    public String getLapangan() {
        return lapangan;
    }

    public String getMulai_jam() {
        return mulai_jam;
    }
    public String getSelesai_jam() {
        return selesai_jam;
    }
    public String getTanggal() {
        return tanggal;
    }
    public String getCatatan() {
        return catatan;
    }
    public String getStatus_bayar() {
        return status_bayar;
    }
    public int getType() {
        return type;
    }
}
