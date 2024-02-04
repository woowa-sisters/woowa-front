package com.example.jakdangmodok

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class BookAPIService {
    private val gson = GsonBuilder().setLenient().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://www.aladin.co.kr/ttb/api/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val bookService = retrofit.create(BookService::class.java)

    suspend fun getBookSearch(query: String): List<Book> {
        val bookSearchList = bookService
            .getBookSearch(TTBKEY, query, OUTPUT, VERSION)

        return bookSearchList.body()!!.books
    }

    suspend fun getBookList(): List<Book> {
        val bookList = bookService
            .getBookList(TTBKEY, QUERY_TYPE, SEARCH_TARGET, OUTPUT, VERSION)

        return bookList.body()!!.books
    }

    suspend fun getBookDetail(itemid: String): Book {
        val bookDetail = bookService
            .getBookDetail(TTBKEY, itemid, ITEM_ID_TYPE, OUTPUT, VERSION)

        return bookDetail.body()!!.books[0]
    }

    private companion object{
        private const val TTBKEY = "ttbnaahyin02016001"
        private const val QUERY_TYPE = "ItemNewSpecial"
        private const val SEARCH_TARGET = "Book"
        private const val ITEM_ID_TYPE = "ISBN13"
        private const val OUTPUT = "JS"
        private const val VERSION = "20131101"
        private const val TAG = "BookSearchActivity"
    }
}