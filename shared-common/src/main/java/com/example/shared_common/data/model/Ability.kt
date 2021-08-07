package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("ability")
    var ability: AbilityX? = null,
    @SerializedName("is_hidden")
    var isHidden: Boolean? = false,
    @SerializedName("slot")
    var slot: Int? = 0
)