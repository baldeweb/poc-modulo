package com.example.details.data.model

import com.google.gson.annotations.SerializedName

data class TypeInfo(
    @SerializedName("slot")
    var slot: String? = "",
    @SerializedName("type")
    var type: KeyValueNameUrl? = null
)