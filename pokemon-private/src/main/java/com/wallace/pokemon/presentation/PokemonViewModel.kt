package com.wallace.pokemon.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wallace.navigation.IntentActions.openDetailPokemon
import com.wallace.pokemon_public.domain.PokemonRepository
import com.wallace.shared_common.presentation.BaseViewModel
import com.wallace.shared_domain.pokemon.PokemonDTO
import com.wallace.storage.pokemon.PokemonDAO
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val repository: PokemonRepository,
    private val dao: PokemonDAO
) : BaseViewModel() {
    private var _pokemon = MutableLiveData<PokemonDTO>()
    var pokemon: LiveData<PokemonDTO> = _pokemon

    fun getPokemon() {
        viewModelScope.launch(apiException) {
            serviceCaller(repository.getPokemon())?.let { _pokemon.value = it  }
            saveEndpoint(pokemon.value?.forms?.first()?.url ?: "")
        }
    }

    fun redirectDetailPokemon(context: AppCompatActivity) {
        viewModelScope.launch {
            openDetailPokemon(context)
        }
    }

    private suspend fun saveEndpoint(url: String) {
        dao.saveDetailPokemonUrl(url)
    }
}