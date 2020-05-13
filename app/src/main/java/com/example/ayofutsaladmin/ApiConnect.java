package com.example.ayofutsaladmin;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiConnect extends AsyncTask<String,String,String> {

    private int action;
    private ProgressDialog pd;
    private Context context;
    private User user;
    private Lapangan lapangan;
    private Pesanan pesanan;

//    JANGAN LUPA INI DIISI PER FUNGSINYA
    public static final int SYNC_ACTION_USER = 1;
    public static final int INSERT_ACTION_USER = 2;
    public static final int UPDATE_ACTION_USER = 3;
    public static final int DELETE_ACTION_USER = 4;
    public static final int SYNC_ACTION_LAPANGAN = 5;
    public static final int INSERT_ACTION_LAPANGAN = 6;
    public static final int UPDATE_ACTION_LAPANGAN = 7;
    public static final int DELETE_ACTION_LAPANGAN = 8;
    public static final int SYNC_ACTION_PESANAN = 9;
    public static final int INSERT_ACTION_PESANAN = 10;
    public static final int UPDATE_ACTION_PESANAN = 11;
    public static final int DELETE_ACTION_PESANAN = 12;


    //memunculkan progress dialog menandakan proses sedang berjalan
    protected void onPreExecute(){
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

//  DO IN BACKGROUND NYA JUGA DIISI PER FUNGSINYA
    @Override
    protected String doInBackground(String... params) {
        this.action = Integer.parseInt(params[0]);
        switch (action){
//            USER
            case ApiConnect.SYNC_ACTION_USER :
                return syncUSERAPI();
            case ApiConnect.UPDATE_ACTION_USER :
                return updateUserAPI();
            case ApiConnect.DELETE_ACTION_USER :
                return deleteUserAPI();
            case ApiConnect.INSERT_ACTION_USER:
                return insertUserAPI();
//                LAPANGAN
            case ApiConnect.SYNC_ACTION_LAPANGAN :
                return syncLAPANGANAPI();
            case ApiConnect.UPDATE_ACTION_LAPANGAN :
                return updateLapanganAPI();
            case ApiConnect.DELETE_ACTION_LAPANGAN :
                return deleteLapanganAPI();
            case ApiConnect.INSERT_ACTION_LAPANGAN:
                return insertLapanganAPI();
//                PESANAN
            case ApiConnect.SYNC_ACTION_PESANAN :
                return syncPESANANAPI();
            case ApiConnect.UPDATE_ACTION_PESANAN :
                return updatePesananAPI();
            case ApiConnect.DELETE_ACTION_PESANAN :
                return deletePesananAPI();
            case ApiConnect.INSERT_ACTION_PESANAN:
                return insertPesananAPI();
            default:
                return "Something is went wrong";
        }
    }
    //menandakan proses selesai
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (pd.isShowing()){
            pd.dismiss();
        }
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }

//    INI BUAT JSON JSON ANNYA
//    JSON DECODER USER
    private String JSONDecoderUserSync(String in){
        try {
            JSONObject reader = new JSONObject(in);
            JSONArray records = reader.getJSONArray("records");
            String result = "";
            ListViewUser.db.emptyData();
            for(int i=0;i<records.length();i++){
                JSONObject items = records.getJSONObject(i);
                int id_user = items.getInt("id_user");
                String name = items.getString("name");
                String point = items.getString("point");
                String nohp = items.getString("nohp");
                String username = items.getString("username");
                String password = items.getString("password");
                boolean isSynced = ListViewUser.db.insertUser(id_user, name, point, nohp, username, password);
                if(isSynced == true)
                    result = "Data Synced";
                else
                    result = "Data Not Synced";
            }
            Log.d("result",result);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            int d = Log.d("result", "NULL");
            return null;
        }
    }
//    JSONDECODER LAPANGAN
    private String JSONDecoderLapanganSync(String in){
        try {
            JSONObject reader = new JSONObject(in);
            JSONArray records = reader.getJSONArray("records");
            String result = "";
            ListViewLapangan.db.emptyData();
            for(int i=0;i<records.length();i++){
                JSONObject items = records.getJSONObject(i);
                int id_lap = items.getInt("id_lap");
                String name = items.getString("name");
                String status = items.getString("status");
                boolean isSynced = ListViewLapangan.db.insertLapangan(id_lap, name, status);
                if(isSynced == true)
                    result = "Data Synced";
                else
                    result = "Data Not Synced";
            }
            Log.d("result",result);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            int d = Log.d("result", "NULL");
            return null;
        }
    }
    //    JSONDECODER PESANAN
    private String JSONDecoderPesananSync(String in){
        try {
            JSONObject reader = new JSONObject(in);
            JSONArray records = reader.getJSONArray("records");
            String result = "";
            ListViewPesanan.db.emptyData();
            for(int i=0;i<records.length();i++){
                JSONObject items = records.getJSONObject(i);
                int id_pesanan = items.getInt("id_pesanan");
                String nama = items.getString("nama");
                String lapangan = items.getString("lapangan");
                String mulai_jam = items.getString("mulai_jam");
                String selesai_jam = items.getString("selesai_jam");
                String tanggal = items.getString("tanggal");
                String catatan = items.getString("catatan");
                String status_bayar = items.getString("status_bayar");
                boolean isSynced = ListViewPesanan.db.insertPesanan(id_pesanan, nama, lapangan, mulai_jam, selesai_jam, tanggal, catatan, status_bayar);
                if(isSynced == true)
                    result = "Data Synced";
                else
                    result = "Data Not Synced";
            }
            Log.d("result",result);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            int d = Log.d("result", "NULL");
            return null;
        }
    }
//    CRUD USER
//    READ
    private String syncUSERAPI(){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_user/read.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);
            }
            return JSONDecoderUserSync(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
//    INSERT
    private String insertUserAPI(){
        HttpURLConnection connection = null;
        if (user == null){
            return "user is null";
        }
        try{
            URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_user/create.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = user.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
//  UPDATE
    private String updateUserAPI(){
        HttpURLConnection connection = null;
        if (user == null){
            return "user is null";
        }
        try{
            URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_user/update.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = user.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
//    DELETE
    private String deleteUserAPI(){
        HttpURLConnection connection = null;
        if (user == null){
            return "gedung is null";
        }
        try{
            URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_user/delete.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = user.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

//    CRUD LAPANGAN
    //    READ
    private String syncLAPANGANAPI(){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_lapangan/read.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);
            }
            return JSONDecoderLapanganSync(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
        //    INSERT
        private String insertLapanganAPI(){
            HttpURLConnection connection = null;
            if (lapangan == null){
                return "user is null";
            }
            try{
                URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_lapangan/create.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/json; utf-8");
                connection.setDoOutput(true);
                String jsonString = lapangan.getJsonString();
                OutputStream os = connection.getOutputStream();
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                Log.d("response",response.toString());
                return response.toString();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        //  UPDATE
        private String updateLapanganAPI(){
            HttpURLConnection connection = null;
            if (lapangan == null){
                return "user is null";
            }
            try{
                URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_lapangan/update.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setDoOutput(true);
                String jsonString = lapangan.getJsonString();
                OutputStream os = connection.getOutputStream();
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                Log.d("response",response.toString());
                return response.toString();

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        //    DELETE
        private String deleteLapanganAPI(){
            HttpURLConnection connection = null;
            if (lapangan == null){
                return "gedung is null";
            }
            try{
                URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_lapangan/delete.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setDoOutput(true);
                String jsonString = lapangan.getJsonString();
                OutputStream os = connection.getOutputStream();
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                Log.d("response",response.toString());
                return response.toString();

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

    //    CRUD PESANAN
    //    READ
    private String syncPESANANAPI(){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_pemesanan/read.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);
            }
            return JSONDecoderPesananSync(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //    INSERT
    private String insertPesananAPI(){
        HttpURLConnection connection = null;
        if (pesanan == null){
            return "user is null";
        }
        try{
            URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_pemesanan/create.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = pesanan.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    //  UPDATE
    private String updatePesananAPI(){
        HttpURLConnection connection = null;
        if (pesanan == null){
            return "user is null";
        }
        try{
            URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_pemesanan/update.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = pesanan.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    //    DELETE
    private String deletePesananAPI(){
        HttpURLConnection connection = null;
        if (pesanan == null){
            return "gedung is null";
        }
        try{
            URL url = new URL("http://192.168.43.16/ayofutsalapi/API/tbl_pemesanan/delete.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = pesanan.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


//    TERAKHIR JANGAN LUPA SEMUA HARUS DIDEKLARASI
    public ApiConnect(Context context)
{
    this.context = context;
}

    public ApiConnect(Context context,User user){
        this.context = context;
        this.user = user;
    }

    public ApiConnect(Context context,Lapangan lapangan){
        this.context = context;
        this.lapangan = lapangan;
    }
    public ApiConnect(Context context,Pesanan pesanan){
        this.context = context;
        this.pesanan = pesanan;
    }

}

