package com.omoolen.omooroid.onboarding.fragments.one

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.compose.ui.Alignment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentOnboardOneBinding
import com.omoolen.omooroid.onboarding.OnboardDatabase
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator


class OneOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: OneOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var ageLayoutManager: RecyclerView.LayoutManager
    private lateinit var genderLayoutManager: RecyclerView.LayoutManager

    private var nextArr = arrayOf(-1,-1)
    private var onboardDatabase = OnboardDatabase()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardOneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        onboardDatabase.initOnboard()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar(0,25)
        ageInit()
        genderInit()
        singleChoice()
        observeValue()
        nextBtn()
        Toast.makeText(requireContext(),viewModel.gender.value.toString()+" , "+viewModel.age.value.toString(),Toast.LENGTH_SHORT).show()

    }

    private fun progressBar(start:Int,end:Int) {
        val mProgressBar = binding.pbLoading
        mProgressBar.progress = 25
//        val progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", start, end)
//        progressAnimator.duration = 300
//        progressAnimator.interpolator = LinearInterpolator()
//        progressAnimator.start()
    }

    private fun observeValue(){
        viewModel.gender.observe(viewLifecycleOwner){ gender ->
            nextArr[0] = gender
            binding.tvButton.isSelected = !(nextArr[0] < 0 || nextArr[1] < 0) //다음 버튼 활성화 판단
        }
        viewModel.age.observe(viewLifecycleOwner){ age ->
            nextArr[1] = age
            binding.tvButton.isSelected = !(nextArr[0] < 0 || nextArr[1] < 0)
        }
    }

    private fun genderInit() {
        binding.rvGender.adapter = viewModel.setGenderAdapter()
        genderLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvGender.layoutManager = genderLayoutManager
        binding.rvGender.addItemDecoration(HorizontalItemDecorator(10, requireContext()))
    }

    private fun ageInit() {
        binding.rvAge.adapter = viewModel.setAgeAdapter()
        ageLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvAge.layoutManager = ageLayoutManager
        binding.rvAge.addItemDecoration(VerticalItemDecorator(10, requireContext()))
        binding.rvAge.addItemDecoration(HorizontalItemDecorator(10, requireContext()))
    }

    private fun singleChoice() {
        binding.rvGender.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) { }
                else viewModel.genderSingleChoice(rv,e)
                return false
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        binding.rvAge.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) { }
                else viewModel.ageSingleChoice(rv,e)
                return false
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun nextBtn() {
        binding.tvButton.setOnClickListener {
            //test
            Toast.makeText(requireContext(),viewModel.gender.value.toString()+" , "+viewModel.age.value.toString(),Toast.LENGTH_SHORT).show()
            if (binding.tvButton.isSelected) {
                //onboardDatabase에 정보 저장
                viewModel.gender.value?.let { gender ->
                    viewModel.age.value?.let { age ->
                        onboardDatabase.setOne(

                            gender,
                            age
                        )
                    }
                }
//              //온보딩2로 화면 전환
                Navigation.findNavController(binding.root).navigate(R.id.action_fragment_onboard_one_to_fragment_onboard_two)
            }
        }
    }

}