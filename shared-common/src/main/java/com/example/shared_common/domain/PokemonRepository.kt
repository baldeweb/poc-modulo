package com.example.shared_common.domain

import com.example.shared_common.data.model.PokemonDTO

interface PokemonRepository {
    suspend fun getPokemon(): PokemonDTO?
}