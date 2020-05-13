package com.example.ayofutsaladmin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LapanganAction extends AppCompatActivity {
    EditText nama, status;
    Button btnDelete,btnEdit;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapangan_action);

        Intent fromList = getIntent();
        id = fromList.getIntExtra("id",0);


        nama = (EditText) findViewById(R.id.InputNamaLapangan);
        status = (EditText) findViewById(R.id.InputStatus);
        btnDelete = (Button) findViewById(R.id.btnHapus);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        loadLapangan();
        deleteAction();
        editAction();
    }
    private void loadLapangan(){
        Cursor cursor = ListViewLapangan.db.getLapangan(id);
        if(cursor.moveToFirst()){
            nama.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_7)));
            status.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_8)));
        }
    }

    public void deleteAction(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lapangan lapangan = Lapangan.generateDeleteObject(id);
                new ApiConnect(LapanganAction.this, lapangan).execute(ApiConnect.DELETE_ACTION_LAPANGAN+"");
            }
        });
    }

    public void editAction(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lapangan lapangan = Lapangan.generateUpdateObject(id,
                        nama.getText().toString(),
                        status.getText().toString());
                new ApiConnect(LapanganAction.this,lapangan).execute(ApiConnect.UPDATE_ACTION_LAPANGAN+"");
            }
        });
    }
}
