package com.example.mvp.main.allproduct;

import com.example.mvp.main.model.Product;

import java.util.List;

public interface AllContract {

    public interface view
    {
        public void showData(List<Product> products);

    }

    public interface presenter
    {
        public void getProducts();
        public void addToFav(Product product);
    }

}
