package com.omoolen.omooroid.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.omoolen.omooroid.databinding.FragmentDetailBinding
import com.omoolen.omooroid.detail.popular.DetailNewListAdapter
import com.omoolen.omooroid.detail.recommend.DetailRecommendListAdapter
import com.omoolen.omooroid.home.fragments.one.LensColorListAdapter

class DetailFragment : Fragment() {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private lateinit var viewPagerAdapter: DetailViewPagerAdapter

    private val detailViewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        detailViewModel.setDetailImageList()
        setDetailAdapter()
        setDetailObserve()
        setDetailIndicator()

        detailViewModel.setDetailRecommendList()
        setDetailRecommendAdapter()
        setDetailRecommendObserve()

        detailViewModel.setDetailNewList()
        setDetailNewAdapter()
        setDetailNewObserve()

        detailViewModel.setDetailLensColorList()
        setDetailLensColorAdapter()
        setDetailLensColorObserve()




        return binding.root
    }

    // 디테일뷰 메인 이미지 탭
    private fun setDetailAdapter() {
        binding.vpDetailMainImage.adapter = DetailViewPagerAdapter()
    }

    private fun setDetailObserve() {
        detailViewModel.detailImageList.observe(viewLifecycleOwner) { detailImageList ->
            with(binding.vpDetailMainImage.adapter as DetailViewPagerAdapter) {
                setDetailImage(detailImageList)
            }
        }
    }

    private fun setDetailIndicator() {
        TabLayoutMediator(
            binding.tlDetailMainImage,
            binding.vpDetailMainImage
        ) { tab, position -> }.attach()
    }


    // 디테일뷰 추천 리사이클러뷰
    private fun setDetailRecommendAdapter() {
        binding.rvDetailRecommend.adapter = DetailRecommendListAdapter()
    }

    private fun setDetailRecommendObserve() {
        detailViewModel.detailRecommendList.observe(viewLifecycleOwner) { detailRecommendList ->
            with(binding.rvDetailRecommend.adapter as DetailRecommendListAdapter) {
                setDetailRecommend(detailRecommendList)
            }
        }
    }


    // 디테일뷰 인기있는 신제품 리사이클러뷰
    private fun setDetailNewAdapter() {
        binding.rvDetailNew.adapter = DetailNewListAdapter()
    }

    private fun setDetailNewObserve() {
        detailViewModel.detailNewList.observe(viewLifecycleOwner) { detailNewList ->
            with(binding.rvDetailNew.adapter as DetailNewListAdapter) {
                setDetailNew(detailNewList)
            }
        }
    }

    // 디테일뷰 메인 렌즈 컬러 리사이클러뷰
    private fun setDetailLensColorAdapter() {
        binding.rvDetailLensColor.adapter = DetailLensColorListAdapter()
    }

    private fun setDetailLensColorObserve(){
        detailViewModel.detailLensColorList.observe(viewLifecycleOwner) { detailLensColorList ->
            with(binding.rvDetailLensColor.adapter as DetailLensColorListAdapter) {
                setColoring(detailLensColorList)
            }

        }
    }

}