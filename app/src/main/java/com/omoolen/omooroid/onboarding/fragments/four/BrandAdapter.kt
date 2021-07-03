package com.omoolen.omooroid.onboarding.fragments.four

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemBrandBinding

class BrandAdapter : RecyclerView.Adapter<BrandAdapter.MyViewHolder>() {
    val brandList = mutableListOf<BrandInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemBrandBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = brandList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(brandList[position])


    }

    class MyViewHolder(
        private val binding: ItemBrandBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(brandInfo: BrandInfo) {
            binding.tvName.text = brandInfo.name
        }
    }
}
