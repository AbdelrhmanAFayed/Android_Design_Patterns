package com.example.mvvm.mainnav

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.R
import com.example.mvvm.allproducts.AllProducts
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.favproducts.FavActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnALL.setOnClickListener {
            startActivity(Intent(this, AllProducts::class.java))
        }
        binding.btnFAV.setOnClickListener {
            startActivity(Intent(this, FavActivity::class.java))
        }
        binding.btnEXIT.setOnClickListener {
            finish()
        }

    }
}