package com.example.jakdangmodok

import org.json.JSONStringer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.Date

interface AuthService {

    // 유저 정보 생성
    @Headers("Content-Type: application/json")
    @POST("v1/token/save")
    fun createUser(
        @Body tokenValue: TokenRequest,    // 모임명
    ): Call<String>

}

data class TokenRequest(val tokenValue: String)