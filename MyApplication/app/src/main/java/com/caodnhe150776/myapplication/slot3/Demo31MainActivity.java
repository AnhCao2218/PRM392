package com.caodnhe150776.myapplication.slot3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;

public class Demo31MainActivity extends AppCompatActivity {
    EditText txt1,txt2;
    Button btn1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo31_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1=findViewById(R.id.sl31txt1);
        txt2=findViewById(R.id.sl31txt2);
        btn1=findViewById(R.id.demo31btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            login();
            }
        });

    }
    private void login(){
        if(txt1.getText().toString().equals("admin")&&txt2.getText().toString().equals("123456789")){
            Toast.makeText(Demo31MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(Demo31MainActivity.this,"User or pass WRONG",Toast.LENGTH_LONG).show();
        }
    }

}