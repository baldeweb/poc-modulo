package com.wallace.unit_test.detail.repository

import com.wallace.shared_domain.detail_pokemon.DetailPokemonDTO
import com.wallace.details_public.domain.DetailPokemonRepository
import retrofit2.Response

class FakeDetailPokemonRepository : DetailPokemonRepository {
    override suspend fun getPokemonDetail(endpoint: String):
            Response<DetailPokemonDTO>? = Response.success(
        DetailPokemonDTO()
    )
}