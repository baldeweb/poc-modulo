package com.example.pokemon.domain

import android.content.Context
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.pokemon.domain.PokemonRepository
import com.example.shared_common.data.PokemonAPI
import com.example.shared_common.data.model.PokemonDTO
import com.example.shared_common.data.model.ServiceErrorModel

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