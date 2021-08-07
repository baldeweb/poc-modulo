package com.example.shared_common.data.model


import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("abilities")
    var abilities: List<Any>? = null,
    @SerializedName("base_experience")
    var baseExperience: Int? = 0,
    @SerializedName("forms")
    var forms: List<Any>? = null,
    @SerializedName("game_indices")
    var gameIndices: List<Any>? = null,
    @SerializedName("height")
    var height: Int? = 0,
    @SerializedName("held_items")
    var heldItems: List<Any>? = null,
    @SerializedName("is_default")
    var isDefault: Boolean? = null,
    @SerializedName("location_area_encounters")
    var locationAreaEncounters: String? = "",
    @SerializedName("moves")
    var moves: List<Any>? = null,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("order")
    var order: Int? = 0,
    @SerializedName("past_types")
    var pastTypes: List<Any>? = null,
    @SerializedName("species")
    var species: Any? = null,
    @SerializedName("sprites")
    var sprites: Any? = null,
    @SerializedName("stats")
    var stats: List<Any>? = null,
    @SerializedName("types")
    var types: List<Any>? = null,
    @SerializedName("weight")
    var weight: Int? = 0
)