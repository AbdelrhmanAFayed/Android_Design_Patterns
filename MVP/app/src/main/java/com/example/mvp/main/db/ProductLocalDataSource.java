package com.example.mvp.main.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mvp.main.model.Product;

import java.util.List;

public interface ProductLocalDataSource {

    LiveData<List<Product>> getAllProducts(Context context);

    void insertProduct(Product product, Context context);

    void deleteProduct(Product product,Context context);


}
