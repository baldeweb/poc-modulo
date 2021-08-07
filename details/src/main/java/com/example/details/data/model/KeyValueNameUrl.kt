package com.example.details.data.model

import com.google.gson.annotations.SerializedName

data class KeyValueNameUrl(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
)