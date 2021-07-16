package com.omoolen.omooroid.home.fragments.one.curating

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOneCuratingBinding
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationByUser

class CuratingListAdapter:RecyclerView.Adapter<CuratingListAdapter.CuratingViewHolder>() {

    private var curateList = emptyList<RecommendationByUser>()

    class CuratingViewHolder(
        private val binding : ItemOneCuratingBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(curatingInfo: RecommendationByUser){
            binding.curatingInfo = curatingInfo

            val listForColor = LensColorListAdapter()
            listForColor.setColoring(curatingInfo.otherColorList as List<String>)
            binding.rvOneCuratingColor.adapter = listForColor

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuratingViewHolder {
        val binding = ItemOneCuratingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CuratingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CuratingViewHolder, position: Int) {
        holder.bind(curateList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = curateList.size

    fun setCurating(curateList : List<RecommendationByUser>){
        this.curateList = curateList
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