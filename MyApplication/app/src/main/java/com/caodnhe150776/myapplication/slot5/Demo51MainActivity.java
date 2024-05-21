package com.caodnhe150776.myapplication.slot5;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Demo51MainActivity extends AppCompatActivity {
    ListView listView;
    Demo51Adapter adapter;
    List<Demo51Product> list= new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo51_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.demo51_listView);
        //Demo51SQLiteHelper helper= new Demo51SQLiteHelper(this);
        //SQLiteDatabase database=helper.getReadableDatabase();
        Demo51ProductDAO dao= new Demo51ProductDAO(this);
        //Demo51Product p = new Demo51Product("1","San Pham 1", 123, 1);
        //int kq= dao.insertProduct(p);
        list=dao.getAll();
        adapter=new Demo51Adapter(list, this);
        listView.setAdapter(adapter);
        //Toast.makeText(this,kq,Toast.LENGTH_LONG).show();
    }
}