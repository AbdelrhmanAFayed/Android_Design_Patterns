package com.example.mvc.network;

public interface NetWorkCallBack {

    public void onSuccessResult(ProductResponse productResponse);
    public void onFailureResult(String error);

}
