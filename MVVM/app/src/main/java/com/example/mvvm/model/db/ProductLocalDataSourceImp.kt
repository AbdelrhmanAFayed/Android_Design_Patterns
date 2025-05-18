package com.example.mvvm.model.db

import android.content.Context
import com.example.mvvm.model.Product
import com.example.mvvm.model.ProductLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductLocalDataSourceImp private constructor(context: Context) : ProductLocalDataSource {

    companion object {
        private lateinit var dao: ProductDao

        @Volatile
        private var instance: ProductLocalDataSourceImp? = null

        fun getInstance(context: Context): ProductLocalDataSourceImp {
            return instance ?: synchronized(this)
            { instance ?: ProductLocalDataSourceImp(context).also{
                dao = ProductDataBase.getInstance(context).getProductDao()
                instance = it }
            }
        }
    }

    override suspend fun insertProduct(product: Product): Long {
        return withContext(Dispatchers.IO) { dao.insert(product) }
    }
    override suspend fun deleteProduct(product: Product): Int {
        return withContext(Dispatchers.IO) { dao.delete(product) }
    }
    override suspend fun getAllStored(): List<Product> {
        return withContext(Dispatchers.IO) { dao.getAll() }
    }

}
