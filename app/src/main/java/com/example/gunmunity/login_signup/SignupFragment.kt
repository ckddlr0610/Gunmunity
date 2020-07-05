package com.example.gunmunity.login_signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gunmunity.R
import com.example.gunmunity.databinding.FragmentSignupBinding

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

        })
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        binding.viewModel = signupViewModel
        binding.viewModel = signupViewModel
    }
}