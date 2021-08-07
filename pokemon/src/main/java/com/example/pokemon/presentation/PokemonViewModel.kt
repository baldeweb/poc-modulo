package com.example.pokemon.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.daycoval_service.presentation.BaseViewModel
import com.example.pokemon.domain.usecase.PokemonUseCase
import com.example.daycoval_service.data.model.PokemonDTO
import com.example.daycoval_service.presentation.DetailPokemonNavigation
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val navigation: DetailPokemonNavigation,
    private val useCase: PokemonUseCase
) : BaseViewModel() {
    private var _pokemon = MutableLiveData<PokemonDTO>()
    var pokemon : LiveData<PokemonDTO> = _pokemon

    fun getPokemon() {
        viewModelScope.launch(webServiceException) {
            useCase.getPokemon({
                _pokemon.value = it
            }, {
                Log.d("LOG", "DEU RUIIIM: (${it.httpCode}) - ${it.throwable}")
            })
        }
    }

    fun redirectDetailPokemon() {
        navigation.redirectDetailPokemon("pokemon-form/4/")
    }
}