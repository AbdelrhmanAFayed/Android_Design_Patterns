package com.example.mvvm.favproducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.databinding.FavProductBinding
import com.example.mvvm.model.Product
import com.example.mvvm.model.ProductDiffCallback

class FavProductAdapter (var listener : (Product) -> Unit ) : ListAdapter<Product, FavProductAdapter.ProductHolder>(ProductDiffCallback())
{
    lateinit var binding: FavProductBinding

    class ProductHolder(val binding: FavProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        binding = FavProductBinding.inflate(inflater,parent,false)
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
