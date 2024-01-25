package com.example.jakdangmodok

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    // 알라딘 책 목록 api
    @GET("ItemList.aspx")
    fun getBookList(
        @Query("TTBKey") ttbkey: String,
        @Query("QueryType") querytype: String,
        @Query("Query") query: String
    ): Call<String>

}