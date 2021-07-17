package com.omoolen.omooroid.home.fragments.one.recommend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOneSituationBinding
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySituation

class SituationListAdapter : RecyclerView.Adapter<SituationListAdapter.SituationViewHolder>() {

    private var recommendList = emptyList<RecommendationBySituation>()

    class SituationViewHolder(
        private val binding : ItemOneSituationBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(recommendInfo: RecommendationBySituation){
            binding.recommendInfo = recommendInfo

            val listForColor = LensColorListAdapter()
            listForColor.setColoring(recommendInfo.otherColorList)
            binding.rvOneRecommendColor.adapter = listForColor

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SituationViewHolder {
        val binding = ItemOneSituationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SituationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SituationViewHolder, position: Int) {
        holder.bind(recommendList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = recommendList.size

    fun setRecommend(recommendList : List<RecommendationBySituation>){
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