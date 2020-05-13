package com.example.ayofutsaladmin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LapanganTambah extends AppCompatActivity {
    EditText nama, status;
    Button btnTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapangan_tambah);

        nama = (EditText) findViewById(R.id.InputNamaLapangan);
        status = (EditText) findViewById(R.id.InputStatus);
        btnTambah = (Button) findViewById(R.id.btnTambah);

        addAction();
    }
    public void addAction() {
        btnTambah.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lapangan lapangan = Lapangan.generateInsertObject(
                                nama.getText().toString(),
                                status.getText().toString()
                        );
                        new ApiConnect(LapanganTambah.this, lapangan).execute(ApiConnect.INSERT_ACTION_LAPANGAN + "");
                    }
                }
        );
    }

}
