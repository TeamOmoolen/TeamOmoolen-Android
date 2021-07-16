package com.omoolen.omooroid.detail.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemDetailNewBinding
import com.omoolen.omooroid.detail.detailApi.Popular
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter

class DetailPopularListAdapter : RecyclerView.Adapter<DetailPopularListAdapter.DetailNewViewHolder>() {

    private var detailPopularList = emptyList<Popular>()

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

    override fun getItemCount(): Int = detailPopularList.size

    class DetailNewViewHolder(
        private val binding: ItemDetailNewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(popular: Popular) {
            binding.detailNewInfo = popular
        }
    }

    override fun onBindViewHolder(holder: DetailNewViewHolder, position: Int) {
        holder.bind(detailPopularList[position])
    }

    fun setDetailNew(detailNewList : List<Popular>){
        this.detailPopularList = detailNewList
        notifyDataSetChanged()
    }


}