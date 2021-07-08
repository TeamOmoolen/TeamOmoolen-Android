package com.omoolen.omooroid.home.fragments.two.situation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omoolen.omooroid.R

class TwoHomeSituFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeSituFragment()
    }

    private lateinit var viewModel: TwoHomeSituViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_two_situ, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TwoHomeSituViewModel::class.java)
        // TODO: Use the ViewModel
    }

}