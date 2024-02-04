package com.example.jakdangmodok

import com.google.gson.annotations.SerializedName

data class PlaceListDTO (
    @SerializedName("item") val places: List<Place>
)