package com.example.pokemon.domain.repository

import com.example.daycoval_service.model.ServiceErrorModel
import com.example.daycoval_service.model.PokemonDTO

interface PokemonRepository {
    suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    )
}