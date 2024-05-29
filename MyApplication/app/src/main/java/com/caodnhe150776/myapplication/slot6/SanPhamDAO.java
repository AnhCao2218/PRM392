package com.caodnhe150776.myapplication.slot6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper helper;
    private Context context;


    public static final String TABLE_NAME="sanpham";
    public SanPhamDAO(Context context){
        this.context=context;
        helper= new Demo61SQLiteHelper(context);
        db=helper.getWritableDatabase();
    }
    public  int insertSp(SanPham p){
        ContentValues values= new ContentValues();
        values.put("maSp",p.getMaSp());
        values.put("tenSp", p.getTenSp());
        values.put("soluong",String.valueOf(p.getSoluong()));
        if(db.insert(TABLE_NAME,null,values)<0){
            return  -1;
        }
        return 1;
    }
    public int deleteSp(String maSp){
        if(db.delete(TABLE_NAME,"maSp=?",new String[]{maSp})>=0){
            return -1;
        }
        return 1;
    }
    public  int updateSp(SanPham p){
        ContentValues values= new ContentValues();
        values.put("maSp",p.getMaSp());
        values.put("tenSp", p.getTenSp());
        values.put("soluong",String.valueOf(p.getSoluong()));
        if(db.update(TABLE_NAME,values,"maSp=?", new String[]{p.getMaSp()})<0){
            return  -1;
        }
        return 1;
    }
    public List<SanPham> getAllProduct(){
        List<SanPham> ls=new ArrayList<>();
        Cursor c=db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            SanPham sanPham= new SanPham();
            sanPham.setMaSp(c.getString(0));
            sanPham.setTenSp(c.getString(1));
            sanPham.setSoluong(c.getInt(2));
            ls.add(sanPham);
            c.moveToNext();

        }
        c.close();
        return ls;
    }
    public List<String> getAllProductToString(){
        List<String> ls=new ArrayList<>();
        Cursor c=db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            SanPham sanPham= new SanPham();
            sanPham.setMaSp(c.getString(0));
            sanPham.setTenSp(c.getString(1));
            sanPham.setSoluong(c.getInt(2));
            String chuoi=sanPham.getMaSp()+"-"+ sanPham.getTenSp()+"-"+sanPham.getSoluong();
            ls.add(chuoi);
            c.moveToNext();
        }
        c.close();
        return ls;
    }

}
