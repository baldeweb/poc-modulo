package com.example.pokemon.business.repository

import android.content.Context
import com.example.daycoval_service.DayFactory.Companion.serviceCaller
import com.example.daycoval_service.model.ServiceErrorModel
import com.example.daycoval_service.PokemonAPI
import com.example.daycoval_service.model.PokemonDTO

class PokemonRepositoryImpl(
    private val api: PokemonAPI,
    private val context: Context
) : BaseRepository<PokemonAPI>(api, context), PokemonRepository {
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