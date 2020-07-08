package com.example.gunmunity.presentation.community

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.gunmunity.R
import com.example.gunmunity.databinding.ActivityCommunityPostBinding

class CommunityPostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCommunityPostBinding
    private var viewModel: CommunityPostViewModel = CommunityPostViewModel()
    private var category = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        setLiveDataObserver()

        binding.createCategory.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> category = "COUNSEL"
                    1 -> category = "INFORMATION"
                    2 -> category = "FREE"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.createSubmit.setOnClickListener(View.OnClickListener {
            viewModel.postArticle(category, binding.createContent.text.toString(),
                binding.createTitle.text.toString())
        })
    }

    private fun setLiveDataObserver() {
        viewModel.postSuccess.observe(this, Observer {
            Toast.makeText(this, "작성을 완료했습니다.", Toast.LENGTH_LONG).show()
            onBackPressed()
        })
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_post)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }


}