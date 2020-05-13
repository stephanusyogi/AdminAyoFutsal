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

public class ListViewLapangan extends AppCompatActivity implements LapanganAdapter.customButtonListener {
    public static DatabaseHelper db;
    private ListView listViewLapangan;
    private List<Lapangan> lapangans;
    private LapanganAdapter lapanganAdapter;
    private Button buttonSyncLapangan;
    private Button buttonTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_lapangan);

        db = new DatabaseHelper(this);
        lapangans = new ArrayList<Lapangan>();
        buttonSyncLapangan = (Button) findViewById(R.id.buttonSyncLapangan);
        listViewLapangan = (ListView) findViewById(R.id.listViewLapangan);
        buttonTambah = (Button) findViewById(R.id.btnPageTambah);
        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toTambah = new Intent(ListViewLapangan.this, LapanganTambah.class);
                startActivity(toTambah);
            }
        });

        loadLapangans();
        sync();
    }
    private void loadLapangans(){
        lapangans.clear();
        Cursor cursor = db.getAllLapangan();
        if (cursor.moveToFirst()){
            do {
                Lapangan lapangan = new Lapangan(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_6)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_7)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_8))
                );
                lapangans.add(lapangan);
            } while (cursor.moveToNext());
        }

        lapanganAdapter= new LapanganAdapter(this, R.layout.lapangan, lapangans);
        lapanganAdapter.setCustomButtonListener(ListViewLapangan.this);
        listViewLapangan.setAdapter(lapanganAdapter);
    }
    public void sync(){
        buttonSyncLapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiConnect(ListViewLapangan.this).execute(ApiConnect.SYNC_ACTION_LAPANGAN+"");
                loadLapangans();
                finish();
                startActivity(getIntent());
            }
        });
    }
    @Override
    public void onButtonClickListener(int position, int id) {
        Intent toAction = new Intent(ListViewLapangan.this, LapanganAction.class);
        toAction.putExtra("id",id);
        startActivity(toAction);
    }

}
