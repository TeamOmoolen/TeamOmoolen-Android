package com.omoolen.omooroid.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

//    private val detailViewModel: DetailViewModel by viewModels() //위임초기화

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment()
    }

    private fun setFragment(){
        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.fl_detail, DetailFragment())
        transaction.commit()
    }

}