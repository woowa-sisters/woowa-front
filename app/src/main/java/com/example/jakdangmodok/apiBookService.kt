package com.example.jakdangmodok

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class apiBookService {
    private val gson = GsonBuilder().setLenient().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://www.aladin.co.kr/ttb/api/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val bookService = retrofit.create(BookService::class.java)

    public fun getBookSearch(query: String): List<Book> {
        val call = bookService.getBookSearch(TTBKEY, query, OUTPUT, VERSION)
        var bookList: List<Book> = listOf()

        call.enqueue(object: Callback<BookListDTO> {
            override fun onResponse(
                call: Call<BookListDTO>,
                response: Response<BookListDTO>
            ) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse(search): " + response.body())
                    bookList = response.body()!!.books
                } else {
                    Log.d("BookFragment", "fail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BookListDTO>, t: Throwable) {
                Log.d("BookFragment", "onFailure: ${t.message}")
            }
        })

        return bookList
    }

    suspend fun getBookList(): List<Book> {
        val bookList = bookService
            .getBookList(TTBKEY, QUERYTYPE, SEARCHTARGET, OUTPUT, VERSION)

        return bookList.body()!!.books
    }

    private companion object{
        private const val TTBKEY = "ttbnaahyin02016001"
        private const val QUERYTYPE = "ItemNewSpecial"
        private const val SEARCHTARGET = "Book"
        private const val OUTPUT = "JS"
        private const val VERSION = "20131101"
        private const val TAG = "BookSearchActivity"
    }
}