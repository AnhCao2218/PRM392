package com.caodnhe150776.myapplication.slot6;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class Demo6MainActivity extends AppCompatActivity {
    EditText txtMa, txtTen, txtSL;
    Button btnLoad, btnThem, btnSua, btnXoa;
    ListView lv;
    SanPhamDAO sanPhamDAO;
    ArrayAdapter<String> adapter;
    List<String> ds= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo6_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtMa=findViewById(R.id.demo6TxtMaSP);
        txtTen=findViewById(R.id.demo6TxtTenSP);
        txtSL=findViewById(R.id.demo6TxtSLSP);
        btnLoad=findViewById(R.id.demo6btnDisplay);
        btnThem=findViewById(R.id.demo6btnAdd);
        btnSua=findViewById(R.id.demo6btnUpdate);
        btnXoa=findViewById(R.id.demo6btnDelete);
        lv=findViewById(R.id.demo61lv);
        sanPhamDAO = new SanPhamDAO(this);
        ds.clear();
        ds=sanPhamDAO.getAllProductToString();
        adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,ds);
        lv.setAdapter(adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham s= new SanPham();
                s.setMaSp(txtMa.getText().toString());
                s.setTenSp(txtTen.getText().toString());
                s.setSoluong(Integer.parseInt(txtSL.getText().toString()));
                int kq=sanPhamDAO.insertSp(s);
                if(kq==-1){
                    Toast.makeText(getApplicationContext(),
                            "Insert that bai",Toast.LENGTH_LONG).show();
                }
                if(kq==1){
                    Toast.makeText(getApplicationContext(),
                            "Insert thanh cong",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kq=sanPhamDAO.deleteSp(txtMa.getText().toString());
                if(kq==-1){
                    Toast.makeText(getApplicationContext(),
                            "Xoa thanh cong",Toast.LENGTH_LONG).show();
                }
                if(kq==1){
                    Toast.makeText(getApplicationContext(),
                            "Xoa thanh cong",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham s= new SanPham();
                s.setMaSp(txtMa.getText().toString());
                s.setTenSp(txtTen.getText().toString());
                s.setSoluong(Integer.parseInt(txtSL.getText().toString()));
                int kq=sanPhamDAO.updateSp(s);
                if(kq==-1){
                    Toast.makeText(getApplicationContext(),
                            "Sua that bai",Toast.LENGTH_LONG).show();
                }
                if(kq==1){
                    Toast.makeText(getApplicationContext(),
                            "Sua thanh cong",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.clear();
                ds=sanPhamDAO.getAllProductToString();
                adapter= new ArrayAdapter<>(Demo6MainActivity.this, android.R.layout.simple_list_item_1,ds);
                lv.setAdapter(adapter);

            }
        });
    }
}