package com.gunmunity.gunmunity.presentation.community

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gunmunity.gunmunity.R
import com.gunmunity.gunmunity.databinding.FragmentBoardListBinding
import com.gunmunity.gunmunity.model.CardList

class CommunityListFragment : Fragment() {
    private lateinit var binding : FragmentBoardListBinding
    private lateinit var viewModel : CommunityListViewModel
    private var adapter = CommunityListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDataBinding(inflater, container)
        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        val boardList = ArrayList<CardList>(listOf(
            CardList(resources.getDrawable(R.drawable.image_army), "육군 게시판"),
            CardList(resources.getDrawable(R.drawable.image_navy), "해군 게시판"),
            CardList(resources.getDrawable(R.drawable.image_air), "공군 게시판"),
            CardList(resources.getDrawable(R.drawable.image_marine), "해병 게시판")))

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.boardListRecycler.adapter = adapter
        binding.boardListRecycler.layoutManager = linearLayoutManager
        adapter.setData(boardList)
    }

    private fun setDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_list, container, false)
        viewModel =
            ViewModelProviders.of(this).get(CommunityListViewModel::class.java)
        binding.viewModel = viewModel
    }

    public fun startCommunityMain() {
        val intent = Intent(activity, CommunityMainActivity::class.java)
        startActivity(intent)
    }
}