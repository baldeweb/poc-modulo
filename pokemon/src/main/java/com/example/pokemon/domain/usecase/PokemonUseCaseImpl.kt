package com.example.pokemon.domain.usecase

import com.example.pokemon.domain.repository.PokemonRepository
import com.example.shared_common.data.model.PokemonDTO
import com.example.shared_common.data.model.ServiceErrorModel

class PokemonUseCaseImpl(private val repository: PokemonRepository): PokemonUseCase {
    override suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    ) {
        repository.getPokemon(onSuccess, onError)
    }
}