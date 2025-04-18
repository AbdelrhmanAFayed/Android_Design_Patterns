package com.example.mvp.main.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mvp.main.model.Product;

import java.util.List;

public class ProductLocalDataSourceImp implements ProductLocalDataSource {



    private ProductDAO productDAO ;
    private static ProductLocalDataSourceImp localSource = null ;
    private LiveData<List<Product>> products ;

    public ProductLocalDataSourceImp(Context context) {
        productDAO = AppDataBase.getInstance(context).getProductDAO();
        products = productDAO.getAllProducts();
    }

    public static ProductLocalDataSourceImp getInstance(Context context) {
        if (localSource == null)
        {
            localSource = new ProductLocalDataSourceImp(context);
        }

        return localSource ;
    }

    @Override
    public LiveData<List<Product>> getAllProducts() {
        return products;
    }

    @Override
    public void insertProduct(Product product) {
        new Thread()
        {
            @Override
            public void run() {
                productDAO.insertProduct(product);
            }
        }.start();

    }

    @Override
    public void deleteProduct(Product product) {

        new Thread()
        {
            @Override
            public void run() {
                productDAO.deleteProduct(product);
            }
        }.start();



    }
}
