package com.omoolen.omooroid.search.fragment.two

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentSearchTwoBinding
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandAdapter
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandInfo
import com.omoolen.omooroid.onboarding.fragments.two.recycle.color.ColorAdapter
import com.omoolen.omooroid.onboarding.fragments.two.recycle.color.ColorInfo
import com.omoolen.omooroid.search.SearchDatabase
import com.omoolen.omooroid.search.fragment.two.recycle.diameter.DiameterAdapter
import com.omoolen.omooroid.search.fragment.two.recycle.diameter.DiameterInfo
import com.omoolen.omooroid.search.fragment.two.recycle.period.PeriodAdapter
import com.omoolen.omooroid.search.fragment.two.recycle.period.PeriodInfo
import com.omoolen.omooroid.search.search_result.SearchResultActivity
import com.omoolen.omooroid.search.search_result.SearchResultListAdapter
import com.omoolen.omooroid.search.search_result.SearchResultViewModel
import com.omoolen.omooroid.util.VerticalItemDecoration

class TwoSearchFragment : Fragment() {
    private var _binding: FragmentSearchTwoBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: TwoSearchViewModel by viewModels() //위임초기화

    private lateinit var brandAdapter: BrandAdapter
    private lateinit var brandLayoutManager: RecyclerView.LayoutManager
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var colorLayoutManager: RecyclerView.LayoutManager
    private lateinit var diameterAdapter: DiameterAdapter
    private lateinit var diameterLayoutManager: RecyclerView.LayoutManager
    private lateinit var periodAdapter: PeriodAdapter
    private lateinit var periodLayoutManager: RecyclerView.LayoutManager

    lateinit var brandChoice: MutableList<String>
    lateinit var colorChoice: MutableList<String>
    lateinit var diameterChoice: MutableList<Int>
    lateinit var periodChoice: MutableList<Int>

    var chipSelect = arrayOf(true, false, false, false)
    var searchDatabase = SearchDatabase()

    val brandList = arrayOf(
        "오렌즈", "렌즈미", "렌즈베리", "앤365", "렌즈타운", "다비치", "아이돌렌즈", "렌즈나인", "렌즈디바",
        "아큐브", "바슈롬", "클라렌", "알콘", "뉴바이오", "프레쉬콘", "쿠퍼비전"
    )
    val colorList = arrayOf(
        "clear",
        "black",
        "gray",
        "choco",
        "green",
        "brown",
        "purple",
        "blue",
        "gold",
        "pink",
        "glitter"
    )
    val otherColorList = arrayOf("yellow", "espressogold", "hazel", "rich brown", "white", "red")
    val cycleList = arrayOf(0, 1, 2, 3, 4, 5, 6, 7)

    val firstList = arrayOf(true,true,true,true)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchTwoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvent()
        brandInit()
        colorInit()
        diameterInit()
        periodInit()

        searchDatabase.initSearch()
        init()
        reset()
        binding.tvBrand.isSelected = chipSelect[0]
        initChoice()
    }

    override fun onStart() {
        super.onStart()
        initAfter()
        doFilter()
        observeFilterList()
    }

    private fun init() {
        binding.clBrand.visibility = View.VISIBLE
        binding.clColor.visibility = View.VISIBLE
        binding.clDiameter.visibility = View.VISIBLE
        binding.clCycle.visibility = View.VISIBLE
    }
    private fun initAfter(){
        binding.clBrand.visibility = View.VISIBLE
        binding.clColor.visibility = View.GONE
        binding.clDiameter.visibility = View.GONE
        binding.clCycle.visibility = View.GONE
    }

    //전체 선택(초기 선택)
    private fun initChoice() {
        brandChoice = mutableListOf(
            "오렌즈", "렌즈미", "렌즈베리", "앤365", "렌즈타운", "다비치", "아이돌렌즈", "렌즈나인", "렌즈디바",
            "아큐브", "바슈롬", "클라렌", "알콘", "뉴바이오", "프레쉬콘", "쿠퍼비전"
        )
        colorChoice = mutableListOf(
            "clear",
            "black",
            "gray",
            "choco",
            "green",
            "brown",
            "purple",
            "blue",
            "gold",
            "pink",
            "glitter"
        )
        diameterChoice = mutableListOf(-1)
        periodChoice = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7)
        for(i in firstList.indices){
            firstList[i] = true
        }
    }

