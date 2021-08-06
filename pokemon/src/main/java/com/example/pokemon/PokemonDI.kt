package com.example.pokemon

import com.example.pokemon.business.repository.BaseRepository
import com.example.pokemon.business.repository.PokemonRepository
import com.example.pokemon.business.repository.PokemonRepositoryImpl
import com.example.pokemon.business.usecase.PokemonUseCase
import com.example.pokemon.business.usecase.PokemonUseCaseImpl
import com.example.daycoval_service.PokemonAPI
import com.example.pokemon.presentation.BaseViewModel
import com.example.pokemon.presentation.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class PokemonDI {
    fun getModule() : Module {
        return module {
            viewModel { BaseViewModel() }
            viewModel { PokemonViewModel(get()) }
            single<PokemonUseCase> { PokemonUseCaseImpl(get()) }
            single { BaseRepository<PokemonAPI>(get(), get()) }
            single<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }
        }
    }
}