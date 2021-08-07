package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    var dreamWorld: DreamWorld? = null,
    @SerializedName("official-artwork")
    var officialArtwork: OfficialArtwork? = null
)