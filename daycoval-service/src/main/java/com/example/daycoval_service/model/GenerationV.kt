package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    var blackWhite: BlackWhite? = null
)