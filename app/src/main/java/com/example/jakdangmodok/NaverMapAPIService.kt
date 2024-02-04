package com.example.jakdangmodok

import android.R.attr.text
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class NaverMapAPIService {
    private val gson = GsonBuilder().setLenient().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val mapService = retrofit.create(NaverMapService::class.java)

    suspend fun getMapSearch(query: String): List<Place> {
        val mapSearchList = mapService.getMapSearch(query)

        return mapSearchList.body()!!.places
    }

}