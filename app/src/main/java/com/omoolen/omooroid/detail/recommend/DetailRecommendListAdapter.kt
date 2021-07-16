package com.omoolen.omooroid.detail.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemDetailRecommendBinding
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter

class DetailRecommendListAdapter :RecyclerView.Adapter<DetailRecommendListAdapter.DetailRecommendViewHolder>(){

    val detailRecommendList = mutableListOf<DetailRecommendInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRecommendViewHolder {
        val binding = ItemDetailRecommendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DetailRecommendViewHolder(binding)
    }

    override fun getItemCount(): Int  = detailRecommendList.size

    override fun onBindViewHolder(holder: DetailRecommendViewHolder, position: Int) {
        holder.bind(detailRecommendList[position])
    }

    class DetailRecommendViewHolder(
        private val binding : ItemDetailRecommendBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(detailRecommendInfo: DetailRecommendInfo){
            binding.detailRecommendInfo = detailRecommendInfo

            val listForColor = LensColorListAdapter()
            listForColor.setColoring(detailRecommendInfo.colors)
            binding.rvOneRecommendColor.adapter = listForColor
        }
    }




}