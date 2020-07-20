package com.gunmunity.gunmunity.presentation.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunmunity.gunmunity.databinding.CardviewBoardListBinding
import com.gunmunity.gunmunity.model.CardList
import com.gunmunity.gunmunity.model.entity.CommentInfo
import org.jetbrains.anko.image

class CommunityListAdapter(var fragment: CommunityListFragment) : RecyclerView.Adapter<CommunityListAdapter.ViewHolder>() {
    private var cardLists = ArrayList<CardList>()

    fun setData(cardList : ArrayList<CardList>) {
        this.cardLists = cardList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardviewBoardListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (cardLists != null) cardLists!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardList: CardList = cardLists!![position]
        holder.bindHolder(cardList)
    }

    inner class ViewHolder(
        private val binding : CardviewBoardListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(cardList: CardList) {
            binding.cardviewImage.image = cardList.cardImage
            binding.cardviewText.text = cardList.cardText
            binding.root.setOnClickListener {
                fragment.startCommunityMain()
            }
        }
    }

}