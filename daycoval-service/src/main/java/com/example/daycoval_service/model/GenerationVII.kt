package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class GenerationVII(
    @SerializedName("icons")
    var icons: Icons? = null,
    @SerializedName("ultra-sun-ultra-moon")
    var ultraSunUltraMoon: UltraSunUltraMoon? = null
)