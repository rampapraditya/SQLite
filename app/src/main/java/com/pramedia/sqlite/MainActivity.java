package com.pramedia.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBaca, btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBaca = findViewById(R.id.btnBaca);
        btnBaca.setOnClickListener(this);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnBaca){
            baca();
        }else if(view == btnSimpan){
            simpan("01", "Rampa", "Sistem Informasi");
            simpan("02", "Praditya", "Informatika");
        }
    }

    private void baca(){
        DBHelper db = new DBHelper(this);
        int baris = Integer.parseInt(db.cari("select count(*) as jml from mahasiswa;"));
        String [][]data = db.cari_array("select * from mahasiswa", baris, 3);
        for(int i=0; i<data.length; i++){
            Log.d("DATA", data[i][0] + "\t" + data[i][1] + "\t" + data[i][2]);
        }
    }

    private void simpan(String nim, String nama, String prodi){
        DBHelper db = new DBHelper(this);
        db.dml("insert into mahasiswa values ('" + nim + "','" + nama + "','" + prodi + "');");
    }
}