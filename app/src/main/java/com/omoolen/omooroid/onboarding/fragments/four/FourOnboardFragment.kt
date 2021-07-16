package com.omoolen.omooroid.onboarding.fragments.four

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.FragmentOnboardFourBinding
import com.omoolen.omooroid.home.HomeActivity
import com.omoolen.omooroid.onboarding.OnboardDatabase
import com.omoolen.omooroid.onboarding.api.*
import com.omoolen.omooroid.util.VerticalItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FourOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardFourBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: FourOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var brandLayoutManager: RecyclerView.LayoutManager
    private lateinit var whenLayoutManager: RecyclerView.LayoutManager
    private var whenClick = false
    private var nameClick = false

    private var height = 0

    private var nextArr = arrayOf(-1,-1)
    private var nextString = ""
    private var onboardDatabase = OnboardDatabase()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardFourBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        binding.etPersonnal.isEnabled = false //editText default 비활성화

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //함수
        progressBar()
        brandInit()
        whenInit()
        brandAnimation()
        observeValue()
        singleChoice()
        brandOk()
        nextBtn()
    }

    private fun progressBar() {
        val mProgressBar = binding.pbLoading
        mProgressBar.progress = 100
    }
    private fun observeValue(){
        viewModel.brand.observe(viewLifecycleOwner){ brand ->
            nextArr[0] = brand
        }
        viewModel.whens.observe(viewLifecycleOwner){ whens ->
            nextArr[1] = whens
            binding.tvButton.isSelected = !(nextArr[0] < 0 || nextArr[1] < 0 || nextString == "") //다음 버튼 활성화 판단
        }
    }

    private fun brandOk(){
        binding.tvOk.setOnClickListener {
            startAnimation(false) //닫기
            whenClick = !whenClick

            //Toast.makeText(requireContext(),nextArr[0].toString()+"@"+nextArr[1].toString(), Toast.LENGTH_SHORT).show()
            binding.tvButton.isSelected = !(nextArr[0] < 0 || nextArr[1] < 0 || nextString == "") //다음 버튼 활성화 판단
            binding.tvToggle1.text = viewModel.brandName.value
            //edittext 활성화
            binding.etPersonnal.isEnabled = true
        }
        binding.clSearch.setOnClickListener {
            //검색되면 할거 -> nextArr업데이트
            //검사
            if(binding.etPersonnal.text.toString() != ""){ //공란이 아니면
                binding.clPersonnal.isSelected = true
                binding.tvGuide.isVisible = false
                nextString = binding.etPersonnal.text.toString()
                binding.tvButton.isSelected = !(nextArr[0] < 0 || nextArr[1] < 0 || nextString == "") //다음 버튼 활성화 판단

            }
            else{
                binding.tvGuide.isVisible = true
            }
        }
    }


    private fun brandAnimation() {
        //TODO : click을 배열로 관리할 건지 전역으로 관리할건지
        height = binding.clToggleContent.height
        binding.clToggle1.setOnClickListener {
            whenClick = !whenClick
            binding.clToggle1.isSelected = whenClick
            if(whenClick) binding.clToggleContent.visibility = View.VISIBLE
            startAnimation(whenClick)
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun startAnimation(bool:Boolean){
        val va = if (bool) ValueAnimator.ofInt(0, 1100) else ValueAnimator.ofInt(1100, 0)
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



    fun brandInit() {
        binding.rvBrand.adapter = viewModel.setBrandAdapter()
        brandLayoutManager = GridLayoutManager(requireContext(), 3)
        brandLayoutManager = object : GridLayoutManager(requireContext(), 3) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                // force size of viewHolder here, this will override layout_height and layout_width from xml
                lp.width = ((width-40) / spanCount)
                //lp.height = lp.width
                return true
            }
        }
        binding.rvBrand.setHasFixedSize(true)
        binding.rvBrand.addItemDecoration(VerticalItemDecoration(10))
        binding.rvBrand.layoutManager = brandLayoutManager
    }

    private fun whenInit() {
        binding.rvWhen.adapter = viewModel.setWhenAdapter()
        whenLayoutManager = GridLayoutManager(requireContext(), 2)
        whenLayoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                // force size of viewHolder here, this will override layout_height and layout_width from xml
                lp.width = ((width - 50) / spanCount)
                //lp.height = lp.width
                return true
            }
        }
        binding.rvWhen.setHasFixedSize(true)
        binding.rvWhen.layoutManager = whenLayoutManager
        binding.rvWhen.addItemDecoration(VerticalItemDecoration(10))
        //binding.rvWhen.addItemDecoration(HorizontalItemDecorator(10, 2, requireContext()))
    }

    private fun singleChoice() {
        binding.rvBrand.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) { }
                else {
                    viewModel.brandSingleChoice(rv,e)
                    binding.tvOk.isSelected = true
                }
                return false
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        binding.rvWhen.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) { }
                else viewModel.whenSingleChoice(rv,e)
                return false
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun nextBtn() {
        binding.tvButton.setOnClickListener {
            if (binding.tvButton.isSelected) {
                //onboardDatabase에 정보 저장
                viewModel.brand.value?.let { brand ->
                    viewModel.whens.value?.let { whens ->
                        onboardDatabase.setFour(
                            brand,
                            nextString,
                            whens
                        )
                    }
                }
                //TODO : 서버로 온보딩 정보 다 전달하기
                Log.d("ONBOARD","온보딩 정보 전달")
                onboardDatabase.show()

                //데이터 convert

                //서버 전달
                postOnboard()

                //홈화면으로 전환
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent) //액티비티 띄우기

                activity?.finish()
            }
        }
    }

    //서버 통신
    private fun postOnboard(){
        viewModel.postOnboard()
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}