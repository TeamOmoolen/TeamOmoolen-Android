package com.omoolen.omooroid.onboarding.fragments.three.recycle.period

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOnboardText3Binding


class PeriodAdapter : RecyclerView.Adapter<PeriodAdapter.MyViewHolder>() {
    val periodList = mutableListOf<PeriodInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemOnboardText3Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = periodList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(periodList[position])
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
        private val binding: ItemOnboardText3Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(periodInfo: PeriodInfo) {
//            val params: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(periodInfo.width, periodInfo.height)
//            params.setMargins(1)
//            binding.tvText.layoutParams = params

            binding.tvText.text = periodInfo.period
        }
    }


}
