package com.example.pokemon.domain.repository

import android.content.Context
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.daycoval_service.data.model.ServiceErrorModel
import com.example.daycoval_service.data.PokemonAPI
import com.example.daycoval_service.data.model.PokemonDTO

class PokemonRepositoryImpl(
    context: Context
) : BaseRepository<PokemonAPI>(context), PokemonRepository {
    override suspend fun getPokemon(
        onSuccess: (PokemonDTO) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    ) {
        serviceCaller(create<PokemonAPI>().getPokemon().await(), {
            onSuccess.invoke(it)
        }, {
            onError.invoke(it)
        })
    }
}