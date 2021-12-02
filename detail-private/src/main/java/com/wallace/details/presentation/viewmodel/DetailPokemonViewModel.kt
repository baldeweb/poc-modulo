package com.wallace.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wallace.shared_domain.detail_pokemon.DetailPokemonDTO
import com.wallace.details_public.domain.DetailPokemonRepository
import com.wallace.shared_common.presentation.BaseViewModel
import com.wallace.storage.dao.ConstantsDAO.ENDPOINT_URL_PREFS
import com.wallace.storage.pokemon.PokemonDAO
import kotlinx.coroutines.launch

class DetailPokemonViewModel(
    private val repository: DetailPokemonRepository,
    private val dao: PokemonDAO
) : BaseViewModel() {
    private var _pokemonDetail = MutableLiveData<DetailPokemonDTO>()
    var pokemonDetail: LiveData<DetailPokemonDTO> = _pokemonDetail

    private suspend fun getEndpoint() = dao.getDetailPokemonUrl(ENDPOINT_URL_PREFS)

    fun getPokemonDetail() {
        viewModelScope.launch(apiException) {
            serviceCaller(repository.getPokemonDetail(getEndpoint()))?.let {
                _pokemonDetail.value = it
            }
        }
    }
}