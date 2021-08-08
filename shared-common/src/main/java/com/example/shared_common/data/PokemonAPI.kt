package com.example.shared_common.data

import com.example.shared_common.data.model.PokemonDTO
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PokemonAPI {

    @GET("pokemon/charmander")
    suspend fun getPokemon(): Deferred<Response<PokemonDTO>>
}