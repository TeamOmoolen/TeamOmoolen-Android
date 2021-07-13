package com.omoolen.omooroid.home.fragments.two.newItem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentHomeTwoForyouBinding
import com.omoolen.omooroid.databinding.FragmentHomeTwoNewBinding
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter
import com.omoolen.omooroid.home.fragments.two.foryou.TwoHomeForYouViewModel

class TwoHomeNewFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeNewFragment()
    }

    private var _binding: FragmentHomeTwoNewBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val viewModel: TwoHomeNewViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoNewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.setNewList()
        setNewAdapter()
        setNewObserve()

        return binding.root    }

    private fun setNewAdapter(){
        binding.rvFindNewItem.adapter = RecommendListAdapter()
    }

    private fun setNewObserve() {
        viewModel.newList.observe(viewLifecycleOwner) { newList ->
            with(binding.rvFindNewItem.adapter as RecommendListAdapter) {
                setRecommend(newList)
            }
        }
    }
}