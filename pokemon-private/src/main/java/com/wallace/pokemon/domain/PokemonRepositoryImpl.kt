package com.wallace.pokemon.domain

import android.content.Context
import com.wallace.shared_service.repository.BaseRepository
import com.wallace.pokemon.data.PokemonAPI
import com.wallace.pokemon_public.domain.PokemonRepository

class PokemonRepositoryImpl(
    context: Context
) : BaseRepository<PokemonAPI>(context), PokemonRepository {
    override suspend fun getPokemon() = caller(PokemonAPI::getPokemon)
}