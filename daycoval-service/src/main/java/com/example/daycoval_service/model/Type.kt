package com.example.daycoval_service.model


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot")
    var slot: Int? = 0,
    @SerializedName("type")
    var type: TypeX? = null
)