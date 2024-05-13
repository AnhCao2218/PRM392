package com.caodnhe150776.myapplication.slot3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;

public class Demo32MainActivity extends AppCompatActivity {
    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo32_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.demo32lv);
        getDataToListView();
    }
    private void getDataToListView(){
        String [] arr= new String[]{
                "Lap trinh java 1",
                "Computer science introduction",
                "Mobile programing",
                "Cross-flatform .NET",
                "Javascript Introduction "
        };
        ArrayAdapter<String> adapter= new ArrayAdapter<>(Demo32MainActivity.this, android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(adapter);
    }

}