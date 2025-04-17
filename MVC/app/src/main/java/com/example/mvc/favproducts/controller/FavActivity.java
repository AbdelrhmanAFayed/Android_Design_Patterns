package com.example.mvc.favproducts.controller;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvc.R;
import com.example.mvc.allproducts.controller.AllProducts;
import com.example.mvc.db.AppDataBase;
import com.example.mvc.db.ProductDAO;
import com.example.mvc.favproducts.view.FavProductAdapter;
import com.example.mvc.favproducts.view.onRemoveClick;
import com.example.mvc.model.Product;
import com.example.mvc.network.NetWorkCallBack;
import com.example.mvc.network.ProductResponse;

import java.util.List;

public class FavActivity extends AppCompatActivity implements onRemoveClick {

    List<Product> products;
    ProductDAO productDAO;
    Handler handler ;
    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        recyclerView = findViewById(R.id.recyclerView);

        productDAO = AppDataBase.getInstance(FavActivity.this).getProductDAO();


        new Thread() {

            @Override
            public void run() {
                products = productDAO.getAllProducts();
                FavProductAdapter adapter = new FavProductAdapter(FavActivity.this, products, recyclerView);
            }

        }.start();

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                FavProductAdapter adapter = new FavProductAdapter(FavActivity.this, products, recyclerView);


            }
        };

    }

    @Override
    public void onRemClick(Product product) {
        productDAO = AppDataBase.getInstance(FavActivity.this).getProductDAO();

        new Thread() {

            @Override
            public void run() {
                productDAO.deleteProduct(product);
                products = productDAO.getAllProducts();

                Message msg = new Message();
                handler.sendMessage(msg);

            }

        }.start();

    }
}