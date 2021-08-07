package com.example.pokemon.domain.repository

import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.daycoval_service.data.model.ServiceErrorModel
import com.example.daycoval_service.data.PokemonAPI
import com.example.daycoval_service.data.model.PokemonDTO

class PokemonRepositoryImpl(
    private val api: PokemonAPI
) : BaseRepository<PokemonAPI>(api), PokemonRepository {
    override suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    ) {
        serviceCaller(api.getPokemon().await(), {
            onSuccess.invoke(it)
        }, {
            onError.invoke(it)
        })
    }
}