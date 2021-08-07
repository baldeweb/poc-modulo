package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class GenerationII(
    @SerializedName("crystal")
    var crystal: Crystal? = null,
    @SerializedName("gold")
    var gold: Gold? = null,
    @SerializedName("silver")
    var silver: Silver? = null
)