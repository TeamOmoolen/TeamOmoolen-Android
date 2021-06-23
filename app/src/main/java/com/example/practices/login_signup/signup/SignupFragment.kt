package com.example.practices.login_signup.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.practices.databinding.FragmentSignupBinding

class SignupFragment : Fragment(){
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel : SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //observeSuccess()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        //signup()
        //updateMemberList() //test
    }

//    private fun signup(){
//        binding.btnSignup.setOnClickListener {
//            if(viewModel.checkInputText()){
//                Toast.makeText(requireContext(), "모든 정보를 입력해주세요!", Toast.LENGTH_SHORT).show()
//            }
//            else if(viewModel.isDuplicate(binding.etId.text.toString())){
//                Toast.makeText(requireContext(), "중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                viewModel.doSignup()
//            }
//            /*else{
//                //정보 저장
//                val member = Member(null,binding.etName.text.toString(),binding.etId.text.toString(),binding.etPassword.text.toString())
//                viewModel.insert(member)
//
//                val bundle = Bundle()
//                bundle.putString("NAME",binding.etName.text.toString())
//                bundle.putString("ID",binding.etId.text.toString())
//                bundle.putString("PASSWORD",binding.etPassword.text.toString())
//
//                Navigation.findNavController(binding.root).navigate(R.id.passArgs_signup_to_login,bundle)
//            }*/
//        }
//    }

//    private fun observeSuccess(){
//        viewModel.signupSuccess.observe(viewLifecycleOwner){success ->
//            Log.d("Test","signupFragment > signup"+success)
//            if(success){
//                toast("Signup Success! Let's Login")
//                val bundle = Bundle()
//                bundle.putString("NAME",binding.etName.text.toString())
//                bundle.putString("ID",binding.etId.text.toString())
//                bundle.putString("PASSWORD",binding.etPassword.text.toString())
//                Navigation.findNavController(binding.root).navigate(R.id.passArgs_signup_to_login,bundle)
//            }
//            else{
//                toast("Oops, Signup failed!")
//            }
//        }
//    }
//    private fun updateMemberList(){
//        viewModel.getAll().observe(viewLifecycleOwner, Observer{
//            binding.tvMemberTest.text = it.toString()
//        })
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //뷰가 죽었을 때 참조 삭제
    }

    private fun toast(string : String){
        Toast.makeText(requireContext(),string, Toast.LENGTH_SHORT).show()
    }
}
