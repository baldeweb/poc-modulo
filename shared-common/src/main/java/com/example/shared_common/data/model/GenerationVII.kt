package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class GenerationVII(
    @SerializedName("icons")
    var icons: Icons? = null,
    @SerializedName("ultra-sun-ultra-moon")
    var ultraSunUltraMoon: UltraSunUltraMoon? = null
)