package com.example.daycoval_service.data.model


import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat")
    var baseStat: Int? = 0,
    @SerializedName("effort")
    var effort: Int? = 0,
    @SerializedName("stat")
    var stat: StatX? = null
)