package com.example.details_public.domain

import com.example.details_public.data.model.DetailPokemonDTO

interface DetailPokemonRepository {
    suspend fun getPokemonDetail(endpoint: String): DetailPokemonDTO?
}