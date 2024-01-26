package com.example.jakdangmodok

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentBookBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class BookFragment : Fragment() {

    private val gson = GsonBuilder().setLenient().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://www.aladin.co.kr/ttb/api/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val bookService = retrofit.create(BookService::class.java)

    private val filterList: Array<String> = arrayOf("최신순", "마감순", "거리순")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookBinding.inflate(inflater, container, false)

        getBookList(binding)
        //getBookSearch("안드로이드")

        val filterAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, filterList)
        filterAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.recyclerviewBook.layoutManager = LinearLayoutManager(activity)
        binding.spinnerBook.adapter = filterAdapter

        return binding.root
    }

    // 책 검색
    private fun getBookSearch(query: String) {
        val call = bookService.getBookSearch(TTBKEY, query)

        call.enqueue(object: Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse(search): " + response.body());
                } else {
                    Log.d("BookFragment", "fail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("BookFragment", "onFailure: ${t.message}")
            }
        })
    }

    // 책 목록
    private fun getBookList(binding: FragmentBookBinding) {

        val call = bookService.getBookList(TTBKEY, QUERYTYPE, SEARCHTARGET, OUTPUT, VERSION)
        var bookList: List<Book> = listOf()

        call.enqueue(object: Callback<BookListDTO> {
            override fun onResponse(
                call: Call<BookListDTO>,
                response: Response<BookListDTO>
            ) {
                if (response.isSuccessful) {
                    binding.recyclerviewBook.adapter = BookAdapter(response.body()!!.books)
                    Log.e(TAG, "onResponse(list): " + (response.body()!!.books[0].title));
                } else {
                    Log.d("BookFragment", "fail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BookListDTO>, t: Throwable) {
                Log.d("BookFragment", "onFailure: ${t.message}")
            }
        })
    }

    companion object{
        private const val TTBKEY = "ttbnaahyin02016001"
        private const val QUERYTYPE = "ItemNewSpecial"
        private const val SEARCHTARGET = "Book"
        private const val OUTPUT = "JS"
        private const val VERSION = "20131101"
        private const val TAG = "MainActivity"
    }

}