package com.wallace.unit_test.pokemon.repository

import com.wallace.pokemon_public.domain.PokemonRepository
import com.wallace.shared_domain.pokemon.PokemonDTO
import retrofit2.Response

open class FakePokemonRepository : PokemonRepository {
    override suspend fun getPokemon(): Response<PokemonDTO> = Response.success(PokemonDTO())
}