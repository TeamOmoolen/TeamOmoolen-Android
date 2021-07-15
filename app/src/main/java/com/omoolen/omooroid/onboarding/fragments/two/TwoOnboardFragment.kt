package com.omoolen.omooroid.onboarding.fragments.two

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentOnboardTwoBinding
import com.omoolen.omooroid.onboarding.OnboardDatabase
import com.omoolen.omooroid.onboarding.fragments.two.recycle.color.ColorAdapter
import com.omoolen.omooroid.onboarding.fragments.two.recycle.what.WhatAdapter
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator


class TwoOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardTwoBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: TwoOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var colorAdapter: ColorAdapter
    private lateinit var colorLayoutManager: RecyclerView.LayoutManager
    private lateinit var whatAdapter: WhatAdapter
    private lateinit var whatLayoutManager: RecyclerView.LayoutManager

    private lateinit var nextArr :Array<Int>

    private var onboardDatabase = OnboardDatabase()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardTwoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        whatAdapter = WhatAdapter()
        colorAdapter = ColorAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextArr = arrayOf(-1,-1)
        progressBar()
        whatInit()
        colorInit()
        observeWhat()
        nextBtn()
    }
    private fun progressBar() {
        val mProgressBar = binding.pbLoading
        mProgressBar.progress = 50
    }

    private fun observeWhat(){
        viewModel.whatChoice.observe(viewLifecycleOwner, {
            //다음 버튼 감지
            nextArr[0] = it.size
            Toast.makeText(requireContext(),nextArr[0].toString()+"/"+nextArr[1].toString(),Toast.LENGTH_SHORT).show()
            binding.tvButton.isSelected = nextArr[0] > 0 && nextArr[1] > 0
        })
        viewModel.colorChoice.observe(viewLifecycleOwner, {
            //다음 버튼 감지
            nextArr[1] = it.size
            Toast.makeText(requireContext(),nextArr[0].toString()+"/"+nextArr[1].toString(),Toast.LENGTH_SHORT).show()
            binding.tvButton.isSelected = nextArr[0] > 0 && nextArr[1] > 0
        })
    }

    private fun whatInit() {
        binding.rvWhat.adapter = viewModel.setWhatAdapter()
        whatLayoutManager = GridLayoutManager(requireContext(), 2)

        whatLayoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                // force size of viewHolder here, this will override layout_height and layout_width from xml
                lp.width = ((width - 50) / spanCount)
                //lp.height = lp.width
                return true
            }
        }
        binding.rvWhat.setHasFixedSize(true)
        binding.rvWhat.layoutManager = whatLayoutManager

        //binding.rvWhat.addItemDecoration(HorizontalItemDecorator(20, 2, requireContext()))
        binding.rvWhat.addItemDecoration(VerticalItemDecorator(10, requireContext()))
    }

    private fun colorInit() {
        binding.rvColor.adapter = viewModel.setColorAdapter()
        colorLayoutManager = GridLayoutManager(requireContext(), 2)
        colorLayoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                // force size of viewHolder here, this will override layout_height and layout_width from xml
                lp.width = ((width - 50) / spanCount)
                //lp.height = lp.width
                return true
            }
        }
        binding.rvColor.setHasFixedSize(true)
        binding.rvColor.layoutManager = colorLayoutManager

        //binding.rvColor.addItemDecoration(HorizontalItemDecorator(10, 2, requireContext()))
        binding.rvColor.addItemDecoration(VerticalItemDecorator(10, requireContext()))
    }

    private fun nextBtn() {
        binding.tvButton.setOnClickListener {
            //test
            if (binding.tvButton.isSelected) {
                //onboardDatabase에 정보 저장
                onboardDatabase.setTwo(viewModel.whatChoice,viewModel.colorChoice)
                for(c in 0 until viewModel.colorChoice.size())
                    Log.d("ONBOARD2_COLOR", viewModel.colorChoice[c].toString())
                //온보딩3로 화면 전환
                Navigation.findNavController(binding.root).navigate(R.id.action_fragment_onboard_two_to_fragment_onboard_three)
                //TODO : 온보딩1으로 넘어가는데 왜 이상해
                //viewModel.whatChoice.removeAll()
                //viewModel.colorChoice.removeAll()
            }
        }
    }

}