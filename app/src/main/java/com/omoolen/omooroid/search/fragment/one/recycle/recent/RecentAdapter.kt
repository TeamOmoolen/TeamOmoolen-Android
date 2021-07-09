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
        private val binding: ItemRecentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(recentInfo: RecentInfo) {
            //TODO : clSearchTouch랑 ivClose에 따로따로 리스너 달기
            //viewModel = OneSearchViewModel()
            //rank 설정
            binding.ivClose.setOnClickListener {
                Log.d("recentAdapter","ivcloseClick")

            }
            binding.tvSearchName.setOnClickListener {
                Log.d("recentAdapter","searchNameCLick")

            }
            binding.tvSearchName.text = recentInfo.name

        }
    }
}
