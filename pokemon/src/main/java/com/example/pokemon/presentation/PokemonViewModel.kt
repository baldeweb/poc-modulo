package com.example.pokemon.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shared_common.domain.PokemonRepository
import com.example.shared_common.data.model.PokemonDTO
import com.example.shared_common.presentation.BaseViewModel
import com.example.shared_common.presentation.navigation.DetailPokemonNavigation
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val navigation: DetailPokemonNavigation,
    private val repository: PokemonRepository
) : BaseViewModel() {
    private var _pokemon = MutableLiveData<PokemonDTO>()
    var pokemon: LiveData<PokemonDTO> = _pokemon

    fun getPokemon() {
        viewModelScope.launch {
            repository.getPokemon({
                _pokemon.value = it
            }, {
                Log.d("LOG", "DEU RUIIIM: (${it.httpCode}) - ${it.throwable}")
            })
        }
    }

    fun redirectDetailPokemon() {
        navigation.redirectDetailPokemon(pokemon.value?.forms?.get(0)?.url ?: "")
    }
}