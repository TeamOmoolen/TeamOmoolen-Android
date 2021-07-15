package com.omoolen.omooroid.search.fragment.two.recycle.diameter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemSearchTextBinding

class DiameterAdapter : RecyclerView.Adapter<DiameterAdapter.MyViewHolder>() {
    val diameterList = mutableListOf<DiameterInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemSearchTextBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = diameterList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(diameterList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
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

    class MyViewHolder(
        private val binding: ItemSearchTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(diameterInfo: DiameterInfo) {
            binding.tvText.text = diameterInfo.name
        }
    }
}
