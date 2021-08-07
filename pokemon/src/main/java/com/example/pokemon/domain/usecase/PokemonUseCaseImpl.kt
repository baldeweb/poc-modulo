package com.example.pokemon.domain.usecase

import com.example.daycoval_service.model.ServiceErrorModel
import com.example.pokemon.domain.repository.PokemonRepository
import com.example.daycoval_service.model.PokemonDTO

class PokemonUseCaseImpl(private val repository: PokemonRepository): PokemonUseCase {
    override suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    ) {
        //  TODO: realizar uma verificacao de online/offline
        repository.getPokemon(onSuccess, onError)
    }
}