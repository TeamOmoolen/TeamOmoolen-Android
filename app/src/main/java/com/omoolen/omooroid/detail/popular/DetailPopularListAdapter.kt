package com.omoolen.omooroid.detail.popular

import android.view.LayoutInflater
import android.view.View
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
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    fun setDetailNew(detailNewList : List<Popular>){
        this.detailPopularList = detailNewList
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