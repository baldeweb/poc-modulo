package com.wallace.shared_domain.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("abilities")
    var abilities: ArrayList<Any>? = arrayListOf(),
    @SerializedName("base_experience")
    var baseExperience: Int? = 0,
    @SerializedName("forms")
    var forms: ArrayList<Form>? = arrayListOf(),
    @SerializedName("game_indices")
    var gameIndices: ArrayList<Any>? = arrayListOf(),
    @SerializedName("height")
    var height: Int? = 0,
    @SerializedName("held_items")
    var heldItems: ArrayList<Any>? = arrayListOf(),
    @SerializedName("is_default")
    var isDefault: Boolean? = null,
    @SerializedName("location_area_encounters")
    var locationAreaEncounters: String? = "",
    @SerializedName("moves")
    var moves: ArrayList<Any>? = arrayListOf(),
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("order")
    var order: Int? = 0,
    @SerializedName("past_types")
    var pastTypes: ArrayList<Any>? = arrayListOf(),
    @SerializedName("species")
    var species: Any? = null,
    @SerializedName("sprites")
    var sprites: Any? = null,
    @SerializedName("stats")
    var stats: ArrayList<Any>? = arrayListOf(),
    @SerializedName("types")
    var types: ArrayList<Any>? = arrayListOf(),
    @SerializedName("weight")
    var weight: Int? = 0
)