package com.omoolen.omooroid.onboarding.fragments.three.recycle.effect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOnboardTextBinding

class EffectAdapter : RecyclerView.Adapter<EffectAdapter.MyViewHolder>() {
    val effectList = mutableListOf<EffectInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemOnboardTextBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = effectList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(effectList[position])
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
        private val binding: ItemOnboardTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(effectInfo: EffectInfo) {
            binding.tvText.text = effectInfo.effect
        }
    }
}
