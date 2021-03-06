package com.omoolen.omooroid.search.fragment.two

import android.util.Log
import android.view.MotionEvent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.search.data.Item
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentInfo
import com.omoolen.omooroid.search.fragment.two.filterSearchApi.RequestSearchData
import com.omoolen.omooroid.search.fragment.two.filterSearchApi.ResponseSearchData
import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TwoSearchViewModel() : ViewModel() {
    val _diameterChoice = MutableLiveData<Int>()

    val filterSuccess = MutableLiveData<Boolean>()
    val filterResultList = ArrayList<Item>()
    var filterTotalItem = MutableLiveData<Int>()
    var filterTotalPages = MutableLiveData<Int>()

    fun getFilterSearch(
        page : Int, sort : String, order : String,
        brandChoice: MutableList<String>,
        colorChoice: MutableList<String>,
        diameterChoice: MutableList<Int>,
        periodChoice: MutableList<Int>
    ) {
        val requestSearchData = setRequestSearchData(brandChoice, colorChoice, diameterChoice, periodChoice)
        Log.d("SERVER_FILTER_SEARCH","포스트 시작")
        val call: Call<ResponseSearchData> =
            RetrofitClient.getApi.getFilterSearch(requestSearchData,page,sort,order)
        call.enqueue(object : Callback<ResponseSearchData> {
            override fun onResponse(
                call: Call<ResponseSearchData>,
                response: Response<ResponseSearchData>
            ){
                Log.d("SERVER_FILTER_SEARCH",response.isSuccessful.toString())
                Log.d("SERVER_FILTER_SEARCH",response.body()?.status.toString())
                Log.d("SERVER_FILTER_SEARCH",response.body()?.message.toString())
                filterResultList.clear()
                val da = response.body()?.data
                da?.items?.forEach {
                    filterResultList.add(Item(it.brand,it.changeCycleMaximum,it.changeCycleMinimum,it.diameter,
                        it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price))
                }
                if (da != null) {
                    filterTotalItem.value = da.totalCount
                    filterTotalPages.value = da.totalPage
                }
                Log.d("SERVER_FILTER_SEARCH","$filterTotalItem , $filterTotalPages")
                filterSuccess.value = true
            }
            override fun onFailure(call: Call<ResponseSearchData>, t: Throwable) {
                TODO("Not yet implemented")
                Log.d("SERVER_FILTER_SEARCH","포스트 실패")
            }
        })
    }

    //    val brand: List<String>,
//    val changeCycleRange: List<Int>,
//    val color: List<String>,
//    val diameter: Int
    //val searchDatabase = SearchDatabase()
    private fun setRequestSearchData(
        brandChoice: MutableList<String>,
        colorChoice: MutableList<String>,
        diameterChoice: MutableList<Int>,
        periodChoice: MutableList<Int>
    ): RequestSearchData {
        return RequestSearchData(brandChoice, periodChoice, colorChoice, diameterChoice[0])
    }


    fun diameterSingleChoice(rv: RecyclerView, e: MotionEvent){
        var child = rv.findChildViewUnder(e.x, e.y)
        if (child != null) {
            var position = rv.getChildAdapterPosition(child)
            var view = rv.layoutManager?.findViewByPosition(position)
            view?.isSelected = true
            _diameterChoice.value = position
            for (i in 0..rv.adapter!!.itemCount) {
                var otherView = rv.layoutManager?.findViewByPosition(i)
                if (otherView != view) {
                    otherView?.isSelected = false
                } else {
                }
            }
        }
    }

}