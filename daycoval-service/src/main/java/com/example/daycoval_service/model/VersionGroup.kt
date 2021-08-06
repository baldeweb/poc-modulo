package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class VersionGroup(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
)