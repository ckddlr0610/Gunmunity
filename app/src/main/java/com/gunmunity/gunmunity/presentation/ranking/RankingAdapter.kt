package com.gunmunity.gunmunity.presentation.ranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunmunity.gunmunity.databinding.ItemRankingBinding
import com.gunmunity.gunmunity.model.entity.RankingInfo

class RankingAdapter() : RecyclerView.Adapter<RankingAdapter.ViewHolder>(){
    private var rankingLists = List<RankingInfo>(0, {i -> RankingInfo()})

    fun setData(rankingLists : List<RankingInfo>) {
        this.rankingLists = rankingLists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRankingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (rankingLists != null) rankingLists!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rankingInfo: RankingInfo = rankingLists!![position]
        holder.bindHolder(rankingInfo, position)
    }

    inner class ViewHolder(
        private val binding: ItemRankingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(rankingInfo: RankingInfo, position: Int) {
            binding.data = rankingInfo
            binding.rankingName.text = rankingInfo.prdtnm
            binding.rankingSellYear.text = rankingInfo.sellyear.toString() +"년 "+ rankingInfo.sellmonth.toString()+"월"
            binding.rankingNumber.text = (position+1).toString()
        }
    }
}