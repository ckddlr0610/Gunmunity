package com.example.gunmunity.presentation.community

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.gunmunity.R
import com.example.gunmunity.databinding.ActivityCommunityDetailBinding
import com.example.gunmunity.model.entity.BoardInfo
import java.util.Observer

class CommunityDetailActivity: AppCompatActivity() {
    private lateinit var binding : ActivityCommunityDetailBinding
    private val viewModel = CommunityDetailViewModel()
    private lateinit var boardInfo: BoardInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        boardInfo = intent.getSerializableExtra("boardInfo") as BoardInfo

        setDataBinding()
        setLiveDataObserver()
    }

    private fun setLiveDataObserver() {

    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.data = boardInfo
    }
}