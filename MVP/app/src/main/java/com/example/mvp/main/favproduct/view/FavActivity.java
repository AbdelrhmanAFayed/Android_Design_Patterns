package com.example.mvp.main.favproduct.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.R;
import com.example.mvp.main.db.ProductLocalDataSourceImp;
import com.example.mvp.main.favproduct.FavContract;
import com.example.mvp.main.favproduct.presenter.FavPresenter;
import com.example.mvp.main.model.Product;
import com.example.mvp.main.model.ProductRepositoryImp;
import com.example.mvp.main.network.ProductRemoteDataSourceImp;

import java.util.List;

public class FavActivity extends AppCompatActivity implements FavContract.view,onRemoveClick {

    RecyclerView favRecview ;

    FavProductAdapter adapter ;
    FavPresenter presenter ;

    LinearLayoutManager manager ;

    LiveData<List<Product>> products ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        presenter = new FavPresenter(new ProductRepositoryImp(ProductRemoteDataSourceImp.getInstance(),ProductLocalDataSourceImp.getInstance(this)));

        products = presenter.getStored();
        initUI();
        manager = new LinearLayoutManager(this);
        favRecview.setLayoutManager(manager);


        products.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
            adapter = new FavProductAdapter(FavActivity.this,products,favRecview,FavActivity.this);
            favRecview.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onRemClick(Product product) {
    presenter.removeFav(product);
    }


    @Override
    public void initUI() {
        favRecview = findViewById(R.id.recyclerView);
    }
}