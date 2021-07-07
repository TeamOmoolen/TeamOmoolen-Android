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

        return binding.root
    }

    private fun setDetailAdapter(){
        binding.vpDetailMainImage.adapter = DetailViewPagerAdapter()
    }

    private fun setDetailObserve(){
        detailViewModel.detailImageList.observe(viewLifecycleOwner){ detailImageList ->
            with(binding.vpDetailMainImage.adapter as DetailViewPagerAdapter){
                setDetailImage(detailImageList)
            }
        }
    }

    private fun setDetailIndicator() {
        TabLayoutMediator(binding.tabDetailMainImage, binding.vpDetailMainImage) { tab, position -> }.attach()
    }

}