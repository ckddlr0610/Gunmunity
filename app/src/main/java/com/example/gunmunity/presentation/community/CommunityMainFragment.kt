package com.example.gunmunity.presentation.community

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gunmunity.R
import com.example.gunmunity.databinding.FragmentCommunityMainBinding
import com.example.gunmunity.model.entity.BoardInfo
import com.example.gunmunity.util.RetrofitManager
import kotlinx.android.synthetic.main.fragment_community_main.*

class CommunityMainFragment : Fragment() {

    private lateinit var binding: FragmentCommunityMainBinding
    private lateinit var communityMainViewModel: CommunityMainViewModel
    private lateinit var adapter: CommunityMainAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setDataBinding(inflater, container)
        initRecyclerView()
        clickButton()
        communityMainViewModel.getBoardList("FREE", 0)
        setLiveDataObserver()

        binding.mainCreate.setOnClickListener {
            startCreateActivity()
        }

        return binding.root
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
        communityMainViewModel.getListCall.observe(viewLifecycleOwner, Observer {
            adapter.setData(communityMainViewModel.boardInfos.value)
        })
    }

    private fun setDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_main, container, false)
        communityMainViewModel =
            ViewModelProviders.of(this).get(CommunityMainViewModel::class.java)
        binding.viewModel = communityMainViewModel
    }

    fun startCreateActivity() {
        val intent = Intent(activity, CommunityPostActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(activity)
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
        val intent = Intent(activity, CommunityDetailActivity::class.java)
        intent.putExtra("boardInfo", boardInfo)
        startActivity(intent)
    }
}
