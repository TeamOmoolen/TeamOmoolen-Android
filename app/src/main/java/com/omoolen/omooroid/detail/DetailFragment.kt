package com.omoolen.omooroid.detail

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.omoolen.omooroid.databinding.FragmentDetailBinding
import com.omoolen.omooroid.detail.popular.DetailNewListAdapter
import com.omoolen.omooroid.detail.recommend.DetailRecommendListAdapter
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter
import com.omoolen.omooroid.util.VerticalItemDecoration

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val detailViewModel: DetailViewModel by activityViewModels()
    private lateinit var mContext: Context

    private lateinit var detailNewLayoutManager: RecyclerView.LayoutManager
    private lateinit var detailRecommendLayoutManager: RecyclerView.LayoutManager

    val detailViewPagerAdapter = DetailViewPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailNewInit()
        detailRecommendInit()
        detailImageInit()

    }

    private fun detailImageInit() {
        detailViewModel.setDetailImageList()
        binding.vpDetailMainImage.adapter = detailViewPagerAdapter
        binding.vpDetailMainImage.setCurrentItem(1, true)
        binding.detailDotsIndicator.setViewPager2(binding.vpDetailMainImage)


//        TabLayoutMediator(binding.tlDetailMainImage, binding.vpDetailMainImage) {tab, position ->}.attach()

        detailViewModel.detailImageList.observe(viewLifecycleOwner){detailImageList ->
            with(binding.vpDetailMainImage.adapter as DetailViewPagerAdapter){
                setDetailImage(detailImageList)
            }
        }

    }

    private fun detailNewInit() {
        binding.rvDetailNew.adapter = detailViewModel.setDetailNewListAdapter()
        detailNewLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        binding.rvDetailNew.layoutManager = detailNewLayoutManager
    }

    private fun detailRecommendInit() {
        binding.rvDetailRecommend.adapter = detailViewModel.setDetailRecommendAdapter()
        detailRecommendLayoutManager = GridLayoutManager(requireContext(), 2)
        detailRecommendLayoutManager = object :GridLayoutManager(requireContext(),2){
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                lp?.width = ((width - 52)/spanCount)
                return true
            }
        }
        binding.rvDetailRecommend.setHasFixedSize(true)
        binding.rvDetailRecommend.addItemDecoration(VerticalItemDecoration(40))
        binding.rvDetailRecommend.layoutManager = detailRecommendLayoutManager
    }

}