package com.gunmunity.gunmunity.presentation.community

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gunmunity.gunmunity.R
import com.gunmunity.gunmunity.databinding.ActivityCommunityMainBinding
import com.gunmunity.gunmunity.model.entity.BoardInfo
import kotlinx.android.synthetic.main.activity_community_main.*

class CommunityMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityMainBinding
    private lateinit var communityMainViewModel: CommunityMainViewModel
    private lateinit var adapter: CommunityMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        initRecyclerView()
        clickButton()
        communityMainViewModel.getBoardList("FREE", 0)
        setLiveDataObserver()

        binding.mainCreate.setOnClickListener {
            startCreateActivity()
        }
    }

    private fun clickButton() {
        binding.mainCategory1.setOnClickListener {
            communityMainViewModel.clickCategory(1)
            changeButtonColor()
            main_category1.setBackgroundColor(resources.getColor(R.color.colorAccent))
            main_category1.setTextColor(resources.getColor(R.color.colorWhite))
        }
        binding.mainCategory2.setOnClickListener {
            communityMainViewModel.clickCategory(2)
            changeButtonColor()
            main_category2.setBackgroundColor(resources.getColor(R.color.colorAccent))
            main_category2.setTextColor(resources.getColor(R.color.colorWhite))
        }
        binding.mainCategory3.setOnClickListener {
            communityMainViewModel.clickCategory(3)
            changeButtonColor()
            main_category3.setBackgroundColor(resources.getColor(R.color.colorAccent))
            main_category3.setTextColor(resources.getColor(R.color.colorWhite))
        }
        binding.mainCategory4.setOnClickListener {
            communityMainViewModel.clickCategory(4)
            changeButtonColor()
            main_category4.setBackgroundColor(resources.getColor(R.color.colorAccent))
            main_category4.setTextColor(resources.getColor(R.color.colorWhite))
        }
    }

    private fun setLiveDataObserver() {
        communityMainViewModel.getListCall.observe(this, Observer {
            adapter.setData(communityMainViewModel.boardInfos.value)
        })
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_main)
        communityMainViewModel =
            ViewModelProviders.of(this).get(CommunityMainViewModel::class.java)
        binding.viewModel = communityMainViewModel
    }

    fun startCreateActivity() {
        val intent = Intent(this, CommunityPostActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        adapter = CommunityMainAdapter(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.communityRecyclerview.adapter = adapter
        binding.communityRecyclerview.layoutManager = linearLayoutManager
    }

    fun changeButtonColor() {
        main_category1.setBackgroundColor(resources.getColor(R.color.colorLightGrey))
        main_category1.setTextColor(resources.getColor(R.color.colorBlack))
        main_category2.setBackgroundColor(resources.getColor(R.color.colorLightGrey))
        main_category2.setTextColor(resources.getColor(R.color.colorBlack))
        main_category3.setBackgroundColor(resources.getColor(R.color.colorLightGrey))
        main_category3.setTextColor(resources.getColor(R.color.colorBlack))
        main_category4.setBackgroundColor(resources.getColor(R.color.colorLightGrey))
        main_category4.setTextColor(resources.getColor(R.color.colorBlack))
    }

    fun startDetailActivity(boardInfo: BoardInfo?) {
        val intent = Intent(this, CommunityDetailActivity::class.java)
        intent.putExtra("boardInfo", boardInfo)
        startActivity(intent)
    }
}
