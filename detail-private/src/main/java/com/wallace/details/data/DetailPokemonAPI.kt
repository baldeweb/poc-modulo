package com.wallace.details.data

import com.wallace.shared_domain.detail_pokemon.DetailPokemonDTO
import com.wallace.shared_common.mock.LoadMock
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DetailPokemonAPI {

    @LoadMock("detail/detail.json")
    @GET
    suspend fun getPokemonDetail(@Url endpoint: String): Response<DetailPokemonDTO>
}