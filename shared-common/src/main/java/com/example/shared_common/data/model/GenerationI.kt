package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    var redBlue: RedBlue? = null,
    @SerializedName("yellow")
    var yellow: Yellow? = null
)