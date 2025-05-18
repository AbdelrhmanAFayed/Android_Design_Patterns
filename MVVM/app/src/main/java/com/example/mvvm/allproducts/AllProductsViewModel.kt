package com.example.mvvm.allproducts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvm.model.Product
import com.example.mvvm.model.repo.ProductRepo
import kotlinx.coroutines.launch

class AllProductsViewModelFactory(private val remoteRepo: ProductRepo) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllProductsViewModel(remoteRepo) as T
    }
}


class AllProductsViewModel(private val remoteRepo: ProductRepo) : ViewModel() {

    private val productListPrivate = MutableLiveData<List<Product>>()
    val productList : LiveData<List<Product>> = productListPrivate
    fun getAllProducts()
    {
        viewModelScope.launch {
            val products = remoteRepo.getAllRemote()
            productListPrivate.postValue(products)
        }
    }

    fun insertFav(product: Product)
    {
        viewModelScope.launch {
            val result =  remoteRepo.insertProductLocal(product)
            Log.i("TAG", "insert Product $result")
        }
    }

}