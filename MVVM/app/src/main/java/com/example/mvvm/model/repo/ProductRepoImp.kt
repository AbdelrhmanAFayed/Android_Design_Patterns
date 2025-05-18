package com.example.mvvm.model.repo

import com.example.mvvm.model.Product
import com.example.mvvm.model.ProductLocalDataSource
import com.example.mvvm.model.ProductRemoteDataSource
import com.example.mvvm.model.network.ProductRemoteDataSourceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepoImp private constructor(localSource: ProductLocalDataSource , remoteSource: ProductRemoteDataSource): ProductRepo {

    companion object
    {
        lateinit var localDataSource: ProductLocalDataSource
        lateinit var remoteDataSource: ProductRemoteDataSource

        @Volatile
        var instance : ProductRepoImp? = null

        fun getInstance(localSource: ProductLocalDataSource , remoteSource: ProductRemoteDataSource) : ProductRepoImp
        {
            return instance ?: synchronized(this) { instance ?: ProductRepoImp(localSource,remoteSource).also {
                localDataSource = localSource
                remoteDataSource = remoteSource
            }
            }
        }
    }



    override suspend fun getAllRemote(): List<Product> {

        val result = withContext(Dispatchers.IO) { remoteDataSource.getAllProducts() }

        return result
    }

    override suspend fun getAllStoredLocal(): List<Product> {
        val result = withContext(Dispatchers.IO) {
            localDataSource.getAllStored()
        }
        return result
    }

    override suspend fun insertProductLocal(product: Product): Long {
        val result = withContext(Dispatchers.IO) {
            localDataSource.insertProduct(product)
        }
        return result
    }

    override suspend fun deleteProductLocal(product: Product): Int {
        val result = withContext(Dispatchers.IO) {
            localDataSource.deleteProduct(product)
        }
        return result
    }

}