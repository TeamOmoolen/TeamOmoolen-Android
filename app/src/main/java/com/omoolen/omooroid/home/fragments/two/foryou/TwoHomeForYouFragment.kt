package com.omoolen.omooroid.home.fragments.two.foryou

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.omoolen.omooroid.databinding.FragmentHomeTwoForyouBinding
import com.omoolen.omooroid.detail.DetailActivity
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySituation
import com.omoolen.omooroid.home.fragments.one.recommend.SituationListAdapter
import com.omoolen.omooroid.home.fragments.two.FindQuestionFragment
import com.omoolen.omooroid.home.fragments.two.FindSortPriceFragment
import com.omoolen.omooroid.home.fragments.two.TwoHomeViewModel
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator

class TwoHomeForYouFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeForYouFragment()
    }

    private var _binding: FragmentHomeTwoForyouBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val viewModel: TwoHomeViewModel by activityViewModels()
    private val fragmentViewModel: TwoHomeForYouViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoForyouBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        //데이터 setting
        setForYouAdapter()
        setForYouObserve()


        //정렬 클릭 시
        binding.ivForYouSort.setOnClickListener{
            val findSortPriceFragment = FindSortPriceFragment()

            findSortPriceFragment.setButtonClickListener(object: FindSortPriceFragment.OnButtonClickListener {
                override fun onLowPriceClicked() {
                    //여기서 정렬
                    Log.d("click", "low price")
                    fragmentViewModel.getForyou(1,"price","asc")
                }

                override fun onHighPriceClicked() {
                    // 여기서 정렬
                    Log.d("click", "high price")
                    fragmentViewModel.getForyou(1,"price","desc")
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
        val situationListAdapter = SituationListAdapter()
        situationListAdapter.setItemClickListener(object: SituationListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val rbsi :RecommendationBySituation = viewModel.forYouList.get(position)
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("itemId", rbsi.id)
                startActivity(intent)
            }
        })

        binding.rvFindForyou.adapter = situationListAdapter
    }

   private fun setForYouObserve(){
       viewModel.forYouList.observe(viewLifecycleOwner){
               recommendList -> with(binding.rvFindForyou.adapter as SituationListAdapter) {
                    setRecommend(recommendList)
             }
       }
   }

}