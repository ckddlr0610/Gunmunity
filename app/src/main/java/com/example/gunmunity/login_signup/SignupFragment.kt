package com.example.gunmunity.login_signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        val root = inflater.inflate(R.layout.fragment_signup, container, false)

        return root
    }
}