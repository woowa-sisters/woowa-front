package com.example.jakdangmodok

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookService {

    // 알라딘 책 검색 api
    @GET("ItemSearch.aspx")
    fun getBookSearch(
        @Query("TTBKey") ttbkey: String,    // 알라딘 api key
        @Query("Query") query: String,  // 검색어
        @Query("Output") output: String,    // 출력 형식
        @Query("Version") version: String
    ): Call<BookListDTO>

    // 알라딘 책 목록 api
    @GET("ItemList.aspx")
    suspend fun getBookList(
        @Query("TTBKey") ttbkey: String,
        @Query("QueryType") querytype: String,
        @Query("SearchTarget") searchtarget: String,
        @Query("Output") output: String,
        @Query("Version") version: String
    ): Response<BookListDTO>

}