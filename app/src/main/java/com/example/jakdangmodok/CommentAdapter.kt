package com.example.jakdangmodok

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ItemCommentBinding

class CommentViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root)

class CommentAdapter(val commentList: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = commentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = CommentViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as CommentViewHolder).binding

        binding.comment.text = commentList[position]
    }

}