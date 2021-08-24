package com.example.details_public.domain

import com.example.details_public.data.model.DetailPokemonDTO
import retrofit2.Response

interface DetailPokemonRepository {
    suspend fun getPokemonDetail(endpoint: String): Response<DetailPokemonDTO>?
}