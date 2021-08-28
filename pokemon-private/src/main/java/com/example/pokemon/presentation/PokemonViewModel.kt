package com.example.pokemon.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.navigation.Actions.openDetailPokemon
import com.example.pokemon_public.domain.PokemonRepository
import com.example.pokemon_public.model.PokemonDTO
import com.example.shared_common.presentation.BaseViewModel
import com.example.storage.pokemon.PokemonDAO
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val repository: PokemonRepository,
    private val dao: PokemonDAO
) : BaseViewModel() {
    private var _pokemon = MutableLiveData<PokemonDTO>()
    var pokemon: LiveData<PokemonDTO> = _pokemon

    fun getPokemon() {
        viewModelScope.launch {
            serviceCaller(repository.getPokemon(), {
                _pokemon.value = it
                saveEndpoint(pokemon.value?.forms?.get(0)?.url ?: "")
            })
        }
    }

    fun redirectDetailPokemon(context: AppCompatActivity) {
        viewModelScope.launch {
            openDetailPokemon(context, getEndpoint())
        }
    }

    private suspend fun saveEndpoint(url: String) {
        dao.saveDetailPokemonUrl(url)
    }

    private suspend fun getEndpoint() = dao.getDetailPokemonUrl()
}