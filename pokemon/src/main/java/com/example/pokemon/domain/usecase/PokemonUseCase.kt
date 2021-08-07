package com.example.pokemon.domain.usecase

import com.example.daycoval_service.model.ServiceErrorModel
import com.example.daycoval_service.model.PokemonDTO

interface PokemonUseCase {
    suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    )
}