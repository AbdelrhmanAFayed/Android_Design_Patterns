package com.example.mvvm.model

import androidx.lifecycle.LiveData

interface ProductLocalDataSource {

    suspend fun insertProduct(product: Product) : Long

    suspend fun deleteProduct(product: Product) : Int

    suspend fun getAllStored() : List<Product>

}