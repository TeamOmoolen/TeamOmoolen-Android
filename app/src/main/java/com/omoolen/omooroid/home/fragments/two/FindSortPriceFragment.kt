package com.omoolen.omooroid.home.fragments.two

//import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentFindSortPriceBinding

class FindSortPriceFragment : DialogFragment() {

    private var _binding: FragmentFindSortPriceBinding? = null
    private val binding get() = _binding!!


    //@SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View {
        _binding = FragmentFindSortPriceBinding.inflate(
            inflater, container, false)

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