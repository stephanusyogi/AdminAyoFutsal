package com.example.ayofutsaladmin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewPesanan extends AppCompatActivity implements PesananAdapter.customButtonListener {
    public static DatabaseHelper db;
    private ListView listViewPesanan;
    private List<Pesanan> pesanans;
    private PesananAdapter pesananAdapter;
    private Button buttonSyncPesanan;
    private Button buttonTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_pesanan);

        db = new DatabaseHelper(this);
        pesanans = new ArrayList<>();
        buttonSyncPesanan = (Button) findViewById(R.id.buttonSyncPesanan);
        listViewPesanan = (ListView) findViewById(R.id.listViewPesanan);
        buttonTambah = (Button) findViewById(R.id.btnPageTambah);
        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toTambah = new Intent(ListViewPesanan.this, PesananTambah.class);
                startActivity(toTambah);
            }
        });

        loadPesanans();
        sync();
    }
    private void loadPesanans(){
        pesanans.clear();
        Cursor cursor = db.getAllPesanan();
        if (cursor.moveToFirst()){
            do {
                Pesanan pesanan = new Pesanan(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_4)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_5)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_15)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_16)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_17))
                );
                pesanans.add(pesanan);
            } while (cursor.moveToNext());
        }

        pesananAdapter= new PesananAdapter(this, R.layout.pesanan, pesanans);
        pesananAdapter.setCustomButtonListener(ListViewPesanan.this);
        listViewPesanan.setAdapter(pesananAdapter);
    }
    public void sync(){
        buttonSyncPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiConnect(ListViewPesanan.this).execute(ApiConnect.SYNC_ACTION_PESANAN+"");
                loadPesanans();
                finish();
                startActivity(getIntent());
            }
        });
    }
    @Override
    public void onButtonClickListener(int position, int id) {
        Intent toAction = new Intent(ListViewPesanan.this, PesananAction.class);
        toAction.putExtra("id",id);
        startActivity(toAction);
    }

}
