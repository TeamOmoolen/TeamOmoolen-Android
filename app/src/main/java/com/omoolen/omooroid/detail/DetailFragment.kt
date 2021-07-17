package com.omoolen.omooroid.detail

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.omoolen.omooroid.databinding.FragmentDetailBinding
import com.omoolen.omooroid.detail.detailApi.Popular
import com.omoolen.omooroid.detail.detailApi.Suggest
import com.omoolen.omooroid.detail.popular.DetailPopularListAdapter
import com.omoolen.omooroid.detail.recommend.DetailRecommendListAdapter
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySeason
import com.omoolen.omooroid.home.fragments.one.recommend.SeasonListAdapter
import com.omoolen.omooroid.util.BindingAdapters

class DetailFragment (private val itemId : String): Fragment() {
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
        //detailViewModel.getDetailData("60efdf8e3e4ecf590a92403b")
        detailViewModel.getDetailData(itemId)
        Log.d("******ITEM_ID", itemId)

        setDetailAdapter()
        setDetailObserve()

        setDetailRecommendAdapter()
        setDetailRecommendObserve()

        setDetailNewAdapter()
        setDetailNewObserve()

        setDetailLensColorAdapter()
        setDetailLensColorObserve()


        //TODO : 상품 아이디값 넣기
        //detailViewModel.getDetailData("60efdf8e3e4ecf590a92403b")
        //detailViewModel.getDetailData(itemId)

        return binding.root
    }


    override fun onStart() {
        super.onStart()



        detailViewModel.detail.observe(viewLifecycleOwner){
            binding.tvDetailBrand.text = detailViewModel.detail.value?.brand
            binding.tvDetailName.text = detailViewModel.detail.value?.name
            BindingAdapters.setDiameter(binding.tvDetailDiameterValue, detailViewModel.detail.value!!.diameter)
            BindingAdapters.setMonth(binding.tvDetailPeriodValue, detailViewModel.detail.value!!.changeCycleMinimum,
                detailViewModel.detail.value!!.changeCycleMaximum)
            BindingAdapters.setPrice2(binding.tvDetailPrice, detailViewModel.detail.value!!.price)
           BindingAdapters.setSrcFromUrl(binding.ivOneDetailLen, detailViewModel.detail.value!!.imageURL.get(0))

        }

    }

    // 디테일뷰 메인 이미지 탭
    private fun setDetailAdapter() {
        binding.vpDetailMainImage.adapter = DetailViewPagerAdapter()
    }

    private fun setDetailObserve() {
        detailViewModel.detailImgList.observe(viewLifecycleOwner) { detailImageList ->
            with(binding.vpDetailMainImage.adapter as DetailViewPagerAdapter) {
                setDetailImage(detailImageList)
            }
        }
    }


    // 디테일뷰 추천 리사이클러뷰
    private fun setDetailRecommendAdapter() {
        val detailRecommendListAdapter = DetailRecommendListAdapter()
        detailRecommendListAdapter.setItemClickListener(object: DetailRecommendListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val rbs : Suggest = detailViewModel.suggestList.get(position)
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("itemId", rbs.id)
                startActivity(intent)
            }
        })

        binding.rvDetailRecommend.adapter = detailRecommendListAdapter
    }

    private fun setDetailRecommendObserve() {
        detailViewModel.suggestList.observe(viewLifecycleOwner) { detailRecommendList ->
            with(binding.rvDetailRecommend.adapter as DetailRecommendListAdapter) {
                setDetailRecommend(detailRecommendList)
            }
        }
    }


    // 디테일뷰 인기있는 신제품 리사이클러뷰
    private fun setDetailNewAdapter() {
        val detailPopularListAdapter = DetailPopularListAdapter()
        detailPopularListAdapter.setItemClickListener(object: DetailPopularListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val rbs : Popular = detailViewModel.popularList.get(position)
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("itemId", rbs.id)
                startActivity(intent)
            }
        })

        binding.rvDetailNew.adapter = detailPopularListAdapter
        binding.vpDetailMainImage.setCurrentItem(1, true)
        binding.detailDotsIndicator.setViewPager2(binding.vpDetailMainImage)
    }

    private fun setDetailNewObserve() {
        detailViewModel.popularList.observe(viewLifecycleOwner) { detailNewList ->
            with(binding.rvDetailNew.adapter as DetailPopularListAdapter) {
                setDetailNew(detailNewList)
            }
        }
    }

    // 디테일뷰 메인 렌즈 컬러 리사이클러뷰
    private fun setDetailLensColorAdapter() {
        binding.rvDetailLensColor.adapter = DetailLensColorListAdapter()
    }

    private fun setDetailLensColorObserve(){
        detailViewModel.colorDetailList.observe(viewLifecycleOwner) { detailLensColorList ->
            with(binding.rvDetailLensColor.adapter as DetailLensColorListAdapter) {
                setColoring(detailLensColorList)
            }

        }
    }

}