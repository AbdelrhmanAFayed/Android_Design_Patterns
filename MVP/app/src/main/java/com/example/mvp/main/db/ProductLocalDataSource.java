package com.example.mvp.main.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mvp.main.model.Product;

import java.util.List;

public interface ProductLocalDataSource {

    LiveData<List<Product>> getAllProducts();

    void insertProduct(Product product);

    void deleteProduct(Product product);


}
