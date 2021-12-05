package com.wallace.pokemon.data

import com.wallace.shared_common.mock.LoadMock
import com.wallace.shared_domain.pokemon.PokemonDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    //@LoadMock("pokemon/pokemon.json")
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemon(@Path("pokemonName") pokemonName: String): Response<PokemonDTO>
}