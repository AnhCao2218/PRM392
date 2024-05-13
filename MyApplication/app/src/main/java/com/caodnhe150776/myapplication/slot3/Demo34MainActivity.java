package com.caodnhe150776.myapplication.slot3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;

public class Demo34MainActivity extends AppCompatActivity {
    TextView tvKQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo34_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvKQ=findViewById(R.id.demo34tvResult);
        Intent intent= getIntent();
        int a=Integer.parseInt(intent.getExtras().getString("number1"));
        int b=Integer.parseInt(intent.getExtras().getString("number2"));
        int c=Integer.parseInt(intent.getExtras().getString("number3"));

        int delta= b*b-4*a*c;
        if(delta<0){
            tvKQ.setText("PTVN");
        }else if(delta==0){
            tvKQ.setText("PT co nghiem kep x="+ (-b)/(2*a));
        }else {
            float x1= (float) ((-b+Math.sqrt(delta))/(2*a));
            float x2= (float) ((-b-Math.sqrt(delta))/(2*a));
            tvKQ.setText("PT có 2 nghiem x1="+x1+" va x2="+x2);

        }

    }
}