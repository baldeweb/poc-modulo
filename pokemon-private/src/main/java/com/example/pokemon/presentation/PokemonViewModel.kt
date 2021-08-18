package com.example.pokemon.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.details_public.presentation.DetailPokemonNavigation
import com.example.pokemon_public.domain.PokemonRepository
import com.example.pokemon_public.model.PokemonDTO
import com.example.shared_common.presentation.BaseViewModel
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val navigation: DetailPokemonNavigation,
    private val repository: PokemonRepository
) : BaseViewModel() {
    private var _pokemon = MutableLiveData<PokemonDTO>()
    var pokemon: LiveData<PokemonDTO> = _pokemon

    fun getPokemon() {
        Log.d("LOG", "SHOW LOADING")
        viewModelScope.launch(webServiceException) {
            val response = repository.getPokemon()
            _pokemon.value = response
            Log.d("LOG", "getPokemon(): $response")
            Log.d("LOG", "DISMISS LOADING")
        }
    }

    fun redirectDetailPokemon() {
        navigation.redirectDetailPokemon(pokemon.value?.forms?.get(0)?.url ?: "")
    }
}