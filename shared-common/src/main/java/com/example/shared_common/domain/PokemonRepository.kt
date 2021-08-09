package com.example.shared_common.domain

import com.example.shared_common.data.model.PokemonDTO
import com.example.shared_common.data.model.ServiceErrorModel

interface PokemonRepository {
    suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    )
}