package com.example.practices.login_signup.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.practices.R
import com.example.practices.databinding.FragmentLoginBinding
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: LoginViewModel by viewModels() //위임초기화

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
        login()
    }

    fun login(){
        binding.btnLogin.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("ID", binding.etId.text.toString())
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_fragment_login_to_fragment_signup, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //뷰가 죽었을 때 참조 삭제
    }
}