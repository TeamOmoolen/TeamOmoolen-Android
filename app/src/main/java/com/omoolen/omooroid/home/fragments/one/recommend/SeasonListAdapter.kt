package com.omoolen.omooroid.home.fragments.one.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOneRecommendBinding
import com.omoolen.omooroid.databinding.ItemOneSeasonBinding
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySeason
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySituation

class SeasonListAdapter : RecyclerView.Adapter<SeasonListAdapter.SeasonViewHolder>() {

    private var recommendList = emptyList<RecommendationBySeason>()

    class SeasonViewHolder(
        private val binding : ItemOneSeasonBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(recommendInfo: RecommendationBySeason){
            binding.recommendInfo = recommendInfo

            val listForColor = LensColorListAdapter()
            listForColor.setColoring(recommendInfo.otherColorList)
            binding.rvOneSeasonColor.adapter = listForColor

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val binding = ItemOneSeasonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SeasonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(recommendList[position])
    }

    override fun getItemCount(): Int = recommendList.size

    fun setRecommend(recommendList : List<RecommendationBySeason>){
        this.recommendList = recommendList
        notifyDataSetChanged()
    }

}