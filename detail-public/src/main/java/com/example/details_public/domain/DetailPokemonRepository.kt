package com.example.details_public.domain

interface DetailPokemonRepository {
    suspend fun getPokemonDetail(endpoint: String): Any?
}