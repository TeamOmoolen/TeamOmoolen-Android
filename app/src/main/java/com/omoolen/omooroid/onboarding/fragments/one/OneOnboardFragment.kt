package com.omoolen.omooroid.onboarding.fragments.one

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentOnboardOneBinding
import com.omoolen.omooroid.onboarding.fragments.four.BrandAdapter
import com.omoolen.omooroid.onboarding.fragments.four.BrandInfo


class OneOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: OneOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context
    private lateinit var ageAdapter: AgeAdapter

    private lateinit var rAgeAdapter: RAgeAdapter
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
        //buttonClickEvent()
        //setAdapter()
        ageInit()
    }

    fun ageInit() {
        rAgeAdapter = RAgeAdapter()
        binding.rvAge.adapter = rAgeAdapter
        ageLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvAge.layoutManager = ageLayoutManager

        rAgeAdapter.ageList.addAll(
            listOf<AgeInfo>(
                AgeInfo(
                    age = "10대"
                ),
                AgeInfo(
                    age = "20대"
                ),
                AgeInfo(
                    age = "30대"
                ),
                AgeInfo(
                    age = "40대 이상"
                )
            )
        )

        rAgeAdapter.setItemClickListener(object: RAgeAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                v.isSelected = true
            }
        })
    }

//    private fun setAdapter(){
//        ageAdapter.addItem(AgeInfo("10대"))
//        ageAdapter.addItem(AgeInfo("20대"))
//        ageAdapter.addItem(AgeInfo("30대"))
//        ageAdapter.addItem(AgeInfo("40대 이상"))
//        binding.gvAge.adapter = ageAdapter
//    }
//
//    private fun buttonClickEvent(){
//        var stateArr = arrayOf(false,false,false,false)
//        binding.gvAge.onItemClickListener = OnItemClickListener { parent, view, position, id ->
//            val item: AgeInfo = ageAdapter.getItem(position) as AgeInfo
//            //Toast.makeText(requireContext(), "선택 :" + item.getName(), Toast.LENGTH_SHORT).show()
//            if(position==0)
//            view.isSelected = true
//        }
////        var stateArr = arrayOf(false,false,false,false,false,false)
////        binding.clFemale.setOnClickListener {
////            Log.d("ONEONBOARD","female clicked")
////            stateArr[0] = !stateArr[0]
////            binding.tv10.isSelected = stateArr[2]
////        }
////        binding.clMale.setOnClickListener {
////            stateArr[1] = !stateArr[1]
////            binding.tv10.isSelected = stateArr[2]
////        }
////        binding.tv10.setOnClickListener {
////            stateArr[2] = !stateArr[2]
////            binding.tv10.isSelected = stateArr[2]
////        }
//    }

}