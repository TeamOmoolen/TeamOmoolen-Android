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

    fun removeAt(position:Int){
        temp.removeAt(position)
        value = temp
    }

    fun removeAll(){
        for (i in 0 until temp.size){
            temp.removeAt(0)
        }
        value = temp
    }

    fun clear() {
        temp.clear()
        value = temp
    }

    operator fun get(position :Int): T{
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