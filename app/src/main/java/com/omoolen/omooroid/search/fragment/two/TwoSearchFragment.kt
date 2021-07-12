package com.omoolen.omooroid.search.fragment.two

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentSearchOneBinding
import com.omoolen.omooroid.databinding.FragmentSearchTwoBinding
import com.omoolen.omooroid.onboarding.fragments.four.FourOnboardViewModel
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandAdapter
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandInfo

class TwoSearchFragment : Fragment() {
    private var _binding: FragmentSearchTwoBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: TwoSearchViewModel by viewModels() //위임초기화

    private lateinit var brandAdapter: BrandAdapter
    private lateinit var brandLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchTwoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        clickEvent()
        brandInit()
        return binding.root
    }

    private fun brandInit() {
        brandAdapter = BrandAdapter()
        brandAdapter.brandList.addAll(
            listOf<BrandInfo>(
                BrandInfo(resourceId = R.drawable.img_olens_logo, name = "오렌즈"),
                BrandInfo(resourceId = R.drawable.img_lensme_logo, name = "렌즈미"),
                BrandInfo(resourceId = R.drawable.img_lensvery_logo, name = "렌즈베리"),
                BrandInfo(resourceId = R.drawable.img_ann_logo_1, name = "앤365"),
                BrandInfo(resourceId = R.drawable.img_lenstown_logo_1, name = "렌즈타운"),
                BrandInfo(resourceId = R.drawable.img_davi_logo, name = "다비치"),
                BrandInfo(resourceId = R.drawable.img_idol_logo, name = "아이돌렌즈"),
                BrandInfo(resourceId = R.drawable.img_lensnine_logo_1, name = "렌즈나인"),
                BrandInfo(resourceId = R.drawable.img_lensdiva_logo, name = "렌즈디바"),
                BrandInfo(resourceId = R.drawable.img_acuvue_logo, name = "아큐브"),
                BrandInfo(resourceId = R.drawable.img_ba_logo, name = "바슈롬"),
                BrandInfo(resourceId = R.drawable.img_cl_logo, name = "클라렌"),
                BrandInfo(resourceId = R.drawable.img_alcon_logo_1, name = "알콘"),
                BrandInfo(resourceId = R.drawable.img_new_logo_1, name = "뉴바이"),
                BrandInfo(resourceId = R.drawable.image_41, name = "프레쉬콘"),
                BrandInfo(resourceId = R.drawable.image_42, name = "쿠퍼비전"),
                BrandInfo(resourceId = R.drawable.etc, name = "그외")
            )
        )
        brandAdapter.setItemClickListener(object: BrandAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
            }
        })
        binding.rvBrand.adapter = brandAdapter
        brandLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvBrand.layoutManager = brandLayoutManager
    }

    var chipSelect = arrayOf(false,false,false,false)
    private fun clickEvent(){
        binding.tvBrand.setOnClickListener{
            chipSelect[0] = !chipSelect[0]
            binding.tvBrand.isSelected = chipSelect[0]
        }
        binding.tvColor.setOnClickListener{
            chipSelect[1] = !chipSelect[1]
            binding.tvColor.isSelected = chipSelect[1]
        }
        binding.tvDiameter.setOnClickListener{
            chipSelect[2] = !chipSelect[2]
            binding.tvDiameter.isSelected = chipSelect[2]
        }
        binding.tvPeriod.setOnClickListener{
            chipSelect[3] = !chipSelect[3]
            binding.tvPeriod.isSelected = chipSelect[3]
        }
    }

    fun newInstant() : TwoSearchFragment
    {
        val args = Bundle()
        val frag = TwoSearchFragment()
        frag.arguments = args
        return frag
    }
}