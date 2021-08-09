package com.example.shared_common.domain

import com.example.shared_common.data.model.DetailPokemonDTO

interface DetailPokemonRepository {
    suspend fun getPokemonDetail(endpoint: String): DetailPokemonDTO?
}