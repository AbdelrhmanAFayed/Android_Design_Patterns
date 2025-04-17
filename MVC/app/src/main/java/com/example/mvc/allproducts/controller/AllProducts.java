package com.example.mvc.allproducts.controller;

import static com.example.mvc.R.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import com.example.mvc.R;
import com.example.mvc.allproducts.view.OnAddClick;
import com.example.mvc.allproducts.view.ProductAdapter;
import com.example.mvc.db.AppDataBase;
import com.example.mvc.db.ProductDAO;
import com.example.mvc.model.Product;
import com.example.mvc.network.NetWorkCallBack;
import com.example.mvc.network.ProductClient;
import com.example.mvc.network.ProductResponse;
import com.example.mvc.network.ProductService;

public class AllProducts extends AppCompatActivity implements NetWorkCallBack, OnAddClick {

    ProductResponse products ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);


        ProductClient client = new ProductClient();
        client.makeNetworkCall(this);





    }

    @Override
    public void onSuccessResult(ProductResponse productResponse) {

        RecyclerView recyclerView = findViewById(R.id.recALL);

        ProductAdapter adapter = new ProductAdapter(this,productResponse.products,recyclerView);



    }

    @Override
    public void onFailureResult(String error) {

    }

    @Override
    public void onFavClick(Product product) {

        ProductDAO productDAO = AppDataBase.getInstance(AllProducts.this).getProductDAO();


        new Thread(){

            @Override
            public void run() {
                productDAO.insertProduct(product);
            }

        }.start();

    }
}