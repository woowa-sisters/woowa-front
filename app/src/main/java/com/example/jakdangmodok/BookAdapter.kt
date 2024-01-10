package com.example.jakdangmodok

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ItemBookBinding

class BookViewHolder(val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root)

class BookAdapter(val bookList: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //override fun getItemCount(): Int = bookList.size
    override fun getItemCount(): Int = 10   // 데이터 받아오기 전 테스트용

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = BookViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as BookViewHolder).binding

        binding.bookTitle.text = "Title %d".format(position)
        binding.bookAuthor.text = "Author %d".format(position)
        //binding.bookImage.setImageResource(R.drawable.book)
    }

}