package com.caodnhe150776.myapplication.slot9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caodnhe150776.myapplication.R;
import com.squareup.picasso.Picasso;

public class Demo92MainActivity extends AppCompatActivity {

    private TextView tvStyleId, tvBrand, tvPrice, tvAddInfo;
    private ImageView img;
    Intent intent;
    Product91 product;
    CartManager cartManager;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo92_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        img=findViewById(R.id.demo92imageView);
        tvStyleId=findViewById(R.id.demo92tvStyleid);
        tvBrand=findViewById(R.id.demo92tvBrand);
        tvPrice=findViewById(R.id.demo92tvPrice);
        tvAddInfo=findViewById(R.id.demo92tvAddInfo);
        btn=findViewById(R.id.demo92btnAddToCart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCartClicked();
            }
        });
        cartManager =CartManager.getInstance();
        intent=getIntent();
        product=intent.getParcelableExtra("PRODUCT");
        if(product!=null){
            Picasso.get().load(product.getSearchImage()).into(img);
            tvStyleId.setText(product.getStyleId());
            tvBrand.setText(product.getBrand());
            tvPrice.setText(product.getPrice());
            tvAddInfo.setText(product.getInfo());

        }
    }
    private void addToCartClicked(){
        Intent intent1=getIntent();
        Product91 product91= intent1.getParcelableExtra("PRODUCT");
        if(product91!=null){
            cartManager.addProductToCart(product91);
            Intent intent2= new Intent(this,Demo93CartActivity.class);
            startActivity(intent2);
        }
    }
}