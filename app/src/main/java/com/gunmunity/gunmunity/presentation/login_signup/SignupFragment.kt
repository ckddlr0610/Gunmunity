package com.gunmunity.gunmunity.presentation.login_signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gunmunity.gunmunity.GunmunityApplication
import com.gunmunity.gunmunity.R
import com.gunmunity.gunmunity.databinding.FragmentSignupBinding
import com.gunmunity.gunmunity.presentation.MainActivity

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
        })
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        binding.viewModel = signupViewModel
    }

    public fun startMainActivity() {

    }
}