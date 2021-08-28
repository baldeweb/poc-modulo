package com.example.pokemon_public.domain

import com.example.shared_domain.pokemon.PokemonDTO
import retrofit2.Response

interface PokemonRepository {
    suspend fun getPokemon(): Response<PokemonDTO>?
}