package com.example.mvp.main.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mvp.main.model.Product;

import java.util.List;

public class ProductLocalDataSourceImp implements ProductLocalDataSource {

    @Override
    public LiveData<List<Product>> getAllProducts(Context context) {
        return AppDataBase.getInstance(context).getProductDAO().getAllProducts();
    }

    @Override
    public void insertProduct(Product product, Context context) {
        new Thread()
        {
            @Override
            public void run() {
                AppDataBase.getInstance(context).getProductDAO().insertProduct(product);
            }
        }.start();
    }

    @Override
    public void deleteProduct(Product product, Context context) {

        new Thread()
        {
            @Override
            public void run() {
                AppDataBase.getInstance(context).getProductDAO().deleteProduct(product);
            }
        }.start();


    }
}
