package com.omoolen.omooroid.home.fragments.two.newItem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentHomeTwoForyouBinding
import com.omoolen.omooroid.databinding.FragmentHomeTwoNewBinding
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter
import com.omoolen.omooroid.home.fragments.one.recommend.SituationListAdapter
import com.omoolen.omooroid.home.fragments.two.FindQuestionFragment
import com.omoolen.omooroid.home.fragments.two.FindSortPriceFragment
import com.omoolen.omooroid.home.fragments.two.TwoHomeViewModel
import com.omoolen.omooroid.home.fragments.two.foryou.TwoHomeForYouViewModel
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator

class TwoHomeNewFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeNewFragment()
    }

    private var _binding: FragmentHomeTwoNewBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val viewModel: TwoHomeViewModel by activityViewModels()
    private val fragmentViewModel: TwoHomeNewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoNewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getSuggestData()
        setForNewAdapter()
        setForNewObserve()

        //정렬 클릭 시
        binding.ivNewSort.setOnClickListener{
            val findSortPriceFragment = FindSortPriceFragment()

            findSortPriceFragment.setButtonClickListener(object: FindSortPriceFragment.OnButtonClickListener {
                override fun onLowPriceClicked() {
                    //여기서 정렬
                    Log.d("click", "low price")
                    fragmentViewModel.getNews(1,"price","asc")
                }

                override fun onHighPriceClicked() {
                    // 여기서 정렬
                    Log.d("click", "high price")
                    fragmentViewModel.getNews(1,"price","desc")

                }
            })
            findSortPriceFragment.show(childFragmentManager, "CustomDialog")
        }

        binding.ivFindQuestion3.setOnClickListener {
            val findQuestionFragment = FindQuestionFragment(3)
            findQuestionFragment.show(childFragmentManager, "CustomDialog2")
        }

        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFindNewItem.addItemDecoration(HorizontalItemDecorator(12,2, requireContext()))
        binding.rvFindNewItem.addItemDecoration(VerticalItemDecorator(36, requireContext()))

    }
    private fun setForNewAdapter(){
        binding.rvFindNewItem.adapter = SituationListAdapter()
    }

    private fun setForNewObserve(){
        viewModel.forNewList.observe(viewLifecycleOwner){
                recommendList -> with(binding.rvFindNewItem.adapter as SituationListAdapter) {
            setRecommend(recommendList)
        }
        }
    }
}