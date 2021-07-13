package com.omoolen.omooroid.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemDetailLensColorBinding

class DetailLensColorListAdapter : RecyclerView.Adapter<DetailLensColorListAdapter.LensColorViewHolder>() {

    var colorList = emptyList<String>()

    class LensColorViewHolder(
        private val binding : ItemDetailLensColorBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(color : String){ binding.colorData = color}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LensColorViewHolder {
        val binding = ItemDetailLensColorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LensColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LensColorViewHolder, position: Int) {
        holder.bind(colorList[position])
    }

    override fun getItemCount(): Int = colorList.size

    fun setColoring(colorList: List<String>) {
        this.colorList = colorList
        notifyDataSetChanged()
    }
}