package com.wallace.pokemon_public.domain

import com.wallace.shared_domain.pokemon.PokemonDTO
import retrofit2.Response

interface PokemonRepository {
    suspend fun getPokemon(): Response<PokemonDTO>?
}