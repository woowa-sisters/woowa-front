package com.example.jakdangmodok

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.Date

interface GroupService {

    // 모임 생성
    @FormUrlEncoded
    @POST("api/meetings/")
    fun createGroup(
        @Field("meetingTitle") meetingTitle: String,    // 모임명
        @Field("userUuid") userUuid: String,        // 사용자 UUID
        @Field("meetingAttendees") meetingAttendees: Int,    // 참석자 수
        @Field("meetingTime") meetingTime: Date,        // 모임날
        @Field("meetingLocation") meetingLocation: String,        // 모임 장소
        @Field("meetingContent") meetingContent: String,        // 모임 설명
    ): Call<String>

}