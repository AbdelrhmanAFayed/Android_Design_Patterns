package com.example.mvvm.favproducts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.allproducts.AllProductsViewModel
import com.example.mvvm.databinding.ActivityFavBinding
import com.example.mvvm.model.db.ProductLocalDataSourceImp
import com.example.mvvm.model.network.ProductRemoteDataSourceImp
import com.example.mvvm.model.repo.ProductRepoImp

class FavActivity : AppCompatActivity() {

    lateinit var binding: ActivityFavBinding
    lateinit var favProductViewModel: FavProductViewModel
    lateinit var favProductAdapter: FavProductAdapter
    lateinit var myManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myManager = LinearLayoutManager(this)


        favProductViewModel = ViewModelProvider(this, FavProductsViewModelFactory
            (ProductRepoImp.getInstance(ProductLocalDataSourceImp.getInstance(this), ProductRemoteDataSourceImp)))
            .get(FavProductViewModel::class.java)

        favProductAdapter = FavProductAdapter()
        {
         favProductViewModel.deleteFav(it)
        }.apply { submitList(emptyList()) }

        binding.recyclerView.apply {
            adapter = favProductAdapter
            layoutManager =myManager
        }

        favProductViewModel.productList.observe(this) {
            favProductAdapter.submitList(it)
        }
        favProductViewModel.getAllFavProducts()


    }

}