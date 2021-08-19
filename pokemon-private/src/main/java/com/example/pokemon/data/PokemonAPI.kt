package com.example.pokemon.data

import com.example.pokemon_public.model.PokemonDTO
import com.example.shared_common.data.model.mock.LoadMock
import retrofit2.Response
import retrofit2.http.GET

interface PokemonAPI {

    @LoadMock("pokemon/pokemon.json")
    @GET("pokemon/charmander")
    suspend fun getPokemon(): Response<PokemonDTO>
}