package com.omoolen.omooroid.detail.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemDetailNewBinding
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter

class DetailNewListAdapter : RecyclerView.Adapter<DetailNewListAdapter.DetailNewViewHolder>() {

    private var detailNewList = emptyList<DetailNewInfo>()

    class DetailNewViewHolder(
        private val binding: ItemDetailNewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailNewInfo: DetailNewInfo) {
            binding.detailNewInfo = detailNewInfo

            val listForColor = LensColorListAdapter()
            listForColor.setColoring(detailNewInfo.colors)
            binding.rvDetailNewColor.adapter = listForColor
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailNewViewHolder {
        val binding = ItemDetailNewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DetailNewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailNewViewHolder, position: Int) {
        holder.bind(detailNewList[position])
    }

    override fun getItemCount(): Int = detailNewList.size

    fun setDetailNew(detailNewList: List<DetailNewInfo>) {
        this.detailNewList = detailNewList
        notifyDataSetChanged()
    }
}