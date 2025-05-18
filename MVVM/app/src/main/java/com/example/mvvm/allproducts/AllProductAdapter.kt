package com.example.mvvm.allproducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.databinding.SingleProductBinding
import com.example.mvvm.model.Product
import com.example.mvvm.model.ProductDiffCallback

class AllProductAdapter (var listener : (Product) -> Unit ) : ListAdapter<Product, AllProductAdapter.ProductHolder>(ProductDiffCallback())
{
    lateinit var binding: SingleProductBinding

    class ProductHolder(val binding: SingleProductBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        binding = SingleProductBinding.inflate(inflater,parent,false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {

        val product = getItem(position)
        holder.binding.textTitle.text = product.title
        holder.binding.textPrice.text = product.price.toString()
        holder.binding.ratingBar.rating = product.rating.toFloat()
        holder.binding.textDesc.text = product.description
        Glide.with(holder.binding.imgView).load(product.thumbnail).into(holder.binding.imgView)
        holder.binding.btnRemove.setOnClickListener { listener(product) }
    }
}
