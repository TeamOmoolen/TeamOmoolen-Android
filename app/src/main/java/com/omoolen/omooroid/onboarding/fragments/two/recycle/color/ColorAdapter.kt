package com.omoolen.omooroid.onboarding.fragments.two.recycle.color

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOnboardColorBinding

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.MyViewHolder>() {
    val colorList = mutableListOf<ColorInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemOnboardColorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = colorList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(colorList[position])
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
        private val binding: ItemOnboardColorBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(colorInfo: ColorInfo) {
            binding.ivColor.setImageResource(colorInfo.resourceId)
            binding.clColorItem.setBackgroundResource(colorInfo.backId)
        }
    }
}
