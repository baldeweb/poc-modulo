package com.example.details.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.details.domain.DetailPokemonRepository
import com.example.shared_common.presentation.BaseViewModel
import kotlinx.coroutines.launch

class DetailPokemonViewModel(
    private val repository: DetailPokemonRepository
): BaseViewModel() {
    fun getPokemonDetail(endpoint: String) {
        viewModelScope.launch {
            repository.getPokemonDetail(endpoint, {
                Log.d("LOG", "getPokemonDetail(): $it")
            }, {
                Log.d("LOG", "DEU RUIIIM: (${it.httpCode}) - ${it.throwable}")
            })
        }
    }
}