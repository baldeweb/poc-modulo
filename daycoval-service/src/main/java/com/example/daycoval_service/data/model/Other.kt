package com.example.daycoval_service.data.model


import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    var dreamWorld: DreamWorld? = null,
    @SerializedName("official-artwork")
    var officialArtwork: OfficialArtwork? = null
)