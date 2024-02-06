package com.example.jakdangmodok

import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
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

    // 로그아웃
    @POST("/v1/logout")
    fun logout(
        @Header("Authorization") authorization: String,
    ): Call<String>

    // 유저 정보 닉네임, 장르 추가
    @Headers("Content-Type: application/json")
    @POST("v1/signup")
    fun addUserInfo(
        @Body userInfo: UserInfoRequest,    // 모임명
    ): Call<String>

}

data class TokenRequest(val accessToken: String, val refreshToken: String)

data class UserInfoRequest(
    val token: String,
    val nickname: String,
    val jenre: List<Genre>
)

data class Genre(
    val genre: String,
    val isSelected: Boolean
)