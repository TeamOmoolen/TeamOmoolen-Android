package com.omoolen.omooroid.home.fragments.two

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentHomeTwoBinding
import com.omoolen.omooroid.home.HomeActivity
import com.omoolen.omooroid.home.fragments.one.OneHomeFragment
import com.omoolen.omooroid.home.fragments.two.foryou.TwoHomeForYouFragment
import com.omoolen.omooroid.home.fragments.two.newItem.TwoHomeNewFragment
import com.omoolen.omooroid.home.fragments.two.season.TwoHomeSeasonFragment
import com.omoolen.omooroid.home.fragments.two.situation.TwoHomeSituFragment
import com.omoolen.omooroid.search.SearchActivity


class TwoHomeFragment : Fragment() {
    private var _binding: FragmentHomeTwoBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val homeViewModel: TwoHomeViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private  var idx : Int? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        setClickListener()


        homeViewModel.getSuggestData()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = PagerFragmentStateAdapter(requireActivity())
        pagerAdapter.addFragment(TwoHomeForYouFragment())
        pagerAdapter.addFragment(TwoHomeSituFragment())
        pagerAdapter.addFragment(TwoHomeNewFragment())
        pagerAdapter.addFragment(TwoHomeSeasonFragment())

        binding.vpHomeTwo.adapter = pagerAdapter

        TabLayoutMediator(binding.findTabLayout, binding.vpHomeTwo) { tab, position ->

                when (position) {
                    0 -> {
                        homeViewModel.tabItem2.observe(viewLifecycleOwner) {
                            tab.text = homeViewModel.tabItem1
                        }
                    }
                    1 -> {
                        homeViewModel.tabItem2.observe(viewLifecycleOwner){
                            tab.text = homeViewModel.tabItem2.value
                        }
                    }
                    2 -> {
                        homeViewModel.tabItem2.observe(viewLifecycleOwner) {
                            tab.text = homeViewModel.tabItem3
                        }
                    }
                    3 -> {
                        homeViewModel.tabItem4.observe(viewLifecycleOwner){
                            tab.text = homeViewModel.tabItem4.value
                        }
                    }
                }
        }.attach()


        idx = arguments?.getInt("setIdx")
        Log.d("**********TAB_IDX", idx.toString())
        if(idx != null) {
            val tabLayout = binding.findTabLayout
            val tab = tabLayout.getTabAt(idx!! - 1)
            tab!!.select()
            binding.vpHomeTwo.setCurrentItem(idx!! - 1, false)
        }


    }

    private fun setClickListener() {

        binding.tvTwoSearch.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }
        
        binding.ivTwoLogo.setOnClickListener{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(
                    R.id.nav_host_home, OneHomeFragment(), "home->foryou")
                ?.commit()

            (activity as HomeActivity).setBottomChecked(0)
        }
    }

}

