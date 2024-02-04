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

    fun getMapSearch(query: String): String? {
        val mapSearchList = mapService.getMapSearch(query)

        mapSearchList.enqueue(object: retrofit2.Callback<String> {
            override fun onResponse(call: retrofit2.Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    Log.e("result", result.toString())
                } else {
                    Log.e("error", response.code().toString())
                    Log.e("error", response.toString())
                }
            }

            override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                Log.e("error", t.toString())
            }
        })

        return "ss"
    }

}