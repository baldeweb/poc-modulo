package com.example.daycoval_service.data.model


import com.google.gson.annotations.SerializedName

data class Icons(
    @SerializedName("front_default")
    var frontDefault: String? = "",
    @SerializedName("front_female")
    var frontFemale: String? = ""
)