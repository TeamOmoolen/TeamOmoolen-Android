package com.omoolen.omooroid.home.fragments.one.newItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOneNewBinding
import com.omoolen.omooroid.databinding.ItemOneRecommendBinding
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendInfo
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter

class NewListAdapter : RecyclerView.Adapter<NewListAdapter.NewViewHolder>(){

    private var newList = emptyList<NewInfo>()

    class NewViewHolder(
        private val binding : ItemOneNewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(newInfo: NewInfo){
            binding.newInfo = newInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val binding = ItemOneNewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.bind(newList[position])
    }

    override fun getItemCount(): Int = newList.size

    fun setNewItem(newList: List<NewInfo>){
        this.newList = newList
        notifyDataSetChanged()
    }
}