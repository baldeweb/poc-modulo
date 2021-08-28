package com.example.unit_test.detail.repository

import com.example.shared_domain.detail_pokemon.DetailPokemonDTO
import com.example.details_public.domain.DetailPokemonRepository
import retrofit2.Response

class FakeDetailPokemonRepository : DetailPokemonRepository {
    override suspend fun getPokemonDetail(endpoint: String):
            Response<com.example.shared_domain.detail_pokemon.DetailPokemonDTO>? = Response.success(
        com.example.shared_domain.detail_pokemon.DetailPokemonDTO()
    )
}