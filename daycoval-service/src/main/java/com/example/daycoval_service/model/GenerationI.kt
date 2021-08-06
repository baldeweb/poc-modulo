package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    var redBlue: RedBlue? = null,
    @SerializedName("yellow")
    var yellow: Yellow? = null
)