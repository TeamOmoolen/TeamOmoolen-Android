package com.omoolen.omooroid.login_signup.login

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
        //getHash()
        observeKakaoUser()
        kakaologin_logout()
        //sessionLogin()
    }

    private fun getHash(){
        val hashkey = HashKey()
        hashkey.getHashKey(requireContext())
    }

//    fun sessionLogin() {
//        session = Session.getCurrentSession()
//        session?.addCallback(sessionCallback)
//
//        binding.btnKakaoLogin.setOnClickListener { v ->
//            if (Session.getCurrentSession().checkAndImplicitOpen()) {
//                Log.d(TAG, "onClick: 로그인 세션살아있음")
//                // 카카오 로그인 시도 (창이 안뜬다.)
//                sessionCallback.requestMe()
//            } else {
//                Log.d(TAG, "onClick: 로그인 세션끝남")
//                // 카카오 로그인 시도 (창이 뜬다.)
//                session?.open(AuthType.KAKAO_LOGIN_ALL, this@LoginFragment)
//            }
//        }
//    }

    fun kakaologin_logout() {
        binding.btnKakaoLogin.setOnClickListener {
            Log.d("LOGINFRAGMENT_login", "버튼클릭")
            //viewModel.kakaoLogin(requireContext()) //버튼 클릭시 로그인
            viewModel.newKakao(requireContext())
        }
//        binding.btnKakaoLogout.setOnClickListener {
//            viewModel.kakaoLogout()
//        }
        //viewModel.updateKakaoLogin() //로그인 유무 검사, 뷰 적용
    }

    fun observeKakaoUser() {
        viewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (!success) { //로그인 X
                binding.btnKakaoLogin.visibility = View.VISIBLE
                //binding.btnKakaoLogout.visibility = View.GONE
                Toast.makeText(requireContext(), "로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
            } else { //로그인 O
                //binding.btnKakaoLogin.visibility = View.GONE
                //binding.btnKakaoLogin.visibility = View.VISIBLE

                //TODO : 온보딩으로 이동

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //뷰가 죽었을 때 참조 삭제
    }
}