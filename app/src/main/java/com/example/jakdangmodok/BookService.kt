package com.example.jakdangmodok

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    // 알라딘 책 검색 api
    @GET("ItemSearch.aspx")
    suspend fun getBookSearch(
        @Query("TTBKey") ttbkey: String,    // 알라딘 api key
        @Query("Query") query: String,      // 검색어
        @Query("Output") output: String,    // 출력 형식
        @Query("Version") version: String   // api 버전
    ): Response<BookListDTO>

    // 알라딘 책 목록 api
    @GET("ItemList.aspx")
    suspend fun getBookList(
        @Query("TTBKey") ttbkey: String,
        @Query("QueryType") querytype: String,
        @Query("SearchTarget") searchtarget: String,
        @Query("Output") output: String,
        @Query("Version") version: String
    ): Response<BookListDTO>

    // 알라딘 책 상세 api
    @GET("ItemLookUp.aspx")
    suspend fun getBookDetail(
        @Query("TTBKey") ttbkey: String,
        @Query("ItemId") itemid: String,
        @Query("itemIdType") itemidtype: String,
        @Query("Output") output: String,
        @Query("Version") version: String
    ): Response<BookListDTO>

}