//    page : Int, sort : String, order : String,
//    brandChoice: MutableList<String>,
//    colorChoice: MutableList<String>,
//    diameterChoice: MutableList<Int>,
//    periodChoice: MutableList<Int>
    private fun doFilter(){
        binding.tvFilter.setOnClickListener{

            viewModel.getFilterSearch(1,"price","asc",
            brandChoice,colorChoice,diameterChoice,periodChoice)
        }
    }

    private fun observeFilterList(){
        viewModel.filterSuccess.observe(this) {
            val intent = Intent(requireContext(), SearchResultActivity::class.java)
            intent.putExtra("mode","filter")
            val arr = ArrayList(viewModel.filterResultList)
            intent.putExtra("filterList",arr)
            startActivity(intent)

        }
    }



    //TODO : 전체선택 처리해서 CHIOICE 어떻게 넘길지 로직짜기
    private fun brandInit() {
        brandAdapter = BrandAdapter()
        brandAdapter.brandList.addAll(
            listOf<BrandInfo>(
                BrandInfo(resourceId = R.drawable.img_olens_logo_onboarding_normal, name = "오렌즈"),
                BrandInfo(resourceId = R.drawable.img_lensme_logo_onboarding_normal, name = "렌즈미"),
                BrandInfo(resourceId = R.drawable.img_lensvery_logo_onboarding_normal, name = "렌즈베리"),
                BrandInfo(resourceId = R.drawable.img_ann_logo_onboarding_normal, name = "앤365"),
                BrandInfo(resourceId = R.drawable.img_lenstown_logo_onboarding_normal, name = "렌즈타운"),
                BrandInfo(resourceId = R.drawable.img_davi_logo_onboarding_normal, name = "다비치"),
                BrandInfo(resourceId = R.drawable.img_idol_logo_onboarding_normal, name = "아이돌렌즈"),
                BrandInfo(resourceId = R.drawable.img_lensnine_logo_onboarding_normal, name = "렌즈나인"),
                BrandInfo(resourceId = R.drawable.img_lensdiva_logo_onboarding_normal, name = "렌즈디바"),
                BrandInfo(resourceId = R.drawable.img_acuvue_logo_onboarding_normal, name = "아큐브"),
                BrandInfo(resourceId = R.drawable.img_ba_logo_onboarding_normal, name = "바슈롬"),
                BrandInfo(resourceId = R.drawable.img_cl_logo_onboarding_normal, name = "클라렌"),
                BrandInfo(resourceId = R.drawable.img_ilcon_logo_onboarding_normal, name = "알콘"),
                BrandInfo(resourceId = R.drawable.img_newbio_logo_onboarding_normal, name = "뉴바이오"),
                BrandInfo(resourceId = R.drawable.img_freshkon_logo_onboarding_normal, name = "프레쉬콘"),
                BrandInfo(resourceId = R.drawable.img_coupervision_logo_onboarding_normal, name = "쿠퍼비전"),
                BrandInfo(resourceId = R.drawable.img_etc_logo_onboarding_normal, name = "그외")
            )
        )
        var brandArr = arrayOf(
            false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false
        )

        brandAdapter.setItemClickListener(object : BrandAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                brandArr[position] = !brandArr[position]
                if (firstList[0]) {
                    brandChoice.clear()
                    firstList[0] = false
                }
                if (brandArr[position]) brandChoice.add(brandAdapter.brandList[position].name)
                else brandChoice.remove(brandAdapter.brandList[position].name)

                if (brandChoice.size == 0) {
                    brandChoice =
                        brandList.toMutableList() //아무것도 선택 안했을 때 전체 선택으로
                    firstList[0] = true
                }
                v.isSelected = brandArr[position]
            }
        })
        binding.rvBrand.adapter = brandAdapter
        brandLayoutManager = GridLayoutManager(requireContext(), 3)
        brandLayoutManager = object : GridLayoutManager(requireContext(), 3) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                // force size of viewHolder here, this will override layout_height and layout_width from xml
                lp.width = ((width - 40) / spanCount)
                //lp.height = lp.width
                return true
            }
        }
        binding.rvBrand.setHasFixedSize(true)
        binding.rvBrand.addItemDecoration(VerticalItemDecoration(10))
        binding.rvBrand.layoutManager = brandLayoutManager

    }

    private fun colorInit() {
        colorAdapter = ColorAdapter()
        colorAdapter.colorList.addAll(
            listOf<ColorInfo>(
                ColorInfo(
                    backId = R.drawable.ic_btn_noncolor_back,
                    resourceId = R.drawable.ic_btn_noncolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_blackcolor_back,
                    resourceId = R.drawable.ic_btn_blackcolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_greycolor_back,
                    resourceId = R.drawable.ic_btn_greycolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_chococolor_back,
                    resourceId = R.drawable.ic_btn_chococolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_greencolor_back,
                    resourceId = R.drawable.ic_btn_greencolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_browncolor_back,
                    resourceId = R.drawable.ic_btn_browncolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_purplecolor_back,
                    resourceId = R.drawable.ic_btn_purplecolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_bluecolor_back,
                    resourceId = R.drawable.ic_btn_bluecolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_goldcolor_back,
                    resourceId = R.drawable.ic_btn_goldcolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_pinkcolor_back,
                    resourceId = R.drawable.ic_btn_pinkcolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.btn_glittercolor_normal,
                    resourceId = R.drawable.ic_btn_glittercolor_selector
                ),
                ColorInfo(
                    backId = R.drawable.ic_btn_etccolor_back,
                    resourceId = R.drawable.ic_btn_etccolor_selector
                )
            )
        )
        var colorArr =
            arrayOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )

        colorAdapter.setItemClickListener(object : ColorAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                colorArr[position] = !colorArr[position]
                if (firstList[1]) {
                    colorChoice.clear()
                    firstList[1] = false
                }
                if (colorArr[position]) { //선택
                    if (position == 11) { //기타 선택
                        for (i in 0 until otherColorList.size) {
                            colorChoice.add(otherColorList[i])
                        }
                    } else colorChoice.add(colorList[position])

                } else { //제거
                    if (position == 11) { //기타 선택
                        for (i in 0 until otherColorList.size) {
                            Log.d("other없애기", otherColorList[i])
                            colorChoice.remove(otherColorList[i])
                        }
                    } else {
                        colorChoice.remove(colorList[position])
                    }
                }

                if (colorChoice.size == 0) {
                    colorChoice =
                        colorList.toMutableList() //아무것도 선택 안했을 때 전체 선택으로
                    firstList[1] = true
                }
                v.isSelected = colorArr[position]
            }
        })
        binding.rvColor.adapter = colorAdapter
        colorLayoutManager = GridLayoutManager(requireContext(), 2)
        colorLayoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                // force size of viewHolder here, this will override layout_height and layout_width from xml
                lp.width = ((width - 50) / spanCount)
                //lp.height = lp.width + 1000
                return true
            }
        }
        binding.rvColor.setHasFixedSize(true)
        binding.rvColor.addItemDecoration(VerticalItemDecoration(10))
        binding.rvColor.layoutManager = colorLayoutManager

    }

    private fun diameterInit() {
        diameterAdapter = DiameterAdapter()
        diameterAdapter.diameterList.addAll(
            listOf<DiameterInfo>(
                DiameterInfo(name = "12.6 이하"),
                DiameterInfo(name = "12.7 ~ 13.0"),
                DiameterInfo(name = "13.1 ~ 13.3"),
                DiameterInfo(name = "13.4 ~ 13.6"),
                DiameterInfo(name = "13.7 ~ 13.9"),
                DiameterInfo(name = "14.0 이상"),
            )
        )
//        var diameterArr = arrayOf(false, false, false, false, false, false)
//        diameterAdapter.setItemClickListener(object : DiameterAdapter.OnItemClickListener {
//            override fun onClick(v: View, position: Int) {
//                //단일 선택
//                diameterChoice.clear()
//                diameterArr[position] = !diameterArr[position]
//                if (diameterArr[position]) diameterChoice.add(position)
//                else diameterChoice.remove(position)
//                v.isSelected = diameterArr[position]
//            }
//        })
        diameterAdapter.setItemClickListener(object : DiameterAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
            }
        })
        binding.rvDiameter.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) {
                } else {
                    viewModel.diameterSingleChoice(rv, e)
                }
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        binding.rvDiameter.adapter = diameterAdapter
        diameterLayoutManager = GridLayoutManager(requireContext(), 2)
        diameterLayoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                // force size of viewHolder here, this will override layout_height and layout_width from xml
                lp.width = ((width - 50) / spanCount)
                //lp.height = lp.width + 1000
                return true
            }
        }
        binding.rvDiameter.setHasFixedSize(true)
        binding.rvDiameter.addItemDecoration(VerticalItemDecoration(10))
        binding.rvDiameter.layoutManager = diameterLayoutManager

        viewModel._diameterChoice.observe(viewLifecycleOwner, { choice ->
            diameterChoice.clear()
            diameterChoice.add(choice)
        })
    }

    private fun periodInit() {
        periodAdapter = PeriodAdapter()
        periodAdapter.periodList.addAll(
            listOf<PeriodInfo>(
                PeriodInfo(name = "1 day"),
                PeriodInfo(name = "2 ~ 6 days"),
                PeriodInfo(name = "1 week"),
                PeriodInfo(name = "2 week"),
                PeriodInfo(name = "1 month"),
                PeriodInfo(name = "2 ~ 3 months"),
                PeriodInfo(name = "4 ~5 months"),
                PeriodInfo(name = "6 months +")
            )
        )

        var periodArr = arrayOf(false, false, false, false, false, false, false, false)
        periodAdapter.setItemClickListener(object : PeriodAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //다중 선택
                if (firstList[3]) {
                    periodChoice.clear()
                    firstList[3] = false
                }
                periodArr[position] = !periodArr[position]
                if (periodArr[position]) periodChoice.add(position)
                else periodChoice.remove(position)

                if (periodChoice.size == 0) {
                    periodChoice =
                        cycleList.toMutableList() //아무것도 선택 안했을 때 전체 선택으로
                    firstList[3] = true
                }

                v.isSelected = periodArr[position]
            }
        })
        binding.rvCycle.adapter = periodAdapter
        periodLayoutManager = GridLayoutManager(requireContext(), 2)
        periodLayoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                // force size of viewHolder here, this will override layout_height and layout_width from xml
                lp.width = ((width - 50) / spanCount)
                //lp.height = lp.width + 1000
                return true
            }
        }
        binding.rvCycle.setHasFixedSize(true)
        binding.rvCycle.addItemDecoration(VerticalItemDecoration(10))
        binding.rvCycle.layoutManager = periodLayoutManager
    }

    private fun clickEvent() {
        binding.tvBrand.setOnClickListener {
            setChipSelected(0)
            binding.tvBrand.isSelected = chipSelect[0]
            binding.clBrand.visibility = View.VISIBLE
            binding.clColor.visibility = View.GONE
            binding.clDiameter.visibility = View.GONE
            binding.clCycle.visibility = View.GONE
        }
        binding.tvColor.setOnClickListener {
            setChipSelected(1)
            binding.tvColor.isSelected = chipSelect[1]
            binding.clBrand.visibility = View.GONE
            binding.clColor.visibility = View.VISIBLE
            binding.clDiameter.visibility = View.GONE
            binding.clCycle.visibility = View.GONE
        }
        binding.tvDiameter.setOnClickListener {
            setChipSelected(2)
            binding.tvDiameter.isSelected = chipSelect[2]
            binding.clBrand.visibility = View.GONE
            binding.clColor.visibility = View.GONE
            binding.clDiameter.visibility = View.VISIBLE
            binding.clCycle.visibility = View.GONE
        }
        binding.tvPeriod.setOnClickListener {
            setChipSelected(3)
            binding.tvPeriod.isSelected = chipSelect[3]
            binding.clBrand.visibility = View.GONE
            binding.clColor.visibility = View.GONE
            binding.clDiameter.visibility = View.GONE
            binding.clCycle.visibility = View.VISIBLE
        }

        //전체선택
        var allSelectedArr = arrayOf(false,false,false,false)

        binding.clAllTouchBrand.setOnClickListener {
            allSelectedArr[0] = !allSelectedArr[0]
            binding.ivBrand.isSelected = allSelectedArr[0]
            binding.tvBrandSelectAll.isSelected = allSelectedArr[0]

            if (allSelectedArr[0]) { //전체 선택 시
                brandChoice = brandList.toMutableList() //아무것도 선택 안했을 때 전체 선택으로
                firstList[0] = true
                for (i in 0 until brandList.size + 1)
                    binding.rvBrand[i].isSelected = true
            }
            else { //전체 선택 취소
                brandChoice = brandList.toMutableList() //아무것도 선택 안했을 때 전체 선택으로
                firstList[0] = true
                for (i in 0 until brandList.size + 1)
                    binding.rvBrand[i].isSelected = false
            }
        }
        binding.clAllTouchColor.setOnClickListener {
            allSelectedArr[1] = !allSelectedArr[1]
            binding.ivColor.isSelected = allSelectedArr[1]
            binding.tvColorSelectAll.isSelected = allSelectedArr[1]
            if (allSelectedArr[1]) { //전체 선택 시
                colorChoice = colorList.toMutableList() //아무것도 선택 안했을 때 전체 선택으로
                firstList[1] = true
                for (i in 0 until colorList.size + 1)
                    binding.rvColor[i].isSelected = true
            }
            else { //전체 선택 취소
                colorChoice = colorList.toMutableList() //아무것도 선택 안했을 때 전체 선택으로
                firstList[1] = true
                for (i in 0 until colorList.size + 1)
                    binding.rvColor[i].isSelected = false
            }
        }
//        binding.clAllTouchDiameter.setOnClickListener {
//            allSelectedArr[2] = !allSelectedArr[2]
//            binding.ivDiameter.isSelected = allSelectedArr[2]
//            binding.tvDiameterSelectAll.isSelected = allSelectedArr[2]
//            if (allSelectedArr[2]) { //전체 선택 시
//                diameterChoice[0] = -1 //아무것도 선택 안했을 때 전체 선택으로
//                firstList[2] = true
//                for (i in 0 until 6)
//                    binding.rvDiameter[i].isSelected = true
//            }
//            else { //전체 선택 취소
//                diameterChoice[0] = -1 //아무것도 선택 안했을 때 전체 선택으로
//                firstList[2] = true
//                for (i in 0 until 6)
//                    binding.rvDiameter[i].isSelected = false
//            }
//        }
        binding.clAllTouchCycle.setOnClickListener {
            allSelectedArr[3] = !allSelectedArr[3]
            binding.ivCycle.isSelected = allSelectedArr[3]
            binding.tvCycleSelectAll.isSelected = allSelectedArr[3]
            if (allSelectedArr[3]) { //전체 선택 시
                periodChoice = cycleList.toMutableList() //아무것도 선택 안했을 때 전체 선택으로
                firstList[3] = true
                for (i in 0 until cycleList.size)
                    binding.rvCycle[i].isSelected = true
            }
            else { //전체 선택 취소
                periodChoice = cycleList.toMutableList() //아무것도 선택 안했을 때 전체 선택으로
                firstList[3] = true
                for (i in 0 until cycleList.size)
                    binding.rvCycle[i].isSelected = false
            }
        }

        //필터 검색 버튼 클릭
        binding.tvFilter.setOnClickListener {
            //컨버팅 필요
            searchDatabase.setBrand(brandChoice)
            searchDatabase.setColor(colorChoice)
            searchDatabase.setDiameter(diameterChoice)
            searchDatabase.setCycle(periodChoice)
            searchDatabase.show()

            viewModel.getFilterSearch(0,"price","asc",brandChoice,colorChoice,diameterChoice,periodChoice)
        }


    }

    private fun reset(){
        //새로고침
        binding.clRefresh.setOnClickListener {
            initChoice()
            for (i in 0 until brandList.size + 1)
                binding.rvBrand[i].isSelected = false
            for (i in 0 until colorList.size + 1)
                binding.rvColor[i].isSelected = false
            for (i in 0 until 6)
                binding.rvDiameter[i].isSelected = false
            for (i in 0 until cycleList.size)
                binding.rvCycle[i].isSelected = false
        }
    }

    private fun setChipSelected(num: Int) {
        for (i in chipSelect.indices) {
            if (i == num) chipSelect[i] = true
            else {
                if (chipSelect[i]) {
                    chipSelect[i] = false //단일선택으로
                    when (i) {
                        0 -> binding.tvBrand.isSelected = chipSelect[0]
                        1 -> binding.tvColor.isSelected = chipSelect[1]
                        2 -> binding.tvDiameter.isSelected = chipSelect[2]
                        3 -> binding.tvPeriod.isSelected = chipSelect[3]
                    }
                }
            }
        }
    }

    fun newInstant(): TwoSearchFragment {
        val args = Bundle()
        val frag = TwoSearchFragment()
        frag.arguments = args
        return frag
    }
}