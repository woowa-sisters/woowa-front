package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityBookSearchBinding
import kotlinx.coroutines.launch

class BookSearchActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBookSearchBinding.inflate(layoutInflater) }
    private val bookAPIService = BookAPIService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSearchBook)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initSearchView()

        binding.recyclerviewSearch.layoutManager = LinearLayoutManager(this)
    }

    private fun initSearchView() {
        binding.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    lifecycleScope.launch {
                        binding.recyclerviewSearch.adapter = BookSearchAdapter(bookAPIService.getBookSearch(query!!), this@BookSearchActivity)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    fun setBookInfo(book: Book) {
        intent.putExtra("isbn", book.isbn)
        intent.putExtra("title", book.title)
        intent.putExtra("author", book.author)
        intent.putExtra("categoryName", book.categoryName)
        intent.putExtra("cover", book.cover)
        setResult(RESULT_OK, intent)
        finish()
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