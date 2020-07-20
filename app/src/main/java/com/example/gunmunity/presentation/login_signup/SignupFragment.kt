package com.example.gunmunity.presentation.login_signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gunmunity.GunmunityApplication
import com.example.gunmunity.R
import com.example.gunmunity.databinding.FragmentSignupBinding
import com.example.gunmunity.presentation.MainActivity

class SignupFragment : Fragment() {

    private lateinit var signupViewModel: SignupViewModel
    private lateinit var binding : FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signupViewModel =
            ViewModelProviders.of(this).get(SignupViewModel::class.java)

        setDataBinding(inflater, container)
        setLiveDataObserver()
        setListenerBinding()

        return binding.root
    }

    private fun setListenerBinding() {
        binding.signupSubmit.setOnClickListener {
            signupViewModel.doSignup(binding.signupEtEmail.text.toString(),
                binding.signupEtPassword.text.toString(),
                binding.signupEtNickname.text.toString())
        }
    }

    private fun setLiveDataObserver() {
        signupViewModel.signupSuccess.observe(viewLifecycleOwner, Observer {
            MainActivity.start(GunmunityApplication.appContext!!)
            activity?.finish()
        })
        signupViewModel.emailFailed.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "올바른 이메일 형식을 입력해주세요", Toast.LENGTH_LONG).show()
        })
        signupViewModel.passwordFailed.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "비밀번호는 문자와 숫자를 조합해서 8-20자리를 입력해주세요", Toast.LENGTH_LONG).show()
        })
        signupViewModel.signupFailed.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "중복된 이메일이거나 닉네임일 수 있습니다. 다른 이메일이나 닉네임을 입력해주세요", Toast.LENGTH_LONG).show()
        })
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        binding.viewModel = signupViewModel
    }
}