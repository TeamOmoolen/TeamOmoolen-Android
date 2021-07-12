package com.omoolen.omooroid.search.fragment.one.recycle.popular

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ItemPopularBinding

class PopularAdapter(context:Context) : RecyclerView.Adapter<PopularAdapter.MyViewHolder>() {
    val popularList = mutableListOf<PopularInfo>()
    val mContext = context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemPopularBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding,mContext)
    }

    override fun getItemCount(): Int = popularList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(popularList[position])
    }

    class MyViewHolder(
        private val binding: ItemPopularBinding,
        private val mContext : Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(popularInfo: PopularInfo) {
            binding.tvRank.text = popularInfo.rank.toString()
            if(popularInfo.rank <4) {
                binding.tvRank.setTextColor(mContext.resources.getColor(R.color.om_main_orange))
                binding.tvSearchName.setTextColor(mContext.resources.getColor(R.color.om_main_black))
            }
            else{
                binding.tvRank.setTextColor(mContext.resources.getColor(R.color.om_fourth_gray))
                binding.tvSearchName.setTextColor(mContext.resources.getColor(R.color.om_third_gray))
            }

            binding.tvSearchName.text = popularInfo.name
        }
    }
}
