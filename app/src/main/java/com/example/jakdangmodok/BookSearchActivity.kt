package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityBookSearchBinding
import com.example.jakdangmodok.databinding.FragmentBookBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class BookSearchActivity : AppCompatActivity() {

    private val gson = GsonBuilder().setLenient().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://www.aladin.co.kr/ttb/api/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val bookService = retrofit.create(BookService::class.java)
    private val binding = ActivityBookSearchBinding.inflate(layoutInflater)

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
                    getBookSearch(query!!)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    private fun getBookSearch(query: String) {
        val call = bookService.getBookSearch(TTBKEY, query, OUTPUT, VERSION)

        call.enqueue(object: Callback<BookListDTO> {
            override fun onResponse(
                call: Call<BookListDTO>,
                response: Response<BookListDTO>
            ) {
                if (response.isSuccessful) {
                    binding.recyclerviewSearch.adapter = BookAdapter(response.body()!!.books)
                    Log.e(TAG, "onResponse(search): " + response.body());
                } else {
                    Log.d("BookFragment", "fail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BookListDTO>, t: Throwable) {
                Log.d("BookFragment", "onFailure: ${t.message}")
            }
        })
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

    companion object{
        private const val TTBKEY = "ttbnaahyin02016001"
        private const val QUERYTYPE = "ItemNewSpecial"
        private const val SEARCHTARGET = "Book"
        private const val OUTPUT = "JS"
        private const val VERSION = "20131101"
        private const val TAG = "BookSearchActivity"
    }

}