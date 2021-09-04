package com.wallace.storage.pokemon

interface PokemonDAO {
    suspend fun getDetailPokemonUrl(keyPreference: String): String
    suspend fun saveDetailPokemonUrl(url: String)
}