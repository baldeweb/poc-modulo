package com.example.details.data

import com.example.details_public.data.model.DetailPokemonDTO
import com.example.shared_common.data.model.mock.LoadMock
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DetailPokemonAPI {

    @LoadMock("detail/detail.json")
    @GET
    suspend fun getPokemonDetail(@Url endpoint: String): Response<DetailPokemonDTO>
}