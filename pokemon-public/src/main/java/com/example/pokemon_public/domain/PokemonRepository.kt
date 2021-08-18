package com.example.pokemon_public.domain

import com.example.pokemon_public.model.PokemonDTO

interface PokemonRepository {
    suspend fun getPokemon(): PokemonDTO?
}