package com.example.pokemon_public.model

import com.google.gson.annotations.SerializedName

data class Form(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
)