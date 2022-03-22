package com.pramedia.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "latihan";

    String create_table_mhs = "CREATE TABLE \"mahasiswa\" (\n" +
            "\t\"nim\"\tTEXT,\n" +
            "\t\"nama\"\tTEXT,\n" +
            "\t\"prodi\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"nim\")\n" +
            ");";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        db.execSQL(create_table_mhs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void dml(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);
        db.close();
    }

    public String cari(String query){
        String hasil = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            hasil = cursor.getString(0);
        }
        db.close();
        return hasil;
    }

    public String[] cari_array1D(String query, int baris){
        String data[] = new String[baris];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        int counter = 0;
        while (cursor.moveToNext()){
            data[counter] =  cursor.getString(0);
            counter++;
        }
        db.close();
        return data;
    }

    public String[][] cari_array(String query, int baris, int kolom){
        String data[][] = new String[baris][kolom];
        SQLiteDatabase db = this.getReadableDatabase();

        int counter_baris = 0;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            for (int i=0; i<kolom; i++){
                data[counter_baris][i] = cursor.getString(i);
            }
            counter_baris++;
        }
        db.close();
        return data;
    }
}
