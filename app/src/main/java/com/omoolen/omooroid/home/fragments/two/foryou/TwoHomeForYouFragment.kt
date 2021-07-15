package com.omoolen.omooroid.home.fragments.two.foryou

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.omoolen.omooroid.databinding.FragmentHomeTwoForyouBinding
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter
import com.omoolen.omooroid.home.fragments.two.FindQuestionFragment
import com.omoolen.omooroid.home.fragments.two.FindSortPriceFragment
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator

class TwoHomeForYouFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeForYouFragment()
    }

    private var _binding: FragmentHomeTwoForyouBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val viewModel: TwoHomeForYouViewModel  by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoForyouBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        //데이터 setting
        viewModel.setForYouList()
        setForYouAdapter()
        setForYouObserve()


        //정렬 클릭 시
        binding.ivForYouSort.setOnClickListener{
            val findSortPriceFragment = FindSortPriceFragment()

            findSortPriceFragment.setButtonClickListener(object: FindSortPriceFragment.OnButtonClickListener {
                override fun onLowPriceClicked() {
                    //여기서 정렬
                    Log.d("click", "low price")
                }

                override fun onHighPriceClicked() {
                  // 여기서 정렬
                    Log.d("click", "high price")
                }
            })
            findSortPriceFragment.show(childFragmentManager, "CustomDialog")
        }

        binding.ivFindQuestion1.setOnClickListener{
            val findQuestionFragment = FindQuestionFragment(1)
            findQuestionFragment.show(childFragmentManager, "CustomDialog2")

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFindForyou.addItemDecoration(HorizontalItemDecorator(12,2, requireContext()))
        binding.rvFindForyou.addItemDecoration(VerticalItemDecorator(36, requireContext()))

    }

    private fun setForYouAdapter(){
        binding.rvFindForyou.adapter = RecommendListAdapter()
    }

    private fun setForYouObserve() {
        viewModel.foryouList.observe(viewLifecycleOwner) { foryouList ->
            with(binding.rvFindForyou.adapter as RecommendListAdapter) {
                  setRecommend(foryouList)
            }
        }
    }


}