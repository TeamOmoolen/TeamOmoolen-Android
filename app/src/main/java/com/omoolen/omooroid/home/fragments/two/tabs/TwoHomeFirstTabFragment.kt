package com.omoolen.omooroid.home.fragments.two.tabs

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.omoolen.omooroid.databinding.FragmentTwoHomeFirstTabBinding
import com.omoolen.omooroid.home.fragments.two.TwoHomeViewModel

class TwoHomeFirstTabFragment : Fragment() {

    private var _binding : FragmentTwoHomeFirstTabBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val homeViewModel: TwoHomeViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTwoHomeFirstTabBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}