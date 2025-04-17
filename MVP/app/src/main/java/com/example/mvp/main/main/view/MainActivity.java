package com.example.mvp.main.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp.R;
import com.example.mvp.main.allproduct.view.AllProducts;
import com.example.mvp.main.favproduct.view.FavActivity;
import com.example.mvp.main.main.MainContract;


public class MainActivity extends AppCompatActivity implements MainContract.view {

    Button btnAll ;
    Button btnFAV ;
    Button btnExit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = findViewById(R.id.btnALL);
        btnFAV = findViewById(R.id.btnFAV);
        btnExit  =findViewById(R.id.btnEXIT);


        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.OnAllProducts();

            }
        });

        btnFAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.OnFavProducts();

            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.OnExit();


            }
        });


    }

    @Override
    public void OnAllProducts() {
        Intent intent = new Intent(MainActivity.this, AllProducts.class);
        startActivity(intent);

    }

    @Override
    public void OnFavProducts() {
        Intent intent = new Intent(MainActivity.this, FavActivity.class);
        startActivity(intent);

    }

    @Override
    public void OnExit() {

        MainActivity.this.finish();
    }
}


