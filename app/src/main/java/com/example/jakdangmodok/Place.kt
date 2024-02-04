package com.example.jakdangmodok

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("address") val address: String,
    @SerializedName("roadAddress") val roadAddress: String
)