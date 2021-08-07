package com.example.pokemon.domain.usecase

import com.example.shared_common.data.model.PokemonDTO
import com.example.shared_common.data.model.ServiceErrorModel

interface PokemonUseCase {
    suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    )
}