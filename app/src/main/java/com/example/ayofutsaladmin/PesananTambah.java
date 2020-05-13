package com.example.ayofutsaladmin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PesananTambah extends AppCompatActivity {
    EditText nama, lapangan, mulai_jam, selesai_jam, tanggal, catatan, status_bayar;
    Button btnTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_tambah);

        nama = (EditText) findViewById(R.id.InputNama);
        lapangan = (EditText) findViewById(R.id.InputLapangan);
        mulai_jam = (EditText) findViewById(R.id.InputMulaijam);
        selesai_jam = (EditText) findViewById(R.id.InputSelesaijam);
        tanggal = (EditText) findViewById(R.id.InputTanggal);
        catatan = (EditText) findViewById(R.id.InputCatatan);
        status_bayar = (EditText) findViewById(R.id.InputStatusbayar);
        btnTambah = (Button) findViewById(R.id.btnTambah);

        addAction();
    }
    public void addAction() {
        btnTambah.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Pesanan pesanan = Pesanan.generateInsertObject(
                                nama.getText().toString(),
                                lapangan.getText().toString(),
                                mulai_jam.getText().toString(),
                                selesai_jam.getText().toString(),
                                tanggal.getText().toString(),
                                catatan.getText().toString(),
                                status_bayar.getText().toString()
                        );
                        new ApiConnect(PesananTambah.this, pesanan).execute(ApiConnect.INSERT_ACTION_PESANAN + "");
                    }
                }
        );
    }

}
