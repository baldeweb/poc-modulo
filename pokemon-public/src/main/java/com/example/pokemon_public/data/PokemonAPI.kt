package com.example.pokemon_public.data

import com.example.pokemon_public.data.model.PokemonDTO
import retrofit2.Response
import retrofit2.http.GET

interface PokemonAPI {

    @GET("pokemon/charmander")
    suspend fun getPokemon(): Response<PokemonDTO>
}