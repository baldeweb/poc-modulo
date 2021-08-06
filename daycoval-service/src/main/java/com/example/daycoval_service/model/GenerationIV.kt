package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class GenerationIV(
    @SerializedName("diamond-pearl")
    var diamondPearl: DiamondPearl? = null,
    @SerializedName("heartgold-soulsilver")
    var heartgoldSoulsilver: HeartgoldSoulsilver? = null,
    @SerializedName("platinum")
    var platinum: Platinum? = null
)