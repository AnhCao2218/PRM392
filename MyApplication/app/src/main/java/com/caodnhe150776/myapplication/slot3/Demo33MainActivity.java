package com.caodnhe150776.myapplication.slot3;

import android.content.Intent;
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

public class Demo33MainActivity extends AppCompatActivity {
    EditText txt1,txt2,txt3;
    Button btn1;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo33_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1=findViewById(R.id.demonumber1);
        txt2=findViewById(R.id.demonumber2);
        txt3=findViewById(R.id.demonumber3);
        btn1=findViewById(R.id.demo33btn1);
        tvResult=findViewById(R.id.demo33tv1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            senData();
            }
        });
    }
    private void senData(){
        String a=txt1.getText().toString();
        String b=txt2.getText().toString();
        String c=txt3.getText().toString();
        Intent intent= new Intent(Demo33MainActivity.this,Demo34MainActivity.class);
        intent.putExtra("number1",a);
        intent.putExtra("number2",b);
        intent.putExtra("number3",c);
        startActivity(intent);
    }

}