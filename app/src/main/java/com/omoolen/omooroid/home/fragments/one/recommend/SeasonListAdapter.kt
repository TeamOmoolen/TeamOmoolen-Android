package com.omoolen.omooroid.home.fragments.one.recommend

import android.view.LayoutInflater
import android.view.View
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
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = recommendList.size

    fun setRecommend(recommendList : List<RecommendationBySeason>){
        this.recommendList = recommendList
        notifyDataSetChanged()
    }

    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

}