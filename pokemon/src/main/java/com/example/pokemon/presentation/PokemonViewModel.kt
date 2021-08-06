package com.example.pokemon.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemon.business.usecase.PokemonUseCase
import com.example.daycoval_service.model.PokemonDTO
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val useCase: PokemonUseCase
) : BaseViewModel() {
    private var _pokemon = MutableLiveData<PokemonDTO>()
    var pokemon : LiveData<PokemonDTO> = _pokemon

    fun getPokemon() {
        viewModelScope.launch {
            useCase.getPokemon({
                _pokemon.value = it
            }, {
                Log.d("LOG", "DEU RUIIIM: (${it.httpCode}) - ${it.throwable}")
            })
        }
    }
}