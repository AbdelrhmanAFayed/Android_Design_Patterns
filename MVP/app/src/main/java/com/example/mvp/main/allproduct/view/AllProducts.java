package com.example.mvp.main.allproduct.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.R;
import com.example.mvp.main.allproduct.AllContract;
import com.example.mvp.main.allproduct.presenter.AllPresenter;
import com.example.mvp.main.db.ProductLocalDataSourceImp;
import com.example.mvp.main.model.Product;
import com.example.mvp.main.model.ProductRepositoryImp;
import com.example.mvp.main.network.ProductRemoteDataSourceImp;

import java.util.List;

public class AllProducts extends AppCompatActivity implements AllContract.view, OnAddClick {


    RecyclerView allRecView ;

    ProductAdapter adapter ;
    AllPresenter presenter ;
    LinearLayoutManager manager ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        presenter = new AllPresenter(this, ProductRepositoryImp.getInstance(ProductRemoteDataSourceImp.getInstance(),ProductLocalDataSourceImp.getInstance(this)));
        presenter.getProducts() ;



    }

    private void initUI()
    {
        allRecView = findViewById(R.id.recALL);



    }


    @Override
    public void showData(List<Product> products) {
        initUI();
        manager = new LinearLayoutManager(this);
        adapter = new ProductAdapter(this,products, allRecView);
        allRecView.setAdapter(adapter);
        allRecView.setLayoutManager(manager);



    }


    @Override
    public void onFavClick(Product product) {

        presenter.addToFav(product);
    }
}