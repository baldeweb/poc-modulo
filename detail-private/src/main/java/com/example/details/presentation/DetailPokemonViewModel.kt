package com.example.details.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.details_public.data.model.DetailPokemonDTO
import com.example.details_public.domain.DetailPokemonRepository
import com.example.shared_common.presentation.BaseViewModel
import kotlinx.coroutines.launch

class DetailPokemonViewModel(
    private val repository: DetailPokemonRepository
) : BaseViewModel() {
    fun getPokemonDetail(endpoint: String) {
        Log.d("LOG", "SHOW LOADING")
        viewModelScope.launch(webServiceException) {
            val response = repository.getPokemonDetail(endpoint) as DetailPokemonDTO
            Log.d("LOG", "getPokemonDetail(): $response")
            Log.d("LOG", "DISMISS LOADING")
        }
    }
}