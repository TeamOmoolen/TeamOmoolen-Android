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
import com.omoolen.omooroid.databinding.FragmentOnboardOneBinding


class OneOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: OneOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context
    private lateinit var ageAdapter: AgeAdapter

    private lateinit var ageLayoutManager: RecyclerView.LayoutManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardOneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        ageAdapter = AgeAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ageInit()
    }

    fun ageInit() {
        ageAdapter = AgeAdapter()
        binding.rvAge.adapter = ageAdapter
        ageLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvAge.layoutManager = ageLayoutManager

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
                // 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기
                ageArr[position] = !ageArr[position]
                v.isSelected = ageArr[position]
            }
        })
    }


}