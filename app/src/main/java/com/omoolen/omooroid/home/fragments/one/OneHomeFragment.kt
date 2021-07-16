package com.omoolen.omooroid.home.fragments.one

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentHomeOneBinding
import com.omoolen.omooroid.detail.DetailActivity
import com.omoolen.omooroid.home.HomeActivity
import com.omoolen.omooroid.home.fragments.one.curating.CuratingListAdapter
import com.omoolen.omooroid.home.fragments.one.event.EventViewPagerAdapter
import com.omoolen.omooroid.home.fragments.one.event.LastestEventViewPagerAdapter
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationByUser
import com.omoolen.omooroid.home.fragments.one.newItem.NewListAdapter
import com.omoolen.omooroid.home.fragments.one.recommend.SeasonListAdapter
import com.omoolen.omooroid.home.fragments.one.recommend.SituationListAdapter
import com.omoolen.omooroid.home.fragments.one.tip.TipListAdapter
import com.omoolen.omooroid.home.fragments.two.TwoHomeFragment
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandAdapter
import com.omoolen.omooroid.search.SearchActivity
import com.omoolen.omooroid.search.search_result.SearchResultActivity
import com.omoolen.omooroid.util.VerticalItemDecorator


class OneHomeFragment : Fragment() {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private var _binding: FragmentHomeOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val oneHomeViewModel: OneHomeViewModel by activityViewModels()
    private lateinit var situLayoutManager : RecyclerView.LayoutManager
    private lateinit var seasonLayoutManager : RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeOneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        initLayout()
        setClickListener()

        oneHomeViewModel.getHome()

        setCuratingAdapter()
        setCuratingObserve()

        setRecommend1Adapter()
        setRecommend1Observe()

        setRecommend2Adapter()
        setRecommend2Observe()

        setEventAdapter()
        setEventObserve()
        setEventIndicator()

        setAdAdapter()
        setAdObserve()
        setAdIndicator()

        setTipAdapter()
        setTipObserve()

        setNewAdapter()
        setNewObserve()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.rvHomeRecommend.addItemDecoration(HorizontalItemDecorator(12, 2, requireContext()))
        binding.rvHomeRecommend.addItemDecoration(VerticalItemDecorator(40, requireContext()))

