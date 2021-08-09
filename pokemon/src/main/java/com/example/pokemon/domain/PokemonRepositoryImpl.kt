package com.example.pokemon.domain

import android.content.Context
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.shared_common.data.PokemonAPI
import com.example.shared_common.data.model.PokemonDTO
import com.example.shared_common.domain.PokemonRepository

class PokemonRepositoryImpl(
    context: Context
) : BaseRepository<PokemonAPI>(context), PokemonRepository {
    override suspend fun getPokemon(): PokemonDTO? = create<PokemonAPI>().getPokemon().body()
}