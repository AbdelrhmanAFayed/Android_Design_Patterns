package com.example.mvp.main.model;

import androidx.lifecycle.LiveData;

import com.example.mvp.main.network.NetWorkCallBack;

import java.util.List;

public interface ProductRepository {

    public LiveData<List<Product>> getStoredProducts();

    public void getAllProduct(NetWorkCallBack netWorkCallBack );

    public void insertProduct(Product product);

    public void deleteProduct(Product product);


}
