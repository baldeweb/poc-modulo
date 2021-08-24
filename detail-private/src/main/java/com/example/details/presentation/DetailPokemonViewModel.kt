package com.example.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.details_public.data.model.DetailPokemonDTO
import com.example.details_public.domain.DetailPokemonRepository
import com.example.shared_common.presentation.BaseViewModel
import kotlinx.coroutines.launch

class DetailPokemonViewModel(
    private val repository: DetailPokemonRepository
) : BaseViewModel() {
    private var _pokemonDetail = MutableLiveData<DetailPokemonDTO>()
    var pokemonDetail: LiveData<DetailPokemonDTO> = _pokemonDetail

    fun getPokemonDetail(endpoint: String) {
        viewModelScope.launch {
            serviceCaller(repository.getPokemonDetail(endpoint)) {
                _pokemonDetail.value = it
            }
        }
    }
}