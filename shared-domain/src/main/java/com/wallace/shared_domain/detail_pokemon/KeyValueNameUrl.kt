package com.wallace.shared_domain.detail_pokemon

import com.google.gson.annotations.SerializedName

data class KeyValueNameUrl(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
)