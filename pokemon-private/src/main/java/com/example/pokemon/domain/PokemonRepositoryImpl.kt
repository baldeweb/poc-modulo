package com.example.pokemon.domain

import android.content.Context
import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.pokemon.data.PokemonAPI
import com.example.pokemon_public.domain.PokemonRepository

class PokemonRepositoryImpl(
    context: Context
) : BaseRepository<PokemonAPI>(context), PokemonRepository {
    override suspend fun getPokemon() = caller(PokemonAPI::getPokemon)
}