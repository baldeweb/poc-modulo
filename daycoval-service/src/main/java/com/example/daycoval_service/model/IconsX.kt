package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class IconsX(
    @SerializedName("front_default")
    var frontDefault: String? = "",
    @SerializedName("front_female")
    var frontFemale: String? = ""
)