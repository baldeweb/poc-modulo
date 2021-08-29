package com.example.unit_test.pokemon.dao

import com.example.storage.pokemon.PokemonDAO

class FakePokemonDAO: PokemonDAO {
    private var endpoint = ""

    override suspend fun getDetailPokemonUrl(keyPreference: String): String {
        return endpoint
    }

    override suspend fun saveDetailPokemonUrl(url: String) {
        endpoint = url
    }
}