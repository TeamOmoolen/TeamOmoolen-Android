package com.omoolen.omooroid.home.fragments.one

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySeason
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySituation
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationByUser
import com.omoolen.omooroid.home.fragments.one.newItem.NewListAdapter
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter
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
    private val binding get() = _binding ?: error("View??? ???????????? ?????? binding??? ??????????????? ???????????????.")

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

    //adapter()??? ?????? ?????????, ????????? data class??? ?????????.
    //adapter??? observe ????????? ?????? ?????? ??? ltem~.xml??? ?????? dataBinding ?????? ??? ?????? ?????????.

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


    //?????????
    private fun setRecommend1Adapter(){
        val situationListAdapter = SituationListAdapter()
        situationListAdapter.setItemClickListener(object: SituationListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val rbsi :RecommendationBySituation = oneHomeViewModel.recommendationBySituationList.get(position)
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("itemId", rbsi.id)
                startActivity(intent)
            }
        })

        binding.rvHomeRecommend.adapter = situationListAdapter
    }

    override fun onStart() {
        super.onStart()
        oneHomeViewModel.situation.observe(viewLifecycleOwner) {
            if(oneHomeViewModel.situation.value.equals("??????")) {
                binding.tvHomeRecommend.text = oneHomeViewModel.situation.value + "?????? ?????? ?????? ??????"
            }
            else {
                binding.tvHomeRecommend.text  = oneHomeViewModel.situation.value + "?????? ?????? ?????? ??????"
            }
        }
        oneHomeViewModel.userName.observe(viewLifecycleOwner) {
            binding.tvHomeCurating.text = oneHomeViewModel.userName.value + "??? ??? ?????? ?????????????"
        }

    }
    private fun setRecommend1Observe() {
        oneHomeViewModel.recommendationBySituationList.observe(viewLifecycleOwner) { recommendList ->
            with(binding.rvHomeRecommend.adapter as SituationListAdapter) {
                setRecommend(recommendList)
            }
        }
    }

    //?????????
    private fun setRecommend2Adapter(){
        val seasonListAdapter = SeasonListAdapter()
        seasonListAdapter.setItemClickListener(object: SeasonListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Log.d("SEASON_ITEM_VP", "${v.id}/${position}")
                val rbs :RecommendationBySeason = oneHomeViewModel.recommendationBySeasonList.get(position)
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("itemId", rbs.id)
                startActivity(intent)
            }
        })

        binding.rvHomeSeason.adapter = seasonListAdapter
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
        val newListAdapter = NewListAdapter()
        newListAdapter.setItemClickListener(object : NewListAdapter.OnItemClickListener{
            @Override
            override fun onClick(v: View, position: Int) {
                Log.d("NEW_ITEM_VP", "${v.id}/${position}")
            }
        })
        binding.rvHomeNew.adapter = newListAdapter
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