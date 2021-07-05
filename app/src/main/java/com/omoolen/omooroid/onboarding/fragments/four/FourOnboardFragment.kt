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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentOnboardFourBinding
import com.omoolen.omooroid.onboarding.fragments.four.`when`.WhenAdapter
import com.omoolen.omooroid.onboarding.fragments.four.`when`.WhenInfo
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandAdapter
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandInfo
import com.omoolen.omooroid.onboarding.fragments.three.recycle.period.PeriodAdapter
import com.omoolen.omooroid.onboarding.fragments.three.recycle.period.PeriodInfo
import com.omoolen.omooroid.util.VerticalItemDecorator


class FourOnboardFragment : Fragment() {
    private var _binding: FragmentOnboardFourBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: FourOnboardViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var brandAdapter: BrandAdapter
    private lateinit var brandLayoutManager: RecyclerView.LayoutManager
    private lateinit var whenAdapter: WhenAdapter
    private lateinit var whenLayoutManager: RecyclerView.LayoutManager
    private var whenClick = false
    private var nameClick = false

    private var height = 0

    private var brandArr = arrayOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)
    private var whenArr = arrayOf(false,false,false,false,false)

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
        brandAdapter = BrandAdapter()
        whenAdapter = WhenAdapter()
        brandInit()
        whenInit()
        clickBtn()
        //observe()
    }


    private fun clickBtn() {
        //TODO : click을 배열로 관리할 건지 전역으로 관리할건지
        height = binding.clToggleContent.height
        //binding.clToggleContent.visibility = View.GONE
        binding.clToggle1.setOnClickListener {
            whenClick = !whenClick
            binding.clToggle1.isSelected = whenClick
            if(whenClick) binding.clToggleContent.visibility = View.VISIBLE
            startAnimation()
        }
//        binding.etPersonnal.setOnClickListener {
//            //TODO : 정보채워졌을 때만 색 바꾸기 -> 디자인이랑 얘기해보고 수정
//            // Observe 하기
//            nameClick = !nameClick
//
//        }
    }

//    private fun observe(){
//        viewModel.lensName.observe(viewLifecycleOwner) { name ->
//            Log.d("FOUR",name)
//            with(binding){
//                if(name !="" || name != null){
//                    nameClick = !nameClick
//                    binding.etPersonnal.isSelected = nameClick
//                }
//            }
//        }
//    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun startAnimation() {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        Log.d("ONEFRAGMENT_CLICK", whenClick.toString())
        Log.d("ONEFRAGMENT_CLICK", height.toString())
        val va = if (whenClick) ValueAnimator.ofInt(0, 1100) else ValueAnimator.ofInt(1100, 0)
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

        binding.rvBrand.adapter = brandAdapter
        brandLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvBrand.layoutManager = brandLayoutManager

        brandAdapter.brandList.addAll(
            listOf<BrandInfo>(
                BrandInfo(resourceId = "test", name = "오렌즈"),
                BrandInfo(resourceId = "test", name = "렌즈미"),
                BrandInfo(resourceId = "test", name = "렌즈베리"),
                BrandInfo(resourceId = "test", name = "앤365"),
                BrandInfo(resourceId = "test", name = "렌즈타운"),
                BrandInfo(resourceId = "test", name = "다비치"),
                BrandInfo(resourceId = "test", name = "아이돌렌즈"),
                BrandInfo(resourceId = "test", name = "렌즈나인"),
                BrandInfo(resourceId = "test", name = "렌즈디바"),
                BrandInfo(resourceId = "test", name = "아큐브"),
                BrandInfo(resourceId = "test", name = "바슈롬"),
                BrandInfo(resourceId = "test", name = "클라렌"),
                BrandInfo(resourceId = "test", name = "알콘"),
                BrandInfo(resourceId = "test", name = "프레쉬콘"),
                BrandInfo(resourceId = "test", name = "쿠퍼비전"),
                BrandInfo(resourceId = "test", name = "그외")
            )
        )

        brandAdapter.setItemClickListener(object: BrandAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기

                brandArr[position] = !brandArr[position]
                v.isSelected = brandArr[position]
            }
        })

    }

    private fun whenInit() {
        binding.rvWhen.adapter = whenAdapter
        whenLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWhen.layoutManager = whenLayoutManager
        binding.rvWhen.addItemDecoration(VerticalItemDecorator(10))

//        val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, resources.displayMetrics).toInt()
//        val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 54f, resources.displayMetrics).toInt()

        whenAdapter.whenList.addAll(
            listOf<WhenInfo>(
                WhenInfo(name = "운동"),
                WhenInfo(name = "일상생활"),
                WhenInfo(name = "특별한 날"),
                WhenInfo(name = "여행"),
                WhenInfo(name = "기타")
            )
        )

        whenAdapter.setItemClickListener(object: WhenAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기

                whenArr[position] = !whenArr[position]
                v.isSelected = whenArr[position]
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}