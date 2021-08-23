package com.example.unit_test.detail.repository

import com.example.details_public.data.model.DetailPokemonDTO
import com.example.details_public.domain.DetailPokemonRepository

class FakeDetailPokemonRepository : DetailPokemonRepository{
    override suspend fun getPokemonDetail(endpoint: String): DetailPokemonDTO = DetailPokemonDTO()
}