package com.example.details

import com.example.shared_common.presentation.navigation.DetailPokemonNavigation
import com.example.shared_common.domain.DetailPokemonRepository
import com.example.details.domain.DetailPokemonRepositoryImpl
import com.example.details.presentation.navigation.DetailPokemonNavigationImpl
import com.example.details.presentation.DetailPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class DetailPokemonDI {
    fun getModule() : Module {
        return module {
            viewModel { DetailPokemonViewModel(get()) }
            single<DetailPokemonNavigation>{ DetailPokemonNavigationImpl(get()) }
            single<DetailPokemonRepository> { DetailPokemonRepositoryImpl(get()) }
        }
    }
}