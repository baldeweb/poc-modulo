package com.example.shared_common.data

import com.example.shared_common.data.model.DetailPokemonDTO
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DetailPokemonAPI {

    @GET
    suspend fun getPokemonDetail(@Url endpoint: String): Deferred<Response<DetailPokemonDTO>>
}