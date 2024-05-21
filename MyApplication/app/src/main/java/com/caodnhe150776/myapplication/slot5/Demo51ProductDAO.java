package com.caodnhe150776.myapplication.slot5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Demo51ProductDAO {
    private Demo51SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;
    public Demo51ProductDAO(Context context){
        this.context=context;
        dbHelper= new Demo51SQLiteHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public int insertProduct(Demo51Product product){
        ContentValues values= new ContentValues();
        values.put("id",product.getId());
        values.put("name",product.getName());
        values.put("price",product.getPrice());

        if(db.insert("Product",null,values)<0){
            return -1;
        }
        return  1;
    }
    public List<Demo51Product> getAll(){
        List<Demo51Product> list= new ArrayList<>();
        Cursor c=db.query("Product",null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Demo51Product product= new Demo51Product();
            product.setId(c.getString(0));
            product.setName(c.getString(1));
            product.setPrice(c.getDouble(2));
            list.add(product);
            c.moveToNext();
        }
        c.close();
        return list;
    }
}
