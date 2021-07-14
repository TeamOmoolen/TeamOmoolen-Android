package com.omoolen.omooroid.login_signup.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
//import com.kakao.auth.AuthType
//import com.kakao.auth.Session
import com.omoolen.omooroid.databinding.FragmentLoginBinding
import com.omoolen.omooroid.home.HomeActivity
import com.omoolen.omooroid.onboarding.OnboardActivity
import com.omoolen.omooroid.util.HashKey


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: LoginViewModel by viewModels() //위임초기화
    //private val sessionCallback: SessionCallback = SessionCallback()
    //var session: Session? = null
    private val TAG = "LoginFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //함수
        getHash()
        kakaologin()
        intentActivity()
    }

    private fun intentActivity(){
        viewModel.isNew.observe(viewLifecycleOwner) { isNew ->
            if (isNew) {
                val intent = Intent(activity, OnboardActivity::class.java)
                startActivity(intent)
                activity?.finish()
            } else {
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    private fun getHash(){
        val hashkey = HashKey()
        hashkey.getHashKey(requireContext())
    }

    fun kakaologin() {
        binding.btnKakaoLogin.setOnClickListener {
            viewModel.newKakao(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //뷰가 죽었을 때 참조 삭제
    }
}