package com.omoolen.omooroid.search.fragment.one.recycle.recent

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemRecentBinding
import com.omoolen.omooroid.search.fragment.one.OneSearchViewModel

class RecentAdapter() : RecyclerView.Adapter<RecentAdapter.MyViewHolder>() {
    val recentList = mutableListOf<RecentInfo>()
    private lateinit var itemClickListener : OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemRecentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = recentList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(recentList[position])

        holder.binding.run{
            ivClose.setOnClickListener {
                Log.d("recentAdapter","ivcloseClick")
                itemClickListener.deleteRecentOnClick(this.ivClose,position)
            }
            clSearch.setOnClickListener {
                Log.d("recentAdapter","searchNameCLick")
                //TODO : 상세페이지로 이동
                itemClickListener.searchOnClick(this.clSearch,position)
            }
        }
    }

    // 리사이클러 뷰 내에 다른 버튼들에 리스너 다는 법
    interface OnItemClickListener {
        fun deleteRecentOnClick(v: View, position: Int)
        fun searchOnClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }


    class MyViewHolder(
        val binding: ItemRecentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(recentInfo: RecentInfo) {
            //TODO : clSearchTouch랑 ivClose에 따로따로 리스너 달기

            //rank 설정
            binding.tvSearchName.text = recentInfo.name
        }
    }
}
