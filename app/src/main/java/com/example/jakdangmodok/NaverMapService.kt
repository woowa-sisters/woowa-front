package com.example.jakdangmodok

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverMapService {

    // 네이버 지도 장소 검색 api
    @GET("/v1/search/local.json")
    suspend fun getMapSearch(
        @Query("query") query: String,      // 검색어
        @Query("display") display: Int = 5,    // 검색 결과 출력 건수
        @Header("X-Naver-Client-Id") clientId: String = "IEVypWnAYI9BtTLNGSFw",    // 검색 클라이언트 아이디
        @Header("X-Naver-Client-Secret") clientSecret: String = "jltSefe3KN",     // 검색 클라이언트 시크릿
    ): Response<PlaceListDTO>

}