package com.gunmunity.gunmunity.presentation.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gunmunity.gunmunity.R
import com.gunmunity.gunmunity.databinding.FragmentPxRankingBinding

class RankingFragment : Fragment() {
    private lateinit var binding : FragmentPxRankingBinding
    private val viewModel = RankingViewModel()
    private lateinit var adapter: RankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDataBinding(inflater, container)
        initRecyclerView()
        setLiveDataObserver()
        viewModel.getRankingList2()

        return binding.root
    }

    private fun setLiveDataObserver() {
        viewModel.rankingInfos.observe(viewLifecycleOwner, Observer {
            adapter.setData(viewModel.rankingInfos.value!!)
        })
    }

    private fun setDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_px_ranking, container, false)
        binding.viewModel = viewModel
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(activity)
        adapter = RankingAdapter()
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.rankingRecyclerView.adapter = adapter
        binding.rankingRecyclerView.layoutManager = linearLayoutManager
    }
}