package com.example.jakdangmodok

import com.google.gson.annotations.SerializedName

data class BookListDTO(
    @SerializedName("item") val books: String
)