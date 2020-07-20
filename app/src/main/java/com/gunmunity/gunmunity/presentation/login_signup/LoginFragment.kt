package com.gunmunity.gunmunity.presentation.login_signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gunmunity.gunmunity.GunmunityApplication
import com.gunmunity.gunmunity.R
import com.gunmunity.gunmunity.databinding.FragmentLoginBinding
import com.gunmunity.gunmunity.presentation.MainActivity

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setDataBinding(inflater, container)
        setLiveDataObserver()
        setListenerBinding()

        return binding.root
    }

    private fun setListenerBinding() {
        binding.loginSubmit.setOnClickListener {
            loginViewModel.doLogin(binding.loginEtEmail.text.toString(),
                binding.loginEtPassword.text.toString())
        }
    }

    private fun setLiveDataObserver() {
        loginViewModel.loginSuccess.observe(viewLifecycleOwner, Observer {
            val mActivity : LoginActivity = activity as LoginActivity
            mActivity.startMainActivity()
            activity?.finish()
        })
        loginViewModel.loginFailed.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "일치하는 계정이 없습니다", Toast.LENGTH_LONG).show()
        })
        loginViewModel.loginPasswordFailed.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "비밀번호가 맞지 않습니다", Toast.LENGTH_LONG).show()
        })
    }

    private fun setDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        loginViewModel =
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = loginViewModel
        binding.loginQuestionSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }
}