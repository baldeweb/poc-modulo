package com.example.pokemon.presentation

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
        viewModelScope.launch {
            serviceCaller(repository.getPokemon(), {
                _pokemon.value = it
            })
        }
    }

    fun redirectDetailPokemon() {
        navigation.redirectDetailPokemon(pokemon.value?.forms?.get(0)?.url ?: "")
    }
}