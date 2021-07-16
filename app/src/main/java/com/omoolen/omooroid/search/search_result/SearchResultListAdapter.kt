package com.omoolen.omooroid.search.search_result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemSearchResultBinding
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter
import com.omoolen.omooroid.search.data.Item

class SearchResultListAdapter:RecyclerView.Adapter<SearchResultListAdapter.SearchResultViewHolder>() {

    private var searchResultList = emptyList<Item>()

    class SearchResultViewHolder(
        private val binding:ItemSearchResultBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(searchResultInfo: Item){
            binding.searchResultInfo = searchResultInfo

            val listForColor = LensColorListAdapter()
            listForColor.setColoring(searchResultInfo.otherColorList)

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

    override fun getItemCount(): Int = searchResultList.size

    fun setSearchResult(resultList: MutableList<Item>){
        this.searchResultList = resultList
        notifyDataSetChanged()
    }

}