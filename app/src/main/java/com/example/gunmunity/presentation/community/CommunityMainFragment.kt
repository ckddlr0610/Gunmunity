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

class CommunityMainFragment : Fragment() {

    private lateinit var binding: FragmentCommunityMainBinding
    private lateinit var communityMainViewModel: CommunityMainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        communityMainViewModel.text.observe(viewLifecycleOwner, Observer {
        })

        setDataBinding(inflater, container)

        return binding.root
    }

    fun setDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_main, container, false)
        communityMainViewModel =
            ViewModelProviders.of(this).get(CommunityMainViewModel::class.java)
        binding.viewModel = communityMainViewModel
    }

    private fun startCreateActivity() {
        val intent = Intent(activity, CommunityPostActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(activity)
        val adapter = CommunityMainAdapter(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.communityRecyclerview.adapter = adapter
        binding.communityRecyclerview.layoutManager = linearLayoutManager
    }

    fun startDetailActivity(boardInfo: BoardInfo?) {
        val intent = Intent(activity, CommunityDetailActivity::class.java)
        intent.putExtra("boardInfo", boardInfo)
        startActivity(intent)
    }
}
