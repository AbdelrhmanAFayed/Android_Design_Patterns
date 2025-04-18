package com.example.mvp.main.favproduct;

import androidx.lifecycle.LiveData;

import com.example.mvp.main.model.Product;

import java.util.List;

public interface FavContract {

    public interface view
    {
        public void initUI();
    }

    public interface presenter
    {
        public LiveData<List<Product>> getStored();
        public void removeFav(Product product);

    }
}
