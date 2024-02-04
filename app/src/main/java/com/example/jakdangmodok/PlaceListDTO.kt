package com.example.jakdangmodok

import com.google.gson.annotations.SerializedName

data class PlaceListDTO (
    @SerializedName("items") val places: List<Place>
)