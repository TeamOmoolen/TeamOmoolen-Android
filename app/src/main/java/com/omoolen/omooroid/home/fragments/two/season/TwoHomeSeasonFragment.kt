package com.omoolen.omooroid.home.fragments.two.season

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omoolen.omooroid.R

class TwoHomeSeasonFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeSeasonFragment()
    }

    private lateinit var viewModel: TwoHomeSeasonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_two_season, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TwoHomeSeasonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}