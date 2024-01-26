package com.example.jakdangmodok

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("description") val description: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("categoryName") val categoryName: String
)