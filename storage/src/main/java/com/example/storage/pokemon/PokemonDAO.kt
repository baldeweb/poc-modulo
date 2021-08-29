package com.example.storage.pokemon

interface PokemonDAO {
    suspend fun getDetailPokemonUrl(keyPreference: String): String
    suspend fun saveDetailPokemonUrl(url: String)
}