package com.example.storage.pokemon

interface PokemonDAO {
    suspend fun getDetailPokemonUrl(): String
    suspend fun saveDetailPokemonUrl(url: String)
}