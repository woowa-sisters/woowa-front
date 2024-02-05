package com.example.jakdangmodok

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.jakdangmodok.databinding.ActivityBookDetailBinding
import kotlinx.coroutines.launch

class BookDetailActivity : AppCompatActivity() {

    val binding by lazy { ActivityBookDetailBinding.inflate(layoutInflater) }
    private val bookAPIService = BookAPIService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarGroupDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setBookInfo()
    }

    private fun setBookInfo() {
        lifecycleScope.launch {
            val isbn = intent.getStringExtra("isbn")!!
            val book = bookAPIService.getBookDetail(isbn)

            binding.bookTitle.text = book.title
            binding.bookAuthor.text = book.author
            binding.bookPage.text = book.itemPage
            binding.bookGenre.text = book.categoryName
            Glide.with(this@BookDetailActivity)
                .load(book.cover)
                .into(binding.bookImage)
        }
    }

    private fun setClickListeners() {
        binding.btnSubscribeBook.setOnClickListener {
            binding.btnSubscribeBook.setBackgroundResource(R.drawable.baseline_favorite)
        }
    }

    // 뒤로가기 버튼
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}