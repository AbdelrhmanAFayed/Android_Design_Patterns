package com.example.mvvm.allproducts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityAllProductsBinding
import com.example.mvvm.model.db.ProductLocalDataSourceImp
import com.example.mvvm.model.network.ProductRemoteDataSourceImp
import com.example.mvvm.model.repo.ProductRepoImp

class AllProducts : AppCompatActivity() {

    lateinit var binding: ActivityAllProductsBinding
    lateinit var allProductsViewModel: AllProductsViewModel
    lateinit var allAdapter: AllProductAdapter
    lateinit var myManger : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        allProductsViewModel = ViewModelProvider(this,
            AllProductsViewModelFactory
            (ProductRepoImp.getInstance(ProductLocalDataSourceImp.getInstance(this), ProductRemoteDataSourceImp)))
            .get(AllProductsViewModel::class.java)

        allAdapter = AllProductAdapter()
        {
            allProductsViewModel.insertFav(it)
        }
        allAdapter.submitList(emptyList())

        myManger = LinearLayoutManager(this)

        binding.recALL.apply {
            adapter = allAdapter
            layoutManager = myManger
        }

        allProductsViewModel.productList.observe(this) {
            allAdapter.submitList(it)
        }
        allProductsViewModel.getAllProducts()


    }


}