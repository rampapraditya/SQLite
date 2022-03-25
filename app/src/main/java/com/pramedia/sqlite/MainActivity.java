package com.pramedia.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBaca, btnSimpan;
    private EditText nim, nama, prodi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBaca = findViewById(R.id.btnBaca);
        btnBaca.setOnClickListener(this);

        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        prodi = findViewById(R.id.prodi);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnBaca){
            baca();

        }else if(view == btnSimpan){
            String a = nim.getText().toString();
            String b = nama.getText().toString();
            String c = prodi.getText().toString();

            simpan(a, b, c);
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
        try {
            DBHelper db = new DBHelper(this);

            int cek = Integer.parseInt(db.cari("select count(*) as jml from mahasiswa where nim = '" + nim + "';"));
            if( cek > 0){
                Toast.makeText(this, "Gunakan NIM lain", Toast.LENGTH_SHORT).show();
            }else{
                db.dml("insert into mahasiswa values ('" + nim + "','" + nama + "','" + prodi + "');");
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}