package com.example.daycoval_service.data.model


import com.google.gson.annotations.SerializedName

data class XY(
    @SerializedName("front_default")
    var frontDefault: String? = "",
    @SerializedName("front_female")
    var frontFemale: String? = "",
    @SerializedName("front_shiny")
    var frontShiny: String? = "",
    @SerializedName("front_shiny_female")
    var frontShinyFemale: String? = ""
)