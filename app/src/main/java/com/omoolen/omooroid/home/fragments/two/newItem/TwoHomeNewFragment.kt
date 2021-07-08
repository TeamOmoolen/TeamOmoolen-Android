package com.omoolen.omooroid.home.fragments.two.newItem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omoolen.omooroid.R

class TwoHomeNewFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeNewFragment()
    }

    private lateinit var viewModel: TwoHomeNewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_two_new, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TwoHomeNewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}