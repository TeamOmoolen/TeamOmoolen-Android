package com.omoolen.omooroid.onboarding.fragments.one

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
import com.omoolen.omooroid.databinding.FragmentOnboardOneBinding
import com.omoolen.omooroid.onboarding.fragments.one.recycle.age.AgeAdapter
import com.omoolen.omooroid.onboarding.fragments.one.recycle.age.AgeInfo
import com.omoolen.omooroid.onboarding.fragments.one.recycle.gender.GenderAdapter
import com.omoolen.omooroid.onboarding.fragments.one.recycle.gender.GenderInfo
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator


class OneOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: OneOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var ageAdapter: AgeAdapter
    private lateinit var ageLayoutManager: RecyclerView.LayoutManager
    private lateinit var genderAdapter: GenderAdapter
    private lateinit var genderLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardOneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        ageAdapter = AgeAdapter()
        genderAdapter = GenderAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ageInit()
        genderInit()
        allSelected()
    }

    fun genderInit() {
        binding.rvGender.adapter = genderAdapter
        genderLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvGender.layoutManager = genderLayoutManager

        binding.rvGender.addItemDecoration(HorizontalItemDecorator(20))


        genderAdapter.genderList.addAll(
            listOf<GenderInfo>(
                GenderInfo(resourceId = R.drawable.ic_colorlens,name = "여성"),
                GenderInfo(resourceId = R.drawable.onboard_female_selector,name = "남성")
            )
        )
        var genderArr = arrayOf(false,false)
        genderAdapter.setItemClickListener(object: GenderAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기
                genderArr[position] = !genderArr[position]
                v.isSelected = genderArr[position]

            }
        })
    }

    private fun ageInit() {
        binding.rvAge.adapter = ageAdapter
        ageLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvAge.layoutManager = ageLayoutManager
        binding.rvAge.addItemDecoration(VerticalItemDecorator(10))

        ageAdapter.ageList.addAll(
            listOf<AgeInfo>(
                AgeInfo(age = "10대"),
                AgeInfo(age = "20대"),
                AgeInfo(age = "30대"),
                AgeInfo(age = "40대 이상")
            )
        )
        var ageArr = arrayOf(false,false,false,false)
        ageAdapter.setItemClickListener(object: AgeAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기
                ageArr[position] = !ageArr[position]
                v.isSelected = ageArr[position]
            }
        })
    }

    private fun allSelected(){
        //TODO : 다 선택되면 true로 바꾸기
        //binding.tvButton.isSelected = true
    }



}