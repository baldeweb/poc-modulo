package com.wallace.pokemon

import com.wallace.pokemon.domain.PokemonRepositoryImpl
import com.wallace.pokemon.presentation.PokemonViewModel
import com.wallace.pokemon_public.domain.PokemonRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class PokemonDI {
    fun getModule() : Module {
        return module {
            viewModel { PokemonViewModel(get(), get()) }
            single<PokemonRepository> { PokemonRepositoryImpl(get()) }
        }
    }
}