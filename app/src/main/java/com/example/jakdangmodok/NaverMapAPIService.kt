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
        .baseUrl("https://openapi.naver.com/v1/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val mapService = retrofit.create(NaverMapService::class.java)

    fun getMapSearch(query: String): String? {
        val mapSearchList = mapService.getMapSearch(query, "sjrtcbmv0g", "3HZn8KdOylW0kzl1r6KHl4U3APcDk5l0VLKe7zmm")

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

    fun getMapSearch2(query: String) {
        val apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text // JSON 결과

        val requestHeaders: MutableMap<String, String> = HashMap()
        requestHeaders["X-Naver-Client-Id"] = "sjrtcbmv0g"
        requestHeaders["X-Naver-Client-Secret"] = "3HZn8KdOylW0kzl1r6KHl4U3APcDk5l0VLKe7zmm"

        val url: URL = URL(apiURL)

        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        try {
            con.requestMethod = "GET"
            for ((key, value) in requestHeaders) {
                con.setRequestProperty(key, value)
            }
            val responseCode = con.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                readBody(con.inputStream)
            } else { // 오류 발생
                readBody(con.errorStream)
            }
        } catch (e: IOException) {
            throw RuntimeException("API 요청과 응답 실패", e)
        } finally {
            con.disconnect()
        }
    }

    private fun readBody(body: InputStream): String? {
        val streamReader = InputStreamReader(body)
        try {
            BufferedReader(streamReader).use { lineReader ->
                val responseBody = StringBuilder()
                var line: String?
                while (lineReader.readLine().also { line = it } != null) {
                    responseBody.append(line)
                }
                return responseBody.toString()
            }
        } catch (e: IOException) {
            throw java.lang.RuntimeException("API 응답을 읽는 데 실패했습니다.", e)
        }
    }

}