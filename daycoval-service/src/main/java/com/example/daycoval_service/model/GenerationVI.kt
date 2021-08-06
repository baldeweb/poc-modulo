package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class GenerationVI(
    @SerializedName("omegaruby-alphasapphire")
    var omegarubyAlphasapphire: OmegarubyAlphasapphire? = null,
    @SerializedName("x-y")
    var xY: XY? = null
)