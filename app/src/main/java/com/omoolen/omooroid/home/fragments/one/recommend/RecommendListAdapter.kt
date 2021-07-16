package com.omoolen.omooroid.home.fragments.one.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOneRecommendBinding
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter

class RecommendListAdapter : RecyclerView.Adapter<RecommendListAdapter.RecommendViewHolder>() {

    private var recommendList = emptyList<RecommendInfo>()

    class RecommendViewHolder(
        private val binding : ItemOneRecommendBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(recommendInfo: RecommendInfo){
            binding.recommendInfo = recommendInfo

            val listForColor = LensColorListAdapter()
            listForColor.setColoring(recommendInfo.colors)
            binding.rvOneRecommendColor.adapter = listForColor

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemOneRecommendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.bind(recommendList[position])

    }

    override fun getItemCount(): Int = recommendList.size

    fun setRecommend(recommendList : List<RecommendInfo>){
        this.recommendList = recommendList
        notifyDataSetChanged()
    }

}