package com.omoolen.omooroid.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ItemDetailMainImageBinding

class DetailViewPagerAdapter : RecyclerView.Adapter<DetailViewPagerAdapter.DetailViewHolder>() {

    private var detailImageList = emptyList<DetailInfo>()

    class DetailViewHolder(private val binding: ItemDetailMainImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(detailInfo: DetailInfo) {
                binding.detailInfo = detailInfo
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDetailMainImageBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_detail_main_image, parent, false
        )
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(detailImageList[position])
    }

    override fun getItemCount(): Int = detailImageList.size

    fun setDetailImage(detailImageList: List<DetailInfo>) {
        this.detailImageList = detailImageList
        notifyDataSetChanged()
    }


}