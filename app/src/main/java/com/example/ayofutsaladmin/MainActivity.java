package com.example.ayofutsaladmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jadwal = (Button)findViewById(R.id.btn_pesanan);
        jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jadwal = new Intent(MainActivity.this, ListViewPesanan.class);
                startActivity(jadwal);
            }
        });
        Button lapangan = (Button)findViewById(R.id.btn_Lapangan);
        lapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lapangan = new Intent(MainActivity.this, ListViewLapangan.class);
                startActivity(lapangan);
            }
        });


        Button user = (Button)findViewById(R.id.btn_lihatuser);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent user = new Intent(MainActivity.this, ListViewUser.class);
                startActivity(user);
            }
        });
    }


}
