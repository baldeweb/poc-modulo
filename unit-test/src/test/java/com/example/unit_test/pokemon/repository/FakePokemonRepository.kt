package com.example.unit_test.pokemon.repository

import com.example.pokemon_public.domain.PokemonRepository
import com.example.shared_domain.pokemon.PokemonDTO
import retrofit2.Response

open class FakePokemonRepository : PokemonRepository {
    override suspend fun getPokemon(): Response<PokemonDTO> = Response.success(PokemonDTO())
}