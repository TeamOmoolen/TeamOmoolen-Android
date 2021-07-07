package com.omoolen.omooroid.onboarding.fragments.three

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentOnboardThreeBinding
import com.omoolen.omooroid.onboarding.OnboardDatabase
import com.omoolen.omooroid.util.VerticalItemDecorator


class ThreeOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardThreeBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: ThreeOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var effectLayoutManager: RecyclerView.LayoutManager
    private lateinit var periodLayoutManager: RecyclerView.LayoutManager

    private var nextArr = arrayOf(-1,-1)
    private var onboardDatabase = OnboardDatabase()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardThreeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar()
        effectInit()
        periodInit()
        singleChoice()
        observeValue()
        nextBtn()
    }

    private fun progressBar() {
        val mProgressBar = binding.pbLoading
        mProgressBar.progress = 75
    }

    private fun observeValue(){
        viewModel.effect.observe(viewLifecycleOwner){ effect ->
            nextArr[0] = effect
            Toast.makeText(requireContext(),nextArr[0].toString()+"@"+nextArr[1].toString(),Toast.LENGTH_SHORT).show()
            binding.tvButton.isSelected = !(nextArr[0] < 0 || nextArr[1] < 0) //다음 버튼 활성화 판단
        }
        viewModel.period.observe(viewLifecycleOwner){ period ->
            nextArr[1] = period
            Toast.makeText(requireContext(),nextArr[0].toString()+"@"+nextArr[1].toString(),Toast.LENGTH_SHORT).show()
            binding.tvButton.isSelected = !(nextArr[0] < 0 || nextArr[1] < 0)
        }
    }

    private fun effectInit() {
        binding.rvEffect.adapter = viewModel.setEffectAdapter()
        effectLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvEffect.layoutManager = effectLayoutManager
        binding.rvEffect.addItemDecoration(VerticalItemDecorator(10))
    }

    private fun periodInit() {
        binding.rvPeriod.adapter = viewModel.setPeriodAdapter()
        periodLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvPeriod.layoutManager = periodLayoutManager
        binding.rvPeriod.addItemDecoration(VerticalItemDecorator(10))
    }

    private fun singleChoice() {
        binding.rvEffect.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) { }
                else viewModel.effectSingleChoice(rv,e)
                return false
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        binding.rvPeriod.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) { }
                else viewModel.periodSingleChoice(rv,e)
                return false
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun nextBtn() {
        binding.tvButton.setOnClickListener {
            if (binding.tvButton.isSelected) {
                //onboardDatabase에 정보 저장
                viewModel.effect.value?.let { effect ->
                    viewModel.period.value?.let { period ->
                        onboardDatabase.setOne(
                            effect,
                            period
                        )
                    }
                }
//              //온보딩2로 화면 전환
                Navigation.findNavController(binding.root).navigate(R.id.action_fragment_onboard_three_to_fragment_onboard_four)
            }
        }
    }
}