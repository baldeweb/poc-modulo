package com.example.daycoval_service

import com.example.daycoval_service.model.PokemonDTO
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface PokemonAPI {

    @Headers("Content-Type: application/json")
    @GET("pokemon/charmander")
    fun getPokemon(): Deferred<Response<PokemonDTO>>
}