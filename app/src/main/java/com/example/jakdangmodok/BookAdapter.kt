package com.example.jakdangmodok

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jakdangmodok.databinding.ItemBookBinding
import com.example.jakdangmodok.databinding.ItemBookInfoBinding

class BookViewHolder(val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {
init {
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context, BookDetailActivity::class.java)
            intent.putExtra("bookId", binding.bookTitle.text.toString())
            intent.putExtra("bookAuthor", binding.bookAuthor.text.toString())
            binding.root.context.startActivity(intent)
        }
    }
}

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

class BookSearchViewHolder(val binding: ItemBookInfoBinding): RecyclerView.ViewHolder(binding.root)
class BookSearchAdapter(val bookList: List<Book>, val parent: BookSearchActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = bookList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = BookSearchViewHolder(ItemBookInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as BookSearchViewHolder).binding
        val defaultImage = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_launcher_background)

        binding.bookTitleInfo.text = bookList[position].title
        binding.bookAuthorInfo.text = bookList[position].author
        binding.bookGenreInfo.text = bookList[position].categoryName
        Glide.with(binding.root.context)
            .load(bookList[position].cover)
            .error(defaultImage)
            .fallback(defaultImage)
            .into(binding.bookCoverInfo)

        binding.root.setOnClickListener() {
            parent.setBookInfo(bookList[position])
        }
    }

}