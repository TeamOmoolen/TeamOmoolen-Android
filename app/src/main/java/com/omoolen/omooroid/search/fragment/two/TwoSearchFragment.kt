package com.omoolen.omooroid.search.fragment.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentSearchTwoBinding

class TwoSearchFragment : Fragment() {
    private var _binding: FragmentSearchTwoBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search_two, container, false)
    }

    fun newInstant() : TwoSearchFragment
    {
        val args = Bundle()
        val frag = TwoSearchFragment()
        frag.arguments = args
        return frag
    }
}