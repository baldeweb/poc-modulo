package com.wallace.shared_domain.detail_pokemon

import com.google.gson.annotations.SerializedName

data class TypeInfo(
    @SerializedName("slot")
    var slot: String? = "",
    @SerializedName("type")
    var type: KeyValueNameUrl? = null
)