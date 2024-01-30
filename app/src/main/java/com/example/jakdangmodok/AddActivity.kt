package com.example.jakdangmodok

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.jakdangmodok.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val isbn = result.data?.getStringExtra("isbn")
            val title = result.data?.getStringExtra("title")
            val author = result.data?.getStringExtra("author")
            val categoryName = result.data?.getStringExtra("categoryName")
            val cover = result.data?.getStringExtra("cover")
            setBookInfo(isbn, title, author, categoryName, cover)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initButton()
    }

    private fun init() {
        setSupportActionBar(binding.toolbarAdd)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initButton() {
        binding.searchBookAdd.setOnClickListener {
            // 책 검색 액티비티로 이동
            val intent = Intent(this, BookSearchActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun setBookInfo(isbn: String?, title: String?, author: String?, categoryName: String?, cover: String?) {
        binding.bookTitleAdd.text = title
        binding.bookAuthorAdd.text = author
        binding.bookGenreAdd.text = categoryName
        Glide.with(binding.root.context)
            .load(cover)
            .into(binding.bookCoverAdd)
        binding.bookContainer.isVisible = true
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