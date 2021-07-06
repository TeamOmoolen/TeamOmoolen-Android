package com.omoolen.omooroid.util

import androidx.lifecycle.MutableLiveData

class ListLiveData<T> : MutableLiveData<MutableList<T>>() {
    private val temp = mutableListOf<T>()

    init {
        value = temp
    }

    fun add(item: T) {
        temp.add(item)
        value = temp
    }

    fun addAll(items: List<T>) {
        temp.addAll(items)
        value = temp
    }

    fun remove(item: T) {
        temp.remove(item)
        value = temp
    }

    fun removeAll(){
        for (i in 0 until temp.size){
            temp.removeAt(i)
        }
        value = temp
    }

    fun clear() {
        temp.clear()
        value = temp
    }

    fun get(position :Int): T{
        return temp[position]
    }

    fun setBool(position :Int, bool:T){
        temp[position] = bool
    }

    fun size():Int{
        return temp.size
    }

    fun setPosition(position :T){
        temp[0] = position
    }

}