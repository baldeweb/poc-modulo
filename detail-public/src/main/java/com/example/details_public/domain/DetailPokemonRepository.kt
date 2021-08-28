package com.example.details_public.domain

import com.example.shared_domain.detail_pokemon.DetailPokemonDTO
import retrofit2.Response

interface DetailPokemonRepository {
    suspend fun getPokemonDetail(endpoint: String): Response<DetailPokemonDTO>?
}