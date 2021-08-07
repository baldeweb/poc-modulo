package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index")
    var gameIndex: Int? = 0,
    @SerializedName("version")
    var version: Version? = null
)