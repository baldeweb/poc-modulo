package com.example.details_public.data

import com.example.details_public.data.model.DetailPokemonDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DetailPokemonAPI {

    @GET
    suspend fun getPokemonDetail(@Url endpoint: String): Response<DetailPokemonDTO>
}