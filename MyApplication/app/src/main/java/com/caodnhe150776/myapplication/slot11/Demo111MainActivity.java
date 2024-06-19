package com.caodnhe150776.myapplication.slot11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demo111MainActivity extends AppCompatActivity {

    EditText txt0,txt1,txt2,txt3;
    TextView tv1;
    Button btnInsert,btnUpdate, btnDelete, btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo111_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt0=findViewById(R.id.demo121txt0);
        txt1=findViewById(R.id.demo111txt1);
        txt2=findViewById(R.id.demo111txt2);
        txt3=findViewById(R.id.demo111txt3);
        tv1=findViewById(R.id.demo111tvResult);
        btnInsert=findViewById(R.id.demo111btnInsert);
        btnUpdate=findViewById(R.id.demo121Update);
        btnDelete=findViewById(R.id.demo121Delete);
        btnSelect=findViewById(R.id.demo121btnSelect);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectData();
            }
        });
    }
    private List<PrdSelect> ls;
    String strResult="";

    private void selectData(){

        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://192.168.88.156:8080/202406/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        InterfaceSelect interfaceSelect= retrofit.create(InterfaceSelect.class);
        Call<SvrReponseSelect> call = interfaceSelect.getPrd();
        call.enqueue(new Callback<SvrReponseSelect>() {
            @Override
            public void onResponse(Call<SvrReponseSelect> call, Response<SvrReponseSelect> response) {
                SvrReponseSelect svrReponseSelect= response.body();
                //tv1.setText(svrReponseSelect.getMessage());
                ls= new ArrayList<>(Arrays.asList(svrReponseSelect.getProducts()));
                for (PrdSelect p:ls){
                    strResult += "Name: "+p.getName()+"; Price: "+p.getPrice()+"; Des: "+p.getDescription()+"\n";
                }
                tv1.setText(strResult);
            }

            @Override
            public void onFailure(Call<SvrReponseSelect> call, Throwable throwable) {
                tv1.setText(throwable.getMessage());
            }
        });
    }
    private void updateData(){
        PrdUpd prdUpd= new PrdUpd();
        prdUpd.setPid(txt0.getText().toString());
        prdUpd.setName(txt1.getText().toString());
        prdUpd.setPrice(txt2.getText().toString());
        prdUpd.setDescription(txt3.getText().toString());
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://192.168.88.156:8080/202406/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        InterfaceUpd interfaceUpd= retrofit.create(InterfaceUpd.class);
        Call<SvrReponseUpd> call = interfaceUpd.UpdateExe(prdUpd.getPid(),prdUpd.getName(), prdUpd.getPrice(), prdUpd.getDescription());
        call.enqueue(new Callback<SvrReponseUpd>() {
            @Override
            public void onResponse(Call<SvrReponseUpd> call, Response<SvrReponseUpd> response) {
                SvrReponseUpd svrReponseUpd= response.body();
                tv1.setText(svrReponseUpd.getMessage());
            }

            @Override
            public void onFailure(Call<SvrReponseUpd> call, Throwable throwable) {
                tv1.setText(throwable.getMessage());
            }
        });
    }
    private void deleteData(){
        PrdDel prdDel= new PrdDel();
        prdDel.setPid(txt0.getText().toString());
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://192.168.88.156:8080/202406/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        InterfaceDel interfaceDel= retrofit.create(InterfaceDel.class);
        Call<SvrResponePrdDel> call = interfaceDel.deleteExe(prdDel.getPid());
        call.enqueue(new Callback<SvrResponePrdDel>() {
            @Override
            public void onResponse(Call<SvrResponePrdDel> call, Response<SvrResponePrdDel> response) {
                SvrResponePrdDel svrResponePrdDel= response.body();
                tv1.setText(svrResponePrdDel.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponePrdDel> call, Throwable throwable) {
                tv1.setText(throwable.getMessage());
            }
        });
    }
    private void insertData(){
        Prd prd= new Prd();
        prd.setName(txt1.getText().toString());
        prd.setPrice(txt2.getText().toString());
        prd.setDescription(txt3.getText().toString());
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://192.168.88.156:8080/202406/create_product.php/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        InterfaceInsertPrd insertPrdObj= retrofit.create(InterfaceInsertPrd.class);
        Call<SvrResponsePrd> call = insertPrdObj.insertPrd(prd.getName(), prd.getPrice(), prd.getDescription());
        call.enqueue(new Callback<SvrResponsePrd>() {
            @Override
            public void onResponse(Call<SvrResponsePrd> call, Response<SvrResponsePrd> response) {
                SvrResponsePrd svrResponsePrd= response.body();
                tv1.setText(svrResponsePrd.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponsePrd> call, Throwable throwable) {
                tv1.setText(throwable.getMessage());
            }
        });
    }
}