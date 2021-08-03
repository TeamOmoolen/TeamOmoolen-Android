package com.omoolen.omooroid.home.fragments.one.newItem

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOneNewBinding
import com.omoolen.omooroid.detail.DetailActivity



class NewListAdapter : RecyclerView.Adapter<NewListAdapter.NewViewHolder>(){

    private var newList = emptyList<NewInfo>()

    class NewViewHolder(
        private val binding : ItemOneNewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(newInfo: NewInfo){
            binding.newInfo = newInfo

            binding.clOneNewItem0.setOnClickListener {
                Log.d("NEW_VIEW", adapterPosition.toString())
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("itemId", newInfo.newItems[0].id)
                startActivity(itemView.context, intent, null)
            }

            binding.clOneNewItem1.setOnClickListener {
                Log.d("NEW_VIEW", adapterPosition.toString())
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("itemId", newInfo.newItems[1].id)
                startActivity(itemView.context, intent, null)
            }
            binding.clOneNewItem2.setOnClickListener{
                Log.d("NEW_VIEW", adapterPosition.toString())
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("itemId", newInfo.newItems[2].id)
                startActivity(itemView.context, intent, null)
            }
            binding.clOneNewItem3.setOnClickListener {
                Log.d("NEW_VIEW", adapterPosition.toString())
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("itemId", newInfo.newItems[3].id)
                startActivity(itemView.context, intent, null)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val binding = ItemOneNewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.bind(newList[position])
    }

    override fun getItemCount(): Int = newList.size

    fun setNewItem(newList: List<NewInfo>) {
        this.newList = newList
        notifyDataSetChanged()
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
}