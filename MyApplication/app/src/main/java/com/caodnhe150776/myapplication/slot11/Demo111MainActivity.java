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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demo111MainActivity extends AppCompatActivity {

    EditText txt1,txt2,txt3;
    TextView tv1;
    Button btnInsert;
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
        txt1=findViewById(R.id.demo111txt1);
        txt2=findViewById(R.id.demo111txt2);
        txt3=findViewById(R.id.demo111txt3);
        tv1=findViewById(R.id.demo111tvResult);
        btnInsert=findViewById(R.id.demo111btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }
    private void insertData(){
        Prd prd= new Prd();
        prd.setName(txt1.getText().toString());
        prd.setPrice(txt2.getText().toString());
        prd.setDescription(txt3.getText().toString());
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://192.168.88.154:8080/202406/create_product.php/")
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