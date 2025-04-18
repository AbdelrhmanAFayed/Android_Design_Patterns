package com.example.mvp.main.favproduct.presenter;

import androidx.lifecycle.LiveData;

import com.example.mvp.main.favproduct.FavContract;
import com.example.mvp.main.model.Product;
import com.example.mvp.main.model.ProductRepository;

import java.util.List;

public class FavPresenter implements FavContract.presenter {

    private ProductRepository repo ;

    public FavPresenter(ProductRepository repo) {
        this.repo = repo;
    }


    @Override
    public LiveData<List<Product>> getStored() {

        return repo.getStoredProducts();

    }

    public void removeFav(Product product)
    {
        repo.deleteProduct(product);
    }
}
