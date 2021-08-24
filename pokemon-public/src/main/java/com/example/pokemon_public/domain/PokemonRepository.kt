package com.example.pokemon_public.domain

import com.example.pokemon_public.model.PokemonDTO
import retrofit2.Response

interface PokemonRepository {
    suspend fun getPokemon(): Response<PokemonDTO>?
}