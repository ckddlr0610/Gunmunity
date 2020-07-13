package com.example.gunmunity.presentation.community

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gunmunity.databinding.CardviewBoardListBinding
import com.example.gunmunity.model.CardDTO
import org.jetbrains.anko.image

class CommunityListAdapter(var fragment: CommunityListFragment) : RecyclerView.Adapter<CommunityListAdapter.ViewHolder>() {
    private var cardLists = ArrayList<CardDTO>()

    fun setData(cardList : ArrayList<CardDTO>) {
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
        val cardList: CardDTO = cardLists!![position]
        holder.bindHolder(cardList)
    }

    inner class ViewHolder(
        private val binding : CardviewBoardListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(cardList: CardDTO) {
            binding.cardviewImage.image = cardList.cardImage
            binding.cardviewText.text = cardList.cardText
            binding.root.setOnClickListener {
                fragment.startCommunityMain()
            }
        }
    }

}