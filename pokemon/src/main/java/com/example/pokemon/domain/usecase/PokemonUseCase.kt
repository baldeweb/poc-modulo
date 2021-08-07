package com.example.pokemon.domain.usecase

import com.example.daycoval_service.data.model.ServiceErrorModel
import com.example.daycoval_service.data.model.PokemonDTO

interface PokemonUseCase {
    suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    )
}