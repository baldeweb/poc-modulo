package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class Gold(
    @SerializedName("back_default")
    var backDefault: String? = "",
    @SerializedName("back_shiny")
    var backShiny: String? = "",
    @SerializedName("front_default")
    var frontDefault: String? = "",
    @SerializedName("front_shiny")
    var frontShiny: String? = ""
)