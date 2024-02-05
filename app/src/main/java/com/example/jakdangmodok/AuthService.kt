package com.example.jakdangmodok

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.Date

interface AuthService {

    // 유저 정보 생성
    @FormUrlEncoded
    @POST("v1/token/save")
    fun createUser(
        @Field("tokenValue") tokenValue: String,    // 모임명
    ): Call<String>

}