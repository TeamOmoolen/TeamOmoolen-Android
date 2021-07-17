package com.omoolen.omooroid.search.search_result

import android.view.LayoutInflater
import android.view.View
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
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = searchResultList.size

    fun setSearchResult(resultList: MutableList<Item>){
        this.searchResultList = resultList
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