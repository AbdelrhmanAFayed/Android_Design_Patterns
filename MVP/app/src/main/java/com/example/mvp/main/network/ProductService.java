package com.example.mvp.main.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("products")
    Call<ProductResponse> getProducts();
}
