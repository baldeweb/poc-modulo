package com.example.pokemon.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.usecase.PokemonUseCase
import com.example.shared_common.data.dao.DataStorage
import com.example.shared_common.data.dao.PokemonEntity
import com.example.shared_common.data.model.PokemonDTO
import com.example.shared_common.presentation.BaseViewModel
import com.example.shared_common.presentation.DetailPokemonNavigation
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val navigation: DetailPokemonNavigation,
    private val useCase: PokemonUseCase
) : BaseViewModel() {
    private var _pokemon = MutableLiveData<PokemonDTO>()
    var pokemon: LiveData<PokemonDTO> = _pokemon

    fun getPokemon() {
        viewModelScope.launch(webServiceException) {
            useCase.getPokemon({
                _pokemon.value = it
//                launch { dataStorage.save(parseToEntity(it)) }
//                launch {
//                    val all = dataStorage.getAll()
//                    Log.d("LOG", "dataStorage.getAll(): $all")
//                }
            }, {
                Log.d("LOG", "DEU RUIIIM: (${it.httpCode}) - ${it.throwable}")
            })
        }
    }

    private fun parseToEntity(dto: PokemonDTO): PokemonEntity = PokemonEntity(
        dto.id ?: 0,
        dto.abilities.toString(),
        dto.baseExperience ?: 0,
        dto.forms.toString(),
        dto.gameIndices.toString(),
        dto.height ?: 0,
        dto.heldItems.toString(),
        dto.isDefault ?: false,
        dto.locationAreaEncounters ?: "",
        dto.moves.toString(),
        dto.name ?: "",
        dto.order ?: 0,
        dto.pastTypes.toString(),
        dto.species.toString(),
        dto.sprites.toString(),
        dto.stats.toString(),
        dto.types.toString(),
        dto.weight ?: 0
    )

    fun redirectDetailPokemon() {
        navigation.redirectDetailPokemon("pokemon-form/4/")
    }
}