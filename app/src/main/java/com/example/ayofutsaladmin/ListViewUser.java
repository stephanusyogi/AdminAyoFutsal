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

public class ListViewUser extends AppCompatActivity implements UserAdapter.customButtonListener {
    public static DatabaseHelper db;
    private ListView listViewUser;
    private List<User> users;
    private UserAdapter userAdapter;
    private Button buttonSyncUser;
    private Button buttonTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_user);

        db = new DatabaseHelper(this);
        users = new ArrayList<>();
        buttonSyncUser = (Button) findViewById(R.id.buttonSyncUser);
        listViewUser = (ListView) findViewById(R.id.listViewUser);
        buttonTambah = (Button) findViewById(R.id.btnPageTambah);
        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toTambah = new Intent(ListViewUser.this, UserTambah.class);
                startActivity(toTambah);
            }
        });

        loadUsers();
        sync();
    }
    private void loadUsers(){
        users.clear();
        Cursor cursor = db.getAllUser();
        if (cursor.moveToFirst()){
            do {
                User user = new User(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_9)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_10)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_11)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_12)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_13)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_14))
                );
                users.add(user);
            } while (cursor.moveToNext());
        }

        userAdapter= new UserAdapter(this, R.layout.user, users);
        userAdapter.setCustomButtonListener(ListViewUser.this);
        listViewUser.setAdapter(userAdapter);
    }
    public void sync(){
        buttonSyncUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiConnect(ListViewUser.this).execute(ApiConnect.SYNC_ACTION_USER+"");
                loadUsers();
                finish();
                startActivity(getIntent());
            }
        });
    }
    @Override
    public void onButtonClickListener(int position, int id) {
        Intent toAction = new Intent(ListViewUser.this, UserAction.class);
        toAction.putExtra("id",id);
        startActivity(toAction);
    }

}