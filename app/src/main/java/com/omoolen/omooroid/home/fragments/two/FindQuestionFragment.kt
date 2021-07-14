package com.omoolen.omooroid.home.fragments.two

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
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
            binding.tvForyouQMain.text = getString(R.string.forYou_question_main)
            binding.tvForyouQContent.text = getString(R.string.forYou_question_content)
        } else if(where == 2){
            binding.tvForyouQMain.text = getString(R.string.situ_question_main)
            binding.tvForyouQContent.text = getString(R.string.situ_question_content)
        } else if(where == 3){
            binding.tvForyouQMain.text = getString(R.string.new_question_main)
            binding.tvForyouQContent.text = getString(R.string.new_question_content)
        } else if(where == 4){
            binding.tvForyouQMain.text = getString(R.string.season_question_main)
            binding.tvForyouQContent.text = getString(R.string.season_question_content)
        }

        binding.clForyouBtn.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}