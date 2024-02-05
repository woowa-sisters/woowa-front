package com.example.jakdangmodok

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    // 구글 로그인
    @FormUrlEncoded
    @POST("oauth2/v4/token")
    suspend fun loginGoogle(
        @Field("grant_type") grant_type: String,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String,
    ): Response<LoginGoogleResponse>

}