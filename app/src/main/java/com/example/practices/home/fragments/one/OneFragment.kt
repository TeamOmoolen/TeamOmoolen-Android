package com.example.practices.home.fragments.one

import android.content.Intent.getIntent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practices.databinding.FragmentOneBinding


class OneFragment : Fragment() {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //함수
        binding.btnPush.setOnClickListener {
            Toast.makeText(requireContext(),"PUSH!", Toast.LENGTH_SHORT).show()
        }


    }



}