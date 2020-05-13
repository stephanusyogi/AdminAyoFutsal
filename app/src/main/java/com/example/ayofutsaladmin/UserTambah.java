package com.example.ayofutsaladmin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;


public class UserTambah extends AppCompatActivity {
    EditText nama, poin, nohp, username, password;
    Button btnTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tambah);

        nama = (EditText) findViewById(R.id.InputNama);
        poin = (EditText) findViewById(R.id.InputPoin);
        nohp = (EditText) findViewById(R.id.InputHp);
        username = (EditText) findViewById(R.id.InputUsername);
        password = (EditText) findViewById(R.id.InputPassword);
        btnTambah = (Button) findViewById(R.id.btnTambah);

        addAction();
    }
    public void addAction() {
        btnTambah.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = User.generateInsertObject(
                                nama.getText().toString(),
                                poin.getText().toString(),
                                nohp.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString()
                        );
                        new ApiConnect(UserTambah.this, user).execute(ApiConnect.INSERT_ACTION_USER + "");
                    }
                }
        );
    }
}
