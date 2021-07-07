package com.omoolen.omooroid.home.fragments.one

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ItemOneLensColorBinding

class LensColorListAdapter : RecyclerView.Adapter<LensColorListAdapter.LensColorViewHolder>() {

    var colorList = emptyList<String>()

    class LensColorViewHolder(
        private val binding : ItemOneLensColorBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(color : String){ binding.colorData = color}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LensColorViewHolder {
        val binding = ItemOneLensColorBinding.inflate(
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