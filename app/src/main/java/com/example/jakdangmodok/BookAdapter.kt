package com.example.jakdangmodok

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jakdangmodok.databinding.ItemBookBinding

class BookViewHolder(val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root)

class BookAdapter(val bookList: List<Book>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = bookList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = BookViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as BookViewHolder).binding
        val defaultImage = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_launcher_background)

        binding.bookTitle.text = bookList[position].title
        binding.bookAuthor.text = bookList[position].author
        Glide.with(binding.root.context)
            .load(bookList[position].cover)
            .error(defaultImage)
            .fallback(defaultImage)
            .into(binding.bookImage)
    }

}