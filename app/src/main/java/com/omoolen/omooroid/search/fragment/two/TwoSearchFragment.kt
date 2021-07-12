package com.omoolen.omooroid.search.fragment.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentSearchTwoBinding
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandAdapter
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandInfo
import com.omoolen.omooroid.onboarding.fragments.two.recycle.color.ColorAdapter
import com.omoolen.omooroid.onboarding.fragments.two.recycle.color.ColorInfo
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.VerticalItemDecorator

class TwoSearchFragment : Fragment() {
    private var _binding: FragmentSearchTwoBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: TwoSearchViewModel by viewModels() //위임초기화

    private lateinit var brandAdapter: BrandAdapter
    private lateinit var brandLayoutManager: RecyclerView.LayoutManager
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var colorLayoutManager: RecyclerView.LayoutManager

    val brandChoice = ListLiveData<Int>()
    val colorChoice = ListLiveData<Int>()
    val diameterChoice = ListLiveData<Int>()
    val periodChoice = ListLiveData<Int>()

    var chipSelect = arrayOf(true, false, false, false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchTwoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        clickEvent()
        brandInit()
        colorInit()
        binding.tvBrand.isSelected = chipSelect[0]

        return binding.root
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
                if (brandArr[position]) brandChoice.add(position)
                else brandChoice.remove(position)
                v.isSelected = brandArr[position]
            }
        })
        binding.rvBrand.adapter = brandAdapter
        brandLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvBrand.layoutManager = brandLayoutManager
    }

    private fun colorInit() {
        colorAdapter = ColorAdapter()
        colorAdapter.colorList.addAll(
            listOf<ColorInfo>(
                ColorInfo(resourceId = R.drawable.ic_btn_noncolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_blackcolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_greycolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_chococolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_greencolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_browncolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_purplecolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_bluecolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_goldcolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_pinkcolor_selector),
                ColorInfo(resourceId = R.drawable.ic_btn_etccolor_selector)
            )
        )
        var colorArr =
            arrayOf(false, false, false, false, false, false, false, false, false, false, false)
        colorAdapter.setItemClickListener(object : ColorAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                colorArr[position] = !colorArr[position]
                if (colorArr[position]) colorChoice.add(position)
                else colorChoice.remove(position)
                v.isSelected = colorArr[position]
            }
        })
        binding.rvColor.adapter = colorAdapter
        colorLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvColor.layoutManager = colorLayoutManager

        binding.rvColor.addItemDecoration(HorizontalItemDecorator(10))
        binding.rvColor.addItemDecoration(VerticalItemDecorator(10))
    }

    private fun clickEvent() {
        binding.tvBrand.setOnClickListener {
            setChipSelected(0)
            binding.tvBrand.isSelected = chipSelect[0]
            binding.tvTitle.text = "브랜드"
            binding.rvBrand.visibility = View.VISIBLE
            binding.rvColor.visibility = View.GONE
        }
        binding.tvColor.setOnClickListener {
            setChipSelected(1)
            binding.tvColor.isSelected = chipSelect[1]
            binding.tvTitle.text = "컬러"
            binding.rvBrand.visibility = View.GONE
            binding.rvColor.visibility = View.VISIBLE

        }
        binding.tvDiameter.setOnClickListener {
            setChipSelected(2)
            binding.tvDiameter.isSelected = chipSelect[2]
            binding.tvTitle.text = "직경"

        }
        binding.tvPeriod.setOnClickListener {
            setChipSelected(3)
            binding.tvPeriod.isSelected = chipSelect[3]
            binding.tvTitle.text = "주기"

        }
        binding.clAllTouch.setOnClickListener {

        }
    }
    private fun setChipSelected(num:Int){
        for (i in chipSelect.indices) {
            if (i == num) chipSelect[i] = !chipSelect[i]
            else {
                if (chipSelect[i]) {
                    chipSelect[i] = false //단일선택으로
                    when(i){
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