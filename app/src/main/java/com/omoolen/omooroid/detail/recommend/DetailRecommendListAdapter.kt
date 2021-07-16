package com.omoolen.omooroid.detail.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemDetailRecommendBinding
import com.omoolen.omooroid.detail.detailApi.Suggest
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter

class DetailRecommendListAdapter :RecyclerView.Adapter<DetailRecommendListAdapter.DetailRecommendViewHolder>(){

    private var detailRecommendList = emptyList<Suggest>()

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRecommendViewHolder {
//        val binding = ItemDetailRecommendBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//
//        return DetailRecommendViewHolder(binding)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRecommendViewHolder {
        val binding = ItemDetailRecommendBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)

        return DetailRecommendViewHolder(binding)
    }

    class DetailRecommendViewHolder(
        private val binding : ItemDetailRecommendBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(detailRecommendInfo: Suggest){
            binding.detailRecommendInfo = detailRecommendInfo

            val listForColor = LensColorListAdapter()
            listForColor.setColoring(detailRecommendInfo.otherColorList)
            binding.rvOneRecommendColor.adapter = listForColor
        }
    }

    override fun onBindViewHolder(holder: DetailRecommendViewHolder, position: Int) {
        holder.bind(detailRecommendList[position])
    }

    override fun getItemCount(): Int  = detailRecommendList.size

    fun setDetailRecommend(detailRecommendList : List<Suggest>){
        this.detailRecommendList = detailRecommendList
        notifyDataSetChanged()
    }

}