package com.example.daycoval_service.data.model


import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("abilities")
    var abilities: List<Ability>? = null,
    @SerializedName("base_experience")
    var baseExperience: Int? = 0,
    @SerializedName("forms")
    var forms: List<Form>? = null,
    @SerializedName("game_indices")
    var gameIndices: List<GameIndice>? = null,
    @SerializedName("height")
    var height: Int? = 0,
    @SerializedName("held_items")
    var heldItems: List<Any>? = null,
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("is_default")
    var isDefault: Boolean? = null,
    @SerializedName("location_area_encounters")
    var locationAreaEncounters: String? = "",
    @SerializedName("moves")
    var moves: List<Move>? = null,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("order")
    var order: Int? = 0,
    @SerializedName("past_types")
    var pastTypes: List<Any>? = null,
    @SerializedName("species")
    var species: Species? = null,
    @SerializedName("sprites")
    var sprites: Sprites? = null,
    @SerializedName("stats")
    var stats: List<Stat>? = null,
    @SerializedName("types")
    var types: List<Type>? = null,
    @SerializedName("weight")
    var weight: Int? = 0
)