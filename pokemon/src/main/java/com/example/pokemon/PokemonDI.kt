package com.example.pokemon

import com.example.daycoval_service.BaseRepository
import com.example.pokemon.domain.repository.PokemonRepository
import com.example.pokemon.domain.repository.PokemonRepositoryImpl
import com.example.pokemon.domain.usecase.PokemonUseCase
import com.example.pokemon.domain.usecase.PokemonUseCaseImpl
import com.example.daycoval_service.BaseViewModel
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
            single { BaseRepository<Class<*>>(get()) }
            single<PokemonRepository> { PokemonRepositoryImpl(get()) }
        }
    }
}