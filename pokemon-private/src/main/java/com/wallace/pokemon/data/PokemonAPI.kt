package com.wallace.pokemon.data

import com.wallace.shared_common.mock.LoadMock
import com.wallace.shared_domain.pokemon.PokemonDTO
import retrofit2.Response
import retrofit2.http.GET

interface PokemonAPI {

    @LoadMock("pokemon/pokemon.json")
    @GET("pokemon/charmander")
    suspend fun getPokemon(): Response<PokemonDTO>
}