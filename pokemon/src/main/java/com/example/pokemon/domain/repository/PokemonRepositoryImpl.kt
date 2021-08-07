package com.example.pokemon.domain.repository

import com.example.daycoval_service.BaseRepository
import com.example.daycoval_service.model.ServiceErrorModel
import com.example.daycoval_service.PokemonAPI
import com.example.daycoval_service.model.PokemonDTO

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