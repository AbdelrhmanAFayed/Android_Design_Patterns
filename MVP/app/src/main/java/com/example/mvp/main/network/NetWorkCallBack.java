package com.example.mvp.main.network;

public interface NetWorkCallBack {

    public void onSuccessResult(ProductResponse productResponse);
    public void onFailureResult(String error);

}
