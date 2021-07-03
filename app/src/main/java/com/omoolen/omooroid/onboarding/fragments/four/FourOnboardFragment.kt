package com.omoolen.omooroid.onboarding.fragments.four

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentOnboardFourBinding


class FourOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardFourBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: FourOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var brandAdapter: BrandAdapter
    private lateinit var brandLayoutManager: RecyclerView.LayoutManager
    private var click = false
    private var height = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardFourBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //함수
        brandInit()
        //animation()
        toggleBtn()
    }

    private fun toggleBtn() {
        height = binding.clToggleContent.height
        //binding.clToggleContent.visibility = View.GONE
        binding.clToggle1.setOnClickListener {
            click = !click
            startAnimation()
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun startAnimation() {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        Log.d("ONEFRAGMENT_CLICK", click.toString())
        Log.d("ONEFRAGMENT_CLICK", height.toString())
        val va = if (click) ValueAnimator.ofInt(0, 1300) else ValueAnimator.ofInt(1300, 0)
        // Animation이 실행되는 시간, n/1000초
        va.duration = 1000
        val toggleBinding = binding.clToggleContent

        va.addUpdateListener { animation -> // imageView의 높이 변경
            toggleBinding.layoutParams.height = animation.animatedValue as Int
            toggleBinding.requestLayout()
            //toggleBinding.visibility = if (click) View.VISIBLE else View.GONE
        }
        // Animation start
        va.start()
    }

    private fun animation() {
        var click = true
        var anim_down = AnimationUtils.loadAnimation(requireContext(), R.anim.animation_down)
        var anim_up = AnimationUtils.loadAnimation(requireContext(), R.anim.animation_up);

        binding.clToggle1.setOnClickListener {
            if (click) {
                binding.clToggleContent.visibility = View.VISIBLE
                binding.clToggleContent.startAnimation(anim_down)
                //startObjectAnimation()
                click = false
            } else {
                binding.clToggleContent.visibility = View.GONE
                binding.clToggleContent.startAnimation(anim_up)
                //startObjectAnimation()
                click = true
            }
        }
    }

    private fun startObjectAnimation() {
        var height = binding.clToggleContent.height.toFloat()
        var animBg = ObjectAnimator.ofFloat(binding.clToggleContent, "translationY", 0F, +height)
        animBg.duration = 300
        animBg.start()
    }

    fun brandInit() {
        brandAdapter = BrandAdapter()
        binding.rvBrand.adapter = brandAdapter
        brandLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvBrand.layoutManager = brandLayoutManager

        brandAdapter.brandList.addAll(
            listOf<BrandInfo>(
                BrandInfo(
                    imgName = "test",
                    name = "오렌즈"
                ),
                BrandInfo(
                    imgName = "test",
                    name = "에이렌즈"
                ),
                BrandInfo(
                    imgName = "test",
                    name = "비렌즈"
                ),
                BrandInfo(
                    imgName = "test",
                    name = "씨렌즈"
                ),
                BrandInfo(
                    imgName = "test",
                    name = "디렌즈"
                ),
                BrandInfo(
                    imgName = "test",
                    name = "이렌즈"
                ),
                BrandInfo(
                    imgName = "test",
                    name = "에프렌즈"
                )
            )
        )

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}