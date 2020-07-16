package com.gunmunity.gunmunity.presentation.community

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gunmunity.gunmunity.R
import com.gunmunity.gunmunity.databinding.ActivityCommunityDetailBinding
import com.gunmunity.gunmunity.model.entity.BoardInfo

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