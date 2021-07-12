package com.omoolen.omooroid.search.fragment.one.recycle.recent

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemRecentBinding
import com.omoolen.omooroid.util.ListLiveData

class RecentAdapter : RecyclerView.Adapter<RecentAdapter.MyViewHolder>() {
    val recentList = mutableListOf<RecentInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemRecentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = recentList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(recentList[position])

    }

    class MyViewHolder(
        private val binding: ItemRecentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(recentInfo: RecentInfo) {
            binding.tvSearchName.text = recentInfo.name
        }
    }
}
