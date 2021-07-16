package com.omoolen.omooroid.home.fragments.one.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ItemOneLastestEventBinding
import com.omoolen.omooroid.home.fragments.one.networkApi.DeadlineEvent
import com.omoolen.omooroid.home.fragments.one.networkApi.LastestEvent

class LastestEventViewPagerAdapter : RecyclerView.Adapter<LastestEventViewPagerAdapter.LastestEventViewHolder>() {

    private var eventList = emptyList<LastestEvent>()

    class LastestEventViewHolder( private val binding : ItemOneLastestEventBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(eventInfo: LastestEvent){
            binding.eventInfo = eventInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastestEventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemOneLastestEventBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_one_event, parent, false )
        return LastestEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LastestEventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int = eventList.size

    fun setLastestEvent(eventList : List<LastestEvent>){
        this.eventList = eventList
        notifyDataSetChanged()
    }
}