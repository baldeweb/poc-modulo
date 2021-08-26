package com.example.pokemon

import com.example.pokemon.domain.PokemonRepositoryImpl
import com.example.pokemon.presentation.PokemonViewModel
import com.example.pokemon_public.domain.PokemonRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class PokemonDI {
    fun getModule() : Module {
        return module {
            viewModel { PokemonViewModel(get()) }
            single<PokemonRepository> { PokemonRepositoryImpl(get()) }
        }
    }
}