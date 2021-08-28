package com.example.pokemon.data

import com.example.shared_common.mock.LoadMock
import com.example.shared_domain.pokemon.PokemonDTO
import retrofit2.Response
import retrofit2.http.GET

interface PokemonAPI {

    @LoadMock("pokemon/pokemon.json")
    @GET("pokemon/charmander")
    suspend fun getPokemon(): Response<PokemonDTO>
}