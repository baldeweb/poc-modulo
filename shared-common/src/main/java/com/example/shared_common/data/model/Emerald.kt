package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class Emerald(
    @SerializedName("front_default")
    var frontDefault: String? = "",
    @SerializedName("front_shiny")
    var frontShiny: String? = ""
)