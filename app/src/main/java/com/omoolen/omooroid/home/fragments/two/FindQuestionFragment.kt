package com.omoolen.omooroid.home.fragments.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentQuestionBinding

class FindQuestionFragment(val where : Int) : DialogFragment() {
    private var _binding : FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View {
        _binding = FragmentQuestionBinding.inflate(
            inflater, container, false
        )

        if(where == 1){
            binding.tvForyouQMain.text = R.string.forYou_question_main.toString()
            binding.tvForyouQContent.text = R.string.forYou_question_content.toString()
        } else if(where == 2){
            binding.tvForyouQMain.text = R.string.situ_question_main.toString()
            binding.tvForyouQContent.text = R.string.situ_question_content.toString()
        } else if(where == 3){
            binding.tvForyouQMain.text = R.string.forYou_question_main.toString()
            binding.tvForyouQContent.text = R.string.forYou_question_content.toString()
        } else if(where == 4){
            binding.tvForyouQMain.text = R.string.forYou_question_main.toString()
            binding.tvForyouQContent.text = R.string.forYou_question_content.toString()
        }

        binding.clForyouBtn.setOnClickListener {
            dismiss()
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}