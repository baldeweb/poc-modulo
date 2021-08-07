package com.example.details.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.shared_common.presentation.BaseViewModel
import com.example.details.domain.usecase.DetailPokemonUseCase
import kotlinx.coroutines.launch

class DetailPokemonViewModel(
    private val useCase: DetailPokemonUseCase
): BaseViewModel() {
    fun getPokemonDetail(endpoint: String) {
        Log.d("LOG", "DetailPokemonViewModel() >> checkpoint")
        Log.d("LOG", "getPokemonDetail(): endpoint >> $endpoint")
        viewModelScope.launch(webServiceException) {
            useCase.getPokemonDetail(endpoint, {
                Log.d("LOG", "getPokemonDetail(): $it")
            }, {
                Log.d("LOG", "DEU RUIIIM: (${it.httpCode}) - ${it.throwable}")
            })
        }
    }
}