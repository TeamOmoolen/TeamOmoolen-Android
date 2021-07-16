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

    class TipViewHolder(
        private val binding : ItemOneTipBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(tipInfo: GuideList1){
            binding.tipInfo = tipInfo

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val binding = ItemOneTipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(tipList[position])
    }

    override fun getItemCount():Int = tipList.size

    fun setTip(tipList : List<GuideList1>){
        this.tipList = tipList
        notifyDataSetChanged()
    }


}