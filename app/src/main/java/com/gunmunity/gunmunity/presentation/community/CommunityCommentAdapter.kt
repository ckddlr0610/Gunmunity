package com.gunmunity.gunmunity.presentation.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunmunity.gunmunity.databinding.ItemCommentBinding
import com.gunmunity.gunmunity.model.entity.CommentInfo

class CommunityCommentAdapter: RecyclerView.Adapter<CommunityCommentAdapter.ViewHolder>() {
    private var commentLists = ArrayList<CommentInfo>()

    fun setData(commentList : ArrayList<CommentInfo>) {
        this.commentLists = commentList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (commentLists != null) commentLists!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commentInfo: CommentInfo = commentLists!![position]
        holder.bindHolder(commentInfo)
    }

    inner class ViewHolder(
        private val binding : ItemCommentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHolder(comment: CommentInfo) {
            binding.commentAuthor.text = comment.author
            binding.commentContent.text = comment.content
            binding.commentCreatedTime.text = comment.createdDate
        }
    }

}