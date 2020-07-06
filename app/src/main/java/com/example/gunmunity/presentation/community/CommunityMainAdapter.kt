package com.example.gunmunity.presentation.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gunmunity.databinding.ItemCommunityBinding
import com.example.gunmunity.model.entity.BoardInfo
import java.util.*

class CommunityMainAdapter(var mFragment: CommunityMainFragment) :
    RecyclerView.Adapter<CommunityMainAdapter.ViewHolder>() {
    private var lists: ArrayList<BoardInfo>? = ArrayList<BoardInfo>()

    fun setData(lists: ArrayList<BoardInfo>?) {
        this.lists = lists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCommunityBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list: BoardInfo = lists!![position]
        holder.bindHolder(list)
    }

    override fun getItemCount(): Int {
        return if (lists != null) lists!!.size else 0
    }

    inner class ViewHolder(
        private val binding: ItemCommunityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(boardInfo: BoardInfo) {
            binding.boardInfo = boardInfo
            val tokenedStr = tokenCreatedDate(boardInfo.createdDate)
            binding.itemTime.text = tokenedStr[0] + "\n" + tokenedStr[1]
            binding.root
                .setOnClickListener(View.OnClickListener { mFragment.startDetailActivity(boardInfo) })
        }

        private fun tokenCreatedDate(time: String): ArrayList<String> {
            val stringTokenizer = StringTokenizer(time, "T")
            val tokendStr = ArrayList<String>()
            tokendStr.add(stringTokenizer.nextToken())
            tokendStr.add(stringTokenizer.nextToken())
            return tokendStr
        }

    }

}