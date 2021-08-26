package com.example.details

import com.example.details_public.domain.DetailPokemonRepository
import com.example.details.domain.DetailPokemonRepositoryImpl
import com.example.details.presentation.DetailPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class DetailPokemonDI {
    fun getModule() : Module {
        return module {
            viewModel { DetailPokemonViewModel(get()) }
            single<DetailPokemonRepository> { DetailPokemonRepositoryImpl(get()) }
        }
    }
}