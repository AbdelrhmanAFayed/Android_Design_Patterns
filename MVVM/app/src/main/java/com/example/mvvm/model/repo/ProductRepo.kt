package com.example.mvvm.model.repo

import com.example.mvvm.model.Product

interface ProductRepo {

    suspend fun getAllRemote() : List<Product>

    suspend fun getAllStoredLocal() : List<Product>

    suspend fun insertProductLocal(product: Product) : Long

    suspend fun deleteProductLocal(product: Product) : Int

}