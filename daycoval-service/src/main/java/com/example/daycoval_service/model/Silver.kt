package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class Silver(
    @SerializedName("back_default")
    var backDefault: String? = "",
    @SerializedName("back_shiny")
    var backShiny: String? = "",
    @SerializedName("front_default")
    var frontDefault: String? = "",
    @SerializedName("front_shiny")
    var frontShiny: String? = ""
)