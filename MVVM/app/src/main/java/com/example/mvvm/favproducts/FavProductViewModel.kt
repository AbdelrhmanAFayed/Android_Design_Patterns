package com.example.mvvm.favproducts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvm.allproducts.AllProductsViewModel
import com.example.mvvm.model.Product
import com.example.mvvm.model.repo.ProductRepo
import kotlinx.coroutines.launch

class FavProductsViewModelFactory(private val remoteRepo: ProductRepo) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavProductViewModel(remoteRepo) as T
    }
}



class FavProductViewModel(private val remoteRepo: ProductRepo) : ViewModel() {

    private val productListPrivate = MutableLiveData<List<Product>>()
    val productList : LiveData<List<Product>> = productListPrivate
    fun getAllFavProducts()
    {
        viewModelScope.launch {
            val products = remoteRepo.getAllStoredLocal()
            productListPrivate.postValue(products)
        }
    }

    fun deleteFav(product: Product)
    {
        viewModelScope.launch {
            val result =  remoteRepo.deleteProductLocal(product)
            getAllFavProducts()
            Log.i("TAG", "delete Product $result")
        }
    }

}