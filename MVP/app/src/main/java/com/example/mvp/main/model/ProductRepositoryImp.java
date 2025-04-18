package com.example.mvp.main.model;

import androidx.lifecycle.LiveData;

import com.example.mvp.main.db.ProductLocalDataSource;
import com.example.mvp.main.network.NetWorkCallBack;
import com.example.mvp.main.network.ProductRemoteDataSource;
import com.example.mvp.main.network.ProductRemoteDataSourceImp;

import java.util.List;

public class ProductRepositoryImp implements ProductRepository{

    ProductRemoteDataSource remoteDataSource ;
    ProductLocalDataSource localDataSource ;
    private static ProductRepositoryImp repo = null;

    public static ProductRepositoryImp getInstance(ProductRemoteDataSource remoteDataSource, ProductLocalDataSource localDataSource)
    {
        if(repo == null)
        {
            repo = new ProductRepositoryImp(remoteDataSource,localDataSource);
        }

        return repo ;
    }

    public ProductRepositoryImp(ProductRemoteDataSource remoteDataSource, ProductLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public LiveData<List<Product>> getStoredProducts() {
        return localDataSource.getAllProducts();
    }

    @Override
    public void getAllProduct(NetWorkCallBack netWorkCallBack) {
        remoteDataSource.makeNetworkCall(netWorkCallBack);

    }

    @Override
    public void insertProduct(Product product) {
        localDataSource.insertProduct(product);

    }

    @Override
    public void deleteProduct(Product product) {
        localDataSource.deleteProduct(product);
    }
}
