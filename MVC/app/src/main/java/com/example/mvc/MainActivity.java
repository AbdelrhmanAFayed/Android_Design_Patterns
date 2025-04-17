package com.example.mvc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvc.allproducts.controller.AllProducts;
import com.example.mvc.favproducts.controller.FavActivity;
import com.example.mvc.network.ProductResponse;

public class MainActivity extends AppCompatActivity {

    Button btnAll ;
    Button btnFAV ;

    ProductResponse products ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = findViewById(R.id.btnALL);
        btnFAV = findViewById(R.id.btnFAV);



        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllProducts.class);
                startActivity(intent);
            }
        });

        btnFAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavActivity.class);
                startActivity(intent);
            }
        });


    }
}