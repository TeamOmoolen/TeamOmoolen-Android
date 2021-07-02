package com.example.practices.home.fragments.one

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practices.databinding.ItemBrandBinding

class BrandAdapter : RecyclerView.Adapter<BrandAdapter.MyViewHolder>() {
    val brandList = mutableListOf<BrandInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BrandAdapter.MyViewHolder {
        val binding = ItemBrandBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = brandList.size

    override fun onBindViewHolder(holder: BrandAdapter.MyViewHolder, position: Int) {
        holder.onBind(brandList[position])
    }

    class MyViewHolder(
        private val binding : ItemBrandBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(brandInfo: BrandInfo){
            binding.tvName.text = brandInfo.name
        }
    }
}