        //binding.rvHomeSeason.addItemDecoration(HorizontalItemDecorator(12, 2, requireContext()))
        binding.rvHomeSeason.addItemDecoration(VerticalItemDecorator(40, requireContext()))

    }

    //adapter()도 안에 있는거, 서버용 data class로 바꾸기.
    //adapter랑 observe 바꾸기 전에 먼저 각 ltem~.xml에 가서 dataBinding 객체 명 부터 바꾸기.

    private fun initLayout() {
        situLayoutManager = GridLayoutManager(requireContext(), 2)
        situLayoutManager = object  : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                lp?.width = ((width) / spanCount)
                return true
            }
        }
        binding.rvHomeRecommend.setHasFixedSize(true)
        binding.rvHomeRecommend.layoutManager = situLayoutManager

        seasonLayoutManager = GridLayoutManager(requireContext(), 2)
        seasonLayoutManager = object  : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                lp?.width = ((width) / spanCount)
                return true
            }
        }
        binding.rvHomeSeason.setHasFixedSize(true)
        binding.rvHomeSeason.layoutManager = seasonLayoutManager
    }

    private fun setCuratingAdapter(){
        val curatingListAdapter = CuratingListAdapter()
        curatingListAdapter.setItemClickListener(object: CuratingListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val rbu :RecommendationByUser = oneHomeViewModel.recommendationByUserList.get(position)
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("itemId", rbu.id)
                startActivity(intent)
            }
        })

        binding.rvHomeCurating.adapter = curatingListAdapter
    }
    private fun setCuratingObserve(){
        oneHomeViewModel.recommendationByUserList.observe(viewLifecycleOwner){
            curatingList -> with(binding.rvHomeCurating.adapter as CuratingListAdapter){
                setCurating(curatingList)
            }
        }
    }


    //상황별
    private fun setRecommend1Adapter(){
        binding.rvHomeRecommend.adapter = SituationListAdapter()
    }

    private fun setRecommend1Observe() {
        oneHomeViewModel.recommendationBySituationList.observe(viewLifecycleOwner) { recommendList ->
            with(binding.rvHomeRecommend.adapter as SituationListAdapter) {
                setRecommend(recommendList)
            }
        }
    }

    //계절별
    private fun setRecommend2Adapter(){
        binding.rvHomeSeason.adapter = SeasonListAdapter()
    }

    private fun setRecommend2Observe() {
        oneHomeViewModel.recommendationBySeasonList.observe(viewLifecycleOwner) { tempList ->
            with(binding.rvHomeSeason.adapter as SeasonListAdapter) {
                setRecommend(tempList)
            }
        }
    }

    //guide
    private fun setTipAdapter(){
        binding.rvHomeTip.adapter = TipListAdapter()
    }

    private fun setTipObserve() {
        oneHomeViewModel.guideLists.observe(viewLifecycleOwner) { tipList ->
            with(binding.rvHomeTip.adapter as TipListAdapter) {
                setTip(tipList)
            }
        }
    }

    private fun setNewAdapter(){
        binding.rvHomeNew.adapter = NewListAdapter()
    }

    private fun setNewObserve() {
        oneHomeViewModel.newItemList.observe(viewLifecycleOwner) { newList ->
            with(binding.rvHomeNew.adapter as NewListAdapter) {
                setNewItem(newList)
            }
        }
    }



    private fun setEventAdapter(){
        binding.vpHomeEvent.adapter = EventViewPagerAdapter()
    }

    private fun setEventObserve(){
        oneHomeViewModel.deadlineEventList.observe(viewLifecycleOwner){ eventList ->
            with(binding.vpHomeEvent.adapter as EventViewPagerAdapter){
                setEvent(eventList)
            }
        }
    }
    private fun setEventIndicator() {
        TabLayoutMediator(binding.tabHomeEvent, binding.vpHomeEvent) { tab, position -> }.attach()
    }

    private fun setAdAdapter(){
        binding.vpHomeAd.adapter = LastestEventViewPagerAdapter()
    }

    private fun setAdObserve(){
        oneHomeViewModel.lastestEventList.observe(viewLifecycleOwner){ adList ->
            with(binding.vpHomeAd.adapter as LastestEventViewPagerAdapter){
                setLastestEvent(adList)
            }
        }
    }

    private fun setAdIndicator() {
        TabLayoutMediator(binding.tabHomeAd, binding.vpHomeAd) { tab, position -> }.attach()
    }


    private fun setClickListener(){

        binding.tvOneSearch.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }

        binding.clHomeCuratingMore.setOnClickListener{

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.nav_host_home, TwoHomeFragment()
                    .apply {
                        arguments = Bundle().apply {
                            putInt("setIdx", 1)
                        }
                    }, "home->foryou")
                ?.commit()

            (activity as HomeActivity).setBottomChecked(1)
        }

        binding.clHomeRecommendMore.setOnClickListener{

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.nav_host_home, TwoHomeFragment().apply {
                    arguments = Bundle().apply {
                        putInt("setIdx", 2)
                    }
                },"home->situ")
                ?.commit()

            (activity as HomeActivity).setBottomChecked(1)
        }


        binding.clHomeSeasonMore.setOnClickListener{

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.nav_host_home, TwoHomeFragment().apply {
                    arguments = Bundle().apply {
                        putInt("setIdx", 4)
                    }
                }, "home->saeson")
                ?.commit();

            (activity as HomeActivity).setBottomChecked(1)

        }

        binding.clHomeNewMore.setOnClickListener {

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.nav_host_home, TwoHomeFragment().apply {
                    arguments = Bundle().apply {
                        putInt("setIdx", 3)
                    }
                }, "home->saeson")
                ?.commit();

            (activity as HomeActivity).setBottomChecked(1)

        }


    }

}