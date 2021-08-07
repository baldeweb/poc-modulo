package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class GenerationIII(
    @SerializedName("emerald")
    var emerald: Emerald? = null,
    @SerializedName("firered-leafgreen")
    var fireredLeafgreen: FireredLeafgreen? = null,
    @SerializedName("ruby-sapphire")
    var rubySapphire: RubySapphire? = null
)