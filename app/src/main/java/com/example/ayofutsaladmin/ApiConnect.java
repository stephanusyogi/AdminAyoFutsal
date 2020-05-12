package com.example.ayofutsaladmin;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

//    JANGAN LUPA INI DIISI PER FUNGSINYA
    public static final int SYNC_ACTION_USER = 1;
//  DO IN BACKGROUND NYA JUGA DIISI PER FUNGSINYA
    @Override
    protected String doInBackground(String... params) {
        this.action = Integer.parseInt(params[0]);
        switch (action){
            case ApiConnect.SYNC_ACTION_USER :
                return syncUSERAPI();
            default:
                return "Something is went wrong";
        }
    }


//    INI BUAT JSON JSON ANNYA
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
                boolean isSynced = ListViewUser.db.insertUser(id_user, name, point, nohp);
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
//    INI UNTUK FUNGSI SYNCRONIZE
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

//    TERAKHIR JANGAN LUPA SEMUA HARUS DIDEKLARASI
    public ApiConnect(Context context)
    {
        this.context = context;
    }

    public ApiConnect(Context context,User user){
        this.context = context;
        this.user = user;
    }

}

