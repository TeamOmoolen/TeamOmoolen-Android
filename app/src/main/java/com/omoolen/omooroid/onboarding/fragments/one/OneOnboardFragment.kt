package com.omoolen.omooroid.onboarding.fragments.one

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.FragmentOnboardOneBinding
import com.omoolen.omooroid.onboarding.fragments.one.recycle.age.AgeAdapter
import com.omoolen.omooroid.onboarding.fragments.one.recycle.age.AgeInfo
import com.omoolen.omooroid.onboarding.fragments.one.recycle.gender.GenderAdapter
import com.omoolen.omooroid.onboarding.fragments.three.recycle.effect.EffectAdapter
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator


class OneOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: OneOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var ageLayoutManager: RecyclerView.LayoutManager
    private lateinit var genderLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardOneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ageInit()
        genderInit()
        singleChoice()
    }

    private fun genderInit() {
        binding.rvGender.adapter = viewModel.setGenderAdapter()
        genderLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvGender.layoutManager = genderLayoutManager
        binding.rvGender.addItemDecoration(HorizontalItemDecorator(20))
    }

    private fun ageInit() {
        binding.rvAge.adapter = viewModel.setAgeAdapter()
        ageLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvAge.layoutManager = ageLayoutManager
        binding.rvAge.addItemDecoration(VerticalItemDecorator(10))
    }

    private fun singleChoice() {
        binding.rvGender.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) { }
                else viewModel.singleChoice(rv,e)
                return false
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        binding.rvAge.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) { }
                else viewModel.singleChoice(rv,e)
                return false
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }


}