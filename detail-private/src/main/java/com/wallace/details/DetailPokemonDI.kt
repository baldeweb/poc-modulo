package com.wallace.details

import com.wallace.details_public.domain.DetailPokemonRepository
import com.wallace.details.domain.DetailPokemonRepositoryImpl
import com.wallace.details.presentation.DetailPokemonViewModel
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