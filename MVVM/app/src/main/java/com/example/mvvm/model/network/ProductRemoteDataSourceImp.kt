package com.example.mvvm.model.network

import android.util.Log
import com.example.mvvm.model.Product
import com.example.mvvm.model.ProductRemoteDataSource

object ProductRemoteDataSourceImp : ProductRemoteDataSource {

    override suspend fun getAllProducts() : List<Product> {
        try {
            val result = RetroFitHelper.service.getProducts()

            if(result.isSuccessful)
            {
                return result.body()?.products ?: emptyList()
            }
            else
            {
                Log.i("TAG", "getAllProducts: Not Successful ")
                return emptyList()
            }
        }
        catch (th : Throwable)
        {
            th.printStackTrace()
            Log.i("TAG", "getAllProducts: ${th.message} ")
            return emptyList()
        }
    }
}