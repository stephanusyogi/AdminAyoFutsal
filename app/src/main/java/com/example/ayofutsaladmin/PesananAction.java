package com.example.ayofutsaladmin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PesananAction extends AppCompatActivity {
    EditText nama, lapangan, mulai_jam, selesai_jam, tanggal, catatan, status_bayar;
    Button btnDelete,btnEdit;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_action);

        Intent fromList = getIntent();
        id = fromList.getIntExtra("id",0);


        nama = (EditText) findViewById(R.id.InputNama);
        lapangan = (EditText) findViewById(R.id.InputLapangan);
        mulai_jam = (EditText) findViewById(R.id.InputMulaijam);
        selesai_jam = (EditText) findViewById(R.id.InputSelesaijam);
        tanggal = (EditText) findViewById(R.id.InputTanggal);
        catatan = (EditText) findViewById(R.id.InputCatatan);
        status_bayar = (EditText) findViewById(R.id.InputStatusbayar);
        btnDelete = (Button) findViewById(R.id.btnHapus);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        loadPesanan();
        deleteAction();
        editAction();
    }
    private void loadPesanan(){
        Cursor cursor = ListViewPesanan.db.getPesanan(id);
        if(cursor.moveToFirst()){
            nama.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)));
            lapangan.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)));
            mulai_jam.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_4)));
            selesai_jam.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_5)));
            tanggal.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_15)));
            catatan.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_16)));
            status_bayar.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_17)));
        }
    }

    public void deleteAction(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pesanan pesanan = Pesanan.generateDeleteObject(id);
                new ApiConnect(PesananAction.this, pesanan).execute(ApiConnect.DELETE_ACTION_PESANAN+"");
            }
        });
    }

    public void editAction(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pesanan pesanan = Pesanan.generateUpdateObject(id,
                        nama.getText().toString(),
                        lapangan.getText().toString(),
                        mulai_jam.getText().toString(),
                        selesai_jam.getText().toString(),
                        tanggal.getText().toString(),
                        catatan.getText().toString(),
                        status_bayar.getText().toString());
                new ApiConnect(PesananAction.this,pesanan).execute(ApiConnect.UPDATE_ACTION_PESANAN+"");
            }
        });
    }

}
