package com.omoolen.omooroid.search.search_result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemSearchResultBinding
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter

class SearchResultListAdapter:RecyclerView.Adapter<SearchResultListAdapter.SearchResultViewHolder>() {

    val searchResultList = mutableListOf<SearchResultInfo>()

    class SearchResultViewHolder(
        private val binding:ItemSearchResultBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(searchResultInfo: SearchResultInfo){
            binding.searchResultInfo = searchResultInfo

            val listForColor = LensColorListAdapter()
            listForColor.setColoring(searchResultInfo.colors)
            binding.rvOneRecommendColor.adapter = listForColor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(searchResultList[position])
    }

    override fun getItemCount(): Int =searchResultList.size

}