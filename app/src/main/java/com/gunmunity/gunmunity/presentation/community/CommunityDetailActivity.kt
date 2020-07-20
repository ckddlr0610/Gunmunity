package com.gunmunity.gunmunity.presentation.community

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gunmunity.gunmunity.R
import com.gunmunity.gunmunity.databinding.ActivityCommunityDetailBinding
import com.gunmunity.gunmunity.model.entity.BoardInfo
import com.gunmunity.gunmunity.util.SharedPreferenceManager
import org.jetbrains.anko.sdk27.coroutines.onClick

class CommunityDetailActivity: AppCompatActivity() {
    private lateinit var binding : ActivityCommunityDetailBinding
    private val viewModel = CommunityDetailViewModel()
    private lateinit var boardInfo: BoardInfo
    private lateinit var adapter : CommunityCommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        boardInfo = intent.getSerializableExtra("boardInfo") as BoardInfo

        setDataBinding()
        setLiveDataObserver()
        initRecyclerView()

        binding.communityDetailLike.onClick {
            viewModel.clickLikeBtn(boardInfo.id)
        }

        if (SharedPreferenceManager.getBooleanPref(boardInfo.id.toString())) {
            binding.communityDetailLike.imageTintList = resources.getColorStateList(R.color.colorPrimary)
            viewModel.setLikeState(true)
        } else {
            binding.communityDetailLike.imageTintList = resources.getColorStateList(R.color.colorPrimary)
            viewModel.setLikeState(false)
        }

        binding.btnSubmitComment.onClick {
            viewModel.postComment(boardInfo.id, binding.etComment.text.toString())
        }

        viewModel.getComment(boardInfo.id)
    }

    private fun setLiveDataObserver() {
        viewModel.isLike.observe(this, Observer {
            if (viewModel.isLike.value == true) {
                binding.communityDetailLike.imageTintList = resources.getColorStateList(R.color.colorPrimary)
            } else {
                binding.communityDetailLike.imageTintList = resources.getColorStateList(R.color.colorLightGrey)
            }
        })

        viewModel.commentLoad.observe(this, Observer {
            adapter.setData(viewModel.commentList)
        })
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.data = boardInfo
    }

    private fun initRecyclerView() {
        adapter = CommunityCommentAdapter()
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.commentList.adapter = adapter
        binding.commentList.layoutManager = linearLayoutManager
    }
}