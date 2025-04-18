package com.example.mvp.main.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRemoteDataSourceImp  implements ProductRemoteDataSource{

    private static final String BASE_URL ="https://dummyjson.com/" ;
    private ProductService service ;
    private ProductResponse products = null ;
    private static ProductRemoteDataSourceImp client = null ;
    Call<ProductResponse> call ;
    private ProductRemoteDataSourceImp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            service =retrofit.create(ProductService.class);
    }

    public static ProductRemoteDataSourceImp getInstance()
    {
        if(client == null)
        {
            client = new ProductRemoteDataSourceImp();
        }


        return client;
    }


    public void makeNetworkCall(NetWorkCallBack callBack) {


                Call<ProductResponse> call = service.getProducts();

                call.enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if(response.isSuccessful())
                        {
                            callBack.onSuccessResult(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        callBack.onFailureResult(t.getMessage());
                        t.printStackTrace();
                    }
                });

            }


    }


