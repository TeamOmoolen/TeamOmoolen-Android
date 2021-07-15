package com.omoolen.omooroid.search.fragment.one.recycle.popular

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ItemPopularBinding

/*
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
*/

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    var popularList = emptyList<PopularInfo>()
    private lateinit var itemClickListener : OnItemClickListener

    class PopularViewHolder(
        private val binding: ItemPopularBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var rank : Int = 1
        fun bind(popularInfo: PopularInfo) {
            binding.rank = rank.toString()
            binding.popularInfo = popularInfo

            rank += 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = ItemPopularBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(popularList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener{
        fun onClick(v : View, position: Int)
    }

    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }


    override fun getItemCount(): Int = popularList.size

    fun setPopular(popularList : List<PopularInfo>){
        this.popularList = popularList
        notifyDataSetChanged()
    }


}