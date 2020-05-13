package com.example.ayofutsaladmin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserAction extends AppCompatActivity {
    EditText nama, poin, nohp, username, password;
    Button btnDelete,btnEdit;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_action);

        Intent fromList = getIntent();
        id = fromList.getIntExtra("id",0);


        nama = (EditText) findViewById(R.id.InputNama);
        poin = (EditText) findViewById(R.id.InputPoin);
        nohp = (EditText) findViewById(R.id.InputHp);
        username = (EditText) findViewById(R.id.InputUsername);
        password = (EditText) findViewById(R.id.InputPassword);
        btnDelete = (Button) findViewById(R.id.btnHapus);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        loadUser();
        deleteAction();
        editAction();
    }
    private void loadUser(){
        Cursor cursor = ListViewUser.db.getUser(id);
        if(cursor.moveToFirst()){
            nama.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_10)));
            poin.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_11)));
            nohp.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_12)));
            username.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_13)));
            password.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_14)));
        }
    }

    public void deleteAction(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = User.generateDeleteObject(id);
                new ApiConnect(UserAction.this, user).execute(ApiConnect.DELETE_ACTION_USER+"");
            }
        });
    }

    public void editAction(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = User.generateUpdateObject(id,
                        nama.getText().toString(),
                        poin.getText().toString(),
                        nohp.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString());
                new ApiConnect(UserAction.this,user).execute(ApiConnect.UPDATE_ACTION_USER+"");
            }
        });
    }
}
