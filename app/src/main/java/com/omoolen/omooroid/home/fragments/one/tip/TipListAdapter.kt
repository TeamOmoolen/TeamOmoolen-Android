package com.omoolen.omooroid.home.fragments.one.tip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ItemOneTipBinding
import com.omoolen.omooroid.home.fragments.one.networkApi.GuideList1

class TipListAdapter : RecyclerView.Adapter<TipListAdapter.TipViewHolder>() {

    private var tipList = emptyList<GuideList1>()

    private var backIdx : Int = 0

    class TipViewHolder(
        private val binding : ItemOneTipBinding,
    ): RecyclerView.ViewHolder(binding.root){
        private val tipBackList = listOf(
            TipBack(R.drawable.ic_btn_translens_normal, R.drawable.img_1_min_home_1),
            TipBack(R.drawable.ic_btn_normal2, R.drawable.group_8005),
            TipBack(R.drawable.ic_btn_lens3, R.drawable.img_1_min_home_3)
        )
        var idx : Int = 0
        fun bind(tipInfo: GuideList1){
            binding.tipInfo = tipInfo
            binding.tipBack =  tipBackList.get(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val binding = ItemOneTipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TipViewHolder(binding )
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(tipList[position])
    }

    override fun getItemCount():Int = tipList.size

    fun setTip(tipList : List<GuideList1>) {
        this.tipList = tipList
        notifyDataSetChanged()
    }


}