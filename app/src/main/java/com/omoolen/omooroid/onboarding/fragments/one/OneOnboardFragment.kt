package com.omoolen.omooroid.onboarding.fragments.one

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.omoolen.omooroid.databinding.FragmentOnboardOneBinding
import com.omoolen.omooroid.onboarding.fragments.one.OneOnboardViewModel


class OneOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: OneOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context


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
        buttonClickEvent()
    }

    private fun buttonClickEvent(){
        var stateArr = arrayOf(false,false,false,false,false,false)
        binding.clFemale.setOnClickListener {
            Log.d("ONEONBOARD","female clicked")
            stateArr[0] = !stateArr[0]
            binding.tv10.isSelected = stateArr[2]
        }
        binding.clMale.setOnClickListener {
            stateArr[1] = !stateArr[1]
            binding.tv10.isSelected = stateArr[2]
        }
        binding.tv10.setOnClickListener {
            stateArr[2] = !stateArr[2]
            binding.tv10.isSelected = stateArr[2]
        }
    }

}