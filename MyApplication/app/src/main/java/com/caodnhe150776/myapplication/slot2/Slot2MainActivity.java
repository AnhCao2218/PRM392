package com.caodnhe150776.myapplication.slot2;

import android.annotation.SuppressLint;
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

public class Slot2MainActivity extends AppCompatActivity {
    EditText txt1, txt2;
    Button btn1;
    TextView tv1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot2_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1=findViewById(R.id.sl2txt1);
        txt2=findViewById(R.id.sl2txt2);
        btn1=findViewById(R.id.btn1);
        tv1=findViewById(R.id.sl2tv1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i =new Intent(Slot2MainActivity.this, Slot2v2MainActivity.class);
//                i.putExtra("a",Integer.parseInt(txt1.getText().toString()));
//                i.putExtra("b",Integer.parseInt(txt2.getText().toString()));
//                startActivity(i);
                sum();
            }
        });

    }
    void sum(){
        double a= Double.parseDouble(txt1.getText().toString());
        double b= Double.parseDouble(txt2.getText().toString());

        double s=a+b;

        tv1.setText(String.valueOf(s));
    }
}