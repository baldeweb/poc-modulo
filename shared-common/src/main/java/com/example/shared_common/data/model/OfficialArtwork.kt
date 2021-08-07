package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class OfficialArtwork(
    @SerializedName("front_default")
    var frontDefault: String? = ""
)