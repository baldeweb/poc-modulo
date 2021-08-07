package com.example.daycoval_service.data.model


import com.google.gson.annotations.SerializedName

data class Species(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
)