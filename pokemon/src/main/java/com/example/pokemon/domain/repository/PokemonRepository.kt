package com.example.pokemon.domain.repository

import com.example.daycoval_service.data.model.ServiceErrorModel
import com.example.daycoval_service.data.model.PokemonDTO

interface PokemonRepository {
    suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    )
}