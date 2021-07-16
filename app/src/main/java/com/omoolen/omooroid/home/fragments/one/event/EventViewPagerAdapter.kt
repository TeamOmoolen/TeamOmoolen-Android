package com.omoolen.omooroid.home.fragments.one.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ItemOneEventBinding
import com.omoolen.omooroid.home.fragments.one.networkApi.DeadlineEvent

class EventViewPagerAdapter : RecyclerView.Adapter<EventViewPagerAdapter.EventViewHolder>() {

    private var eventList = emptyList<DeadlineEvent>()

    class EventViewHolder( private val binding : ItemOneEventBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(eventInfo: DeadlineEvent){
                binding.eventInfo = eventInfo
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemOneEventBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_one_event, parent, false )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int = eventList.size

    fun setEvent(eventList : List<DeadlineEvent>){
        this.eventList = eventList
        notifyDataSetChanged()
    }


}