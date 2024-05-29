package com.caodnhe150776.myapplication.slot6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Demo61SQLiteHelper extends SQLiteOpenHelper {



    public static final String SQL_CREATE_TABLE_SANPHAM="CREATE TABLE sanpham(\n" +
            "maSp text PRIMARY Key,\n" +
            "  tenSp text,\n" +
            "soluong text\n" +
            ");";
    public Demo61SQLiteHelper(Context context){
        super(context,"QLSP",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_TABLE_SANPHAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS sanpham;");
    }
}
