package com.omoolen.omooroid.onboarding.fragments.two

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentOnboardTwoBinding
import com.omoolen.omooroid.onboarding.fragments.one.recycle.age.AgeAdapter
import com.omoolen.omooroid.onboarding.fragments.one.recycle.gender.GenderAdapter
import com.omoolen.omooroid.onboarding.fragments.one.recycle.gender.GenderInfo
import com.omoolen.omooroid.onboarding.fragments.two.TwoOnboardViewModel
import com.omoolen.omooroid.onboarding.fragments.two.recycle.color.ColorAdapter
import com.omoolen.omooroid.onboarding.fragments.two.recycle.color.ColorInfo
import com.omoolen.omooroid.onboarding.fragments.two.recycle.what.WhatAdapter
import com.omoolen.omooroid.onboarding.fragments.two.recycle.what.WhatInfo
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
        whatInit()
        colorInit()
    }

    fun whatInit() {
        binding.rvWhat.adapter = whatAdapter
        whatLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWhat.layoutManager = whatLayoutManager

        binding.rvWhat.addItemDecoration(HorizontalItemDecorator(20))
        binding.rvWhat.addItemDecoration(VerticalItemDecorator(10))

        whatAdapter.whatList.addAll(
            listOf<WhatInfo>(
                WhatInfo(resourceId = R.drawable.ic_colorlens,name = "컬러렌즈"),
                WhatInfo(resourceId = R.drawable.ic_colorlens,name = "투명렌즈"),
                WhatInfo(resourceId = R.drawable.ic_colorlens,name = "코스프레/공막 렌즈"),
            )
        )
        var whatArr = arrayOf(false,false,false)
        whatAdapter.setItemClickListener(object: WhatAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기
                whatArr[position] = !whatArr[position]
                v.isSelected = whatArr[position]
            }
        })
    }

    fun colorInit() {
        binding.rvColor.adapter = colorAdapter
        colorLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvColor.layoutManager = colorLayoutManager

        binding.rvColor.addItemDecoration(HorizontalItemDecorator(10))
        binding.rvColor.addItemDecoration(VerticalItemDecorator(10))

        //TODO : 이미지로 달라고 부탁함
        colorAdapter.colorList.addAll(
            listOf<ColorInfo>(
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_second_gray, name = "투명"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_white, name = "블랙"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_white, name = "그레이"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_white, name = "초코"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_white, name = "그린"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_white, name = "브라운"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_white, name = "퍼플"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_white, name = "블루"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_white, name = "골드"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_white, name = "핑크"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_second_gray, name = "글리터"),
                ColorInfo(resourceId = R.drawable.ic_btn_transparent,colorId = R.color.om_third_gray, name = "기타")
                )
        )
        var colorArr = arrayOf(false,false,false,false,false,false,false,false,false,false,false,false)
        colorAdapter.setItemClickListener(object: ColorAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기
                colorArr[position] = !colorArr[position]
                v.isSelected = colorArr[position]

            }
        })
    }

}