package com.caodnhe150776.myapplication.slot9;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;

import java.util.List;

public class Demo93CartActivity extends AppCompatActivity {

    private ListView listView;
    private Demo93CartAdapter adapter;

    CartManager cartManager;
    List<Product91> cartItem;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo93_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.demo93_cart_activity);
        //
        cartManager =CartManager.getInstance();
        cartItem=cartManager.getCartItems();
        //
        adapter= new Demo93CartAdapter(this,cartItem);
        listView.setAdapter(adapter);

    }
}