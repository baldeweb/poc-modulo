package com.example.daycoval_service.data.model


import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-i")
    var generationI: GenerationI? = null,
    @SerializedName("generation-ii")
    var generationIi: GenerationII? = null,
    @SerializedName("generation-iii")
    var generationIii: GenerationIII? = null,
    @SerializedName("generation-iv")
    var generationIv: GenerationIV? = null,
    @SerializedName("generation-v")
    var generationV: GenerationV? = null,
    @SerializedName("generation-vi")
    var generationVi: GenerationVI? = null,
    @SerializedName("generation-vii")
    var generationVii: GenerationVII? = null,
    @SerializedName("generation-viii")
    var generationViii: GenerationVIII? = null
)