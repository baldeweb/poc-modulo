package com.example.unit_test.pokemon.repository

import com.example.pokemon_public.domain.PokemonRepository
import com.example.pokemon_public.model.PokemonDTO

open class FakePokemonRepository : PokemonRepository {
    override suspend fun getPokemon(): PokemonDTO = PokemonDTO()
}