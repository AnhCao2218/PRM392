package com.caodnhe150776.myapplication.slot14;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;

public class Demo141MainActivity extends AppCompatActivity {
    Button btnSelect,btnInsert,btnUpdate,btnDelete;
    TextView tvResult;
    FNVolley fn;
    Context context=this;
    EditText txtName,txtPrice,txtDes,txtPid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo141_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnSelect=findViewById(R.id.demo141btngetdata);
        btnInsert=findViewById(R.id.demo141btnInsert);
        btnUpdate=findViewById(R.id.demo141btnUpdate);
        btnDelete=findViewById(R.id.demo141btnDelete);
        tvResult=findViewById(R.id.demo141TvResult);
        txtName=findViewById(R.id.demo141txtName);
        txtPrice=findViewById(R.id.demo141txtPrice);
        txtDes=findViewById(R.id.demo141txtDes);
        txtPid=findViewById(R.id.demo141txtPid);
        fn= new FNVolley();
        btnSelect.setOnClickListener(v->{
            fn.getArrayOfObject(context,tvResult);
        });
        btnInsert.setOnClickListener(v->{
            fn.insertVolley(context,tvResult,txtName,txtPrice,txtDes);
        });
        btnUpdate.setOnClickListener(v->{
            fn.updateVolley(context,tvResult,txtPid,txtName,txtPrice,txtDes);
        });
        btnDelete.setOnClickListener(v->{
            fn.deleteVolley(context,tvResult,txtPid);
        });
    }
}