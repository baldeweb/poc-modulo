package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class StatX(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
)