package com.omoolen.omooroid.home.fragments.one

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.omoolen.omooroid.databinding.FragmentHomeOneBinding
import com.omoolen.omooroid.home.fragments.one.curating.CuratingListAdapter
import com.omoolen.omooroid.home.fragments.one.event.EventViewPagerAdapter
import com.omoolen.omooroid.home.fragments.one.newItem.NewListAdapter
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter
import com.omoolen.omooroid.home.fragments.one.tip.TipListAdapter
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator



class OneHomeFragment : Fragment() {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private var _binding: FragmentHomeOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val oneHomeViewModel: OneHomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeOneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        oneHomeViewModel.setCuratingList()
        setCuratingAdapter()
        setCuratingObserve()

        oneHomeViewModel.setRecommend1List()
        setRecommend1Adapter()
        setRecommend1Observe()

        oneHomeViewModel.setRecommend2List()
        setRecommend2Adapter()
        setRecommend2Observe()

        oneHomeViewModel.setEventList()
        setEventAdapter()
        setEventObserve()
        setEventIndicator()

        oneHomeViewModel.setAdList()
        setAdAdapter()
        setAdObserve()
        setAdIndicator()

        oneHomeViewModel.setTipList()
        setTipAdapter()
        setTipObserve()

        oneHomeViewModel.setNewList()
        setNewAdapter()
        setNewObserve()
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHomeRecommend.addItemDecoration(HorizontalItemDecorator(12, 2, requireContext()))
        binding.rvHomeRecommend.addItemDecoration(VerticalItemDecorator(40, requireContext()))

        binding.rvHomeTemp.addItemDecoration(HorizontalItemDecorator(12, 2, requireContext()))
        binding.rvHomeTemp.addItemDecoration(VerticalItemDecorator(40, requireContext()))

    }

    //adapter()도 안에 있는거, 서버용 data class로 바꾸기.
    //adapter랑 observe 바꾸기 전에 먼저 각 ltem~.xml에 가서 dataBinding 객체 명 부터 바꾸기.

    private fun setCuratingAdapter(){
        binding.rvHomeCurating.adapter = CuratingListAdapter()
    }
    private fun setCuratingObserve(){
        oneHomeViewModel.curatingList.observe(viewLifecycleOwner){
            curatingList -> with(binding.rvHomeCurating.adapter as CuratingListAdapter){
                setCurating(curatingList)
            }
        }
    }


    private fun setRecommend1Adapter(){
        binding.rvHomeRecommend.adapter = RecommendListAdapter()
    }

    private fun setRecommend1Observe() {
        oneHomeViewModel.recommendList1.observe(viewLifecycleOwner) { recommendList ->
            with(binding.rvHomeRecommend.adapter as RecommendListAdapter) {
                setRecommend(recommendList)
            }
        }
    }

    private fun setRecommend2Adapter(){
        binding.rvHomeTemp.adapter = RecommendListAdapter()
    }

    private fun setRecommend2Observe() {
        oneHomeViewModel.recommendList2.observe(viewLifecycleOwner) { tempList ->
            with(binding.rvHomeTemp.adapter as RecommendListAdapter) {
                setRecommend(tempList)
            }
        }
    }

    private fun setTipAdapter(){
        binding.rvHomeTip.adapter = TipListAdapter()
    }

    private fun setTipObserve() {
        oneHomeViewModel.tipList.observe(viewLifecycleOwner) { tipList ->
            with(binding.rvHomeTip.adapter as TipListAdapter) {
                setTip(tipList)
            }
        }
    }

    private fun setNewAdapter(){
        binding.rvHomeNew.adapter = NewListAdapter()
    }

    private fun setNewObserve() {
        oneHomeViewModel.newList.observe(viewLifecycleOwner) { newList ->
            with(binding.rvHomeNew.adapter as NewListAdapter) {
                setNewItem(newList)
            }
        }
    }



    private fun setEventAdapter(){
        binding.vpHomeEvent.adapter = EventViewPagerAdapter()
    }

    private fun setEventObserve(){
        oneHomeViewModel.eventList.observe(viewLifecycleOwner){ eventList ->
            with(binding.vpHomeEvent.adapter as EventViewPagerAdapter){
                setEvent(eventList)
            }
        }
    }

    private fun setEventIndicator() {
        TabLayoutMediator(binding.tabHomeEvent, binding.vpHomeEvent) { tab, position -> }.attach()
    }

    private fun setAdAdapter(){
        binding.vpHomeAd.adapter = EventViewPagerAdapter()
    }

    private fun setAdObserve(){
        oneHomeViewModel.adList.observe(viewLifecycleOwner){ adList ->
            with(binding.vpHomeAd.adapter as EventViewPagerAdapter){
                setEvent(adList)
            }
        }
    }

    private fun setAdIndicator() {
        TabLayoutMediator(binding.tabHomeAd, binding.vpHomeAd) { tab, position -> }.attach()
    }


}