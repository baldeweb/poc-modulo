package com.wallace.shared_domain.detail_pokemon

import com.google.gson.annotations.SerializedName

data class DetailPokemonDTO (
    @SerializedName("form_name")
    var formName: String? = "",
    @SerializedName("form_names")
    var form_names: ArrayList<Any>? = arrayListOf(),
    @SerializedName("form_order")
    var formOrder: Int? = 0,
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("is_battle_only")
    var isBattleOnly: Boolean? = false,
    @SerializedName("is_default")
    var isDefault: Boolean? = false,
    @SerializedName("is_mega")
    var isMega: Boolean? = false,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("names")
    var names: ArrayList<Any>? = arrayListOf(),
    @SerializedName("order")
    var order: Int? = 0,
    @SerializedName("pokemon")
    var pokemon: KeyValueNameUrl? = null,
    @SerializedName("sprites")
    var sprites: Any? = null,
    @SerializedName("types")
    var types: ArrayList<TypeInfo>? = arrayListOf(),
    @SerializedName("version_group")
    var versionGroup: KeyValueNameUrl? = null
)