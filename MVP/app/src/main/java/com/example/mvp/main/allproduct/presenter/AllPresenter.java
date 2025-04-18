package com.example.mvp.main.allproduct.presenter;

import com.example.mvp.main.allproduct.AllContract;
import com.example.mvp.main.model.Product;
import com.example.mvp.main.model.ProductRepository;
import com.example.mvp.main.network.NetWorkCallBack;
import com.example.mvp.main.network.ProductResponse;

public class AllPresenter implements AllContract.presenter, NetWorkCallBack {

    private AllContract.view view ;
    private ProductRepository repo ;



    public AllPresenter(AllContract.view view, ProductRepository repo) {
        this.view = view;
        this.repo = repo;
    }


    @Override
    public void getProducts() {

        repo.getAllProduct(this);
    }

    @Override
    public void addToFav(Product product) {
        repo.insertProduct(product);

    }

    @Override
    public void onSuccessResult(ProductResponse productResponse) {
        view.showData(productResponse.products);

    }

    @Override
    public void onFailureResult(String error) {

    }
}
