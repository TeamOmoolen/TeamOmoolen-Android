package com.omoolen.omooroid.home.fragments.two

//import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.omoolen.omooroid.databinding.FragmentFindSortPriceBinding

class FindSortPriceFragment : DialogFragment() {

    private var _binding: FragmentFindSortPriceBinding? = null
    private val binding get() = _binding!!


    //@SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View {
        _binding = FragmentFindSortPriceBinding.inflate(
            inflater, container, false)
/*
        binding.tvDialogFindSortHigh.setOnTouchListener{ v : View, event: MotionEvent ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    binding.tvDialogFindSortHigh.setTextColor(resources.getColor(R.color.om_main_orange))
                    v.performClick()
                    false
                }
                MotionEvent.ACTION_UP -> {
                    binding.tvDialogFindSortHigh.setTextColor(resources.getColor(R.color.om_third_gray))
                    v.performClick()
                    true
                }
                else -> {
                    binding.tvDialogFindSortHigh.setTextColor(resources.getColor(R.color.om_third_gray))
                    v.performClick()
                    true
                }
            }
        }

        binding.tvDialogFindSortLow.setOnTouchListener{ v : View, event: MotionEvent ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    binding.tvDialogFindSortLow.setTextColor(resources.getColor(R.color.om_main_orange))
                    v.performClick()
                    false
                }
                MotionEvent.ACTION_UP -> {
                    binding.tvDialogFindSortLow.setTextColor(resources.getColor(R.color.om_third_gray))
                    v.performClick()
                    true
                }
                else -> {
                    binding.tvDialogFindSortLow.setTextColor(resources.getColor(R.color.om_third_gray))
                    v.performClick()
                    true
                }
            }
        }
*/


        binding.tvDialogFindSortHigh.setOnClickListener{
            buttonClickListener.onHighPriceClicked()
            dismiss()
        }
        binding.tvDialogFindSortLow.setOnClickListener{
            buttonClickListener.onLowPriceClicked()
            dismiss()
        }

        binding.ivDialogFindSortClose.setOnClickListener{
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

    // 인터페이스
    interface OnButtonClickListener {
        fun onLowPriceClicked()
        fun onHighPriceClicked()
    }

    // 클릭 이벤트 실행
    private lateinit var buttonClickListener: OnButtonClickListener
    // 클릭 이벤트 설정
    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
    }

}