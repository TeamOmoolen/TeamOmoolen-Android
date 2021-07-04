package com.omoolen.omooroid.onboarding.fragments.three

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.FragmentOnboardThreeBinding
import com.omoolen.omooroid.onboarding.fragments.three.recycle.effect.EffectAdapter
import com.omoolen.omooroid.onboarding.fragments.three.recycle.effect.EffectInfo
import com.omoolen.omooroid.onboarding.fragments.three.recycle.period.PeriodAdapter
import com.omoolen.omooroid.onboarding.fragments.three.recycle.period.PeriodInfo
import com.omoolen.omooroid.util.VerticalItemDecorator


class ThreeOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardThreeBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: ThreeOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var effectAdapter: EffectAdapter
    private lateinit var effectLayoutManager: RecyclerView.LayoutManager
    private lateinit var periodAdapter: PeriodAdapter
    private lateinit var periodLayoutManager: RecyclerView.LayoutManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardThreeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        effectAdapter = EffectAdapter()
        periodAdapter = PeriodAdapter()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        effectInit()
        periodInit()
    }

    private fun effectInit() {
        binding.rvEffect.adapter = effectAdapter
        effectLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvEffect.layoutManager = effectLayoutManager
        binding.rvEffect.addItemDecoration(VerticalItemDecorator(10))

        effectAdapter.effectList.addAll(
            listOf<EffectInfo>(
                EffectInfo(effect = "근시"),
                EffectInfo(effect = "난시"),
                EffectInfo(effect = "다초점"),
                EffectInfo(effect = "없음")
                )
        )
        var effectArr = arrayOf(false,false,false,false)
        effectAdapter.setItemClickListener(object: EffectAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기
                effectArr[position] = !effectArr[position]
                v.isSelected = effectArr[position]
            }
        })
    }

    private fun periodInit() {
        binding.rvPeriod.adapter = periodAdapter
        periodLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvPeriod.layoutManager = periodLayoutManager
        binding.rvPeriod.addItemDecoration(VerticalItemDecorator(10))

//        val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, resources.displayMetrics).toInt()
//        val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 54f, resources.displayMetrics).toInt()

        periodAdapter.periodList.addAll(
            listOf<PeriodInfo>(
                PeriodInfo(period = "원데이"),
                PeriodInfo(period = "1주"),
                PeriodInfo(period = "2주"),
                PeriodInfo(period = "1개월"),
                PeriodInfo(period = "2~3개월"),
                PeriodInfo(period = "3~6개월"),
                PeriodInfo(period = "6개월 이상"),
                PeriodInfo(period = "없음")
                )
        )
        var periodArr = arrayOf(false,false,false,false,false,false,false,false)
        periodAdapter.setItemClickListener(object: PeriodAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기

                periodArr[position] = !periodArr[position]
                v.isSelected = periodArr[position]
            }
        })
    }


}