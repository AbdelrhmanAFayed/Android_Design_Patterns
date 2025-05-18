package com.example.mvvm.model

interface ProductRemoteDataSource {
    suspend fun getAllProducts() : List<Product>
}