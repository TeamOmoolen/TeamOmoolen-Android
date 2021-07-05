package com.omoolen.omooroid.home.fragments.two

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.omoolen.omooroid.databinding.FragmentHomeTwoBinding
import com.omoolen.omooroid.home.fragments.two.tabs.TwoHomeFirstTabFragment
import com.omoolen.omooroid.home.fragments.two.tabs.TwoHomeSecondTabFragment
import com.omoolen.omooroid.home.fragments.two.tabs.TwoHomeThirdTabFragment
import com.omoolen.omooroid.home.fragments.two.tabs.TwoHomeViewPagerAdapter


class TwoHomeFragment : Fragment() {
    private var _binding: FragmentHomeTwoBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val homeViewModel: TwoHomeViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = TwoHomeViewPagerAdapter(requireActivity())
        // 3개의 Fragment Add
        pagerAdapter.addFragment(TwoHomeFirstTabFragment())
        pagerAdapter.addFragment(TwoHomeSecondTabFragment())
        pagerAdapter.addFragment(TwoHomeThirdTabFragment())
        // Adapter
        binding.viewPagerHomeTwo.adapter = pagerAdapter

        binding.viewPagerHomeTwo.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        // TabLayout attach
        TabLayoutMediator(binding.tabLayoutHomeTwo, binding.viewPagerHomeTwo) { tab, position ->
            tab.text = "Tab ${position+1}"
        }.attach()
    }
}