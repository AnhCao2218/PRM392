package com.caodnhe150776.myapplication.slot7;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo7MainActivity extends AppCompatActivity {
    Context context=this;
    EditText txt1;
    TextView tvResult;

    Button btnRead, btnSave;
    //----------
    EditText txtU,txtP;
    Button btnLogin, btnCancel;
    CheckBox chekSave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo71login_layout);
//        txt1=findViewById(R.id.demo7txt);
//        tvResult=findViewById(R.id.demo7tw);
//        btnRead=findViewById(R.id.btnRead);
//        btnSave=findViewById(R.id.btnsave);
//        requestPermission();
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveData(txt1.getText().toString(),context);
//
//            }
//        });
//        btnRead.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String data=readData(context);
//                tvResult.setText(data);
//            }
//        });
        txtU=findViewById(R.id.demo71Usertxt);
        txtP=findViewById(R.id.demo71Passtxt);
        btnLogin=findViewById(R.id.demo71Login);
        btnCancel=findViewById(R.id.demo71Cancel);
        chekSave=findViewById(R.id.demo71check);
        restorePass();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }
    String strU,strP;
    public void  Login(){
        strU=txtU.getText().toString();
        strP=txtP.getText().toString();
        if(strU.isEmpty()||strP.isEmpty()){
            Toast.makeText(getApplicationContext(),"U,P is not empty",Toast.LENGTH_LONG).show();
            return;
        }
        if(strU.equalsIgnoreCase("admin")&&strP.equalsIgnoreCase("admin")){
            saveUpToPreferrence(strU,strP,chekSave.isChecked());
            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG).show();
        }

    }
    public void saveDataToFile(String data){
        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/data1.txt";
        try {
            OutputStreamWriter o= new OutputStreamWriter(new FileOutputStream(path));
            o.write(data);
            o.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    public boolean requestPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED&& checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
            PackageManager.PERMISSION_GRANTED&&checkSelfPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
            ==PackageManager.PERMISSION_GRANTED){
                return true;
            }else {
                ActivityCompat.requestPermissions(Demo7MainActivity.this,new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE
                },1);
                return false;
            }
        }else {
        return  true;
        }
    }
    public String saveData(String data, Context context){
        String result="";
        String path="";
        ContextWrapper contextWrapper= new ContextWrapper(context);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD_MR1){

            path= contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DCIM)+"/data1.txt";

        }else {
            path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/data1.txt";
        }
        try {
            OutputStreamWriter o= new OutputStreamWriter(new FileOutputStream(path));
            o.write(data);
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        }

        return result;
    }
    public String  readData(Context context){
        String result="";
        String path="";
        ContextWrapper contextWrapper= new ContextWrapper(context);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD_MR1){

            path= contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DCIM)+"/data1.txt";

        }else {
            path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/data1.txt";
        }
        try{
            Scanner s= new Scanner(new File(path));
            while (s.hasNext()){
                result=s.nextLine()+"\n";
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public String readDataFromFile(){
        String data="";
        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"data1.txt";
        try{
            Scanner s= new Scanner(new File(path));
            while (s.hasNext()){
                data=s.nextLine()+"\n";
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    public void  saveUpToPreferrence(String u, String p, boolean status){
        SharedPreferences  sharedPreferences=getSharedPreferences("H_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.apply();
    }
    public List<Object> restorePass() {
        List<Object> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("H_FILE", MODE_PRIVATE);

        boolean check = sharedPreferences.getBoolean("REMEMBER", false);
        if (check) {
            String u = sharedPreferences.getString("USERNAME", ""); // Corrected key name from "USENAME" to "USERNAME"
            txtU.setText(u);
            String p = sharedPreferences.getString("PASSWORD", ""); // Corrected key name from "PASSSWORD" to "PASSWORD"
            txtP.setText(p);
            list.add(u);
            list.add(p);
            list.add(check);
        }
        chekSave.setChecked(check);
        return list;

    }
}