package com.omoolen.omooroid.onboarding.fragments.one.recycle.gender

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOnboardPictureBinding
import com.omoolen.omooroid.util.ListLiveData

class GenderAdapter : RecyclerView.Adapter<GenderAdapter.MyViewHolder>() {
    val genderList = mutableListOf<GenderInfo>()
    var genderArr = ListLiveData <Boolean>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemOnboardPictureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        genderArr.add(false)
        genderArr.add(false)
        return MyViewHolder(binding)
    }
    fun setSelected(position:Int){
        genderArr.set(position,!genderArr.get(position))
        var num = 0
        for (i in 0 until (genderArr.size())){
            if (genderArr.get(i)){
                num++
            }
            if(num > 1){
                genderArr.set(i,!genderArr.get(i))
                break
            }
        }
    }

    override fun getItemCount(): Int = genderList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(genderList[position])
        holder.itemView.isSelected = genderArr.get(position) //////
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
        notifyDataSetChanged()
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    class MyViewHolder(
        private val binding: ItemOnboardPictureBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(genderInfo: GenderInfo) {
            binding.ivPic.setImageResource(genderInfo.resourceId)
            binding.tvText.text = genderInfo.name

        }
    }
}
